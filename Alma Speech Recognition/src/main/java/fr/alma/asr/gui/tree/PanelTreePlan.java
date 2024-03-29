/*
 * PanelPlan.java
 *
 * Created on 9 févr. 2010, 09:57:40
 */

package fr.alma.asr.gui.tree;

import fr.alma.asr.gui.Controleur;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * Classe de type JPanel pour afficher l'arbre du plan d'un cours.
 * @author Braud Jeremy
 */
public class PanelTreePlan extends javax.swing.JPanel {

    /** Creates new form PanelPlan. */
    public PanelTreePlan() {
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
        arbrePlan = new javax.swing.JTree(this.racinePlan);

        scrollPane.setViewportView(arbrePlan);
        DefaultTreeModel treeModel = (DefaultTreeModel) arbrePlan.getModel();
        treeModel.setAsksAllowsChildren(true);

        MyTreeRendererPlan renderer = new MyTreeRendererPlan();
        arbrePlan.setCellRenderer(renderer);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree arbrePlan;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables

	private DefaultMutableTreeNode racinePlan;
	private Controleur controleur;

	private void initialisation() {
		this.controleur = Controleur.getInstance();
		this.racinePlan = new DefaultMutableTreeNode("Plan");
		controleur.setRacineArbrePlan(this.racinePlan);
	}

	public void update(){
		this.arbrePlan.updateUI();
	}
}
