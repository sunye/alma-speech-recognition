package fr.alma.asr.gui.tree;

import fr.alma.asr.entities.Subject;
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

	/**
	 * Constructeur par defaut.
	 */
	public MyTreeRenderer() {
		super();
		subject_opened = new ImageIcon("img/jtree/subject_opened.png");
		subject_closed = new ImageIcon("img/jtree/subject_closed.png");
	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		if (!leaf) {
			DefaultMutableTreeNode node=(DefaultMutableTreeNode) value;
			Object obj = node.getUserObject();
			if (obj.getClass().isInstance(Subject.class)) {
				if (expanded) {
					setIcon(subject_opened);
				} else {
					setIcon(subject_closed);
				}
			}
		}
		return this;
	}

}
