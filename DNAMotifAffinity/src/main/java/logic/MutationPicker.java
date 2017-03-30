package logic;

import matrix.Matrix;

public class MutationPicker {
    private final ScoreCalculator scoreCalc;
    
    public MutationPicker(Matrix pwm) {
        //this class is actively used to compare VCF and BED
        this.scoreCalc = new ScoreCalculator(pwm);
    }
    
    public boolean mutationInRegion(String vcf, String bed) {
        //analyse the file in a somewhat sensible way
        //this if -clause tests whether the position in vcf (mutation line) is within a region defined in BED.
        String mutatedChrom = vcf.split("\t")[0];
        String bedChrom = bed.split("\t")[0];
        int position = Integer.parseInt(vcf.split("\t")[2]);
        //System.out.println(position);
        int start = Integer.parseInt(bed.split("\t")[1]);
        int end = Integer.parseInt(bed.split("\t")[2]);
        return ((mutatedChrom.equalsIgnoreCase(bedChrom)) && (position >= start) && (position <= end));
    }
    
    public double annotateMutation(String vcf, String bed) {
        if (mutationInRegion(vcf, bed)) {
            //means that mutation is within region => gets a score, otherwise gets a zero
            String mutation = vcf.split("\t")[4];
            int mutatedBase = Integer.parseInt(vcf.split("\t")[2]);
            int startPosition = Integer.parseInt(bed.split("\t")[1]);
            int position = mutatedBase - startPosition;
            double score = scoreCalc.giveAffinityScore(mutation, position);
            
            return score;
        } else {
            return 0.0;
        }
    }
}
