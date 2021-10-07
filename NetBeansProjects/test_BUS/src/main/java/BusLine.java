
import java.io.FileWriter;
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ron
 */
public class BusLine {

    private char Destination;
    private int max_seat, bus_number = -1, space = 0, use, sent;
    private String previous = "", Name = "";
    private  ArrayList<Bus> BUS = new ArrayList<Bus>();
    public BusLine(int m) {
        this.use = 0;
        max_seat = m;
        space = max_seat;
    }

    public void set_destination(char d) {
        Destination = d;
    }

    synchronized public void AllcoateBus(int n, int t, String name) {
        String BN = "";
       // ArrayList<Bus> BUS = new ArrayList<Bus>();
        Bus b1 = new Bus(n, name, BN);
        Bus b2 = new Bus(n, name, BN);
        Bus b3 = new Bus(n, name, BN);
        try {
            if (n + use > max_seat) {
                n = n + use - max_seat;
                if (use == 0) {
                    bus_number++;
                }
                //System.out.println("case A  " + n + "  " + use + " " + max_seat);
                if (space != 0) {
                    System.out.printf("%s >> Transaction %d : %-20s (%2d seats) bus %s%d\n", Thread.currentThread().getName(), t, name, space, Destination, bus_number);
                    sent = space;
                    BN = Destination + Integer.toString(bus_number);
                    b1.set_data(sent, name,Destination,bus_number);
                    BUS.add(b1);
                }
                use = 0;
                while (n >= max_seat) {
                    n = n - max_seat;
                    bus_number++;
                    // System.out.print("in while ");
                    //System.out.println("case B");
                    System.out.printf("%s >> Transaction %d : %-20s (%2d seats) bus %s%d\n", Thread.currentThread().getName(), t, name, max_seat, Destination, bus_number);
                    sent = max_seat;
                    BN = Destination + Integer.toString(bus_number);
                    b2.set_data(sent, name,Destination,bus_number);
                    BUS.add(b2);
                }

            }
            if (n > 0) {
                if (use == 0) {
                    bus_number++;
                }
                use = n + use;
                space = max_seat - use;
                //System.out.print("space " + space + "  ");
                //System.out.print("use "+use+"  ");

                //System.out.println("case C");
                System.out.printf("%s >> Transaction %d : %-20s (%2d seats) bus %s%d\n", Thread.currentThread().getName(), t, name, n, Destination, bus_number);
                sent = n;
                BN = Destination + Integer.toString(bus_number);
                b3.set_data(sent, name,Destination,bus_number);
                BUS.add(b3);
            }

        } catch (Exception e) {
        }

        /*for (int i = 0; i < BUS.size(); i++) {
            BUS.get(i).print();
        }*/

   
    }

    public void printdata() {
        System.out.printf(" (%2d seats) bus %s%d\n", use, Destination, bus_number);
    }

    public int get_bus_number() {
        return bus_number;
    }
    public ArrayList<Bus> get_bus(){
    return BUS;
    
    }
}
