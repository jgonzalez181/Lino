package com.cclip.bo.schedule.scanning;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.utiljdbc.ConnectionWrapper;
import org.utiljdbc.MapperQueryArg;
import org.utiljdbc.MapperQueryOrderArg;
import org.utiljdbc.ResultList;

import com.cclip.dao.geo.cadastre.CustomCadastreDao;
import com.cclip.dao.schedule.scanning.CustomScheduleScanningDao;
import com.cclip.dao.schedule.scanning.CustomScheduleScanningItemDao;
import com.cclip.model.person.CensusTaker;
import com.cclip.model.schedule.Schedule;
import com.cclip.model.schedule.scanning.ScheduleScanning;
import com.cclip.model.schedule.scanning.ScheduleScanningItem;
import com.cclip.util.LinoProperties;
import com.cclip.util.UtilCadastre;

/** BO Barrido de Manzana Catastral */
public class CustomScheduleScanningBo extends ScheduleScanningBo {

	private CustomScheduleScanningDao dao1;
	private CustomScheduleScanningItemDao dao2;
	private CustomCadastreDao dao3;

	private LinoProperties linoProperties;

	public void setDao1(CustomScheduleScanningDao dao1) {
		this.dao1 = dao1;
	}

	public void setDao2(CustomScheduleScanningItemDao dao2) {
		this.dao2 = dao2;
	}

	public void setDao3(CustomCadastreDao dao3) {
		this.dao3 = dao3;
	}

	public void setLinoProperties(LinoProperties linoProperties) {
		this.linoProperties = linoProperties;
	}

	public ResultList find1(String scheduleId, String cadastralCode, Date scanningFrom, Date scanningTo, String scheduleBatchCode, Boolean scheduleBatch, /*
																																						 * Boolean
																																						 * scheduleBatchClose
																																						 * ,
																																						 */
			Boolean scanning, Boolean recorded, Boolean delay, String censusTakerId, String scheduleScanningId, Integer offSet, Integer limit) throws Exception {

		MapperQueryOrderArg[] orderList = { new MapperQueryOrderArg("schedule_year", false), new MapperQueryOrderArg("scanning", false), new MapperQueryOrderArg("cadastral_code", true),
				new MapperQueryOrderArg("number_scanning", false) };

		List<MapperQueryArg> argListTmp = new ArrayList<MapperQueryArg>();

		if (scheduleScanningId != null && scheduleScanningId.trim().length() > 0) {

			MapperQueryArg a = new MapperQueryArg();
			a.setEqualsTo("schedule_scanning_id", scheduleScanningId.trim());
			argListTmp.add(a);
		}

		if (scheduleId != null && scheduleId.toString().trim().length() > 0) {

			MapperQueryArg a = new MapperQueryArg();
			a.setEqualsTo("schedule_id", scheduleId.trim());
			argListTmp.add(a);
		}

		if (cadastralCode != null && cadastralCode.trim().length() > 0) {

			MapperQueryArg a = new MapperQueryArg();
			if (cadastralCode.trim().length() == 7) {
				a.setEqualsTo("cadastral_code", cadastralCode.trim());
			} else {
				a.setBeginsWith("cadastral_code", cadastralCode.trim());
			}

			argListTmp.add(a);

		}

		if (scanningFrom != null) {

			MapperQueryArg a = new MapperQueryArg();
			a.setMajorEqualsTo("scanning", scanningFrom);
			argListTmp.add(a);
		}

		if (scanningTo != null) {

			MapperQueryArg a = new MapperQueryArg();
			a.setMinorEqualsTo("scanning", scanningTo);
			argListTmp.add(a);
		}

		if (scheduleBatchCode != null && scheduleBatchCode.trim().length() > 0) {
			MapperQueryArg a = new MapperQueryArg();

			a.setEqualsToIgnoreCaseTraslate("schedule_batch_code", scheduleBatchCode.trim());
			argListTmp.add(a);

		}

		if (scheduleBatch != null) {

			if (scheduleBatch == true) {
				MapperQueryArg a = new MapperQueryArg();
				a.setIsNotNullTo("schedule_batch_id");
				argListTmp.add(a);
			} else {
				MapperQueryArg a = new MapperQueryArg();
				a.setIsNullTo("schedule_batch_id");
				argListTmp.add(a);
			}

		}

		// if (scheduleBatchClose != null) {
		//
		// if (scheduleBatchClose == true) {
		// MapperQueryArg a = new MapperQueryArg();
		// a.setIsNotNullTo("schedule_batch_close");
		// argListTmp.add(a);
		// } else {
		// MapperQueryArg a = new MapperQueryArg();
		// a.setIsNullTo("schedule_batch_close");
		// argListTmp.add(a);
		// }
		// }

		if (scanning != null) {

			if (scanning == true) {
				MapperQueryArg a = new MapperQueryArg();
				a.setIsNullTo("scanning");
				argListTmp.add(a);
			} else {
				MapperQueryArg a = new MapperQueryArg();
				a.setIsNotNullTo("scanning");
				argListTmp.add(a);
			}
		}

		if (recorded != null) {

			if (recorded == true) {
				MapperQueryArg a = new MapperQueryArg();
				a.setIsNullTo("recorded");
				argListTmp.add(a);
			} else {
				MapperQueryArg a = new MapperQueryArg();
				a.setIsNotNullTo("recorded");
				argListTmp.add(a);

			}
		}

		if (delay != null) {

			if (delay == true) {
				MapperQueryArg a = new MapperQueryArg();
				a.setMajorEqualsTo("schedule_batch_planned_delivered", new Date(System.currentTimeMillis()));
				// argList.add(a);
			}
		}

		if (censusTakerId != null && censusTakerId.trim().length() > 0) {

			MapperQueryArg a = new MapperQueryArg();

			a.setEqualsTo("census_taker_id", censusTakerId.trim());
			argListTmp.add(a);
		}

		MapperQueryArg[] argList = new MapperQueryArg[argListTmp.size()];

		argList = argListTmp.toArray(argList);

		return find1(argList, orderList, offSet, limit);

	}

