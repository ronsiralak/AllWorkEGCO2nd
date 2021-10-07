/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ron
 */
import java.io.*;
import java.util.*;
import java.lang.Math;

public class BruForce {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int n = 5, len;
        double max;
        String temp;
        Board test = new Board(n);
        //test.show();
        ArrayList<data> allcase = new ArrayList<data>();
        //allcase.add(test.findall());
        //createmap(n);
       // System.out.println(Math.pow(2, 32));
        max = Math.pow(2, n * n);
        len = n * n;
        for (int i = 0; i < max; i++) {
            temp = Integer.toBinaryString(i);
            while (temp.length() < len) {
                temp = "0" + temp;
            }
            System.out.println(i+" -> "+temp);
            //char ch1 = temp.charAt(0);
        }

    }

    public static void createmap(int start, int end) {

    }
}
