// gcc neve.c -lglut -lGL -lGLU -lm -o snow_glut

#include <GL/glut.h>
#include <GL/gl.h>
#include <GL/glu.h>
#include <math.h>
#include <unistd.h>
#include <stdlib.h>

#define ESCAPE 27
#define NUM_PARTICLES 2000

struct s_pf {
  float x, y;
  float veloc_x, veloc_y;
} particles[NUM_PARTICLES];

int window;

// Inicializa um floco de neve
void InitParticle(int i)
{
  particles[i].x = ((rand() % 600) - 300) / 50.0f; // -2 a 2
  particles[i].y = 2.0f + (rand() % 100) / 50.0f;  // começa acima da tela

  particles[i].veloc_y = 0.002f + (rand() % 100) / 50000.0f; // queda lenta
  particles[i].veloc_x = ((rand() % 100) - 50) / 50000.0f;   // vento leve
}

// Inicializa tudo
void InitSnow()
{
  for(int i = 0; i < NUM_PARTICLES; i++)
    InitParticle(i);
}

void InitGL(int Width, int Height)
{
  glClearColor(.2f, .2f, .4f, 1.0f); // fundo levemente azul escuro
  glPointSize(2.0f); // tamanho dos flocos

  glMatrixMode(GL_PROJECTION);
  glLoadIdentity();
  gluPerspective(45.0f,(GLfloat)Width/(GLfloat)Height,0.1f,100.0f);

  glMatrixMode(GL_MODELVIEW);

  InitSnow();
}

void ReSizeGLScene(int Width, int Height)
{
  if (Height == 0) Height = 1;

  glViewport(0, 0, Width, Height);

  glMatrixMode(GL_PROJECTION);
  glLoadIdentity();
  gluPerspective(45.0f,(GLfloat)Width/(GLfloat)Height,0.1f,100.0f);

  glMatrixMode(GL_MODELVIEW);
}

