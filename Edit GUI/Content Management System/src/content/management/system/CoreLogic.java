/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package content.management.system;

import java.io.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 *
 * @author Jimmy
 */
public class CoreLogic {
    
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
        //displays the text in the editPane
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
    
    private int countLines(String path)throws IOException
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
    
    public void saveFile(String filePath, String text)
    {
        BufferedWriter writer = null;
    
        try{
             writer = new BufferedWriter(new FileWriter(filePath));
             System.out.println(text);
             writer.write(text);
        }catch(IOException e){System.err.println(e);}
        finally{
            if(writer != null)
            {
                try{
                    writer.close();
                }catch(IOException e){System.err.println(e);}
            }
        }
    }
    
    public String getDate()
    {
        //defines the date format.
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy HH-mm-ss");
        //gets the date from the system
        Date today = Calendar.getInstance().getTime();
        
        //applies teh date to a string using the defined format.
        String reportDate = df.format(today);
        
        return reportDate;
    }
}
