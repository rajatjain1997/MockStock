/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.panels;

import client.*;
import dataclasses.*;
import exceptions.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Rajat
 */
public class TransactPanel extends AbstractFocusablePanel {
        public TransactPanel() {
            super();
            JLabel infoLabel = new JLabel(" ");
            JLabel stockLabel = new JLabel("Stock: ");
            JLabel playerIDLabel = new JLabel("Player ID: ");
            JLabel quantityLabel = new JLabel("Quantity: ");
            JComboBox stock = new JComboBox(new Vector<Stock>(Broker.getStocks()));
            this.setFocusRequester(stock);
            JTextField playerID = new JTextField(20);
            JTextField qty = new JTextField(5);
            JButton sell = new JButton("Sell");
            JButton buy = new JButton("Buy");
            JPanel thePanel = new JPanel(new GridLayout(4,2));
            thePanel.add(stockLabel);
            thePanel.add(stock);
            thePanel.add(playerIDLabel);
            thePanel.add(playerID);
            thePanel.add(quantityLabel);
            thePanel.add(qty);
            thePanel.add(buy);
            thePanel.add(sell);
            thePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(thePanel);
            this.add(infoLabel);
            
            buy.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Player p = Client.getInstance().readPlayer(Integer.parseInt(playerID.getText()));
                        int roundNo = Client.getInstance().readRoundNo();
                        Broker.buyStock(p,(Stock)stock.getSelectedItem(), Integer.parseInt(qty.getText()), roundNo);
                        Client.getInstance().writePlayer(p);
                        System.out.println(p);
                        infoLabel.setText("Transaction Successful");
                        infoLabel.setForeground(Color.green);
                        playerID.setText("");
                        qty.setText("");
                        stock.setSelectedIndex(0);
                        TransactPanel.this.repaint();
                    } catch (NumberFormatException | PlayerLockedException ex) {
                        infoLabel.setText("Transaction Unsuccessful");
                        infoLabel.setForeground(Color.red);
                        playerID.setText("");
                        qty.setText("");
                        stock.setSelectedIndex(0);
                        TransactPanel.this.repaint();
                    } 
                }
                
            });
            
            sell.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Player p = Client.getInstance().readPlayer(Integer.parseInt(playerID.getText()));
                        int roundNo = Client.getInstance().readRoundNo();
                        Broker.sellStock(p,(Stock)stock.getSelectedItem(), Integer.parseInt(qty.getText()), roundNo);
                        Client.getInstance().writePlayer(p);
                        System.out.println(p);
                        infoLabel.setText("Transaction Successful");
                        infoLabel.setForeground(Color.green);
                        playerID.setText("");
                        qty.setText("");
                        stock.setSelectedIndex(0);
                        TransactPanel.this.repaint();
                    } catch (NumberFormatException | PlayerLockedException ex) {
                        infoLabel.setText("Transaction Unsuccessful");
                        infoLabel.setForeground(Color.red);
                        playerID.setText("");
                        qty.setText("");
                        stock.setSelectedIndex(0);
                        TransactPanel.this.repaint();
                    } 
                }
                
            });
        }
}
