/*Siralak    Teekha           6213133
  Weerawich  Wongchatchalikun 6213166*/

import java.util.*;
import java.io.*;

/**
 *
 * @author Ron
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String s;
        float x,vat, money;
        try {
            Scanner input = new Scanner(new File("prices.txt"));
            FileWriter w = new FileWriter("output.txt");
            w.write("product\t\tPrice before VAT\tVAT\t\tNet price\r\n");
            w.write("===================================================================\r\n");
            
            while (input.hasNext()) {
                s = input.next();
                x = input.nextFloat();
                money = x;
                vat = x;
                money = money * 100 / 107;
                vat = vat - money;
                String use = String.format("%-10s\t%10.2f%18.2f%20.2f\r\n",s,money,vat,x);
                System.out.printf(use);
                w.write(use);
                
            }
            w.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
