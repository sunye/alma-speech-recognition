/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.alma.asr.gui.tree;

import fr.alma.asr.gui.Controleur;
import java.util.logging.Level;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellEditor;

/**
 * Editeur des cellules du JTree.
 * @author Jérémy Braud
 */
public class MyDefaultTreeCellEditor extends DefaultTreeCellEditor {

	/**
	 * Constructeur.
	 * @param tree l'arbre
	 * @param renderer le renderer
	 */
	public MyDefaultTreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer) {
		super(tree, renderer);
	}

	/**
	 * Constructeur.
	 * @param tree l'arbre
	 * @param renderer le renderer
	 * @param editor l'éditeur
	 */
	public MyDefaultTreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer, TreeCellEditor editor) {
		super(tree, renderer, editor);
	}

	@Override
	public boolean stopCellEditing() {
		Controleur.printLog(Level.SEVERE, "treecelleditor");
		return true;
	}

}
