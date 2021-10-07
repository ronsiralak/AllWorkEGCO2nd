package lightsOutGraph.gui;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import lightsOutGraph.graphdata.Edge;
import lightsOutGraph.graphdata.Node;

public class GraphEditPanel extends GraphPanelBase
{
   
   GraphEditPanel(){}

// get/set stuff -----------------------------------------------
   private boolean symmetric = true;
   public void setSymmetric(boolean s){ symmetric = s; repaint(); }
   private boolean reflexive = true;
   public void setReflexive(boolean s){ reflexive = s; repaint(); }
   private boolean snapToGrid = true;
   public void setSnapToGrid(boolean s){ snapToGrid = s; repaint(); }

// display stuff ------------------------------------
   
   protected void paintComponentPrepare(Graphics2D g2) {
      // paint grid
      if( snapToGrid ){
         g2.setColor( gridColor );
         double minbx = screenXtoBoard(0);
         double maxbx = screenXtoBoard(getWidth());
         double minby = screenYtoBoard(0);
         double maxby = screenYtoBoard(getHeight());
         final Line2D.Double line = new Line2D.Double(); 

         line.y1 = minby;
         line.y2 = maxby;
         for( double x=Math.ceil(minbx); x<maxbx; x++){
            line.x1 = line.x2 = x;
            g2.draw(line);
         }
         line.x1 = minbx;
         line.x2 = maxbx;
         for( double y=Math.ceil(minby); y<maxby; y++){
            line.y1 = line.y2 = y;
            g2.draw(line);
         }
      }
   }

   protected void paintComponentSecond(Graphics2D g2){
      // paint dragged edge
      if( startNode!=null ){
         g2.setColor(edgeColor);
         drawEdge(g2, startNode.x, startNode.y, screenXtoBoard(offsetX), screenYtoBoard(offsetY), 1, symmetric ? 1 : 0);
      }
   }

   protected void drawNode(Graphics2D g2, Node n){
      final Ellipse2D.Double circle = new Ellipse2D.Double();
      circle.x = n.x - nodeSize;
      circle.y = n.y - nodeSize;
      circle.width = circle.height = nodeSize * 2;
      g2.setColor(nodeColor);
      g2.fill(circle);
   }
   
// mouse stuff -------------------------------------- 
   private Node dragNode=null;
   private Node startNode=null;
   
   public void mousePressed(MouseEvent e) {
      double bx = screenXtoBoard(e.getX());
      double by = screenYtoBoard(e.getY());
      if(e.isControlDown() ){
         Node n = getNearestNode(bx,by, nodeSize);
         if( n==null ){
            // create new node at cursor
            dragNode = graph.addNode(bx,by,0,0);
            if( reflexive ) graph.addEdge(dragNode,dragNode,1,0);
            offsetX = 0;
            offsetY = 0;
            repaint();
         }
      }else if(e.isAltDown() ){
         Node n = getNearestNode(bx,by, nodeSize);
         if( n!=null ){
            graph.deleteNode(n);
            repaint();
         }else{
            Edge ed = getNearestEdge(bx,by, nodeSize);
            if( ed != null ){
               graph.deleteEdge(ed);
               repaint();
            }
         }
      }else if(e.isShiftDown() ){
         startNode = getNearestNode(bx,by, nodeSize);
         offsetX = e.getX();
         offsetY = e.getY();
         repaint();
      }else{
         dragNode = getNearestNode(bx,by, nodeSize);
         if( dragNode!=null ){
            offsetX = boardXtoScreen(dragNode.x)-e.getX();
            offsetY = boardYtoScreen(dragNode.y)-e.getY();
            repaint();
         }else{
            super.mousePressed(e);
         }
      }
   }
   public void mouseDragged(MouseEvent e) {
      if( dragNode!=null ){
         dragNode.x = screenXtoBoard( offsetX+e.getX() );
         dragNode.y = screenYtoBoard( offsetY+e.getY() );
         repaint();
      }else if(startNode!=null){
         offsetX = e.getX();
         offsetY = e.getY();
         repaint();
      }else{
         super.mouseDragged(e);
      }
   }
   public void mouseReleased(MouseEvent e) {
      double bx = screenXtoBoard(e.getX());
      double by = screenYtoBoard(e.getY());
      if( dragNode!=null ){
         // snap node to grid
         if( snapToGrid ){
            dragNode.x = Math.round( dragNode.x );
            dragNode.y = Math.round( dragNode.y );
         }
         // stop dragging node
         graph.notifyChange();
         dragNode = null;
      }else if( startNode!=null ){
         // add edge
         Node n = getNearestNode(bx,by, nodeSize);
         if( startNode==n){
            if( !hideReflex )            
               graph.addEdge(startNode,n,1,0);
         }else if( n!=null )
            graph.addEdge(startNode,n,1,symmetric?1:0);
         startNode = null;
      }else{
         super.mouseReleased(e);
      }
      repaint();
   }
}
