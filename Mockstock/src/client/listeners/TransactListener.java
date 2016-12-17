/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.listeners;

import client.GUI;
import client.panels.TransactPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Rajat
 */
public class TransactListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        GUI.getControlButtons().get(1).setSelected(false);
        GUI.getControlButtons().get(2).setSelected(true);
        GUI.setDisplayPanel(new TransactPanel());
    }
    
    
}
