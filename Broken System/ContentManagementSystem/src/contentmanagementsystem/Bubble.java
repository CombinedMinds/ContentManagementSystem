
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package contentmanagementsystem;

/**
 *
 * @author Jimmy
 */
public class Bubble {
    private int x;
    private int y;
    private boolean parent;
    
    public void Bubble()
    {
        x = 200;
        y = 200;
    }
 
    public void updateX( int addX)
    {
        x += addX;
    }
    
    public void updateY( int addY)
    {
        y += addY;
    }
    
    public void isParent(boolean check)
    {
        parent = check;
    }
    
    public int getX()
    {
        return x;
    } 
    
    public int getY()
    {
        return y;
    }
    
    public int getSize()
    {
        if(parent = true)
            return 100;
        else
            return 50;
    }
    
}
