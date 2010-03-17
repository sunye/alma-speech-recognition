package fr.alma.asr.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListCellRenderer;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.rtf.RTFEditorKit;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

/**
 * Text Panel which contains a text area and its edit toolbar.
 * 
 * @author damien
 * 
 */
@SuppressWarnings("serial")
public class EditPanel extends javax.swing.JPanel {

	private JTextPane textPane;
	private RTFEditorKit editorKit;
	private Document document;
	private UndoManager undoManager;

	private JScrollPane jScrollPane1;
	private JPanel toolBarEditPannel;
	private MenuTextArea menuText;

	private MainWindow mainWindow;

	private JComboBox jComboBoxFont;
	private JComboBox jComboBoxFontSize;

	private JButton textColorButton;
	private Color selectedColor;

	
	private JToolBar toolBar;
	
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
				jScrollPane1.setViewportView(getTextPane());

			}
			{
				toolBarEditPannel = new JPanel(new FlowLayout(FlowLayout.LEFT));
				toolBar = new JToolBar();
				toolBarEditPannel.add(toolBar);
				initToolBar();
				this.add(toolBarEditPannel, BorderLayout.NORTH);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @return JToolBar editToolBar
	 */
	public void initToolBar() {

		
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

		boldButton = toolBar.add(a);
		boldButton.setText("");
		boldButton.setIcon(new ImageIcon(getClass().getResource(
				"/txtformat/format-text-bold.png")));

		a = textPane.getActionMap().get("font-italic");

		italicButton = toolBar.add(a);
		italicButton.setText("");
		italicButton.setIcon(new ImageIcon(getClass().getResource(
				"/txtformat/format-text-italic.png")));

		a = textPane.getActionMap().get("font-underline");

		underlineButton = toolBar.add(a);
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
		toolBar.add(chooseColorButton);

		textColorButton.setAction(new StyledEditorKit.ForegroundAction("Noir",
				Color.black));
		textColorButton.setIcon(new ImageIcon(getClass().getResource(
				"/txtformat/format-text-color.png")));
		textColorButton.setText("");

		toolBar.add(textColorButton);

		toolBar.add(getJComboBoxFontSize());
		toolBar.add(getJComboBoxFont());

		toolBar.addSeparator();

		a = textPane.getActionMap().get(StyledEditorKit.cutAction);
		menuText.getjMenuItemCouper().addActionListener(a);
		mainWindow.getCutMenuItem().addActionListener(a);

		a = textPane.getActionMap().get(StyledEditorKit.copyAction);
		menuText.getjMenuItemCopier().addActionListener(a);
		mainWindow.getCopyMenuItem().addActionListener(a);

		a = textPane.getActionMap().get(StyledEditorKit.pasteAction);
		menuText.getjMenuItemColler().addActionListener(a);
		mainWindow.getPasteMenuItem().addActionListener(a);

		a = textPane.getActionMap().get("Annuler");
		a.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl Z"));
		mainWindow.getUndoMenuItem().setAction(a);

		a.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl Y"));
		a = textPane.getActionMap().get("Rétablir");
		mainWindow.getRedoMenuItem().setAction(a);

		toolBar.addSeparator();
		a = new StyledEditorKit.AlignmentAction("left", 0);
		leftButton = toolBar.add(a);
		leftButton.setText("");
		leftButton.setIcon(new ImageIcon(getClass().getResource(
				"/txtformat/format-justify-left.png")));

		a = new StyledEditorKit.AlignmentAction("center", 1);
		centerButton = toolBar.add(a);
		centerButton.setText("");
		centerButton.setIcon(new ImageIcon(getClass().getResource(
				"/txtformat/format-justify-center.png")));

		a = new StyledEditorKit.AlignmentAction("right", 2);
		rightButton = toolBar.add(a);
		rightButton.setText("");
		rightButton.setIcon(new ImageIcon(getClass().getResource(
				"/txtformat/format-justify-right.png")));

		toolBar.addSeparator();
		h1Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					String selText = textPane.getSelectedText();
					int selStart = textPane.getSelectionStart();
					int textLength = selText.length();

					System.out.println(selText);
				} catch (Exception ignoredForNow) {
				}
			}
		});
		h1Button.setText("Titre");
		toolBar.add(h1Button);

		h2Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					editorKit.write(System.out, document, 0, document
							.getLength());
				} catch (Exception ignoredForNow) {
					ignoredForNow.printStackTrace();
				}
			}
		});
		h2Button.setText("Titre 2");
		toolBar.add(h2Button);

		h3Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					String selText = textPane.getSelectedText();

					int selStart = textPane.getSelectionStart();
					int textLength = selText.length();

				} catch (Exception ignoredForNow) {
					ignoredForNow.printStackTrace();
				}
			}
		});
		h3Button.setText("Titre 3");
		toolBar.add(h3Button);
	
	}

	/**
	 * 
	 * @return JTextPane textPane
	 */

	public JTextPane getTextPane() {

		if (textPane == null) {

			textPane = new JTextPane();
			editorKit = new RTFEditorKit();
			document = editorKit.createDefaultDocument();
			undoManager = new UndoManager();

			// Listen for undo and redo events
			document.addUndoableEditListener(new UndoableEditListener() {
				@Override
				public void undoableEditHappened(UndoableEditEvent e) {
					undoManager.addEdit(e.getEdit());

				}
			});

			textPane.getActionMap().put("Supprimer",
					new AbstractAction("Supprimer") {

						public void actionPerformed(ActionEvent evt) {

							String selText = textPane.getSelectedText();

							if (selText != null) {

								int selStart = textPane.getSelectionStart();
								int textLength = selText.length();

								try {
									document.remove(selStart, textLength);
								} catch (BadLocationException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}

						}

					});

			textPane.getActionMap().put("Annuler",
					new AbstractAction("Annuler") {

						public void actionPerformed(ActionEvent evt) {
							try {
								if (undoManager.canUndo()) {
									undoManager.undo();
								}
							} catch (CannotUndoException e) {
								e.printStackTrace();
								;
							}
						}
					});

			textPane.getActionMap().put("Rétablir",
					new AbstractAction("Rétablir") {
						public void actionPerformed(ActionEvent evt) {
							try {
								if (undoManager.canRedo()) {
									undoManager.redo();
								}
							} catch (CannotUndoException e) {
								e.printStackTrace();
								;
							}
						}
					});

			textPane.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON3) {
						menuText.show((Component) e.getSource(), e.getX(), e
								.getY());
					}
				}

			});

			textPane.setEditable(true);
			textPane.setEditorKit(editorKit);
			textPane.setDocument(document);

			menuText = new MenuTextArea();
			this.setComponentPopupMenu(menuText);

		}
		return textPane;
	}

	/**
	 * @return JComboBox fontSizeCombobox
	 */
	private JComboBox getJComboBoxFontSize() {

		if (jComboBoxFontSize == null) {

			Vector<Integer> fontSize = new Vector<Integer>();
			for (int i = 10; i < 35; i++) {
				fontSize.add(i);
			}

			ComboBoxModel jComboBoxFontSizeModel = new DefaultComboBoxModel(
					fontSize);
			jComboBoxFontSize = new JComboBox();
			jComboBoxFontSize.setModel(jComboBoxFontSizeModel);

			jComboBoxFontSize.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					textPane.getActionMap().put(
							"size",
							new StyledEditorKit.FontSizeAction("size",
									(Integer) jComboBoxFontSize
											.getSelectedItem()));
					textPane.getActionMap().get("size").actionPerformed(e);
				}
			});
		}
		return jComboBoxFontSize;
	}

	/**
	 * @return JComboBox fontCombobox
	 */
	private JComboBox getJComboBoxFont() {
		if (jComboBoxFont == null) {

			// Get the local graphics environment
			GraphicsEnvironment ge;
			ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

			// Get the font names from the graphics environment
			String[] fontNames = ge.getAvailableFontFamilyNames();

			ComboBoxModel jComboBoxFontModel = new DefaultComboBoxModel(
					fontNames);
			jComboBoxFont = new JComboBox();
			jComboBoxFont.setModel(jComboBoxFontModel);

			// Change the Cell renderer
			jComboBoxFont.setRenderer(new DefaultListCellRenderer() {

				@Override
				public Component getListCellRendererComponent(JList list,
						Object value, int index, boolean isSelected,
						boolean cellHasFocus) {

					// Get the default cell renderer
					JLabel label = (JLabel) ((ListCellRenderer) super
							.getListCellRendererComponent(list, value, index,
									isSelected, cellHasFocus));

					Font itemFont = new Font((String) value, Font.PLAIN, 15);

					if (itemFont.canDisplayUpTo((String) value) == -1) {
						// Set the font of the label
						label.setFont(itemFont);
					} else {
						String fontName = label.getFont().getFontName();
						Font largerFont = new Font(fontName, Font.PLAIN, 15);

						label.setFont(largerFont);
					}

					return label;
				};

			});

			jComboBoxFont.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					textPane.getActionMap().put(
							"font",
							new StyledEditorKit.FontFamilyAction("font",
									(String) jComboBoxFont.getSelectedItem()));
					textPane.getActionMap().get("font").actionPerformed(e);
				}
			});

		}
		return jComboBoxFont;
	}

	// For each Repaint we set the action for Undo and Redo
	// Each time the tab is displayed
	@Override
	public void paint(Graphics g) {

		super.paint(g);
		Action a;

		a = textPane.getActionMap().get("Annuler");
		a.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl Z"));
		mainWindow.getUndoMenuItem().setAction(a);

		a = textPane.getActionMap().get("Rétablir");
		a.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl Y"));
		mainWindow.getRedoMenuItem().setAction(a);

		a = textPane.getActionMap().get("Supprimer");
		a.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl S"));
		mainWindow.getDeleteMenuItem().setAction(a);
	}
	
	
	
	/**
	 * 
	 * @return String formated Text of the pane
	 */
	public String getFormatedText(){
		try {
			System.out.println(document.getText(0,document.getLength()));
			return document.getText(0,document.getLength());
		} catch (BadLocationException e) {
			Logger.getLogger("fr.alma.asr").log(Level.SEVERE, e.getMessage());
			return null;
		}
	}
}
