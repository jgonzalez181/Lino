package com.cclip.dao.schedule.scanning;

import com.cclip.model.schedule.Schedule;
import com.cclip.model.person.CensusTaker;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.utiljdbc.ConnectionWrapper;
import org.utiljdbc.MapperQueryArg;
import org.utiljdbc.MapperQueryOrderArg;
import org.utiljdbc.MapperResult;
import org.utiljdbc.ResultList;
import org.utiljdbc.UtilJdbcOld;

import com.cclip.model.schedule.scanning.ScheduleScanning;

/** DAO Barrido de Manzana Catastral */
public class ScheduleScanningDao {

	protected UtilJdbcOld utilJdbc;

	public void setUtilJdbc(UtilJdbcOld utilJdbc) {
		this.utilJdbc = utilJdbc;
	}

	public ScheduleScanning findById(ConnectionWrapper connectionWrapper, String id) {

		ScheduleScanning r = (ScheduleScanning) utilJdbc.findById(connectionWrapper, "cclip.schedule_scanning", id, new MapperResultFind());

		//System.out.println(connectionWrapper.getSql()); // Con esta linea podemos ver todos los sql que se ejecutaron con la misma conexión.

		return r;

	}

	public ScheduleScanning[] find(ConnectionWrapper connectionWrapper) {

		return find(connectionWrapper, new MapperQueryOrderArg[0]);

	}

	public ScheduleScanning[] find(ConnectionWrapper connectionWrapper, MapperQueryOrderArg[] orderList) {

		Object[] r = utilJdbc.find(connectionWrapper, "cclip.schedule_scanning", new MapperResultFind(), orderList);

		ScheduleScanning[] resultList = new ScheduleScanning[r.length];

		for (int i = 0; i < r.length; i++) {

			resultList[i] = (ScheduleScanning) r[i];

		}

		return resultList;

	}

	public ScheduleScanning[] find(ConnectionWrapper connectionWrapper, MapperQueryArg[] argList, MapperQueryOrderArg[] orderList) {

		Object[] r = utilJdbc.findByExample(connectionWrapper, "cclip.schedule_scanning", argList, new MapperResultFind(), orderList);

		ScheduleScanning[] resultList = new ScheduleScanning[r.length];

		for (int i = 0; i < r.length; i++) {

			resultList[i] = (ScheduleScanning) r[i];

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

		ResultList r = utilJdbc.findByExamplePageable(connectionWrapper, "cclip.schedule_scanning", argList, new MapperResultFind(), offSet, limit, orderList);

		return r;

	}

	public class MapperResultFind implements MapperResult {

		public Object mapRow(ResultSet rs) throws SQLException {

			ScheduleScanning dto = new ScheduleScanning();

			/* id */
			dto.setId(rs.getString("id"));

			/* erased */
			dto.setErased(rs.getBoolean("erased"));

			/* Código Catastral de Manzana */
			dto.setCadastralCode(rs.getString("cadastral_code"));

			/* Número de Barrido Anual */
			dto.setNumberScanning(rs.getInt("number_scanning"));

			/* Comentario */
			dto.setComment(rs.getString("comment"));

			/* Fecha Planificada (Resuelto por Censista) */
			dto.setPlannedDelivered(rs.getDate("planned_delivered"));

			/* Barrido Planificado */
			dto.setCreateDate(rs.getDate("create_date"));

			/* Barrido Ejecutado por Censista */
			dto.setScanning(rs.getDate("scanning"));

			/* Barrido Registrado */
			dto.setRecorded(rs.getDate("recorded"));

			if(rs.getString("schedule_id") != null) { 

				/* Cronograma de Facturación y Medición */

				Schedule dtoFk = new Schedule();

				dtoFk.setId(rs.getString("schedule_id"));
				dto.setSchedule(dtoFk);

			}

			if(rs.getString("census_taker_id") != null) { 

				/* Censista */

				CensusTaker dtoFk = new CensusTaker();

				dtoFk.setId(rs.getString("census_taker_id"));
				dto.setCensusTaker(dtoFk);

			}

			return dto;
		}
	}
}
