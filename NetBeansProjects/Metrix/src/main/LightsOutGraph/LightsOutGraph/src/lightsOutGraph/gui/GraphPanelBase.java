package lightsOutGraph.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;

import javax.swing.JButton;
import javax.swing.JPanel;

import lightsOutGraph.graphdata.Edge;
import lightsOutGraph.graphdata.Graph;
import lightsOutGraph.graphdata.Node;

public abstract class GraphPanelBase extends JPanel
	implements ComponentListener, ActionListener, MouseListener, MouseMotionListener
{
	protected Graph graph=null;
	protected boolean hideReflex = false;
	private boolean hideArrows = false;
	private boolean initialised = false;
   private JButton zoomInButton = new JButton("+");
   private JButton zoomOutButton = new JButton("-");
   private static final int ZOOMBUTTONSIZE = 40;

   protected Color edgeColor = Color.BLACK;
   protected Color nodeColor = Color.BLACK;
   protected Color gridColor = Color.GRAY;

   public void setColors(Color bgColor0, Color edgeColor0, Color nodeColor0, Color gridColor0){
      setBackground(bgColor0);
      edgeColor = edgeColor0;
      nodeColor = nodeColor0;
      gridColor = gridColor0;
   }
   
	protected GraphPanelBase(){
      super(null);
		addComponentListener(this);
		// set default background colour
		setBackground(Color.WHITE);
		initialised = false;
		add(zoomInButton);
		add(zoomOutButton);
      zoomInButton.addActionListener(this);
      zoomOutButton.addActionListener(this);
      addMouseListener(this);
      addMouseMotionListener(this);
	}


// coordinate conversions --------------------------------------
	protected double screenCentreX, screenCentreY;
	private double pixelSize;
	private int width, height;
	
	protected double screenXtoBoard( int x ){
      return (x-width/2)*pixelSize + screenCentreX;
   }
	protected double screenYtoBoard( int y ){
      return (y-height/2)*pixelSize + screenCentreY;
   }
	protected int boardXtoScreen( double bx ){
      return (int)((bx-screenCentreX)/pixelSize  + width/2);
   }
	protected int boardYtoScreen( double by ){
      return (int)((by-screenCentreY)/pixelSize  + height/2);
   }
	protected void scaleToFit(double minx, double miny, double maxx, double maxy){
      width = getWidth();
      height = getHeight();
      screenCentreX = (maxx+minx)/2.;
      screenCentreY = (maxy+miny)/2.;
      double scalex = (maxx-minx+nodeSize*2)/width;
      double scaley = (maxy-miny+nodeSize*2)/height;
      pixelSize = Math.max( Math.max(scalex, scaley), 0.04);
   }
	protected void scaleToFitGraph(){
      double minx=0, maxx=0, miny=0, maxy=0;
      boolean first = true;
      for(Node n : graph.getNodes() ){
         if( first ){
            minx = maxx = n.x;
            miny = maxy = n.y;
            first = false;
         }else{
            minx = Math.min(minx, n.x);
            miny = Math.min(miny, n.y);
            maxx = Math.max(maxx, n.x);
            maxy = Math.max(maxy, n.y);
         }
      }
      scaleToFit(minx, miny, maxx, maxy);
   }
   public void rescale(){
      scaleToFitGraph();
      repaint();
   }   
// get/set stuff -----------------------------------------------
	public void setHideArrows(boolean s){ hideArrows = s; repaint(); }
	public void setHideReflex(boolean s){ hideReflex = s; repaint(); }
	public void setGraph(Graph g){
		graph=g;
		initialised = false;
		repaint();
	}
// all screen output stuff -------------------------------------------
	protected static final double nodeSize=.3;
   protected static final double nodeSizeInside=.2;
   private static final double loopSize=.2;
   private static final double arrowSize=.3;
	protected int offsetX, offsetY;
	
	private Stroke stroke = new BasicStroke(0.03f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER);

   abstract protected void paintComponentPrepare(Graphics2D g2);
   abstract protected void paintComponentSecond(Graphics2D g2);
   abstract protected void drawNode(Graphics2D g2, Node n);
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);  //paint background
		if(graph==null || getWidth()==0 ) return;
		if( !initialised ){
		   scaleToFitGraph();
		   initialised = true;
		}
      zoomInButton.setBounds(width - ZOOMBUTTONSIZE, 0, ZOOMBUTTONSIZE, ZOOMBUTTONSIZE);
      zoomOutButton.setBounds(width - 2*ZOOMBUTTONSIZE, 0, ZOOMBUTTONSIZE, ZOOMBUTTONSIZE);

      Graphics2D g2 = (Graphics2D)g;
		AffineTransform savedTransform = g2.getTransform();
		g2.translate(getWidth()/2., getHeight()/2.);
		g2.scale(1/pixelSize, 1/pixelSize);
      g2.translate(-screenCentreX, -screenCentreY);
		
      g2.setStroke(stroke);
      
      paintComponentPrepare(g2);
		
		// paint all edges
		g.setColor(edgeColor);
		Iterable<Edge> edges = graph.getEdges();
		for(Edge edge : edges){
			Node n1 = edge.getNode1();
			Node n2 = edge.getNode2();
			if( n1 != n2 ){
			   int v1 = edge.valence1 % graph.getNumStates();
			   int v2 = edge.valence2 % graph.getNumStates();
				if( v1!=0 || v2!=0 )
				   drawEdge( g2, n1.x, n1.y, n2.x, n2.y, v1, v2);
			}else if(!hideReflex){
			   //TODO
			   final Ellipse2D.Double loop = new Ellipse2D.Double();
			   loop.x = n1.x + nodeSize - loopSize;
			   loop.y = n1.y - nodeSize - loopSize;
			   loop.height = loop.width = loopSize * 2;
				g2.draw(loop);
				if(!hideArrows){
					// TODO: arrows on loops
				}
			}
		}

		// paint all nodes
		Iterable<Node> nodes = graph.getNodes();
		for(Node node : nodes){
		   drawNode(g2,node);
		}
		
		paintComponentSecond(g2);

		g2.setTransform(savedTransform);
	}
	protected void drawEdge( Graphics2D g2, double x1, double y1, double x2, double y2, int v1, int v2 ){
      final Line2D.Double edge = new Line2D.Double(); 
      edge.x1 = x1;
      edge.x2 = x2;
      edge.y1 = y1;
      edge.y2 = y2;
		g2.draw(edge);

		if(!hideArrows){
			drawArrows( g2, x1,y1, x2,y2, v1 );
			drawArrows( g2, x2,y2, x1,y1, v2 );
		}
	}
	
	private Path2D arrowShape;
	private void drawArrows( Graphics2D g2, double x1, double y1, double x2, double y2, int v1 ){
		if( v1<=0 )return;
		if( arrowShape == null ){
		   Path2D.Double path = new Path2D.Double();
		   path.moveTo(0,0);
         path.lineTo(-arrowSize, -arrowSize);
         path.lineTo( .1, 0);
         path.lineTo(-arrowSize,  arrowSize);
         path.lineTo( 0, 0);
         arrowShape = path;
		}
		double dx = (x2-x1)/30;
		double dy = (y2-y1)/30;
		AffineTransform at1 = AffineTransform.getTranslateInstance( (x1+x2)/2 + 11*dx, (y1+y2)/2 + 11*dy);
		at1.rotate(dx,dy);
		
		for( int i=0; i<v1; i++ ){
	      AffineTransform at2 = AffineTransform.getTranslateInstance( -dx*i,-dy*i);
	      at2.concatenate(at1);
	      Shape arrow = arrowShape.createTransformedShape(at2);
			g2.fill(arrow);
			g2.draw(arrow);
		}
	}

	protected Node getNearestNode(double bx, double by, double maxd){
		Node bn = null;
		double d = -1;
		for( Node node : graph.getNodes() ){
			double e = node.squaredDistanceTo(bx, by);
			if(d<0 || e<d ){
				d=e; bn=node;
			}
		}
		return ( d<maxd*maxd ) ? bn : null; 
	}
	protected Edge getNearestEdge(double bx, double by, double maxd){
		Edge ed = null;
		double d = -1;
		for( Edge edge : graph.getEdges() ){
			Node n1 = edge.getNode1();
			Node n2 = edge.getNode2();
			if( n1!=n2 ){
				double ex = n1.x-n2.x;
				double ey = n1.y-n2.y;
				double dx1 = bx-n1.x;
				double dy1 = by-n1.y;
				double dx2 = dx1+ex;
				double dy2 = dy1+ey;
				// 	first check that point lies between endpoints
				if( ex*dx1+ey*dy1<0 && ex*dx2+ey*dy2>0 ){
					// get distance (squared)
				   double e = dx1*ey - dy1*ex;
					e = e*e/(ex*ex+ey*ey);
					// 	check for improvement
					if(d<0 || e<d ){
						d=e; ed=edge;
					}
				}
			}else{
			   double dx = n1.x+nodeSize - bx;
			   double dy = n1.y-nodeSize - by;
			   double e = dx*dx+dy*dy;
				if( e<(maxd+loopSize)*(maxd+loopSize) ){
					double t = Math.sqrt(e)-loopSize;
					t = t*t;
					if(d<0 || t<d ){
						d=(int)t; ed=edge;
					}
				}
			}
		}
		return ( d<maxd*maxd ) ? ed : null; 
	}
	

   public void componentHidden(ComponentEvent arg0) {}
   public void componentMoved(ComponentEvent arg0) {}
   public void componentResized(ComponentEvent arg0) {
      initialised = false;
      repaint();
   }
   public void componentShown(ComponentEvent arg0) {
      initialised = false;
      repaint();
   }
   
   public void actionPerformed(ActionEvent ae){
      if( ae.getSource() == zoomInButton){
         pixelSize /= 1.1;
         repaint();
      }else if( ae.getSource() == zoomOutButton){
         pixelSize *= 1.1;
         repaint();
      }
   }
   
// mouse stuff -----------------------------------
   private boolean dragging;
   public void mousePressed(MouseEvent e) {
      dragging = true;
      offsetX = e.getX();
      offsetY = e.getY();
   }
   public void mouseDragged(MouseEvent e) {
      if(dragging){
         double dx = screenXtoBoard(e.getX()) - screenXtoBoard(offsetX);
         double dy = screenYtoBoard(e.getY()) - screenYtoBoard(offsetY);
         offsetX = e.getX();
         offsetY = e.getY();
         screenCentreX -= dx;
         screenCentreY -= dy;
         repaint();
      }
   }
   public void mouseMoved(MouseEvent e) {}
   public void mouseReleased(MouseEvent e) {
      dragging = false;
   }
   public void mouseEntered(MouseEvent e) {}
   public void mouseExited(MouseEvent e) {}
   public void mouseClicked(MouseEvent e) {}   
}
