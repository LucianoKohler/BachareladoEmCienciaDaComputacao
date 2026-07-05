// g++ jogo_pinguim.cpp glad.c -o jogo_pinguim -I. -lglfw -lGL -ldl && ./jogo_pinguim   
#include <glad/glad.h>
#include <GLFW/glfw3.h>
#include <glm/glm.hpp>
#include <glm/gtc/matrix_transform.hpp>
#include <glm/gtc/type_ptr.hpp>

#include <iostream>
#include <vector>
#include <cstdlib>
#include <ctime>
#include <string>
#include <cmath>

#define STB_IMAGE_IMPLEMENTATION
#include "stb_image.h"

// Tamanho da janela
const unsigned int SCREEN_WIDTH = 500;
const unsigned int SCREEN_HEIGHT = 800;

// Variáveis do jogo
bool gameOver = false;
float score = 0.0f;
float backgroundScroll = 0.0f;

// Variáveis do jogador
float playerX = 200.0f;
const float playerY = 80.0f; 
const float playerWidth = 70.0f;
const float playerHeight = 70.0f;
const float playerSpeed = 400.0f; 

// Obstáculos e atributos
enum ObstacleType { WATER_HOLE, WALRUS, FAST_PENGUIN };

struct Obstacle {
    ObstacleType type;
    float x, y;
    float width, height;
    float velocityX;    
    float warningTimer; 
};

std::vector<Obstacle> obstacles;
float baseObstacleSpeed = 300.0f;
float spawnTimer = 0.0f;
float spawnInterval = 1.2f; 

// Peixes e atributos
enum FishRarity { COMMON, UNCOMMON, RARE, LEGENDARY };

struct Fish {
    FishRarity rarity;
    float x, y;
    float width, height;
    int points;
};

std::vector<Fish> fishes;
float fishSpawnTimer = 0.0f;
float fishSpawnInterval = 2.0f;

// Shader que indica ONDE o objeto ficará
const char* vertexShaderSource = R"(
#version 330 core
layout (location = 0) in vec4 vertex; 
out vec2 TexCoords;

uniform mat4 model;
uniform mat4 projection;
uniform vec4 uvRect; 

void main() {
    TexCoords = vec2(vertex.z * (uvRect.z - uvRect.x) + uvRect.x, 
                     vertex.w * (uvRect.w - uvRect.y) + uvRect.y);
    gl_Position = projection * model * vec4(vertex.xy, 0.0, 1.0);
}
)";

// Shader que indica COMO o objeto ficará (pinta ele)
const char* fragmentShaderSource = R"(
#version 330 core
in vec2 TexCoords;
out vec4 color;
uniform sampler2D sprite;
void main() {
    color = texture(sprite, TexCoords);
    if(color.a < 0.1) discard; 
}
)";

// Carregamento de texturas
unsigned int loadTexture(const char* path) {
    unsigned int textureID;
    glGenTextures(1, &textureID);
    glBindTexture(GL_TEXTURE_2D, textureID);

    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

    int width, height, nrChannels;
    stbi_set_flip_vertically_on_load(true);
    unsigned char *data = stbi_load(path, &width, &height, &nrChannels, 0);
    
    if (data) {
        GLenum format = (nrChannels == 4) ? GL_RGBA : GL_RGB;
        glTexImage2D(GL_TEXTURE_2D, 0, format, width, height, 0, format, GL_UNSIGNED_BYTE, data);
        glGenerateMipmap(GL_TEXTURE_2D);
    } else {
        std::cout << "Erro ao carregar textura (Usando bloco preto como fallback): " << path << std::endl;
        // Se não achar textura, usa um quadrado preto
        unsigned char blackPixel[] = {0, 0, 0, 255};
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, 1, 1, 0, GL_RGBA, GL_UNSIGNED_BYTE, blackPixel);
    }
    stbi_image_free(data);
    return textureID;
}

