package fr.alma.asr.gui.tree;

import fr.alma.asr.entities.Folder;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * Rendu d'un JTree.
 * @author Braud Jeremy
 */
public class MyTreeRendererCours extends DefaultTreeCellRenderer {

	/** Image du module ouvert.*/
	private ImageIcon subjectOpened;
	/** Image du module fermé.*/
	private ImageIcon subjectClosed;
	/** Image du module ouvert.*/
	private ImageIcon folderOpened;
	/** Image du module fermé.*/
	private ImageIcon folderClosed;
	/** Image d'un cours.*/
	private ImageIcon lessonIcon;
	/**
	 * Constructeur par defaut.
	 */
	public MyTreeRendererCours() {
		super();
		subjectOpened = new ImageIcon(getClass().getResource("/jtree/subject_opened.png"));
		subjectClosed = new ImageIcon(getClass().getResource("/jtree/subject_closed.png"));
		folderOpened = new ImageIcon(getClass().getResource("/jtree/folder_opened.png"));
		folderClosed = new ImageIcon(getClass().getResource("/jtree/folder_closed.png"));
		lessonIcon = new ImageIcon(getClass().getResource("/jtree/application-msword.png"));
	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		if (!leaf) {
			DefaultMutableTreeNode node=(DefaultMutableTreeNode) value;
			Folder folder = (Folder) node.getUserObject();
			if (folder.isModule()) {
				if (expanded) {
					setIcon(subjectOpened);
				} else {
					setIcon(subjectClosed);
				}
			} else {
				if (expanded) {
					setIcon(folderOpened);
				} else {
					setIcon(folderClosed);
				}
			}
		} else {
			setIcon(lessonIcon);
		}
		return this;
	}

}
