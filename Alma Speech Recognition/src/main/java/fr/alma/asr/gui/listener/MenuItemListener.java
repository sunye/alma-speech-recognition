package fr.alma.asr.gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import fr.alma.asr.gui.Controleur;
import fr.alma.asr.gui.MainWindow;
import fr.alma.asr.gui.tabbedpane.TabbedPane;

public class MenuItemListener implements ActionListener {

	
	private MainWindow mainWindow;
	
	public MenuItemListener(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		
		JMenuItem source = (JMenuItem)evt.getSource();

		if(source.getText().equals("Préférences interface...")){
			Controleur.getInstance().showParamDialog();
		}
		else if(source.getText().equals("Options du dictionnaire...")){
			Controleur.getInstance().showDicoParam();
		}
		else if(source.getText().equals("Options du  moteur...")){
			Controleur.getInstance().showOptParam();
		}
		else if(source.getText().equals("Enregistrer")){
			Controleur.getInstance().enregistrerCours(mainWindow.getCurrentWorkPane(), mainWindow.getCurrentWorkPane().getViewPanel().getFormatedText(), mainWindow.getCurrentWorkPane().getEditPanel().getFormatedText());
			Controleur.getInstance().setCurrentModified(false);

		}
		else if(source.getText().equals("Exporter en RTF")){

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.showDialog(null, "Enregister en RTF");
			String outPutFile = fileChooser.getSelectedFile()
			.getAbsolutePath();

			if (outPutFile != null) {
				if (!outPutFile.toUpperCase().endsWith(".RTF")) {
					outPutFile += ".rtf";
				}
				Controleur.printOutRtf(outPutFile, mainWindow.getEditTextPane());
			}
			
		}
		else if(source.getText().equals("Quitter")){
			Controleur.getInstance().stopEngine();
			Controleur.getInstance().deconnexion();
			System.exit(0);
		}
	}

}
