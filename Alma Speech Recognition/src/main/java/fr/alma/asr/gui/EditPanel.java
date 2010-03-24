package fr.alma.asr.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.FlavorEvent;
import java.awt.datatransfer.FlavorListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.StringReader;
import java.util.Enumeration;
import java.util.Hashtable;
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
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListCellRenderer;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
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
	private HTMLEditorKit editorKit;
	private HTMLDocument document;
	private UndoManager undoManager;

	private JScrollPane jScrollPane1;
	private JPanel toolBarEditPanel;
	private MenuTextArea menuText;

	private final MainWindow mainWindow;

	private JComboBox jComboBoxFont;
	private JComboBox jComboBoxFontSize;

	private JButton textColorButton;
	private Color selectedColor;

	private JToolBar toolBar;
	private JToolBar toolBar1;
	private JToolBar toolBar2;

	public EditPanel(MainWindow mainWindow) {
		super();
		this.mainWindow = mainWindow;
		initGUI();
	}

	private void initGUI() {
		try {
			//BoxLayout thisLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
			//GridLayout thisLayout = new GridLayout(2,1);
			BorderLayout thisLayout = new BorderLayout();
			//GridBagLayout thisLayout = new GridBagLayout();
			
			final JSplitPane jSplitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
			jSplitPane1.setDividerSize(0);
			jSplitPane1.addComponentListener(new ComponentListener(){
				@Override
				public void componentResized(ComponentEvent arg0) {
					if(toolBarEditPanel.getWidth()<=406){
						jSplitPane1.setDividerLocation(103);
					}
					else if(toolBarEditPanel.getWidth()<=619){
						jSplitPane1.setDividerLocation(76);
					}
					else {
						jSplitPane1.setDividerLocation(40);
					}					
				}

				@Override
				public void componentHidden(ComponentEvent e) {}

				@Override
				public void componentMoved(ComponentEvent e) {}

				@Override
				public void componentShown(ComponentEvent e) {}
				
			});
			
			this.setLayout(thisLayout);
			this.setBorder(BorderFactory.createTitledBorder("Edition du cours"));
			{
				jScrollPane1 = new JScrollPane();
				jScrollPane1.setViewportView(getTextPane());
				jScrollPane1.setMinimumSize(new Dimension(250,210));
				jScrollPane1.setPreferredSize(new Dimension(250,210));
			}
			{
				toolBarEditPanel = new JPanel();
				FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);

				toolBar = new JToolBar();
				toolBar1 = new JToolBar();
				toolBar2 = new JToolBar();

				toolBarEditPanel.setLayout(flowLayout);
				toolBarEditPanel.setMinimumSize(new Dimension(250,40));
				initToolBar();
				toolBarEditPanel.add(toolBar);
				toolBarEditPanel.add(toolBar1);
				toolBarEditPanel.add(toolBar2);

				toolBarEditPanel.setVisible(true);
				jSplitPane1.add(toolBarEditPanel);
				jSplitPane1.add(jScrollPane1);
				this.add(jSplitPane1,BorderLayout.CENTER);

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

		toolBar1.add(getJComboBoxFontSize());
		toolBar1.add(getJComboBoxFont());


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

		
		a = new StyledEditorKit.AlignmentAction("left", 0);
		leftButton = toolBar2.add(a);
		leftButton.setText("");
		leftButton.setIcon(new ImageIcon(getClass().getResource(
				"/txtformat/format-justify-left.png")));

		a = new StyledEditorKit.AlignmentAction("center", 1);
		centerButton = toolBar2.add(a);
		centerButton.setText("");
		centerButton.setIcon(new ImageIcon(getClass().getResource(
				"/txtformat/format-justify-center.png")));

		a = new StyledEditorKit.AlignmentAction("right", 2);
		rightButton = toolBar2.add(a);
		rightButton.setText("");
		rightButton.setIcon(new ImageIcon(getClass().getResource(
				"/txtformat/format-justify-right.png")));

		toolBar2.addSeparator();
		h1Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {

					String selText = textPane.getSelectedText();
					if (selText != null) {
						int selStart = textPane.getSelectionStart();
						int textLength = selText.length();
						if (textLength > 0) {
							insertHTMLBalise("<SPAN STYLE='color: blue; font-size: 20pt'>"
									+ selText + "</SPAN>", selStart, textLength);
						}
					}
				} catch (Exception ignoredForNow) {

					Controleur.printLog(Level.SEVERE, ignoredForNow
							.getMessage());
				}
			}
		});
		h1Button.setText("Titre");
		toolBar2.add(h1Button);

		h2Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {

					String selText = textPane.getSelectedText();
					if (selText != null) {

						int selStart = textPane.getSelectionStart();
						int textLength = selText.length();
						if (textLength > 0) {
							insertHTMLBalise("<SPAN STYLE='color: green ; font-size: 25pt'>"
									+ selText + "</SPAN>", selStart, textLength);
						}
					}
				} catch (Exception ignoredForNow) {

					Controleur.printLog(Level.SEVERE, ignoredForNow
							.getMessage());
				}
			}
		});
		h2Button.setText("Titre 2");
		toolBar2.add(h2Button);

		h3Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {

					String selText = textPane.getSelectedText();
					if (selText != null) {

						int selStart = textPane.getSelectionStart();
						int textLength = selText.length();
						if (textLength > 0) {
							insertHTMLBalise("<SPAN STYLE='color: red; font-size: 30pt'>"
									+ selText + "</SPAN>", selStart, textLength);
						}
					}
				} catch (Exception ignoredForNow) {

					Controleur.printLog(Level.SEVERE, ignoredForNow
							.getMessage());
				}
			}
		});
		h3Button.setText("Titre 3");
		toolBar2.add(h3Button);

	}

	private void insertHTMLBalise(String html, int location, int textLength)
			throws IOException {
		try {

			document.remove(location, textLength);
			HTMLEditorKit kit = (HTMLEditorKit) textPane.getEditorKit();
			StringReader reader = new StringReader(html);
			kit.read(reader, document, location);

			editorKit.write(System.out, document, 0, document.getLength());

		} catch (BadLocationException e) {
			// logger.error("Failed to insert HTML", e);
		}
	}

	/**
	 * 
	 * @return JTextPane textPane
	 */

	public JTextPane getTextPane() {

		if (textPane == null) {

			textPane = new JTextPane();
			editorKit = new HTMLEditorKit();
			document = (HTMLDocument) editorKit.createDefaultDocument();
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
									if (!undoManager.canUndo()) {
										Controleur.getInstance()
												.setCurrentModified(false);
									}
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

			textPane.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
				}

				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void keyPressed(KeyEvent e) {
					if (!((e.getKeyCode() == KeyEvent.VK_Z)
							&& (e.isControlDown()) || (e.isControlDown()) || (e
							.isAltDown() || (e.isShiftDown())))) {
						Controleur.getInstance().setCurrentModified(true);
					}

					if ((e.getKeyCode() == KeyEvent.VK_Y)
							&& (e.isControlDown())) {
						Controleur.getInstance().setCurrentModified(true);
					}

				}
			});
			textPane.setEditable(true);
			textPane.setEditorKit(editorKit);
			textPane.setDocument(document);

			textPane.addCaretListener(new CaretListener() {
				@Override
				public void caretUpdate(CaretEvent e) {
					if (textPane.getSelectedText() == null) {
						Controleur.getInstance().activateCopyCut(false);
					} else {
						Controleur.getInstance().activateCopyCut(true);
					}

				}
			});
			
		

			menuText = new MenuTextArea();
			this.setComponentPopupMenu(menuText);

			// Add a listener on clipborad to enable or disable pasteMenuItem
			final Clipboard clipbd = getToolkit().getSystemClipboard();

			clipbd.addFlavorListener(new FlavorListener() {
				@Override
				public void flavorsChanged(FlavorEvent e) {
					if (clipbd.getAvailableDataFlavors().length > 0) {
						Controleur.getInstance().activatePast(true);
					} else {
						Controleur.getInstance().activatePast(false);
					}
				}
			});
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
	public String getFormatedText() {
		try {
			return document.getText(0, document.getLength());
		} catch (BadLocationException e) {
			Logger.getLogger("fr.alma.asr").log(Level.SEVERE, e.getMessage());
			return null;
		}
	}

	public void setText(String lesson) {
		try {
			if (lesson != null) {
				document.insertString(0, lesson, null);
				undoManager.discardAllEdits();

			}
		} catch (BadLocationException e) {
			Controleur.printLog(Level.INFO, e.getLocalizedMessage());
		}
	}

}
