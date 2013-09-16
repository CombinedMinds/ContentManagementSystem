package navgui;

import java.awt.*; 
import java.awt.image.BufferedImage; 
import javax.swing.JFrame; 
import java.lang.Math;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
/** 
 * Main class for the game 
 */ 
public class NavGUI extends JFrame 
{        
        boolean isRunning = true; 
        int fps = 30; 
        int windowWidth = 500; 
        int windowHeight = 500; 
        
        BufferedImage backBuffer; 
        Insets insets; 
        
        int x = 225; 
        int y = 225;
        int x2 = 225;
        int y2 = 225;

        int degree = 0;
        int degree2 = 180;

        int x3 = 200;
        int y3 = 200;
        
        
        public static void main(String[] args) 
        { 
                NavGUI navGUI = new NavGUI(); 
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
            double radian = Math.toRadians(degree);
            double radian2 = Math.toRadians(degree2);
            int animationSpeed = 5;
            
            double cos = Math.cos(radian);
            double sin = Math.sin(radian);
            
            position = x + ( animationSpeed * cos);
            //x += 5;
            x = (int) position;
            position = y +(animationSpeed * sin);
            y = (int) position;
            //y+=5;
            
            position = y2 + (animationSpeed* Math.sin(radian2));
            y2 = (int) position;
            position = x2 + (animationSpeed * Math.cos(radian2));
            x2 = (int) position;
            
            System.out.println(radian + "," + sin);
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
                bbg.fillOval(x2, y2, 50, 50);
                bbg.fillOval(x3, y3, 100, 100);
                
                g.drawImage(backBuffer, insets.left, insets.top, this); 
        } 
} 