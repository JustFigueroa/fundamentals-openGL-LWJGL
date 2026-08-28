/***************************************************************
* file: FundamentalsOpenGL.java
* author: Justin Figueroa
* class: CS 4450 – Computer Graphics
*
* assignment: program 1
* date last modified: 8/27/2026
*
* purpose: This program draws a window and draws primitives based on coordinates
* from file passed by user via command line
*
****************************************************************/

package fundamentals.opengl;
import java.io.File;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.input.Keyboard;

public class FundamentalsOpenGL{
    
public void readFile(){
    
}
public void start(){
    try{
        createWindow();
        Keyboard.create();
        initGL();
        render();
    }
    catch (Exception e){
        e.printStackTrace();
    }
}
public void end(){
    Display.destroy();
}

private void createWindow() throws Exception{
    Display.setFullscreen(false);
    Display.setDisplayMode(new DisplayMode(640, 480));
    Display.setTitle("Program 1: Render Shapes");
    Display.create();
}

private void initGL(){
    glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    glOrtho(0, 640, 0, 480, 1, -1);
    glMatrixMode(GL_MODELVIEW);
    glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
}

private void render(){
    Circle circle = new Circle();
    Square square = new Square();
    Line line = new Line();
    
    while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
        try{
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glLoadIdentity();
            glColor3f(1.0f,1.0f,1.0f);
            
            circle.render();
            square.render();
            line.render();
           
            Display.update();
            Display.sync(60);
        }
        catch (Exception e){
            
        }
    }
    Display.destroy();
}

interface Shapes{
    public void render();
}

class Square implements Shapes{  
        public void render(){
            
    }
}
class Circle implements Shapes{
        public void render(){

    }

}
class Line implements Shapes{
        public void render(){

    }
}

public static void main(String[] args)throws Exception{
    
    for (int i = 0; i < args.length; i++){
        if (args[i].equals("--path") || args[i].equals("-p")){
            String pathToFile = args[i + 1];
            System.out.println(pathToFile);
            break;
        }
        else{
            break;
        }
    }
    FundamentalsOpenGL instance = new FundamentalsOpenGL();
    instance.start();
}

}