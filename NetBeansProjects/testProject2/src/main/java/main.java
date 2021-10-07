/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ron
 */
public class main {

    public static void main(String[] args) {
        Matrix test = new Matrix(3, 2);
        System.out.println(test.toString());
        test.set(0, 0, 1);
        test.set(0, 1, 0);
        test.set(0, 2, 0);
        test.set(1, 0, 1);
        test.set(1, 1, 1);
        test.set(1, 2, 1);
        test.set(2, 0, 0);
        test.set(2, 1, 0);
        test.set(2, 2, 1);
        System.out.println("pass set");
        System.out.println(test.toString());

        test.calculateInverse();
        System.out.println("pass inverse");
        System.out.println(test);
        System.out.println("start sol");
        int[] a = {1, 0, 0};
        int[] re = test.getSolution(a);
        for (int i = 0; i < re.length; i++) {
            System.out.print(re[i]);
        }
        System.out.println();
        int[] a2 = {1, 1, 1};
        int[] re2 = test.getSolution(a2);
        for (int i = 0; i < re2.length; i++) {
            System.out.print(re2[i]);
        }
        System.out.println();
        int[] a3 = {0, 0, 1};
        int[] re3 = test.getSolution(a3);
        for (int i = 0; i < re3.length; i++) {
            System.out.print(re3[i]);
        }
        System.out.println();
    }

}
