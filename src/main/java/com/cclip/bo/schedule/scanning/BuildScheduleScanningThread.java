package com.cclip.bo.schedule.scanning;

import java.sql.Date;
import java.util.List;

import org.utiljdbc.ConnectionWrapper;

import com.cclip.dao.geo.cadastre.CustomCadastreDao;
import com.cclip.dao.schedule.scanning.CustomScheduleScanningDao;
import com.cclip.dao.schedule.scanning.CustomScheduleScanningItemDao;
import com.cclip.model.geo.cadastre.Cadastre;
import com.cclip.model.person.CensusTaker;
import com.cclip.model.schedule.Schedule;
import com.cclip.model.schedule.scanning.ScheduleScanning;
import com.cclip.model.schedule.scanning.ScheduleScanningItem;

public class BuildScheduleScanningThread /*extends Thread implements Runnable*/ {

	private String cadastralCodeDDZZMMM;
	private Date plannedDelivered;
	private CensusTaker censusTaker;
	private Schedule schedule;

	private CustomScheduleScanningDao dao1;
	private CustomScheduleScanningItemDao dao2;
	private CustomCadastreDao dao3;

	private ConnectionWrapper connectionWrapper;

	private List<ScheduleScanning> scheduleScanningList;

	public BuildScheduleScanningThread(String cadastralCodeDDZZMMM, Date plannedDelivered, CensusTaker censusTaker, Schedule schedule, CustomScheduleScanningDao dao1,
			CustomScheduleScanningItemDao dao2, CustomCadastreDao dao3, ConnectionWrapper connectionWrapper, List<ScheduleScanning> scheduleScanningList) {
		super();
		this.cadastralCodeDDZZMMM = cadastralCodeDDZZMMM;
		this.plannedDelivered = plannedDelivered;
		this.censusTaker = censusTaker;
		this.schedule = schedule;
		this.dao1 = dao1;
		this.dao2 = dao2;
		this.dao3 = dao3;
		this.connectionWrapper = connectionWrapper;
		this.scheduleScanningList = scheduleScanningList;
	}

	public void run() {
		// System.out.println("\t=================== INICIO CREACION PLANILLA DE BARRIDO PARA "
		// + cadastralCodeDDZZMMM + " ====================" + new
		// Timestamp(System.currentTimeMillis()));

		ScheduleScanning scheduleScanning = new ScheduleScanning();

		scheduleScanning.setErased(false);
		scheduleScanning.setCadastralCode(cadastralCodeDDZZMMM);
		scheduleScanning.setPlannedDelivered(plannedDelivered);
		scheduleScanning.setCensusTaker(censusTaker);
		// scheduleScanning.setScheduleBatch(scheduleBatch);
		scheduleScanning.setSchedule(schedule);
		scheduleScanning.setCreateDate(new Date(System.currentTimeMillis()));

		scheduleScanning = dao1.insert(connectionWrapper, scheduleScanning);

		Cadastre[] cadastreList = dao3.find2(connectionWrapper, scheduleScanning.getCadastralCode());

		for (Cadastre cadastre : cadastreList) {

			ScheduleScanningItem scheduleScanningItem = new ScheduleScanningItem();

			scheduleScanningItem.setErased(false);
			scheduleScanningItem.setCadastre(cadastre);
			scheduleScanningItem.setScheduleScanning(scheduleScanning);

			scheduleScanning.addScheduleScanningItemList(scheduleScanningItem);

			scheduleScanningItem = dao2.insert(connectionWrapper, scheduleScanningItem);

		}

		//scheduleScanning.setNumberScanning(dao1.findById(connectionWrapper, scheduleScanning.getId()).getNumberScanning());

		scheduleScanningList.add(scheduleScanning);

		// System.out.println("\t=================== FIN CREACION PLANILLA DE BARRIDO PARA "
		// + cadastralCodeDDZZMMM + " ====================" + new
		// Timestamp(System.currentTimeMillis()));
	}

}
