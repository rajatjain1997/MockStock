/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.panels;

import java.awt.Graphics;
import javax.swing.*;

/**
 *
 * @author Rajat
 */
public abstract class AbstractFocusablePanel extends JPanel{
    
    private JComponent focusRequester;
    public void giveFocus() {
        focusRequester.requestFocus();
    }
    protected final void setFocusRequester(JComponent focusRequester) {
        this.focusRequester = focusRequester;
    }
    
    @Override
    public void repaint() {
        super.repaint();
        if(this.focusRequester!=null) {
            this.giveFocus();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(this.focusRequester!=null) {
            this.giveFocus();
        }
    }
    
    
    
}
