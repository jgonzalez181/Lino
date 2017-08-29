package com.cclip.bo.schedule.scanning;

import org.utiljdbc.ConnectionWrapper;
import org.utiljdbc.MapperQueryArg;
import org.utiljdbc.MapperQueryOrderArg;
import org.utiljdbc.ResultList;

import com.cclip.dao.schedule.scanning.CustomScheduleScanningItemDao;
import com.cclip.model.schedule.scanning.ScheduleScanningItem;

/** BO Item de Barrido de Manzana Catastral */
public class CustomScheduleScanningItemBo extends ScheduleScanningItemBo {

	protected CustomScheduleScanningItemDao dao1;

	public void setDao1(CustomScheduleScanningItemDao dao1) {
		this.dao1 = dao1;
	}

	public ScheduleScanningItem[] find3(String cadastreId) throws Exception {

		MapperQueryOrderArg[] orderList = { new MapperQueryOrderArg("schedule_year", false), new MapperQueryOrderArg("schedule_scanning_scanning", false),
				new MapperQueryOrderArg("cadastre_cadastral_code", true) };

		MapperQueryArg[] argList = new MapperQueryArg[0];

		if (cadastreId != null && cadastreId.trim().length() > 0) {

			argList = new MapperQueryArg[1];

			MapperQueryArg a = new MapperQueryArg();
			a.setEqualsTo("cadastre_id", cadastreId.trim());
			argList[0] = a;
		}

		return find1(argList, orderList);

	}

	public ScheduleScanningItem[] find1(String scheduleScanningId) throws Exception {

		MapperQueryOrderArg[] orderList = { new MapperQueryOrderArg("schedule_year", false), new MapperQueryOrderArg("schedule_scanning_scanning", false),
				new MapperQueryOrderArg("cadastre_cadastral_code", true), new MapperQueryOrderArg("cadastre_cta_cli", true) };

		MapperQueryArg[] argList = new MapperQueryArg[0];

		if (scheduleScanningId != null && scheduleScanningId.trim().length() > 0) {

			argList = new MapperQueryArg[1];

			MapperQueryArg a = new MapperQueryArg();
			a.setEqualsTo("schedule_scanning_id", scheduleScanningId.trim());
			argList[0] = a;
		}

		return find1(argList, orderList);

	}

	public ResultList find2(String cadastreId, Integer offSet, Integer limit) throws Exception {

		MapperQueryOrderArg[] orderList = { new MapperQueryOrderArg("schedule_year", false), new MapperQueryOrderArg("schedule_scanning_scanning", false),
				new MapperQueryOrderArg("cadastre_cadastral_code", true) };

		MapperQueryArg[] argList = new MapperQueryArg[0];

		if (cadastreId != null && cadastreId.trim().length() > 0) {

			argList = new MapperQueryArg[1];

			MapperQueryArg a = new MapperQueryArg();
			a.setEqualsTo("cadastre_id", cadastreId.trim());
			argList[0] = a;
		}

		return find1(argList, orderList, offSet, limit);

	}

	private ScheduleScanningItem[] find1(MapperQueryArg[] argList, MapperQueryOrderArg[] orderList) throws Exception {

		ConnectionWrapper connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();

		ScheduleScanningItem[] r = dao1.find1(connectionWrapper, argList, orderList);

		connectionWrapper.close(connectionWrapper);
		// System.out.println(connectionWrapper.getSql()); // Con esta linea
		// // podemos ver todos los
		// // sql que se ejecutaron
		// // con la misma
		// // conexión.
		return r;

	}

	private ResultList find1(MapperQueryArg[] argList, MapperQueryOrderArg[] orderList, Integer offSet, Integer limit) throws Exception {

		ConnectionWrapper connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();
		ResultList r = dao1.find1(connectionWrapper, argList, orderList, offSet, limit);
		connectionWrapper.close(connectionWrapper);
		// System.out.println(connectionWrapper.getSql()); // Con esta linea
		// // podemos ver todos los
		// // sql que se ejecutaron
		// // con la misma
		// // conexión.
		return r;

	}
}
