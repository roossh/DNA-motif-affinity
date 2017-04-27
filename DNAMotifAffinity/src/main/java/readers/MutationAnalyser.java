/**
 * Luokka analysoi sille syötetyt tiedostot ja kirjoittaa uuden tiedoston, joka sisältää scoratut mutaation
 */
package readers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import matrix.Matrix;

public class MutationAnalyser {
    
    private final Matrix matrix;
    
    /**Analysoi mutaatioita.
     * 
     * @param matrix Annetaan syötteenä matriisi, jonka pohjalta mutaatiot scoretetaan
     */
    public MutationAnalyser(Matrix matrix) {
        this.matrix = matrix;
    }
    /**
     * Metodi hakee BED-tiedoston määrittelemälle alueelle osuvat mutaatiot ja hakee niille motiiviscoren.
     * @param vcfLocation VCF-tiedoston sijainti.
     * @param bedLocation BED-tiedoston sijainti.
     * @param outputName Tulostettavan tiedoston nimi.
     * @throws FileNotFoundException heittää FileNotFoundin, jos tiedosto puuttuu
     * @throws IOException heittää IOExceptionin, kun on tarvis...
     */
    public void run(String vcfLocation, String bedLocation, String outputName) throws FileNotFoundException, IOException {
        PrintWriter writer = new PrintWriter(outputName, "UTF-8");
        writer.println("#CHROM\tPOS\tREF\tALT\tMOTIFPOS\tMOTIFSEQUENCE\tMOTIFSCORE");
        BufferedReader bedReader = new BufferedReader(new FileReader(bedLocation));
        String bedLine, vcfLine;
        while ((bedLine = bedReader.readLine()) != null) {
            BufferedReader vcfReader = new BufferedReader(new FileReader(vcfLocation));
            String bedChr = bedLine.split("\t")[0];
            int bedStart = Integer.parseInt(bedLine.split("\t")[1]);
            int bedStop = Integer.parseInt(bedLine.split("\t")[2]);
                    
            while ((vcfLine = vcfReader.readLine()) != null) {
                if (vcfLine.startsWith("#")) {
                    continue;
                }
                        
                String vcfChr = vcfLine.split("\t")[0];
                int vcfPosition = Integer.parseInt(vcfLine.split("\t")[2]);
                        
                if (vcfChr.equals(bedChr) && vcfPosition >= bedStart && vcfPosition <= bedStop) {
                    int motifPosition = defineMotifPosition(bedStart, vcfPosition);
                    String refAllele = vcfLine.split("\t")[3];
                    String altAllele = vcfLine.split("\t")[4];
                    double affinityScore = defineMutationScore(motifPosition, altAllele);
                    writer.println(vcfChr + "\t" + vcfPosition + "\t" +
                            refAllele + "\t" + altAllele + "\t" +
                            (motifPosition + 1) + "\t" + affinityScore);
                }
            }
            
            vcfReader.close();
        }
        
        bedReader.close();
        writer.close();
    }
    
   /**
    * 
    * @param bedStart BED-tiedoston aloituspositio TF-alueelle
    * @param vcfPosition VCF-tiedostossa määritelty positio mutaatiolle
    * @return mutaation positio motiivin sisällä
    */
    private int defineMotifPosition(int bedStart, int vcfPosition) {
        return vcfPosition - bedStart;
    }
    /**
     * 
     * @param motifPosition positio motiivin sisällä
     * @param altAllele vaihtoehtoinen (lue: mutatoitunut) alleeli
     * @return palauttaa kyseiselle mutaatiolle scoren (lasketaan matriisin perusteella)
     */
    private double defineMutationScore(int motifPosition, String altAllele) {
        if (motifPosition > matrix.getSequenceLength()) {
            return 0.0;
        } else {
            List<String> nucleotides = matrix.getNucleotides();
            int rowNumber = nucleotides.indexOf(altAllele);
            return matrix.getCellValue(rowNumber, motifPosition);
        }
    }
}
