package lightsOutGraph.gui;
/*
*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JTextField;

import lightsOutGraph.graphdata.Puzzle;


public final class TabPlay extends MyTab
	implements ActionListener
{
	private GraphDisplayPanel playPanel;
	private JButton clearButton,mixButton,solveButton,editButton;
	private JLabel status;
   private JTextField description = new JTextField();
	private JCheckBox hideArrowBox, hideReflexBox, hideSolutionBox;
	private boolean editMode = false;

	public TabPlay(){
      super("Play", "Play/solve the graph.");

      add(description, BorderLayout.NORTH); 
      description.setEditable(false);

      playPanel = new GraphDisplayPanel();
		add(playPanel);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));
		// create buttons
		clearButton = new JButton("Reset");
		mixButton = new JButton("Mix");
		solveButton = new JButton("Solve");
		editButton = new JButton("Edit");
		// add buttons to panel
		buttonPanel.add(clearButton);
		buttonPanel.add(mixButton);
		buttonPanel.add(solveButton);
		buttonPanel.add(editButton);
		// listen to buttons
		clearButton.addActionListener(this);
		mixButton.addActionListener(this);
		solveButton.addActionListener(this);
		editButton.addActionListener(this);

		JPanel boxPanel = new JPanel();
		boxPanel.setLayout(new BoxLayout(boxPanel,BoxLayout.Y_AXIS));
		boxPanel.add(Box.createVerticalStrut(10));
		// create boxes
		hideArrowBox = new JCheckBox("Hide Arrows",true);
		hideReflexBox = new JCheckBox("Hide Loops",true);
		hideSolutionBox = new JCheckBox("Hide Solution",true);
		// set box states in play panel
		playPanel.setHideArrows(true);
		playPanel.setHideReflex(true);
		playPanel.setHideSolution(true);
		// add boxes to panel
		boxPanel.add(hideArrowBox);
		boxPanel.add(hideReflexBox);
		boxPanel.add(hideSolutionBox);
		// listen to boxes
		hideArrowBox.addActionListener(this);
		hideReflexBox.addActionListener(this);
		hideSolutionBox.addActionListener(this);

		status = new JLabel(" ");
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));
		rightPanel.add(Box.createVerticalStrut(10));
		rightPanel.add(buttonPanel);
		rightPanel.add(status);

		JPanel southPanel = new JPanel();
		southPanel.setLayout(new BoxLayout(southPanel,BoxLayout.X_AXIS));
		southPanel.add(boxPanel);
		southPanel.add(rightPanel);
		add(southPanel, BorderLayout.SOUTH);
	}

	protected void init(){
		clearButton.setEnabled(false);
		mixButton.setEnabled(false);
		solveButton.setEnabled(false);
		editButton.setEnabled(false);
		playPanel.setGraph(puz.getGraph());
		playPanel.setBackground(getBackground());
		playPanel.setHideSolution(true);
      description.setText(puz.getDescription());
		puz.setActionListener(this);
		// initialise
      setStatus(null,false);
		puz.startAnalysing();
	}
	public void initColours(){
      Color bgColor = LightsOutGraph.settingsManager.getColorSetting(LightsOutGraph.settingKeyBgColour);
      Color edgeColor = LightsOutGraph.settingsManager.getColorSetting(LightsOutGraph.settingKeyEdgeColour);
      Color nodeColor = LightsOutGraph.settingsManager.getColorSetting(LightsOutGraph.settingKeyNodeColour);
      Color gridColor = LightsOutGraph.settingsManager.getColorSetting(LightsOutGraph.settingKeyGridColour);
	   playPanel.setColors(bgColor, edgeColor, nodeColor, gridColor);	   
	}
	private void setStatus(String t, boolean s){
		status.setText(t==null?" ":t);
		clearButton.setEnabled(s);
		mixButton.setEnabled(s);
		solveButton.setEnabled(s);
		editButton.setEnabled(s);
		editButton.setText(editMode ? "Play" : "Edit");
	}

	protected void exit(){
      puz.setActionListener(null);
	}

	public void actionPerformed(ActionEvent ae){
		if(ae.getSource() == clearButton ){
			// clear state
			clearState();
			setStatus(null,true);
         hideSolutionBox.setSelected(true);
         playPanel.setHideSolution(true);
		}else if(ae.getSource() == solveButton ){
		   puz.startSolving(true);
		}else if(ae.getSource() == mixButton ){
			mixState();
			setStatus(null,true);
		}else if(ae.getSource() == editButton ){
			editMode = ! editMode;
			playPanel.setEditMode( editMode ? GraphDisplayPanel.EDITMODE : GraphDisplayPanel.PLAYMODE );
			setStatus(null,true);
		}else if(ae.getSource() == hideArrowBox ){
			playPanel.setHideArrows(hideArrowBox.isSelected());
		}else if(ae.getSource() == hideReflexBox ){
			playPanel.setHideReflex(hideReflexBox.isSelected());
		}else if(ae.getSource() == hideSolutionBox ){
			playPanel.setHideSolution(hideSolutionBox.isSelected());
		}else if(ae.getSource() == puz ){
		   switch(ae.getID()){
         case Puzzle.STARTANALYSIS:
            setStatus("Starting analysis...", false);
            break;
         case Puzzle.INITIALISEDANALYSIS:
            setStatus("Matrix initialised...", false);
            break;
         case Puzzle.ENDANALYSIS:
            setStatus("Analysis complete.", true);
            break;

         case Puzzle.INITIALISEDSOLVE:
            setStatus("Matrix initialised", false);
            break;
         case Puzzle.ENDINVERSION:
            setStatus("Matrix inversion done", false);
            break;
         case Puzzle.UNSOLVABLE:
            setStatus("Position is not solvable.", true);
            hideSolutionBox.setSelected(true);
            playPanel.setHideSolution(true);
            break;
         case Puzzle.STARTOPTIMISE:
            setStatus("Trying to optimise solution.", false);
            break;
         case Puzzle.ENDOPTIMISE:
            setStatus("Optimal solution found.", true);
            hideSolutionBox.setSelected(false);
            playPanel.setHideSolution(false);
            break;
         case Puzzle.SOLVABLE:
            setStatus("Solution found.", true);
            hideSolutionBox.setSelected(false);
            playPanel.setHideSolution(false);
            break;
         default:
		   }
		}else if(ae.getSource() == playPanel ){
		}
	}
	
	
	private void clearState(){
		puz.getGraph().clearState();
		playPanel.repaint();
	}
	private void mixState(){
		puz.getGraph().mixState();
		playPanel.repaint();
	}	
}