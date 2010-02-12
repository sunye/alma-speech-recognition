package fr.alma.gui;
import java.awt.BorderLayout;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
//
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

	private JTextArea textArea;
	private JScrollPane jScrollPane1;
	private ToolBarEditPannel toolBarEditPannel;
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
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			this.setBorder(BorderFactory.createTitledBorder("Edition du cours"));
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1, BorderLayout.CENTER);
				jScrollPane1.setPreferredSize(new java.awt.Dimension(60, 19));
				{
					textArea = new JTextArea();
					jScrollPane1.setViewportView(textArea);
					textArea.addMouseListener(new MouseAdapter() {
						
						@Override
						public void mouseClicked(MouseEvent e) {
							if (e.getButton() == MouseEvent.BUTTON3){
								MenuText.show((Component)e.getSource(), e.getX(), e.getY());
							}
						}
						
					});
				}
			}
			{
				MenuText = new MenuTextArea();

				setComponentPopupMenu(this, MenuText);
			}
			setComponentPopupMenu(this, new MenuTextArea());
			{
				toolBarEditPannel = new ToolBarEditPannel();
				this.add(toolBarEditPannel, BorderLayout.NORTH);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	* Auto-generated method for setting the popup menu for a component
	*/
	private void setComponentPopupMenu(final java.awt.Component parent, final javax.swing.JPopupMenu menu) {
		parent.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				if(e.isPopupTrigger())
					menu.show(parent, e.getX(), e.getY());
			}
			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {
				if(e.isPopupTrigger())
					menu.show(parent, e.getX(), e.getY());
			}
		});
	}

}
