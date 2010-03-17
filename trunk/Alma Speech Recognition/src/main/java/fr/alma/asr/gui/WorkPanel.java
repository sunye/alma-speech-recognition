package fr.alma.asr.gui;
import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.logging.Level;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

import fr.alma.asr.entities.Lesson;

public class WorkPanel extends JPanel {

	private JSplitPane jSplitPane1;
	private EditPanel editPanel;
	private ViewPanel viewPanel;
	private MainWindow mainWindow;
	
	public static int panelWidth = 800;
	public static int panelHeight = 600;

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
		this.setMinimumSize(new Dimension(panelWidth,panelHeight));

	}

	/**
	 * Main method which build interface. 
	 */
	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			{
				jSplitPane1 = new JSplitPane();
				this.add(jSplitPane1, BorderLayout.CENTER);
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
			Controleur.printLog(Level.INFO, e.getMessage());
		}
	}

	
	/**
	 * 
	 * @return JTextPane textPane
	 */
	public EditPanel getEditPanel() {
		return this.editPanel;
	}
	
	/**
	 * 
	 * @return JTextPane textPane
	 */
	public ViewPanel getViewPanel() {
		return this.viewPanel;
	}
	
	/**
	 * @param jPanel
	 */
	public void addViewPanel(JPanel jPanel) {
		jSplitPane1.add(viewPanel, JSplitPane.LEFT);
	}
	
	/**
	 * Set the content of the workpanel
	 * @param lesson lesson
	 */
	public void setLesson(Lesson lesson){
		try {
			getViewPanel().getTextArea().getDocument().insertString(0, lesson.getDataProf(), null);
			getEditPanel().getTextPane().getDocument().insertString(0,lesson.getDataEleve(), null);

		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
}
