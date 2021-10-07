
/*Siralak    Teekha                 6213133
  Weerawich  Wongchatchalikun       6213166
  Korawit    Wisetsuwan 	    6213192*/
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Simulator {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println(Thread.currentThread().getName() + "  >>  enter max seats ");
        int num = input.nextInt();
        System.out.println(Thread.currentThread().getName() + "  >>  enter checkpoint ");
        int checkpoint = input.nextInt();
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
                System.out.printf("%s  >> %-4s", Thread.currentThread().getName(), BUSA.get(i).get_busname());
                BUSA.get(i).print();
                previous = BUSA.get(i).get_busnumber();
            } else {
                System.out.print(",\t");
                BUSA.get(i).print();
            }
        }

        previous = -1;
        System.out.println("");
        System.out.println("");
        System.out.print(Thread.currentThread().getName() + " >> " + " ====== City Bound ====== ");
        for (int i = 0; i < BUSC.size(); i++) {
            if (BUSC.get(i).get_busnumber() != previous) {
                System.out.println("");
                System.out.printf("%s  >> %-4s", Thread.currentThread().getName(), BUSC.get(i).get_busname());
                BUSC.get(i).print();
                previous = BUSC.get(i).get_busnumber();
            } else {
                System.out.print(",  ");
                BUSC.get(i).print();
            }
        }

    }

}
