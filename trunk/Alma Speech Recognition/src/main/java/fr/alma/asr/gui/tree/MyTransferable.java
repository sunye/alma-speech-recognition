package fr.alma.asr.gui.tree;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Classe de transfer pour utiliser le drag'n drop sur un jtree.
 * @author Braud Jérémy
 */
public class MyTransferable implements Transferable {

	/** Le noeud à transférer. */
	private DefaultMutableTreeNode node;

	/**
	 * Accès à l'objet du transfert.
	 * @return le noeud transféré
	 */
	public DefaultMutableTreeNode getObject() {
		return node;
	}
	/**
	 * Accès à la data.
	 */
	public static DataFlavor data = create();

	/**
	 * Création du type de transfert.
	 * @return un dataFlavor
	 */
	public static DataFlavor create() {
		String mimeType = DataFlavor.javaJVMLocalObjectMimeType
				+ ";class=" + DefaultMutableTreeNode.class.getName();
		try {
			return new DataFlavor(mimeType);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Constructeur.
	 * @param o le noeud  transférer
	 */
	public MyTransferable(DefaultMutableTreeNode o) {
		node = o;
	}

	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		if (flavor.equals(DataFlavor.stringFlavor)) {
			return node.toString();
		} else {
			return node;
		}
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return new DataFlavor[]{data, DataFlavor.stringFlavor};
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		if (flavor.equals(data)) {
			return true;
		} else if (flavor.equals(DataFlavor.stringFlavor)) {
			return true;
		}
		return false;
	}
}
