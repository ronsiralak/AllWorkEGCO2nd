
/*Siralak    Teekha                 6213133
  Weerawich  Wongchatchalikun       6213166
  Korawit    Wisetsuwan 	    6213192*/

import java.io.*;
import java.util.*;
class SportTeam {

    protected String name;
    protected int totalscore;

    public SportTeam(String na) {
        name = na;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return totalscore;
    }

    public void print() {
        /* override this method in child class */ }
}
class Football extends SportTeam{
    private String league;
    private int win;
    private int draw;
    private int lost;

    public  Football(String n,String le,int dt[]){
    super(n);
    league = le;
    win = dt[0];
    draw = dt[1];
    lost = dt[2];
    totalscore = win*3 + draw;

    }
    
    //แก้ด้วยครับ
    public void print(){
    
    String output = String.format("    %-20s             %-14s             \t\t\tTotal score = %d ",name,league,totalscore);
    System.out.println(output);
  
    

    }

}
class Formula extends SportTeam{

     private String country;
     
     
     public Formula(String n,String c,int s[]){
     super(n);
     country = c;
     
     for(int i = 0;i<10;i++)
         totalscore += s[i];
     }
     //แก้ด้วยครับ
     public void print(){
         String output = String.format("    %-20s             \t\t\t\t%-14s          Total score = %d ",name,country,totalscore);
         System.out.println(output);
    }
     
}

public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SportTeam st[] = new SportTeam[10];
        Createobj(st);
        //only fooball team
        System.out.println("=== Only Football team ===");
        for(int i=0;i<10;i++){
           if(st[i] instanceof Football)
            st[i].print();        
        }
         System.out.println("=== Only Formula team ===");
         for(int i=0;i<10;i++){
           if(st[i] instanceof Formula)
            st[i].print();        
        }
         
         System.out.println("=== All team in reverse order ===");
         for(int i=9;i>=0;i--){
         
            st[i].print();        
        }
      
    }
    public static void Createobj(SportTeam ct[]){

        try{
         Scanner input = new Scanner(new File("teams.txt"));
         int count = 0;
         
         
            while (input.hasNext()) {
              
              String line = input.nextLine();
              String []buf = line.split(",");
              
              
              int type = Integer.parseInt(buf[0].trim());
              String name = buf[1].trim();
              String le   = buf[2].trim();
              int dt[] = new int[buf.length-3];
              
              for(int i= 3;i<buf.length;i++){
              
              dt[i-3] = Integer.parseInt(buf[i].trim());
              
              }
              
              if(type == 1){
                  ct[count] = new Football(name,le,dt);
              }else if(type == 2){
                  ct[count] = new Formula(name,le,dt);
              }

              
              count++; 

                
            }



        }
        catch(Exception e){
            System.out.print(e);

        }
        
    }

}
