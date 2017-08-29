package com.cclip.dao.schedule.scanning;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.utiljdbc.ConnectionWrapper;
import org.utiljdbc.MapperQueryArg;
import org.utiljdbc.MapperQueryOrderArg;
import org.utiljdbc.MapperResult;
import org.utiljdbc.ResultList;
import org.utiljdbc.UtilJdbcOld;

import com.cclip.model.schedule.scanning.ScheduleScanningResult;

/** DAO Resultado de Barrido */
public class ScheduleScanningResultDao {

	protected UtilJdbcOld utilJdbc;

	public void setUtilJdbc(UtilJdbcOld utilJdbc) {
		this.utilJdbc = utilJdbc;
	}

	public ScheduleScanningResult findById(ConnectionWrapper connectionWrapper, String id) {

		ScheduleScanningResult r = (ScheduleScanningResult) utilJdbc.findById(connectionWrapper, "cclip.schedule_scanning_result", id, new MapperResultFind());

		//System.out.println(connectionWrapper.getSql()); // Con esta linea podemos ver todos los sql que se ejecutaron con la misma conexión.

		return r;

	}

	public ScheduleScanningResult[] find(ConnectionWrapper connectionWrapper) {

		return find(connectionWrapper, new MapperQueryOrderArg[0]);

	}

	public ScheduleScanningResult[] find(ConnectionWrapper connectionWrapper, MapperQueryOrderArg[] orderList) {

		Object[] r = utilJdbc.find(connectionWrapper, "cclip.schedule_scanning_result", new MapperResultFind(), orderList);

		ScheduleScanningResult[] resultList = new ScheduleScanningResult[r.length];

		for (int i = 0; i < r.length; i++) {

			resultList[i] = (ScheduleScanningResult) r[i];

		}

		return resultList;

	}

	public ScheduleScanningResult[] find(ConnectionWrapper connectionWrapper, MapperQueryArg[] argList, MapperQueryOrderArg[] orderList) {

		Object[] r = utilJdbc.findByExample(connectionWrapper, "cclip.schedule_scanning_result", argList, new MapperResultFind(), orderList);

		ScheduleScanningResult[] resultList = new ScheduleScanningResult[r.length];

		for (int i = 0; i < r.length; i++) {

			resultList[i] = (ScheduleScanningResult) r[i];

		}

		return resultList;

	}

	public ResultList find(ConnectionWrapper connectionWrapper, Integer offSet, Integer limit) {

		return find(connectionWrapper, new MapperQueryArg[0], new MapperQueryOrderArg[0], offSet, limit);

	}

	public ResultList find(ConnectionWrapper connectionWrapper, MapperQueryOrderArg[] orderList, Integer offSet, Integer limit) {

		return find(connectionWrapper, new MapperQueryArg[0], orderList, offSet, limit);

	}

	public ResultList find(ConnectionWrapper connectionWrapper, MapperQueryArg[] argList, MapperQueryOrderArg[] orderList, Integer offSet, Integer limit) {

		ResultList r = utilJdbc.findByExamplePageable(connectionWrapper, "cclip.schedule_scanning_result", argList, new MapperResultFind(), offSet, limit, orderList);

		return r;

	}

	public class MapperResultFind implements MapperResult {

		public Object mapRow(ResultSet rs) throws SQLException {

			ScheduleScanningResult dto = new ScheduleScanningResult();

			/* id */
			dto.setId(rs.getString("id"));

			/* erased */
			dto.setErased(rs.getBoolean("erased"));

			/* Nombre */
			dto.setName(rs.getString("name"));

			/* Comentario */
			dto.setComment(rs.getString("comment"));

			return dto;
		}
	}
}
