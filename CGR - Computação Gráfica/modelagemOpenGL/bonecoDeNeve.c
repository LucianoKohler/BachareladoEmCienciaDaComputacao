// Lnx: gcc bonnie.c -lglut -lGL -lGLU -lm -o bonnie && ./bonnie
// Win: gcc bonnie.c -lfreeglut -lopengl32 -lglu32 -o bonnie; .\bonnie

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
    glClearColor(.6f, 0.6f, 0.8f, 1.0f);  
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
  
    // Buffer swap  
    glutSwapBuffers();  

}    

int main(int argc, char *argv[]){

    glutInit(&argc, argv);  
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);  
    glutInitWindowSize(800, 600);  
    glutCreateWindow("Bonnie o boneco de neve");  
    glutReshapeFunc(ChangeSize);  
    glutSpecialFunc(SpecialKeys);  
    glutDisplayFunc(RenderScene);  
    SetupRC();  
    glutMainLoop();  
      
    return 0; 
}
