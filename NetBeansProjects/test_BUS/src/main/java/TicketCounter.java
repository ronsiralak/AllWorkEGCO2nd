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
import java.io.FileWriter;   // Import the FileWriter class

public class TicketCounter extends Thread {

    private BusLine buslineA, buslineC;//airport-bound , city-bound.
    private CyclicBarrier cfinish;
    private String previous = "";
    private Scanner file;
    private ArrayList<FileData> DATA = new ArrayList<FileData>();
    private ArrayList<Bus> BUSA = new ArrayList<Bus>();
    private ArrayList<Bus> BUSC = new ArrayList<Bus>();
    private int check;

    public TicketCounter(String n, BusLine bl, BusLine bl2,int c) {
        super(n);
        buslineA = bl;
        buslineC = bl2;
        buslineA.set_destination('A');
        buslineC.set_destination('C');
        check = c;
    }

    public void setBarrier(CyclicBarrier c) {
        cfinish = c;

    }

    public void run() {
        int ch = -1;
        

        //System.out.print("hellp");
        //System.out.print(Filename + ">>" + DATA.size());
        for (int i = 0; i < DATA.size(); i++) {
            if (DATA.get(i).get_destinaiton() == 'A') {
                   // System.out.print("case 1");
                buslineA.AllcoateBus(DATA.get(i).get_people(), DATA.get(i).get_transaction(), DATA.get(i).get_name());
            } else if (DATA.get(i).get_destinaiton() == 'C') {
                //System.out.print("case 2");
                buslineC.AllcoateBus(DATA.get(i).get_people(), DATA.get(i).get_transaction(), DATA.get(i).get_name());
            }

           if (DATA.get(i).get_transaction() == check) {
                try {

                    ch = cfinish.await();
                    //cfinish.reset();
                    //System.out.println("stop");
                    if (ch == 0) {
                        getBUSA();
                        getBUSC();
                        //BUSA.get(BUSA.size()-1).get_busnumber();
                        //System.out.println();
                        System.out.printf("\n%s >> %d airport-bound buses have been allocated \n", Thread.currentThread().getName(), (BUSA.get(BUSA.size() - 1).get_busnumber() + 1));
                        System.out.printf("%s >> %d city-bound buses have been allocated\n\n", Thread.currentThread().getName(), (BUSC.get(BUSC.size() - 1).get_busnumber() + 1));
                        //System.out.println(Thread.currentThread().getName() + " >> " + (BUSA.get(BUSA.size()-1).get_busnumber() +1) + "  "+"airport-bound buses have been allocated");
                        // System.out.println(Thread.currentThread().getName() + " >> " + (BUSC.get(BUSC.size()-1).get_busnumber() +1) + "  "+"city-bound buses have been allocated");
                        // BUSA.clear();
                        // BUSC.clear();
                    }

                    cfinish.await();

                } catch (InterruptedException | BrokenBarrierException e) {
                    System.out.println(e);

                }

            }

        }
        /*try (Scanner input = new Scanner(new File(Filename));) {
            //System.out.println(Thread.currentThread().getName());

            //openfile = true;
            //Scanner input = new Scanner(new File(Filename));
            //myWriter.write("test");
            while (input.hasNext()) {
                line = input.nextLine();
                String[] buf = line.split(",");
                int transaction = Integer.parseInt(buf[0].trim());
                String name = buf[1].trim();
                int people = Integer.parseInt(buf[2].trim());
                char destination = buf[3].trim().charAt(0);

                //System.out.println(name + " " + people + " " + destination);
                if (destination == 'A') {
                    //System.out.println("go A");                
                    buslineA.AllcoateBus(people, transaction, name);
                    //BUSA.addAll(BUSa);

                } else if (destination == 'C') {
                    //System.out.println("go C");
                    buslineC.AllcoateBus(people, transaction, name);
                    //BUSc = buslineC.AllcoateBus(people, transaction, name);
                    // BUSC.addAll(BUSc);
                }

            }
        } catch (FileNotFoundException e) {
            // System.out.println("input line >> " + line);
            //cfinish.await();
            System.out.println(e);
            // Filename = getFilename();

        }*/

    
    }

    public ArrayList<Bus> getBUSA() {
        BUSA = buslineA.get_bus();
        return BUSA;
    }

    public ArrayList<Bus> getBUSC() {
        BUSC = buslineC.get_bus();
        return BUSC;
    }

    public void setdata(ArrayList<FileData> dt) {
        DATA = dt;
    }

    public String getFilename() {
        System.out.println("New file name = ");
        String filename = file.next();
        return filename;
    }

}
