package logic;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import matrix.Matrix;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import matrix.Matrix;
import logic.PWMCalculator;
/**
 *
 * @author roosa
 */
public class PWMCalculatorTest {
    private Matrix matrix;
    private double[][] m;
    private PWMCalculator calculator;
    
    public PWMCalculatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        this.m = new double[4][9];
        
        m[0][0] = 3.0;
        m[1][0] = 2.0;
        m[2][0] = 1.0;
        m[3][0] = 4.0;
        m[0][1] = 6.0;
        m[1][1] = 2.0;
        m[2][1] = 1.0;
        m[3][1] = 1.0;
        m[0][2] = 1.0;
        m[1][2] = 1.0;
        m[2][2] = 7.0;
        m[3][2] = 1.0;
        m[0][3] = 0.0;
        m[1][3] = 0.0;
        m[2][3] = 10.0;
        m[3][3] = 0.0;
        m[0][4] = 0.0;
        m[1][4] = 0.0;
        m[2][4] = 0.0;
        m[3][4] = 10.0;
        m[0][5] = 6.0;
        m[1][5] = 2.0;
        m[2][5] = 1.0;
        m[3][5] = 1.0;
        m[0][6] = 7.0;
        m[1][6] = 1.0;
        m[2][6] = 1.0;
        m[3][6] = 1.0;
        m[0][7] = 2.0;
        m[1][7] = 1.0;
        m[2][7] = 5.0;
        m[3][7] = 2.0;
        m[0][8] = 1.0;
        m[1][8] = 2.0;
        m[2][8] = 1.0;
        m[3][8] = 6.0;
        List<String> nucleotides = new ArrayList<>();
        nucleotides.add("A");
        nucleotides.add("C");
        nucleotides.add("G");
        nucleotides.add("T");
        
        this.matrix = new Matrix(m, true, nucleotides);
        
        this.calculator = new PWMCalculator(this.matrix);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void PPMConversionWorks() {
        double[][] ppm = this.calculator.calculatePPM();
        
        assertEquals(ppm[0][0], 0.3, 0.00001);
    }
    
    @Test
    public void getModelWorks() {
        assertEquals(0.25,this.calculator.getModel(), 0.001);
    }
    
    @Test
    public void PWMConversionWorks() {
        double[][] ppm = this.calculator.calculatePPM();
        Matrix pwm = this.calculator.getPWM(ppm);
        
        assertEquals(0.26,pwm.getCellValue(0, 0),0.01);
    }
}
