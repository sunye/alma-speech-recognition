package fr.alma.asr.gui;

import java.awt.Dimension;

import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

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
public class MenuTextArea extends javax.swing.JPopupMenu {
	private JMenuItem jMenuItemCouper;
	private JMenuItem jMenuItemCopier;	
	private JMenuItem jMenuItemColler;	

	private JSeparator jSeparator1;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	
	public MenuTextArea() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				jMenuItemCouper = new JMenuItem();
				this.add(jMenuItemCouper);
				jMenuItemCouper.setText("Couper");
			}
			{
				jMenuItemCopier = new JMenuItem();
				this.add(jMenuItemCopier);
				jMenuItemCopier.setText("Copier");
			}
			{
				jMenuItemColler = new JMenuItem();
				this.add(jMenuItemColler);
				jMenuItemColler.setText("Coller");
			}			
			{
				jSeparator1 = new JSeparator();
				this.add(jSeparator1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JMenuItem getjMenuItemCouper() {
		return jMenuItemCouper;
	}

	public JMenuItem getjMenuItemCopier() {
		return jMenuItemCopier;
	}

	public JMenuItem getjMenuItemColler() {
		return jMenuItemColler;
	}

	public JSeparator getjSeparator1() {
		return jSeparator1;
	}
	
	
}
