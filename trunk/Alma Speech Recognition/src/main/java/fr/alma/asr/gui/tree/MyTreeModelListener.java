/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.alma.asr.gui.tree;

import fr.alma.asr.gui.Controleur;
import java.util.logging.Level;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author LeClubber
 */
public class MyTreeModelListener implements TreeModelListener {

	@Override
	public void treeNodesChanged(TreeModelEvent e) {
		Controleur.printLog(Level.SEVERE, "MyTreeModelListener info!");
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getTreePath().getLastPathComponent();
//		new Controleur().changerNomNoeud(node, nom);
	}

	@Override
	public void treeNodesInserted(TreeModelEvent e) {}

	@Override
	public void treeNodesRemoved(TreeModelEvent e) {}

	@Override
	public void treeStructureChanged(TreeModelEvent e) {}

}
