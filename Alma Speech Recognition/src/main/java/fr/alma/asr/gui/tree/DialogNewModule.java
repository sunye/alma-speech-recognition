/*
 * DialogNewModule.java
 *
 * Created on 9 févr. 2010, 22:07:42
 */

package fr.alma.asr.gui.tree;

/**
 * Classe de type JDialog pour l'ajout d'un module.
 * @author Braud Jeremy
 */
public class DialogNewModule extends javax.swing.JDialog {

    /** Creates new form DialogNewModule. */
    public DialogNewModule(java.awt.Frame parent) {
        super(parent, true);
		setLocationRelativeTo(null);
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

        labelName = new javax.swing.JLabel();
        textNom = new javax.swing.JTextField();
        annuler = new javax.swing.JButton();
        valider = new javax.swing.JButton();
        checkBoxDossiers = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nouveau module");

        labelName.setText("Entrez le nom du nouveau module :");

        textNom.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                textNomCaretUpdate(evt);
            }
        });
        textNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNomActionPerformed(evt);
            }
        });

        annuler.setText("Annuler");
        annuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annulerActionPerformed(evt);
            }
        });

        valider.setText("Valider");
        valider.setEnabled(false);
        valider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validerActionPerformed(evt);
            }
        });

        checkBoxDossiers.setSelected(true);
        checkBoxDossiers.setText("Créer les sous-dossier usuels (CM, TD, TP)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textNom, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                    .addComponent(labelName)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(valider)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(annuler))
                    .addComponent(checkBoxDossiers))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkBoxDossiers)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(annuler)
                    .addComponent(valider))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	/**
	 * ActionPerformed du bouton valider.
	 * @param evt l'evenement capturé
	 */
	private void validerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validerActionPerformed
		this.infos = new InfoModule(this.textNom.getText(), checkBoxDossiers.isSelected());
		this.setVisible(false);
	}//GEN-LAST:event_validerActionPerformed

	/**
	 * ActionPerformed du bouton annuler.
	 * @param evt l'evenement capturé
	 */
	private void annulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annulerActionPerformed
		this.setVisible(false);
	}//GEN-LAST:event_annulerActionPerformed

	/**
	 * ActionPerformed de la zone de texte.
	 * @param evt l'evenement capturé
	 */
	private void textNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNomActionPerformed
		if (textNom.getText().length() > 0) {
			validerActionPerformed(evt);
		}
	}//GEN-LAST:event_textNomActionPerformed

	/**
	 * Entrée de texte dans  la zone de saisie.
	 * @param evt l'evenement capturé
	 */
	private void textNomCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_textNomCaretUpdate
		valider.setEnabled(textNom.getText().length() > 0);
	}//GEN-LAST:event_textNomCaretUpdate

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton annuler;
    private javax.swing.JCheckBox checkBoxDossiers;
    private javax.swing.JLabel labelName;
    private javax.swing.JTextField textNom;
    private javax.swing.JButton valider;
    // End of variables declaration//GEN-END:variables

	/** Le nom retourné. */
	private InfoModule infos;

	/**
	 * Méthode d'affichage de la fenetre avec retour.
	 * @return le nom rentré
	 */
	public InfoModule showDialog() {
		this.setVisible(true);
		return infos;
	}

}
