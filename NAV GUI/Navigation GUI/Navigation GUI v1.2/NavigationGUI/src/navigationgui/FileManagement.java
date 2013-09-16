package navigationgui;

import java.io.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 *
 * @author Jimmy
 */
public class FileManagement {
    
    public String getTextFromFile(String filePath)
    {
            int numberOfLines = 0;
            String[] textArray = {""};
            String fullText = "";
            //sets the editor pane to allow html.
   
        try{
            //calls method to count lines in .txt file
            numberOfLines = countLines(filePath); 
            //creates new string array and populates it with each line of text from
            //.txt file
            textArray = new String[numberOfLines];
            textArray = openFile(filePath , numberOfLines); 
        }
        catch(IOException e){
            System.out.println( e.getMessage());
        }
    
        //below for loop combines text into one string.
        for(int i = 0; i < numberOfLines; i++)
        {
            fullText += textArray[i];
        }
        //returns the entire string of text
        return fullText;
    }
    
    private String[] openFile(String path, int lines) throws IOException
    {
        FileReader fr = new FileReader(path);
        BufferedReader textReader = new BufferedReader(fr);
  
        String[] textData= new String[lines];
  
        for(int i = 0; i < lines; i++)
        {
           textData[i] = textReader.readLine();
        }
        textReader.close();
        return textData;
    }
    
    public int countLines(String path)throws IOException
    {
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
    
        String aLine;
        int numOfLines = 0;
    
        while((aLine = br.readLine()) != null)
        {
            numOfLines++;
        }
        br.close();
        return numOfLines;
    }
}
