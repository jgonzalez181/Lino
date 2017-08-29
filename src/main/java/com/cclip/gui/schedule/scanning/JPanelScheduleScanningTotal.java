package com.cclip.gui.schedule.scanning;

import com.cclip.Context;
import com.cclip.bo.schedule.scanning.ScheduleScanningResultTotalBo;
import com.cclip.gui.util.JScrollPaneJTableSimpleList;
import com.cclip.model.schedule.scanning.ScheduleScanning;
import com.cclip.model.schedule.scanning.ScheduleScanningResultTotal;

public class JPanelScheduleScanningTotal extends JScrollPaneJTableSimpleList {

	private static final long serialVersionUID = 8740413335514099146L;

	private int total = 0;
	
	public JPanelScheduleScanningForm jPanelScheduleScanningForm;

	public JPanelScheduleScanningTotal() {

		addColumnMetaData("id", String.class, 0);
		addColumnMetaData("Código Resultado", String.class, 120);
		addColumnMetaData("Resultado de Barrido", String.class, -1);
		addColumnMetaData("Total", Integer.class, 70);

		showData(null);

	}

	protected void find() throws Exception {

		ScheduleScanning scheduleScanning = (ScheduleScanning) dto;

		ScheduleScanningResultTotalBo bo = Context.getBean("scheduleScanningResultTotalBo", ScheduleScanningResultTotalBo.class);

		ScheduleScanningResultTotal[] list = bo.find(scheduleScanning.getId());

		model = new Object[list.length][4];

		total = 0;

		for (int i = 0; i < list.length; i++) {

			ScheduleScanningResultTotal scheduleScanningResultTotal = (ScheduleScanningResultTotal) list[i];

			model[i][0] = scheduleScanningResultTotal.getId();
			model[i][1] = scheduleScanningResultTotal.getCode();
			model[i][2] = scheduleScanningResultTotal.getName();
			model[i][3] = scheduleScanningResultTotal.getTotal();

			total += scheduleScanningResultTotal.getTotal();

		}
		
		jPanelScheduleScanningForm.jTabbedPane1.setTitleAt(2, "Totales (" + total + ")");
		
	}

}
