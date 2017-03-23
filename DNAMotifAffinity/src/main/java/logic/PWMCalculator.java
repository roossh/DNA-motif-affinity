/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;
import matrix.Matrix;
import java.lang.Math;

/**
 *
 * @author roosa
 */
public class PWMCalculator {
    // this class is used for calculating PWM matrix from PFM
    private Matrix matrix;
    private double[][] ppm;
    
    public PWMCalculator(Matrix matrix) {
        this.matrix = matrix;
    }
    
    public double getModel() {
        double model = 1.0/Math.abs(matrix.getRowCount());
        
        return model;
    }
    
    public Matrix getPWM(double[][] positionArray) {
        // calculates PWM based
        // assumes simples possible model where b = 1/|k|, where k = rowCount()
        
        double b = getModel();
        
        double[][] pwm = new double[matrix.getRowCount()][matrix.getSequenceLength()];
        
        for (int i = 0; i < matrix.getSequenceLength(); i++) {
            for (int j = 0; j < matrix.getRowCount(); j++) {
                double weight = positionArray[j][i]/b;
                pwm[j][i] = Math.log(weight)/Math.log(2);
            }
        }
        
        Matrix weightMatrix = new Matrix(pwm, true);
        
        return weightMatrix;
    }
    
    public double[][] calculatePPM() {
        this.ppm = new double[matrix.getRowCount()][matrix.getSequenceLength()];
        
        for (int i = 0; i < matrix.getSequenceLength(); i++) {
            for (int j = 0; j < matrix.getRowCount(); j++) {
                double pmValue = matrix.getCellValue(j, i)/matrix.getColSum(i);
                this.ppm[j][i] = pmValue;
            }
        }
        
        return this.ppm;
    }
}
