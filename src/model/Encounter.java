/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Represents an encounter the player is going through
 * @author sdg0118
 */
public class Encounter {
    
    private int encounterID;
    private int nextEncounter;
    private int optionCount;
    String encounterText;
    private Option[] options;    
    
    //Constructor
    public Encounter(int encounterID, int nextEncounter, Option[] options, String encounterText)
    {   
        setEncounterID(encounterID);
        setNextEncounter(nextEncounter);        
        setOptions(options);
        setEncounterText(encounterText);
        setOptionCount(options.length);        
    }
    
    //Constructor, Default
    public Encounter()
    {        
    }
    
    //Returns a string that is readable by the TextReader object
    @Override
    public String toString()
    {
        String returnString = ("#%" + encounterID + "#" + nextEncounter + "#" + optionCount + "#" + encounterText);//TODO: inventory.toString
        if (this.options != null)
        {
            if (this.getOptions().length != 0)
            {
                for (int i = 0 ; i < this.optionCount ; i++)
                {
                    returnString = returnString + ("#" + this.options[i].getId());
                }
            }
        }
        returnString = returnString + ("#/%");
        
        return returnString;
    }
    
    //Getters and Setters   
    public int getEncounterID()
    {
        return this.encounterID;
    }
    
    public void setEncounterID(int encounterID)
    {
        this.encounterID = encounterID;
    }
    
        public int getNextEncounter()
    {
        return this.nextEncounter;
    }
    
    public void setNextEncounter(int nextEncounter)
    {
        this.nextEncounter = nextEncounter;
    }    
    
    public int getOptionCount()
    {
        return this.optionCount;
    }
    
    public void setOptionCount(int optionCount)
    {
        this.optionCount = optionCount;
    }    
    
    public Option[] getOptions()
    {
        return this.options;
    }
    
    public void setOptions(Option[] options)
    {
        this.options = options;
        setOptionCount(options.length);
    }
    
    public String getEncounterText()
    {
        return this.encounterText;
    }
    
    public void setEncounterText(String encounterText)
    {
        this.encounterText = encounterText;
    }
}
