/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package navigationgui;

/**
 *
 * @author Jimmy
 */
public class Bubble {
    
    private int x;
    private int y;
    private int degree;
    private String label;
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public void setX(int newX)
    {
        x = newX;
    }
    
    public void setY(int newY)
    {
        y = newY;
    }
    
    public void setDegree(int newDegree)
    {
        degree = newDegree;
    }
    
    public int getDegree()
    {
        return degree;
    }
    
    public void setName(String name)
    {
        label = name;
    }
    
    public String getName()
    {
        return label;
    }
}
