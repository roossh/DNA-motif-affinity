/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dnamotifaffinity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author roosa
 */
public class MenuListener implements ActionListener {
    
    private final JFileChooser fileChooser;
    private final JFrame frame;
    private String fileLocation;
    private JTextField textArea;
    private final JMenuItem vcf;
    private final JMenuItem bed;
    private final JMenuItem pfm;
    private final boolean allFiles;
    private boolean vcfFound;
    private boolean bedFound;
    private boolean pfmFound;
    private final JButton runButton;
    private JTextField vcfField;
    private JTextField bedField;
    private JTextField pfmField;
    
    public MenuListener(JFrame frame, JMenuItem vcf, JMenuItem bed, JMenuItem pfm, JButton runButton, JTextField vcfField, JTextField bedField, JTextField pfmField) {
        this.fileChooser = new JFileChooser();
        this.frame = frame;
        //his.textArea = textArea;
        this.vcf = vcf;
        this.bed = bed;
        this.pfm = pfm;
        this.allFiles = false;
        this.vcfFound = false;
        this.bedFound = false;
        this.pfmFound = false;
        this.runButton = runButton;
        this.vcfField = vcfField;
        this.bedField = bedField;
        this.pfmField = pfmField;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        System.out.println(e.getSource());
        System.out.println(vcf);
        //JFileChooser fileChooser = new JFileChooser();
        if (e.getSource() == this.vcf) {
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Variant Call Format", "vcf");
            String vcfText = chooseFile(filter);
            vcfFound = true;
            vcfField.setText(vcfText);
        } else if (e.getSource() == bed) {
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Regions file", "bed");
            String bedText = chooseFile(filter);
            bedFound = true;
            bedField.setText(bedText);
        } else if (e.getSource() == pfm) {
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Position Frequency Matrix", "pfm");
            String pfmText = chooseFile(filter);
            pfmFound = true;
            pfmField.setText(pfmText);
        }
        
        if (vcfFound && bedFound && pfmFound) {
            runButton.setEnabled(true);
        }
    }
    
    public String getMenuItemLocation() {
        return "";
    }
    
    public String chooseFile(FileNameExtensionFilter filter) {
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(frame);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                fileChooser.getSelectedFile().getName());
        }
        
        return fileChooser.getSelectedFile().getAbsolutePath();
    }
    
    public String getAbsolutePath() {
        return fileChooser.getSelectedFile().getAbsolutePath();
    }
    
}
