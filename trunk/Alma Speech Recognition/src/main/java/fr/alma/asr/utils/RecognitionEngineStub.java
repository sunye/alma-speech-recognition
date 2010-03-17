package fr.alma.asr.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

import fr.alma.asr.gui.Controleur;

/**
 * Classe de bouchon des moteurs de reconnaissance vocale.
 * @author Cédric Krommenhoek
 */
public class RecognitionEngineStub implements RecognitionEngineInterface {

	@Override
	public boolean closeMic() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean openMic() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean start() {
		Controleur.printLog(Level.INFO, "Moteur démarré");
		return true;
	}

	@Override
	public boolean stop() {
		// TODO Auto-generated method stub
		return false;
	}

}
