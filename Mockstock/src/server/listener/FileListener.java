/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.listener;

import java.awt.event.*;
import server.Game;

/**
 *
 * @author Rajat
 */
public class FileListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        Game.readStocks();
    }  
}
