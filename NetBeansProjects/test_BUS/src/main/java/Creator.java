
/*import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;*/
import java.io.*;
import java.util.*;
import java.util.concurrent.CyclicBarrier;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Nearu
 */
public class Creator {

    private BusLine BusA;
    private BusLine BusB;
    private ArrayList<TicketCounter> Ticket;
    private int checkpoint;
    //private ArrayList<FileData> Data;

    public Creator(ArrayList<TicketCounter> tc, BusLine bl, BusLine bl2, int c) {
        BusA = bl;
        BusB = bl2;
        Ticket = tc;
        checkpoint = c;
    }

    public void Create() {
        CyclicBarrier finish = new CyclicBarrier(3);
        for (int i = 0; i < 3; i++) {
            TicketCounter TC = new TicketCounter("T" + Integer.toString(i + 1), BusA, BusB,checkpoint-1);
            ArrayList<FileData> Dt = new ArrayList<FileData>();
            Readdata("T" + Integer.toString(i + 1),Dt);
            Ticket.add(TC);
            Ticket.get(i).setBarrier(finish);
            Ticket.get(i).setdata(Dt);
            
        }

    }

    public  void Readdata(String FN,ArrayList<FileData> Data) {
        String line = "";
        //String Filename = Thread.currentThread().getName() + ".txt";
        String Filename = FN + ".txt";
        Scanner fe = new Scanner(System.in);
        boolean openfile = false;
       while(!openfile){
       try (Scanner input = new Scanner(new File(Filename));) {
           openfile = true;
            while (input.hasNext()) {
                line = input.nextLine();
                String[] buf = line.split(",");
                int transaction = Integer.parseInt(buf[0].trim());
                String name = buf[1].trim();
                int people = Integer.parseInt(buf[2].trim());
                char destination = buf[3].trim().charAt(0);
                
                FileData dt = new FileData(name,transaction,people,destination);
                Data.add(dt);
                
            }
           

        } catch (FileNotFoundException e) {

            System.out.println(e);
            System.out.println("New file name = ");
            Filename = fe.next();
            
        }
       
       }
        
        
    }
    
    public void RUNThread(ArrayList<TicketCounter> tc) {
        for (int i = 0; i < 3; i++) {

            tc.get(i).start();
        }
    }

    public ArrayList<TicketCounter> getTC() {
        return Ticket;
    }
}

