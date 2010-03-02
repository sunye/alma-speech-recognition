package fr.alma.asr.gui;
import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeListener;

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
public class ToolBarPanel extends javax.swing.JPanel {
	private JPanel jPanelRecord;
	private JLabel labelRecord ;
	private boolean onRec = false;

	private JPanel jPanelEdit;
	private JButton jLabelOpen;
	private JButton jLabelSave;

	private ImageIcon micOn = new ImageIcon("img/micOnWhite.png");
	private ImageIcon micOff = new ImageIcon("img/micOff.png");
	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	
	public ToolBarPanel() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			{
				jPanelRecord = new JPanel();
				FlowLayout jPanelButtonLayout = new FlowLayout();
				jPanelButtonLayout.setAlignment(FlowLayout.CENTER);
				jPanelRecord.setLayout(jPanelButtonLayout);
				this.add(jPanelRecord, BorderLayout.CENTER);
				{
					final JToggleButton rec = new JToggleButton(micOff);
					
					jPanelRecord.add(rec);
					rec.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							onRec=!onRec;
							if(onRec)
								rec.setIcon(micOn);
							else
								rec.setIcon(micOff);
						}
					});
				}
			}
			{
				jPanelEdit = new JPanel();
				this.add(jPanelEdit, BorderLayout.WEST);
				{
					jLabelSave = new JButton();
					ImageIcon recordImage = new ImageIcon("img/pdf.png");
					jLabelSave.setIcon(recordImage);
					jPanelEdit.add(jLabelSave);
				}
				{
					jLabelOpen = new JButton();
					jPanelEdit.add(jLabelOpen);
					ImageIcon recordImage = new ImageIcon("img/print.png");
					jLabelOpen.setIcon(recordImage);
					jPanelEdit.add(jLabelSave);				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
