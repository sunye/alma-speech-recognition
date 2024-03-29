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
	
	/**
	 * @param mainWindow
	 */
	public WorkPanel(MainWindow mainWindow) {
		super();
		this.mainWindow = mainWindow;
		initGUI();

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
				jSplitPane1.setBorder(null);
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
						jSplitPane1.setDividerLocation(0.5);
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


		 getViewPanel().setText(lesson.getDataProf());
		 getEditPanel().setText(lesson.getDataEleve());


		}
}
