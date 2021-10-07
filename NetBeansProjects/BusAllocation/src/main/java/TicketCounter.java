
/*Siralak    Teekha                 6213133
  Weerawich  Wongchatchalikun       6213166
  Korawit    Wisetsuwan 	    6213192*/
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

    public TicketCounter(String n, BusLine bl, BusLine bl2, int c) {
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
        for (int i = 0; i < DATA.size(); i++) {
            if (DATA.get(i).get_destinaiton() == 'A') {

                buslineA.AllcoateBus(DATA.get(i).get_people(), DATA.get(i).get_transaction(), DATA.get(i).get_name());
            } else if (DATA.get(i).get_destinaiton() == 'C') {

                buslineC.AllcoateBus(DATA.get(i).get_people(), DATA.get(i).get_transaction(), DATA.get(i).get_name());
            }

            if (DATA.get(i).get_transaction() == check) {
                try {

                    ch = cfinish.await();

                    if (ch == 0) {
                        getBUSA();
                        getBUSC();

                        System.out.printf("\n%s >> %d airport-bound buses have been allocated \n", Thread.currentThread().getName(), (BUSA.get(BUSA.size() - 1).get_busnumber() + 1));
                        System.out.printf("%s >> %d city-bound buses have been allocated\n\n", Thread.currentThread().getName(), (BUSC.get(BUSC.size() - 1).get_busnumber() + 1));

                    }

                    cfinish.await();

                } catch (InterruptedException | BrokenBarrierException e) {
                    System.out.println(e);

                }

            }

        }

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
