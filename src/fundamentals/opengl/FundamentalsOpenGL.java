/***************************************************************
* file: FundamentalsOpenGL.java
* author: Justin Figueroa
* class: CS 4450 – Computer Graphics
*
* assignment: program 1
* date last modified: 09/02/2026
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
    
final int ELLIPSE_PARAMS = 3;
final int CIRCLE_PARAMS = 3;
final int LINE_PARAMS = 4;
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
static public String[][] readFile(String pathToFile)
        throws FileNotFoundException {

    String[][] toRender = new String[10][10];
    int row = 0;

    try (Scanner fileScanner = new Scanner(new File(pathToFile))) {

        while (fileScanner.hasNextLine() && row < toRender.length) {
            String line = fileScanner.nextLine();

            try (Scanner lineScanner = new Scanner(line)) {
                lineScanner.useDelimiter("[,\\s]+");

                int column = 0;

                while (lineScanner.hasNext()
                        && column < toRender[row].length) {

                    toRender[row][column] = lineScanner.next();
                    column++;
                }
            }
            row++;
        }
    }

    return toRender;
}

public void start(String[] args){
    try{
        String pathToFile = getPathToFile(args);
        String[][] toRender = readFile(pathToFile);
        createWindow();
        Keyboard.create();
        initGL();
        render(toRender);
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
    float dx = endX - startX;
    float dy = Math.abs(endY - startY);
    float incramentRight = 2 * dy;
    float incramentUpRight = 2 * (dy - dx);
    float distToMidpoint = ((2 * (dy)) - dx);
    float toPlotX = startX;
    float toPlotY = startY;
    glColor3f(1.0f, 0.0f, 0.0f);
    glPointSize(1);
    glBegin(GL_POINTS);
    glVertex2f(startX, startY);

    while (toPlotX <= endX){
        if (endY <= startY){
            if (distToMidpoint < 0){
                toPlotX += 1;
                distToMidpoint += incramentRight;
                glVertex2f(toPlotX, toPlotY);
            }
            else{
                toPlotX += 1;
                toPlotY += -1;
                distToMidpoint += incramentUpRight;
                glVertex2f(toPlotX, toPlotY);
            }
    }
    else {
        if (distToMidpoint > 0){
            toPlotX += 1;
            toPlotY += 1;
            distToMidpoint += incramentUpRight;
            glVertex2f(toPlotX, toPlotY);
        }
        else if (distToMidpoint < 0){
            toPlotX += 1;
            distToMidpoint += incramentRight;
            glVertex2f(toPlotX, toPlotY);
        }
    }
}
    glEnd();
}


void renderEllipse(float x, float y, float radius){

}

private void render(String[][] toRender){
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
        try{
            for (int i = 0; i < 10; i++){
                if (toRender[i] == null ||
                    toRender[i].length == 0 ||
                    toRender[i][0] == null) {
                    continue;
                }
                else{
                        if (toRender[i][0].equals("l")){
                            renderLine(Float.parseFloat(toRender[i][1]), Float.parseFloat(toRender[i][2]), Float.parseFloat(toRender [i][3]), Float.parseFloat(toRender[i][4]));
                        }
                        else if (toRender[i][0].equals("e")){
                            renderEllipse(Float.parseFloat(toRender[i][1]), Float.parseFloat(toRender[i][2]), Float.parseFloat(toRender [i][3]));
                        }
                        else if (toRender[i][0].equals("c")){
                            renderCircle(Float.parseFloat(toRender[i][1]), Float.parseFloat(toRender[i][2]), Float.parseFloat(toRender [i][3]));
                        }
                    }
                }
            Display.update();
            Display.sync(60);
            }
        catch (Exception e){
            e.printStackTrace();
            }
        }
    Display.destroy();
}


public static void main(String[] args)throws Exception{
    
    FundamentalsOpenGL instance = new FundamentalsOpenGL();
    instance.start(args);
}
}