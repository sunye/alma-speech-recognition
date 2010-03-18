/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.alma.asr.gui.tree;

import java.awt.Component;
import java.util.EventObject;
import javax.swing.JTree;
import javax.swing.event.CellEditorListener;
import javax.swing.tree.TreeCellEditor;

/**
 *
 * @author LeClubber
 */
public class MyTreeCellEditor implements TreeCellEditor {

	@Override
	public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Object getCellEditorValue() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean isCellEditable(EventObject anEvent) {
		return true;
	}

	@Override
	public boolean shouldSelectCell(EventObject anEvent) {
		return true;
	}

	@Override
	public boolean stopCellEditing() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void cancelCellEditing() {
	}

	@Override
	public void addCellEditorListener(CellEditorListener l) {
	}

	@Override
	public void removeCellEditorListener(CellEditorListener l) {
	}

}
