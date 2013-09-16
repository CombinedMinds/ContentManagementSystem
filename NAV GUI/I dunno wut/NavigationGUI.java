package navigation.gui;

import java.awt.*; 
import java.awt.image.BufferedImage; 
import javax.swing.JFrame; 
import java.lang.Math;
/** 
 * Main class for the game 
 */ 
public class NavigationGUI extends JFrame 
{        
        boolean isRunning = true; 
        int fps = 30; 
        int windowWidth = 500; 
        int windowHeight = 500; 
        
        BufferedImage backBuffer; 
        Insets insets; 
        
        int x = 200; 
        int y = 200;
        int x2 = 200;
        int y2 = 200;

        int degree = 90;
        
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
                setTitle("Nav GUI"); 
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
            //the below calculation I got from:
            //http://www.reddit.com/r/math/comments/1ia1zo/could_someone_please_provide_the_formula_for_this/
            //the reddit page describes what the calculation does.
            //this is not used as yet.
            //double position = 0;
            //position = (x + 10) * Math.cos(degree);
            //position = (y + 10) * Math.sin(degree);
            
            //the below just moves xpositon +5 and the ypositoin +5.
            x += 5;
            y+=5;
        } 
        
        /** 
         * This method will draw everything 
         */ 
        void draw() 
        {       
               //this method draws the components to screen.
                Graphics g = getGraphics(); 
                
                Graphics bbg = backBuffer.getGraphics(); 
                
                bbg.setColor(Color.WHITE); 
                bbg.fillRect(0, 0, windowWidth, windowHeight); 
                
                //the below displays the bubbles.
                //Set the "pen" color.
                bbg.setColor(Color.BLUE); 
                //draw an oval.(x, y, xSize, ySize)
                bbg.fillOval(x, y, 50, 50); 
                bbg.fillOval(x2, y2, 100, 100);
                
                g.drawImage(backBuffer, insets.left, insets.top, this); 
        } 
} 