package com.cclip.bo.person;

import org.utiljdbc.ConnectionWrapper;
import org.utiljdbc.DataSourceWrapper;
import org.utiljdbc.MapperQueryArg;
import org.utiljdbc.MapperQueryOrderArg;
import org.utiljdbc.ResultList;

import com.cclip.dao.person.UfCensusDao;
import com.cclip.model.person.UfCensus;

/** BO Unidad de Facturación */
public class UfCensusBo {

	protected UfCensusDao dao;
	protected DataSourceWrapper dataSourceWrapper;

	public void setDao(UfCensusDao dao) {
		this.dao = dao;
	}

	public void setDataSourceWrapper(DataSourceWrapper dataSourceWrapper) {
		this.dataSourceWrapper = dataSourceWrapper;
	}

	public UfCensus findById(String id) throws Exception {
		ConnectionWrapper connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();
		UfCensus r =  dao.findById(connectionWrapper, id);
		connectionWrapper.close(connectionWrapper);
		//System.out.println(connectionWrapper.getSql()); // Con esta linea podemos ver todos los sql que se ejecutaron con la misma conexión.
		return r;

	}

	public UfCensus[] find() throws Exception {
		ConnectionWrapper connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();
		UfCensus[] r =  dao.find(connectionWrapper);
		connectionWrapper.close(connectionWrapper);
		//System.out.println(connectionWrapper.getSql()); // Con esta linea podemos ver todos los sql que se ejecutaron con la misma conexión.
		return r;

	}

	public UfCensus[] find(MapperQueryOrderArg[] orderList) throws Exception {
		ConnectionWrapper connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();
		UfCensus[] r =  dao.find(connectionWrapper, orderList);
		connectionWrapper.close(connectionWrapper);
		//System.out.println(connectionWrapper.getSql()); // Con esta linea podemos ver todos los sql que se ejecutaron con la misma conexión.
		return r;

	}

	public UfCensus[] find(MapperQueryArg[] argList, MapperQueryOrderArg[] orderList) throws Exception {
		ConnectionWrapper connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();
		UfCensus[] r =  dao.find(connectionWrapper, argList, orderList);
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
