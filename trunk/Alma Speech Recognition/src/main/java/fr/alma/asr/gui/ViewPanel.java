package fr.alma.asr.gui;

import java.awt.Dimension;
import java.util.logging.Level;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.Document;

/**
 * @author Damien lévin
 *
 */
public final class ViewPanel extends javax.swing.JPanel {


	private static ViewPanel viewPanelInstance;

	private JTextPane textPane;
	private Document document;
	private DefaultEditorKit editorKit;

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
		return this.textPane;
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
					textPane = new JTextPane();
					
					jScrollPane1.setViewportView(textPane);
					textPane.setEditable(false);
					editorKit = new DefaultEditorKit();
					document = editorKit.createDefaultDocument();
					textPane.setEditorKit(editorKit);
					textPane.setDocument(document);
				}
			}
			this.setMinimumSize(new Dimension(500,200));
		} catch (Exception e) {
			Controleur.printLog(Level.INFO, e.getMessage());
		}
	}
	
	/**
	 * 
	 * @return JTextPane textPane
	 */
	public JTextPane getTextArea() {
		return textPane;
	}
	
	/**
	 * 
	 * @return String formated Text of the pane
	 */
	public String getFormatedText(){
		return textPane.getText();
	}
	
	public void setText(String lesson){
	
		try {
			if (lesson!=null){
				document.insertString(0, lesson, null);
			}
		} catch (BadLocationException e) {
			Controleur.printLog(Level.INFO, e.getLocalizedMessage());
		}
	}
	
	public void addText(String lesson){
		try {
			if (lesson!=null){
				
				document.insertString(document.getLength(), lesson, null);		
				textPane.setText(document.getText(document.getLength(),document.getLength()));
			}
		} catch (BadLocationException e) {
			Controleur.printLog(Level.INFO, e.getLocalizedMessage());
		}
	}
	
	
	
}
