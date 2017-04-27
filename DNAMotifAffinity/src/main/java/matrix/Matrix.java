/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

import java.util.List;

/**Matriisi-luokka laskee double[][]-arraystä arvoja ja tallentaa tietoa nukleotidikohtaisesti.
 *
 * @author roosa
 */
public class Matrix {

    private double[][] matrix;
    private List<String> nucleotides;
    private boolean freqMatrix;
    
    /**Matriisi sisältää N riviä, ja jokaisen elementin frekvenssin/todennäköisyyden nukleotidia kohden.
     * 
     * @param matrix käytetään double[][]-arraytä matriisin pohjana
     * @param freqMatrix boolean, joka kertoo onko kyseessä frekvenssimatriisi vai ei
     * @param nucleotides listaus nukleotideista, jotka määritellään matriisissa.
     */
    public Matrix(double[][] matrix, boolean freqMatrix, List<String> nucleotides) {
        this.matrix = matrix;
        this.freqMatrix = freqMatrix;
        this.nucleotides = nucleotides;
    }
    /**Metodi laskee sarakesumman halutulle sarakkeelle.
     * 
     * @param colNumber sarakenumero, josta halutaan laskea matriisin lukujen summa
     * @return colSum sarakkeen summa
     */
    public double getColSum(int colNumber) {
        double colSum = 0.0;
        
        for (int i = 0; i < matrix.length; i++) {
            colSum += matrix[i][0];
        }
        
        return colSum;
    }
    /**Metodi laskee rivisumman halutulle riville.
     * 
     * @param rowNumber rivinumero, josta halutaan laskea summa
     * @return rowSum rivin summa
     */
    public double getRowSum(int rowNumber) {
        // with for loop
        double rowSum = 0.0;
        
        for (int i = 0; i < matrix[rowNumber].length; i++) {
            rowSum += matrix[rowNumber][i];
        }
        
        return rowSum;
    }
    /**
     * Laskee kaikkien lukujen yhteisen summan matriisissa.
     * @return rowTotals rivisummien summa
     */
    public double getTotalSum() {
        double rowTotals = 0.0;
        
        for (int i = 0; i < matrix.length; i++) {
            rowTotals += getRowSum(i);
        }
        
        return rowTotals;
    }
    /**
     * Palauttaa yksittäisen solun arvon.
     * @param row määritetty rivi, josta halutaan arvo
     * @param col määritetty sarake, josta halutaan arvo
     * @return cellValue tietyn solun sisältämä arvo
     */
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
    /**TODO
     * Metodi määrittelee nukleotidit, jotka esiintyvät matriisissa ja palauttaa nämä merkkijonona.
     * @return palauttaa nukleotidit merkkijonona
     */
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
    /**
     * Metodi kirjoittaa motiivin sekvenssin merkkijonomuodossa.
     * @param mutationPosition positio, jossa on mutaatio
     * @param altAllele vaihtoehtoinen alleeli
     * @return palauttaa mutatoitudun sekvenssin merkkijonona
     */
    public String returnConsensusSequenceWithMutation(int mutationPosition, String altAllele) {
        String consensus = returnConsensusSequence();
        consensus = consensus.toLowerCase();
        String mutatedSequence = consensus.substring(0, mutationPosition - 1) + altAllele + consensus.substring(mutationPosition + 1);
        
        return mutatedSequence;


    }
}