	private ResultList find1(MapperQueryArg[] argList, MapperQueryOrderArg[] orderList, Integer offSet, Integer limit) throws Exception {

		ConnectionWrapper connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();
		ResultList r = dao1.find1(connectionWrapper, argList, orderList, offSet, limit);
		connectionWrapper.close(connectionWrapper);
		System.out.println(connectionWrapper.getSql()); // Con esta linea
		// podemos ver todos los
		// sql que se ejecutaron
		// con la misma
		// conexión.
		return r;

	}

	public ScheduleScanning[] find7(String cadastralCode, String scheduleId) throws Exception {

		if (cadastralCode == null || cadastralCode.trim().length() == 0) {
			return new ScheduleScanning[0];
		}

		if (scheduleId == null || scheduleId.trim().length() == 0) {
			return new ScheduleScanning[0];
		}

		MapperQueryOrderArg[] orderList = { new MapperQueryOrderArg("cadastral_code", true) };

		MapperQueryArg[] argList = new MapperQueryArg[4];

		MapperQueryArg a = new MapperQueryArg();
		a.setBeginsWith("cadastral_code", cadastralCode.trim());
		argList[0] = a;

		MapperQueryArg a2 = new MapperQueryArg();
		a2.setEqualsTo("schedule_id", scheduleId.trim());
		argList[1] = a2;

		MapperQueryArg a3 = new MapperQueryArg();
		a3.setIsNullTo("scanning");
		argList[2] = a3;

		MapperQueryArg a4 = new MapperQueryArg();
		a4.setIsNullTo("recorded");
		argList[3] = a4;

		return find6(argList, orderList);

	}

	private ScheduleScanning[] find6(MapperQueryArg[] argList, MapperQueryOrderArg[] orderList) throws Exception {

		ConnectionWrapper connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();
		ScheduleScanning[] r = dao1.find6(connectionWrapper, argList, orderList);
		connectionWrapper.close(connectionWrapper);
		// System.out.println(connectionWrapper.getSql()); // Con esta linea
		// podemos ver todos los sql que se ejecutaron con la misma conexión.
		return r;

	}

	public Integer[] find2(Boolean asc) {

		ConnectionWrapper connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();

		Integer[] r = dao1.find2(connectionWrapper, asc);

		connectionWrapper.close(connectionWrapper);

		return r;

	}

	public CensusTaker[] find3() {

		ConnectionWrapper connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();

		CensusTaker[] resultList = dao1.find3(connectionWrapper);

		connectionWrapper.close(connectionWrapper);

		return resultList;

	}

	public String[] find4(String scheduleId) {

		ConnectionWrapper connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();

		String[] resultList = dao1.find4(connectionWrapper, scheduleId);

		connectionWrapper.close(connectionWrapper);

		return resultList;

	}

	public String[] find5(String scheduleId, String dd) {

		ConnectionWrapper connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();

		String[] resultList = dao1.find5(connectionWrapper, scheduleId, dd);

		connectionWrapper.close(connectionWrapper);

		return resultList;

	}

