package fr.alma.speechroot;

/**
 * Interface du moteur Speechroot.
 * @author Cédric Krommenhoek
 */
public interface TreatMessageInterface {

	/**
	 * Fonction de callback.
	 * @param msg
	 * @param body
	 * @return
	 */
	int treatMsgFunction(String msg, String body);
	
}
