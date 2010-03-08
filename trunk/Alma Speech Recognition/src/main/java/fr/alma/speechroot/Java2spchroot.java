package fr.alma.speechroot;

import java.io.File;
import java.io.IOException;

import fr.alma.speechroot.RegQuery.StreamReader;

/**
 * Implémentation de l'interface du moteur Speechroot.
 * @author Cédric Krommenhoek
 */
public class Java2spchroot implements TreatMessageInterface {

	public static native int micoff();
	public static native int micon(String save_audio_flag);
	public static native int startEngine();
	public static native int stopEngine();
	public static native int RegisterRecognitionCB();
	public static native void initInterfaceToSmapi(TreatMessageInterface tmi, String msg, String text);
	public static native void endInterfaceToSmapi();
	public static native int AdaptVoiceDialog();
	public static native int AdaptVocabDialog();
	
	private static final String REGQUERY_UTIL = "reg query ";
	private static final String REGSTR_TOKEN = "REG_SZ";
	private static final String SPEECHROOT_FOLDER = REGQUERY_UTIL +
	  "\"HKLM\\SOFTWARE\\IBM\\VoiceType\\Engine\\Directories\" /v bin";
	
	static {
		File dll = new File(getSpchrootPath() + "/java2spchroot.dll");
		try {
			System.load(dll.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
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
	 * @see fr.alma.speechroot.TreatMessageInterface#treatMsgFunction(java.lang.String, java.lang.String)
	 */
	@Override
	public int treatMsgFunction(String msg, String body) {
		System.out.println("Message : " + msg);
		System.out.println("Corps : " + body);
		return 0;
	}
	
}
