package lightsOutGraph.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

import lightsOutGraph.graphdata.Node;

public class GraphDisplayPanel extends GraphPanelBase
{
   GraphDisplayPanel(){}

   static final int PLAYMODE = 0;
   static final int EDITMODE = 1;
   static final int LOCKMODE = 2;
   private int editMode; // 0=play, position edit
   public void setEditMode( int s ){ editMode = s; }

// get/set stuff -----------------------------------------------
   protected boolean hideSolution = true;
   public void setHideSolution(boolean s){ hideSolution = s; repaint(); }

// display stuff ------------------------------------
   private Color[] colList;

   protected void paintComponentPrepare(Graphics2D g2) {
      // generate colours for state
      if( colList==null || colList.length != graph.getNumStates() ){
         int n=graph.getNumStates();
         colList = new Color[n];
         colList[0] = Color.BLACK;
         colList[n-1] = Color.WHITE;
         for( int i=1; i<n-1; i++){
            colList[i] = Color.getHSBColor((float)(i-1)/(n-2),1,1);
         }
      }
   }

   protected void paintComponentSecond(Graphics2D g2){}

   protected void drawNode(Graphics2D g2, Node node){
      final Ellipse2D.Double circle = new Ellipse2D.Double();
      circle.x = node.x - nodeSize;
      circle.y = node.y - nodeSize;
      circle.width = circle.height = nodeSize * 2;
      // draw state
      g2.setColor( colList[node.getState()] );
      g2.fill(circle);
      g2.setColor( nodeColor );
      g2.draw(circle);
      // draw solution
      if( !hideSolution ){
         g2.setColor( colList[node.getSolState()] );
         circle.x = node.x - nodeSizeInside;
         circle.y = node.y - nodeSizeInside;
         circle.width = circle.height = nodeSizeInside * 2;
         g2.fill(circle);
         g2.setColor( nodeColor );
         g2.draw(circle);
      }
   }
   
// mouse stuff -------------------------------------- 
   
   public void mouseClicked(MouseEvent e) {
      if( editMode!=LOCKMODE){
         double bx = screenXtoBoard(e.getX());
         double by = screenYtoBoard(e.getY());
         Node n = getNearestNode(bx,by, nodeSize);
         if( n!=null ){
            if( editMode==PLAYMODE){
               graph.doMove(n);
            }else if( editMode==EDITMODE){
               graph.incState(n, 1);
            }
            repaint();
         }else{
            super.mouseClicked(e);
         }
      }else{
         super.mouseClicked(e);
      }
   }
}
