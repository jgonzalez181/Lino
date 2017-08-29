package com.cclip.gui.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.cclip.util.UtilComponent;

public abstract class JPanelListSimple extends JPanelForm {

	private static final long serialVersionUID = -3193984553678654584L;

	private JSplitPane jSplitPane;
	private JScrollPaneJTable jScrollPaneJTable;
	private JPanelForm jPanelForm;

	protected List<Object> list;
	protected Object[][] model;
	protected Object dto;

	public JPanelListSimple() {
		jScrollPaneJTable = new JScrollPaneJTable();
	}

	public void find(Object dto) {
		this.dto = dto;
		
		jScrollPaneJTable.clearData();
		
		if (dto == null) {

			return;
		}

		searchData();
	}

	protected void initComponents(JPanelForm jPanelForm) {

		this.jPanelForm = jPanelForm;

		this.setLayout(new BorderLayout(3, 3));
		this.setBackground(Color.WHITE);

		// -----------------------------------------------------------

		jScrollPaneJTable.getjTable().getSelectionModel()
				.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						jTableEvt();
					}
				});

		if (jScrollPaneJTable.getjTable().getModel().getRowCount() > 0) {

			jScrollPaneJTable.getjTable().getSelectionModel()
					.setSelectionInterval(0, 0);
			jTableEvt();
		}

		jScrollPaneJTable.showData(null);

		// ---------------------------------------------------------------------

		jSplitPane = new JSplitPane();
		jSplitPane.setDividerSize(5);
		jSplitPane.setLeftComponent(jScrollPaneJTable);
		jSplitPane.setRightComponent(jPanelForm);
		jSplitPane.setDividerLocation(jScrollPaneJTable.getWidthSumColumn());
		this.add(jSplitPane, BorderLayout.CENTER);

	}

	private void jTableEvt() {
		try {
			if (list != null
					&& jScrollPaneJTable.getjTable().getSelectedRow() > -1) {

				jPanelForm.setDto(list.get(jScrollPaneJTable.getjTable()
						.getSelectedRow()), jScrollPaneJTable.getjTable(), jScrollPaneJTable.getjTable()
						.getSelectedRow());
			}
		} catch (Exception e) {
			UtilComponent.logger(e);
		}
	}

	@SuppressWarnings("rawtypes")
	protected void addColumnMetaData(String name, Class type, int width) {
		jScrollPaneJTable.addColumnMetaData(name, type, width);
	}

	public void searchData() {
		try {
			jScrollPaneJTable.clearData();
			if(dto == null){
				
				return;
			}
			
			jPanelForm.setDto(null, null, null);

			jScrollPaneJTable.clearData();
			find();
			jScrollPaneJTable.showData(model);

		} catch (Exception e) {

			UtilComponent.logger(e);
		}

	}
	
	

	public JTable getjTable() {
		return jScrollPaneJTable.getjTable();
	}

	protected abstract void find() throws Exception;

	public void showData(Object[][] model) {
		jScrollPaneJTable.showData(model);
	}
	
	

}
