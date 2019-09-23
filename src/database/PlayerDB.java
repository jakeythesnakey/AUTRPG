/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Player;

/**
 * Holds code related to adding players to and removing them from the database
 * @author sdg0118
 */
public class PlayerDB {
    
    Connection conn;
    
    PlayerDB(Connection conn)
    {
        this.conn = conn;
    }
    
    //Creates the player table
    public void createTablePlayer() throws SQLException
    {
        conn.createStatement().executeUpdate("Create TABLE PLAYER (ENCOUNTERID INT, NAME VARCHAR(100), CHA INT, STR INT, INTELLIGENCE INT, LCK INT, SANITY INT, SLEEP INT, GRADES INT)");
    }

    //Adds a players record yto the table via parts
    public void insertPlayer(int encounterid, String name, int cha, int str, int intelligence, int lck, int sanity, int sleep, int grades) throws SQLException
    {
        name = name.toUpperCase();
        conn.createStatement().executeUpdate("INSERT INTO PLAYER VALUES ("+ encounterid + ", '" + name + "' , " + cha + " , "+ str + " , "+ intelligence + " , "+ lck + " , "+ sanity + " , "+ sleep + " , "+ grades + ")");
    }
    
    //Adds a players record yto the table
    public void insertPlayer(int encounterid, Player player) throws SQLException
    {
        player.setName(player.getName().toUpperCase());
        player.setName(player.getName().replace("'", "''"));
        conn.createStatement().executeUpdate("INSERT INTO PLAYER VALUES ("+ encounterid + ", '" + player.getName() + "' , " + player.getCha() + " , "+ player.getStr() + " , "+ player.getIntelligence() + " , "+ player.getLck() + " , "+ player.getSanity() + " , "+ player.getSleep() + " , "+ player.getGrades() + ")");
    }
    
    //returns a player from database
    public Player retrievePlayer(String name) throws SQLException
    {
        name = name.toUpperCase();
        String[] results = new String[9];
        ResultSet resultSet = null;
        resultSet = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM PLAYER WHERE NAME = '"+name+"'");
        resultSet.beforeFirst();
        while (resultSet.next())
        {
            for (int i = 1; i <= 9; i++)
            {
                results[i-1] = resultSet.getString(i);
            }
        }
        for(String s:results)
        {
            System.out.println(s);
        }
        System.out.println(results[1]);
        
        Player returnPlayer = new Player(results[0], Integer.parseInt(results[1]), Integer.parseInt(results[2]), Integer.parseInt(results[3]), Integer.parseInt(results[4]), Integer.parseInt(results[5]), Integer.parseInt(results[6]), Integer.parseInt(results[7]));
        returnPlayer.setCurrentEncounter(Integer.parseInt(results[8]));
        return (returnPlayer);
    }
    
    
    
}
