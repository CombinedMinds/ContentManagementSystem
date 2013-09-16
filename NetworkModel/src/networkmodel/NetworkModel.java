/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package networkmodel;

/**
 *
 * @author Martin
 */
public class NetworkModel 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    public boolean createSession( String serverIP, String serverPort, String userID, String userPW )
    {
        return true;
    }
    
    public boolean checkSession()
    {
        return true;
    }
    
    public boolean closeSession()
    {
        return true;
    }
    
    public String getDirMetaText()
    {
        String dirMetaText;
        
        //Example value
        dirMetaText = "Name:Index;" +
                      "Subpages:1.1_Cat:1.2_Dog:1.3_Mouse;" +
                      "Text:1.0_Index_Text;";       
        
        return dirMetaText;
    }
    
    public String getDirText()
    {
        String dirText;
        
        //Example value
        dirText = "This is an index";
        
        return dirText;
    }
}
