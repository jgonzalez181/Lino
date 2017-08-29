package com.cclip.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.utiljdbc.ConnectionWrapper;
import org.utiljdbc.DataSourceWrapper;

import com.cclip.Context;
import com.cclip.model.schedule.scanning.ScheduleScanning;

public class UtilJasper {

	protected DataSourceWrapper dataSourceWrapper;

	public void setDataSourceWrapper(DataSourceWrapper dataSourceWrapper) {
		this.dataSourceWrapper = dataSourceWrapper;
	}

	public void buildScheduleScanning(ScheduleScanning scheduleScaninng) throws Exception {

		ConnectionWrapper connectionWrapper = null;

		try {

			// 103b0f75-3e71-441f-8831-b392430f0fb3

			connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();

			Map<String, Object> hm = new HashMap<String, Object>();
			hm.put("pId", scheduleScaninng.getId());

			LinoProperties linoProperties = Context.getBean("linoProperties", LinoProperties.class);

			String path = linoProperties.getUrlFiles() + File.separatorChar + "jasper";

			String filename = path + File.separatorChar + "rptPlanillaBarrido" + ".jasper";

			// System.out.println(scheduleScaninng.getId());

			JasperPrint jasperPrint = JasperFillManager.fillReport(filename, hm, connectionWrapper.getConnection());

			path = linoProperties.getUrlFiles() + File.separatorChar + "barridos";

			JasperExportManager.exportReportToPdfFile(jasperPrint, path + File.separatorChar + UtilCadastre.formatCadastralCodeB(scheduleScaninng.getCadastralCode()) + "_"
					+ scheduleScaninng.getSchedule().getYear() + "_" + scheduleScaninng.getNumberScanning() + ".pdf");

			connectionWrapper.close(connectionWrapper);

			// InputStream inputStream = new FileInputStream
			// ("reports/test_jasper.jrxml");
			//
			// JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
			// JasperReport jasperReport =
			// JasperCompileManager.compileReport(jasperDesign);
			// JasperPrint jasperPrint =
			// JasperFillManager.fillReport(jasperReport, hm,
			// connectionWrapper.getConnection());
			// JasperExportManager.exportReportToPdfFile(jasperPrint,
			// "c:/reports/test_jasper.pdf");

			// JasperViewer.viewReport(jasperPrint, false);

		} catch (Exception e) {

			throw e;
		} catch (Throwable e) {

			throw new Exception(e);
		} finally {
			connectionWrapper.close(connectionWrapper);
		}
	}

}
