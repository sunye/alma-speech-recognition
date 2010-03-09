package fr.alma.asr.gui;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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
	
	private MainWindow mainWindow;

	public WorkPanel(MainWindow mainWindow) {
		super();
		this.mainWindow = mainWindow;
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
					editPannel = new EditPanel(mainWindow);
					jSplitPane1.add(editPannel, JSplitPane.RIGHT);
				}
				{
					viewPannel = ViewPanel.getViewPanel();
					jSplitPane1.add(viewPannel, JSplitPane.LEFT);
				}
				this.addComponentListener(new ComponentAdapter() {
					@Override
					public void componentShown(ComponentEvent e) {
						WorkPanel.this.add(ViewPanel.getViewPanel().getViewPanel());
					}
					@Override
					public void componentHidden(ComponentEvent e) {
						WorkPanel.this.remove(ViewPanel.getViewPanel().getViewPanel());

					}
					
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
