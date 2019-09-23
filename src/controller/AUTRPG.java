/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.GameController;
import model.Player;
import view.EndCondition;
import view.EntryFrame;
import view.View;

/**
 *Main Functionality of RPG game
 * @author sdg0118
 */
public class AUTRPG {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        GameController mainGame = new GameController();//Model
        View view = new View();//View
        int newOption = 1; //This is basically the main status indicator for the entire game
        EndCondition end;
        
        //Checks if main button is pressed
        ConfirmListener listen = new ConfirmListener(view, mainGame, newOption);
        view.getFrame().getConfirm().addActionListener(listen);
        
        Scanner scanner = new Scanner(System.in);//Legacy 
        
        //Sets name
        System.out.println("Welcome to AUTRPG v1.1");
        System.out.println("The game where we are committed to mocking both you and your lifestyle.");
        
        //This part checks if a saved game should be opened.
        
        System.out.println("1: New Game, 2: Retrive Previous Save");
        int startOrSave = 0;
        view.displayEntry();
        while (startOrSave != 1 && startOrSave != 2)
        {
            startOrSave = view.getEntry().exitCondition;
            System.out.println(view.getEntry().exitCondition);
            try {
                Thread.sleep(500);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(AUTRPG.class.getName()).log(Level.SEVERE, null, ex);
            }
            /*//Legacy CLI interface
                System.out.println("1: New Game, 2: Retrive Previous Save");
                try{
                startOrSave = scanner.nextInt();
                }
                catch(Exception e)
                {
                System.out.println("1 or 2, it ain't that hard");
                }*/
        }
        view.getEntry().dispose();
        
        if((new File("SaveState.txt").isFile()) && (startOrSave == 2))//checks if saved file exists and opens it
        {
            try {
                mainGame.setPlayer(mainGame.getDb().getPlayerDB().retrievePlayer("Testy"));
            } catch (SQLException ex) {
                System.out.println("Player load failed. Creating new player");
                mainGame.setPlayer(new Player());
            }
            //mainGame.retrieveProgress();//legacy
            // = mainGame.getNextEncounter(); //legacy
        }        
        else//allows player to create their own player object amd new game
        {
            view.createNewPlayer();
            boolean nameSet = false;
            while (nameSet == false)
            {
                if(view.getCreate().isClose())
                {
                    mainGame.getPlayer().setName(view.getCreate().getNewName()); 
                    nameSet = true;                    
                } 
                try {
                    Thread.sleep(250);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AUTRPG.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            view.getCreate().dispose();
            /*//Legacy code
            System.out.println("What is your name?");
            mainGame.getPlayer().setName(scanner.nextLine()); 
            System.out.println("Imaginitive parents, I see... Well, let's get on with it.");*/
        }
        
        System.out.println("System set up. Name = " + mainGame.getPlayer().getName());
        
        //The following loop is the main funcionality of the game              
        while(newOption < 90)//options over 90 end the game
        {
            
            view.displayEncounter(mainGame.getCurrentEncounter());//Display in window
            System.out.println("99. to exit, 89. for player stats, 79. to save your progress");//Legacy CLI display
            
            //ActionListener 'listen' checks for player input and RUNS ENCOUNTER if player clicks confirm
            listen.clicked = 0;
            while(listen.clicked == 0)
            {
                //System.out.println(listen.clicked);//Debugging purposes
                try {
                    Thread.sleep(250);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AUTRPG.class.getName()).log(Level.SEVERE, null, ex);
                }
            }    
            
            //These are the game fail states
            if (mainGame.getPlayer().getSanity() == 0)
            {
                newOption = 91;
                String endString = "\nYou go mad. Delightfully, terribly mad. Was it the stress? The drink? The social anxiety? We may never know. What we do know is that you now inhabit a room with padded walls.";
                endString = endString + "\n\nFinal Stats: \n" + mainGame.getPlayer().toReadableString();
                end = new EndCondition(endString);
            }
            if (mainGame.getPlayer().getSleep() == 0)
            {
                newOption = 92;
                String endString = ("\nYou die due to lack of sleep. Elton John's 'Candle in the Wind' plays at you funeral. It's all very sad.");
                endString = endString + "\n\nFinal Stats: \n" + mainGame.getPlayer().toReadableString();
                end = new EndCondition(endString);
            }
            if (mainGame.getPlayer().getGrades() == 0)
            {
                newOption = 93;
                String endString = ("\nYou drop out. Sorry buddy. Too much play makes "+ mainGame.getPlayer().getName() + " a grocery boy.");
                endString = endString + "\n\nFinal Stats: \n" + mainGame.getPlayer().toReadableString();
                end = new EndCondition(endString);
            }
            if (mainGame.getNextEncounter() == 99)
            {
                newOption = 99;                
                String endString = ("You did it. By god you did it.");
                endString = endString + "\n\nFinal Stats: \n" + mainGame.getPlayer().toReadableString();
                end = new EndCondition(endString);
            }            
        }        
    }
    
}
