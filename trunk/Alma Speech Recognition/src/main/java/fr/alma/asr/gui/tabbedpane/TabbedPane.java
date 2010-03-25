/*
 * NewJPanel.java
 *
 * Created on 8 févr. 2010, 19:00:03
 */

package fr.alma.asr.gui.tabbedpane;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import fr.alma.asr.gui.Controleur;
import fr.alma.asr.gui.MainWindow;
import fr.alma.asr.gui.WorkPanel;
import fr.alma.asr.gui.HomePanel;

/**
 *
 * @author Gaëtan Hervouet
 */
public class TabbedPane extends JPanel {

	private JTabbedPane onglets;
	private Component component;
	private javax.swing.JButton bouton;
	private javax.swing.JLabel labelNom;
	private JButton boutonModified;
	private JButton boutonIcon;
	private ImageIcon modified = new ImageIcon(getClass().getResource("/icones/modifySmall16.png"));
	private ImageIcon close = new ImageIcon(getClass().getResource("/icones/closeTabbedPane.png"));
	private ImageIcon closeHover = new ImageIcon(getClass().getResource("/icones/closeTabbedPaneHover.png"));
	private ImageIcon tabIcon ;
	private JPanel panel ;
	
	/** Creates new form NewJPanel */
	public TabbedPane(JTabbedPane pane, Component cmp, String title,ImageIcon icon) {
		super();
		this.onglets = pane;
		this.tabIcon = icon;
		this.component = cmp;
		initComponents();
		this.labelNom.setText("  "+title+"  ");
	}

	private void initComponents() {

		labelNom = new javax.swing.JLabel();
		bouton = new javax.swing.JButton();
		boutonModified = new JButton();

		setOpaque(false);
		setPreferredSize(null);
		setLayout(new java.awt.BorderLayout());

		panel = new JPanel(new java.awt.BorderLayout());
		panel.setOpaque(false);

		boutonIcon = new JButton();
		boutonIcon.setIcon(tabIcon);
		boutonIcon.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		boutonIcon.setBorderPainted(false);
		boutonIcon.setContentAreaFilled(false);
		boutonIcon.setFocusable(false);
		boutonIcon.setSize(10,10);
		boutonIcon.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				onglets.setSelectedComponent(component);
			}			
		});
		add(boutonIcon,java.awt.BorderLayout.WEST);

		boutonModified.setIcon(modified);
		boutonModified.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		boutonModified.setBorderPainted(false);
		boutonModified.setContentAreaFilled(false);
		boutonModified.setFocusable(false);
		boutonModified.setSize(10,10);
		boutonModified.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				onglets.setSelectedComponent(component);
			}			
		});
		
		panel.add(labelNom,java.awt.BorderLayout.LINE_START);

		add(panel,java.awt.BorderLayout.CENTER);

		bouton.setIcon(close);
		bouton.setToolTipText("Fermer");
		bouton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		bouton.setBorderPainted(false);
		bouton.setContentAreaFilled(false);
		bouton.setFocusable(false);
		bouton.setPreferredSize(new java.awt.Dimension(16, 16));
		bouton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				bouton.setIcon(closeHover);
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
				bouton.setIcon(close);
			}
		});
		bouton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if(panel.getComponentCount()>1){
					Controleur.getInstance().enregistrerCours(MainWindow.getInstance().getCurrentWorkPane(), MainWindow.getInstance().getCurrentWorkPane().getViewPanel().getFormatedText(), MainWindow.getInstance().getCurrentWorkPane().getEditPanel().getFormatedText());
				}	
				onglets.remove(component);
				Controleur.getInstance().closeWorkPanel((WorkPanel)component);			
			}
		});
		
		if(!(component instanceof HomePanel)){
			add(bouton, java.awt.BorderLayout.EAST);
		}
		
	}
	
	public void setModified(boolean modified){
		if(!modified){
			panel.remove(boutonModified);
			//labelNom.setText("  "+labelNom.getText());
		}
		else{
			panel.add(boutonModified,java.awt.BorderLayout.LINE_END);
			//labelNom.setText(labelNom.getText().substring(2, labelNom.getText().length()));
		}
			
	}
	
}
