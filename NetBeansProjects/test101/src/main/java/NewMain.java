/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import  java.util.*;
/**
 *
 * @author Ron
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    static void myMethod() {
    System.out.println("I just got executed!");
  }
    void x(){
    System.out.println("from Newmain");
    }
    public static void main(String[] args) {
        int[][][] ARY = new int[5][6][4];
        int[] A =  {1,2,3};
        // TODO code application logic here
       /* System.out.println("Hello World");
        myMethod();
        int[] data = {1,2,3,5,7,11,13,17,19,23};
        for(int i : data){
        System.out.println(i);
        }
        Scanner input = new Scanner(System.in);
        int a = input.nextInt();
        System.out.println("Input is " +a);*/
    }
    
}

class b extends NewMain{
    void x(){
    System.out.println("from b");
    }
}