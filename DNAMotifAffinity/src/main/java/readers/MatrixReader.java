package readers;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import matrix.Matrix;

/**
 * Luokka muokkaa sille syötetyn JASPAR-matriisin ohjelman käytettävään muotoon.
 * @author roosa
 */
public class MatrixReader {
    
    private BufferedReader reader;
    private final String fileName;
    private final boolean freqMatrix;
    private Matrix m;
    private double[][] arrayBase;
    private List<Double> frequencies;
    private List<String> nucleotides;
    /**Konstruoi MatrixReaderin.
     * 
     * @param fileName tiedostinimi, joka luetaan
     */
    public MatrixReader(String fileName) {
        this.fileName = fileName;
        this.freqMatrix = true;
    }
    /**
     * Metodi lukee sille syötetyn tiedoston.
     * @throws FileNotFoundException heittää poikkeuksen, jos tiedostoa ei löydy
     * @throws IOException heittää IOExceptionin, jos tarvetta
     */
    public void readTheFile() throws FileNotFoundException, IOException {
        //reads the file and converts it into a matrix
        this.reader = new BufferedReader(new FileReader(fileName));
        
        try {
            StringBuilder builder = new StringBuilder();
            String line;
            int rowCount = 0;
            this.nucleotides = new ArrayList<>();
            this.frequencies = new ArrayList<>();
            while ((line  = reader.readLine()) != null) {
                if (line.startsWith(">")) {
                    continue;
                } else {
                    String nucleotide = Character.toString(line.charAt(0));
                    nucleotides.add(nucleotide);
                    
                    String nucleotideCounts = line.split("\\[")[1].split("\\]")[0];
                    
                    String[] nucleotideList = nucleotideCounts.split("\t");
                    
                    for (String n : nucleotideList) {
                        Double n1 = Double.parseDouble(n.trim());
                        frequencies.add(n1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException ex) {
            throw new IOException();
        }
    }
    /**
     * luo uuden instanssin luokasta Matrix.
     * @return palauttaa uuden matriisin
     */
    public Matrix createMatrix() {
        int seqLength = this.frequencies.size() / this.nucleotides.size();
        this.arrayBase = new double[nucleotides.size()][seqLength];
        int base = 0;
        for (int row = 0; row < nucleotides.size(); row++) {
            for (int col = 0; col < seqLength; col++) {
                this.arrayBase[row][col] = this.frequencies.get(base);
                //System.out.println(this.frequencies.get(base));
                base += 1;
            }
        }
        
        this.m = new Matrix(arrayBase, true, nucleotides);
        return this.m;
    }
    /**Palauttaa nukleotidien frekvenssit.
     * 
     * @return palauttaa listan, joka sisältää frekvenssit
     */
    public List<Double> getFrequencies() {
        if (this.frequencies == null) {
            throw new IllegalStateException();
        } else {
            return this.frequencies;
        }
    }
    /**Palauttaa listan nukleotideista.
     * 
     * @return lista, johon luokiteltuna kaikki uniikit nukleotidit
     */
    public List<String> getNucleotides() {
        if (this.nucleotides == null) {
            throw new IllegalStateException();
        } else {
            return this.nucleotides;
        }
    }
    /**Sulkee lukijan.
     * 
     * @throws IOException heittää exceptionin, jos tarvis 
     */
    public void closeReader() throws IOException {
        this.reader.close();
    }
}
