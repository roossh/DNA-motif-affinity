package logic;

import java.util.List;
import matrix.Matrix;

public class ScoreCalculator {
    //requires there to be already calculated PWM
    //this will be used when annotating mutations in a variant call file
    private final Matrix pwm;
    
    public ScoreCalculator(Matrix pwm) {
        this.pwm = pwm;
    }
    
    public double giveAffinityScore(String alt, int pos) {
        double affinity = 0.0;
        
        if (pos >= pwm.getSequenceLength()) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            List<String> nucleotides = pwm.getNucleotides();
            int rowNumber = nucleotides.indexOf(alt);
            affinity = pwm.getCellValue(rowNumber, pos);
        }
        
        return affinity;
    }
}
