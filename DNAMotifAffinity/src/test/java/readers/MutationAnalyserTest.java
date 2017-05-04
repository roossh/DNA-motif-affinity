/**
 * 
 */
package readers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import logic.PWMCalculator;
import matrix.Matrix;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author roosa
 */
public class MutationAnalyserTest {
    private MutationAnalyser ma;
    public MutationAnalyserTest() {
    
    }
    
    @Before
    public void setUp() throws IOException {      
        MatrixReader matrixReader = new MatrixReader("src/main/resources/MA0466.1.pfm");
        matrixReader.readTheFile();
        Matrix matrix = matrixReader.createMatrix();
        
        matrixReader.closeReader();
        
        PWMCalculator calc = new PWMCalculator(matrix);
        double[][] ppm = calc.calculatePPM();
        
        Matrix pwm = calc.getPWM(ppm);
        this.ma = new MutationAnalyser(pwm);
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
    @Test
    public void returnsTrueIfAllFilesInRightFormat() throws IOException {
        boolean run = ma.run("src/main/resources/sample1.vcf", "src/main/resources/cebpb.bed", "test.txt");    
        assertEquals(run, true);
    }
    
    @Test
    public void returnsFalseIfFileWronglyFormatted() throws IOException {
        boolean run = ma.run("src/main/resources/wrong.vcf", "src/main/resources/cebpb.bed", "test.txt");
        assertEquals(run, false);
    }
    
    @Test
    public void returnsFalseIfVCFInWrongFormat() throws IOException {
        boolean run = ma.run("src/main/resources/wrong.vcf", "src/main/resources/cebpb.bed", "test.txt");
        assertEquals(run, false);
    }
    
    @Test
    public void returnsFalseIfBEDInWrongFormat() throws IOException {
        boolean run = ma.run("src/main/resources/sample1.vcf", "src/main/resources/wrong.bed", "test.txt");    
        assertEquals(run, false);
    }
    
    public void formatsFirstLineRight() throws IOException {
        boolean run = ma.run("src/main/resources/sample1.vcf", "src/main/resources/wrong.bed", "test.txt");
        String line;
        boolean formattedRight = false;
        if (run) {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/test.txt"));
            while ((line = reader.readLine()) != null) {
                assertEquals(line, "#CHROM\tPOS\tREF\tALT\tMOTIFPOS\tMOTIFSEQUENCE\tMOTIFSCORE");
                break;
            }
        }
    }
    
     @Test
    public void throwsFileNotFound() throws IOException{
        boolean thrown = false;
        try {
            boolean run = ma.run("src/main/resources/nosuchthing.vcf", "src/main/resources/nosuchthing.bed", "test.txt");

        } catch (FileNotFoundException e) {
            thrown = true;
        }
        
        assertEquals(true, thrown);
    }
}
