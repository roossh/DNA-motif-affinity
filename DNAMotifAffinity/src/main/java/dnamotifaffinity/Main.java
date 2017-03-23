package dnamotifaffinity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import matrix.Matrix;
import java.lang.Math;

public class Main {
    //Main is now used for testing stuff, in the end it will be used for starting the program only.
    public static void main(String[] args) throws FileNotFoundException, IOException {
        System.out.println(Math.log(0.3));
        System.out.println(Math.log(0.25));
        double value = 0.3/0.25;
        System.out.println(value);
        System.out.println(Math.log(value)/Math.log(2));
    }    
}