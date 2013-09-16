/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package contentmanagementsystem;

/**
 *
 * @author Jimmy
 */
public class ContentManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here 
        //Create core logic.
    
        boolean loggedIn = false;
        boolean allowNewFrame = false;
        
        CoreLogic core = new CoreLogic();
               
        loggedIn = runLogin();
    }
     
    private static boolean runLogin()
    {
            LoginGUI login = new LoginGUI();
            NetworkModel network = new NetworkModel();
            boolean check = false;
            
            while(login.open == true)
            {
                if(login.loginClick == true)
                {
                    //login.getCredentials();
                    check = network.checkCredentials(login.getCredentials()); 
                    System.out.println(check);
                }
            }
            
            login.dispose();
            return check;
        
    }

}

