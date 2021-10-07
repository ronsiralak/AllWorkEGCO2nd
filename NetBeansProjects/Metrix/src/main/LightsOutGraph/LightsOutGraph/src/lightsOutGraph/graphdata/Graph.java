package lightsOutGraph.graphdata;
//	Contains complete puzzle specification

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fileMenu.Parser;


public class Graph
{
   private ICallBack _callback;
	private int numStates = 2;
	private List<Edge> edges = new ArrayList<Edge>();
	private List<Node> nodes = new ArrayList<Node>();

	Graph(ICallBack callback){ _callback = callback; }
	
//	all get/set stuff
	public void clear(){
		edges.clear();
		nodes.clear();
	}
	public void notifyChange(){
	   _callback.callback();
	}
	public void clearState(){
		for (Node node : nodes) {
			node.reset();
		}
	}
	public void incState(Node node,int s){
		node.incState(numStates, s);
	}
	public void doMove(Node n){
		for (Edge edge : n.getEdges()) {
			Node n2 = edge.getOtherNode(n);
			if( n2!=null ){
				n2.incState(numStates, edge.getValence(n));
			}
		}
		n.incSolState(numStates, 1);
	}
	public int getNumStates(){
	   return numStates;
	}
	public void setNumStates(int i){
	   numStates=i;
	   notifyChange();
	}
	public Node addNode(double x,double y,int s,int t){
		Node n = new Node(x,y,s,t);
		nodes.add(n);
		notifyChange();
		return n;
	}

	public Edge addEdge(Node n1, Node n2, int v1,int v2){
		Edge edge = new Edge(n1,n2);
		edge.valence1 = v1;
		edge.valence2 = v2;
		addEdge(edge);
		return edge;
	}
	public void addEdge(Edge edge){
		// check for duplicate
		for( Edge edge2 : edges ){
			if( edge.equals(edge2)){
				edge2.add( edge, numStates );
				if( edge2.isTrivial() )
				   deleteEdge(edge2);
				else
				   notifyChange();
				return;
			}
		}
		if( !edge.isTrivial() ){
			edge.getNode1().addEdge(edge);
			if( edge.getNode1()!= edge.getNode2() )
				edge.getNode2().addEdge(edge);
			edges.add(edge);
		}
      notifyChange();
	}
	public void deleteNode(Node n){
		if( n==null )return;
		notifyChange();

		// Disconnect its edges from adjacent nodes.
		for (Edge edge : n.getEdges()) {
			Node n2 = edge.getOtherNode(n);
			if( n2!=n) n2.removeEdge(edge);
			edges.remove(edge);
		}
		// remove node
		nodes.remove(n);
	}
	public void deleteEdge(Edge e){
		if( e==null )return;
		notifyChange();
		// Disconnect edge from adjacent nodes.
		e.getNode1().removeEdge(e);
		e.getNode2().removeEdge(e);
		edges.remove(e);
	}

	public int getNumNodes(){return nodes.size(); }
	public int getNumEdges(){return edges.size(); }
	public int getNumReflexiveNodes(){
	   int nr = 0;
	   for( Node n : getNodes() ){
	      for( Edge e : n.getEdges() ){
	         if( e.getNode1() == e.getNode2() ){
	            nr++;
	            break;
	         }
	      }
	   }
	   return nr;
	}
   public int getNumSymmetricEdges(){
      int ns = 0;
      for( Edge e : getEdges() ){
         if( e.getNode1()!=e.getNode2() && e.valence1 == e.valence2 ) ns++;
      }
      return ns;
   }
	
	public int[] getState(){
		int[] state = new int[nodes.size()];
		int i=0;
		for (Node node : nodes) {
			state[i] = node.getState();
			i++;
		}
		return state;
	}
	public void setSolution(int[] solution){
		int i=0;
		for (Node node : nodes) {
			node.setSolState( solution[i] );
			i++;
		}
	}
	
	public void setState(int[] pattern){
		int i=0;
		for (Node node : nodes) {
			node.setState( pattern[i] );
			i++;
		}
	}

	public void resetState(){
      for( Node node : nodes ) node.reset();
	}

	public void mixState(){
		clearState();
		for( Node node : nodes){
			int k = (int)Math.floor(Math.random()*numStates);
			while(k>0) {
				doMove(node);
				k--;
			}
		}
	}	
	
//	all text io stuff ----------------------------------------------
	public static Graph parse(Parser parser, ICallBack callback) throws IOException{
		Graph g=new Graph(callback);
		String s;
		
		g.numStates = parser.readNumber(2);
		// read nodes
		while(true){
			s=parser.readString();
			if(s==null || s.equals(";")) break;
			parser.pushback();
			double x = parser.readDouble();
			double y = parser.readDouble();
			g.addNode(x,y,0,0);
		}
		// read edges
		while(true){
			s=parser.readString();
			if(s==null || s.equals(";")) break;
			parser.pushback();
			int n1 = parser.readNumber();
			int n2 = parser.readNumber();
			int v = parser.readNumber();
			int w = parser.readNumber();
			g.addEdge(g.nodes.get(n1),g.nodes.get(n2),v,w);
		}
		return g;
	}
	public int getNodeIndex( Node n ){
		return nodes.indexOf(n);		
	}

	public String toString(){
		StringBuffer sb=new StringBuffer();
		sb.append(numStates).append("\n");
		for( Node node : nodes ){
			sb.append(node.x).append(" ").append(node.y).append("\n");
		}
		sb.append(";\n");
		for( Edge edge : edges ){
			int n1 = nodes.indexOf(edge.getNode1());
			int n2 = nodes.indexOf(edge.getNode2());
			sb.append(n1).append(" ").append(n2).append(" ").append(edge.valence1).append(" ").append(edge.valence2).append("\n");
		}
		sb.append(";");
		return sb.toString();
	}

	public Iterable<Node> getNodes() {
		return nodes;
	}
	public Iterable<Edge> getEdges() {
		return edges;
	}
}