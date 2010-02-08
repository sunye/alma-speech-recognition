package fr.alma.gui;
import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;

import javax.swing.ImageIcon;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
public class ToolBarPannel extends javax.swing.JPanel {
	private JPanel jPanelButton;
	private JPanel jPanelStatus;
	private JLabel labelRecord ;	
	private JLabel jLabelStatus;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	
	public ToolBarPannel() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			{
				jPanelButton = new JPanel();
				FlowLayout jPanelButtonLayout = new FlowLayout();
				jPanelButtonLayout.setAlignment(FlowLayout.CENTER);
				jPanelButton.setLayout(jPanelButtonLayout);
				this.add(jPanelButton, BorderLayout.CENTER);
				{
					JLabel labelRecord = new JLabel();
					ImageIcon recordImage = new ImageIcon("src/main/resources/text-speak.png");
					labelRecord.setIcon(recordImage);
					jPanelButton.add(labelRecord);
		
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
