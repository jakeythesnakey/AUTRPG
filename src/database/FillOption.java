/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Option;
import model.TextReader;
import model.ToFile;

/**
 *Class for reading from text file to database
 * @author sdg0118
 */
public class FillOption {
    
    
    DataBase db;
    public FillOption(DataBase newdb)
            
    {
        this.db  = newdb;
        TextReader textReader = new TextReader();
        String optionURL = "options.txt";
        ToFile toFile = new ToFile();
        
        Option currentOption = new Option();
        String optionList = "";
        
        try {
            db.getOptionDB().createTableOption();
        } catch (SQLException ex) {
            System.out.println("Option table not Created");;
        }
        
        try{
            optionList = toFile.readFile(optionURL);//reads list of options from text file
        }
        catch(Exception e)
        {
            System.out.print("Ja Fucked up da Readin de OptionList!");
        }
        
        for (int i = 1; i <35; i++)
        {
            currentOption = textReader.optionReader(optionList, Integer.toString(i));//
            try {
                db.getOptionDB().insertOption(currentOption);
            } catch (SQLException ex) {
                Logger.getLogger(FillOption.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
