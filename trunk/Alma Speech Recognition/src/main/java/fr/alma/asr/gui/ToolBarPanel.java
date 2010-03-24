package fr.alma.asr.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class ToolBarPanel extends javax.swing.JPanel {
	private JPanel jPanelRecord;
	private boolean onRec = false;

	private JPanel jPanelEdit;
	private JButton buttonPrint;
	private JButton buttonPdf;
	
	private final MainWindow mainWindow;
	private final ImageIcon micOn = new ImageIcon(getClass().getResource(
			"/icones/micOnWhite.png"));
	private final ImageIcon micOff = new ImageIcon(getClass().getResource(
			"/icones/micOff.png"));
	private final ImageIcon scrollOn = new ImageIcon(getClass().getResource(
	"/icones/autoScrollOn.png"));
	private final ImageIcon scrollOff = new ImageIcon(getClass().getResource(
	"/icones/autoScrollOff.png"));
	
	private final JToggleButton rec = new JToggleButton(micOff);
	private final JToggleButton scroll = new JToggleButton(scrollOn);

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */

	public ToolBarPanel(MainWindow mainWindow) {
		super();
		this.mainWindow = mainWindow;
		initGUI();
	}

	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);

			jPanelRecord = new JPanel();
			FlowLayout jPanelButtonLayout = new FlowLayout();
			jPanelButtonLayout.setAlignment(FlowLayout.CENTER);
			jPanelRecord.setLayout(jPanelButtonLayout);
			this.add(jPanelRecord, BorderLayout.CENTER);

			jPanelRecord.add(scroll);
			jPanelRecord.add(rec);
			
			
			
			rec.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					onRec = !onRec;
					if (onRec) {
						rec.setIcon(micOn);
						Controleur.getInstance().setOnRec(true);						
					} else {						
						rec.setIcon(micOff);
						Controleur.getInstance().setOnRec(false);
					}

				}
			});

			scroll.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					if (!MainWindow.getInstance().getAutoScroll()) {
						scroll.setIcon(scrollOn);
						MainWindow.getInstance().setAutoScroll(true);						
					} else {						
						scroll.setIcon(scrollOff);
						MainWindow.getInstance().setAutoScroll(false);
					}

				}
			});

			jPanelEdit = new JPanel();
			this.add(jPanelEdit, BorderLayout.WEST);

			buttonPdf = new JButton();
			ImageIcon recordImage = new ImageIcon(getClass()
					.getResource("/icones/pdf.png"));
			buttonPdf.setIcon(recordImage);
			buttonPdf.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					JFileChooser fileChooser = new JFileChooser();
					fileChooser.showDialog(null, "Enregister en PDF");
					String outPutFile = fileChooser.getSelectedFile()
					.getAbsolutePath();

					if (outPutFile != null) {
						if (!outPutFile.toUpperCase().endsWith(".PDF")) {
							outPutFile += ".pdf";
						}

						Controleur.printOutPdf(outPutFile, mainWindow
								.getEditTextPane());
					}

				}
			});
			jPanelEdit.add(buttonPdf);


			buttonPrint = new JButton();
			jPanelEdit.add(buttonPrint);
			ImageIcon printImage = new ImageIcon(getClass()
					.getResource("/icones/print.png"));
			buttonPrint.setIcon(printImage);
			jPanelEdit.add(buttonPdf);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public JButton getPrintButton(){
		return buttonPrint;
	}
	
	public JButton getPdfButton(){
		return buttonPdf;
	}
	
	public JToggleButton getRecButton(){
		return rec;
	}
	
	public JToggleButton getScrollButton(){
		return scroll;
	}

}
