// IMPORTANTE: HÁ UM GUIA DE MANUSEIO DO BONECO DENOMINADO tutorialManuseioDoBoneco.png
// É recomendado vê-lo antes de tentar movimentar o boneco

// Códigos de compilação
// Lnx: gcc aceno.c -lglut -lGL -lGLU -lm -o boneco && ./boneco
// Win: gcc aceno.c -lfreeglut -lopengl32 -lglu32 -o boneco; .\boneco

#include <GL/freeglut.h>
#include <math.h>
  
static GLfloat bodyYRot = 0.0f;

typedef enum WaveState {
    WAVE_IDLE,
    WAVE_ENTER,
    WAVE_LOOP,
    WAVE_EXIT
} WaveState;

static WaveState waveState = WAVE_IDLE;

// Shoulder
static GLfloat rightShoulderXRrot = 90.0f;
static GLfloat rightShoulderYRrot = 20.0f;

static GLfloat leftShoulderXRrot = 90.0f;
static GLfloat leftShoulderYRrot = 20.0f;

static GLfloat rightArmXRrot = 00.0f;
static GLfloat rightArmYRrot = 20.0f;

static GLfloat leftArmXRrot = -20.0f;
static GLfloat leftArmYRrot = 00.0f;

// Animação
static GLfloat leftShoulderRot = 0.0f;
static GLfloat rightShoulderRot = 0.0f;

static GLfloat leftElbowRot = 0.0f;
static GLfloat rightElbowRot = 0.0f;

static GLfloat leftThighRot = 0.0f;
static GLfloat rightThighRot = 0.0f;

static GLfloat leftKneeRot = 0.0f;
static GLfloat rightKneeRot = 0.0f;

static float waveT = 0.0f;
static float t = 0.0f;
static int waveCycles = 0;
static const int MAX_CYCLES = 3;

// Change viewing volume and viewport.  Called when window is resized  
void ChangeSize(int w, int h)  
    {   
    GLfloat fAspect;  
  
    // Prevent a divide by zero  
    if(h == 0)  
        h = 1;  
  
    // Set Viewport to window dimensions  
    glViewport(0, 0, w, h); 
  
    fAspect = (GLfloat)w/(GLfloat)h;
  
    // Reset coordinate system  
    glMatrixMode(GL_PROJECTION);  
    glLoadIdentity();  
  
    // Produce the perspective projection  
    gluPerspective(35.0f, fAspect, 1.0, 40.0);  
  
    glMatrixMode(GL_MODELVIEW);  
    glLoadIdentity();  
    }  
  
  
// This function does any needed initialization on the rendering context.  Here it sets up and initializes the lighting for the scene.  
void SetupRC(){  

    // Light values and coordinates  
    GLfloat  whiteLight[] = { 0.05f, 0.05f, 0.05f, 1.0f };  
    GLfloat  sourceLight[] = { 0.25f, 0.25f, 0.25f, 1.0f };  
    GLfloat  lightPos[] = { -10.f, 5.0f, 5.0f, 1.0f };  
  
    glEnable(GL_DEPTH_TEST);    // Hidden surface removal  
    glFrontFace(GL_CCW);        // Counter clock-wise polygons face out  
    glEnable(GL_CULL_FACE);     // Do not calculate inside  
  
    // Enable lighting  
    glEnable(GL_LIGHTING);  
  
    // Setup and enable light 0  
    glLightModelfv(GL_LIGHT_MODEL_AMBIENT,whiteLight);  
    glLightfv(GL_LIGHT0,GL_AMBIENT,sourceLight);  
    glLightfv(GL_LIGHT0,GL_DIFFUSE,sourceLight);  
    glLightfv(GL_LIGHT0,GL_POSITION,lightPos);  
    glEnable(GL_LIGHT0);  
  
    // Enable color tracking  
    glEnable(GL_COLOR_MATERIAL);  
      
    // Set Material properties to follow glColor values  
    glColorMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE);  
  
    // Background color  
    glClearColor(.6f, .8f, 0.9f, 1.0f);  
}  
  
