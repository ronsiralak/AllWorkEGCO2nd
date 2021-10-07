/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ron
 */
public class Bus  {

    private String Name = "", Bus_name = "";
    private int Number=0;
    private int busnumber = 0;
    private char destination;

    public Bus(int n, String name, String busName) {
        Number = n;
        Name = name;
        Bus_name = busName;
    }

   /* public void set_data(int n, String name, String busName,int bn) {
        Number = n;
        Name = name;
        Bus_name = busName;
        
    }*/
    public void set_data(int n, String name,char c ,int bn) {
        Number = n;
        Name = name;
       destination = c;
       busnumber = bn;
       Bus_name = destination + Integer.toString(busnumber);
        
    }
    public void print() {
        System.out.printf("%-20s %-3s ",Name,Number);
       //System.out.print(destination + Integer.toString(busnumber) + "  " + Name + "  " + Number + "  ");
    }

   
  
    public  int get_busnumber(){
    return busnumber;
    }
    public String get_busname(){
    return Bus_name;
    }
}
