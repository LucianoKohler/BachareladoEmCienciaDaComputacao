// IMPORTANTE: HÁ UM GUIA DE MANUSEIO DO BONECO DENOMINADO tutorialManuseioDoBoneco.png
// É recomendado vê-lo antes de tentar movimentar o boneco

// Códigos de compilação
// Lnx: gcc bonecoArticulado.c -lglut -lGL -lGLU -lm -o boneco && ./boneco
// Win: gcc bonecoArticulado.c -lfreeglut -lopengl32 -lglu32 -o boneco; .\boneco

#include <GL/freeglut.h>
  
// Rotation
static GLfloat cameraYRot = 0.0f;

// Shoulder
static GLfloat rightShoulderXRrot = 90.0f;
static GLfloat rightShoulderYRrot = 20.0f;

static GLfloat leftShoulderXRrot = 90.0f;
static GLfloat leftShoulderYRrot = 20.0f;

static GLfloat rightArmXRrot = 00.0f;
static GLfloat rightArmYRrot = 20.0f;

static GLfloat leftArmXRrot = 00.0f;
static GLfloat leftArmYRrot = -20.0f;

static GLfloat rightLegXRrot = 90.0f;
static GLfloat rightLegYRrot = 20.0f;

static GLfloat leftLegXRrot = 90.0f;
static GLfloat leftLegYRrot = 20.0f;

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

// Respond to arrow keys (rotacionar snowman)
void SpecialKeys(unsigned char key, int x, int y){  

    switch(key){
        case ',':rotate(&cameraYRot, -5); break;

        case '.':rotate(&cameraYRot, +5); break;

        // Right Shoulder rotations: 1,2,q,w 
        // Right arm rotations: 3,4,e,r
        case '1': rotate(&rightShoulderXRrot, +5); break;
        case '2': rotate(&rightShoulderXRrot, -5); break;
        case 'q':
        case 'Q': rotate(&rightShoulderYRrot, +5); break;
        case 'w':
        case 'W': rotate(&rightShoulderYRrot, -5); break;

        case '3': rotate(&rightArmXRrot, +5); break;
        case '4': rotate(&rightArmXRrot, -5); break;
        case 'e':
        case 'E': rotate(&rightArmYRrot, +5); break;
        case 'r':
        case 'R': rotate(&rightArmYRrot, -5); break;

        // Left Shoulder rotations: 5,6,t,y
        // Left Arm rotations: 7,8,u,i
        case '5': rotate(&leftShoulderXRrot, +5); break;
        case '6': rotate(&leftShoulderXRrot, -5); break;
        case 't':
        case 'T': rotate(&leftShoulderYRrot, +5); break;
        case 'y':
        case 'Y': rotate(&leftShoulderYRrot, -5); break;

        case '7': rotate(&leftArmXRrot, +5); break;
        case '8': rotate(&leftArmXRrot, -5); break;
        case 'u':
        case 'U': rotate(&leftArmYRrot, +5); break;
        case 'i':
        case 'I': rotate(&leftArmYRrot, -5); break;



        // Right Leg rotations: a,s,z,x 
        // Left Leg rotations: d,f,c,v
        case 'a':
        case 'A': rotate(&rightLegXRrot, +5); break;
        case 's':
        case 'S': rotate(&rightLegXRrot, -5); break;
        case 'z':
        case 'Z': rotate(&rightLegYRrot, +5); break;
        case 'x':
        case 'X': rotate(&rightLegYRrot, -5); break;

        case 'd':
        case 'D': rotate(&leftLegXRrot, +5); break;
        case 'f':
        case 'F': rotate(&leftLegXRrot, -5); break;
        case 'c':
        case 'C': rotate(&leftLegYRrot, +5); break;
        case 'v':
        case 'V': rotate(&leftLegYRrot, -5); break;

    }
        cameraYRot = (GLfloat)((const int)cameraYRot % 360);  
  
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
	glRotatef(cameraYRot, 0.0f, 1.0f, .0f);  

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

        glRotatef(leftShoulderXRrot, 1, 0, 0);
        glRotatef(leftShoulderYRrot, 0, 1, 0);

        // Arm 
        glutSolidCylinder(.1f, .75f, 26, 26);

        glTranslatef(0.0f, 0.0f, .75f);

        // Elbow
        gluSphere(pObj, .12f, 26, 26);

        glPushMatrix();
            glRotatef(leftArmXRrot, 1, 0, 0);
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

        glRotatef(rightShoulderXRrot, 1, 0, 0);
        glRotatef(-rightShoulderYRrot, 0, 1, 0);

        // Arm 
        glutSolidCylinder(.1f, .75f, 26, 26);

        glTranslatef(0.0f, 0.0f, .75f);

        // Elbow
        gluSphere(pObj, .12f, 26, 26);

        glPushMatrix();
            glRotatef(rightArmXRrot, 1, 0, 0);
            glRotatef(rightArmYRrot, 0, 1, 0);

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

        glRotatef(leftLegXRrot, 1, 0, 0);
        glRotatef(-leftLegYRrot, 0, 1, 0);

        // Upper leg
        glutSolidCylinder(.15f, .8f, 26, 26);

        glTranslatef(0.0f, 0.0f, .8f);

        // Knee
        gluSphere(pObj, .2f, 26, 26);

        glPushMatrix();
            glRotatef(leftLegXRrot - 90.0f, 1, 0, 0);

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

        // Hip
        gluSphere(pObj, .2f, 26, 26);

        glRotatef(rightLegXRrot, 1, 0, 0);
        glRotatef(rightLegYRrot, 0, 1, 0);

        // Upper leg (coxa)
        glutSolidCylinder(.15f, .8f, 26, 26);

        glTranslatef(0.0f, 0.0f, .8f);

        // Knee
        gluSphere(pObj, .2f, 26, 26);

        glPushMatrix();
            // 🔥 rotação do joelho (normalmente só eixo X)
            glRotatef(rightLegXRrot - 90.0f, 1, 0, 0);

            // Lower leg (canela)
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

int main(int argc, char *argv[]){

    glutInit(&argc, argv);  
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);  
    glutInitWindowSize(800, 600);  
    glutCreateWindow("Eddy Azbear o boneco");  
    glutReshapeFunc(ChangeSize);  
    glutKeyboardFunc(SpecialKeys);  
    glutDisplayFunc(RenderScene);  
    SetupRC();  
    glutMainLoop();  
      
    return 0; 
}
