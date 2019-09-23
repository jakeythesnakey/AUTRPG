/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * The player's avatar in the game. holds stats and a name
 * @author sdg0118
 */
public class Player {
    
    protected String name;    
    protected int cha;
    protected int str;
    protected int intelligence;
    protected int lck;    
    protected int sanity;
    protected int sleep;
    protected int grades;
    protected int currentEncounter;
    
    
    //Constructor
    public Player(String name, int cha, int str, int intelligence, int lck, int sanity, int sleep, int grades)
    {
        
        this.name = name;
        setCha(cha);
        setStr(str);
        setIntelligence(intelligence);
        setLck(lck);
        
        setSanity(sanity);
        setSleep(sleep);
        setGrades(grades);    
        this.currentEncounter = 1;
    }
    

    
    //Default Constructor
    public Player()
    {
        setName("");
        setCha(1);
        setStr(1);
        setIntelligence(1);
        setLck(1);
        
        setSanity(50);
        setSleep(50);
        setGrades(50); 
        this.currentEncounter = 1;
    }
    
    //Allows an option to change a player's stats
    public void add(Option option)
    {
        setCha(getCha() + option.getCha());
        setStr(getStr() + option.getStr());
        setIntelligence(getIntelligence() + option.getIntelligence());
        setLck(getLck() + option.getLck());
        
        setSanity(getSanity() + option.getSanity());
        setSleep(getSleep() + option.getSleep());
        setGrades(getGrades() + option.getGrades());        
    }
    
    //Returns a string that is readable by the TextReader object
    @Override
    public String toString()
    {
        String returnString = ("#Player#" + name + "#" + cha + "#" + str+ "#" + intelligence + "#" + lck+ "#" + sanity + "#" + sleep + "#" + grades );//TODO: inventory.toString
        //if (inventory != null)
        //{
        //    returnString = returnString + ("#" + inventory.toString());
        //}
        returnString = returnString + ("#/Player\n");
        
        return returnString;
    }
    
    //like toString but more easily readable
    public String toReadableString()
    {
        String returnString = ("NAME: " + name + " CHA: " + cha + " STR: " + str+ " INT: " + intelligence + " LCK: " + lck+ " SaNiTy: " + sanity + " SLEEP: " + sleep + " GRADES: " + grades );//TODO: inventory.toString
        //if (inventory != null)
        //{
        //    returnString = returnString + ("#" + inventory.toString());
        //}       
        return returnString;
    }
    
    //Getters and Setters
    public String getName()
    {
        return this.name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public int getCha()
    {
        return this.cha;
    }
    
    public void setCha(int cha)
    {
        if (cha < 1)
        {
            this.cha = 1;
        }
        else if (cha > 3)
        {
           this.cha = 3; 
        }        
        else
        {
           this.cha = cha; 
        }        
    }
    
    public int getStr()
    {
        return this.str;
    }
    
    public void setStr(int str)
    {
        if (str < 1)
        {
            this.str = 1;
        }
        else if (str > 3)
        {
           this.str = 3; 
        }        
        else
        {
           this.str = str; 
        }        
    }
    
    public int getIntelligence()
    {
        return this.intelligence;
    }
    
        public void setIntelligence(int intelligence)
    {
        if (intelligence < 1)
        {
            this.intelligence = 1;
        }
        else if (intelligence > 3)
        {
           this.intelligence = 3; 
        }        
        else
        {
           this.intelligence = intelligence; 
        }        
    }
    
    public int getLck()
    {
        return this.lck;
    }
    
    public void setLck(int lck)
    {
        if (lck < 1)
        {
            this.lck = 1;
        }
        else if (lck > 3)
        {
           this.lck = 3; 
        }        
        else
        {
           this.lck = lck; 
        }        
    }
    
    public int getSanity()
    {
        return this.sanity;
    }
    
        public void setSanity(int sanity)
    {
        if (sanity < 0)
        {
            this.sanity = 0;
        }
        else if (sanity > 100)
        {
           this.sanity = 100; 
        }        
        else
        {
           this.sanity = sanity; 
        }        
    }
    
    public int getSleep()
    {
        return this.sleep;
    }
    
    public void setSleep(int sleep)
    {
        if (sleep < 0)
        {
            this.sleep = 0;
        }
        else if (sleep > 100)
        {
           this.sleep = 100; 
        }        
        else
        {
           this.sleep = sleep; 
        }        
    }
    
    public int getGrades()
    {
        return this.grades;
    }
    
    public void setGrades(int grades)
    {
        if (grades < 0)
        {
            this.grades = 0;
        }
        else if (grades > 100)
        {
           this.grades = 100; 
        }        
        else
        {
           this.grades = grades; 
        }        
    }    

    public int getCurrentEncounter() {
        return currentEncounter;
    }

    public void setCurrentEncounter(int currentEncounter) {
        this.currentEncounter = currentEncounter;
    }
    
}
