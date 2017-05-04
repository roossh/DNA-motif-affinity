/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.CancelListener;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import readers.MatrixReader;

/**
 *Metodi rakentaa graafisen käyttöliittymän.
 * @author roosa
 */
public class GUI implements Runnable {
    
    private JFrame frame;
    private File pfm;
    private File vcf;
    private File bed;
    private MatrixReader matrixReader;
    
    @Override
    public void run() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        // If Nimbus is not available, you can set the GUI to another look and feel.
        }


        this.frame = new JFrame("Motif Affinity Calculator");
        //frame.setPreferredSize(new Dimension(800, 400));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        createComponents(frame.getContentPane());
        
        frame.setPreferredSize(new Dimension(800, 400));
        //frame.pack();
        
        frame.setIconImage(new ImageIcon(getClass().getResource("/dna.png")).getImage());
        
        frame.pack();
        
        frame.setVisible(true);
    }
    
    private void createComponents(Container container) {
        JPanel basePanel = new JPanel();
        
        basePanel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Files");
        JMenu helpMenu = new JMenu("Help");
        
        JMenuItem vcfFile = new JMenuItem("Choose a VCF");
        JMenuItem bedFile = new JMenuItem("Choose a BED");
        JMenuItem pfmFile = new JMenuItem("Choose a PFM");
        
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 1;
        constraints.gridy = 0;
        
        fileMenu.add(vcfFile);
        fileMenu.add(bedFile);
        fileMenu.add(pfmFile);
        
        JMenu helpFile = new JMenu("Help");
        JEditorPane helpPane = new JEditorPane();
        helpPane.setEditable(false);
        
        String helpText = "DNA Motif Affinity Change\n\nCalculate affinity score for specific variants " +
                "using a position frequency matrix\nfor different variants\n\n" + 
                "Required files (all must be tab-separated!):\nVCF: Variant Call Format CHROM | ID | POS | REF | ALT | ...\n" +
                "BED: regions file CHROM | START | STOP | ...\n" + 
                "PFM: position frequency matrix from JASPAR";
        
        helpPane.setText(helpText);
        
        helpPane.setSize(500, 500);
        
        helpFile.add(helpPane);
        helpMenu.add(helpFile);        
       
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        
        menuBar.setSize(frame.getWidth(), 25);
        
        JPanel lowerPanel = new JPanel();
        
        JButton runButton = new JButton("Run");
        lowerPanel.add(runButton);
        
        runButton.setEnabled(false);

        JTextField vcfField = new JTextField();
        JTextField bedField = new JTextField();
        JTextField pfmField = new JTextField();
        JTextField outputField = new JTextField();
        
        vcfField.setSize(100, 25);
        bedField.setSize(100, 25);
        pfmField.setSize(100, 25);
        outputField.setSize(100, 25);
        
        vcfField.setEnabled(false);
        bedField.setEnabled(false);
        pfmField.setEnabled(false);
        
        JPanel chosenFields = new JPanel();
        
        chosenFields.setSize(400, 200);
        
        chosenFields.setLayout(new GridLayout(4, 2));
        
        JLabel vcfLabel = new JLabel("VCF:");
        JLabel bedLabel = new JLabel("BED:");
        JLabel pfmLabel = new JLabel("PFM:");
        JLabel outputLabel = new JLabel("Output name: ");
        
        chosenFields.add(vcfLabel);
        chosenFields.add(vcfField);
        chosenFields.add(bedLabel);
        chosenFields.add(bedField);
        chosenFields.add(pfmLabel);
        chosenFields.add(pfmField);
        chosenFields.add(outputLabel);
        chosenFields.add(outputField);
        
        MenuListener menuListener = new MenuListener(frame, vcfFile, bedFile, pfmFile, runButton, vcfField, bedField, pfmField, outputField);
        
        vcfFile.addActionListener(menuListener);
        bedFile.addActionListener(menuListener);
        pfmFile.addActionListener(menuListener);
        
        runButton.addActionListener(new RunListener(vcfField, bedField, pfmField, outputField));
        
        JButton cancelButton = new JButton("Cancel");
        lowerPanel.add(cancelButton);
        cancelButton.setEnabled(true);
        cancelButton.addActionListener(new CancelListener());
        
        JTabbedPane tabPane = new JTabbedPane();
        JTextField logger = new JTextField();
        JTextField outputter = new JTextField();
        JPanel plotter = new JPanel();
        
        logger.setText("Logger here.");
        outputter.setText("Output info here");
        
        logger.setEnabled(false);
        outputter.setEnabled(false);
        tabPane.addTab("Logger", logger);
        tabPane.addTab("Output", outputter);
        tabPane.addTab("Plot", plotter);
        
        
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        
        basePanel.add(menuBar, constraints);
        
        
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 0;
        constraints.gridy = 2;
        
        basePanel.add(chosenFields, constraints);
        
        
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 0;
        constraints.gridy = 3;
        
        basePanel.add(lowerPanel, constraints);

        container.add(basePanel);
    }
}
