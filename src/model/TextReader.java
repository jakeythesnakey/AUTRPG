/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Legacy Class. For Reading Text from file
 * @author sdg0118
 */
public class TextReader {
    
    protected ToFile toFile;
    
    public TextReader()
    {
        toFile = new ToFile();
    }
    
    public int getFirstInt(String fileName)
    {
        String inString = "";
        try{
            inString = toFile.readFile(fileName);
        }
        catch(Exception e)
        {
            System.out.print("Ja Fucked up da FirstInt Readin!");//TODO: debug remove after
        }
        int returnInt = 1;
        String[] inArray = inString.split("#");
        returnInt = Integer.parseInt(inArray[0]);
        return returnInt;
    }
        
    
    public Player playerReader(String inString)
    {
        Player returnPlayer = new Player();
        String[] inArray = inString.split("#");

        for (int i = 0 ; i < inArray.length; i++)
        {
            if (inArray[i].equals("Player"))
            {
                i++;
                returnPlayer.setName(inArray[i]);
                i++;
                returnPlayer.setCha(Integer.parseInt(inArray[i]));
                i++;
                returnPlayer.setStr(Integer.parseInt(inArray[i]));
                i++;
                returnPlayer.setIntelligence(Integer.parseInt(inArray[i]));
                i++;
                returnPlayer.setLck(Integer.parseInt(inArray[i]));
                i++;
                returnPlayer.setSanity(Integer.parseInt(inArray[i]));
                i++;
                returnPlayer.setSleep(Integer.parseInt(inArray[i]));
                i++;
                returnPlayer.setGrades(Integer.parseInt(inArray[i]));   
            }
        }
        
        return returnPlayer;
    }
    
    public Option optionReader(String inString, String optionNumber)
    {
        Option returnOption = new Option();
        String[] inArray = inString.split("#");
        for (int i = 0 ; i < inArray.length; i++)
        {
            //checks for start of option sequence
            if (inArray[i].equals("*" + optionNumber))
            {
                //passes saved option variables into returned option
                returnOption.setId(Integer.parseInt(optionNumber));
                i++;
                returnOption.setName(inArray[i]);
                i++;
                returnOption.setCha(Integer.parseInt(inArray[i]));
                i++;
                returnOption.setStr(Integer.parseInt(inArray[i]));
                i++;
                returnOption.setIntelligence(Integer.parseInt(inArray[i]));
                i++;
                returnOption.setLck(Integer.parseInt(inArray[i]));
                i++;
                returnOption.setSanity(Integer.parseInt(inArray[i]));
                i++;
                returnOption.setSleep(Integer.parseInt(inArray[i]));
                i++;
                returnOption.setGrades(Integer.parseInt(inArray[i]));
                i++;
                returnOption.setOutcome(inArray[i]);
            }
        }        
        return returnOption;
    }
    
    public Encounter encounterReader(String inString, String encounterNumber, String optionSaveList)
    {
        Encounter returnEncounter = new Encounter();
        String[] inArray = inString.split("#");//Tokenizes incoming string
        for (int i = 0 ; i < inArray.length; i++)//begins earch for specific encounter
        {
            if (inArray[i].equals("%" + encounterNumber))//finds unique encounter id denoted by %
            {
                returnEncounter.setEncounterID(Integer.parseInt(encounterNumber));
                i++;
                returnEncounter.setNextEncounter(Integer.parseInt(inArray[i]));
                i++;
                returnEncounter.setOptionCount(Integer.parseInt(inArray[i]));
                i++;
                returnEncounter.setEncounterText(inArray[i]);
                i++;
                Option[] optionArray = new Option[returnEncounter.getOptionCount()];//creates a new option array with sizeof as recorded in the file
                
                // This part grabs the options file and turns it into a local string 
                    String fullOptionString = "";
                    try{
                        fullOptionString = toFile.readFile(optionSaveList);                        
                    }
                    catch(Exception e)
                    {
                        System.out.print("Ja Fucked up da optionSaveList Readin!");//TODO: debug remove after
                    }
                for (int j = 0 ; j < optionArray.length; j++)
                {                   
                    if(fullOptionString != "")
                    {
                        optionArray[j] = this.optionReader(fullOptionString, inArray[i]);//This part tokenizes the options string and pulls out the option at current token as an object, adding it to the local optionArray
                        i++;//next option
                    }                    
                }                
                returnEncounter.setOptions(optionArray);//sets encounters optionArray to local optionArray                 
                i++;  
            }
        }        
        return returnEncounter;
    }
}
