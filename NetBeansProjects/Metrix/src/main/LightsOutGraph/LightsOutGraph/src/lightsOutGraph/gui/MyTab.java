package lightsOutGraph.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import lightsOutGraph.graphdata.Puzzle;
public abstract class MyTab extends JPanel
{
	protected Puzzle puz=null;
	public void setPuzzle(Puzzle p){
		puz=p;
		init();
	}
	abstract protected void init();
	abstract protected void exit();

   abstract public void initColours();

	private String _label;
	public String getLabel(){ return _label; }
	
   private String _toolTip;
   public String getToolTip(){ return _toolTip; }
   
	protected MyTab(String label, String toolTip){
      super(new BorderLayout());
	   _label = label;
	   _toolTip = toolTip;
	}
}