/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *This class is the core functionality of the database for the RPG game
 * @author sdg0118
 */
public class DataBase {
    
    private static final String DRIVER= "org.apache.derby.jdbc.EmbeddedDriver";
    public static final String JDBC_URL = "jdbc:derby:AUTRPGDB;create = true";
    
    private Connection conn;
    private PlayerDB playerDB;
    private EncounterDB encounterDB;
    private OptionDB optionDB;
    
    public DataBase()
    {
        connect();
        playerDB = new PlayerDB(conn);
        encounterDB = new EncounterDB(conn);
        optionDB = new OptionDB(conn);        
    }
    
    //Connects game to database
    public void connect()
    {
        try {
              this.conn = DriverManager.getConnection(JDBC_URL);
          } catch (SQLException ex) {
              Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
          }

        if (this.conn !=null)
        {
            System.out.println("Connected to Database");
        }
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public PlayerDB getPlayerDB() {
        return playerDB;
    }

    public void setPlayerDB(PlayerDB playerDB) {
        this.playerDB = playerDB;
    }

    public EncounterDB getEncounterDB() {
        return encounterDB;
    }

    public void setEncounterDB(EncounterDB encounterDB) {
        this.encounterDB = encounterDB;
    }

    public OptionDB getOptionDB() {
        return optionDB;
    }

    public void setOptionDB(OptionDB optionDB) {
        this.optionDB = optionDB;
    }
    
    
}
