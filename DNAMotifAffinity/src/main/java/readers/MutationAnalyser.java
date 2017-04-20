
package readers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import logic.ScoreCalculator;
import matrix.Matrix;

public class MutationAnalyser {
    
    private final Matrix matrix;
    private ScoreCalculator scoreCalculator;
    
    public MutationAnalyser(Matrix matrix) {
        this.matrix = matrix;
    }
    
    public void run(String vcfLocation, String bedLocation, String outputName) throws FileNotFoundException, IOException {
        PrintWriter writer = new PrintWriter(outputName, "UTF-8");
        BufferedReader bedReader = new BufferedReader(new FileReader(bedLocation));
        String bedLine, vcfLine;
        while ((bedLine = bedReader.readLine()) != null) {
            BufferedReader vcfReader = new BufferedReader(new FileReader(vcfLocation));
            String bedChr = bedLine.split("\t")[0].replaceAll("chr", "");
            int bedStart = Integer.parseInt(bedLine.split("\t")[1]);
            int bedStop = Integer.parseInt(bedLine.split("\t")[2]);
                    
            while ((vcfLine = vcfReader.readLine()) != null) {
                if (vcfLine.startsWith("#")) {
                    continue;
                }
                        
                String vcfChr = vcfLine.split("\t")[0];
                int vcfPosition = Integer.parseInt(vcfLine.split("\t")[2]);
                        
                if (vcfChr.equals(bedChr) && vcfPosition >= bedStart && vcfPosition <= bedStop) {
                            //score mutation
                    int motifPosition = defineMotifPosition(bedStart, vcfPosition);
                    String refAllele = vcfLine.split("\t")[3];
                    String altAllele = vcfLine.split("\t")[4];
                    double affinityScore = defineMutationScore(motifPosition, altAllele);
                    writer.println(vcfChr + "\t" + vcfPosition + "\t" + refAllele + "\t" + altAllele + "\t" + (motifPosition + 1) + "\t" + affinityScore);
                }
            }
            
            vcfReader.close();
        }
        
        bedReader.close();
        writer.close();
    }
    public int defineMotifPosition(int bedStart, int vcfPosition) {
        return vcfPosition - bedStart;
    }
    
    public double defineMutationScore(int motifPosition, String altAllele) {
        if (motifPosition > matrix.getSequenceLength()) {
            return 0.0;
        } else {
            List<String> nucleotides = matrix.getNucleotides();
            int rowNumber = nucleotides.indexOf(altAllele);
            return matrix.getCellValue(rowNumber, motifPosition);
        }
    }
}
