/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.listener;

import java.awt.event.*;
import server.GUI;
import server.Game;

/**
 *
 * @author Rajat
 */
public class PlusButtonListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        GUI.setCurrentRound(GUI.getCurrentRound()+1);
        Game.setCurrentRound(GUI.getCurrentRound()+1);
    }
    
}
