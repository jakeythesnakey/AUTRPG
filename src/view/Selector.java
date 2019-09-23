/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import javax.swing.JComboBox;

/**
 *This class is the jcombobox that the user selects their chosen path with
 * @author sdg0118
 */
public class Selector extends JComboBox {
    
    public Selector()
    {
       
        //this.setSize(300, 300);
        //this.setLocation(20,300);
        this.setVisible(true);
        
    }
    
    public void addArray(String[] options)
    {
        for (String item : options)
        {
            this.addItem(item);
        }
    }
    
}
