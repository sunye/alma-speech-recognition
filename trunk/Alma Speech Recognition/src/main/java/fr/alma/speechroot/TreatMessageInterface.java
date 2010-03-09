package fr.alma.speechroot;

/**
 * Interface du moteur Speechroot.
 * @author Cédric Krommenhoek
 */
public interface TreatMessageInterface {

	/**
	 * Fonction de callback.
	 * @param msg Code du message
	 * @param body Corps du message
	 * @return 0 en cas de succès
	 */
	int treatMsgFunction(String msg, String body);
	
}
