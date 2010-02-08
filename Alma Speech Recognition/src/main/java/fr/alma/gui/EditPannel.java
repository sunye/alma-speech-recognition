package fr.alma.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.BoxLayout;

import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

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
public class EditPannel extends javax.swing.JPanel {

	private JTextArea jTextArea;
	private JTabbedPane jTabbedPane1;
	private JScrollPane jScrollPaneText;
	private MenuTextArea MenuText;


	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */

	public EditPannel() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			BoxLayout thisLayout = new BoxLayout(this,
					javax.swing.BoxLayout.X_AXIS);
			this.setLayout(thisLayout);
			{
				jTabbedPane1 = new JTabbedPane();
				this.add(jTabbedPane1);
				jTabbedPane1.setPreferredSize(new java.awt.Dimension(400, 300));
				{
					jScrollPaneText = new JScrollPane();
					jTabbedPane1.addTab("jScrollPaneText", null,
							jScrollPaneText, null);
					{
						jTextArea = new JTextArea();
						jScrollPaneText.setViewportView(jTextArea);
						jTextArea.setText("jTextArea");
						jTextArea.addMouseListener(new MouseAdapter() {
					
							public void mouseClicked(MouseEvent e) {
								if (e.getButton() == MouseEvent.BUTTON3){
									MenuText.show((Component)e.getSource(), e.getX(), e.getY());
								}
							}
		
						});
					}
				}
			}
			{
				MenuText = new MenuTextArea();

				this.add(MenuText);
			}
			this.add(new MenuTextArea());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
