/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
public class MatrixReaderTest {
    private MatrixReader reader;
    public MatrixReaderTest() {
    }

    @Before
    public void setUp() {
        this.reader = new MatrixReader("src/main/resources/MA0466.1.pfm", true);
    }

    @Test
    public void readsFileWhenFound() throws FileNotFoundException, IOException {
        this.reader.readTheFile();
    }
    
    @Test
    public void returnsAMatrix() throws FileNotFoundException, IOException {
        this.reader.readTheFile();
        Matrix m = this.reader.createMatrix();
        assertEquals(13006.0, m.getCellValue(0, 0), 0.0001);
    }
    
    @Test
    public void matrixIsFormattedProperly() {
        //TODO
    }
    
    @Test
    public void throwsFileNotFound() throws IOException{
        MatrixReader r1 = new MatrixReader("doesntexist.txt", false);
        boolean thrown = false;
        
        try {
            r1.readTheFile();
        } catch (FileNotFoundException e) {
            thrown = true;
        }
        
        assertEquals(true, thrown);
    }
    
    @Test
    public void getNucleotidesWorksAfterFileIsRead() throws FileNotFoundException, IOException {
        List<String> nucleotides = new ArrayList<>();
        nucleotides.add("A");
        nucleotides.add("C");
        nucleotides.add("G");
        nucleotides.add("T");
        
        this.reader.readTheFile();
        assertEquals(nucleotides, this.reader.getNucleotides());
    }
    
    @Test
    public void getNucleotidesThrowsErrorWhenFileNotRead() {
        boolean thrown = false;
        try {
            reader.getNucleotides();
        } catch (IllegalStateException e) {
            thrown = true;
        }
        
        assertEquals(true, thrown);
    }
    
    @Test
    public void getFrequenciesWorksAfterFileIsRead() throws FileNotFoundException, IOException {
        this.reader.readTheFile();
        assertEquals(13006.0, this.reader.getFrequencies().get(0), 0.00001);

    }
    
    @Test
    public void getFrequenciesThrowsErrorWhenFileNotRead() {
        boolean thrown = false;
        try {
            reader.getFrequencies();
        } catch (IllegalStateException e) {
            thrown = true;
        }
        
        assertEquals(true, thrown);
    }
    
    @Test
    public void findsFormattingErrors() {
        //MatrixReader should notice when the file is not tab-sep
    }
}
