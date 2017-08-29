package com.cclip.bo.schedule.scanning;

import java.util.ArrayList;
import java.util.List;

import org.utiljdbc.ConnectionWrapper;
import org.utiljdbc.DataSourceWrapper;
import org.utiljdbc.MapperQueryArg;
import org.utiljdbc.MapperQueryOrderArg;

import com.cclip.dao.schedule.scanning.ScheduleScanningResultTotalDao;
import com.cclip.model.schedule.scanning.ScheduleScanningResultTotal;

/** BO Resultado de Barrido */
public class ScheduleScanningResultTotalBo {

	protected ScheduleScanningResultTotalDao dao;
	protected DataSourceWrapper dataSourceWrapper;

	public void setDao(ScheduleScanningResultTotalDao dao) {
		this.dao = dao;
	}

	public void setDataSourceWrapper(DataSourceWrapper dataSourceWrapper) {
		this.dataSourceWrapper = dataSourceWrapper;
	}

	public ScheduleScanningResultTotal[] find(String scheduleScanningId)
			throws Exception {

		MapperQueryOrderArg[] orderList = { new MapperQueryOrderArg("code",
				false) };

		MapperQueryArg[] argList = new MapperQueryArg[1];

		MapperQueryArg a0 = new MapperQueryArg();
		a0.setEqualsTo("schedule_scanning_id", scheduleScanningId);
		argList[0] = a0;

		ConnectionWrapper connectionWrapper = this.dataSourceWrapper
				.getConnectionWrapper();

		ScheduleScanningResultTotal[] r = dao.find(connectionWrapper, argList,
				orderList);

		connectionWrapper.close(connectionWrapper);

		// System.out.println(connectionWrapper.getSql()); // Con esta linea
		// podemos ver todos los
		// sql que se ejecutaron
		// con la misma
		// conexión.
		return r;

	}

}
