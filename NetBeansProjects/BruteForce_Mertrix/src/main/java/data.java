/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ron
 */
public class data {
    private int[][] metrix;
    public data(int size,int[] value) {

        System.out.println("initialize");
        metrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                metrix[i][j] = value[ (size*i )+ j ];
            }
        }
    }
}
