/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

/**
 *
 * @author roosa
 */
public class Matrix {
    //matrix will contain information about the 2D-array and calculate some basic things
    //CANNOT contain anything other than numbers => error message if the matrix is formatted wrong
    // also all of the rows have the same number of columns!! (4 rows x N columns)
    
    private double[][] matrix;
    private boolean freqMatrix;
    
    public Matrix(double[][] matrix, boolean freqMatrix) {
        //constructor
        this.matrix = matrix;
        this.freqMatrix = freqMatrix;
    }
    
    public void printMatrix() {
        // this prints the matrix for the user
        // needed more for the Weight Matrix than anything else :)
        
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col]+"\t");
            }   
                System.out.println();
        }
        
    }
    
    public double getColSum(int colNumber) {
        // with for-loop
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
        // fix this mess
        // returns the sum of all rows, this functions as the overall total
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
        //all rows have the same amount of columns...(which is the standard)
        return matrix[1].length;
    }
    
    public int getRowCount() {
        return matrix.length;
    }
    
    public boolean isFreqMatrix() {
        return this.freqMatrix;
    }
}
