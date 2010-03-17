package fr.alma.asr.gui;

import java.util.logging.Level;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

/**
 * @author Damien lévin
 *
 */
public final class ViewPanel extends javax.swing.JPanel {


	private static ViewPanel viewPanelInstance;

	private JTextPane textArea;
	private JScrollPane jScrollPane1;

	/**
	 * @param mainWindow
	 */
	private ViewPanel(MainWindow mainWindow) {
		super();
		initGUI();
	}

	/**
	 * @return
	 */
	public JTextPane getViewTextPane() {
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
					textArea = new JTextPane();
					jScrollPane1.setViewportView(textArea);
					textArea.setEditable(false);
				}
			}
		} catch (Exception e) {
			Controleur.printLog(Level.INFO, e.getMessage());
		}
	}
	
	/**
	 * 
	 * @return JTextPane textPane
	 */
	public JTextPane getTextArea() {
		return textArea;
	}
	
	/**
	 * 
	 * @return String formated Text of the pane
	 */
	public String getFormatedText(){
		return textArea.getText();
	}

}
