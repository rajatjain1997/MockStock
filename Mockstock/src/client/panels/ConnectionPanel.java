/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.panels;

import client.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Rajat
 */
public class ConnectionPanel extends JPanel {
    
    public ConnectionPanel() {
        super();
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        JLabel infoLabel = new JLabel();
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel label = new JLabel("Server IP:");
        JTextField serverIP = new JTextField(20);
        serverIP.requestFocus();
        JButton connect = new JButton("Connect!");
        connect.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel infoPanel = new JPanel();
        infoPanel.add(label);
        infoPanel.add(serverIP);
        this.add(infoLabel);
        this.add(infoPanel);
        this.add(connect);
        connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Client.initializeConnection(serverIP.getText());
                    Client.getInstance().readStocks();
                    GUI.getControlButtons().get(0).setEnabled(false);
                    GUI.getControlButtons().get(1).setEnabled(true);
                    GUI.getControlButtons().get(2).setEnabled(true);
                    GUI.getControlButtons().get(1).setSelected(true);
                    GUI.setDisplayPanel(new RegisterPanel());
                } catch(Exception ex) {
                    infoLabel.setText("Connection Unsuccessful, try again");
                    infoLabel.setForeground(Color.red);
                    ConnectionPanel.this.repaint();
                }
            }  
        });
    }
}
