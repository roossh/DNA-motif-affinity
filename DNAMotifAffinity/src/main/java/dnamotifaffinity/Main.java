package dnamotifaffinity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import matrix.Matrix;
import java.lang.Math;
import logic.MutationPicker;
import logic.PWMCalculator;
import logic.ScoreCalculator;
import readers.MatrixReader;

public class Main {
    //Main is now used for testing stuff, in the end it will be used for starting the program only.
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        //basic functionality for a Positional Frequency Matrix:
        
        MatrixReader reader = new MatrixReader("src/main/resources/MA0466.1.pfm", true);
        reader.readTheFile();
        reader.createMatrix();
        Matrix matrix = reader.createMatrix();
        PWMCalculator calc = new PWMCalculator(matrix);
        double[][] ppm = calc.calculatePPM();
        Matrix pwm = calc.getPWM(ppm);
        
        MutationPicker picker = new MutationPicker(pwm);
        String vcf = "1\t.\t14\tG\tA";
        String bed = "1\t10\t20";
        System.out.println(picker.mutationInRegion(vcf, bed));
        System.out.println(picker.annotateMutation(vcf, bed));
    }    
}