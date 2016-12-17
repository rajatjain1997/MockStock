/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import server.listener.*;
/**
 *
 * @author Rajat
 */
public class GUI {
    private static final JFrame theFrame = new JFrame("Mockstock Server");
    private static JTextArea leaderboardDisplay;
    private static JLabel currentRound;
    
    public static File getFile() {
		JFileChooser fileOpen = new JFileChooser();
		fileOpen.showOpenDialog(theFrame);
		return fileOpen.getSelectedFile();
    }
    
    public static void buildGUI() {
        theFrame.setLayout(new BoxLayout(theFrame.getContentPane(),BoxLayout.Y_AXIS));
        //RoundNumber GUI starts here
        JLabel roundNo = new JLabel("Round Number",SwingConstants.CENTER);
        roundNo.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton addButton = new JButton("+");
        JButton subButton = new JButton("-");
        currentRound = new JLabel("1",SwingConstants.CENTER);
        currentRound.setMinimumSize(new Dimension(100,0));
        currentRound.setPreferredSize(new Dimension(100,10));
        JPanel roundPanel = new JPanel();
        roundPanel.add(subButton);
        roundPanel.add(currentRound);
        roundPanel.add(addButton);
        
        addButton.addActionListener(new PlusButtonListener());
        subButton.addActionListener(new SubButtonListener());
        //RoundNumber GUI ends
        //Leaderboard GUI starts
        JLabel leaderboard = new JLabel("LEADERBOARD",SwingConstants.CENTER);
        leaderboard.setAlignmentX(Component.CENTER_ALIGNMENT);
        leaderboardDisplay = new JTextArea();
        leaderboardDisplay.setAlignmentX(Component.CENTER_ALIGNMENT);
        leaderboardDisplay.setMaximumSize(new Dimension(500,1000000));
        JButton serializeButton = new JButton("Serialize");
        serializeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        //Leaderboard GUI ends
        //Menubar GUI starts here
        JMenuBar menuBar = new JMenuBar();
        JMenuItem fileMenu = new JMenuItem("File");
        JMenuItem statsMenu = new JMenuItem("Stats");
        menuBar.setLayout(new FlowLayout());
        menuBar.add(fileMenu);
        menuBar.add(statsMenu);
        
        fileMenu.addActionListener(new FileListener());
        statsMenu.addActionListener(new StatsListener());
        //Just adding stuff now
        theFrame.setJMenuBar(menuBar);
        theFrame.add(roundNo);
        theFrame.add(roundPanel);
        theFrame.add(leaderboard);
        theFrame.add(leaderboardDisplay);
        theFrame.add(serializeButton);
        //Adding stuff is done
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setSize(550, 500);
        theFrame.setResizable(false);
        theFrame.setVisible(true);
    }

    public static JTextArea getLeaderboardDisplay() {
        return leaderboardDisplay;
    }

    public static int getCurrentRound() {
        return Integer.parseInt(currentRound.getText());
    }

    public static void setLeaderboardDisplay(JTextArea leaderboardDisplay) {
        GUI.leaderboardDisplay = leaderboardDisplay;
    }

    public static void setCurrentRound(int currentRound) {
        GUI.currentRound.setText(Integer.toString(currentRound));
    }

    public static JFrame getTheFrame() {
        return theFrame;
    }
    
    
    
}
