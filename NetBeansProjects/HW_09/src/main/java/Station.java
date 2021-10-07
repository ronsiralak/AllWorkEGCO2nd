/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nearu
 */
public class Station {
     private String name;
    private double fare;
    public Station(String n, double c)	{ name = n; fare = c; }
    public String getName()			    { return name; }
    public double getfare()			{ return fare; }
    public String getMessage()
    {
	String s = String.format("%-9s (%.2f $)", name, fare);
	return s;
    }
    public void print()
    {
	System.out.println( getMessage() );
    }
    
    /*
    // equality based on name
    public boolean equals(Object o)
    {
	Country other = (Country)o;
	return this.name.equalsIgnoreCase(other.name);
    }
    
    // hashcode based on the hashcode of name
    public int hashCode()
    {
	return name.toLowerCase().hashCode();
    }
    */
}
