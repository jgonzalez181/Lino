package com.cclip.gui.util;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.cclip.util.UtilJTable;

public class JScrollPaneJTable extends JScrollPane {

	private static final long serialVersionUID = 4923901229033665165L;

	public JTable jTable;

	@SuppressWarnings("rawtypes")
	private Class[] columnTypes = new Class[0];
	private String[] columnNames = new String[0];
	private int[] columnWidth = new int[0];
	private boolean[] columnEdit = new boolean[0];

	private Object[][] model;

	private List<List> modelTmp = new ArrayList<List>();

	public JScrollPaneJTable() {

		initComponents();
	}

	private void initComponents() {

		jTable = new JTable();
		jTable.setAutoCreateRowSorter(false);
		
		jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// jTable1.getSelectionModel().addListSelectionListener(listSelectionListener);

		// jTable1.getSelectionModel().addListSelectionListener(
		// new ListSelectionListener() {
		// public void valueChanged(ListSelectionEvent event) {
		// // try {
		// // if (rl != null && jTable1.getSelectedRow() > -1) {
		// // jPanelScheduleCensusForm1
		// // .setScheduleCensus((ScheduleCensus) rl
		// // .getList()
		// // .get(jTable1.getSelectedRow()));
		// // }
		// // } catch (Exception e) {
		// // e.printStackTrace();
		// // }
		//
		// }
		// });
		//
		// if (jTable1.getModel().getRowCount() > 0) {
		// jTable1.getSelectionModel().setSelectionInterval(0, 0);
		// try {
		// // if (rl != null && jTable1.getSelectedRow() > -1) {
		// // jPanelScheduleCensusForm1
		// // .setScheduleCensus((ScheduleCensus) rl.getList()
		// // .get(jTable1.getSelectedRow()));
		// // }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }

		setBackground(Color.WHITE);
		setViewportView(jTable);

	}

	public void setAutoCreateRowSorter(boolean autoCreateRowSorter) {
		jTable.setAutoCreateRowSorter(autoCreateRowSorter);
	}

	public int getWidthSumColumn() {
		int c = 0;
		for (int i = 0; i < jTable.getColumnModel().getColumnCount(); i++) {
			c += jTable.getColumnModel().getColumn(i).getWidth();
		}

		return c;
	}

	public void addColumnMetaData(String name, Class type, int width) {
		addColumnMetaData(name, type, width, false);
	}

	public void addColumnMetaData(String name, Class type, int width, boolean edit) {

		Class[] columnTypesTmp = new Class[columnTypes.length + 1];
		String[] columnNamesTmp = new String[columnNames.length + 1];
		int[] columnWidthTmp = new int[columnWidth.length + 1];
		boolean[] columnEditTmp = new boolean[columnEdit.length + 1];

		for (int i = 0; i < columnTypes.length; i++) {
			columnTypesTmp[i] = columnTypes[i];
		}

		for (int i = 0; i < columnNames.length; i++) {
			columnNamesTmp[i] = columnNames[i];
		}

		for (int i = 0; i < columnWidth.length; i++) {
			columnWidthTmp[i] = columnWidth[i];
		}

		for (int i = 0; i < columnEdit.length; i++) {
			columnEditTmp[i] = columnEdit[i];
		}

		columnTypesTmp[columnTypesTmp.length - 1] = type;
		columnNamesTmp[columnNamesTmp.length - 1] = name;
		columnWidthTmp[columnWidthTmp.length - 1] = width;
		columnEditTmp[columnEditTmp.length - 1] = edit;

		columnTypes = columnTypesTmp;
		columnNames = columnNamesTmp;
		columnWidth = columnWidthTmp;
		columnEdit = columnEditTmp;
	}

	public void setEditable(boolean b, int index) {
		columnEdit[index] = b;
	}

	public void addFieldValue(int index, Object fieldValue) {

		if (modelTmp.size() <= index) {
			List row = new ArrayList();

			row.add(fieldValue);

			modelTmp.add(row);
		} else {
			modelTmp.get(index).add(fieldValue);
		}

	}

	public void clearData() {

		modelTmp = new ArrayList<List>();

		model = new Object[0][columnNames.length];
		showData(model);
		// model = null;
		// showData(null);
	}

	public void showData() {

	}

	public void showData(Object[][] model) {
		this.model = model;
		// model = new Object[modelTmp.size()][columnNames.length];
		//
		// for (int i = 0; i < modelTmp.size(); i++) {
		// for (int j = 0; j < modelTmp.get(i).size(); j++) {
		// model[i][j] = modelTmp.get(i).get(j);
		// }
		// }

		DefaultTableModel defaultTableModel = new DefaultTableModel(model, columnNames) {

			private static final long serialVersionUID = 7798875997013763645L;

			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return columnEdit[columnIndex];
			}
		};

		jTable.setModel(defaultTableModel);

		UtilJTable.setWidthColumn(jTable.getColumnModel(), columnWidth);

		if (jTable.getModel().getRowCount() > 0) {

			jTable.getSelectionModel().setSelectionInterval(0, 0);
		}
	}

	public JTable getjTable() {
		return jTable;
	}

}
