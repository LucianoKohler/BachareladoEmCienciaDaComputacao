// Lnx: gcc castelo.c -lglut -lGL -lGLU -lm -o castelo && ./castelo
// Win: gcc castelo.c -lfreeglut -lopengl32 -lglu32 -o castelo; .\castelo

#include <GL/freeglut.h>
  
// Rotation
static GLfloat yRot = 0.0f;
static GLfloat xRot = 0.0f;

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
    glClearColor(.7f, 0.5f, 0.0f, 1.0f);  
}  
  
// Respond to arrow keys (rotate snowman)
void SpecialKeys(int key, int x, int y){  

    if(key == GLUT_KEY_LEFT)  
        yRot -= 5.0f;  
  
    if(key == GLUT_KEY_RIGHT)  
        yRot += 5.0f;  
    
    if(key == GLUT_KEY_UP) 
        xRot += 5.0;
    
    if(key == GLUT_KEY_DOWN) 
        xRot -= 5.0;

        yRot = (GLfloat)((const int)yRot % 360);  
        xRot = (GLfloat)((const int)xRot % 360);  
  
    // Refresh the Window  
    glutPostRedisplay();
}

void makeTower(GLUquadricObj *pObj, float x, float y, float z, float height){
    
	// Tower color
	glColor3f(.8f, 0.6f, 0.0f);
    
	glPushMatrix();
        // Tower body
        glTranslatef(x, y, z); 
        glRotatef(90, 1, 0, 0);
        gluCylinder(pObj, .3f, .3f, height, 26, 13);
        
        // Tower roof
        glColor3f(1.0f, 0.0f, 0.0f);
        
        glRotatef(180, 1, 0, 0);
        gluCylinder(pObj, .5f, .0f, .7f, 26, 13);
	glPopMatrix();

    // Tower windows
    glColor3f(.0f,.0f,.0f);

    glPushMatrix();
        glTranslatef(x, y-height/4, z); 
        glScalef(.3f, .5f, .6f);
        glutSolidCube(1.0);
    glPopMatrix();
    glPushMatrix();
        glTranslatef(x, y-height/4, z); 
        glScalef(.6f, .5f, .3f);
        glutSolidCube(1.0);
    glPopMatrix();
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
	glRotatef(yRot, 0.0f, 1.0f, .0f);  
	glRotatef(xRot, 1.0f, .0f, .0f);  

	// Draw something  
	pObj = gluNewQuadric();  
	gluQuadricNormals(pObj, GLU_SMOOTH);  

    glTranslatef(.0f, 1.0f, .0f);
	// Main Body
    makeTower(pObj,  2.0f, 0.0f, -10.0f, 1.5f);
    makeTower(pObj, -2.0f, 0.0f, -4.00f, 1.5f);
    makeTower(pObj,  2.0f, 0.0f, -4.00f, 1.5f);
    makeTower(pObj, -2.0f, 0.0f, -10.0f, 1.5f);

    // Walls around towers
    glPushMatrix();
        glColor3f(.8f, 0.6f, 0.0f);  
        glScalef(4.5f, .75f, .3f);
        
        glTranslatef(0.0f, -1.5f, -14.0f);
        glutSolidCube(1.0);
        
        glTranslatef(0.0f, 0.0f, -20.0f);
        glutSolidCube(1.0);
    glPopMatrix();

    glPushMatrix();
        glScalef(.3f, .75f, 5.0f);
        
        glTranslatef(7.0f, -1.5f, -1.5f);
        glutSolidCube(1.0);
        
        glTranslatef(-14.0f, 0.0f, 0.0f);
        glutSolidCube(1.0);
    glPopMatrix();

    // Front door
    glPushMatrix();
        glColor3f(0.0f, 0.0f, 0.0f);
        glTranslatef(0.0f, -1.2f, -4.0f);
        
        glScalef(.4f, .55f, .1f);
        glutSolidCube(1.0);
    glPopMatrix();

    // Earth Beneath
    glColor3f(1.0f, .8f, .1f);  
    glTranslatef(.0f, -51.5f, -10.0f);
    gluSphere(pObj, 50.0f, 72, 72);

    // Restore the matrix state  
    glPopMatrix();  
  
    // Buffer swap  
    glutSwapBuffers();  

}    

int main(int argc, char *argv[]){

    glutInit(&argc, argv);  
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);  
    glutInitWindowSize(800, 600);  
    glutCreateWindow("Castelo de Goldtown");  
    glutReshapeFunc(ChangeSize);  
    glutSpecialFunc(SpecialKeys);  
    glutDisplayFunc(RenderScene);  
    SetupRC();  
    glutMainLoop();  
      
    return 0; 
}
