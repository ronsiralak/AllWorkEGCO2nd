
/*Siralak    Teekha                 6213133
  Weerawich  Wongchatchalikun       6213166
  Korawit    Wisetsuwan 	    6213192*/
import java.io.*;
import java.util.*;

public class FileData {

    private int transaction;
    private String name;
    private int people;
    private char destination;

    public FileData(String n, int t, int p, char d) {
        name = n;
        transaction = t;
        people = p;
        destination = d;

    }

    public int get_transaction() {
        return transaction;
    }

    public String get_name() {
        return name;
    }

    public int get_people() {
        return people;
    }

    public char get_destinaiton() {
        return destination;
    }
}