// Pinta o sprite
void drawSprite(unsigned int shaderProgram, unsigned int VAO, unsigned int texture, glm::vec2 position, glm::vec2 size, glm::mat4 &projection, bool flipX = false, glm::vec4 uvRect = glm::vec4(0.0f, 0.0f, 1.0f, 1.0f)) {
    glUseProgram(shaderProgram);
    glm::mat4 model = glm::mat4(1.0f);
    model = glm::translate(model, glm::vec3(position, 0.0f));
    model = glm::scale(model, glm::vec3(size, 1.0f));

    glUniformMatrix4fv(glGetUniformLocation(shaderProgram, "model"), 1, GL_FALSE, glm::value_ptr(model));
    glUniformMatrix4fv(glGetUniformLocation(shaderProgram, "projection"), 1, GL_FALSE, glm::value_ptr(projection));

    glm::vec4 finalUV = flipX ? glm::vec4(uvRect.z, uvRect.y, uvRect.x, uvRect.w) : uvRect;
    glUniform4f(glGetUniformLocation(shaderProgram, "uvRect"), finalUV.x, finalUV.y, finalUV.z, finalUV.w);

    glActiveTexture(GL_TEXTURE0);
    glBindTexture(GL_TEXTURE_2D, texture);

    glBindVertexArray(VAO);
    glDrawArrays(GL_TRIANGLES, 0, 6);
}

// Printa a pontuação
void drawScore(int currentScore, float x, float y, float size, unsigned int shaderProgram, unsigned int VAO, unsigned int texture, glm::mat4 &projection) {
    std::string scoreStr = std::to_string(currentScore);
    float digitWidth = size * 0.7f; 
    float totalWidth = scoreStr.length() * digitWidth;
    float startX = x - (totalWidth / 2.0f); 

    for (size_t i = 0; i < scoreStr.length(); i++) {
        int digit = scoreStr[i] - '0';
        float u_min = digit * 0.1f;
        float u_max = (digit + 1) * 0.1f;
        drawSprite(shaderProgram, VAO, texture, glm::vec2(startX + i * digitWidth, y), glm::vec2(digitWidth, size), projection, false, glm::vec4(u_min, 0.0f, u_max, 1.0f));
    }
}

// Checa colisão entre inimigo/peixe e jogador
bool checkCollision(float ax, float ay, float aw, float ah, float bx, float by, float bw, float bh) {
    float margin = 10.0f;
    return ax + margin < bx + bw - margin && 
           ax + aw - margin > bx + margin && 
           ay + margin < by + bh - margin && 
           ay + ah - margin > by + margin;
}

// Controles
void processInput(GLFWwindow* window, float deltaTime) {
    if (glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_PRESS)
        glfwSetWindowShouldClose(window, true);

    if (!gameOver) {
        if (glfwGetKey(window, GLFW_KEY_LEFT) == GLFW_PRESS) {
            playerX -= playerSpeed * deltaTime;
            if (playerX < 20.0f) playerX = 20.0f; 
        }
        if (glfwGetKey(window, GLFW_KEY_RIGHT) == GLFW_PRESS) {
            playerX += playerSpeed * deltaTime;
            if (playerX > SCREEN_WIDTH - playerWidth - 20) playerX = SCREEN_WIDTH - playerWidth - 20; 
        }
    } else {
        if (glfwGetKey(window, GLFW_KEY_R) == GLFW_PRESS) {
            gameOver = false;
            score = 0.0f;
            backgroundScroll = 0.0f;
            baseObstacleSpeed = 300.0f;
            spawnInterval = 1.2f;
            fishSpawnTimer = 0.0f;
            playerX = (SCREEN_WIDTH - playerWidth) / 2.0f;
            obstacles.clear();
            fishes.clear(); 
        }
    }
}

