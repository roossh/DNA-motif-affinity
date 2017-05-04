/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import logic.PWMCalculator;
import matrix.Matrix;
import readers.MatrixReader;
import readers.MutationAnalyser;

/**Kuuntelee Run-nappia.
 *
 * @author roosa
 */
public class RunListener implements ActionListener {

    private final JTextField vcf;
    private final JTextField bed;
    private final JTextField pfm;
    private final JTextField output;
    /**RunListener vaatii tiedostoja toimiakseen.
     * 
     * @param vcf Variant Call Format
     * @param bed Regions file
     * @param pfm Frekvenssimatriisi
     * @param output Nimi tulostiedostolle
     */
    public RunListener(JTextField vcf, JTextField bed, JTextField pfm, JTextField output) {
        this.vcf = vcf;
        this.bed = bed;
        this.pfm = pfm;
        this.output = output;
    }
    
    @Override
    @SuppressWarnings("empty-statement")
    public void actionPerformed(ActionEvent e) {
        boolean runSucceeded = false;
        try {
            runSucceeded = runAnalysis();
        } catch (IOException ex) {
            System.out.println("IOException");;
        }
        
        if (runSucceeded) {
            JDialog done = new JDialog();
            done.setSize(200, 100);
            done.setAlwaysOnTop(true);
            done.setTitle("Done!");
            done.setVisible(true);
        } else {
            JDialog failed = new JDialog();
            failed.setSize(200, 100);
            failed.setAlwaysOnTop(true);
            failed.setTitle("Run failed!");
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());
            JTextField textField = new JTextField();
            textField.setText("Run failed, check the log file.");
            textField.setEditable(false);
            panel.add(textField);
            failed.add(panel);
            failed.setVisible(true);
        }
    }
    /**Käynnistää analyysin ajon napista.
     * 
     * @return runSucceeded kertoo, onnistuiko ajo vai ei.
     * @throws IOException heittää errorin
     * @throws FileNotFoundException heittää errorin
     * 
     */
    private boolean runAnalysis() throws IOException, FileNotFoundException {
        MatrixReader matrixReader = new MatrixReader(pfm.getText());
        matrixReader.readTheFile();
        Matrix matrix = matrixReader.createMatrix();
        
        matrixReader.closeReader();
        
        PWMCalculator calc = new PWMCalculator(matrix);
        double[][] ppm = calc.calculatePPM();
        
        Matrix pwm = calc.getPWM(ppm);
        MutationAnalyser mutationAnalysis = new MutationAnalyser(pwm);
        
        String outputName;
        
        if (output.getText().equals("")) {
            outputName = "motif_output.txt";
        } else {
            outputName = output.getText();
        }
        
        boolean runSucceeded = mutationAnalysis.run(vcf.getText(), bed.getText(), outputName);
        return runSucceeded;
    }
}
