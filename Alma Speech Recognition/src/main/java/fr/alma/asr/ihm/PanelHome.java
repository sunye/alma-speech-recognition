/*
 * PanelHome.java
 *
 * Created on 6 mars 2010, 11:16:52
 */

package fr.alma.asr.ihm;

import fr.alma.asr.entities.Lesson;
import java.util.List;

/**
 * Panel d'acceuil.
 * @author Braud Jeremy
 */
public class PanelHome extends javax.swing.JPanel {

    /** Creates new form PanelHome. */
    public PanelHome() {
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

        labelClasser = new javax.swing.JLabel();
        comboDate = new javax.swing.JComboBox();

        labelClasser.setText("Classer les cours par ordre de :");

        comboDate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "création", "modification" }));
        comboDate.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboDateItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelClasser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(154, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelClasser)
                    .addComponent(comboDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(269, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

	private void comboDateItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboDateItemStateChanged
		this.classerParCreation = comboDate.getSelectedIndex() == 0;
		updateListeFichiers();
	}//GEN-LAST:event_comboDateItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox comboDate;
    private javax.swing.JLabel labelClasser;
    // End of variables declaration//GEN-END:variables

	private Controleur controleur;
	private Boolean classerParCreation;

	private void initialisation() {
		this.controleur = Controleur.getInstance();
		this.classerParCreation = Boolean.FALSE;
		updateListeFichiers();
	}

	private void updateListeFichiers() {
		List<Lesson> listeFichier = controleur.getListeFichiers(classerParCreation);
		for (Lesson fichier : listeFichier) {
			System.out.println(fichier);
		}
	}

}
