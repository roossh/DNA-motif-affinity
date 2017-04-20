package readers;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import matrix.Matrix;

// this superclass will define the basic functions of file reading
// error messages to be added
// matrix readers should be used to read the file and convert it into java object matrix

public class MatrixReader{
    
    private BufferedReader reader;
    private final String fileName;
    private final boolean freqMatrix;
    private Matrix m;
    private double[][] arrayBase;
    private List<Double> frequencies;
    private List<String> nucleotides;
    
    public MatrixReader(String fileName) {
        this.fileName = fileName;
        this.freqMatrix = true;
    }
    
    public void readTheFile() throws FileNotFoundException, IOException {
        //reads the file and converts it into a matrix
        this.reader = new BufferedReader(new FileReader(fileName));
        
        try {
            StringBuilder builder = new StringBuilder();
            String line;
            int rowCount = 0; //counts only the rows with actual nucleotide freqs
            this.nucleotides = new ArrayList<>();
            this.frequencies = new ArrayList<>();
            while ((line  = reader.readLine()) != null) {
                //reads the file
                //should check if the file is not in proper format

                if (line.startsWith(">")) {
                    //the line is an identifier, this is ignored
                    continue;
                } else {
                    String nucleotide = Character.toString(line.charAt(0));
                    //char nucleotide = line.charAt(0);
                    //System.out.println(nucleotide);
                    nucleotides.add(nucleotide);
                    
                    String nucleotideCounts = line.split("\\[")[1].split("\\]")[0];
                    
                    String[] nucleotideList = nucleotideCounts.split("\t");
                    
                    
                    //converts the read matrix into a proper matrix for later calculations
                    for (String n : nucleotideList) { //reads row by row
                        Double n1 = Double.parseDouble(n.trim());
                        frequencies.add(n1);
                    }

             
                    //now only assumes the nucleotides to be in this specific order. will be fixed later
                    //String[] nucleotides = new String[]{"A","C","G","T"};
                    //this.m = new Matrix(matrix, true, nucleotides);
                }
                //System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException ex) {
            throw new IOException();
        }
    }
    
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
    
    public List<Double> getFrequencies() {
        if (this.frequencies == null) {
            throw new IllegalStateException();
        } else {
            return this.frequencies;
        }
    }
    
    public List<String> getNucleotides() {
        if (this.nucleotides == null) {
            throw new IllegalStateException();
        } else {
            return this.nucleotides;
        }
    }
    
    public void closeReader() throws IOException {
        this.reader.close();
    }
}
