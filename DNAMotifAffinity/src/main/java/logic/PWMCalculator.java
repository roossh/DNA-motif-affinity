
package logic;
import matrix.Matrix;

public class PWMCalculator {
    // this class is used for calculating PWM matrix from PFM
    private final Matrix matrix;
    private double[][] ppm;
    
    public PWMCalculator(Matrix matrix) {
        this.matrix = matrix;
    }
    
    public double getModel() {
        double model = 1.0 / Math.abs(matrix.getRowCount());
        
        return model;
    }
    
    public Matrix getPWM(double[][] positionArray) {
        // calculates PWM based
        // assumes simples possible model where b = 1/|k|, where k = rowCount()
        
        double b = getModel();
        
        double[][] pwm = new double[matrix.getRowCount()][matrix.getSequenceLength()];
        
        for (int i = 0; i < matrix.getSequenceLength(); i++) {
            for (int j = 0; j < matrix.getRowCount(); j++) {
                double weight = positionArray[j][i] / b;
                pwm[j][i] = Math.log(weight) / Math.log(2);
            }
        }
        
        Matrix weightMatrix = new Matrix(pwm, false, this.matrix.getNucleotides());
        
        return weightMatrix;
    }
    
    public double[][] calculatePPM() {
        this.ppm = new double[matrix.getRowCount()][matrix.getSequenceLength()];
        
        for (int i = 0; i < matrix.getSequenceLength(); i++) {
            for (int j = 0; j < matrix.getRowCount(); j++) {
                double pmValue = matrix.getCellValue(j, i) / matrix.getColSum(i);
                this.ppm[j][i] = pmValue;
            }
        }
        
        return this.ppm;
    }
}
