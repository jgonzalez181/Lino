package com.cclip.bo.geo.cadastre.aacc;

import org.utiljdbc.ConnectionWrapper;
import org.utiljdbc.DataSourceWrapper;
import org.utiljdbc.MapperQueryArg;
import org.utiljdbc.MapperQueryOrderArg;
import org.utiljdbc.ResultList;

import com.cclip.dao.geo.cadastre.aacc.UnlDao;
import com.cclip.model.geo.cadastre.aacc.Unl;

public class UnlBo {

	protected UnlDao dao;
	protected DataSourceWrapper dataSourceWrapper;

	public void setDao(UnlDao dao) {
		this.dao = dao;
	}

	public void setDataSourceWrapper(DataSourceWrapper dataSourceWrapper) {
		this.dataSourceWrapper = dataSourceWrapper;
	}

	public ResultList find(MapperQueryArg[] argList, MapperQueryOrderArg[] orderList, Integer offSet, Integer limit, String table) throws Exception {
		ConnectionWrapper connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();
		ResultList r = dao.find(connectionWrapper, argList, orderList, offSet, limit, table);
		connectionWrapper.close(connectionWrapper);

		return r;

	}

	public Unl[] find(MapperQueryArg[] argList, MapperQueryOrderArg[] orderList, String table) throws Exception {
		ConnectionWrapper connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();
		Unl[] r = dao.find(connectionWrapper, argList, orderList, table);
		connectionWrapper.close(connectionWrapper);

		return r;

	}

}
