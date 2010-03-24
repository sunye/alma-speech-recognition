/*
 * DialogNouvelElement.java
 *
 * Created on 22 févr. 2010, 16:39:41
 */

package fr.alma.asr.gui.tree;

import fr.alma.asr.gui.Controleur;
import java.util.logging.Level;
import javax.persistence.UniqueConstraint;

/**
 * Classe de type JDialog pour l'ajout d'un élément.
 * @author Braud Jeremy
 */
public class DialogNewElement extends javax.swing.JDialog {
	
	/**
	 * Creates new form DialogNouvelElement.
	 * @param parent l'appelant
	 * @param titre le titre de la fenetre
	 * @param label le vtexte de la fenetre
	 */
    public DialogNewElement(java.awt.Frame parent, String titre, String label) {
        super(parent, true);
		setLocationRelativeTo(null);
        initComponents();
		this.setTitle(titre);
		this.labelName.setText(label);

		// apparence selon le systeme
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
			javax.swing.SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			Controleur.printLog(Level.SEVERE, e.getMessage());
		}
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        annuler = new javax.swing.JButton();
        valider = new javax.swing.JButton();
        labelName = new javax.swing.JLabel();
        textName = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        labelName.setText("Entrez le nom du nouveau dossier :");

        textName.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                textNameCaretUpdate(evt);
            }
        });
        textName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(valider)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(annuler))
                    .addComponent(labelName)
                    .addComponent(textName, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(annuler)
                    .addComponent(valider))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	/**
	 * ActionPerformed de la zone de texte.
	 * @param evt l'evenement capturé
	 */
	private void textNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNameActionPerformed
		if (textName.getText().length() > 0) {
			validerActionPerformed(evt);
		}
	}//GEN-LAST:event_textNameActionPerformed

	/**
	 * ActionPerformed du bouton valider.
	 * @param evt l'evenement capturé
	 */
	private void validerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validerActionPerformed
		this.name = new String(this.textName.getText());
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
	 * Entrée de texte dans  la zone de saisie.
	 * @param evt l'evenement capturé
	 */
	private void textNameCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_textNameCaretUpdate
		valider.setEnabled(textName.getText().length() > 0);
	}//GEN-LAST:event_textNameCaretUpdate

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton annuler;
    private javax.swing.JLabel labelName;
    private javax.swing.JTextField textName;
    private javax.swing.JButton valider;
    // End of variables declaration//GEN-END:variables

	/** Le nom retourné. */
	private String name;

	/**
	 * Méthode d'affichage de la fenetre avec retour.
	 * @return le nom rentré
	 */
	public String showDialog() {
		this.setVisible(true);
		return name;
	}

}
