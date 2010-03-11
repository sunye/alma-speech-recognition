package fr.alma.asr.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import fr.alma.asr.utils.FileExporter;

//import com.sun.java.swing.plaf.nimbus.TextPanePainter;
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
public class EditPanel extends javax.swing.JPanel {

	private JTextPane textPane;
	private HTMLEditorKit htmlEditorKit;
	private HTMLDocument htmlDocument;

	private JScrollPane jScrollPane1;
	private JPanel toolBarEditPannel;
	private MenuTextArea menuText;

	private MainWindow mainWindow;

	private JButton textColorButton;
	private Color selectedColor;

	public EditPanel(MainWindow mainWindow) {
		super();
		this.mainWindow = mainWindow;
		initGUI();
	}

	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			this
					.setBorder(BorderFactory
							.createTitledBorder("Edition du cours"));
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1, BorderLayout.CENTER);
				jScrollPane1.setPreferredSize(new java.awt.Dimension(60, 19));
				{
					textPane = new JTextPane();
					htmlEditorKit = new HTMLEditorKit();
					htmlDocument = (HTMLDocument) htmlEditorKit
							.createDefaultDocument();
					textPane.setContentType("text/html");
					textPane.setEditable(true);
					textPane.setEditorKit(htmlEditorKit);
					textPane.setDocument(htmlDocument);
					menuText = new MenuTextArea();

					jScrollPane1.setViewportView(textPane);
					textPane.addMouseListener(new MouseAdapter() {

						@Override
						public void mouseClicked(MouseEvent e) {
							if (e.getButton() == MouseEvent.BUTTON3) {
								menuText.show((Component) e.getSource(), e
										.getX(), e.getY());
							}
						}

					});
				}
			}
			setComponentPopupMenu(this, new MenuTextArea());
			{
				toolBarEditPannel = new JPanel();
				this.add(toolBarEditPannel, BorderLayout.NORTH);
				toolBarEditPannel.add(getToolBar(), BorderLayout.CENTER);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public JToolBar getToolBar() {

		JToolBar bar = new JToolBar();

		JButton boldButton = new JButton();
		JButton italicButton = new JButton();
		JButton underlineButton = new JButton();

		textColorButton = new JButton();
		JButton chooseColorButton = new JButton();

		JButton leftButton = new JButton();
		JButton centerButton = new JButton();
		JButton rightButton = new JButton();

		JButton h1Button = new JButton();
		JButton h2Button = new JButton();
		JButton h3Button = new JButton();

		Action a = textPane.getActionMap().get("font-bold");

		boldButton = bar.add(a);
		boldButton.setText("");
		boldButton.setIcon(new ImageIcon(getClass().getResource(
				"/txtformat/format-text-bold.png")));

		a = textPane.getActionMap().get("font-italic");

		italicButton = bar.add(a);
		italicButton.setText("");
		italicButton.setIcon(new ImageIcon(getClass().getResource(
				"/txtformat/format-text-italic.png")));

		a = textPane.getActionMap().get("font-underline");

		underlineButton = bar.add(a);
		underlineButton.setText("");
		underlineButton.setIcon(new ImageIcon(getClass().getResource(
				"/txtformat/format-text-underline.png")));

		chooseColorButton.setText("");
		chooseColorButton.setIcon(new ImageIcon(getClass().getResource(
				"/txtformat/format-fill-color.png")));
		chooseColorButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedColor = JColorChooser.showDialog(EditPanel.this,
						"Couleur de la police", Color.black);
				EditPanel.this.textColorButton
						.setAction(new StyledEditorKit.ForegroundAction(null,
								selectedColor));
				textColorButton.setIcon(new ImageIcon(getClass().getResource(
						"/txtformat/format-text-color.png")));
				textColorButton.setText("");

			}
		});
		bar.add(chooseColorButton);

		textColorButton.setAction(new StyledEditorKit.ForegroundAction("Noir",
				Color.black));
		textColorButton.setIcon(new ImageIcon(getClass().getResource(
				"/txtformat/format-text-color.png")));
		textColorButton.setText("");

		bar.add(textColorButton);

		bar.addSeparator();
		a = textPane.getActionMap().get(StyledEditorKit.cutAction);
		menuText.getjMenuItemCouper().addActionListener(a);
		mainWindow.getCutMenuItem().setAction(a);

		a = textPane.getActionMap().get(StyledEditorKit.copyAction);
		menuText.getjMenuItemCopier().addActionListener(a);
		mainWindow.getCopyMenuItem().setAction(a);

		a = textPane.getActionMap().get(StyledEditorKit.pasteAction);
		menuText.getjMenuItemColler().addActionListener(a);
		mainWindow.getPasteMenuItem().setAction(a);

		bar.addSeparator();
		a = new StyledEditorKit.AlignmentAction("left", 0);
		leftButton = bar.add(a);
		leftButton.setText("");
		leftButton.setIcon(new ImageIcon(getClass().getResource(
				"/txtformat/format-justify-left.png")));

		a = new StyledEditorKit.AlignmentAction("center", 1);
		centerButton = bar.add(a);
		centerButton.setText("");
		centerButton.setIcon(new ImageIcon(getClass().getResource(
				"/txtformat/format-justify-center.png")));

		a = new StyledEditorKit.AlignmentAction("right", 2);
		rightButton = bar.add(a);
		rightButton.setText("");
		rightButton.setIcon(new ImageIcon(getClass().getResource(
				"/txtformat/format-justify-right.png")));

		bar.addSeparator();
		h1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					HTML.Tag htmlTag = HTML.Tag.H1;
					Hashtable htmlAttribs = new Hashtable();
					String selText = textPane.getSelectedText();

					int selStart = textPane.getSelectionStart();
					int textLength = selText.length();

					textPane.select(selStart, selStart + textLength);
					SimpleAttributeSet sasTag = new SimpleAttributeSet();
					SimpleAttributeSet sasAttr = new SimpleAttributeSet();

					Enumeration attribEntries = htmlAttribs.keys();
					while (attribEntries.hasMoreElements()) {
						Object entryKey = attribEntries.nextElement();
						Object entryValue = htmlAttribs.get(entryKey);
						sasAttr.addAttribute(entryKey, entryValue);
						htmlAttribs.remove(entryKey);
					}
					sasTag.addAttribute(htmlTag, sasAttr);
					textPane.setCharacterAttributes(sasTag, false);
					textPane.setText(textPane.getText());
					textPane.select(selStart, selStart + textLength);
				} catch (Exception ignoredForNow) {
				}
			}
		});
		h1Button.setText("Titre");
		bar.add(h1Button);

		h2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					HTML.Tag htmlTag = HTML.Tag.H2;
					Hashtable htmlAttribs = new Hashtable();
					String selText = textPane.getSelectedText();
					int selStart = textPane.getSelectionStart();
					int textLength = selText.length();

					textPane.select(selStart, selStart + textLength);
					SimpleAttributeSet sasTag = new SimpleAttributeSet();
					SimpleAttributeSet sasAttr = new SimpleAttributeSet();

					Enumeration attribEntries = htmlAttribs.keys();
					while (attribEntries.hasMoreElements()) {
						Object entryKey = attribEntries.nextElement();
						Object entryValue = htmlAttribs.get(entryKey);
						sasAttr.addAttribute(entryKey, entryValue);
						htmlAttribs.remove(entryKey);
					}
					sasTag.addAttribute(htmlTag, sasAttr);
					textPane.setCharacterAttributes(sasTag, false);
					textPane.setText(textPane.getText());
					textPane.select(selStart, selStart + textLength);
				} catch (Exception ignoredForNow) {
				}
			}
		});
		h2Button.setText("Titre 2");
		bar.add(h2Button);

		h3Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					HTML.Tag htmlTag = HTML.Tag.H3;
					Hashtable htmlAttribs = new Hashtable();
					String selText = textPane.getSelectedText();

					int selStart = textPane.getSelectionStart();
					int textLength = selText.length();

					textPane.select(selStart, selStart + textLength);
					SimpleAttributeSet sasTag = new SimpleAttributeSet();
					SimpleAttributeSet sasAttr = new SimpleAttributeSet();

					Enumeration attribEntries = htmlAttribs.keys();
					while (attribEntries.hasMoreElements()) {
						Object entryKey = attribEntries.nextElement();
						Object entryValue = htmlAttribs.get(entryKey);
						sasAttr.addAttribute(entryKey, entryValue);
						htmlAttribs.remove(entryKey);
					}
					sasTag.addAttribute(htmlTag, sasAttr);
					textPane.setCharacterAttributes(sasTag, false);
					textPane.setText(textPane.getText());
					textPane.select(selStart, selStart + textLength);

				} catch (Exception ignoredForNow) {
				}
			}
		});
		h3Button.setText("Titre 3");
		bar.add(h3Button);

		return bar;
	}

	/**
	 * Auto-generated method for setting the popup menu for a component
	 */
	private void setComponentPopupMenu(final java.awt.Component parent,
			final javax.swing.JPopupMenu menu) {
		parent.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				if (e.isPopupTrigger())
					menu.show(parent, e.getX(), e.getY());
			}

			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {
				if (e.isPopupTrigger())
					menu.show(parent, e.getX(), e.getY());
			}
		});
	}

	/**
	 * 
	 * @return JTextPane textPane
	 */
	public JTextPane getTextPane() {
		return textPane;
	}
}
