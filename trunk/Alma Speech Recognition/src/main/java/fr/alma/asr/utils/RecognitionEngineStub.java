package fr.alma.asr.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;

import javax.swing.JOptionPane;

import fr.alma.asr.gui.Controleur;

/**
 * Classe de bouchon des moteurs de reconnaissance vocale.
 * 
 * @author Cédric Krommenhoek
 */
public class RecognitionEngineStub extends RecognitionEngine implements
		Runnable {

	/**	Le processus simulant l'action du moteur. */
	private Thread thread;

	@Override
	public boolean closeMic() {
		if (this.thread.isAlive()) {
			this.thread.interrupt();
			try {
				this.thread.join();
			} catch (InterruptedException e) {
				Controleur.printLog(Level.SEVERE, e.getMessage());
			}
		}
		Controleur.printLog(Level.INFO, "Fermeture du microphone");
		return true;
	}

	@Override
	public boolean openMic() {
		this.thread = new Thread(this);
		this.thread.start();
		Controleur.printLog(Level.INFO, "Ouverture du microphone");
		return true;
	}

	@Override
	public boolean start() {
		Controleur.printLog(Level.INFO, "Moteur démarré");
		return true;
	}

	@Override
	public boolean stop() {
		Controleur.printLog(Level.INFO, "Moteur stoppé");
		return true;
	}

	@Override
	public void run() {
		
		try{
			Scanner fileScan = new Scanner(new File("toPrint.txt"));

			while (true) {
				try {
					Thread.sleep(3000);
					this.setChanged();
					this.notifyObservers(fileScan.nextLine()+"\n\n");
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					break;	
				}
			}
		} catch (FileNotFoundException e) {
			Controleur.getInstance().printLog(null, e.getMessage());
		}
	}

	@Override
	public void dictionary() {
		Controleur.printLog(Level.INFO, "Gestion des dictionnaires");
		JOptionPane.showMessageDialog(null, "Gestion des dictionnaires.");
	}

	@Override
	public void voiceModel() {
		Controleur.printLog(Level.INFO, "Gestion des modèles vocaux");
		JOptionPane.showMessageDialog(null, "Gestion des modèles vocaux.");
	}

}
