/*
 * PanelCours.java
 *
 * Created on 9 févr. 2010, 09:57:13
 */

package fr.alma.asr.gui.tree;

import fr.alma.asr.gui.Controleur;
import java.util.logging.Level;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * Classe de type JPanel pour afficher l'arbre des cours.
 * @author Braud Jeremy
 */
public class PanelTreeCours extends javax.swing.JPanel {

    /** Creates new form PanelCours. */
    public PanelTreeCours() {
		initialisation();
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        arbreCours = new javax.swing.JTree(this.racineCours);

        arbreCours.setDragEnabled(true);
        arbreCours.setDropMode(javax.swing.DropMode.ON);
        arbreCours.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                arbreCoursMouseClicked(evt);
            }
        });
        scrollPane.setViewportView(arbreCours);
        arbreCours.setTransferHandler(new MyTransferHandler());
        MyTreeRenderer renderer = new MyTreeRenderer();
        arbreCours.setCellRenderer(renderer);

        //arbreCours.setCellEditor(new MyDefaultTreeCellEditor(arbreCours, renderer));

        DefaultTreeModel treeModel = (DefaultTreeModel) arbreCours.getModel();
        treeModel.setAsksAllowsChildren(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

	private void arbreCoursMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_arbreCoursMouseClicked
		//Lors d'un clic droit
		if(evt.getButton() == java.awt.event.MouseEvent.BUTTON3){
			//Si on a cliqué sur l'arbre, on peut récupérer l'indice de la ligne avec cette méthode
			//Sinon, la méthode retourne -1
			int row = arbreCours.getRowForLocation(evt.getX(), evt.getY());
			if(row != -1){
				//On peut récupérer le chemin du noeud qui a généré l'événement
				arbreCours.setSelectionPath(arbreCours.getPathForLocation(evt.getX(), evt.getY()));
				//On peut donc en déduire le noeud
				DefaultMutableTreeNode node =  (DefaultMutableTreeNode)arbreCours.getLastSelectedPathComponent();
				//On n'a plus qu'à générer notre menu contextuel !
				javax.swing.JPopupMenu jpm = new javax.swing.JPopupMenu();
				
				// traitement différent pour la racine
				if (row != 0) {

					if (node.getAllowsChildren()) {
						javax.swing.JMenuItem addFolderMenu = new javax.swing.JMenuItem("Ajouter un dossier");
						addFolderMenu.addActionListener(new TreeAddElementMenuListener(node, false, false));
						jpm.add(addFolderMenu);

						javax.swing.JMenuItem addFileMenu = new javax.swing.JMenuItem("Ajouter un cours");
						addFileMenu.addActionListener(new TreeAddElementMenuListener(node, true, false));
						jpm.add(addFileMenu);
					} else {

						javax.swing.JMenuItem openMenu = new javax.swing.JMenuItem("Ouvrir");
						openMenu.addActionListener(new TreeOpenMenuListener(node));
						jpm.add(openMenu);

						javax.swing.JMenuItem impressionMenu = new javax.swing.JMenuItem("Imprimer");
						impressionMenu.addActionListener(new TreeImpressMenuListener(node));
						jpm.add(impressionMenu);
					}

					javax.swing.JMenuItem eraseMenu = new javax.swing.JMenuItem("Effacer cet élément");
					eraseMenu.addActionListener(new TreeEraseMenuListener(node));
					jpm.add(eraseMenu);

					javax.swing.JMenuItem addRenameMenu = new javax.swing.JMenuItem("Renommer");
					addRenameMenu.addActionListener(new TreeRenameMenuListener(node));
					jpm.add(addRenameMenu);

					javax.swing.JMenuItem propertiesMenu = new javax.swing.JMenuItem("Propriétés");
					propertiesMenu.addActionListener(new TreePropertiesMenuListener(node));
					jpm.add(propertiesMenu);
				} else {
					javax.swing.JMenuItem addFolderMenu = new javax.swing.JMenuItem("Ajouter un Module");
					addFolderMenu.addActionListener(new TreeAddElementMenuListener(node, false, true));
					jpm.add(addFolderMenu);
				}

				jpm.show(arbreCours, evt.getX(), evt.getY());
			}
			//Si double clic
		} else if(evt.getClickCount()==2&&(!evt.isConsumed())){
			evt.consume();
			//ouverture du fichier
			int row = arbreCours.getRowForLocation(evt.getX(), evt.getY());
			if(row != -1){
				//On peut récupérer le chemin du noeud qui a généré l'événement
				arbreCours.setSelectionPath(arbreCours.getPathForLocation(evt.getX(), evt.getY()));
				//On peut donc en déduire le noeud
				DefaultMutableTreeNode node =  (DefaultMutableTreeNode) arbreCours.getLastSelectedPathComponent();
				if (!node.getAllowsChildren()) {
					controleur.ouvrir(node);
				}
			}
		}
}//GEN-LAST:event_arbreCoursMouseClicked

	class TreeEraseMenuListener implements java.awt.event.ActionListener{
		private DefaultMutableTreeNode  node;
		public TreeEraseMenuListener(DefaultMutableTreeNode node){
			this.node = node;
		}
		@Override
		public void actionPerformed(java.awt.event.ActionEvent e) {
			DefaultTreeModel model = (DefaultTreeModel) arbreCours.getModel();
			DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node.getParent();
			controleur.suppressionElement(node);
			model.removeNodeFromParent(this.node);
			model.nodeChanged(parentNode);
		}
	}

	class TreeAddElementMenuListener implements java.awt.event.ActionListener{
		private DefaultMutableTreeNode  node;
		private boolean isFile;
		private boolean isModule;
		public TreeAddElementMenuListener(DefaultMutableTreeNode node, boolean isFile, boolean isModule){
			this.node = node;
			this.isFile = isFile;
			this.isModule = isModule;
		}
		@Override
		public void actionPerformed(java.awt.event.ActionEvent e) {
			String nom = null;
			boolean ajoutDossiersCourants = true;
			if (this.isFile) {
				nom = new DialogNewElement(null, "Nouveau fichier", "Nom du fichier à créer :").showDialog();
			} else if (this.isModule) {
				InfoModule infos = new DialogNewModule(null).showDialog();
				if (infos != null) {
					nom = infos.getNom();
					ajoutDossiersCourants = infos.creerDossiersCourants();
				}
			} else {
				nom = new DialogNewElement(null, "Nouveau dossier", "Nom du dossier à créer :").showDialog();
			}
			if (nom != null) {
				DefaultTreeModel model = (DefaultTreeModel) arbreCours.getModel();
				if (this.isFile) {
					Object element = controleur.ajoutFichier(nom, node);
					node.add(new DefaultMutableTreeNode(element, false));
				} else {
					controleur.ajoutFolder(nom, node, this.isModule, ajoutDossiersCourants);
				}
				model.nodeChanged(node);
				arbreCours.updateUI();
			}
		}
	}

	class TreeImpressMenuListener implements java.awt.event.ActionListener{
		private DefaultMutableTreeNode  node;
		public TreeImpressMenuListener(DefaultMutableTreeNode node){
			this.node = node;
		}
		@Override
		public void actionPerformed(java.awt.event.ActionEvent e) {
			controleur.impression(node);
		}
	}

	class TreePropertiesMenuListener implements java.awt.event.ActionListener{
		private DefaultMutableTreeNode  node;
		public TreePropertiesMenuListener(DefaultMutableTreeNode node){
			this.node = node;
		}
		@Override
		public void actionPerformed(java.awt.event.ActionEvent e) {
			controleur.afficherProprietes(node);
		}
	}

	class TreeOpenMenuListener implements java.awt.event.ActionListener{
		private DefaultMutableTreeNode  node;
		public TreeOpenMenuListener(DefaultMutableTreeNode node){
			this.node = node;
		}
		@Override
		public void actionPerformed(java.awt.event.ActionEvent e) {
			controleur.ouvrir(node);
		}
	}

	class TreeRenameMenuListener implements java.awt.event.ActionListener{
		private DefaultMutableTreeNode  node;
		public TreeRenameMenuListener(DefaultMutableTreeNode node){
			this.node = node;
		}
		@Override
		public void actionPerformed(java.awt.event.ActionEvent e) {
			String nom = new DialogNewElement(null, "Renommer", "Entrez le nouveau nom :").showDialog();
			if (nom != null) {
				controleur.renommerElement(node, nom);
				DefaultTreeModel model = (DefaultTreeModel) arbreCours.getModel();
				model.nodeChanged(node);
			}
		}
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree arbreCours;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables

	/** Le noeud racine. */
	private DefaultMutableTreeNode racineCours;
	/** Le controleur. */
	private Controleur controleur;

	/**
	 * Initialisation du panel.
	 */
	private void initialisation() {
		this.controleur = Controleur.getInstance();
		this.racineCours = new DefaultMutableTreeNode("Cours");
		try {
			controleur.construireArbreCours(this.racineCours);
		} catch (Exception e) {
			Controleur.printLog(Level.INFO, "Chargement de l'arbre de cours échoué");
			controleur.erreurChargementArbre();
		}
	}

}
