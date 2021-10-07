import java.util.*;
import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.alg.connectivity.*;
import org.jgrapht.traverse.*;
import org.jgrapht.alg.cycle.*;
import org.jgrapht.alg.shortestpath.*;
import org.jgrapht.alg.interfaces.*;


class D11_3_SimpleDirectedGraph extends D11_1_SimpleGraph
{
    // Use members inherited from D11_1_SimpleGraph
    //    Variables : AllCountries, CountryNames, G
    //    Methods : searchCountry, printGraph, printDefaultEdges, 
    //              checkType, testContent, testWeakConnectivity, testColoring

    ///////////////////////////////////////////////////////////
    //protected SimpleDirectedGraph<String, DefaultEdge>      G


    public D11_3_SimpleDirectedGraph()
    {
	// real Country objects
	AllCountries = new HashMap<String, Country>();
	AllCountries.put("china", new Country("China", 2.44));
	AllCountries.put("korea", new Country("Korea", 3.19));
	AllCountries.put("japan", new Country("Japan", 4.16));
	AllCountries.put("thailand",  new Country("Thailand",  2.46));
	AllCountries.put("malaysia",  new Country("Malaysia",  2.34));
	AllCountries.put("singapore", new Country("Singapore", 3.75));
	AllCountries.put("australia", new Country("Australia", 4.20));

	// graph nodes are only country names
	CountryNames = new ArrayList<String>();
	CountryNames.add("china");	CountryNames.add("korea"); 
	CountryNames.add("japan");	CountryNames.add("thailand");
	CountryNames.add("malaysia");	CountryNames.add("singapore");
	CountryNames.add("australia");

	G = new SimpleDirectedGraph<>(DefaultEdge.class);

	Graphs.addAllVertices(G, CountryNames);
	G.addEdge("china", "korea");		G.addEdge("china", "thailand");
	G.addEdge("china", "malaysia");		G.addEdge("korea", "japan");
	G.addEdge("korea", "australia");	G.addEdge("japan", "australia");
	G.addEdge("thailand", "japan");		G.addEdge("thailand", "singapore");
	G.addEdge("malaysia", "thailand");	G.addEdge("malaysia", "singapore");
	G.addEdge("singapore", "japan");	G.addEdge("singapore", "australia");
                                                //AUS->SIN instead of SIN->AUS causes cycle
                                                //G.addEdge("australia", "singapore");     
    }

    ///////////////////////////////////////////////////////////    
    public boolean testStrongConnectivity()
    {
        GabowStrongConnectivityInspector<String, DefaultEdge> strongConn = 
                new GabowStrongConnectivityInspector<>(G);
        
        if (strongConn.isStronglyConnected()) System.out.println("\nStrongly connected");
        else                                  System.out.println("\nNot strongly connected");
        
        return strongConn.isStronglyConnected();
    }    
    
    public void iterate(GraphIterator<String, DefaultEdge> GI)
    {
	while (GI.hasNext()) 
            System.out.printf("%s  ", GI.next());
	System.out.println();
    }

    public void testTraversal()
    {
	Scanner scan = new Scanner(System.in);
	System.out.println("\nEnter Country name for traversal : "); 
	String key1 = scan.next();

        GraphIterator<String, DefaultEdge> GI;
        
	// start node must exist, otherwise error
	if (G.containsVertex(key1))
	{	
            GI = new DepthFirstIterator<>(G, key1);
            System.out.printf("DF traversal from %s : ", key1);
            iterate(GI);

            GI = new BreadthFirstIterator<>(G, key1);
            System.out.printf("BF traversal from %s : ", key1);
            iterate(GI);
	}
    }

    public void testTopology()
    {
        GraphIterator<String, DefaultEdge> GI;
        
	// cycle must not exist
	CycleDetector<String, DefaultEdge> cycle = new CycleDetector<>(G);
	if (!cycle.detectCycles())
	{
            GI = new TopologicalOrderIterator<>(G);
            System.out.print("\nTopological order : ");
            iterate(GI);
        }
	else 
            System.out.println("\nCycle exists, no topological order");        
    }
    
    ///////////////////////////////////////////////////////////
    public void testShortestPath()
    {
	Scanner scan = new Scanner(System.in);
	System.out.println("\nEnter Country name 1 (source) : "); 
	String key1 = scan.next();
	System.out.println("Enter Country name 2 (target) : "); 
	String key2 = scan.next();

	// source and target must exist, otherwise error
	if (G.containsVertex(key1) && G.containsVertex(key2))
	{
            ShortestPathAlgorithm<String, DefaultEdge> shpath;
            
            shpath = new DijkstraShortestPath<>(G);
            //shpath = new BellmanFordShortestPath<>(G);
            //shpath = new FloydWarshallShortestPaths<>(G);
            
            // For unweighted graph we don't worry about negative edges/negative cycles
            if (shpath.getPath(key1, key2) != null)
            {
                System.out.printf("\nTotal edges = %d, total weight = %.2f \n", 
                                  shpath.getPath(key1, key2).getLength(), 
                                  shpath.getPath(key1, key2).getWeight());
                
                System.out.println("\nEdge list");
                List<DefaultEdge> allEdges = shpath.getPath(key1, key2).getEdgeList();
                printDefaultEdges(allEdges, false);
                
                System.out.print("\nNode list = ");
                List<String> allNodes = shpath.getPath(key1, key2).getVertexList();
                for (String s : allNodes) System.out.printf("%s  ", s);
                System.out.println();
            }
            else
                System.out.printf("\nPath from %s to %s doesn't exist\n", key1, key2);
	}
    }
    
    ///////////////////////////////////////////////////////////
    public static void main(String[] args) 
    {
	D11_3_SimpleDirectedGraph mygraph = new D11_3_SimpleDirectedGraph();
	System.out.println("Directed graph (no weight) \n");
	mygraph.printGraph();                  // parent's method
        //mygraph.checkType();                 // parent's method
	//mygraph.testContent();               // parent's method
        
        //mygraph.testWeakConnectivity();      // parent's method
        //mygraph.testStrongConnectivity();
        //mygraph.testColoring();              // parent's method
	//mygraph.testTraversal();
        //mygraph.testTopology();

	//mygraph.testShortestPath();
    }
}
