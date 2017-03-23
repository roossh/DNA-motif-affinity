package matrix;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import matrix.Matrix;

/**
 *
 * @author roosa
 */
public class MatrixTest {
    
    private Matrix matrix;
    
    public MatrixTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        //create a simple matrix:
       // based on the one found on Wikipedia
        double[][] m = new double[4][9];
        
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
        
        this.matrix = new Matrix(m, false);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void getRowSumWorks() {
        assertEquals(26, matrix.getRowSum(0), 0.00001);
    }
    
    @Test
    public void getColSumWorks() {
        assertEquals(10, matrix.getColSum(0), 0.00001);
    }
    
    @Test
    public void getTotalSumWorks() {
        assertEquals(90, matrix.getTotalSum(), 0.00001);
    }
    
    @Test
    public void getCellValueWorks() {
        assertEquals(3, matrix.getCellValue(0, 0), 0.00001);
    }
    
    @Test
    public void getSeqLengthWorks() {
        assertEquals(9, matrix.getSequenceLength(), 0.00001);
    }
    
    @Test
    public void getRowCountWorks() {
        assertEquals(4, matrix.getRowCount(), 0.00001);
    }
    
    @Test
    public void getFreqMatrixWorksIfFalse() {
        assertEquals(false, matrix.isFreqMatrix());
    }
    
    @Test
    public void getFreqMatrixWorksIfTrue() {
        Matrix m1 = new Matrix(new double[4][4], true);
        assertEquals(true, m1.isFreqMatrix());
    }
}
