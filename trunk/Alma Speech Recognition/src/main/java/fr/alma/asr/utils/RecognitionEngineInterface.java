package fr.alma.asr.utils;

/**
 * Interface des moteurs de reconnaissance vocale.
 * @author Cédric Krommenhoek
 */
public interface RecognitionEngineInterface {

	/**
	 * Démarrage du moteur.
	 * @return vrai si le moteur est correctement démarré
	 */
	boolean start();
	
	/**
	 * Arrêt du moteur.
	 * @return vrai si le moteur est correctement arrêté
	 */
	boolean stop();
	
	/**
	 * Ouverture du microphone.
	 * @return vrai si le microphone est correctement ouvert
	 */
	boolean openMic();
	
	/**
	 * Fermeture du microphone.
	 * @return vrai si le microphone est correctement fermé
	 */
	boolean closeMic();
	
	/**
	 * Gestion des dictionnaires.
	 */
	void dictionary();
	
	/**
	 * Gestion des modèles vocaux.
	 */
	void voiceModel();	
	
}
