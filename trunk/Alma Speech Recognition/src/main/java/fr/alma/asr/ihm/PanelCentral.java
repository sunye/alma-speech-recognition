/*
 * PanelCentral.java
 *
 * Created on 11 févr. 2010, 10:56:52
 */

package fr.alma.asr.ihm;

import fr.alma.asr.ihm.tree.MyTransferHandler;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * Le panel central de l'application.
 * @author Braud Jeremy
 */
public class PanelCentral extends javax.swing.JPanel {

    /**
	 * Creates new form PanelCentral.
	 */
    public PanelCentral() {
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

        splitPane = new javax.swing.JSplitPane();
        ongletsArbres = new javax.swing.JTabbedPane();
        scrollPane1 = new javax.swing.JScrollPane();
        arbreCours = new javax.swing.JTree(this.racineCours);
        scrollPane2 = new javax.swing.JScrollPane();
        arbrePlan = new javax.swing.JTree(this.racinePlan);
        ongletsEdition = new javax.swing.JTabbedPane();

        splitPane.setDividerLocation(120);

        arbreCours.setDragEnabled(true);
        arbreCours.setDropMode(javax.swing.DropMode.INSERT);
        arbreCours.setEditable(true);
        arbreCours.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                arbreCoursMouseClicked(evt);
            }
        });
        scrollPane1.setViewportView(arbreCours);
        arbreCours.setTransferHandler(new MyTransferHandler());

        DefaultTreeModel treeModel = (DefaultTreeModel) arbreCours.getModel();
        treeModel.setAsksAllowsChildren(true);

        ongletsArbres.addTab("Cours", scrollPane1);

        scrollPane2.setViewportView(arbrePlan);
        DefaultTreeModel treeModel2 = (DefaultTreeModel) arbrePlan.getModel();
        treeModel2.setAsksAllowsChildren(true);

        ongletsArbres.addTab("Plan", scrollPane2);

        splitPane.setLeftComponent(ongletsArbres);
        splitPane.setRightComponent(ongletsEdition);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
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

				if (node.getAllowsChildren()) {
					javax.swing.JMenuItem addFolderMenu = new javax.swing.JMenuItem("Ajouter un dossier");
					addFolderMenu.addActionListener(new TreeAddElementMenuListener(node, Boolean.FALSE));
					jpm.add(addFolderMenu);

					javax.swing.JMenuItem addFileMenu = new javax.swing.JMenuItem("Ajouter un Fichier");
					addFileMenu.addActionListener(new TreeAddElementMenuListener(node, Boolean.TRUE));
					jpm.add(addFileMenu);
				} else {

					javax.swing.JMenuItem openMenu = new javax.swing.JMenuItem("Ouvrir");
					openMenu.addActionListener(new TreeOpenMenuListener(node));
					jpm.add(openMenu);

					javax.swing.JMenuItem impressionMenu = new javax.swing.JMenuItem("Imprimer");
					impressionMenu.addActionListener(new TreeImpressMenuListener(node));
					jpm.add(impressionMenu);
				}

				// on efface pas la racine
				if (row != 0) {
					javax.swing.JMenuItem eraseMenu = new javax.swing.JMenuItem("Effacer cet élément");
					eraseMenu.addActionListener(new TreeEraseMenuListener(node));
					jpm.add(eraseMenu);
				}

				javax.swing.JMenuItem propertiesMenu = new javax.swing.JMenuItem("Propriétés");
				propertiesMenu.addActionListener(new TreePropertiesMenuListener(node));
				jpm.add(propertiesMenu);

				jpm.show(arbreCours, evt.getX(), evt.getY());
			}
		}
		//Si double clic
		else if(evt.getClickCount()==2&&(!evt.isConsumed())){
			evt.consume();
			//ouverture du fichier
			int row = arbreCours.getRowForLocation(evt.getX(), evt.getY());
			if(row != -1){
				//On peut récupérer le chemin du noeud qui a généré l'événement
				arbreCours.setSelectionPath(arbreCours.getPathForLocation(evt.getX(), evt.getY()));
				//On peut donc en déduire le noeud
				DefaultMutableTreeNode node =  (DefaultMutableTreeNode)arbreCours.getLastSelectedPathComponent();
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
		private Boolean isFile;
		public TreeAddElementMenuListener(DefaultMutableTreeNode node, Boolean isFile){
			this.node = node;
			this.isFile = isFile;
		}
		@Override
		public void actionPerformed(java.awt.event.ActionEvent e) {
			String nom;
			if (this.isFile) {
				nom = new DialogNouvelElement(null, "Nouveau fichier", "Nom du fichier à créer :").showDialog();
			} else {
				nom = new DialogNouvelElement(null, "Nouveau dossier", "Nom du dossier à créer :").showDialog();
			}
			if (nom != null) {
				DefaultTreeModel model = (DefaultTreeModel) arbreCours.getModel();
				if (this.isFile) {
					Object element = controleur.ajoutFichier(nom,node);
					node.add(new DefaultMutableTreeNode(element, false));
				} else {
					Object element = controleur.ajoutDossier(nom,node);
					node.add(new DefaultMutableTreeNode(element));
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree arbreCours;
    private javax.swing.JTree arbrePlan;
    private javax.swing.JTabbedPane ongletsArbres;
    private javax.swing.JTabbedPane ongletsEdition;
    private javax.swing.JScrollPane scrollPane1;
    private javax.swing.JScrollPane scrollPane2;
    private javax.swing.JSplitPane splitPane;
    // End of variables declaration//GEN-END:variables

    private DefaultMutableTreeNode racineCours;
    private DefaultMutableTreeNode racinePlan;
	private Controleur controleur;

	private void initialisation() {
		this.controleur = Controleur.getInstance();
		this.racineCours = new DefaultMutableTreeNode("Cours");
		controleur.construireArbreCours(this.racineCours);
		this.racinePlan = new DefaultMutableTreeNode("Plan");
		controleur.construireArbrePlan(this.racinePlan);
	}

}
