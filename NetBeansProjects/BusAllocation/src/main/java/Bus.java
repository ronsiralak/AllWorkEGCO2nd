
/*Siralak    Teekha                 6213133
  Weerawich  Wongchatchalikun       6213166
  Korawit    Wisetsuwan 	    6213192*/
public class Bus {

    private String Name = "", Bus_name = "";
    private int Number = 0;
    private int busnumber = 0;
    private char destination;

    public Bus(int n, String name, String busName) {
        Number = n;
        Name = name;
        Bus_name = busName;
    }

    public void set_data(int n, String name, char c, int bn) {
        Number = n;
        Name = name;
        destination = c;
        busnumber = bn;
        Bus_name = destination + Integer.toString(busnumber);

    }

    public void print() {
        System.out.printf("%-18s %2s(seats)", Name, Number);
    }

    public int get_busnumber() {
        return busnumber;
    }

    public String get_busname() {
        return Bus_name;
    }
}
