package fr.alma.asr.utils;

import java.awt.Graphics2D;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTextPane;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Tool class used to export file in pdf
 * @author Damien Lévin
 * 
 */
public class FileExporter {

	/**
	 * Method which provides a way create PDF documents.
	 * @param shapes Defines if we use shape instead of fonts
	 * @param jTextPane The component to printout
	 * @param outputFile The filePath of the output pdf
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
				if (shapes)
					g2 = tp.createGraphicsShapes(500, 500);
				else
					g2 = tp.createGraphics(500, 500);
				jTextPane.print(g2);
				g2.dispose();
				cb.addTemplate(tp, 30, 300);
			}
		} catch (Exception e) {
			Logger.getLogger("fr.alma.asr").log(Level.SEVERE, e.getMessage());
		}
		document.close();
	}
}
