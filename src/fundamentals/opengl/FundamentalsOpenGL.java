/***************************************************************
* file: FundamentalsOpenGL.java
* author: Justin Figueroa
* class: CS 4450 – Computer Graphics
*
* assignment: program 1
* date last modified: 09/01/2026
*
* purpose: This program draws a window and draws primitives based on coordinates
* from file passed by user via command line
*
****************************************************************/
//For Drawing Shapes
package fundamentals.opengl;
import java.io.File;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.input.Keyboard;
import java.util.ArrayList;
//For Reading the File
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FundamentalsOpenGL{
    
/* 
 * getPathToFile();
 * @param args : The arguments passed after running program in the command line to be parsed
 * @return pathToFile : The path to the coordinate file
 */
    
public static String getPathToFile(String[] args){
    String pathToFile;
    try{
        for (int i = 0; i < args.length; i++){
        if (args[i].equals("--path") || args[i].equals("-p")){
            pathToFile = args[i + 1];
        return pathToFile;
            }
        else
            return "Invalid arguements";
        }
    }
    catch (Exception e){
        return "Error: 1";
    }
    return "Error: 1";
}
/*
 * readFile();
 * @param pathToFile : The path to the coordingate file
 * @return toRender : a 2D array with the desired shapes and their respectice parameters
 */

static public String[][] readFile (String pathToFile) throws FileNotFoundException{
    
    String[][] toRender = new String[10][10];
    InputStream is = new FileInputStream(pathToFile);
    Scanner sc = new Scanner(is);
    while (sc.hasNextLine()){
    System.out.println(sc.nextLine());
    
    }
    return toRender;
}

public void start(){
    try{
        String[][] test = new String[0][0];
        createWindow();
        Keyboard.create();
        initGL();
        render(test);
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

void renderCircle(float x, float y, float radius){

}
void renderLine(float startX, float startY, float endX, float endY){

}
void renderEllipse(){

}
private void render(String[][] toRender){
    ArrayList<Integer> circleIndex = new ArrayList<Integer>();
    ArrayList<Integer> lineIndex = new ArrayList<Integer>();
    ArrayList<Integer> ellipseIndex = new ArrayList<Integer>();

    while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
        try{
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            for (int i = 0; i < toRender[0].length; i++){
                
                if (toRender[0][i].equals("l")){
                    lineIndex.add(i);
                }
                else if (toRender[0][i].equals("e")){
                    ellipseIndex.add(i);
                }
                else if (toRender[0][i].equals("c")){
                    circleIndex.add(i);
                }
            }
            for (int i = 0; i < lineIndex.size(); i++){
                
            }
            for (int i = 0; i < ellipseIndex.size(); i++){
                
            } 
            for (int i = 0; i < circleIndex.size(); i++){
                
            }
            
            Display.update();
            Display.sync(60);
        }
        catch (Exception e){
            
        }
    }
    Display.destroy();
}


public static void main(String[] args)throws Exception{
    String pathToFile = getPathToFile(args);
    String[][] toRender = readFile(pathToFile);
    FundamentalsOpenGL instance = new FundamentalsOpenGL();
    instance.start();
}
}