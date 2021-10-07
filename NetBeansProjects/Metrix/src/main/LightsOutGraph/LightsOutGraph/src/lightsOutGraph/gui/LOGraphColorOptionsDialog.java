package lightsOutGraph.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JFrame;

import settingsmodule.ColorOptionsDialog;

public class LOGraphColorOptionsDialog
   extends ColorOptionsDialog
{
   private static String[] colorKeys = {
         LightsOutGraph.settingKeyBgColour,
         LightsOutGraph.settingKeyEdgeColour,
         LightsOutGraph.settingKeyNodeColour,
         LightsOutGraph.settingKeyGridColour,
   };
   private static String[] colorString = {
         "Background Colour",
         "Edge Colour",
         "Node Outline Colour",
         "Grid Colour",
   };

   class PreviewIcon implements Icon{
      int cellSize = 20;
      int width = 8*cellSize;
      int height = 7*cellSize;
      public int getIconHeight() { return height; }
      public int getIconWidth() { return width; }
      public void paintIcon(Component c, Graphics g, int x, int y) {
         g.setColor(getColor(LightsOutGraph.settingKeyBgColour));
         g.fillRect(x,y,width,height);
         g.setColor(getColor(LightsOutGraph.settingKeyGridColour));
         for( int i=cellSize/2; i<width; i+=cellSize){
            g.drawLine(i, 0, i, height);
         }
         for( int i=cellSize/2; i<height; i+=cellSize){
            g.drawLine(0, i, width, i);
         }
         int x1 = cellSize*3/2;
         int x2 = cellSize*13/2;
         int y1 = cellSize*3/2;
         int y2 = cellSize*11/2;
         g.setColor(getColor(LightsOutGraph.settingKeyEdgeColour));
         g.drawLine(x1, y1, x2, y2);
         int r = cellSize/4;
         g.setColor(Color.WHITE);
         g.fillOval(x1-r, y1-r, r+r, r+r);
         g.fillOval(x2-r, y2-r, r+r, r+r);
         g.setColor(getColor(LightsOutGraph.settingKeyNodeColour));
         g.drawOval(x1-r, y1-r, r+r, r+r);
         g.drawOval(x2-r, y2-r, r+r, r+r);
      }
   }   
   
   public LOGraphColorOptionsDialog(JFrame frame) {
      super(frame, LightsOutGraph.settingsManager, colorKeys, colorString);
      setPreviewIcon(new PreviewIcon());
   }
}
