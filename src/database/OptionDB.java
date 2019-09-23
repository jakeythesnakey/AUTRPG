/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Option;

/**
 * Storing options in and removing them from database
 * @author sdg0118
 */
public class OptionDB {
    
    Connection conn;
    
    //Constructor
    OptionDB(Connection conn)
    {
        this.conn = conn;
    }
    
    //creates option table
    public void createTableOption() throws SQLException
    {
        conn.createStatement().executeUpdate("Create TABLE OPTIONS (ID INT, NAME VARCHAR(100), CHA INT, STR INT, INTELLIGENCE INT, LCK INT, SANITY INT, SLEEP INT, GRADES INT, OUTCOME VARCHAR(500))");
    }

    //adds option to DB
    public void insertOption(int id, String name, int cha, int str, int intelligence, int lck, int sanity, int sleep, int grades, String outcome) throws SQLException
    {
        name.replace("'", "''");
        outcome.replace("'", "''");
        conn.createStatement().executeUpdate("INSERT INTO OPTIONS VALUES (" + id + " , '" + name + "' , " + cha + " , "+ str + " , "+ intelligence + " , "+ lck + " , "+ sanity + " , "+ sleep + " , "+ grades + " , '"+ outcome + "')");
    }

    //adds option to DB
    public void insertOption(Option option) throws SQLException
    {
        option.setName(option.getName().replace("'", "''"));
        option.setOutcome(option.getOutcome().replace("'", "''"));
        conn.createStatement().executeUpdate("INSERT INTO OPTIONS VALUES ("+ option.getId() + ", '" + option.getName() + "' , " + option.getCha() + " , "+ option.getStr() + " , "+ option.getIntelligence() + " , "+ option.getLck() + " , "+ option.getSanity() + " , "+ option.getSleep() + " , "+ option.getGrades() + " , '"+ option.getOutcome() + "')");
    }
    
    //returns option from db
    public Option retrieveOption(int id) throws SQLException
    {       
        String[] results = new String[10];
        ResultSet resultSet = null;
        
        resultSet = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM OPTIONS WHERE ID = " + id + "");
        resultSet.beforeFirst();
        while (resultSet.next())
        {
            for (int i = 1; i <= 10; i++)
            {
                results[i-1] = resultSet.getString(i);
            }
        }

        Option returnOption = new Option(Integer.parseInt(results[0]), results[1], Integer.parseInt(results[2]), Integer.parseInt(results[3]), Integer.parseInt(results[4]), Integer.parseInt(results[5]), Integer.parseInt(results[6]), Integer.parseInt(results[7]), Integer.parseInt(results[8]), results[9]);
        return (returnOption);
    }    
}
