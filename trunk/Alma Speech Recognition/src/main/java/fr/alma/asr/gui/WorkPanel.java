package fr.alma.asr.gui;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;

import javax.swing.JPanel;
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
	private EditPanel editPanel;
	private ViewPanel viewPanel;
	
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
					editPanel = new EditPanel(mainWindow);
					jSplitPane1.add(editPanel, JSplitPane.RIGHT);
				}
				{
					viewPanel = ViewPanel.getViewPanel();
					addViewPanel(viewPanel);
				}
				this.addComponentListener(new ComponentAdapter() {
					
					
					@Override
					public void componentShown(ComponentEvent e) {
						WorkPanel.this.addViewPanel(ViewPanel.getViewPanel());
						System.out.println(e);
					}
					
					@Override
					public void componentHidden(ComponentEvent e) {
					//	WorkPanel.this.addViewPanel(ViewPanel.getViewPanel());

					}
					
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addViewPanel(JPanel jPanel){
		jSplitPane1.add(viewPanel, JSplitPane.LEFT);	
	}
	
}
