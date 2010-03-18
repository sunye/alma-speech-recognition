package fr.alma.asr.utils;

import java.util.logging.Level;

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
		while (true) {
			try {
				Thread.sleep(3000);
				this.setChanged();
				this.notifyObservers("Voici une phrase reconnue par "
						+ "le moteur de reconnaissance vocale. ");
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				break;
			}
		}
	}

}
