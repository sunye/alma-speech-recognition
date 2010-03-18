package fr.alma.asr.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.logging.Level;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import fr.alma.asr.gui.listener.MenuItemListener;
import fr.alma.asr.gui.tabbedpane.TabContainer;
import fr.alma.asr.gui.tree.PanelTreeCours;
import fr.alma.asr.gui.tree.PanelTreePlan;

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

/**
 * Main class for the application Contains method to access all GUI actions
 * 
 * @author Gaëtan Hervouet
 * 
 */
public final class MainWindow extends javax.swing.JFrame {

	public static int widthSize = 800;
	public static int heightSize = 600;
	
	private static final long serialVersionUID = 658652694660147986L;
	private static MainWindow instance;
	{
		// Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager
					.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			Controleur.printLog(Level.WARNING,"Look and feel non supporté.");
		}
	}

	private JMenuItem helpMenuItem;
	private JMenu jMenuHelp;
	private JMenuItem undoMenuItem;
	private JMenuItem redoMenuItem;
	private JMenuItem deleteMenuItem;
	private JMenuItem pasteMenuItem;
	private JMenuItem copyMenuItem;
	private JMenuItem cutMenuItem;
	private JMenu jMenuEdition;
	private JMenuItem closeFileMenuItem;
	private JMenuItem saveAsMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem openFileMenuItem;
	private JMenuItem newFileMenuItem;
	private JMenuItem guiParametersMenuItem;
	private JMenuItem dicoParametersMenuItem;
	private JMenuItem optParametersMenuItem;
	private JMenu jMenuFile;
	private JMenuBar jMenuBar;

	// Panels list
	private TabContainer tabbedPaneHomeWork;
	private JTabbedPane tabbedPanePlanCourses;
	private HomePanel homePanel;
	private ToolBarPanel toolBarPannel;
	private StatusPanel statusPanel;
	private JSplitPane splitPanel;

	// ---------------------------------- Getters and setters
	// ----------------------------------

	public JMenuItem getDeleteMenuItem() {
		return deleteMenuItem;
	}

	public JMenuItem getRedoMenuItem() {
		return redoMenuItem;
	}

	public JMenuItem getUndoMenuItem() {
		return undoMenuItem;
	}

	public JMenuItem getPasteMenuItem() {
		return pasteMenuItem;
	}

	public void setPasteMenuItem(JMenuItem pasteMenuItem) {
		this.pasteMenuItem = pasteMenuItem;
	}

	public JMenuItem getCopyMenuItem() {
		return copyMenuItem;
	}

	public void setCopyMenuItem(JMenuItem copyMenuItem) {
		this.copyMenuItem = copyMenuItem;
	}

	public JMenuItem getCutMenuItem() {
		return cutMenuItem;
	}

	public void setCutMenuItem(JMenuItem cutMenuItem) {
		this.cutMenuItem = cutMenuItem;
	}

	public JMenuItem getSaveAsMenuItem() {
		return saveAsMenuItem;
	}

	public void setSaveAsMenuItem(JMenuItem saveAsMenuItem) {
		this.saveAsMenuItem = saveAsMenuItem;
	}

	public JMenuItem getSaveMenuItem() {
		return saveMenuItem;
	}

	public void setSaveMenuItem(JMenuItem saveMenuItem) {
		this.saveMenuItem = saveMenuItem;
	}

	public JMenuItem getOpenFileMenuItem() {
		return openFileMenuItem;
	}

	public void setOpenFileMenuItem(JMenuItem openFileMenuItem) {
		this.openFileMenuItem = openFileMenuItem;
	}

	public JMenuItem getNewFileMenuItem() {
		return newFileMenuItem;
	}

	public void setNewFileMenuItem(JMenuItem newFileMenuItem) {
		this.newFileMenuItem = newFileMenuItem;
	}

	public TabContainer getTabbedPaneHomeWork() {
		return tabbedPaneHomeWork;
	}

	public JTabbedPane getTabbedPanePlanCourses() {
		return tabbedPanePlanCourses;
	}

	public void setTabbedPanePlanCourses(JTabbedPane tabbedPanePlanCourses) {
		this.tabbedPanePlanCourses = tabbedPanePlanCourses;
	}

	public StatusPanel getStatusPanel() {
		return statusPanel;
	}

	public void setStatusPanel(StatusPanel statusPanel) {
		this.statusPanel = statusPanel;
	}

	/**
	 * Build the toolbar
	 */
	private void initToolbar(){
		
		toolBarPannel = new ToolBarPanel(this);
		getContentPane().add(toolBarPannel, BorderLayout.NORTH);

		jMenuBar = new JMenuBar();
		setJMenuBar(jMenuBar);

		jMenuFile = new JMenu();
		jMenuBar.add(jMenuFile);
		jMenuFile.setText("Fichier");

		newFileMenuItem = new JMenuItem();
		jMenuFile.add(newFileMenuItem);
		newFileMenuItem.setText("Nouveau cours");

		openFileMenuItem = new JMenuItem();
		jMenuFile.add(openFileMenuItem);
		openFileMenuItem.setText("Ouvrir");

		saveMenuItem = new JMenuItem();
		jMenuFile.add(saveMenuItem);
		saveMenuItem.setText("Enregistrer");
		saveMenuItem.addActionListener(new MenuItemListener(this));


		saveAsMenuItem = new JMenuItem();
		jMenuFile.add(saveAsMenuItem);
		saveAsMenuItem.setText("Enregistrer sous...");
		saveAsMenuItem.addActionListener(new MenuItemListener(this));

		jMenuFile.add(new JSeparator());

		closeFileMenuItem = new JMenuItem();
		jMenuFile.add(closeFileMenuItem);
		closeFileMenuItem.setText("Quitter");
		closeFileMenuItem.addActionListener(new MenuItemListener(this));

		jMenuEdition = new JMenu();
		jMenuBar.add(jMenuEdition);
		jMenuEdition.setText("Edition");

		undoMenuItem = new JMenuItem();
		jMenuEdition.add(undoMenuItem);
		undoMenuItem.setText("Annuler");

		redoMenuItem = new JMenuItem();
		jMenuEdition.add(redoMenuItem);
		redoMenuItem.setText("Rétablir");

		jMenuEdition.add(new JSeparator());

		cutMenuItem = new JMenuItem();
		jMenuEdition.add(cutMenuItem);
		cutMenuItem.setText("Couper");

		copyMenuItem = new JMenuItem();
		jMenuEdition.add(copyMenuItem);
		copyMenuItem.setText("Copier");

		pasteMenuItem = new JMenuItem();
		jMenuEdition.add(pasteMenuItem);
		pasteMenuItem.setText("Coller");

		jMenuEdition.add(new JSeparator());

		deleteMenuItem = new JMenuItem();
		jMenuEdition.add(deleteMenuItem);
		deleteMenuItem.setText("Supprimer");

		jMenuEdition.add(new JSeparator());

		guiParametersMenuItem = new JMenuItem();
		jMenuEdition.add(guiParametersMenuItem);
		guiParametersMenuItem.setText("Préférences interface...");
		guiParametersMenuItem.addActionListener(new MenuItemListener(this));

		dicoParametersMenuItem = new JMenuItem();
		jMenuEdition.add(dicoParametersMenuItem);
		dicoParametersMenuItem.setText("Options du dictionnaire...");
		dicoParametersMenuItem.addActionListener(new MenuItemListener(this));

		optParametersMenuItem = new JMenuItem();
		jMenuEdition.add(optParametersMenuItem);
		optParametersMenuItem.setText("Options du moteur...");
		optParametersMenuItem.addActionListener(new MenuItemListener(this));

		jMenuHelp = new JMenu();
		jMenuBar.add(jMenuHelp);
		jMenuHelp.setText("Aide");

		helpMenuItem = new JMenuItem();
		jMenuHelp.add(helpMenuItem);
		helpMenuItem.setText("A propos");

		jMenuHelp.add(new JSeparator());

		helpMenuItem = new JMenuItem();
		jMenuHelp.add(helpMenuItem);
		helpMenuItem.setText("Aide");
		
	}
	
	/**
	 * build Plan,Courses,Work and Home panels
	 */
	private void initPanels(){
		splitPanel = new JSplitPane();
		getContentPane().add(splitPanel, BorderLayout.CENTER);

		tabbedPanePlanCourses = new JTabbedPane();
		splitPanel.add(tabbedPanePlanCourses, JSplitPane.LEFT);
		tabbedPanePlanCourses.setOpaque(true);
		tabbedPanePlanCourses.add("Cours", new PanelTreeCours());
		tabbedPanePlanCourses.setIconAt(0, new ImageIcon(getClass()
				.getResource("/icones/course24.png")));
		tabbedPanePlanCourses.add("Plan", new PanelTreePlan());
		tabbedPanePlanCourses.setIconAt(1, new ImageIcon(getClass()
				.getResource("/icones/doc24.png")));

		tabbedPaneHomeWork = new TabContainer();
		splitPanel.add(tabbedPaneHomeWork, JSplitPane.RIGHT);

		homePanel = new HomePanel();
		tabbedPaneHomeWork.add(homePanel, " Home", new ImageIcon(getClass()
				.getResource("/icones/home24.png")));

		statusPanel = new StatusPanel();
		getContentPane().add(statusPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Main method which build interface
	 */
	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setTitle("Alma Speech Recognition");
			getContentPane().setLayout(thisLayout);
			
			this.addWindowListener(new WindowAdapter() {
				
				@Override
				public void windowClosing(WindowEvent e) {

					Controleur.getInstance().stopEngine();
					Controleur.getInstance().deconnexion();
					System.exit(0);
				}

			});
			// TODO maximized
			// this.setExtendedState(JFrame.MAXIMIZED_BOTH);

			/*----------------------------------TOOLBAR CREATION-------------------------------------------*/			
			initToolbar();
			
			
			/*----------------------------------Plan,Courses,Work,Home CREATION-------------------------------------------*/
			initPanels();
			
			this.setMinimumSize(new Dimension(widthSize, heightSize));

		} catch (Exception e) {
			e.printStackTrace();
			Controleur.printLog(Level.INFO, e.getMessage());
		}
	}

	/**
	 * Add a work panel as a new tab
	 * Set the content of the work panel.
	 * @param name
	 *            name of the module.
	 */
	public void addNewWorkPanel(WorkPanel workPanel,String lesson) {
		tabbedPaneHomeWork.add(workPanel, lesson, new ImageIcon(getClass()
				.getResource("/icones/RSSfolder24.png")));
		tabbedPaneHomeWork.setSelectedComponent(workPanel);
	}

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		Controleur.debutChargement();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {				
				MainWindow inst = MainWindow.getInstance();
				Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
				inst.setLocation((screen.width - inst.getSize().width) / 2,
						(screen.height - inst.getSize().height) / 2);				
				inst.setLocationRelativeTo(null);
				Controleur.chargementTermine();
				inst.setVisible(true);
			}
		});
	}

	/**
	 * Singleton pattern
	 * 
	 * @return Return MainWindow isntance
	 */
	public static MainWindow getInstance() {
		if (instance == null) {
			instance = new MainWindow("Alma Speech Recognition");
		}
		return instance;
	}

	/**
	 * Private constructor
	 * 
	 * @param title
	 *            Frame title
	 */
	private MainWindow(String title) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/icones/appIcon.png")));
		initGUI();
	}

	/**
	 * 
	 * @return JTextPane textPane
	 */
	public JTextPane getEditTextPane() {
		return ((WorkPanel) getTabbedPaneHomeWork().getSelectedComponent())
				.getEditPanel().getTextPane();
	}
	
	/**
	 * 
	 * @return JTextPane textPane
	 */
	public JTextPane getViewTextPane() {
		return ((WorkPanel) getTabbedPaneHomeWork().getSelectedComponent())
				.getViewPanel().getTextArea();
	}
	
	/**
	 * 
	 * @return WorkPanel workPanel
	 */
	public WorkPanel getCurrentWorkPane() {
		return ((WorkPanel) getTabbedPaneHomeWork().getSelectedComponent());
	}
	
	/**
	 * enable or disable cut and copy menu item
	 * @param activated
	 */
	public void activateCopyCut(boolean activated){
		this.copyMenuItem.setEnabled(activated);
		this.cutMenuItem.setEnabled(activated);
	}
	
	/**
	 * enable or disable paste menu item
	 * @param activated
	 */
	public void activatePast(boolean activated){
		this.pasteMenuItem.setEnabled(activated);
	}
	

}
