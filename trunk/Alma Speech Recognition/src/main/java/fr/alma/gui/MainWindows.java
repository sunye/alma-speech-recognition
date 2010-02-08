package fr.alma.gui;
import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;


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
public class MainWindows extends javax.swing.JFrame {

	private JMenuItem helpMenuItem;
	private JMenu jMenuHelp;
	private JMenuItem deleteMenuItem;
	private JSeparator jSeparator1;
	private JMenuItem pasteMenuItem;
	private JMenuItem copyMenuItem;
	private JMenuItem cutMenuItem;
	private JMenu jMenuEdition;
	private JMenuItem exitMenuItem;
	private JSeparator jSeparator2;
	private JMenuItem closeFileMenuItem;
	private JMenuItem saveAsMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem openFileMenuItem;
	private JMenuItem newFileMenuItem;
	private JMenu jMenuFile;
	private JMenuBar jMenuBar;
	
	private ToolBarPannel toolBarPannel;
	private ExplorerPannel explorerPannel;
	private EditPannel editPannel;
	/**
	* Auto-generated main method to display this JFrame
	*/
	
	public MainWindows() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				BorderLayout thisLayout = new BorderLayout();
				this.setTitle("Alma Speech Recognition");
				getContentPane().setLayout(thisLayout);
				{
					toolBarPannel = new ToolBarPannel();
					getContentPane().add(toolBarPannel, BorderLayout.NORTH);
				}
				{
					explorerPannel = new ExplorerPannel();
					getContentPane().add(explorerPannel, BorderLayout.WEST);
				}
				{
					editPannel = new EditPannel();
					getContentPane().add(editPannel, BorderLayout.CENTER);
				}
			}
			setSize(400, 300);
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
						newFileMenuItem.setText("New");
					}
					{
						openFileMenuItem = new JMenuItem();
						jMenuFile.add(openFileMenuItem);
						openFileMenuItem.setText("Open");
					}
					{
						saveMenuItem = new JMenuItem();
						jMenuFile.add(saveMenuItem);
						saveMenuItem.setText("Save");
					}
					{
						saveAsMenuItem = new JMenuItem();
						jMenuFile.add(saveAsMenuItem);
						saveAsMenuItem.setText("Save As ...");
					}
					{
						closeFileMenuItem = new JMenuItem();
						jMenuFile.add(closeFileMenuItem);
						closeFileMenuItem.setText("Close");
					}
					{
						jSeparator2 = new JSeparator();
						jMenuFile.add(jSeparator2);
					}
					{
						exitMenuItem = new JMenuItem();
						jMenuFile.add(exitMenuItem);
						exitMenuItem.setText("Exit");
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
						helpMenuItem.setText("Aide");
					}
				}
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		MainWindows mainWindows = new MainWindows();
		mainWindows.setVisible(true);
	}

}
