import java.util.*;
import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.alg.connectivity.*;
import org.jgrapht.alg.spanning.*;


class D11_2_SimpleWeightedGraph 
{
    ///////////////////////////////////////////////////////////
    protected HashMap<String, Country>  AllCountries;           // real objects
    protected ArrayList<String>         CountryNames;           // graph nodes

    protected Graph<String, DefaultWeightedEdge>                       G;
    //protected SimpleWeightedGraph<String, DefaultWeightedEdge>       G;
    
    public D11_2_SimpleWeightedGraph()
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

	G = new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);

	//Graphs.addEdgeWithVertices(G, "china", "china", 87);	         // error!

	Graphs.addEdgeWithVertices(G, "china", "korea", 87);
	Graphs.addEdgeWithVertices(G, "china", "thailand",  255);
	Graphs.addEdgeWithVertices(G, "china", "malaysia",  335);
	Graphs.addEdgeWithVertices(G, "korea", "japan",     101);
	Graphs.addEdgeWithVertices(G, "korea", "australia", 649);
	Graphs.addEdgeWithVertices(G, "japan", "thailand",  357);
	Graphs.addEdgeWithVertices(G, "japan", "singapore", 411);
	Graphs.addEdgeWithVertices(G, "japan", "australia", 613);
	Graphs.addEdgeWithVertices(G, "thailand", "malaysia",   104);
	Graphs.addEdgeWithVertices(G, "thailand", "singapore",  117);
	Graphs.addEdgeWithVertices(G, "malaysia", "singapore",  28);
	Graphs.addEdgeWithVertices(G, "singapore", "australia", 482);
    }

    ///////////////////////////////////////////////////////////
    public Country searchCountry(String name)
    {
	return AllCountries.get(name);
    }

    public void printGraph()
    {
	Set<DefaultWeightedEdge> allEdges = G.edgeSet();
	printDefaultWeightedEdges(allEdges, true);
    }

    public void printDefaultWeightedEdges(Collection<DefaultWeightedEdge> E, boolean f)
    {
	for (DefaultWeightedEdge e : E)
        {
            //System.out.println(e.toString());
	
            
            Country source = searchCountry(G.getEdgeSource(e));
            Country target = searchCountry(G.getEdgeTarget(e));
            double  weight = G.getEdgeWeight(e);

            if (f)  // print Country details
		System.out.printf("%18s - %18s     weight = %4.0f mins \n", 
				  source.getMessage(), target.getMessage(), weight);

            else    // print only Country name
		System.out.printf("%s - %s \n", source.getName(), target.getName());
        }
    }

    public void checkType()
    {
        // (1) use Java's operator -- difficult to get exact type
        //if (G instanceof SimpleGraph)         System.out.println("\nInstance of SimpleGraph");
        if (G instanceof SimpleWeightedGraph)   System.out.println("\nInstance of SimpleWeightedGraph");
        else                                    System.out.println("\nInstance of other");
        
        // (2) use GraphType
        GraphType type = G.getType();
        System.out.print("GraphType = ");
        if (type.isDirected())      System.out.print("directed  ");
        if (type.isSimple())        System.out.print("simple  ");
        if (type.isUndirected())    System.out.print("undirected  ");
        if (type.isWeighted())      System.out.print("weighted  ");
        System.out.println();
    }    
    
    public void testContent()
    {
	Scanner scan = new Scanner(System.in);
	System.out.println("\nEnter Country name 1 : "); 
	String key1 = scan.next();
	System.out.println("Enter Country name 2 : "); 
	String key2 = scan.next();

	if (G.containsEdge(key1, key2))
	{
            DefaultWeightedEdge edge = G.getEdge(key1, key2);
            System.out.printf("\nWeight of %s-%s = %.2f mins \n\n", 
			      key1, key2, G.getEdgeWeight(edge));
	}
	else
            System.out.printf("\n%s-%s does not exist \n\n", key1, key2);

	if (G.containsVertex(key1))
	{
            System.out.printf("Neighbors of %s : ", key1);
            List<String> neighbors = Graphs.neighborListOf(G, key1);
            for (int i=0; i < neighbors.size(); i++) 
		System.out.printf("%s  ", neighbors.get(i));
            System.out.println();
	}
    }

    ///////////////////////////////////////////////////////////
    public boolean testWeakConnectivity()
    {
        ConnectivityInspector<String, DefaultWeightedEdge> weakConn = 
                new ConnectivityInspector<String, DefaultWeightedEdge>(G);
        
        if (weakConn.isConnected()) System.out.println("\nWeakly connected");
        else                        System.out.println("\nNot weakly connected");
        
        return weakConn.isConnected();
    }
    
    public void testMST()
    {
        // check whether graph is connected first
	if (testWeakConnectivity()) 
	{
            KruskalMinimumSpanningTree<String, DefaultWeightedEdge> MST = new KruskalMinimumSpanningTree<>(G);
            //PrimMinimumSpanningTree<String, DefaultWeightedEdge> MST = new PrimMinimumSpanningTree<>(G);
            
            Set<DefaultWeightedEdge> treeEdges = MST.getSpanningTree().getEdges();
            System.out.printf("Total MST cost = %.0f \n", MST.getSpanningTree().getWeight());
            printDefaultWeightedEdges(treeEdges, true);
	}
	else 
            System.out.println("\nGraph is not connected");
    }
    
    ///////////////////////////////////////////////////////////
    public static void main(String[] args) 
    {
	D11_2_SimpleWeightedGraph mygraph = new D11_2_SimpleWeightedGraph();
	System.out.println("Simple weighted graph \n");
	mygraph.printGraph();
        //mygraph.checkType();
	mygraph.testContent();
        
        //mygraph.testWeakConnectivity();
        
	//mygraph.testMST();
    }
}
