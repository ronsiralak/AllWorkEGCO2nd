package lightsOutGraph.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.JMenuBar;

import settingsmodule.SettingsManager;
import settingsmodule.SettingsMenu;

import fileMenu.FileMenu;
import fileMenu.ISavableDocument;

public class LightsOutGraph extends JApplet
{
   public static SettingsManager settingsManager;
   static public final String settingKeyBgColour = "bgcolor";
   static public final String settingKeyGridColour = "gridcolor";
   static public final String settingKeyEdgeColour = "edgecolor";
   static public final String settingKeyNodeColour = "nodecolor";
   
   public void init() {
      // the look&feel must be set before any swing objects are made.
      try {
         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (Exception e) { }
      
      settingsManager = SettingsManager.createSettingsManager(this,"lograph");
      mainPanel = new MainPanel();
      getContentPane().add(mainPanel);
      setJMenuBar(getMenu(true, mainPanel));
   }
   public String getAppletInfo() {
      return "LightsOutGraph, by Jaap Scherphuis";
   }
   
   private static JFrame frame;
   public static void main(String[] args) {
      try {
         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (Exception e) { }

      settingsManager = SettingsManager.createSettingsManager(LightsOutGraph.class);
      frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

      mainPanel = new MainPanel();
      frame.getContentPane().add(mainPanel);
      frame.setJMenuBar(getMenu(false, mainPanel));

      frame.addWindowListener(new WindowAdapter() {
          public void windowClosing(WindowEvent e) {
              attemptClose();
          }
      });

      frame.pack();
      frame.setVisible(true);
      updateTitle();
   }

   static private MainPanel mainPanel;
   static private FileMenu fileMenu;
   private static JMenuBar getMenu(boolean isApplet, ISavableDocument savable){
      JMenuBar menuBar = new JMenuBar();
      fileMenu = new FileMenu(isApplet, savable, frame);
      menuBar.add(fileMenu);
      JMenu smenu = new SettingsMenu(isApplet, settingsManager);
      initialiseSettingsMenu(smenu);
      menuBar.add(smenu);
      return menuBar;
   }

   private static void initialiseSettingsMenu(JMenu smenu){
      settingsManager.registerColorSetting(settingKeyBgColour, Color.WHITE);
      settingsManager.registerColorSetting(settingKeyGridColour, Color.LIGHT_GRAY);
      settingsManager.registerColorSetting(settingKeyEdgeColour, Color.BLACK);
      settingsManager.registerColorSetting(settingKeyNodeColour, Color.BLACK);
      
      smenu.addSeparator();
      JMenuItem menuItem = new JMenuItem("Edit colours", KeyEvent.VK_C);
      smenu.add(menuItem);
      menuItem.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent arg0) { editColours(); }
      });
   }

   static LOGraphColorOptionsDialog colorOptionsDialog;
   static void editColours(){
      if( colorOptionsDialog==null ) colorOptionsDialog = new LOGraphColorOptionsDialog(frame);
      colorOptionsDialog.showDialog();
      mainPanel.repaint();
   }

   public static void updateTitle(){
      if( frame !=null ){
         frame.setTitle("LightsOutGraph: " + fileMenu.getFileString());
      }
   }

   static void attemptClose(){
      fileMenu.menuExit();
   }
}
