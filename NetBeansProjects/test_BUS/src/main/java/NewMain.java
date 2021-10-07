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
import java.util.concurrent.*;

public class NewMain {

   
    public static void main(String[] args) {
        /*int a = -10; //test Busline
        int b = -a;
        int r;
        System.out.println(a);
        System.out.println(b);
        BusLine test = new BusLine(50);
        r = test.get_remainder();
        test.AllcoateBus(15);
        test.AllcoateBus(20);
        test.AllcoateBus(10);
        test.AllcoateBus(25);
       r = test.get_remainder();
        if (r > 0) {
            r = 0;
            test.add_bus();
        }
        test.printdata();*/
        int num = 50;
        int checkpoint = 5;
        ArrayList<TicketCounter> TC = new ArrayList<TicketCounter>();
        ArrayList<Bus> BUSA = new ArrayList<Bus>();
        ArrayList<Bus> BUSC = new ArrayList<Bus>();

        BusLine BL = new BusLine(num);
        BusLine BL2 = new BusLine(num);

        Creator ct = new Creator(TC, BL, BL2, checkpoint);
        ct.Create();
        ct.RUNThread(TC);

        try {
            for (int i = 0; i < 3; i++) {

                TC.get(i).join();
            }
        } catch (InterruptedException e) {
        }
        
        BUSA = TC.get(0).getBUSA();
        BUSC = TC.get(0).getBUSC();

        System.out.println("");

        int previous = -1;
        System.out.print(Thread.currentThread().getName() + " >> " + " ====== Air Bound ====== ");
        for (int i = 0; i < BUSA.size(); i++) {
            if (BUSA.get(i).get_busnumber() != previous) {
                System.out.println("");
                System.out.print(Thread.currentThread().getName() + " >> " + BUSA.get(i).get_busname() + " ");
                previous = BUSA.get(i).get_busnumber();
            }

            BUSA.get(i).print();

        }

        previous = -1;
        System.out.println("");
        System.out.println("");
        System.out.print(Thread.currentThread().getName() + " >> " + " ====== City Bound ====== ");
        for (int i = 0; i < BUSA.size(); i++) {
            if (BUSC.get(i).get_busnumber() != previous) {
                System.out.println("");
                System.out.print(Thread.currentThread().getName() + " >> " + BUSC.get(i).get_busname() + " ");
                previous = BUSC.get(i).get_busnumber();
            }

            BUSC.get(i).print();

        }

    }

    /*public static void Create(ArrayList<TicketCounter> tc, BusLine bl, BusLine bl2, int c) {
        CyclicBarrier finish = new CyclicBarrier(c - 1);
        for (int i = 0; i < 3; i++) {
            TicketCounter TC = new TicketCounter("T" + Integer.toString(i + 1), bl, bl2);
            tc.add(TC);
            tc.get(i).setBarrier(finish);
        }
    }

    public static void RUNThread(ArrayList<TicketCounter> tc) {
        for (int i = 0; i < 3; i++) {

            tc.get(i).start();
        }

    }*/
}
