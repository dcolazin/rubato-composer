package org.rubato.rubettes.bigbang.view.subview;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.rubato.rubettes.bigbang.view.View;
import org.rubato.rubettes.bigbang.view.controller.ViewController;
import org.rubato.rubettes.bigbang.view.controller.display.ViewParametersTableModelListener;

public class JViewParametersScrollPane extends JScrollPane implements View {
	
	private JTable viewParametersTable;
	private JTable rowNameTable;
	
	public JViewParametersScrollPane(ViewController controller) {
		this.viewParametersTable = new JTable(new ViewParametersTableModel());
		this.viewParametersTable.setCellSelectionEnabled(false);
		//this.viewParametersTable.setPreferredSize(new Dimension(100, 100));
		this.viewParametersTable.getModel().addTableModelListener(new ViewParametersTableModelListener(controller));
		this.viewParametersTable.setDragEnabled(false);
		this.rowNameTable = new JTable(new ViewParametersRowHeaderTableModel());
		this.rowNameTable.setEnabled(false);
		this.rowNameTable.setDragEnabled(false);
		this.rowNameTable.getColumnModel().getColumn(0).setHeaderValue("");
		this.setViewportView(this.viewParametersTable);
		this.setRowHeaderView(this.rowNameTable);
		this.setCorner(JScrollPane.UPPER_LEFT_CORNER, this.rowNameTable.getTableHeader());
		this.setPreferredSize(new Dimension(200, 100));
		this.viewParametersTable.setPreferredScrollableViewportSize(new Dimension(10, 10));
		this.rowNameTable.setPreferredScrollableViewportSize(new Dimension(100, 100));
		//this.rowNameTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		//this.setColumnHeaderView(this.viewParametersTable)
		//this.setCorner(JScrollPane.UPPER_LEFT_CORNER, this.rowNameTable.getTableHeader());
	}
	
	private void updateViewParameters(int[] newParameters) {
		((ViewParametersTableModel)this.viewParametersTable.getModel()).setData(newParameters);
	}
	
	private void updateRowNames(List<String> rowNames) {
		((ViewParametersRowHeaderTableModel)this.rowNameTable.getModel()).setValues(rowNames);
		this.updateRowCount(rowNames.size());
	}
	
	private void updateRowCount(int rowCount) {
		((ViewParametersTableModel)this.viewParametersTable.getModel()).setRowCount(rowCount);
		//this.viewParametersTable.setPreferredSize(new Dimension(100, 100/6*rowCount));
		this.setPreferredSize(new Dimension(200, 100/6*rowCount+20));
	}
	
	public void modelPropertyChange(PropertyChangeEvent event) {
		String propertyName = event.getPropertyName();
		if (propertyName.equals(ViewController.DISPLAY_NOTES)) {
			this.updateRowNames(((DisplayObjectList)event.getNewValue()).getValueNames());
		} else if (propertyName.equals(ViewController.SELECTED_VIEW_PARAMETERS)) {
			this.updateViewParameters((int[]) event.getNewValue());
		} else if (propertyName.equals(ViewController.VIEW_PARAMETERS_VISIBLE)) {
			this.setVisible(((Boolean)event.getNewValue()).booleanValue());
		}
	}

}