	public ScheduleScanning[] buildScheduleScanning(String cadastralCodeDDZZ, Date plannedDelivered, CensusTaker censusTaker,
	/* ScheduleBatch scheduleBatch, */Schedule schedule) throws Exception {

		if (cadastralCodeDDZZ == null || cadastralCodeDDZZ.trim().length() != 4) {
			return null;
		}

		cadastralCodeDDZZ = cadastralCodeDDZZ.trim();

		// --------------------------------------------------------------

		ConnectionWrapper connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();

		try {

			connectionWrapper.begin(connectionWrapper);

			// --------------------------------------------------------------

			Timestamp start = new Timestamp(System.currentTimeMillis());

			// System.out.println("=================== INICIO CREACION PLANILLAS DE BARRIDO PARA "
			// + cadastralCodeDDZZ + " ====================" + start);

			List<ScheduleScanning> scheduleScanningList = new ArrayList<ScheduleScanning>();

			// devuelve los códigos de las manzanas (DD-ZZ-MMM) en base al
			// codigo del distrito y zona (DD-ZZ)
			String[] scheduleScanningCodeDDZZMMMList = dao3.find3(connectionWrapper, cadastralCodeDDZZ);

			for (String cadastralCodeDDZZMMM : scheduleScanningCodeDDZZMMMList) {

				BuildScheduleScanningThread thread = new BuildScheduleScanningThread(cadastralCodeDDZZMMM, plannedDelivered, censusTaker, schedule, dao1, dao2, dao3, connectionWrapper,
						scheduleScanningList);

				thread.run();

			}

			// while (scheduleScanningList.size() <
			// scheduleScanningCodeDDZZMMMList.length +1) {
			// System.out.println("Barridos " + scheduleScanningList.size() +
			// " de " + scheduleScanningCodeDDZZMMMList.length);
			// }

			Timestamp end = new Timestamp(System.currentTimeMillis());

			// System.out.println("=================== FIN CREACION PLANILLAS DE BARRIDO PARA "
			// + cadastralCodeDDZZ + " ====================" + start + " -->>> "
			// + end);

			// --------------------------------------------------------------

			connectionWrapper.commit(connectionWrapper);

			ScheduleScanning[] scheduleScanningListR = new ScheduleScanning[scheduleScanningList.size()];
			scheduleScanningListR = scheduleScanningList.toArray(scheduleScanningListR);

			return scheduleScanningListR;

		} catch (Exception e) {

			connectionWrapper.rollBack(connectionWrapper);

			// System.out.println(connectionWrapper.getSql()); // Con esta linea
			// podemos ver todos los sql que se ejecutaron con la misma
			// conexión.

			throw e;

		}

	}

	public ScheduleScanning update(ScheduleScanning updateDto) throws Exception {

		ConnectionWrapper connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();

		try {

			connectionWrapper.begin(connectionWrapper);

			for (ScheduleScanningItem item : updateDto.getScheduleScanningItemList()) {

				if (item.getProgressConstruction() < 0 || item.getProgressConstruction() > 100) {

					item.setProgressConstruction(null);

				}
			}

			updateDto = dao1.update(connectionWrapper, updateDto);

			if (updateDto != null && updateDto.getScheduleScanningItemList() != null) {
				for (ScheduleScanningItem item : updateDto.getScheduleScanningItemList()) {

					if (updateDto.getRecorded() != null) {

						if (item.getScheduleScanningResult() != null) {

							item.getCadastre().setDateScanning2(item.getCadastre().getDateScanning1());
							item.getCadastre().setDateScanning1(item.getCadastre().getDateScanning0());
							item.getCadastre().setDateScanning0(updateDto.getScanning());

							item.getCadastre().setScheduleScanningResult2(item.getCadastre().getScheduleScanningResult1());
							item.getCadastre().setScheduleScanningResult1(item.getCadastre().getScheduleScanningResult0());
							item.getCadastre().setScheduleScanningResult0(item.getScheduleScanningResult());
						}

					}

					item = dao2.update(connectionWrapper, item);
				}
			}

			connectionWrapper.commit(connectionWrapper);

			// System.out.println(connectionWrapper.getSql()); // Con esta linea
			// podemos ver todos los sql que se ejecutaron con la misma
			// conexión.

			return updateDto;

		} catch (Exception e) {

			connectionWrapper.rollBack(connectionWrapper);

			// System.out.println(connectionWrapper.getSql()); // Con esta linea
			// podemos ver todos los sql que se ejecutaron con la misma
			// conexión.

			throw e;

		}

	}

