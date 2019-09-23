package database;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Encounter;
import model.Option;
import model.Player;
import model.TextReader;
import model.ToFile;

/**
 *Class for reading from text file to database
 * @author sdg0118
 */
public class FillEncounter {
    
    public DataBase db;
    
    public FillEncounter(DataBase newdb)
    {
        this.db  = newdb;
        TextReader textReader = new TextReader();
        String encounterURL = "encounters.txt";
        String optionURL = "options.txt";
        ToFile toFile = new ToFile();
        
        Encounter currentEncounter = new Encounter();
        String encounterList = "";
        
        try {
            db.getEncounterDB().createTableEncounter();
        } catch (SQLException ex) {
            System.out.println("Table not Created");;
        }
        
        try{
            encounterList = toFile.readFile(encounterURL);//reads list of encounters from text file
        }
        catch(Exception e)
        {
            System.out.print("Ja Fucked up da Readin de EncounterList!");
        }
        
        for (int i = 1; i <16; i++)
        {
            currentEncounter = textReader.encounterReader(encounterList, Integer.toString(i), optionURL);//
            try {
                db.getEncounterDB().insertEncounter(currentEncounter);
            } catch (SQLException ex) {
                Logger.getLogger(FillEncounter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
}