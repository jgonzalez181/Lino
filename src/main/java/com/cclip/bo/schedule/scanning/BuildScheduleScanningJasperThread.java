package com.cclip.bo.schedule.scanning;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.utiljdbc.ConnectionWrapper;

import com.cclip.model.schedule.scanning.ScheduleScanning;
import com.cclip.util.LinoProperties;
import com.cclip.util.UtilCadastre;

public class BuildScheduleScanningJasperThread /*extends Thread implements Runnable*/ {

	// private ConnectionWrapper connectionWrapper;

	private LinoProperties linoProperties;
	private ScheduleScanning scheduleScanning;
	private List<String> scheduleScanningList;
	private ConnectionWrapper connectionWrapper;

	public BuildScheduleScanningJasperThread(ConnectionWrapper connectionWrapper, LinoProperties linoProperties, ScheduleScanning scheduleScanning, List<String> scheduleScanningList) {
		super();
		this.connectionWrapper = connectionWrapper;
		this.linoProperties = linoProperties;
		this.scheduleScanning = scheduleScanning;
		this.scheduleScanningList = scheduleScanningList;
	}

	public void run() {
//		System.out.println("\t=================== INICIO CREACION PLANILLA DE BARRIDO PARA " + scheduleScanning.getCadastralCode() + " ===================="
//				+ new Timestamp(System.currentTimeMillis()));

		try {
			buildScheduleScanningJasper(scheduleScanning);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		connectionWrapper.close(connectionWrapper);

		scheduleScanningList.add(scheduleScanning.getCadastralCode());

//		System.out.println("\t=================== FIN CREACION PLANILLA DE BARRIDO PARA " + scheduleScanning.getCadastralCode() + " ====================" + new Timestamp(System.currentTimeMillis()));
	}

	private void buildScheduleScanningJasper(ScheduleScanning scheduleScanning) throws Exception {

		Map<String, Object> hm = new HashMap<String, Object>();
		hm.put("pId", scheduleScanning.getId());

		String path = linoProperties.getUrlFiles() + File.separatorChar + "jasper";

		String filename = path + File.separatorChar + "rptPlanillaBarrido" + ".jasper";

		JasperPrint jasperPrint = JasperFillManager.fillReport(filename, hm, connectionWrapper.getConnection());

		path = linoProperties.getUrlFiles() + File.separatorChar + "barridos";

		JasperExportManager.exportReportToPdfFile(jasperPrint, path + File.separatorChar + UtilCadastre.formatCadastralCodeB(scheduleScanning.getCadastralCode()) + "_"
				+ scheduleScanning.getSchedule().getYear() + "_" + scheduleScanning.getNumberScanning() + ".pdf");

	}

}
