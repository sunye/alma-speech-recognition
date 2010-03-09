package fr.alma.speechroot;

/**
 * Classe de contrôle du moteur Speechroot.
 * @author Cédric Krommenhoek
 */
public class Moteur {
	
	/** Classe contenant la fonction de callback. */
	private TreatMessageInterface java2spchroot;
	
	/**
	 * Constructeur.
	 */
	public Moteur() {
		this.java2spchroot = new Java2spchroot();
		this.connect();
	}

	/**
	 * Connexion du moteur.
	 */
	public void connect() {
		Java2spchroot.initInterfaceToSmapi(this.java2spchroot, "init message", "init body");
	}
	
	/**
	 * Déconnexion du moteur.
	 */
	public void disconnect() {
		Java2spchroot.endInterfaceToSmapi();
	}
	
	/**
	 * Démarrage du moteur.
	 * @return vrai si le moteur est correctement démarré
	 */
	public boolean start() {
		return Java2spchroot.startEngine() == 0;
	}
	
	/**
	 * Arrêt du moteur.
	 * @return vrai si le moteur est correctement arrêté
	 */
	public boolean stop() {
		return Java2spchroot.stopEngine() == 0;
	} 
	
	/**
	 * Ouverture du microphone.
	 * @param save vrai pour sauvegarder le flux audio
	 * @return vrai si le microphone est correctement activé
	 */
	public boolean micon(boolean save) {
		String msg;
		if (save) {
			msg = "SAVE_ON";
		} else {
			msg = "SAVE_OFF";
		}
		return Java2spchroot.micon(msg) == 0;
	}
	
	/**
	 * Fermeture du microphone.
	 * @return vrai si le microphone est correctement fermé
	 */
	public boolean micoff() {
		return Java2spchroot.micoff() == 0;
	}
	
	/**
	 * Ouverture de l'interface de gestion de la voix.
	 * @return vrai si l'opération s'est correctement déroulée
	 */
	public boolean adaptVoiceModelDialog() {
		return Java2spchroot.AdaptVoiceDialog() == 0;
	}
	
	/**
	 * Ouverture de l'interface de gestion des dictionnaires vocaux.
	 * @return vrai si l'opération s'est correctement déroulée
	 */
	public boolean adaptDictionnaryDialog() {
		return Java2spchroot.AdaptVocabDialog() == 0;
	}
	
}