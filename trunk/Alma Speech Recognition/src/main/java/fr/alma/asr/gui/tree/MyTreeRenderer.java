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
public class MyTreeRenderer extends DefaultTreeCellRenderer {

	/** Image du module ouvert.*/
	ImageIcon subject_opened;
	/** Image du module fermé.*/
	ImageIcon subject_closed;
	/** Image du module ouvert.*/
	ImageIcon folder_opened;
	/** Image du module fermé.*/
	ImageIcon folder_closed;
	/** Image d'un cours.*/
	ImageIcon lessonIcon;
	/**
	 * Constructeur par defaut.
	 */
	public MyTreeRenderer() {
		super();
		subject_opened = new ImageIcon(getClass().getResource("/jtree/subject_opened.png"));
		subject_closed = new ImageIcon(getClass().getResource("/jtree/subject_closed.png"));
		folder_opened = new ImageIcon(getClass().getResource("/jtree/folder_opened.png"));
		folder_closed = new ImageIcon(getClass().getResource("/jtree/folder_closed.png"));
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
					setIcon(subject_opened);
				} else {
					setIcon(subject_closed);
				}
			} else {
				if (expanded) {
					setIcon(folder_opened);
				} else {
					setIcon(folder_closed);
				}
			}
		}else{
			setIcon(lessonIcon);
		}
		return this;
	}

}
