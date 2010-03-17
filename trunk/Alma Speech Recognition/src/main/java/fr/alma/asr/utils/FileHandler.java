package fr.alma.asr.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * Classe utilitaire de log dans un fichier.
 * 
 * @author Cédric Krommenhoek
 */
public final class FileHandler extends Handler {

	/** Instance du singleton. */
	private static FileHandler instance;

	/** Le flux de sortie. */
	private FileWriter fileWriter;

	/** Niveau de log. */
	private Level level;

	/**
	 * Constructeur utilisant le fichier log.txt par défaut.
	 */
	private FileHandler() {
		this.setFile("log.txt");
		this.level = Level.INFO;
	}

	/**
	 * Méthode statique d'accès au singleton.
	 * 
	 * @return l'instance du singleton
	 */
	public static FileHandler getInstance() {
		if (instance == null) {
			instance = new FileHandler();
		}
		return instance;
	}

	/**
	 * Spécification du fichier de sortie.
	 * 
	 * @param pathname
	 *            le chemin du fichier
	 */
	public void setFile(String pathname) {
		try {
			File file = new File(pathname);
			if (!file.exists()) {
				file.createNewFile();
			}
			if (file.isFile() && file.canWrite()) {
				this.fileWriter = new FileWriter(file, true);
			}
		} catch (IOException e) {
			Logger.getLogger("fr.alma.asr").log(Level.SEVERE, e.getMessage());
		}
	}

	@Override
	public void close() {
		try {
			this.fileWriter.close();
		} catch (IOException e) {
			Logger.getLogger("fr.alma.asr").log(Level.SEVERE, e.getMessage());
		}
	}

	@Override
	public void flush() {
		try {
			this.fileWriter.flush();
		} catch (IOException e) {
			Logger.getLogger("fr.alma.asr").log(Level.SEVERE, e.getMessage());
		}
	}

	@Override
	public void publish(LogRecord record) {
		if (record.getLevel().intValue() >= this.level.intValue()) {
			try {
				Date date = new Date(record.getMillis());
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"dd MMM yyyy HH:mm:ss");
				this.fileWriter.write(dateFormat.format(date) + " - "
						+ record.getLevel() + " - " + record.getMessage()
						+ "\n");
			} catch (IOException e) {
				Logger.getLogger("fr.alma.asr").log(Level.SEVERE,
						e.getMessage());
			}
		}
	}

	@Override
	public void setLevel(Level level) {
		this.level = level;
		super.setLevel(level);
	}

}
