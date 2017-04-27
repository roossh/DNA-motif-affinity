
package logic;
import matrix.Matrix;
/**
 * Luokka määrittää frekvenssimatriisista Position Weight -matriisin.
 * @author roosa
 */
public class PWMCalculator {
    private final Matrix matrix;
    private double[][] ppm;
    /**Luo matriisin frekvenssimatriisista.
     * 
     * @param matrix syötteenä annetaan frekvenssimatriisi
     */
    public PWMCalculator(Matrix matrix) {
        this.matrix = matrix;
    }
    
    /**
     * Käytössä olevaa laskentamallia voi potentiaalisesti vaihtaa.
     * Nyt ohjelma käyttää oletuksena "klassikkoa".
     * @return model käytössä oleva malli
     */
    private double getModel() {
        double model = 1.0 / Math.abs(matrix.getRowCount());
        
        return model;
    }
    /**
     * Laskee jokaiselle solulle PFM:ssä "painon" (eli logaritmisen todennäköisyyden).
     * @param positionArray array joka sisältää tietoa positioista
     * @return palauttaa painomatriisin
     */
    public Matrix getPWM(double[][] positionArray) {
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
    /**
     * Laskee PPM:n, toimii välivaiheena laskennassa.
     * @return palauttaa double[][]
     */
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
