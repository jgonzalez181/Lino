package com.cclip.gui.schedule;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import com.cclip.gui.util.JPanelForm;

public class JPanelScheduleBatchForm extends JPanelForm {

	private static final long serialVersionUID = 9182874068298148232L;

	private JTabbedPane jTabbedPane1;

	private JPanelScheduleItem jPanelScheduleCensusItem;

	public JPanelScheduleBatchForm() {
		initComponents();
	}

	public void setDto(Object dto, JTable jTable, Integer row) {

		if (dto == null) {
			return;
		}

		Object[] objArray = (Object[]) dto;

		if (objArray[0] == null) {
			return;
		}

		jPanelScheduleCensusItem.find(objArray[0]);

	}

	private void initComponents() {

		jPanelScheduleCensusItem = new JPanelScheduleItem();

		jTabbedPane1 = new JTabbedPane();
		jTabbedPane1.setBackground(Color.WHITE);

		jTabbedPane1.addTab("Planillas de Censo", jPanelScheduleCensusItem);
		jTabbedPane1.addTab("Cierre", new JPanel());

		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout(3, 3));
		this.add(jTabbedPane1);
	}

}
