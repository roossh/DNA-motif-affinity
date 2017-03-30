package logic;

import java.io.IOException;
import matrix.Matrix;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import readers.MatrixReader;

public class ScoreCalculatorTest {
    private ScoreCalculator scoreCalc;
    public ScoreCalculatorTest() {
    }
    
    @Before
    public void setUp() throws IOException {
        MatrixReader reader = new MatrixReader("src/main/resources/MA0466.1.pfm", true);
        reader.readTheFile();
        reader.createMatrix();
        Matrix matrix = reader.createMatrix();
        PWMCalculator calc = new PWMCalculator(matrix);
        double[][] ppm = calc.calculatePPM();
        Matrix pwm = calc.getPWM(ppm);
        
        this.scoreCalc = new ScoreCalculator(pwm);
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void calculatorGivesCorretScoreWhenPWMIsZero() {
        double score = scoreCalc.giveAffinityScore("A", 2);
        assertEquals(score, Double.NEGATIVE_INFINITY, 0.0001);
    }
    
    @Test
    public void calculatorGivesCorrectScoreWhenPWMIsMoreThanZero() {
        double score = scoreCalc.giveAffinityScore("T", 4);
        assertEquals(score, -0.358032, 0.000001);
    }
    
    @Test
    public void calculatorGivesCorrectScoreWhenAltIsOnlyBase() {
        double score = scoreCalc.giveAffinityScore("T", 2);
        assertEquals(score, 2.0, 0.00001);
    }
    
    @Test
    public void throwsOutOfBoundsException() {
        boolean thrown = false;
        try {
            double score = scoreCalc.giveAffinityScore("A", 11);
        } catch (ArrayIndexOutOfBoundsException e) {
            thrown = true;
        }
        
        assertEquals(true, thrown);
    }
}
