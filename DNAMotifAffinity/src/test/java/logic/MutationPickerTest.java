/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author roosa
 */
public class MutationPickerTest {

    private MutationPicker picker;
    private String vcf;
    private String bed;
    
    public MutationPickerTest() {
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
        
        this.picker = new MutationPicker(pwm);
        this.vcf = "1\t.\t14\tG\tA";
        this.bed = "1\t10\t20";
    }

    @Test
    public void inRegionReturnsTrueWhenInRegion() {
        assertEquals(true, this.picker.mutationInRegion(vcf, bed));
    }
    
    @Test
    public void correctScoreWhenInRegion() {
        assertEquals(-2.448769, this.picker.annotateMutation(vcf, bed), 0.0001);
    }
    
    @Test
    public void returnsFalseWhenNotInRegion() {
        String alt = "2\t.\t12\tA\tG";        
        assertEquals(false, this.picker.mutationInRegion(alt, bed));
    }
    
    @Test
    public void returnsZeroWhenNotInRegion() {
        String alt = "2\t.\t12\tA\tG";
        assertEquals(0.0, this.picker.annotateMutation(alt, bed), 0.0001);
    }
}
