package com.cclip.bo.schedule.scanning;

import org.utiljdbc.ConnectionWrapper;
import org.utiljdbc.DataSourceWrapper;
import org.utiljdbc.MapperQueryArg;
import org.utiljdbc.MapperQueryOrderArg;
import org.utiljdbc.ResultList;

import com.cclip.dao.schedule.scanning.ScheduleScanningResultDao;
import com.cclip.model.schedule.scanning.ScheduleScanningResult;

/** BO Resultado de Barrido */
public class ScheduleScanningResultBo {

	protected ScheduleScanningResultDao dao;
	protected DataSourceWrapper dataSourceWrapper;

	public void setDao(ScheduleScanningResultDao dao) {
		this.dao = dao;
	}

	public void setDataSourceWrapper(DataSourceWrapper dataSourceWrapper) {
		this.dataSourceWrapper = dataSourceWrapper;
	}

	public ScheduleScanningResult findById(String id) throws Exception {
		ConnectionWrapper connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();
		ScheduleScanningResult r =  dao.findById(connectionWrapper, id);
		connectionWrapper.close(connectionWrapper);
		//System.out.println(connectionWrapper.getSql()); // Con esta linea podemos ver todos los sql que se ejecutaron con la misma conexión.
		return r;

	}

	public ScheduleScanningResult[] find() throws Exception {
		ConnectionWrapper connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();
		ScheduleScanningResult[] r =  dao.find(connectionWrapper);
		connectionWrapper.close(connectionWrapper);
		//System.out.println(connectionWrapper.getSql()); // Con esta linea podemos ver todos los sql que se ejecutaron con la misma conexión.
		return r;

	}

	public ScheduleScanningResult[] find(MapperQueryOrderArg[] orderList) throws Exception {
		ConnectionWrapper connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();
		ScheduleScanningResult[] r =  dao.find(connectionWrapper, orderList);
		connectionWrapper.close(connectionWrapper);
		//System.out.println(connectionWrapper.getSql()); // Con esta linea podemos ver todos los sql que se ejecutaron con la misma conexión.
		return r;

	}

	public ScheduleScanningResult[] find(MapperQueryArg[] argList, MapperQueryOrderArg[] orderList) throws Exception {
		ConnectionWrapper connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();
		ScheduleScanningResult[] r =  dao.find(connectionWrapper, argList, orderList);
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
