/*
 * JSplashScreen.java
 *
 * Created on 24 févr. 2010, 13:59:33
 */

package fr.alma.asr.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.GroupLayout;

import javax.swing.ImageIcon;
import javax.swing.LayoutStyle;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
/**
 * Classe de type JDialog pour créer un splash screen de chargement.
 * @author Braud Jeremy
 */
public class SplashScreen extends javax.swing.JDialog {

    /**
	 * Creates new form JSplashScreen.
	 * @param parent la frame parent
	 * @param modal la modalité
	 */
    public SplashScreen(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
		initComponents();

		// apparence selon le systeme
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
			javax.swing.SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			System.out.println("LookAndFeel non supporté.");
		}
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - getSize().width)/2,(screen.height - getSize().height)/2);
			
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);

        jLabel1.setIcon(new ImageIcon(getClass().getResource("/ASRSplash.png")));
        //jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/load.gif"))); // NOI18N
        //jLabel1.setText("Chargement...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setVerticalGroup(layout.createSequentialGroup()
        	.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));
        layout.setHorizontalGroup(layout.createSequentialGroup()
        	.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));

        pack();
        this.setSize(450, 250);
    }// </editor-fold>//GEN-END:initComponents

    /**
	 * The main function.
    * @param args the command line arguments
    */
    public static void main(String[] args) {
		new SplashScreen(null, true).setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

}