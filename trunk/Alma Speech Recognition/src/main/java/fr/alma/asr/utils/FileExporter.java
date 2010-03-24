package fr.alma.asr.utils;

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.rtf.RTFEditorKit;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

import fr.alma.asr.gui.Controleur;

/**
 * Tool class used to export file in pdf.
 * 
 * @author Damien Lévin
 */
public final class FileExporter {

	/**
	 * Private constructor.
	 */
	private FileExporter() {}
	
	/**
	 * Method which provides a way create PDF documents.
	 * 
	 * @param shapes
	 *            Defines if we use shape instead of fonts
	 * @param jTextPane
	 *            The component to printout
	 * @param outputFile
	 *            The filePath of the output pdf
	 */
	public static void createPdf(boolean shapes, JTextPane jTextPane,
			String outputFile) {
		Document document = new Document();
		try {
			PdfWriter writer;
			if (shapes) {
				writer = PdfWriter.getInstance(document, new FileOutputStream(
						outputFile));
			} else {
				writer = PdfWriter.getInstance(document, new FileOutputStream(
						outputFile));
				document.open();
				PdfContentByte cb = writer.getDirectContent();
				PdfTemplate tp = cb.createTemplate(500, 500);
				Graphics2D g2;
				if (shapes) {
					g2 = tp.createGraphicsShapes(500, 500);
				} else {
					g2 = tp.createGraphics(500, 500);
				}
				jTextPane.print(g2);
				g2.dispose();
				cb.addTemplate(tp, 30, 300);
			}
		} catch (Exception e) {
			Logger.getLogger("fr.alma.asr").log(Level.SEVERE, e.getMessage());
		}
		document.close();
	}
	
	/**
	 * Method which provides a way create PDF documents.
	 * @param jTextPane  The component to printout
	 * @param outputFile The filePath of the output pdf
	 */
	public static void createRtf(JTextPane jTextPane,
			String outputFile) {
		
		RTFEditorKit editorKit = new RTFEditorKit();
		
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(new File(outputFile));
			editorKit.write(fileOutputStream, jTextPane.getDocument(), 0, jTextPane.getDocument().getLength());
		} catch (IOException e) {
			Controleur.printLog(Level.SEVERE, e.getMessage());
		} catch (BadLocationException e) {
			Controleur.printLog(Level.SEVERE, e.getMessage());
		}	
		
	}
}
