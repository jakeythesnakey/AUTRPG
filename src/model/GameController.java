/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import database.DataBase;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 * This class holds most of the games functionality. It is the Model in the MVC architecture
 * @author sdg0118
 */
public class GameController {
    
    private Player player;    
    private Encounter currentEncounter;    
    private int nextEncounter;
    private String encounterURL;
    private String encounterList;
    private String optionURL;
    private String optionList;

    
    private ToFile toFile;
    private TextReader textReader;
    
    private DataBase db;
    
    
    //Constructor
    public GameController()
    {
        player = new Player();
        nextEncounter = 1;
        encounterURL = "encounters.txt";
        optionURL = "options.txt";  
        toFile = new ToFile();
        textReader = new TextReader();
        
        db = new DataBase();
        
        
        //This part reads the encounters file to a string
        encounterList = "";
        try{
            encounterList = toFile.readFile(encounterURL);
        }
        catch(Exception e)
        {
            System.out.print("Ja Fucked up da Readin!");
        }
        
        //sets first encounter
        this.nextEncounter();
    }
    
    //This method reads the next encounter from file and sets it as the current encounter.
    public void nextEncounter()
    {
        try {
            currentEncounter = db.getEncounterDB().retrieveEncounter(nextEncounter, db.getOptionDB());
        } catch (SQLException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //currentEncounter = textReader.encounterReader(encounterList, Integer.toString(nextEncounter), optionURL);//legacy code
        nextEncounter = currentEncounter.getNextEncounter();//increments next encounter
    }
    
    //This method accepts input selecting the next option taken and processes it.
    public int runEncounter(int newOption)
    {
        
        if(newOption == 89){
            System.out.println(this.getPlayer().toReadableString());
        }
        if(newOption == 79){
            saveProgress();
        }
        else if(newOption < 1 || newOption > this.getCurrentEncounter().getOptions().length){
            System.out.println("please select an option between 1 and " + this.getCurrentEncounter().getOptionCount());
        }
        else{
            System.out.println(this.getCurrentEncounter().getOptions()[newOption - 1].getOutcome());
            this.getPlayer().add(this.getCurrentEncounter().getOptions()[newOption - 1]);
            this.nextEncounter();
        }
        return newOption;        
    }
    
    //This method saves the player's progress to file
    public void saveProgress()
    {
        try {
            db.getPlayerDB().insertPlayer(currentEncounter.getEncounterID(), player);
            System.out.println("Progress saved");
        } catch (SQLException ex) {
            System.out.println("Progress not saved");
        }
        
    }
    
    
    //This method retrieves a save state from file

    public void retrieveProgress()
    {
        try {
            this.setPlayer(db.getPlayerDB().retrievePlayer("ReturnedfromTheDead"));//TO IMPROVE
            currentEncounter = db.getEncounterDB().retrieveEncounter(player.getCurrentEncounter(), db.getOptionDB());
        } catch (SQLException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nextEncounter = currentEncounter.getNextEncounter();
    }
    
    //Getters and setters
    public Player getPlayer() {
        return player;
    }  
        
    public Encounter getCurrentEncounter() {
        return currentEncounter;
    }

    public int getNextEncounter() {
        return nextEncounter;
    }

    public String getEncounterURL() {
        return encounterURL;
    }

    public String getEncounterList() {
        return encounterList;
    }

    public String getOptionList() {
        return optionList;
    }

    public String getOptionURL() {
        return optionURL;
    }

    public ToFile getToFile() {
        return toFile;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setCurrentEncounter(Encounter currentEncounter) {
        this.currentEncounter = currentEncounter;
    }

    public void setNextEncounter(int nextEncounter) {
        this.nextEncounter = nextEncounter;
    }

    public void setEncounterURL(String encounterURL) {
        this.encounterURL = encounterURL;
    }

    public void setEncounterList(String encounterList) {
        this.encounterList = encounterList;
    }

    public void setOptionURL(String optionURL) {
        this.optionURL = optionURL;
    }

    public void setOptionList(String optionList) {
        this.optionList = optionList;
    }

    public void setToFile(ToFile toFile) {
        this.toFile = toFile;
    }
    
    public void setTextReader(TextReader textReader) {
        this.textReader = textReader;
    }

    public TextReader getTextReader() {
        return textReader;
    }

    public DataBase getDb() {
        return db;
    }

    public void setDb(DataBase db) {
        this.db = db;
    }
    
    
}
