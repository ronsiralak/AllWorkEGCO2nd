
import java.io.*;
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Nearu
 */
public class FileData {
    private int transaction;
    private String name;
    private int people;
    private char destination;
    public FileData(String n,int t,int p ,char d) {
        name = n;
        transaction = t;
        people = p;
        destination = d;
        
    }

    public int get_transaction(){
    return transaction;
    }
    public String get_name(){
    return name;
    }
    public int get_people(){
    return people;
    }
    public char get_destinaiton(){
    return destination;
    }
}
