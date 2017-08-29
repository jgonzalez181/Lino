package com.cclip.bo.schedule.scanning;

import org.utiljdbc.ConnectionWrapper;
import org.utiljdbc.DataSourceWrapper;
import org.utiljdbc.MapperQueryArg;
import org.utiljdbc.MapperQueryOrderArg;
import org.utiljdbc.ResultList;

import com.cclip.dao.schedule.scanning.ScheduleScanningItemDao;
import com.cclip.model.schedule.scanning.ScheduleScanningItem;

/** BO Item de Barrido de Manzana Catastral */
public class ScheduleScanningItemBo {

	protected ScheduleScanningItemDao dao;
	protected DataSourceWrapper dataSourceWrapper;

	public void setDao(ScheduleScanningItemDao dao) {
		this.dao = dao;
	}

	public void setDataSourceWrapper(DataSourceWrapper dataSourceWrapper) {
		this.dataSourceWrapper = dataSourceWrapper;
	}

	public ScheduleScanningItem findById(String id) throws Exception {
		ConnectionWrapper connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();
		ScheduleScanningItem r =  dao.findById(connectionWrapper, id);
		connectionWrapper.close(connectionWrapper);
		//System.out.println(connectionWrapper.getSql()); // Con esta linea podemos ver todos los sql que se ejecutaron con la misma conexión.
		return r;

	}

	public ScheduleScanningItem[] find() throws Exception {
		ConnectionWrapper connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();
		ScheduleScanningItem[] r =  dao.find(connectionWrapper);
		connectionWrapper.close(connectionWrapper);
		//System.out.println(connectionWrapper.getSql()); // Con esta linea podemos ver todos los sql que se ejecutaron con la misma conexión.
		return r;

	}

	public ScheduleScanningItem[] find(MapperQueryOrderArg[] orderList) throws Exception {
		ConnectionWrapper connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();
		ScheduleScanningItem[] r =  dao.find(connectionWrapper, orderList);
		connectionWrapper.close(connectionWrapper);
		//System.out.println(connectionWrapper.getSql()); // Con esta linea podemos ver todos los sql que se ejecutaron con la misma conexión.
		return r;

	}

	public ScheduleScanningItem[] find(MapperQueryArg[] argList, MapperQueryOrderArg[] orderList) throws Exception {
		ConnectionWrapper connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();
		ScheduleScanningItem[] r =  dao.find(connectionWrapper, argList, orderList);
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
