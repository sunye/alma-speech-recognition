package fr.alma.asr.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class MenuItemListener implements ActionListener {
	private MainWindow main;
	
	public MenuItemListener(MainWindow main){
		this.main=main;
	}
	
	public void actionPerformed(ActionEvent evt) {
		
		JMenuItem source = (JMenuItem)evt.getSource();
		
		if(source.getText().equals("Préférences...")){
			main.showParamDialog();
		}
		else if(source.getText().equals("Quitter")){
			System.exit(0);
		}
	}

}
