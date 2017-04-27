package statistics;

import java.util.ArrayList;
import matrix.Matrix;

/**TODO
 * Laskee statistiikkaa mutaatioista.
 * Tekee permutaatiotestin, jonka perusteella voidaan määrittää mutaatiopositioille p-arvoja.
 * @author roosa
 */
public class Permutation {
    
    private int rounds;
    private String sequence;
    private Matrix pwm;
    private ArrayList<Double> nullDistribution;
    /**Luokka toteuttaa permutaatiotestin mutaatiolle.
     * Toistaiseksi kesken.
     */
    public Permutation() {
        
    }
    /**permutoi mutaatioita.
     * 
     * @param rounds permutaatioiden lukumäärä
     * @param sequence sekvenssi, johon permutaatio kohdistuu
     * @param pwm matriisi, jonka pohjalta lasketaan score
     */
    public void permuteMutation(int rounds, String sequence, Matrix pwm) {
        this.rounds = rounds;
        this.sequence = sequence;
        this.pwm = pwm;
        
        performRandomisation(rounds);
    }
    
    private void performRandomisation(int rounds) {
        for (int i = 0; i <= rounds; i++) {
            i++;
        }
    }
    /**Palauttaa lasketun p-arvon.
     * 
     * @return 0.0 kunnes permutaatiotesti kunnossa
     */
    public double returnPValue() {
        return 0.0;
    }
}