void rotate(GLfloat *angle, float delta){
    *angle += delta;
    *angle = (GLfloat)((int)(*angle) % 360);
}

void startWave(){
    waveState = WAVE_ENTER;
    waveT = 0.0f;
    waveCycles = 0;
}

// Respond to arrow keys (rotacionar snowman)
void SpecialKeys(unsigned char key, int x, int y){  

    switch(key){
        case ',': rotate(&bodyYRot, -5); break;
        case '.': rotate(&bodyYRot, +5); break;
        case 'r': startWave(); break;

    }
    bodyYRot = (GLfloat)((int)bodyYRot % 360);  
    // Refresh the Window  
    glutPostRedisplay();
}
  
// Called to draw scene  
void RenderScene(void){

    GLUquadricObj *pObj;    // Quadric Object  
      
    // Clear the window with current clearing color  
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);  
  
    // Save the matrix state and do the rotations  
    glPushMatrix();

	// Move object back and do in place rotation  
	glTranslatef(0.0f, -1.0f, -5.0f);  

    // Draw something  
	pObj = gluNewQuadric();  
	gluQuadricNormals(pObj, GLU_SMOOTH);  
    
    // Color
    glColor3f(.9f, .6f, .5f);
    glTranslatef(.0f, 1.0f, -5.0f);
    
    // Head
    glPushMatrix();
        glTranslatef(.0f, 2.2f, -.5f);
        gluSphere(pObj, .25f, 26, 26);

        // Neck
        glTranslatef(.0f, -.2f, .0f);
        glRotatef(90, 1, 0, 0);
        glutSolidCylinder(.1f, .2f, 26, 26);
        glRotatef(-90, 1, 0, 0);
    glPopMatrix();    
    
    // Torso
    glPushMatrix();
        glTranslatef(.0f, 1.3f, -.5f);
        glScalef(.7f, 1.0f, .7f);
        gluSphere(pObj, .6f, 26, 26);
    glPopMatrix();
    
    // Left Arm
    glPushMatrix();
        glTranslatef(.3f, 1.8f, -.5f);

        // Shoulder
        gluSphere(pObj, .15f, 26, 26);

        glRotatef(90.0f + leftShoulderRot, 1, 0, 0);
        glRotatef(leftShoulderYRrot, 0, 1, 0);

        // Arm 
        glutSolidCylinder(.1f, .75f, 26, 26);

        glTranslatef(0.0f, 0.0f, .75f);

        // Elbow
        gluSphere(pObj, .12f, 26, 26);

        glPushMatrix();
            glRotatef(leftElbowRot, 1, 0, 0);
            glRotatef(leftArmYRrot, 0, 1, 0);

            // Forearm
            glutSolidCylinder(.1f, .6f, 26, 26);

            glTranslatef(0.0f, 0.0f, .6f);

            // Hand
            gluSphere(pObj, .15f, 26, 26);
        glPopMatrix();
    glPopMatrix();
    
    // Right Arm
    glPushMatrix();
        glTranslatef(-.3f, 1.8f, -.5f);

        // Shoulder
        gluSphere(pObj, .15f, 26, 26);

        glRotatef(90.0f + rightShoulderRot, 1, 0, 0);
        glRotatef(-rightShoulderYRrot, 0, 1, 0);

        // Arm 
        glutSolidCylinder(.1f, .75f, 26, 26);

        glTranslatef(0.0f, 0.0f, .75f);

        // Elbow
        gluSphere(pObj, .12f, 26, 26);

        glPushMatrix();
            glRotatef(rightArmXRrot, 1, 0, 0);
            glRotatef(rightElbowRot, 0, 1, 0);

            // Forearm
            glutSolidCylinder(.1f, .6f, 26, 26);

            glTranslatef(0.0f, 0.0f, .6f);

            // Hand
            gluSphere(pObj, .15f, 26, 26);
        glPopMatrix();
    glPopMatrix();      

    // Lower body
    glPushMatrix();
        glTranslatef(.0f, .5f, -.5f);
        glScalef(1.5f, 1.2f, 1.0f);
        gluSphere(pObj, .3f, 26, 26);
    glPopMatrix();
    
    // Left Leg
    glPushMatrix();
        glTranslatef(-.3f, .2f, -.5f);

        // Hip
        gluSphere(pObj, .2f, 26, 26);

        glRotatef(90.0f + leftThighRot, 1, 0, 0);

        // Upper leg
        glutSolidCylinder(.15f, .8f, 26, 26);

        glTranslatef(0.0f, 0.0f, .8f);

        // Knee
        gluSphere(pObj, .2f, 26, 26);

        glPushMatrix();
            glRotatef(leftKneeRot, 1, 0, 0);

            // Lower leg
            glutSolidCylinder(.15f, 1.0f, 26, 26);

            glTranslatef(0.0f, 0.0f, 1.0f);

            // Foot
            gluSphere(pObj, .2f, 26, 26);
        glPopMatrix();
    glPopMatrix();

    // Right Leg
    glPushMatrix();
        glTranslatef(.3f, .2f, -.5f);

        // Hips
        gluSphere(pObj, .2f, 26, 26);

        glRotatef(90.0f + rightThighRot, 1, 0, 0);

        // Upper leg
        glutSolidCylinder(.15f, .8f, 26, 26);

        glTranslatef(0.0f, 0.0f, .8f);

        // Knee
        gluSphere(pObj, .2f, 26, 26);

        glPushMatrix(); 
            glRotatef(rightKneeRot, 1, 0, 0);

            // Lower leg
            glutSolidCylinder(.15f, 1.0f, 26, 26);

            glTranslatef(0.0f, 0.0f, 1.0f);

            // Foot
            gluSphere(pObj, .2f, 26, 26);
        glPopMatrix();
    glPopMatrix(); 

    // Restore the matrix state  
    glPopMatrix();  
    
    // Buffer swap  
    glutSwapBuffers();  

} 


