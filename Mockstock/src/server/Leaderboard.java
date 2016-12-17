/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import dataclasses.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Rajat
 */
public class Leaderboard {
    private static ArrayList<Player> leaderboard;
    public static String toPrint() {
        leaderboard =Game.getPlayers();
        Collections.sort(leaderboard);
        int x=1;
        String s="";
        for(Player i:leaderboard) {
            s=s+x+i+"\n";
            if(++x>10) {
                break;
            }
        }
        return s;
    }
}
