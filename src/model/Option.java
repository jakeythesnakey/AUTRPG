/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * A choice that effects the player. It is provided be Encounter
 * @author sdg0118
 */
public class Option extends Player{
    
    private int id;
    private String outcome;

    //Constructor
    public Option(int id, String name, int cha, int str, int intelligence, int lck, int sanity, int sleep, int grades, String outcome)
    {
        this.id = id;
        this.name = name;
        this.cha = cha;
        this.str = str;
        this.intelligence = intelligence;
        this.lck = lck;
        this.sanity = sanity;
        this.sleep = sleep;
        this.grades = grades;
        this.outcome = outcome;
    }
    
        //Constructor, default
    public Option()
    {  
        this.name = "NameEmpty";
        this.cha = 0;
        this.str = 0;
        this.intelligence = 0;
        this.lck = 0;
        this.sanity = 0;
        this.sleep = 0;
        this.grades = 0;
        this.outcome = "OutcomeEmpty";
    }
    
    //Returns a string that is readable by the TextReader object
    @Override
    public String toString()
    {
        String returnString = ("#*" + id + "#" + name + "#" + cha + "#" + str+ "#" + intelligence + "#" + lck+ "#" + sanity + "#" + sleep + "#" + grades + "#" + outcome );//TODO: inventory.toString
        //if (inventory != null)
        //{
        //    returnString = returnString + ("#" + inventory.toString());
        //}
        returnString = returnString + ("#/*");
        
        return returnString;
    }
    
    //Getters and Setters
    public String getOutcome()
    {
        return this.outcome;
    } 
    
    public void setOutcome(String outcome)
    {
        this.outcome = outcome;
    } 
    
    public void setId(int id)
    {
        this.id = id;
    } 
    
    public int getId()
    {
        return this.id;
    } 
    
    public void setCha(int cha)
    {
        this.cha = cha;
    }    
    
    public void setStr(int str)    
    {
        this.str = str;
    } 
      
    public void setIntelligence(int intelligence)
    {
        this.intelligence = intelligence;
    } 
    
    public void setLck(int lck)
    {
        this.lck = lck;
    } 
    
        public void setSanity(int sanity)
    {
        this.sanity = sanity;
    }
    
    public void setSleep(int sleep)
    {
        this.sleep = sleep;
    }
    
    public void setGrades(int grades)
    {
        this.grades = grades;
    }  

    public void setName(String name) {
        this.name = name;
    }
    
    

    public String getName() {
        return name;
    }

    public int getCha() {
        return cha;
    }

    public int getStr() {
        return str;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getLck() {
        return lck;
    }

    public int getSanity() {
        return sanity;
    }

    public int getSleep() {
        return sleep;
    }

    public int getGrades() {
        return grades;
    }
}



