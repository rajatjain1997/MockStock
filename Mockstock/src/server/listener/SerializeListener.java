/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.Leaderboard;
/**
 *
 * @author Rajat
 */
public class SerializeListener implements ActionListener {
    private int files = 0;
    PrintWriter out;
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            out = new PrintWriter("Result "+(++files)+".txt");
            out.print(Leaderboard.toPrint());
            out.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
}
