/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;


import client.listeners.*;
import client.panels.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
/**
 *
 * @author Rajat
 */
public class GUI {
    private static final JFrame theFrame = new JFrame("Mockstock Brokering Agency");
    private static JPanel displayPanel = new ConnectionPanel();
    private static final ArrayList<JToggleButton> controlButtons = new ArrayList<>();
    public static void setDisplayPanel(JPanel panel) {
        theFrame.remove(displayPanel);
        displayPanel = panel;
        theFrame.add(displayPanel);
        theFrame.validate();
        theFrame.repaint();
    }

    public static JFrame getTheFrame() {
        return theFrame;
    }

    public static ArrayList<JToggleButton> getControlButtons() {
        return controlButtons;
    }
    
    public static void buildGUI() {
        theFrame.setLayout(new BoxLayout(theFrame.getContentPane(),BoxLayout.Y_AXIS));
        JPanel controlPanel = new JPanel();
        initializeControlButtons();
        for(JToggleButton i:controlButtons) {
            controlPanel.add(i);
        }
        theFrame.add(controlPanel);
        theFrame.add(displayPanel);
        theFrame.setSize(550, 500);
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setVisible(true);
    }
    
    private static void initializeControlButtons() {
        controlButtons.add(new JToggleButton("Connect",true));
        controlButtons.get(0).addActionListener(new ConnectListener());
        controlButtons.add(new JToggleButton("Register"));
        controlButtons.get(1).addActionListener(new RegisterListener());
        controlButtons.get(1).setEnabled(false);
        controlButtons.add(new JToggleButton("Transact"));
        controlButtons.get(2).addActionListener(new TransactListener());
        controlButtons.get(2).setEnabled(false);
    }
}
