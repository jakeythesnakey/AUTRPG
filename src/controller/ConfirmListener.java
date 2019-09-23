/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.GameController;
import view.Result;
import view.View;

/**
 * Listens for the player to click the confirm button and spurs further functionality
 * @author sdg0118
 */
public class ConfirmListener implements ActionListener {
    
    View view;
    GameController control;
    int newOption;
    int clicked;
    
    public ConfirmListener(View view, GameController control, int newOption)
    {
        this.view = view;
        this.control = control;
        this.newOption = newOption;
        this.clicked = 0;
    }
    
    @Override
    public void actionPerformed(ActionEvent event){
        //System.out.println("Button pressed");//testing functionality

        //checks if valid option has been selected in combobox and then executes the option
        if (view.getFrame().getBox().getSelectedIndex() != 0)
        {

            newOption = view.getFrame().getBox().getSelectedIndex();
            System.out.println(newOption);
            Result result = new Result(control.getCurrentEncounter().getOptions()[newOption-1].getOutcome());

            newOption = control.runEncounter(newOption);
            this.clicked = 1;
            
            
        }
    }
}