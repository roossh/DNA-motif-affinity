/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

import java.util.List;

/**
 *
 * @author roosa
 */
public class Matrix {
    //matrix will contain information about the 2D-array and calculate some basic things
    //CANNOT contain anything other than numbers => error message if the matrix is formatted wrong
    // also all of the rows have the same number of columns!! (4 rows x N columns)
    // matrix should contain information about which nucleotide is which row
    
    private double[][] matrix;
    private List<String> nucleotides;
    private boolean freqMatrix;
    
    public Matrix(double[][] matrix, boolean freqMatrix, List<String> nucleotides) {
        this.matrix = matrix;
        this.freqMatrix = freqMatrix;
        this.nucleotides = nucleotides;
    }
    
    public double getColSum(int colNumber) {
        double colSum = 0.0;
        
        for (int i = 0; i < matrix.length; i++) {
            colSum += matrix[i][0];
        }
        
        return colSum;
    }
    
    public double getRowSum(int rowNumber) {
        // with for loop
        double rowSum = 0.0;
        
        for (int i = 0; i < matrix[rowNumber].length; i++) {
            rowSum += matrix[rowNumber][i];
        }
        
        return rowSum;
    }
    
    public double getTotalSum() {
        double rowTotals = 0.0;
        
        for (int i = 0; i < matrix.length; i++) {
            rowTotals += getRowSum(i);
        }
        
        return rowTotals;
    }
    
    public double getCellValue(int row, int col) {
        double cellVal = matrix[row][col];
        
        return cellVal;
    }
    
    public int getSequenceLength() {
        return matrix[1].length;
    }
    
    public int getRowCount() {
        return matrix.length;
    }
    
    public boolean isFreqMatrix() {
        return this.freqMatrix;
    }
    
    public List<String> getNucleotides() {
        return this.nucleotides;
    }
    
    public String returnNucleotidesAsString() {
        String returnNucleos = "";
        for (String n : this.nucleotides) {
            returnNucleos += n + ";";
        }
        
        return returnNucleos;
    }
    
    private String returnConsensusSequence() {
        return "";
    }
    
    public String returnConsensusSequenceWithMutation(int mutationPosition, String altAllele) {
        String consensus = returnConsensusSequence();
        consensus = consensus.toLowerCase();
        String mutatedSequence = consensus.substring(0,mutationPosition - 1) + altAllele + consensus.substring(mutationPosition + 1);
        
        return mutatedSequence;


    }
}