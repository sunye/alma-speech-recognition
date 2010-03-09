package fr.alma.asr.gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import fr.alma.asr.gui.Controleur;

public class MenuItemListener implements ActionListener {
	
	
	public void actionPerformed(ActionEvent evt) {
		
		JMenuItem source = (JMenuItem)evt.getSource();
		
		if(source.getText().equals("Préférences...")){
			Controleur.getInstance().showParamDialog();
		}
		else if(source.getText().equals("Quitter")){
			System.exit(0);
		}
	}

}
