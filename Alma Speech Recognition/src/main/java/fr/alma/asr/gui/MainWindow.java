package fr.alma.asr.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

import javax.swing.*;



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
	private JSeparator jSeparator1;
	private JMenuItem pasteMenuItem;
	private JMenuItem copyMenuItem;
	private JMenuItem cutMenuItem;
	private JMenu jMenuEdition;
	private JSeparator jSeparator2;
	private JMenuItem closeFileMenuItem;
	private JMenuItem saveAsMenuItem;
	private JMenuItem saveMenuItem;
	private StatusPanel statusPannel;
	private JMenuItem openFileMenuItem;
	private JMenuItem newFileMenuItem;
	private JMenuItem parametersMenuItem;
	private JMenu jMenuFile;
	private JMenuBar jMenuBar;
	
	//Panels list
	private JTabbedPane tabbedPane;
	private HomePanel homePanel;
	private HashMap<String,WorkPanel> workPanelList;	
	private ToolBarPanel toolBarPannel;
	private StatusPanel statusPanel;
	
	
	//Dialogs
	private ParametersDialog paramDialog;
	
	/*Parameters variables*/
	private String workPlanPosition = "right";
	private boolean workShowPlan = true;
	private boolean workShowCourses =true;
	
	
	
	/*Getters and setters*/
	
	//Parameters
	public String getWorkPlanPosition() {
		return workPlanPosition;
	}


	public void setWorkPlanPosition(String workPlanPosition) {
		this.workPlanPosition = workPlanPosition;
	}


	public boolean isWorkShowPlan() {
		return workShowPlan;
	}


	public void setWorkShowPlan(boolean workShowPlan) {
		this.workShowPlan = workShowPlan;
	}


	public boolean isWorkShowCourses() {
		return workShowCourses;
	}


	public void setWorkShowCourses(boolean workShowCourses) {
		this.workShowCourses = workShowCourses;
	}

	
	
	//GUI
	
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


	private void initGUI() {
		try {
			{
				BorderLayout thisLayout = new BorderLayout();
				this.setTitle("Alma Speech Recognition");
				getContentPane().setLayout(thisLayout);
				this.setExtendedState(JFrame.MAXIMIZED_BOTH);

				{
					toolBarPannel = new ToolBarPanel();
					getContentPane().add(toolBarPannel, BorderLayout.NORTH);
				}
				
				{
					tabbedPane = new JTabbedPane();
					getContentPane().add(tabbedPane, BorderLayout.CENTER);
					tabbedPane.setOpaque(true);
					
				}
				
				{
					homePanel = new HomePanel();
					tabbedPane.add(homePanel, " Home");
					tabbedPane.setIconAt(0, new ImageIcon("img/home24.png"));
					workPanelList = new HashMap<String,WorkPanel>();
				}

				{
					statusPanel = new StatusPanel();
					getContentPane().add(statusPanel, BorderLayout.SOUTH);
					
				}
				
				
				addNewWorkPanel("Mathématiques");
				
			}
			this.setSize(800, 600);
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
						jSeparator1 = new JSeparator();
						jMenuFile.add(jSeparator1);
					}
					{
						parametersMenuItem = new JMenuItem();
						jMenuFile.add(parametersMenuItem);
						parametersMenuItem.setText("Préférences...");
						parametersMenuItem.addActionListener(new MenuItemListener(this));
					}
					{
						jSeparator2 = new JSeparator();
						jMenuFile.add(jSeparator2);
					}
					{
						closeFileMenuItem = new JMenuItem();
						jMenuFile.add(closeFileMenuItem);
						closeFileMenuItem.setText("Quitter");
						closeFileMenuItem.addActionListener(new MenuItemListener(this));
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
						jSeparator1 = new JSeparator();
						jMenuEdition.add(jSeparator1);
					}
					{
						deleteMenuItem = new JMenuItem();
						jMenuEdition.add(deleteMenuItem);
						deleteMenuItem.setText("Supprimer");
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
						jMenuHelp.add(jSeparator1);
					}
					{
						helpMenuItem = new JMenuItem();
						jMenuHelp.add(helpMenuItem);
						helpMenuItem.setText("Aide");
					}
				}
				
				paramDialog = new ParametersDialog(this);
				
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Add a work panel as a new tab.
	 * @param name name of the module.
	 */
	public void addNewWorkPanel(String name){
		WorkPanel workPanel = new WorkPanel();
		this.tabbedPane.add(name,workPanel);
		tabbedPane.setIconAt(tabbedPane.getTabCount()-1, new ImageIcon("img/RSSfolder24.png"));
		this.workPanelList.put(name, workPanel);
	}
	
	
	/**
	 * Display text in status bar
	 * @param text
	 */
	public void setLastAction(String text){
		this.statusPannel.setStatus(text);
	}
	
	
	public void showParamDialog(){
		paramDialog.setVisible(true);
	}

	
	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainWindow inst = new MainWindow("Alma Speech Recognition");
				Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
				inst.setLocation((screen.width - inst.getSize().width)/2,(screen.height - inst.getSize().height)/2);
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public MainWindow(String title) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/appIcon.png"));
		initGUI();
	}
	
	
}
