/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package undoredo;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Martin the great and powerfull
 * Leader of all men
 * Destroyer of worlds
 * Lover of Boys and all things with a penis
 * Also Roya helped I guess
 *
 */

public class UndoRedo extends JFrame 
{
    
    static int maxVersions = 50;
    static String[] previousVersions = new String[ maxVersions ];
    static int previousVersionsPosition = 0;
    static int undoCount = 0;
    
    static TestInterface undoRedoForm = new TestInterface();
    
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater( new Runnable()
        {
            @Override
            public void run()
            {
                undoRedoForm.setupForm();
                undoRedoForm.setVisible( true );
            }
        });
    }
    
    //I r best program
    
    public static void createVersion( String newVersion )
    {
        writeLog();
        
        previousVersionsPosition += 1;
        
        previousVersions[ previousVersionsPosition % maxVersions ] = newVersion;
        
        if ( undoCount < ( maxVersions - 1 ) )
        {
            undoCount += 1;
        }
        
        writeLog();
    }
    
    public static void undo()
    {        
        writeLog();
        
        if ( undoCount > 0 )
        {
            previousVersionsPosition -= 1;
            
            undoRedoForm.setInputText( previousVersions[ previousVersionsPosition % maxVersions ] );
            
            undoCount -= 1;
        }
        
        writeLog();
    }
    
    public static void redo()
    {
        writeLog();
        
        if ( undoCount < 50 && previousVersions[ ( previousVersionsPosition + 1 ) % maxVersions ] != null )
        {
            previousVersionsPosition += 1;
            
            undoRedoForm.setInputText( previousVersions[ previousVersionsPosition % maxVersions ] );
            
            undoCount += 1;
        }
        
        writeLog();
    }
    
    public static void reset()
    {
        undoCount = 0;
        previousVersionsPosition = 0;
        undoRedoForm.setInputText( "" );
        
        writeLog();
    }
    
    public static void writeLog()
    {
        String tempLogText;
        
        tempLogText = undoRedoForm.getLogText();
        tempLogText = ( tempLogText +  
                        previousVersionsPosition + 
                        " - " + 
                        undoCount + 
                        " - " + 
                        previousVersions[ previousVersionsPosition % maxVersions ] +
                        "\n" );
        undoRedoForm.setLogText( tempLogText );
    }
}