	public ScheduleScanning updateItem(ScheduleScanningItem item) throws Exception {

		ConnectionWrapper connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();

		try {

			connectionWrapper.begin(connectionWrapper);

			

				if (item.getProgressConstruction() < 0 || item.getProgressConstruction() > 100) {

					item.setProgressConstruction(null);

				}
			

			item = dao2.update(connectionWrapper, item);

			if (item != null) {
				

					

					if (item.getScheduleScanningResult() != null) {

						item.getCadastre().setDateScanning2(item.getCadastre().getDateScanning1());
						item.getCadastre().setDateScanning1(item.getCadastre().getDateScanning0());
						item.getCadastre().setDateScanning0(item.getScheduleScanning().getScanning());

						item.getCadastre().setScheduleScanningResult2(item.getCadastre().getScheduleScanningResult1());
						item.getCadastre().setScheduleScanningResult1(item.getCadastre().getScheduleScanningResult0());
						item.getCadastre().setScheduleScanningResult0(item.getScheduleScanningResult());
					}

					

					item = dao2.update(connectionWrapper, item);
				
			}

			connectionWrapper.commit(connectionWrapper);

			// System.out.println(connectionWrapper.getSql()); // Con esta linea
			// podemos ver todos los sql que se ejecutaron con la misma
			// conexión.

			return item.getScheduleScanning();

		} catch (Exception e) {

			connectionWrapper.rollBack(connectionWrapper);

			// System.out.println(connectionWrapper.getSql()); // Con esta linea
			// podemos ver todos los sql que se ejecutaron con la misma
			// conexión.

			throw e;

		}

	}
	public void buildScheduleScanningJasper(ScheduleScanning[] scheduleScanningList) throws Exception {

		ConnectionWrapper connectionWrapper = null;

		try {

			connectionWrapper = dataSourceWrapper.getConnectionWrapper();

			Timestamp start = new Timestamp(System.currentTimeMillis());

			// System.out.println("=================== INICIO CREACION PLANILLAS DE BARRIDO  ===================="
			// + start);

			List<String> scheduleScanningPathList = new ArrayList<String>();

			if (scheduleScanningList != null) {

				for (int i = 0; i < scheduleScanningList.length; i++) {

					boolean b = false;

					while (b == false) {
						try {
							connectionWrapper = dataSourceWrapper.getConnectionWrapper();
							b = true;
						} catch (Exception e) {
							connectionWrapper = null;
							b = false;
						}
					}

					// buildScheduleScanningJasper(scheduleScanningList[i],
					// connectionWrapper.getConnection());

					BuildScheduleScanningJasperThread thread = new BuildScheduleScanningJasperThread(connectionWrapper, linoProperties, scheduleScanningList[i], scheduleScanningPathList);
					// thread.start();
					thread.run();

				}

				// while (scheduleScanningPathList.size() <
				// scheduleScanningList.length ) {
				// System.out.println("Barridos " +
				// scheduleScanningPathList.size() + " de " +
				// scheduleScanningList.length);
				// }

			}

			Timestamp end = new Timestamp(System.currentTimeMillis());

			// System.out.println("=================== FIN CREACION PLANILLAS DE BARRIDO  ===================="
			// + start + " -->>> " + end);

		} catch (Exception e) {

			throw e;
		} catch (Throwable e) {

			throw new Exception(e);
		} finally {
			connectionWrapper.close();
		}

	}

	public void buildScheduleScanningJasper(ScheduleScanning scheduleScaninng) throws Exception {

		ConnectionWrapper connectionWrapper = null;

		try {

			connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();

			buildScheduleScanningJasper(scheduleScaninng, connectionWrapper.getConnection());

			connectionWrapper.close(connectionWrapper);

		} catch (Exception e) {

			throw e;
		} catch (Throwable e) {

			throw new Exception(e);
		} finally {
			connectionWrapper.close(connectionWrapper);
		}
	}

	private void buildScheduleScanningJasper(ScheduleScanning scheduleScaninng, Connection connection) throws Exception {

		Map<String, Object> hm = new HashMap<String, Object>();
		hm.put("pId", scheduleScaninng.getId());

		String path = linoProperties.getUrlFiles() + File.separatorChar + "jasper";

		String filename = path + File.separatorChar + "rptPlanillaBarrido" + ".jasper";

		JasperPrint jasperPrint = JasperFillManager.fillReport(filename, hm, connection);

		path = linoProperties.getUrlFiles() + File.separatorChar + "barridos";

		JasperExportManager.exportReportToPdfFile(jasperPrint, path + File.separatorChar + UtilCadastre.formatCadastralCodeB(scheduleScaninng.getCadastralCode()) + "_"
				+ scheduleScaninng.getSchedule().getYear() + "_" + scheduleScaninng.getNumberScanning() + ".pdf");
	}

}