void DrawGLScene()
{
  glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
  glLoadIdentity();

  glTranslatef(0.0f, 0.0f, -6.0f);
  glColor3f(1.0f, 1.0f, 1.0f);

  glBegin(GL_POINTS);

  for(int i = 0; i < NUM_PARTICLES; i++) {
    // Atualiza posição
    particles[i].y -= particles[i].veloc_y;
    particles[i].x += particles[i].veloc_x;

    // Efeito de "vento oscilando"
    particles[i].x += sin(particles[i].y * 10.0f) * 0.0005f;

    // Se saiu da tela, reseta
    if(particles[i].y < -2.5f)
      InitParticle(i);

    glVertex3f(particles[i].x, particles[i].y, 0.0f);
  }

  glEnd();
  glPushMatrix();

  // Move o chão pra baixo
  glTranslatef(0.0f, -2.0f, 0.0f);

  // Rotaciona pra ficar plano (horizontal)
  glRotatef(90, 1, 0, 0);
  
  // Cor branca (neve no chão)
  glColor3f(1.0f, 1.0f, 1.0f);
  
  // Desenha um “chão” circular
  GLUquadric *pObj = gluNewQuadric();
  gluDisk(pObj, 0.0, 2.0, 50, 1); // raio 5
  
  glPopMatrix();
  glPushMatrix();
  
  // Move object back and do in place rotation  
    glTranslatef(0.0f, -3.0f, -5.0f);  
    glRotatef(15, 0, 1, 0);

	// Draw something  
	gluQuadricNormals(pObj, GLU_SMOOTH);  

	// white
	glColor3f(1.0f, 1.0f, 1.0f);  

	// Main Body
	glPushMatrix();
		glTranslatef(0.0f, 0.3f, 0.0f); 
		gluSphere(pObj, .54f, 26, 13);
	glPopMatrix();

	// Mid section
	glPushMatrix();
		glTranslatef(0.0f, 1.04f, 0.0f); 
		gluSphere(pObj, 0.37f, 26, 13);
	glPopMatrix();

	// Head
	glPushMatrix(); // save transform matrix state
		glTranslatef(0.0f, 1.5f, 0.0f);
		gluSphere(pObj, 0.24f, 26, 13);
	glPopMatrix(); // restore transform matrix state

	// Nose (orange)
	glColor3f(1.0f, 0.4f, 0.51f);  
	glPushMatrix();
		glTranslatef(0.0f, 1.5f, 0.2f);
		gluCylinder(pObj, 0.04f, 0.0f, 0.3f, 26, 13);  
	glPopMatrix();  

	// Black for hat and eyes
    glColor3f(.0f, .0f, .0f);
    
    // Hat
    glPushMatrix();
        glRotatef(90, 1, 0, 0);
        glTranslatef(.0f, .0f, -2.1f);
        gluCylinder(pObj, 0.1f, 0.1f, 0.3f, 26, 13);
        glRotatef(180, 1, 0, 0);
        gluDisk(pObj,.0f, .1f, 26, 13);
    glPopMatrix();

    // Hat Brim
    glPushMatrix();
        glRotatef(90, 1, 0, 0);
        glTranslatef(.0f, .0f, -1.8f);
        gluCylinder(pObj, 0.2f, 0.2f, 0.1f, 26, 13);
        glTranslatef(.0f, .0f, .1f);
        gluDisk(pObj,.0f, .2f, 26, 13);
        glTranslatef(.0f, .0f, -0.1f);
        glRotatef(180, 1, 0, 0);
        gluDisk(pObj,.0f, .2f, 26, 13);
    glPopMatrix();

    // Eyes
    glPushMatrix();
        glTranslatef(.1f, 1.6f, .2f);
		gluSphere(pObj, .03f, 26, 13);
        glTranslatef(-.2f, .0f, .0f);
        gluSphere(pObj, .03f, 26, 13);
    glPopMatrix();

    // Buttons
    glPushMatrix();
        glTranslatef(.0f, 1.25f, .28f);
        gluSphere(pObj, .03f, 26, 13);
        glTranslatef(.0f, -0.1f, .05f);
        gluSphere(pObj, .03f, 26, 13);
        glTranslatef(.0f, -0.1f, .02f);
        gluSphere(pObj, .03f, 26, 13);
    glPopMatrix();

    // Arms
    glColor3f(.6f, .3f, .05f);

    glPushMatrix();
        glTranslatef(.3f, 1.1f, .0f);
        glRotatef(80, 0, 1, 0);  
        glRotatef(-15, 1, 0, 0);  
		gluCylinder(pObj, 0.04f, 0.04f, .6f, 26, 13);
        
        glTranslatef(.0f, .2f, .4f);
        glRotatef(90, 1, 0, 0);
		gluCylinder(pObj, 0.04f, 0.04f, .2f, 26, 13);
        
        glTranslatef(.0f, -.2f, .2f);
		gluCylinder(pObj, 0.02f, 0.02f, .1f, 26, 13);
    glPopMatrix();

    glPushMatrix();
        glTranslatef(-.3f, 1.1f, .0f);
        glRotatef(-80, 0, 1, 0);  
        glRotatef(-10, 1, 0, 0);  
		gluCylinder(pObj, 0.04f, 0.04f, .6f, 26, 13);
        
        glRotatef(180, 0, 0, 1);
        glTranslatef(.0f, .2f, .4f);
        glRotatef(90, 1, 0, 0);
		gluCylinder(pObj, 0.03f, 0.03f, .2f, 26, 13);
        
        glTranslatef(.0f, -.2f, .2f);
		gluCylinder(pObj, 0.04f, 0.04f, .18f, 26, 13);
    glPopMatrix();


    
    // Restore the matrix state  
    glPopMatrix();  


  glutSwapBuffers();
  usleep(16000); // ~60 FPS
}

void keyPressed(unsigned char key, int x, int y)
{
  if (key == ESCAPE) {
    glutDestroyWindow(window);
    exit(0);
  }
}

int main(int argc, char **argv)
{
  glutInit(&argc, argv);
  glutInitDisplayMode(GLUT_RGBA | GLUT_DOUBLE);

  glutInitWindowSize(640, 480);
  glutInitWindowPosition(0, 0);

  window = glutCreateWindow("Neve");

  glutDisplayFunc(&DrawGLScene);
  glutIdleFunc(&DrawGLScene);
  glutReshapeFunc(&ReSizeGLScene);
  glutKeyboardFunc(&keyPressed);

  glutFullScreen();

  InitGL(640, 480);
  glutMainLoop();

  return 0;
}