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
       // JLabel[] displayLabel;
       
        public static void main(String[] args) 
        { 
                NavigationGUI navGUI = new NavigationGUI(); 
                navGUI.run(); 
                System.exit(0); 
        } 
        
        /** 
         * This method starts the game and navigation it in a loop 
         */ 
        public void run() 
        { 
                initialize(); 
                file = new FileManagement();
                
                int degree = 0;
                int bubbleCount = 0;
                String[] stringLabel;
                                
                // the below is put in to display where the project is working from.
                System.out.println(new File(".").getAbsolutePath());
                
                try
                {
                    // the below code counts the amount of lines in Labels.txt
                    //and sets up an array with that amount of lines.
                    bubbleCount = file.countLines("Nav Files\\labels.txt");
                    bubble = new Bubble[bubbleCount];
                }
                catch(IOException e){
                    System.out.println( e.getMessage());
                }
                
                //the below block of code sets up the local variables to be inserted into bubbles
               if(bubbleCount > 8)
               {
                   //this is to ensure that even if there are more than 8 bubbles
                   //the bubbles are evenly spaced out.
                   degree = 360/8;
               }
               else
               {
                    degree = 360/bubble.length;
               }
                stringLabel = new String[bubble.length];
                stringLabel = file.getTextFromFile("Nav Files\\labels.txt");
                
                //the below for loop cycles through the bubble array and implements
                //each bubble with the appropriate attribute.
                for(int i = 0; i < bubble.length; i++)
                {   //new bubble
                    bubble[i] = new Bubble();
                    //sets the angle from the centre in which the bubble will travel
                    if(i ==0)
                    {
                        bubble[i].setDegree(0);
                    }
                    else
                    {   
                        if(i < 8)
                        {
                            bubble[i].setDegree(bubble[i-1].getDegree() + degree);
                        }
                        else
                        {
                            bubble[i].setDegree(270);
                        }
                    }
                    //sets the starting co-ordinates for each bubble.
                    bubble[i].setX(225);
                    bubble[i].setY(225);
                    
                    //sets the name for each bubble.
                    bubble[i].setName(stringLabel[i]);
                    
                    //prints out the bubble details
                    System.out.println("bubble " + i + " is now labeled "
                                + bubble[i].getName() + " and is on a " + 
                            bubble[i].getDegree() + " degree angle from the centre");
                }
                
                        
                int count = 0;
                
                while(isRunning) 
                { 
                        long time = System.currentTimeMillis(); 
                        int endPoint = 400;
                        
                        //the below if statement contains the function calls 
                        //for the expanding bubble animation
                        if(count != 40)
                        {
                            update(); 
                            draw(); 
                            
                            count++;
                        }
                        
                        //the below if statement contains the function calls for
                        //the rotating bubble animations.
                        if(count == 40 && bubble[7].getDegree() != 270)
                        {
                            next();
                            draw();
                        }
                        
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
         * This method will set up everything need for the navigation to run 
         */ 
        void initialize() 
        {     
                setTitle("Content Management System"); 
                setSize(windowWidth, windowHeight); 
                setResizable(false); 
                setDefaultCloseOperation(EXIT_ON_CLOSE); 
                setVisible(true); 
                
                insets = getInsets(); 
                setSize(insets.left + windowWidth + insets.right, 
                                insets.top + windowHeight + insets.bottom); 
                
                backBuffer = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB); 
        } 
        
              
        //this function is called when the animation begins.
        void update() 
        { 
            double position;
            double radian;
            double cos;
            double sin;
            int animationSpeed = 0;
                   
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
        
        //this function is called when the user clicks the next arrow.
        void next()
        {
            double position;
            double radian;
            double cos;
            double sin;
                    
            for(int i = 0; i < bubble.length; i++)
            {
                bubble[i].setDegree(bubble[i].getDegree() + 5);
                radian = Math.toRadians(bubble[i].getDegree());
           
                cos = Math.cos(radian);
                sin = Math.sin(radian);
                
                //225 is the centre of the screen.
                //200 is the distance from the centre.
                position = 225 + (200 * cos);
                bubble[i].setX((int) position);
                position = 225 + (200 * sin);
                bubble[i].setY((int) position);
            }
        }
        
        //this function is called when the user clicks the previous arrow.
        void previous()
        {
            double position;
            double radian;
            double cos;
            double sin;
                    
            for(int i = 0; i < bubble.length; i++)
            {
                bubble[i].setDegree(bubble[i].getDegree() - 5);
                radian = Math.toRadians(bubble[i].getDegree());
           
                cos = Math.cos(radian);
                sin = Math.sin(radian);
                
                //225 is the centre of the screen.
                //200 is the distance from the centre.
                position = 225 + (200 * cos);
                bubble[i].setX((int) position);
                position = 225 + (200 * sin);
                bubble[i].setY((int) position);
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
                
                //this below Oval is the centre bubble.
                bbg.fillOval(x2, y2, 100, 100);
                
                
                g.drawImage(backBuffer, insets.left, insets.top, this); 
        } 
} 