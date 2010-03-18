package fr.alma.asr.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
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
	private JButton jLabelOpen;
	private JButton jLabelSave;

	private final MainWindow mainWindow;
	private final ImageIcon micOn = new ImageIcon(getClass().getResource(
			"/icones/micOnWhite.png"));
	private final ImageIcon micOff = new ImageIcon(getClass().getResource(
			"/icones/micOff.png"));

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
			{
				jPanelRecord = new JPanel();
				FlowLayout jPanelButtonLayout = new FlowLayout();
				jPanelButtonLayout.setAlignment(FlowLayout.CENTER);
				jPanelRecord.setLayout(jPanelButtonLayout);
				this.add(jPanelRecord, BorderLayout.CENTER);
				{
					final JToggleButton rec = new JToggleButton(micOff);

					jPanelRecord.add(rec);
					rec.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent evt) {
							onRec = !onRec;
							if (onRec) {
								Controleur.getInstance().openMic();
								Controleur.getInstance().setLastAction(
										"Reconnaissance vocale démarrée.");
								rec.setIcon(micOn);
								Controleur.getInstance().setCurrentModified(true);
							} else {
								Controleur.getInstance().closeMic();
								Controleur.getInstance().setLastAction(
										"Reconnaissance vocale stoppée.");
								rec.setIcon(micOff);
								Controleur.getInstance().setAllUnModified();
							}

						}
					});
				}
			}
			{
				jPanelEdit = new JPanel();
				this.add(jPanelEdit, BorderLayout.WEST);
				{
					jLabelSave = new JButton();
					ImageIcon recordImage = new ImageIcon(getClass()
							.getResource("/icones/pdf.png"));
					jLabelSave.setIcon(recordImage);
					jLabelSave.addActionListener(new ActionListener() {

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
					jPanelEdit.add(jLabelSave);
				}
				{
					jLabelOpen = new JButton();
					jPanelEdit.add(jLabelOpen);
					ImageIcon recordImage = new ImageIcon(getClass()
							.getResource("/icones/print.png"));
					jLabelOpen.setIcon(recordImage);
					jPanelEdit.add(jLabelSave);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
