package fr.alma.asr.gui.tree;

import fr.alma.asr.entities.Folder;
import fr.alma.asr.gui.Controleur;
import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.logging.Level;
import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 * Classe de transfer pour utiliser le drag'n drop sur un jtree.
 * @author Braud Jérémy
 */
public class MyTransferHandler extends TransferHandler {

    @Override
    public boolean canImport(TransferHandler.TransferSupport t) {
	   try {
		  DefaultMutableTreeNode node = (DefaultMutableTreeNode) t.getTransferable().getTransferData(MyTransferable.data);
		  if (node.isRoot()) {
			 return false;
		  }
		  if (node.getAllowsChildren()) {
			 Folder dossier = (Folder) node.getUserObject();
			 if (dossier.isModule()) {
				return false;
			 }
		  }
		  Component comp = t.getComponent();
		  if (comp instanceof JTree) {
			 JTree tree = (JTree) comp;
			 JTree.DropLocation location = tree.getDropLocation();
			 if (location != null) {
				DefaultMutableTreeNode cible = (DefaultMutableTreeNode) location.getPath().getLastPathComponent();
				if (!cible.getAllowsChildren() || cible.isRoot()) {
				    return false;
				}
			 }
		  }
		  DataFlavor[] df = t.getDataFlavors();
		  // vérifie si le bon type est drop
		  for (int i = 0; i < df.length; i++) {
			 if (df[i].equals(MyTransferable.data)) {
				return true;
			 }
		  }
	   } catch (UnsupportedFlavorException ex) {
		  Controleur.printLog(Level.SEVERE, ex.getMessage());
	   } catch (IOException ex) {
		  Controleur.printLog(Level.SEVERE, ex.getMessage());
	   }
	   return false;
    }

    @Override
    public boolean importData(TransferHandler.TransferSupport th) {
	   Transferable tr = th.getTransferable();
	   Component cp = th.getComponent();
	   if (tr.isDataFlavorSupported(MyTransferable.data)) {
		  if (cp instanceof JTree) {
			 JTree tree = (JTree) cp;
			 JTree.DropLocation location = tree.getDropLocation();
			 TreePath path = location.getPath();
			 int index = location.getChildIndex();
			 if (index == -1) {
				index = 0;
			 }
			 DefaultMutableTreeNode node;
			 try {
				node = (DefaultMutableTreeNode) tr.getTransferData(MyTransferable.data);
				DefaultMutableTreeNode parent = (DefaultMutableTreeNode) path.getLastPathComponent();

				// vérifie si on ne drop pas dans un noeud fils
				if (node.isNodeDescendant(parent)) {
				    return false;
				}
				Controleur controleur = Controleur.getInstance();
				controleur.deplacerElement(node, parent, index);

				DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
				model.removeNodeFromParent(node);
				// vérifie si on est plus hors index lors d'une réorganisation par ex
				if (index > parent.getChildCount()) {
				    index = parent.getChildCount();
				}
				model.insertNodeInto(node, parent, index);

				TreePath newPath = path.pathByAddingChild(node);
				tree.makeVisible(newPath);
				tree.scrollRectToVisible(tree.getPathBounds(newPath));
			 } catch (Exception e) {
				Controleur.printLog(Level.SEVERE, e.getMessage());
			 }
			 return true;
		  }
	   }
	   return false;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
	   JTree tree = (JTree) c;
	   DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
	   return new MyTransferable(node);
    }

    @Override
    public int getSourceActions(JComponent c) {
	   return COPY_OR_MOVE;
    }
}
