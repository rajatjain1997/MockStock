/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import client.*;

/**
 *
 * @author Rajat
 */
public class RegisterPanel extends JPanel{
    
    JLabel nameLabel = new JLabel(" ");
    JLabel teamLabel = new JLabel(" ");
    JLabel confirmLabel = new JLabel(" ");

    public RegisterPanel() {
        super();
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        JLabel registerLabel = new JLabel("Player Name: ");
        JTextField playerName = new JTextField(20);
        JButton registerButton = new JButton("Register!");
        JPanel infoPanel = new JPanel();
        infoPanel.add(registerLabel);
        infoPanel.add(playerName);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        teamLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(confirmLabel);
        this.add(nameLabel);
        this.add(teamLabel);
        this.add(infoPanel);
        this.add(registerButton);
        
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!playerName.getText().trim().equals("")) {
                    int teamNo = Client.getInstance().registerPlayer(playerName.getText());
                    nameLabel.setText(playerName.getText());
                    teamLabel.setText(Integer.toString(teamNo));
                    confirmLabel.setText("Registration Successful!");
                    confirmLabel.setForeground(Color.green);
                    playerName.setText("");
                    RegisterPanel.this.repaint();
                } else {
                    confirmLabel.setText("Registration Unsuccessful!");
                    confirmLabel.setForeground(Color.red);
                    teamLabel.setText(" ");
                    nameLabel.setText(" ");
                }
            }
            
        });
    }
    
}
