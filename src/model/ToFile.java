/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * Legacy class. The ToFile class holds tools for writing to file and reading from file
 * @author sdg0118
 */
public class ToFile {
    
    
    //Constructor
    public ToFile()
    {
        
    }

    //Appends given text to file
    public void scanToFile(String savetxt, String fileName)
    {
        try{
            FileWriter writer = new FileWriter(fileName, true);
            BufferedWriter out = new BufferedWriter(writer);
            writer.append(savetxt);
            writer.close();
        } 
        catch(Exception e)
        {
            System.out.println("scanToFile fucked up");
        }
    }
    
    //Replaces a file with new text
    public void scanToWipedFile(String savetxt, String fileName)
    {
        try{
            PrintWriter printWriter = new PrintWriter (fileName);
            printWriter.println (savetxt);
            printWriter.close (); 
        } 
        catch(Exception e)
        {
            System.out.println("scanToWipedFile fucked up");
        }
    }
    
    //Reads text from a file to a string
    public String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String returnString = "There's nothing here";
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
        returnString = sb.toString();
        } finally {
            br.close();
            return returnString;
        }
    }
}
