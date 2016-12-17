/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.listener;

import dataclasses.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import server.GUI;
import server.Game;

/**
 *
 * @author Rajat
 */
public class StatsListener implements ActionListener{

    private JLabel noOfStocks;
    private JLabel noOfPlayers;
    private JLabel noOfBrokers;
    private JTextField startBalance;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JDialog statsDialog = new JDialog(GUI.getTheFrame(),"Stats!");
        JPanel statsPanel = new JPanel(new GridLayout(4,2));
        JPanel controlPanel = new JPanel();
        JLabel noOfBrokersLabel = new JLabel("No. of Brokers: ");
        JLabel noOfPlayersLabel = new JLabel("No. of Players: ");
        JLabel noOfStocksLabel = new JLabel("No. of Stocks: ");
        JLabel titleLabel = new JLabel("Server Statistics");
        JLabel balanceLabel = new JLabel("Start Balance: ");
        noOfBrokers = new JLabel(Integer.toString(Game.getNoOfBrokers()));
        noOfPlayers = new JLabel(Integer.toString(Player.getNumberOfTeams()));
        noOfStocks = new JLabel(Integer.toString(Game.getStocks().size()));
        startBalance = new JTextField(Long.toString(Game.getInitialBalance()));
        JButton exitButton = new JButton("Exit");
        JButton refreshButton = new JButton("Refresh");
        statsPanel.add(noOfBrokersLabel);
        statsPanel.add(noOfBrokers);
        statsPanel.add(noOfPlayersLabel);
        statsPanel.add(noOfPlayers);
        statsPanel.add(noOfStocksLabel);
        statsPanel.add(noOfStocks);
        statsPanel.add(balanceLabel);
        statsPanel.add(startBalance);
        controlPanel.add(refreshButton);
        controlPanel.add(exitButton);
        
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Game.setInitialBalance(Long.parseLong(startBalance.getText()));
               statsDialog.setVisible(false);
            }
        });
        
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetch();
                statsDialog.repaint();
            }
            
        });
        
        statsDialog.setLayout(new BoxLayout(statsDialog.getContentPane(),BoxLayout.Y_AXIS));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statsDialog.add(titleLabel);
        statsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statsDialog.add(statsPanel);
        controlPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statsDialog.add(controlPanel);
        statsDialog.setSize(250,200);
        statsDialog.setVisible(true);
    }
    
    public void fetch() {
        noOfBrokers.setText(Integer.toString(Game.getNoOfBrokers()));
        noOfPlayers.setText(Integer.toString(Player.getNumberOfTeams()));
        noOfStocks.setText(Integer.toString(Game.getStocks().size()));
    }
    
}
