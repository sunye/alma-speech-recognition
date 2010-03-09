package fr.alma.speechroot;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * Classe de correspondance entre la DLL et Java.
 * @author Cédric Krommenhoek
 */
public class Java2spchroot implements TreatMessageInterface {

	/**
	 * Fermeture du microphone
	 * @return 0 en cas de succès
	 */
	public static native int micoff();
	
	/**
	 * Ouverture du microphone.
	 * @param save_audio_flag Paramètre de sauvegarde du flux audio
	 * @return 0 en cas de succès
	 */
	public static native int micon(String save_audio_flag);
	
	/**
	 * Démarrage du moteur.
	 * @return 0 en cas de succès
	 */
	public static native int startEngine();
	
	/**
	 * Arrêt du moteur.
	 * @return 0 en cas de succès
	 */
	public static native int stopEngine();
	
	public static native int RegisterRecognitionCB();
	
	/**
	 * Fonction d'initialisation de l'application.
	 * @param tmi La classe contenant la fonction de callback
	 * @param msg Message (à ne pas laisser vide)
	 * @param text Corps (à ne pas laisser vide)
	 */
	public static native void initInterfaceToSmapi(TreatMessageInterface tmi, String msg, String text);
	
	/**
	 * Fermeture de l'application.
	 */
	public static native void endInterfaceToSmapi();
	
	public static native int AdaptVoiceDialog();
	public static native int AdaptVocabDialog();
//	public static native int ManageUserDico();

	/** Initialisation de la requête sur la base de registre. */
	private static final String REGQUERY_UTIL = "reg query ";
	/** Clé registre. */
	private static final String REGSTR_TOKEN = "REG_SZ";
	/** Emplacement de la clé registre de Speechroot. */
	private static final String SPEECHROOT_FOLDER = REGQUERY_UTIL +
	"\"HKLM\\SOFTWARE\\IBM\\VoiceType\\Engine\\Directories\" /v bin";

	/** Chargement de la DLL. */
	static {
		File dll = new File(getSpchrootPath() + "/java2spchroot.dll");
		try {
			System.load(dll.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Méthode d'accès à l'emplacement de Speechroot à l'aide de la base de registre.
	 * @return L'emplacement du dossier "bin" de Speechroot
	 */
	public static String getSpchrootPath() {
		Process process;
		try {
			process = Runtime.getRuntime().exec(SPEECHROOT_FOLDER);
			StreamReader reader = new StreamReader(process.getInputStream());			
			reader.start();
			process.waitFor();
			reader.join();		    
			String result = reader.getResult();
			int p = result.indexOf(REGSTR_TOKEN);		    
			if (p == -1) {
				return null;
			}		    
			return result.substring(p + REGSTR_TOKEN.length()).trim();		    
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Classe interne utilisée pour la lecture de la base de registre.
	 * @author Cédric Krommenhoek
	 */
	static class StreamReader extends Thread {
		private InputStream is;
		private StringWriter sw;

		StreamReader(InputStream is) {
			this.is = is;
			sw = new StringWriter();
		}

		public void run() {
			try {
				int c;
				while ((c = is.read()) != -1)
					sw.write(c);
			}
			catch (IOException e) { ; }
		}

		String getResult() {
			return sw.toString();
		}
	}

	/**
	 * @see fr.alma.speechroot.TreatMessageInterface#treatMsgFunction(java.lang.String, java.lang.String)
	 */
	@Override
	public int treatMsgFunction(String msg, String body) {
		System.out.println("Message : " + msg);
		System.out.println("Corps : " + body);
		return 0;
	}

}
