/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Encounter;

/**
 *This class takes care of most of the functionality relating to the View part of the MVC Architecture
 * @author sdg0118
 */
public class View {
    private MainFrame frame;
    private EntryFrame entry;
    private CreateNewPlayer create;
    
    public View()
    {
        frame = new MainFrame();
    }
    
    public void displayEntry()
    {
        entry = new EntryFrame();
    }
    
    public void createNewPlayer()
    {
        create = new CreateNewPlayer();
    }


    
    public void displayEncounter(Encounter encounter)
    {
        //Sets text in main box
        String displayText = "";
        displayText = displayText + encounter.getEncounterText();
        
        for (int i = 0 ; i < encounter.getOptions().length ; i++)
        {
            displayText = displayText + "\n" + (i+1) + (". ") + encounter.getOptions()[i].getName();
        }
        frame.getDisplayText().setText(displayText);
        
        //sets text in JComboBox
        frame.getBox().removeAllItems();
        String[] boxArray = new String[encounter.getOptions().length + 1];
        boxArray[0] = "---Please select an option. Choose wisely.---";
        for (int i = 0 ; i < encounter.getOptions().length ; i++)
        {
            boxArray[i+1] = (i+1) + (". ") + encounter.getOptions()[i].getName();
        }
        frame.getBox().addArray(boxArray);
    }
    
    public MainFrame getFrame() {
    return frame;
    }

    public void setFrame(MainFrame frame) {
        this.frame = frame;
    }
    
    public EntryFrame getEntry() {
        return entry;
    }

    public void setEntry(EntryFrame entry) {
        this.entry = entry;
        
        
    }

    public CreateNewPlayer getCreate() {
        return create;
    }

    public void setCreate(CreateNewPlayer create) {
        this.create = create;
    }
    
    
}
