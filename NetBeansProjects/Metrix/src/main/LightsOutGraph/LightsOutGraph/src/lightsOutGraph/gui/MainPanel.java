package lightsOutGraph.gui;
// Tile Puzzle Solver applet

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import lightsOutGraph.graphdata.Puzzle;
import fileMenu.ISavableAspect;
import fileMenu.ISavableDocument;

public final class MainPanel
   extends JPanel
   implements ChangeListener, ISavableDocument
{
   private int currentTab;
   /* tabs are:
    *  input: for textual import/export of puzzle
    *  board: for editing the board
    *  analyse: for analysing the game
    *  play: for playing the game
    *  notes: for making notes
    */

   private JTabbedPane tabSet;

   private MyTab[] tabPanels = {
         new TabBoard(),
         new TabAnalyse(),
         new TabPlay(),
         new TabNotes(),
   };
   Puzzle puz=new Puzzle();

   public MainPanel() {
      setPreferredSize(new Dimension(600,500));

      tabSet = new JTabbedPane();

      // build row of tabs
      setLayout(new BorderLayout());
      add(tabSet);

      JPanel southPanel = new JPanel(new BorderLayout());
      JLabel copy = new JLabel(" Written by Jaap Scherphuis, © 2004-2017");
      copy.setToolTipText(" email: puzzles@jaapsch.net   website: www.jaapsch.net/puzzles ");
      southPanel.add(copy,BorderLayout.EAST);
      southPanel.add(new JLabel("Version 1.71"),BorderLayout.WEST);
      add(southPanel,BorderLayout.SOUTH);

      for( int i=0; i<tabPanels.length; i++ ){
         MyTab tab = tabPanels[i];
         tabSet.addTab(tab.getLabel(),null,tab,tab.getToolTip());
      }
      currentTab=0;
      tabSet.setSelectedIndex(currentTab);
      tabSet.addChangeListener(this);
      tabPanels[currentTab].setPuzzle(puz);
   }

   public void stateChanged(ChangeEvent ce){
      tabPanels[currentTab].exit();
      int i=tabSet.getSelectedIndex();
      tabPanels[i].setPuzzle(puz);
      currentTab=i;
      tabPanels[i].init();
   }
   
   @Override
   public void paintComponent(Graphics g){
      tabPanels[currentTab].initColours();
      super.paintComponent(g);
   }


   
   // ISavableDocument

   // set/get used for loading/saving.
   @Override
   public String get(boolean allowLarge) {
      return puz.toString();
   }

   @Override
   public void set(String input) throws IOException{
      Reader reader = new StringReader(input);
      Puzzle p = Puzzle.parse(reader);
      reader.close();
      puz = p;
      tabPanels[currentTab].setPuzzle(puz);
      LightsOutGraph.updateTitle();
      repaint();
   }

   // flag used to know if it needs to be saved
   @Override
   public boolean hasUnsavedChanges(){
      return puz.hasUnsavedChanges();
   }

   // called after successful save, should clear the flag till savable contents change.
   @Override
   public void setNoUnsavedChanges(){
      puz.setNoUnsavedChanges();
   }

   // clear all savable content. Called when the New menu option chosen.
   @Override
   public void clear(){
      puz.clear();
      tabPanels[currentTab].init(); // ensure changes reflected in the screen
      repaint();
   }

   // name of the server directory that this object is allowed to write to/read from. null means no server access allowed.
   @Override
   public String getServerDir(){ return "lights"; }

   // Name of the file extension.
   @Override
   public String getFileExtension(){ return "lo"; }

   // Description of the type of files with that extension.
   @Override
   public String getFileExtensionDesc(){ return "Lights Out files"; }

   @Override
   public ISavableAspect[] getSavables() { return null; }
}
