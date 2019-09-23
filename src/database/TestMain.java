/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Encounter;
import model.Option;
import model.Player;
import model.TextReader;

/**
 * Class for reading from text file to database (main)
 * @author sdg0118
 */
public class TestMain {
    
    public static void main(String[] args)
    {
        DataBase db = new DataBase();
        
        FillEncounter e = new FillEncounter(db);
        FillOption o = new FillOption(db);
        
        try {
            System.out.println(db.getEncounterDB().retrieveEncounter(1, db.getOptionDB()).toString());
        } catch (SQLException ex) {
            Logger.getLogger(TestMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        
        
        
    }    
}
