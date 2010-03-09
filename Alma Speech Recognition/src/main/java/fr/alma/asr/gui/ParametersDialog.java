package fr.alma.asr.gui;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;

import javax.swing.JLabel;
import javax.swing.JPanel;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class ParametersDialog extends javax.swing.JDialog {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JLabel labelWorkPlanPanel;
	private JCheckBox cbShowPlan;
	private JCheckBox cbShowCourses;
	private JPanel spchRootPanel;
	private JComboBox cbWokrPlanPosition;
	private JLabel jLabel1;
	
	private static ParametersDialog instance;
	//private MainWindow main = MainWindow.getInstance();
	private Controller controleur = Controller.getInstance();
	
	public static ParametersDialog getInstance(){
		if(instance==null)
			instance = new ParametersDialog();
		return instance;
	}
	
	private ParametersDialog() {
		super(MainWindow.getInstance());
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				this.setTitle("Paramètres");
				this.setModal(true);
				this.setLocationRelativeTo(null);
				
				
				JTabbedPane tabbedPane = new JTabbedPane();
				getContentPane().setLayout(new GridLayout(1, 1));
				getContentPane().add(tabbedPane);

				getContentPane().add(tabbedPane);
				{
					JPanel guiParamPane = new JPanel();
					GridBagLayout thisLayout = new GridBagLayout();
					guiParamPane.setLayout(thisLayout);
					tabbedPane.addTab("Interface Graphique", null, guiParamPane, null);
					{
						labelWorkPlanPanel = new JLabel();
						guiParamPane.add(labelWorkPlanPanel, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						labelWorkPlanPanel.setText("Position du plan par défaut");
					}
					{
						jLabel1 = new JLabel();
						guiParamPane.add(jLabel1, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					}
					{
						cbShowPlan = new JCheckBox();
						guiParamPane.add(cbShowPlan, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 20, 0, 0), 0, 0));
						cbShowPlan.setText("Afficher Plan");
						cbShowPlan.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								controleur.setWorkShowPlan(((JCheckBox)evt.getSource()).isSelected());
							}
						});
					}
					{
						ComboBoxModel cbWokrPlanPositionModel = 
							new DefaultComboBoxModel(
									new String[] { "Droite", "Gauche" });
						cbWokrPlanPosition = new JComboBox();
						guiParamPane.add(cbWokrPlanPosition, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 20, 0, 0), 0, 0));
						cbWokrPlanPosition.setModel(cbWokrPlanPositionModel);
						cbWokrPlanPosition.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								controleur.setWorkPlanPosition(((JComboBox)evt.getSource()).getSelectedItem().toString());
							}
						});
					}
					{
						cbShowCourses = new JCheckBox();
						guiParamPane.add(cbShowCourses, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 20, 0, 0), 0, 0));
						cbShowCourses.setText("Afficher les modules");
						cbShowCourses.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								controleur.setWorkShowCourses(((JCheckBox)evt.getSource()).isSelected());
							}
						});
					}
					thisLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.1};
					thisLayout.rowHeights = new int[] {18, 30, 21, 22, 22, 28, 24, 20};
					thisLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.1};
					thisLayout.columnWidths = new int[] {32, 176, 201, 7};
				}
				{
					spchRootPanel = new JPanel();
					tabbedPane.addTab("Reconnaissance vocale", null, spchRootPanel, null);
				}
			}
			pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setVisible(boolean b){
		setValues();
		super.setVisible(b);
	}
	
	public void setValues(){
		cbShowCourses.setSelected(controleur.getWorkShowCourses());
		cbWokrPlanPosition.setSelectedItem(controleur.getWorkPlanPosition());
		cbShowPlan.setSelected(controleur.getWorkShowPlan());
	}
}
