package fr.alma.asr.gui.tabbedpane;

/*
 * TabComponentDemo.java requires one additional file:
 *   ButtonTabComponent.java
 */
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import fr.alma.asr.gui.WorkPanel;


public class TabContainer extends JTabbedPane {

	public TabContainer() {
		super();
		setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);	
	}
	
	public void add(JPanel panel,String title,ImageIcon icon){
		super.add(title,panel);
		super.setTabComponentAt(super.getTabCount()-1, new TabbedPane(this, panel, title, icon));
	}
	
	public void setCurrentModified(boolean modified){
		Component pane = this.getSelectedComponent();
		if(pane instanceof WorkPanel){
			((TabbedPane)this.getTabComponentAt(this.getSelectedIndex())).setModified(modified);
			((TabbedPane)this.getTabComponentAt(this.getSelectedIndex())).repaint();
		}
	}
	
	public void setAllUnModified(){
		for(int i = 0; i<this.getTabCount() ; i++){
			Component pane = this.getComponentAt(i);
			if(pane instanceof WorkPanel){
				((TabbedPane)this.getTabComponentAt(i)).setModified(false);
			}
		}
	}


}