float suave(float current, float target, float step){
    if(current < target) return fmin(current + step, target);
    if(current > target) return fmax(current - step, target);
    return target;
}

void animacao(int value){

    float wave = sin(waveT);

    switch(waveState){

        case WAVE_ENTER:

            rightShoulderRot = suave(rightShoulderRot, -150.0f, 4.0f);
            rightElbowRot    = suave(rightElbowRot, 30.0f, 1.0f);

            // quando chega perto da pose → começa loop
            if(fabs(rightShoulderRot + 150.0f) < 1.0f){
                waveState = WAVE_LOOP;
                waveT = 0.0f;
            }
        break;

        case WAVE_LOOP:

            waveT += 0.15f;

            rightShoulderRot = -150.0f;
            rightElbowRot = 30.0f + wave * 20.0f;

            if(waveT >= 2 * 3.1415f){
                waveT -= 2 * 3.1415f;
                waveCycles++;

                if(waveCycles >= MAX_CYCLES){
                    waveState = WAVE_EXIT;
                }
            }
        break;

        case WAVE_EXIT:

            rightShoulderRot = suave(rightShoulderRot, 0.0f, 2.5f);
            rightElbowRot    = suave(rightElbowRot, 0.0f, 2.5f);

            if(fabs(rightShoulderRot) < 0.5f &&
               fabs(rightElbowRot) < 0.5f){
                waveState = WAVE_IDLE;
            }
        break;

        case WAVE_IDLE:
            break;
    }

    glutPostRedisplay();
    glutTimerFunc(16, animacao, 0);
}

int main(int argc, char *argv[]){

    glutInit(&argc, argv);  
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);  
    glutInitWindowSize(800, 600);  
    glutCreateWindow("Eddy Azbear o boneco");  

    glutReshapeFunc(ChangeSize);  
    glutKeyboardFunc(SpecialKeys);  
    glutDisplayFunc(RenderScene);  
    
    SetupRC();  

    glutTimerFunc(16, animacao, 0);

    glutMainLoop();  
      
    return 0; 
}
