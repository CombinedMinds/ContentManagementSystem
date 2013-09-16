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
        
        int x = 225; 
        int y = 225;
        int x2 = 200;
        int y2 = 200;

        int degree = 180;
        
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
            double position = 0;
            double radian = degree / Math.PI;
            int animationSpeed = 5;
            
            position = (x + animationSpeed) * Math.cos(radian);
            //x += 5;
            x = (int) position;
            position = (y + animationSpeed) * Math.sin(radian);
            //y = (int) position;
            //y+=5;
            
            System.out.println(x + " ," + y);
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
                bbg.fillOval(x, y, 50, 50); 
                bbg.fillOval(x2, y2, 100, 100);
                
                g.drawImage(backBuffer, insets.left, insets.top, this); 
        } 
} 