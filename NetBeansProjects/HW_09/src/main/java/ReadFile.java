 /*Siralak    Teekha                 6213133
  Weerawich  Wongchatchalikun       6213166
  Korawit    Wisetsuwan 	    6213192*/
import java.io.*;
import java.util.*;
import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.alg.connectivity.*;
import org.jgrapht.alg.spanning.*;
public class ReadFile {

    private String Filename = "";
    private  Graph<String, DefaultWeightedEdge> G;
    private HashSet<String> Hn;
    public ReadFile() {

    }

    public Graph<String, DefaultWeightedEdge> read() {

        boolean open = true;
        Scanner getname = new Scanner(System.in);
        int count = 0;
      
        ArrayList<String> stationName = new ArrayList<String>();
        stationName.add("A");stationName.add("B");
        stationName.add("C");stationName.add("D");
        stationName.add("E");stationName.add("F");
        stationName.add("G");stationName.add("H");
        stationName.add("I");stationName.add("J");
        
        //G =  new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
        G = new SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
        Hn =  new HashSet<String>();
        while (open) {
//            System.out.println("Enter Filename : ");
//           Filename = getname.nextLine().trim();
              Filename = "trainmap.txt";
            try (Scanner input = new Scanner(new File(Filename))) {
                open = false;
                while (input.hasNext()) {
                         
                         String line = input.nextLine();
                         String []buf = line.split(",");
                        // System.out.printf("%s %s %d ",buf[0].trim(), buf[1].trim(),Integer.parseInt(buf[2].trim()));
                       Graphs.addEdgeWithVertices(G, buf[0].trim(), buf[1].trim(), Integer.parseInt(buf[2].trim()));
                        
                      //if(count == 19)
                          //  break;
                }

            }catch(FileNotFoundException e){
        System.out.println(e);
        System.out.println(" Please input File name movies.txt into your Folder");
        
        }
            
                
           
        }
       
            return G;
    }
    
   

}

