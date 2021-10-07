package lightsOutGraph.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import lightsOutGraph.graphdata.Edge;
import lightsOutGraph.graphdata.Graph;
import lightsOutGraph.graphdata.Node;

public final class TabBoard extends MyTab
	implements ActionListener, ChangeListener, DocumentListener
{
	private JButton clearButton, addShortButton, delLongButton, centreButton, reflexButton;
	private JSpinner colourSpinner;
	private JCheckBox symBox, reflexBox, hideArrowBox, hideReflexBox, snapGridBox;
   private JTextField description = new JTextField();
	private GraphEditPanel editPanel;

	public TabBoard(){
      super("Edit", "Edit the board");

      add(description, BorderLayout.NORTH);
      description.getDocument().addDocumentListener(this);

      editPanel = new GraphEditPanel(); add(editPanel);
		editPanel.setForeground(Color.BLACK);

		clearButton = new JButton("Clear");
		clearButton.addActionListener(this);
      centreButton = new JButton("Centralise view");
      centreButton.addActionListener(this);
      addShortButton = new JButton("Add Shortest Edge");
		addShortButton.addActionListener(this);
		delLongButton = new JButton("Remove Longest Edge");
		delLongButton.addActionListener(this);
      reflexButton = new JButton("Make all (un)reflexive");
      reflexButton.addActionListener(this);
		colourSpinner = new JSpinner(new SpinnerNumberModel(2,2,9,1));
		colourSpinner.addChangeListener(this);
	   JPanel spinnerPanel = new JPanel();
	   spinnerPanel.add(new JLabel("Number of colours:"));
	   spinnerPanel.add(colourSpinner);

		symBox = new JCheckBox("Symmetric",true);
		reflexBox = new JCheckBox("Reflexive",true);
		hideArrowBox = new JCheckBox("Hide Arrows",true);
		hideReflexBox = new JCheckBox("Hide Loops",true);
		snapGridBox = new JCheckBox("Snap To Grid",true);
		editPanel.setSymmetric(true);
		editPanel.setReflexive(true);
		editPanel.setHideArrows(true);
		editPanel.setHideReflex(true);
		editPanel.setSnapToGrid(true);
		JPanel boxPanel = new JPanel();
		boxPanel.setLayout(new BoxLayout(boxPanel,BoxLayout.Y_AXIS));
		boxPanel.add(symBox);
		boxPanel.add(reflexBox);
		boxPanel.add(hideArrowBox);
		boxPanel.add(hideReflexBox);
		boxPanel.add(snapGridBox);
		symBox.addActionListener(this);
		reflexBox.addActionListener(this);
		hideArrowBox.addActionListener(this);
		hideReflexBox.addActionListener(this);
		snapGridBox.addActionListener(this);

		JPanel buttonPanel = new JPanel(new GridLayout(0,2));
      buttonPanel.add(centreButton);
      buttonPanel.add(clearButton);
		buttonPanel.add(addShortButton);
      buttonPanel.add(delLongButton);
      buttonPanel.add(reflexButton);
		buttonPanel.add(spinnerPanel);

      JPanel instructPanel = new JPanel();
      instructPanel.setLayout(new BoxLayout(instructPanel,BoxLayout.Y_AXIS));
      instructPanel.add(new JLabel("Ctrl-click: Add Node"));
      instructPanel.add(new JLabel("Alt-click: Delete Node/Edge"));
      instructPanel.add(new JLabel("Drag: Move Node"));
      instructPanel.add(new JLabel("Shift-Drag: Create Edge"));

		JPanel controlPanel = new JPanel();
      controlPanel.add(instructPanel);
      controlPanel.add(boxPanel);
      controlPanel.add(buttonPanel);

		add(controlPanel, BorderLayout.SOUTH);
	}
	protected void init(){
		// get data from puzzle and store in panel
		editPanel.setGraph(puz.getGraph());
      description.setText(puz.getDescription());
	}
	protected void exit(){}
   public void initColours(){
      Color bgColor = LightsOutGraph.settingsManager.getColorSetting(LightsOutGraph.settingKeyBgColour);
      Color edgeColor = LightsOutGraph.settingsManager.getColorSetting(LightsOutGraph.settingKeyEdgeColour);
      Color nodeColor = LightsOutGraph.settingsManager.getColorSetting(LightsOutGraph.settingKeyNodeColour);
      Color gridColor = LightsOutGraph.settingsManager.getColorSetting(LightsOutGraph.settingKeyGridColour);
      editPanel.setColors(bgColor, edgeColor, nodeColor, gridColor);    
   }
	public void actionPerformed(ActionEvent ae){
		if(ae.getSource() == clearButton ){
			puz.getGraph().clear();
			editPanel.repaint();
      }else if(ae.getSource() == centreButton ){
         editPanel.rescale();
		}else if(ae.getSource() == addShortButton ){
			addShortest();
      }else if(ae.getSource() == delLongButton ){
         delLongest();
      }else if(ae.getSource() == reflexButton ){
         doReflex();
		}else if(ae.getSource() == symBox ){
			editPanel.setSymmetric(symBox.isSelected());
		}else if(ae.getSource() == reflexBox ){
			editPanel.setReflexive(reflexBox.isSelected());
		}else if(ae.getSource() == hideArrowBox ){
			editPanel.setHideArrows(hideArrowBox.isSelected());
		}else if(ae.getSource() == hideReflexBox ){
			editPanel.setHideReflex(hideReflexBox.isSelected());
		}else if(ae.getSource() == snapGridBox ){
			editPanel.setSnapToGrid(snapGridBox.isSelected());
		}
	}
   public void stateChanged(ChangeEvent ce) {
      puz.getGraph().setNumStates((Integer)colourSpinner.getValue());
   }


	private void addShortest(){
		Graph graph = puz.getGraph();
		Edge shortest = null;
		double len = -1;
		for( Node n1 : graph.getNodes()){
			for( Node n2 : graph.getNodes()){
				if( n1!=n2 ){
				   double d = n1.squaredDistanceTo(n2);
					if( d<len || len<0 ){
						boolean found = false;
						for( Edge e2 : graph.getEdges()){
							if( e2.hasNodes(n1, n2) ){
								found=true;
								break;
							}
						}
						if( !found ){
							len = d;
							shortest = new Edge(n1,n2);
						}
					}
				}
			}
		}
		if( shortest!=null ){
			shortest.valence1 = 1;
			shortest.valence2 = 1;
			graph.addEdge(shortest);
			repaint();
		}
	}
	private void delLongest(){
		Edge longest = null;
		double len = -1;
		for( Edge e : puz.getGraph().getEdges()){
			Node n1 = e.getNode1();
			Node n2 = e.getNode2();
			if( n1!=n2 ){
				double d = n1.squaredDistanceTo(n2);
				if( d>len ){
					len = d;
					longest = e;
				}
			}
		}
		if( len>=0 ){
			puz.getGraph().deleteEdge(longest);
			repaint();
		}
	}

	private void doReflex(){
	   // see if there is a non-reflexive node
	   Graph g = puz.getGraph();
      boolean wantReflex = g.getNumNodes() != g.getNumReflexiveNodes();
      
      for( Node n : g.getNodes()){
         boolean foundEdge = false;
         for( Edge e : n.getEdges() ){
            if( e.getNode1() == e.getNode2() ){
               foundEdge = true;
               if( wantReflex ){
                  e.valence1 = 1;
                  e.valence2 = 0;
               }else{
                  g.deleteEdge(e);
               }
               break;
            }
         }
         if( !foundEdge && wantReflex ){
            g.addEdge(n, n, 1, 0);
         }
      }
      repaint();
	}
	
   public void changedUpdate(DocumentEvent arg0) {
      puz.setDescription(description.getText());
   }
   public void insertUpdate(DocumentEvent arg0) {
      puz.setDescription(description.getText());
   }
   public void removeUpdate(DocumentEvent arg0) {
      puz.setDescription(description.getText());
   }
}