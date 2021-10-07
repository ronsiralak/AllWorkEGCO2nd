
import java.util.*;
import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.alg.connectivity.*;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.BellmanFordShortestPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.shortestpath.NegativeCycleDetectedException;
import org.jgrapht.alg.spanning.*;

public class myapplication {

    protected Graph<String, DefaultWeightedEdge> G;

    public myapplication() {
        ReadFile re = new ReadFile();
        G = re.read();

    }

    public void printGraph() {
        Set<DefaultWeightedEdge> allEdges = G.edgeSet();
        printDefaultWeightedEdges(allEdges);
    }

    public void printDefaultWeightedEdges(Collection<DefaultWeightedEdge> E) {
        for (DefaultWeightedEdge e : E) {
            //System.out.println(e.toString());

            String source = G.getEdgeSource(e);
            String target = G.getEdgeTarget(e);
            double weight = G.getEdgeWeight(e);

            System.out.printf("%2s - %2s  weight = %4.0f  \n", source, target, weight);

        }
    }

    public void SetEdge() {

        Scanner scan = new Scanner(System.in);
        System.out.println("\nEnter name 1 : ");
        String key1 = scan.next();
        System.out.println("Enter Country name 2 : ");
        String key2 = scan.next();

        if (G.containsEdge(key1, key2)) {
            DefaultWeightedEdge edge = G.getEdge(key1, key2);
            double weight = -1 * G.getEdgeWeight(edge);

            // System.out.printf("\nWeight of %s-%s = %.2f mins \n\n",  key1, key2, weight);
            Graphs.addEdgeWithVertices(G, key1, key2, weight);
            System.out.printf("\nWeight of %s-%s = %.2f mins \n\n", key1, key2, weight);
        }

    }

    public void testShortestPath() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nEnter  source : ");
        String key1 = scan.next();
        System.out.println("Enter  destination : ");
        String key2 = scan.next();

        // source and target must exist, otherwise error
        if (G.containsVertex(key1) && G.containsVertex(key2)) {
            ShortestPathAlgorithm<String, DefaultWeightedEdge> shpath = null;
            boolean negweight = false;
            boolean negcycle = false;

            try // Error if negative weight exists
            {
                shpath = new DijkstraShortestPath<>(G);
                shpath.getPath(key1, key2);                   // dummy instruction to check exception
            } catch (IllegalArgumentException e) {
                System.out.println(e);
                negweight = true;
            }

            if (negweight) {
                try // Error (from BellmanFord) if negative cycle exists
                {
                    shpath = new BellmanFordShortestPath<>(G);
                    //shpath = new FloydWarshallShortestPaths<>(G);  
                    shpath.getPath(key1, key2);               // dummy instruction to check exception
                } catch (NegativeCycleDetectedException e) {
                    System.out.println(e);
                    negcycle = true;
                }
            }

            if (negcycle) {
                return;
            }
            if (shpath.getPath(key1, key2) != null) {
                System.out.printf("\nTotal edges = %d, total weight = %.2f \n",
                        shpath.getPath(key1, key2).getLength(),
                        shpath.getPath(key1, key2).getWeight());

                System.out.println("\nEdge list");
                List<DefaultWeightedEdge> allEdges = shpath.getPath(key1, key2).getEdgeList();
                printDefaultWeightedEdges(allEdges);

                System.out.print("\nNode list = ");
                List<String> allNodes = shpath.getPath(key1, key2).getVertexList();
                for (String s : allNodes) {
                    System.out.printf("%s  ", s);
                }
                System.out.println();
            } else {
                System.out.printf("\nPath from %s to %s doesn't exist\n", key1, key2);
            }
        }
    }

    public static void main(String args[]) {
        myapplication mygraph = new myapplication();

        mygraph.SetEdge();
        mygraph.printGraph();
        //mygraph.testShortestPath();
    }
}