int main() {
    std::srand(static_cast<unsigned int>(std::time(nullptr)));

    glfwInit();
    glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
    glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
    glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

    GLFWwindow* window = glfwCreateWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Caverna Congelada - Penguin Run", NULL, NULL);
    glfwMakeContextCurrent(window);
    gladLoadGLLoader((GLADloadproc)glfwGetProcAddress);

    glEnable(GL_BLEND);
    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

    unsigned int vertexShader = glCreateShader(GL_VERTEX_SHADER);
    glShaderSource(vertexShader, 1, &vertexShaderSource, NULL);
    glCompileShader(vertexShader);

    unsigned int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
    glShaderSource(fragmentShader, 1, &fragmentShaderSource, NULL);
    glCompileShader(fragmentShader);

    unsigned int shaderProgram = glCreateProgram();
    glAttachShader(shaderProgram, vertexShader);
    glAttachShader(shaderProgram, fragmentShader);
    glLinkProgram(shaderProgram);

    glDeleteShader(vertexShader);
    glDeleteShader(fragmentShader);

    float vertices[] = { 
        0.0f, 1.0f, 0.0f, 1.0f,
        1.0f, 0.0f, 1.0f, 0.0f,
        0.0f, 0.0f, 0.0f, 0.0f, 
        0.0f, 1.0f, 0.0f, 1.0f,
        1.0f, 1.0f, 1.0f, 1.0f,
        1.0f, 0.0f, 1.0f, 0.0f
    };

    unsigned int VBO, VAO;
    glGenVertexArrays(1, &VAO);
    glGenBuffers(1, &VBO);
    glBindVertexArray(VAO);
    glBindBuffer(GL_ARRAY_BUFFER, VBO);
    glBufferData(GL_ARRAY_BUFFER, sizeof(vertices), vertices, GL_STATIC_DRAW);
    glEnableVertexAttribArray(0);
    glVertexAttribPointer(0, 4, GL_FLOAT, GL_FALSE, 4 * sizeof(float), (void*)0);

    // Carrega todas as texturas
    unsigned int texBackground = loadTexture("sprites/background.png");
    glBindTexture(GL_TEXTURE_2D, texBackground);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

    unsigned int texPlayer     = loadTexture("sprites/bluePenguin.png");
    unsigned int texWaterHole  = loadTexture("sprites/hole.png"); 
    unsigned int texWalrus     = loadTexture("sprites/walrus.png");  
    unsigned int texRedPenguin = loadTexture("sprites/redPenguin.png"); 
    unsigned int texWarning    = loadTexture("sprites/warning.png"); 
    unsigned int texNumbers    = loadTexture("sprites/numbers.png");
    unsigned int texGameOver   = loadTexture("sprites/youLose.png");
    unsigned int texScoreLabel = loadTexture("sprites/score.png"); 
    unsigned int texRestart    = loadTexture("sprites/restart.png"); 
    unsigned int texFishCommon    = loadTexture("sprites/commonFish.png");
    unsigned int texFishUncommon  = loadTexture("sprites/uncommonFish.png");
    unsigned int texFishRare      = loadTexture("sprites/rareFish.png");
    unsigned int texFishLegendary = loadTexture("sprites/legendaryFish.png");

    glm::mat4 projection = glm::ortho(0.0f, static_cast<float>(SCREEN_WIDTH), 0.0f, static_cast<float>(SCREEN_HEIGHT), -1.0f, 1.0f);

    float lastFrame = 0.0f;

    while (!glfwWindowShouldClose(window)) {
        float currentFrame = glfwGetTime();
        float deltaTime = currentFrame - lastFrame;
        lastFrame = currentFrame;

        processInput(window, deltaTime);

        if (!gameOver) {
            score += deltaTime * 15.0f; 
            spawnTimer += deltaTime;
            fishSpawnTimer += deltaTime; 
            baseObstacleSpeed += deltaTime * 2.0f; 

            // Muda o scroll do background
            backgroundScroll += (baseObstacleSpeed / 1600.0f) * deltaTime;
            if (backgroundScroll >= 1.0f) backgroundScroll -= 1.0f;

            // Gera um obstáculo aleatório
            if (spawnTimer >= spawnInterval) {
                spawnTimer = 0.0f;
                Obstacle obs;
                obs.warningTimer = 0.0f;
                obs.velocityX = 0.0f;
                int dropChance = std::rand() % 100;

                if (dropChance < 45) { 
                    obs.type = WATER_HOLE;
                    obs.width = 100.0f; obs.height = 100.0f;
                    obs.x = static_cast<float>(30 + std::rand() % static_cast<int>(SCREEN_WIDTH - obs.width-50));
                    obs.y = SCREEN_HEIGHT + 50.0f;
                } else if (dropChance < 75) { 
                    obs.type = WALRUS;
                    obs.width = 80.0f; obs.height = 70.0f;
                    obs.x = static_cast<float>(30 + std::rand() % static_cast<int>(SCREEN_WIDTH - obs.width-50));
                    obs.y = SCREEN_HEIGHT + 50.0f;
                    obs.velocityX = 200.0f;
                    if (std::rand() % 2 == 0) obs.velocityX = -obs.velocityX;
                } else { 
                    obs.type = FAST_PENGUIN;
                    obs.width = 80.0f; obs.height = 80.0f;
                    obs.x = static_cast<float>(30 + std::rand() % static_cast<int>(SCREEN_WIDTH - obs.width-50));
                    obs.y = SCREEN_HEIGHT - obs.height; 
                    obs.warningTimer = 1.2f; 
                }

                obstacles.push_back(obs);
                if (spawnInterval > 0.6f) spawnInterval -= 0.02f;
            }

            // Gera um peixe aleatório
            if (fishSpawnTimer >= fishSpawnInterval) {
                fishSpawnTimer = 0.0f;
                Fish f;
                f.width = 60.0f; f.height = 60.0f; 
                f.x = static_cast<float>(30 + std::rand() % static_cast<int>(SCREEN_WIDTH - f.width-50));
                f.y = SCREEN_HEIGHT + 50.0f;

                int fishChance = std::rand() % 100;
                if (fishChance < 60) { f.rarity = COMMON; f.points = 10; } 
                else if (fishChance < 85) { f.rarity = UNCOMMON; f.points = 25; } 
                else if (fishChance < 95) { f.rarity = RARE; f.points = 50; } 
                else { f.rarity = LEGENDARY; f.points = 250; }

                fishes.push_back(f);
            }

            // Atualiza a posição dos inimigos
            for (auto it = obstacles.begin(); it != obstacles.end(); ) {
                if (it->type == WATER_HOLE) {
                    it->y -= baseObstacleSpeed * deltaTime;
                } else if (it->type == WALRUS) {
                    it->y -= baseObstacleSpeed * deltaTime;
                    it->x += it->velocityX * deltaTime;
                    if (it->x <= 0.0f) {
                        it->x = 0.0f; it->velocityX = -it->velocityX; 
                    } else if (it->x >= SCREEN_WIDTH - it->width) {
                        it->x = SCREEN_WIDTH - it->width; it->velocityX = -it->velocityX;
                    }
                } else if (it->type == FAST_PENGUIN) {
                    if (it->warningTimer > 0.0f) {
                        it->warningTimer -= deltaTime;
                        it->y = SCREEN_HEIGHT - it->height; 
                    } else {
                        it->y -= (baseObstacleSpeed * 3.0f) * deltaTime;
                    }
                }

                bool canCollide = !(it->type == FAST_PENGUIN && it->warningTimer > 0.0f);
                if (canCollide && checkCollision(playerX, playerY, playerWidth, playerHeight, it->x, it->y, it->width, it->height)) {
                    gameOver = true;
                }

                if (it->y < -150.0f) it = obstacles.erase(it);
                else ++it;
            }

            // Atualiza a posição dos peixes
            for (auto it = fishes.begin(); it != fishes.end(); ) {
                it->y -= baseObstacleSpeed * deltaTime; 
                if (checkCollision(playerX, playerY, playerWidth, playerHeight, it->x, it->y, it->width, it->height)) {
                    score += it->points; 
                    it = fishes.erase(it); 
                } else if (it->y < -100.0f) {
                    it = fishes.erase(it); 
                } else {
                    ++it;
                }
            }
        }

        // Separação das entidades em camadas
        glClearColor(0.1f, 0.1f, 0.2f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT);

        // Z = 1: Background 
        drawSprite(shaderProgram, VAO, texBackground, glm::vec2(0.0f, 0.0f), glm::vec2(SCREEN_WIDTH, SCREEN_HEIGHT), projection, false, glm::vec4(0.0f, backgroundScroll, 1.0f, backgroundScroll + 0.5f));

        // Z = 2: Buracos 
        for (const auto& obs : obstacles) {
            if (obs.type == WATER_HOLE) {
                drawSprite(shaderProgram, VAO, texWaterHole, glm::vec2(obs.x, obs.y), glm::vec2(obs.width, obs.height), projection);
            }
        }

        // Z = 3: Peixes
        for (const auto& f : fishes) {
            unsigned int currentFishTex;
            if (f.rarity == COMMON) currentFishTex = texFishCommon;
            else if (f.rarity == UNCOMMON) currentFishTex = texFishUncommon;
            else if (f.rarity == RARE) currentFishTex = texFishRare;
            else currentFishTex = texFishLegendary;

            drawSprite(shaderProgram, VAO, currentFishTex, glm::vec2(f.x, f.y), glm::vec2(f.width, f.height), projection);
        }

        // Z = 4: Outros obstáculos
        for (const auto& obs : obstacles) {
            if (obs.type == WATER_HOLE) continue; 

            unsigned int currentTex;
            bool flipSprite = false;
            bool skipDraw = false; 

            if (obs.type == WALRUS) {
                currentTex = texWalrus;
                if (obs.velocityX < 0.0f) flipSprite = true; 
            } else if (obs.type == FAST_PENGUIN) {
                if (obs.warningTimer > 0.0f) {
                    currentTex = texWarning;
                    if (std::fmod(obs.warningTimer, 0.2f) < 0.1f) {
                        skipDraw = true;
                    }
                }
                else currentTex = texRedPenguin;
            }

            if (!skipDraw) {
                drawSprite(shaderProgram, VAO, currentTex, glm::vec2(obs.x, obs.y), glm::vec2(obs.width, obs.height), projection, flipSprite);
            }
        }

        // Z = 5: Jogador
        drawSprite(shaderProgram, VAO, texPlayer, glm::vec2(playerX, playerY), glm::vec2(playerWidth, playerHeight), projection);

        // Z = 6: Interface
        if (!gameOver) {
            drawScore((int)score, SCREEN_WIDTH / 2.0f, SCREEN_HEIGHT - 40.0f, 30.0f, shaderProgram, VAO, texNumbers, projection);
        } else { // Fim de jogo
            float baseGoWidth = 350.0f;
            float baseGoHeight = 100.0f;
            float pulseScale = 1.0f + std::sin(glfwGetTime() * 4.0f) * 0.15f;
            float goWidth = baseGoWidth * pulseScale;
            float goHeight = baseGoHeight * pulseScale;
            float goX = (SCREEN_WIDTH - goWidth) / 2.0f;
            float goY = (SCREEN_HEIGHT / 2.0f + 120.0f) - (goHeight - baseGoHeight) / 2.0f;
            drawSprite(shaderProgram, VAO, texGameOver, glm::vec2(goX, goY), glm::vec2(goWidth, goHeight), projection);
            
            float labelWidth = 200.0f;
            float labelHeight = 40.0f;
            float labelX = (SCREEN_WIDTH - labelWidth) / 2.0f;
            float labelY = SCREEN_HEIGHT / 2.0f; 
            drawSprite(shaderProgram, VAO, texScoreLabel, glm::vec2(labelX, labelY), glm::vec2(labelWidth, labelHeight), projection);

            drawScore((int)score, SCREEN_WIDTH / 2.0f, SCREEN_HEIGHT / 2.0f - 50.0f, 40.0f, shaderProgram, VAO, texNumbers, projection);

            float restartWidth = 400.0f;
            float restartHeight = 40.0f;
            float restartX = (SCREEN_WIDTH - restartWidth) / 2.0f;
            float restartY = SCREEN_HEIGHT / 2.0f - 140.0f; 
            drawSprite(shaderProgram, VAO, texRestart, glm::vec2(restartX, restartY), glm::vec2(restartWidth, restartHeight), projection);
        }

        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    glDeleteVertexArrays(1, &VAO);
    glDeleteBuffers(1, &VBO);
    glDeleteProgram(shaderProgram);

    glfwTerminate();
    return 0;
}