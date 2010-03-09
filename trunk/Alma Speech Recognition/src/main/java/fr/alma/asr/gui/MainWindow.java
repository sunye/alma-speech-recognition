package fr.alma.asr.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import fr.alma.asr.gui.listener.MenuItemListener;
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
 * Main class for the application
 * Contains method to access all GUI actions
 * @author Gaëtan Hervouet
 *
 */
public class MainWindow extends javax.swing.JFrame {

	
	private static final long serialVersionUID = 658652694660147986L;
	private static MainWindow instance;
	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JMenuItem helpMenuItem;
	private JMenu jMenuHelp;
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
	private JMenuItem parametersMenuItem;
	private JMenu jMenuFile;
	private JMenuBar jMenuBar;

	
	//Panels list
	private JTabbedPane tabbedPaneHomeWork;
	private JTabbedPane tabbedPanePlanCourses;
	private HomePanel homePanel;	
	private ToolBarPanel toolBarPannel;
	private StatusPanel statusPanel;
	private JSplitPane splitPanel;
	
	
	//Dialogs
	private ParametersDialog paramDialog;

	
	
	//---------------------------------- Getters and setters ----------------------------------
	
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

	public ParametersDialog getParamDialog() {
		return paramDialog;
	}
	public JTabbedPane getTabbedPaneHomeWork() {
		return tabbedPaneHomeWork;
	}


	public void setTabbedPaneHomeWork(JTabbedPane tabbedPaneHomeWork) {
		this.tabbedPaneHomeWork = tabbedPaneHomeWork;
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
	 * Main method which build interface
	 */
	private void initGUI() {
		try {
			{
				BorderLayout thisLayout = new BorderLayout();
				this.setTitle("Alma Speech Recognition");
				getContentPane().setLayout(thisLayout);
				//TODO maximized
				//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
				
				/*----------------------------------TOOLBAR CREATION-------------------------------------------*/
				{
					toolBarPannel = new ToolBarPanel();
					getContentPane().add(toolBarPannel, BorderLayout.NORTH);
				}
				{
					jMenuBar = new JMenuBar();
					setJMenuBar(jMenuBar);
					{
						jMenuFile = new JMenu();
						jMenuBar.add(jMenuFile);
						jMenuFile.setText("Fichier");
						{
							newFileMenuItem = new JMenuItem();
							jMenuFile.add(newFileMenuItem);
							newFileMenuItem.setText("Nouveau cours");
						}
						{
							openFileMenuItem = new JMenuItem();
							jMenuFile.add(openFileMenuItem);
							openFileMenuItem.setText("Ouvrir");
						}
						{
							saveMenuItem = new JMenuItem();
							jMenuFile.add(saveMenuItem);
							saveMenuItem.setText("Enregistrer");
						}
						{
							saveAsMenuItem = new JMenuItem();
							jMenuFile.add(saveAsMenuItem);
							saveAsMenuItem.setText("Enregistrer sous...");
						}
						{
							jMenuFile.add(new JSeparator());
						}
						{
							closeFileMenuItem = new JMenuItem();
							jMenuFile.add(closeFileMenuItem);
							closeFileMenuItem.setText("Quitter");
							closeFileMenuItem.addActionListener(new MenuItemListener());
						}
					}
					{
						jMenuEdition = new JMenu();
						jMenuBar.add(jMenuEdition);
						jMenuEdition.setText("Edition");
						{
							cutMenuItem = new JMenuItem();
							jMenuEdition.add(cutMenuItem);
							cutMenuItem.setText("Couper");
						}
						{
							copyMenuItem = new JMenuItem();
							jMenuEdition.add(copyMenuItem);
							copyMenuItem.setText("Copier");
						}
						{
							pasteMenuItem = new JMenuItem();
							jMenuEdition.add(pasteMenuItem);
							pasteMenuItem.setText("Coller");
						}
						{
							jMenuEdition.add(new JSeparator());
						}
						{
							deleteMenuItem = new JMenuItem();
							jMenuEdition.add(deleteMenuItem);
							deleteMenuItem.setText("Supprimer");
						}
						{
							jMenuEdition.add(new JSeparator());
						}
						{
							parametersMenuItem = new JMenuItem();
							jMenuEdition.add(parametersMenuItem);
							parametersMenuItem.setText("Préférences...");
							parametersMenuItem.addActionListener(new MenuItemListener());
						}
					}
					{
						jMenuHelp = new JMenu();
						jMenuBar.add(jMenuHelp);
						jMenuHelp.setText("Aide");
						{
							helpMenuItem = new JMenuItem();
							jMenuHelp.add(helpMenuItem);
							helpMenuItem.setText("A propos");
						}
						{
							jMenuHelp.add(new JSeparator());
						}
						{
							helpMenuItem = new JMenuItem();
							jMenuHelp.add(helpMenuItem);
							helpMenuItem.setText("Aide");
						}
					}
				}
				
				
				/*----------------------------------Plan,Courses,Work,Home CREATION-------------------------------------------*/
				{
					splitPanel = new JSplitPane();
					getContentPane().add(splitPanel, BorderLayout.CENTER);
				}
				{
					tabbedPanePlanCourses = new JTabbedPane();
					splitPanel.add(tabbedPanePlanCourses, JSplitPane.LEFT);
					tabbedPanePlanCourses.setOpaque(true);
					tabbedPanePlanCourses.add("Cours", new PanelTreeCours());
					tabbedPanePlanCourses.setIconAt(0, new ImageIcon(getClass().getResource("/icones/course24.png")));
					tabbedPanePlanCourses.add("Plan", new PanelTreePlan());
					tabbedPanePlanCourses.setIconAt(1, new ImageIcon(getClass().getResource("/icones/doc24.png")));
				}
				{
					tabbedPaneHomeWork = new JTabbedPane();
					splitPanel.add(tabbedPaneHomeWork,JSplitPane.RIGHT);
				}
				{
					homePanel = new HomePanel();
					tabbedPaneHomeWork.add(homePanel, " Home");
					tabbedPaneHomeWork.setIconAt(0, new ImageIcon(getClass().getResource("/icones/home24.png")));
				}
				{
					statusPanel = new StatusPanel();
					getContentPane().add(statusPanel, BorderLayout.SOUTH);	
				}
			}
			this.setSize(900, 700);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//TODO a passer dans controller
	/**
	 * Add a work panel as a new tab.
	 * @param name name of the module.
	 */
	public void addNewWorkPanel(String name){
		WorkPanel workPanel = new WorkPanel(this);
		tabbedPaneHomeWork.add(name,workPanel);
	    tabbedPaneHomeWork.setIconAt(tabbedPaneHomeWork.getTabCount()-1, new ImageIcon(getClass().getResource("/icones/RSSfolder24.png")));
		//workPanelList.put(name, workPanel);
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
				inst.setLocation((screen.width - inst.getSize().width)/2,(screen.height - inst.getSize().height)/2);
				inst.setLocationRelativeTo(null);
				Controleur.chargementTermine();
				inst.setVisible(true);

				Controleur.addNewWorkPanel("Mathématiques");
				Controleur.addNewWorkPanel("SVT");
			}
		});
	}
	
	/**
	 * Singleton pattern
	 * @return Return MainWindow isntance
	 */
	public static MainWindow getInstance(){
		if(instance==null){
			instance = new MainWindow("Alma Speech Recognition");
		}
		return instance;
	}
	/**
	 * Private constructor
	 * @param title Frame title
	 */
	private MainWindow(String title) {
		super(title);		
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icones/appIcon.png")));
		initGUI();
	}
	
	
}
