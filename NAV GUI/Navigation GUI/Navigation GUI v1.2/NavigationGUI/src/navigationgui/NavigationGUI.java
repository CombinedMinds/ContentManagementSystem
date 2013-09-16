package navigationgui;

import java.awt.*; 
import java.awt.image.BufferedImage; 
import javax.swing.JFrame; 
import java.lang.Math;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import java.io.*;

public class NavigationGUI extends JFrame 
{        
        boolean isRunning = true; 
        int fps = 30; 
        int windowWidth = 500; 
        int windowHeight = 500; 
        
        BufferedImage backBuffer; 
        Insets insets; 
        
        int x = 225; 
        int y = 225;
        int x2 = 200;
        int y2 = 200;
        
        FileManagement file;
        Bubble[] bubble;
        
        public static void main(String[] args) 
        { 
                NavigationGUI navGUI = new NavigationGUI(); 
                navGUI.run(); 
                System.exit(0); 
        } 
        
        /** 
         * This method starts the game and runs it in a loop 
         */ 
        public void run() 
        { 
                initialize(); 
                file = new FileManagement();
                
                int degree = 0;
                int bubbleCount;
                
                // the below is put in to display where the project is working from.
                System.out.println(new File(".").getAbsolutePath());
                
                try
                {
                    // the below code counts the amount of lines in Labels.txt
                    //and sets up an array with that amount of lines.
                    bubbleCount = file.countLines("Nav Files\\labels.txt");
                    bubble = new Bubble[bubbleCount];
                    degree = 360/bubble.length;
                }
                catch(IOException e){
                    System.out.println( e.getMessage());
                }
                
                
                for(int i = 0; i < bubble.length; i++)
                {
                    bubble[i] = new Bubble();
                    if(i ==0)
                    {
                        bubble[i].setDegree(0);
                    }
                    else
                    {
                        bubble[i].setDegree(bubble[i-1].getDegree() + degree);
                    }
                    bubble[i].setX(225);
                    bubble[i].setY(225);
                    System.out.println(i + " " + bubble[i].getDegree());
                }
                
                while(isRunning) 
                { 
                        long time = System.currentTimeMillis(); 
                        
                        update(); 
                        draw(); 
                        
                        //  delay for each frame  -   time it took for one frame 
                        time = (1000 / fps) - (System.currentTimeMillis() - time); 
                        
                        if (time > 0) 
                        { 
                                try 
                                { 
                                        Thread.sleep(time); 
                                } 
                                catch(Exception e){} 
                        } 
                } 
                
                setVisible(false); 
        } 
        
        /** 
         * This method will set up everything need for the game to run 
         */ 
        void initialize() 
        {     
                setTitle("Game Tutorial"); 
                setSize(windowWidth, windowHeight); 
                setResizable(false); 
                setDefaultCloseOperation(EXIT_ON_CLOSE); 
                setVisible(true); 
                
                insets = getInsets(); 
                setSize(insets.left + windowWidth + insets.right, 
                                insets.top + windowHeight + insets.bottom); 
                
                backBuffer = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB); 
        } 
        
        /** 
         * This method will check for input, move things 
         * around and check for win conditions, etc 
         */ 
        void update() 
        { 
            double position;
            double radian;
            double cos;
            double sin;
            int animationSpeed;
            int endPoint = 400;
            
            if(bubble[0].getX() != endPoint)
            { 
                      
                for(int i = 0; i < bubble.length; i ++)
                {
                    radian = Math.toRadians(bubble[i].getDegree());
                    animationSpeed = 5;
            
                    cos = Math.cos(radian);
                    sin = Math.sin(radian);
                
                    position = bubble[i].getX() + (animationSpeed * cos);
                    bubble[i].setX((int) position);
                    position = bubble[i].getY() + (animationSpeed * sin);
                    bubble[i].setY((int) position);
                }
            }
        } 
        
        /** 
         * This method will draw everything 
         */ 
        void draw() 
        {               
                Graphics g = getGraphics(); 
                
                Graphics bbg = backBuffer.getGraphics(); 
                
                bbg.setColor(Color.WHITE); 
                bbg.fillRect(0, 0, windowWidth, windowHeight); 
                
                bbg.setColor(Color.BLUE);
                
                for(int i = 0; i < bubble.length; i++)
                {
                    bbg.fillOval(bubble[i].getX(), bubble[i].getY(), 50, 50);
                }
                
                bbg.fillOval(x2, y2, 100, 100);
                
                g.drawImage(backBuffer, insets.left, insets.top, this); 
        } 
} 