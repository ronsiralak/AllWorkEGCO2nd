package lightsOutGraph.graphdata;


public class Edge {
	private Node node1, node2;
	public int valence1, valence2;
	
	public Edge( Node n1, Node n2 ){
		node1 = n1; node2 = n2;
	}
	public Node getNode1(){ return node1; }
	public Node getNode2(){ return node2; }
	public Node getOtherNode(Node n){
		if( n==node1 ) return node2;
		else if( n==node2 ) return node1;
		return null;
	}
	public int getValence(Node n){
      if( n==node1 && n==node2 ) return valence1-valence2;
      else if( n==node1 ) return valence1;
		else if( n==node2 ) return valence2;
		return 0;
	}
	public boolean hasNodes(Node n1, Node n2){
		if( node1==n1 && node2==n2 ) return true;
		if( node2==n1 && node1==n2 ) return true;
		return false;
	}
	public boolean equals(Edge edge2){
		return hasNodes(edge2.node1, edge2.node2 );
	}
	public void add(Edge edge2, int numStates){
		if( node1==edge2.node1 && node2==edge2.node2 ) {
			valence1 += edge2.valence1;
			valence2 += edge2.valence2;
		}else if( node2==edge2.node1 && node1==edge2.node2 ) {
			valence1 += edge2.valence2;
			valence2 += edge2.valence1;
		}
      if( node1 == node2 ){
         valence1 += numStates+numStates-valence2;
         valence2 = 0;
      }
		valence1 %= numStates;
		valence2 %= numStates;
	}
	public boolean isTrivial(){
		return valence1 ==0 && valence2==0;
	}
}
