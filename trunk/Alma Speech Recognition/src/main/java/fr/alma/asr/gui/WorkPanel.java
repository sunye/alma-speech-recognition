package fr.alma.asr.gui;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JSplitPane;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class WorkPanel extends javax.swing.JPanel {
	private JSplitPane jSplitPane1;
	private EditPanel editPannel;
	private ViewPanel viewPannel;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new WorkPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public WorkPanel() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			BoxLayout thisLayout = new BoxLayout(this, javax.swing.BoxLayout.X_AXIS);
			this.setLayout(thisLayout);
			setPreferredSize(new Dimension(400, 300));
			{
				jSplitPane1 = new JSplitPane();
				this.add(jSplitPane1);
				{
					editPannel = new EditPanel();
					jSplitPane1.add(editPannel, JSplitPane.RIGHT);
				}
				{
					viewPannel = new ViewPanel();
					jSplitPane1.add(viewPannel, JSplitPane.LEFT);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}