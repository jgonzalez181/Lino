package com.cclip.dao.schedule.scanning;

import com.cclip.model.geo.cadastre.Cadastre;
import com.cclip.model.schedule.scanning.ScheduleScanningResult;
import com.cclip.model.schedule.scanning.ScheduleScanning;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.utiljdbc.ConnectionWrapper;
import org.utiljdbc.MapperQueryArg;
import org.utiljdbc.MapperQueryOrderArg;
import org.utiljdbc.MapperResult;
import org.utiljdbc.ResultList;
import org.utiljdbc.UtilJdbcOld;

import com.cclip.model.schedule.scanning.ScheduleScanningItem;

/** DAO Item de Barrido de Manzana Catastral */
public class ScheduleScanningItemDao {

	protected UtilJdbcOld utilJdbc;

	public void setUtilJdbc(UtilJdbcOld utilJdbc) {
		this.utilJdbc = utilJdbc;
	}

	public ScheduleScanningItem findById(ConnectionWrapper connectionWrapper, String id) {

		ScheduleScanningItem r = (ScheduleScanningItem) utilJdbc.findById(connectionWrapper, "cclip.schedule_scanning_item", id, new MapperResultFind());

		//System.out.println(connectionWrapper.getSql()); // Con esta linea podemos ver todos los sql que se ejecutaron con la misma conexión.

		return r;

	}

	public ScheduleScanningItem[] find(ConnectionWrapper connectionWrapper) {

		return find(connectionWrapper, new MapperQueryOrderArg[0]);

	}

	public ScheduleScanningItem[] find(ConnectionWrapper connectionWrapper, MapperQueryOrderArg[] orderList) {

		Object[] r = utilJdbc.find(connectionWrapper, "cclip.schedule_scanning_item", new MapperResultFind(), orderList);

		ScheduleScanningItem[] resultList = new ScheduleScanningItem[r.length];

		for (int i = 0; i < r.length; i++) {

			resultList[i] = (ScheduleScanningItem) r[i];

		}

		return resultList;

	}

	public ScheduleScanningItem[] find(ConnectionWrapper connectionWrapper, MapperQueryArg[] argList, MapperQueryOrderArg[] orderList) {

		Object[] r = utilJdbc.findByExample(connectionWrapper, "cclip.schedule_scanning_item", argList, new MapperResultFind(), orderList);

		ScheduleScanningItem[] resultList = new ScheduleScanningItem[r.length];

		for (int i = 0; i < r.length; i++) {

			resultList[i] = (ScheduleScanningItem) r[i];

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

		ResultList r = utilJdbc.findByExamplePageable(connectionWrapper, "cclip.schedule_scanning_item", argList, new MapperResultFind(), offSet, limit, orderList);

		return r;

	}

	public class MapperResultFind implements MapperResult {

		public Object mapRow(ResultSet rs) throws SQLException {

			ScheduleScanningItem dto = new ScheduleScanningItem();

			/* id */
			dto.setId(rs.getString("id"));

			/* erased */
			dto.setErased(rs.getBoolean("erased"));

			/* Metros Cuadrados en Construcción */
			dto.setM2Construction(rs.getDouble("m2_construction"));

			/* Progreso de Construcción */
			dto.setProgressConstruction(rs.getDouble("progress_construction"));

			/* Comentario */
			dto.setComment(rs.getString("comment"));

			if(rs.getString("cadastre_id") != null) { 

				/* Catastro de Parcela de Tierra */

				Cadastre dtoFk = new Cadastre();

				dtoFk.setId(rs.getString("cadastre_id"));
				dto.setCadastre(dtoFk);

			}

			if(rs.getString("schedule_scanning_result_id") != null) { 

				/* Resultado de Barrido */

				ScheduleScanningResult dtoFk = new ScheduleScanningResult();

				dtoFk.setId(rs.getString("schedule_scanning_result_id"));
				dto.setScheduleScanningResult(dtoFk);

			}

			if(rs.getString("schedule_scanning_id") != null) { 

				/* Barrido de Manzana Catastral */

				ScheduleScanning dtoFk = new ScheduleScanning();

				dtoFk.setId(rs.getString("schedule_scanning_id"));
				dto.setScheduleScanning(dtoFk);

			}

			return dto;
		}
	}
}
