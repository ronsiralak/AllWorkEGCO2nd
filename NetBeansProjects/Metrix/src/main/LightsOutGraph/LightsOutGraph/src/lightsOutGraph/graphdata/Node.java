package lightsOutGraph.graphdata;
import java.util.ArrayList;
import java.util.List;



public class Node {
	public double x, y;
	private int state;
	private int solState;
	private List<Edge> edges = new ArrayList<Edge>();
	
	public Node( double x, double y, int s, int t){
		this.x=x;
		this.y=y;
		state=s;
		solState=t;
	}

	public void reset(){
		state = 0;
		solState = 0;
	}
	public void incState(int numStates, int s){
		state +=s + numStates;
		state %=numStates;
	}
	public void incSolState(int numStates, int s){
		solState +=s;
		solState %=numStates;
	}
   public double squaredDistanceTo(double x2, double y2){
      return (x-x2)*(x-x2)+(y-y2)*(y-y2);
   }
   public double squaredDistanceTo(Node n2){
      return squaredDistanceTo(n2.x,n2.y);
   }
	public int getState(){ return state; }
	public void setState(int s){ state = s; }
	public int getSolState(){ return solState; }
	public void setSolState(int s){ solState = s; }
	public void addEdge( Edge e ){
		edges.add(e);
	}
	public void removeEdge( Edge e ){ edges.remove(e); }
	public Iterable<Edge> getEdges(){ return edges; }
}
