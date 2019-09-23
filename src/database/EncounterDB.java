/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Encounter;
import model.Option;

/**
 * Storing encounters in and removing them from database
 * @author sdg0118
 */
public class EncounterDB {
    
    Connection conn;
    
    EncounterDB(Connection conn)
    {
        this.conn = conn;
    }
    //creates encounter table
    public void createTableEncounter() throws SQLException
    {
        conn.createStatement().executeUpdate("Create TABLE ENCOUNTER (ID INT, NEXTENCOUNTER INT, OPTIONS VARCHAR(500), OPTIONCOUNT INT, ENCOUNTERTEXT VARCHAR(500))");
    }

    //Adds encounter to table
    public void insertEncounter(Encounter encounter) throws SQLException
    {
        //This part turns the options array into a string of options IDs
        String optionsString = " ";
        for (int i = 0; i < encounter.getOptions().length; i++)
        {
            optionsString = optionsString + encounter.getOptions()[i].getId() + " ";
        }
        optionsString.replace("'", "''");
        encounter.setEncounterText(encounter.getEncounterText().replace("'", "''"));
        //This part inserts it all into the table
        conn.createStatement().executeUpdate("INSERT INTO ENCOUNTER VALUES (" + encounter.getEncounterID() + ", " + encounter.getNextEncounter() + ", '" + optionsString + "', " + encounter.getOptionCount() + ", '" + encounter.getEncounterText() + "')");
    }
    
    //Returns encounter from table
    public Encounter retrieveEncounter(int id, OptionDB odb) throws SQLException
    {       
        String[] results = new String[5];
        ResultSet resultSet = null;
        
        resultSet = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM ENCOUNTER WHERE ID = " + id + "");
        resultSet.beforeFirst();
        while (resultSet.next())
        {
            for (int i = 1; i <= 5; i++)
            {
                results[i-1] = resultSet.getString(i);
            }
        }
        
        ArrayList<Option> newOptions = new ArrayList<Option>();//holds options
        String[] optionStrings = results[2].split(" ");//holds option IDs
        System.out.println("Number or options: " + optionStrings.length + " Officially: "+ results[3] + " instring : " + results[2]);
        for (int i = 0; i < optionStrings.length;i++)//Iterates thru option IDs
        {
            if (optionStrings[i] != "" && optionStrings[i] != null)//makes sure it is a valid ID
            {
                try{
                    newOptions.add(odb.retrieveOption(Integer.parseInt(optionStrings[i])));//adds option to arraylist
                }
                catch(Exception e)
                {
                    System.out.println("Option ID: " + optionStrings[i] + " not added");
                }
                
            }
        }
        
        Option[] options = newOptions.toArray(new Option[newOptions.size()]);

        Encounter returnEncounter = new Encounter(Integer.parseInt(results[0]), Integer.parseInt(results[1]), options, results[4]);
        return returnEncounter;
    }
    
}
