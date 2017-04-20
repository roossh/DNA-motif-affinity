/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dnamotifaffinity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JDialog;
import javax.swing.JTextField;
import logic.PWMCalculator;
import matrix.Matrix;
import readers.MatrixReader;
import readers.MutationAnalyser;

/**
 *
 * @author roosa
 */
public class RunListener implements ActionListener {

    private final JTextField vcf;
    private final JTextField bed;
    private final JTextField pfm;
    
    public RunListener(JTextField vcf, JTextField bed, JTextField pfm) {
        this.vcf = vcf;
        this.bed = bed;
        this.pfm = pfm;
    }
    
    @Override
    @SuppressWarnings("empty-statement")
    public void actionPerformed(ActionEvent e) {
        try {
            runAnalysis();
        } catch (IOException ex) {
            System.out.println("IOException");;
        }
        JDialog done = new JDialog();
        done.setSize(100, 100);
        done.setAlwaysOnTop(true);
        done.setTitle("Done!");
        done.setVisible(true);
    }
    
    public void runAnalysis() throws IOException, FileNotFoundException {
        System.out.println("VCF file is: " + vcf.getText());
        MatrixReader matrixReader = new MatrixReader(pfm.getText());
        matrixReader.readTheFile();
        Matrix matrix = matrixReader.createMatrix();
        
        matrixReader.closeReader();
        
        PWMCalculator calc = new PWMCalculator(matrix);
        double[][] ppm = calc.calculatePPM();
        Matrix pwm = calc.getPWM(ppm);
        MutationAnalyser mutationAnalysis = new MutationAnalyser(pwm);
        mutationAnalysis.run(vcf.getText(), bed.getText(), "src/main/resources/output_gui.txt");
    }
}
