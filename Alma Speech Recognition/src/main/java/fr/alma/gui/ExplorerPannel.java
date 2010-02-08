package fr.alma.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;

import javax.swing.ImageIcon;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

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
public class ExplorerPannel extends javax.swing.JPanel {
	private JTree jtreeFile;
	private JTree jTreeLesson;
	private JScrollPane jScrollPaneFile;
	private JTabbedPane jTabbedPaneExplorer;
	private JScrollPane jScrollPaneLesson;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	
	public ExplorerPannel() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			BoxLayout thisLayout = new BoxLayout(this, javax.swing.BoxLayout.Y_AXIS);
			this.setLayout(thisLayout);
			{
				jTabbedPaneExplorer = new JTabbedPane();
				this.add(jTabbedPaneExplorer);
				jTabbedPaneExplorer.setPreferredSize(new java.awt.Dimension(189, 408));
				{
					jScrollPaneLesson = new JScrollPane();
					jTabbedPaneExplorer.addTab("Plan", null, jScrollPaneLesson, null);
					{
						jTreeLesson = new JTree();
						{
						ImageIcon leafLesson = new ImageIcon("src/main/resources/application-msword.png");
						ImageIcon leafFolder = new ImageIcon("src/main/resources/folder.png");

						DefaultTreeCellRenderer renderer = 	new DefaultTreeCellRenderer();
						
						renderer.setLeafIcon(leafLesson);
						renderer.setClosedIcon(leafFolder);
						renderer.setOpenIcon(leafFolder);
						
						jTreeLesson.setCellRenderer(renderer);
						}
						jScrollPaneLesson.setViewportView(jTreeLesson);
					}
				}
				{
					jScrollPaneFile = new JScrollPane();
					jTabbedPaneExplorer.addTab("Cours", null, jScrollPaneFile, null);
					{
						jtreeFile = new JTree();
						{
							ImageIcon leafSection = new ImageIcon("src/main/resources/application-x-font-pcf.png");
							ImageIcon leafChapter = new ImageIcon("src/main/resources/application-x-font-otf.png");

							DefaultTreeCellRenderer renderer = 	new DefaultTreeCellRenderer();
							
							renderer.setLeafIcon(leafSection);
							renderer.setClosedIcon(leafChapter);
							renderer.setOpenIcon(leafChapter);
							
							jtreeFile.setCellRenderer(renderer);

						}
						jScrollPaneFile.setViewportView(jtreeFile);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
