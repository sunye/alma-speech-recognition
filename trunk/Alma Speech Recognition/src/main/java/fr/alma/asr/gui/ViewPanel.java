package fr.alma.asr.gui;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author damien
 *
 */
public class ViewPanel extends javax.swing.JPanel {


	private static ViewPanel viewPanelInstance;


	private MainWindow mainWindow;
	
	private JTextArea textArea;
	private JScrollPane jScrollPane1;

	/**
	 * @param mainWindow
	 */
	private ViewPanel(MainWindow mainWindow) {
		super();
		this.mainWindow = mainWindow;
		initGUI();
	}

	/**
	 * @return
	 */
	public JTextArea getViewTextArea() {
		return this.textArea;
	}

	/**
	 * @param mainWindow
	 * @return
	 */
	public static ViewPanel getViewPanel(MainWindow mainWindow) {
		if (viewPanelInstance == null) {
			viewPanelInstance = new ViewPanel(mainWindow);
		}
		return viewPanelInstance;
	}
	
	/**
	 * @return
	 */
	public static ViewPanel getViewPanel() {
		return viewPanelInstance;
	}

	/**
	 * Main method which build interface
	 */
	private void initGUI() {
		try {
			BoxLayout thisLayout = new BoxLayout(this,
					javax.swing.BoxLayout.X_AXIS);
			this.setLayout(thisLayout);
			this.setBorder(BorderFactory
					.createTitledBorder("Visualisation du cours"));
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				{
					textArea = new JTextArea();
					jScrollPane1.setViewportView(textArea);
					textArea.setEditable(false);
				}
			}
		this.setMinimumSize(new java.awt.Dimension(400, 200));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
