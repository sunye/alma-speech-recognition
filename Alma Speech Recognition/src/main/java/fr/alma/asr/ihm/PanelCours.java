/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelCours.java
 *
 * Created on 8 févr. 2010, 13:21:40
 */

package fr.alma.asr.ihm;

/**
 *
 * @author Le_Clubber
 */
public class PanelCours extends javax.swing.JPanel {

    /** Creates new form PanelCours */
    public PanelCours() {
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

        separation = new javax.swing.JSplitPane();
        panelProf = new javax.swing.JPanel();
        jLabelProf = new javax.swing.JLabel();
        jScrollPaneProf = new javax.swing.JScrollPane();
        jEditorPaneProf = new javax.swing.JEditorPane();
        panelEleve = new javax.swing.JPanel();
        jLabelEleve = new javax.swing.JLabel();
        jScrollPaneEleve = new javax.swing.JScrollPane();
        jEditorPaneEleve = new javax.swing.JEditorPane();

        separation.setDividerLocation(200);

        jLabelProf.setText("Cour dicté (non-modifiable) :");

        jScrollPaneProf.setViewportView(jEditorPaneProf);

        javax.swing.GroupLayout panelProfLayout = new javax.swing.GroupLayout(panelProf);
        panelProf.setLayout(panelProfLayout);
        panelProfLayout.setHorizontalGroup(
            panelProfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProfLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelProfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneProf, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                    .addComponent(jLabelProf))
                .addContainerGap())
        );
        panelProfLayout.setVerticalGroup(
            panelProfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProfLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelProf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneProf, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addContainerGap())
        );

        separation.setLeftComponent(panelProf);

        jLabelEleve.setText("Cour perso :");

        jScrollPaneEleve.setViewportView(jEditorPaneEleve);

        javax.swing.GroupLayout panelEleveLayout = new javax.swing.GroupLayout(panelEleve);
        panelEleve.setLayout(panelEleveLayout);
        panelEleveLayout.setHorizontalGroup(
            panelEleveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEleveLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEleveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneEleve, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                    .addComponent(jLabelEleve))
                .addContainerGap())
        );
        panelEleveLayout.setVerticalGroup(
            panelEleveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEleveLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelEleve)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneEleve, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addContainerGap())
        );

        separation.setRightComponent(panelEleve);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(separation, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(separation, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane jEditorPaneEleve;
    private javax.swing.JEditorPane jEditorPaneProf;
    private javax.swing.JLabel jLabelEleve;
    private javax.swing.JLabel jLabelProf;
    private javax.swing.JScrollPane jScrollPaneEleve;
    private javax.swing.JScrollPane jScrollPaneProf;
    private javax.swing.JPanel panelEleve;
    private javax.swing.JPanel panelProf;
    private javax.swing.JSplitPane separation;
    // End of variables declaration//GEN-END:variables

}
