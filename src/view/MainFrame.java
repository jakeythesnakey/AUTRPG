/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * This class creates the central frame of the game. It is manually coded and does not use a form in order to show proficiency with GUI functionality
 * @author sdg0118
 */
public class MainFrame extends JFrame{
    
    private JPanel panel;
    private JButton confirm;
    private Selector box;
    private JTextArea displayText;
    public static final int INDENT = 10;
    
    public MainFrame()
    {
        this.setTitle("AUTRPG");
        this.setSize(800, 600);
        this.setLocation(200,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        
        //Setup main continer
        panel = new JPanel();
        panel.setSize(this.getWidth()-40, this.getHeight()-40);
        panel.setLocation(0,0);
        panel.setLayout(null);
        panel.setVisible(true);        
        this.getContentPane().add(panel);

        //Setup drop-down box and confirm button
        box = new Selector();
        confirm = new ConfirmButton("Confirm");
        panel.add(confirm);
        panel.add(box);
        
        displayText = new JTextArea();
        panel.add(displayText);
        
        int bottomRowHeight = panel.getHeight() - INDENT - box.getPreferredSize().height;
        
        box.setBounds(INDENT ,bottomRowHeight, panel.getWidth() - confirm.getPreferredSize().width - 3 * INDENT, box.getPreferredSize().height);
        confirm.setBounds(INDENT + box.getX() + box.getWidth(), bottomRowHeight, confirm.getPreferredSize().width, confirm.getPreferredSize().height);
        displayText.setBounds(INDENT, INDENT, panel.getWidth() -2*INDENT, panel.getHeight()-box.getPreferredSize().height - 3 * INDENT);
        
        displayText.setText("Hello World");
        
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JButton getConfirm() {
        return confirm;
    }

    public void setConfirm(JButton confirm) {
        this.confirm = confirm;
    }

    public Selector getBox() {
        return box;
    }

    public void setBox(Selector box) {
        this.box = box;
    }

    public JTextArea getDisplayText() {
        return displayText;
    }

    public void setDisplayText(JTextArea displayText) {
        this.displayText = displayText;
    }
    
    
    
}
