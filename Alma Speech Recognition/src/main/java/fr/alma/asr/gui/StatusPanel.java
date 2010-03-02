package fr.alma.asr.gui;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

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
public class StatusPanel extends javax.swing.JPanel {
	private JLabel jLabelStatus;

	
	public StatusPanel() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			{
				jLabelStatus = new JLabel();
				this.add(jLabelStatus, BorderLayout.EAST);
				jLabelStatus.setText("Application succesfully loaded.");
			}
			this.setBorder(BorderFactory.createMatteBorder(1,0, 0, 0,Color.DARK_GRAY));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * display text in status bar
	 * @param text
	 */
	public void setStatus(String text){
		this.jLabelStatus.setText(text);
	}

}
