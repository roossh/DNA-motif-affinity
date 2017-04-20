/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dnamotifaffinity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author roosa
 */
public class GUIListener implements ActionListener {
    
    private final String fileLocation;
    
    public GUIListener(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
