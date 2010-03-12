package fr.alma.asr.gui;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;

public class WorkPanel extends JPanel {

	private JSplitPane jSplitPane1;
	private EditPanel editPanel;
	private ViewPanel viewPanel;

	private MainWindow mainWindow;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame a = new JFrame();
		WorkPanel panel = new WorkPanel(null);
		panel.setVisible(true);
		a.add(panel);
		a.setVisible(true);
	}

	/**
	 * @param mainWindow
	 */
	public WorkPanel(MainWindow mainWindow) {
		super();
		this.mainWindow = mainWindow;
		initGUI();

	}

	/**
	 * Main method which build interface
 
	 */
	private void initGUI() {
		try {
			BoxLayout thisLayout = new BoxLayout(this,
					javax.swing.BoxLayout.X_AXIS);
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
					viewPanel = ViewPanel.getViewPanel(mainWindow);
					addViewPanel(viewPanel);
				}
				this.addComponentListener(new ComponentAdapter() {

					@Override
					//Displays the single instance of view pannel in the work pannel
					public void componentShown(ComponentEvent e) {
						WorkPanel.this.addViewPanel(ViewPanel.getViewPanel(mainWindow));
					}

					@Override
					public void componentHidden(ComponentEvent e) {
						// DO NOTHING
					}

				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 
	 * @return JTextPane textPane
	 */
	public JTextPane getWorkTextPane() {
		return this.editPanel.getTextPane();
	}
	
	
	/**
	 * @param jPanel
	 */
	public void addViewPanel(JPanel jPanel) {
		jSplitPane1.add(viewPanel, JSplitPane.LEFT);
	}

}
