package lightsOutGraph.gui;
/*
*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.Box;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import lightsOutGraph.graphdata.Graph;
import lightsOutGraph.graphdata.Puzzle;


public final class TabAnalyse extends MyTab
	implements ActionListener, ChangeListener
{
	private GraphDisplayPanel showgraphPanel;
   private JTextField description = new JTextField();
	private JLabel status;
	private JCheckBox hideArrowBox, hideReflexBox;
	private JSlider quietSlider;
	private JTextArea propertiesText;
   private int quietPat = 0;
   private int numQuiet = 0;

	public TabAnalyse(){
	   super("Analyse", "Analyse the game");

	   add(description, BorderLayout.NORTH);
	   description.setEditable(false);
	   
		showgraphPanel = new GraphDisplayPanel();
		add(showgraphPanel);

		Box sliderPanel = Box.createVerticalBox();
		// create buttons
		quietSlider = new JSlider(0,0);
		// add buttons to panel
		sliderPanel.add(quietSlider);
		// listen to buttons
		quietSlider.addChangeListener(this);

		Box boxPanel = Box.createVerticalBox();
		//boxPanel.add(Box.createVerticalStrut(10));
		// create boxes
		hideArrowBox = new JCheckBox("Hide Arrows",true);
		hideReflexBox = new JCheckBox("Hide Loops",true);
		// set box states in play panel
		showgraphPanel.setHideArrows(true);
		showgraphPanel.setHideReflex(true);

		// add boxes to panel
		boxPanel.add(hideArrowBox);
		boxPanel.add(hideReflexBox);
		// listen to boxes
		hideArrowBox.addActionListener(this);
		hideReflexBox.addActionListener(this);

		status = new JLabel(" ");
		Box rightPanel = Box.createVerticalBox();
		//rightPanel.add(Box.createVerticalStrut(10));
		rightPanel.add(sliderPanel);
		rightPanel.add(status);
		
		Box topPanel = Box.createHorizontalBox();
		topPanel.add(boxPanel);
		topPanel.add(rightPanel);

      propertiesText = new JTextArea();
      propertiesText.setEditable(false);
		
      Box southPanel = Box.createVerticalBox();		
      southPanel.add(topPanel);
      southPanel.add(propertiesText);

		add(southPanel, BorderLayout.SOUTH);
	}

	protected void init(){
		showgraphPanel.setGraph(puz.getGraph());
		showgraphPanel.setBackground(getBackground());
		showgraphPanel.setHideSolution(true);
		showgraphPanel.setEditMode(GraphDisplayPanel.LOCKMODE);
		description.setText(puz.getDescription());
		puz.setActionListener(this);
		// initialise
      setStatus(null,false);
		puz.startAnalysing();
	}
	private void setStatus(String t, boolean s){
		status.setText(t==null?" ":t);
		quietSlider.setEnabled(s);
	}

	protected void exit(){
      puz.setActionListener(null);
	}
   public void initColours(){
      Color bgColor = LightsOutGraph.settingsManager.getColorSetting(LightsOutGraph.settingKeyBgColour);
      Color edgeColor = LightsOutGraph.settingsManager.getColorSetting(LightsOutGraph.settingKeyEdgeColour);
      Color nodeColor = LightsOutGraph.settingsManager.getColorSetting(LightsOutGraph.settingKeyNodeColour);
      Color gridColor = LightsOutGraph.settingsManager.getColorSetting(LightsOutGraph.settingKeyGridColour);
      showgraphPanel.setColors(bgColor, edgeColor, nodeColor, gridColor);    
   }
	public void actionPerformed(ActionEvent ae){
		if(ae.getSource() == hideArrowBox ){
			showgraphPanel.setHideArrows(hideArrowBox.isSelected());
		}else if(ae.getSource() == hideReflexBox ){
			showgraphPanel.setHideReflex(hideReflexBox.isSelected());
		}else if(ae.getSource() == puz ){
         switch(ae.getID()){
         case Puzzle.STARTANALYSIS:
            setStatus("Starting analysis...", false);
            break;
         case Puzzle.INITIALISEDANALYSIS:
            setStatus("Matrix initialised...", false);
            break;
         case Puzzle.ENDANALYSIS:
            setStatus(null, true);
            generateReport();
            break;
         default:
         }
		}
	}
	private void generateReport(){
      numQuiet = puz.getNumQuiet();
      setSlider(numQuiet);
      
      String s = "";
      Graph graph = puz.getGraph();
      int nn = graph.getNumNodes();
      int nnr = graph.getNumReflexiveNodes();
      s += "Graph has "+nn+(nn==1 ? " node" : " nodes");
      if( nn == nnr ){
         s += " (all reflexive)";
      }else if( nnr == 0 ){
         s += " (all non-reflexive)";
      }else{
         s += " ("+nnr+" reflexive, "+(nn-nnr)+" non-reflexive)";
      }
      s += "\n";
      int ne = graph.getNumEdges()-nnr;
      int nes = graph.getNumSymmetricEdges();
      s += "and "+ne+(ne==1 ? " edge" : " edges");
      if( ne == nes ){
         s += " (all symmetric)";
      }else if( nes == 0 ){
         s += " (all non-symmetric)";
      }else{
         s += " ("+nes+" symmetric, "+(ne-nes)+" non-symmetric)";
      }
      s += "\n";
      
      int nlty = puz.getNullity();
      int rank = nn - nlty;
      int ns = graph.getNumStates();
      BigInteger nq = BigInteger.valueOf(ns).pow(nlty);
      s += "Matrix has rank "+rank+" and nullity "+nlty+", giving\n";
      s += ns+"^"+nlty+" = " +nq+" null-space patterns.";
      
      propertiesText.setText(s);
}
	private void setSlider(int max){
      quietSlider.setMaximum(max-1);
      quietSlider.setMinimum(0);
      quietSlider.setSnapToTicks(false);
      quietSlider.setPaintTicks(true);
      int intv = max<10 ? 1 :
                 max<50 ? 5 :
                 max<100? 10 :
                 max<500? 50 : max-1;
      quietSlider.setLabelTable( quietSlider.createStandardLabels(intv, 0) );
      quietSlider.setMajorTickSpacing(10);
      quietSlider.setMinorTickSpacing(5);
      quietSlider.setPaintLabels(true);
      quietSlider.setValue(0);
	}

	
   public void stateChanged(ChangeEvent ce) {
      int q = quietSlider.getValue();
      if( q!=quietPat ){
         quietPat = q;
         puz.setQuiet(quietPat);
         setStatus(null,true);
         showgraphPanel.setHideSolution(false);
      }
   }
}