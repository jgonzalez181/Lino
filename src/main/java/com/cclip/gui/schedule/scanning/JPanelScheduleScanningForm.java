package com.cclip.gui.schedule.scanning;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JTabbedPane;
import javax.swing.JTable;

import com.cclip.gui.util.JPanelForm;
import com.cclip.model.schedule.scanning.ScheduleScanning;

public class JPanelScheduleScanningForm extends JPanelForm {

	private static final long serialVersionUID = 9182874068298148232L;

	public JTabbedPane jTabbedPane1;

	private JPanelScheduleScanningGeneralForm jPanelScheduleScanningGeneralForm1;
	private JPanelScheduleScanningItem jPanelScheduleScanningItem1;
	public JPanelScheduleScanningTotal jPanelScheduleScanningTotal1;

	private JPanelScheduleScanning jPanelScheduleScanning;

	public JPanelScheduleScanningForm(JPanelScheduleScanning jPanelScheduleScanning) {

		this.jPanelScheduleScanning = jPanelScheduleScanning;

		initComponents();

	}

	public void setDto(Object dto, JTable jTable, Integer row) {

		ScheduleScanning scheduleScanning = (ScheduleScanning) dto;

		jPanelScheduleScanningGeneralForm1.setScheduleScanning(scheduleScanning, jTable, row);
		jPanelScheduleScanningItem1.find(scheduleScanning);
		jPanelScheduleScanningTotal1.find(scheduleScanning);
	}

	private void initComponents() {

		jPanelScheduleScanningGeneralForm1 = new JPanelScheduleScanningGeneralForm(jPanelScheduleScanning);
		jPanelScheduleScanningGeneralForm1.jPanelScheduleScanningForm = this;
		jPanelScheduleScanningItem1 = new JPanelScheduleScanningItem(jPanelScheduleScanning);
		jPanelScheduleScanningItem1.jPanelScheduleScanningForm = this;
		jPanelScheduleScanningTotal1 = new JPanelScheduleScanningTotal();
		jPanelScheduleScanningTotal1.jPanelScheduleScanningForm = this;

		jTabbedPane1 = new JTabbedPane();
		jTabbedPane1.setBackground(Color.WHITE);

		jTabbedPane1.addTab("Barrido", jPanelScheduleScanningGeneralForm1);
		jTabbedPane1.addTab("Parcelas", jPanelScheduleScanningItem1);
		jTabbedPane1.addTab("Totales", jPanelScheduleScanningTotal1);

		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout(3, 3));
		this.add(jTabbedPane1);
	}

}
