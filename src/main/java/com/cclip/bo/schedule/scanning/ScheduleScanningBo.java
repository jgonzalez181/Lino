package com.cclip.bo.schedule.scanning;

import org.utiljdbc.ConnectionWrapper;
import org.utiljdbc.DataSourceWrapper;
import org.utiljdbc.MapperQueryArg;
import org.utiljdbc.MapperQueryOrderArg;
import org.utiljdbc.ResultList;

import com.cclip.dao.schedule.scanning.ScheduleScanningDao;
import com.cclip.model.schedule.scanning.ScheduleScanning;

/** BO Barrido de Manzana Catastral */
public class ScheduleScanningBo {

	protected ScheduleScanningDao dao;
	protected DataSourceWrapper dataSourceWrapper;

	public void setDao(ScheduleScanningDao dao) {
		this.dao = dao;
	}

	public void setDataSourceWrapper(DataSourceWrapper dataSourceWrapper) {
		this.dataSourceWrapper = dataSourceWrapper;
	}

	public ScheduleScanning findById(String id) throws Exception {
		ConnectionWrapper connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();
		ScheduleScanning r =  dao.findById(connectionWrapper, id);
		connectionWrapper.close(connectionWrapper);
		//System.out.println(connectionWrapper.getSql()); // Con esta linea podemos ver todos los sql que se ejecutaron con la misma conexión.
		return r;

	}

	public ScheduleScanning[] find() throws Exception {
		ConnectionWrapper connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();
		ScheduleScanning[] r =  dao.find(connectionWrapper);
		connectionWrapper.close(connectionWrapper);
		//System.out.println(connectionWrapper.getSql()); // Con esta linea podemos ver todos los sql que se ejecutaron con la misma conexión.
		return r;

	}

	public ScheduleScanning[] find(MapperQueryOrderArg[] orderList) throws Exception {
		ConnectionWrapper connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();
		ScheduleScanning[] r =  dao.find(connectionWrapper, orderList);
		connectionWrapper.close(connectionWrapper);
		//System.out.println(connectionWrapper.getSql()); // Con esta linea podemos ver todos los sql que se ejecutaron con la misma conexión.
		return r;

	}

	public ScheduleScanning[] find(MapperQueryArg[] argList, MapperQueryOrderArg[] orderList) throws Exception {
		ConnectionWrapper connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();
		ScheduleScanning[] r =  dao.find(connectionWrapper, argList, orderList);
		connectionWrapper.close(connectionWrapper);
		//System.out.println(connectionWrapper.getSql()); // Con esta linea podemos ver todos los sql que se ejecutaron con la misma conexión.
		return r;

	}

	public ResultList find(Integer offSet, Integer limit) throws Exception {
		ConnectionWrapper connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();
		ResultList r =  dao.find(connectionWrapper, offSet, limit);
		connectionWrapper.close(connectionWrapper);
		//System.out.println(connectionWrapper.getSql()); // Con esta linea podemos ver todos los sql que se ejecutaron con la misma conexión.
		return r;

	}

	public ResultList find(MapperQueryOrderArg[] orderList, Integer offSet, Integer limit) throws Exception {
		ConnectionWrapper connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();
		ResultList r =  dao.find(connectionWrapper, orderList, offSet, limit);
		connectionWrapper.close(connectionWrapper);
		//System.out.println(connectionWrapper.getSql()); // Con esta linea podemos ver todos los sql que se ejecutaron con la misma conexión.
		return r;

	}

	public ResultList find(MapperQueryArg[] argList, MapperQueryOrderArg[] orderList, Integer offSet, Integer limit) throws Exception {
		ConnectionWrapper connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();
		ResultList r =  dao.find(connectionWrapper, argList, orderList, offSet, limit);
		connectionWrapper.close(connectionWrapper);
		//System.out.println(connectionWrapper.getSql()); // Con esta linea podemos ver todos los sql que se ejecutaron con la misma conexión.
		return r;

	}
}
