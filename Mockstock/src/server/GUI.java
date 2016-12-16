/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
/**
 *
 * @author Rajat
 */
public class GUI {
    private static JFrame theFrame = new JFrame("Mockstock Server");
    public static File getFile() {
		JFileChooser fileOpen = new JFileChooser();
		fileOpen.showOpenDialog(theFrame);
		return fileOpen.getSelectedFile();
	}
}
