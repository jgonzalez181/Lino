package com.cclip.dao.schedule.census;

import com.cclip.model.schedule.Schedule;
import com.cclip.model.person.CensusTaker;
import com.cclip.model.schedule.ScheduleBatch;
import com.cclip.model.geo.cadastre.CadastreActivityType;
import com.cclip.model.schedule.census.ScheduleCensusResult;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.utiljdbc.ConnectionWrapper;
import org.utiljdbc.MapperQueryArg;
import org.utiljdbc.MapperQueryOrderArg;
import org.utiljdbc.MapperResult;
import org.utiljdbc.ResultList;
import org.utiljdbc.UtilJdbcOld;

import com.cclip.model.schedule.census.ScheduleCensus;

/** DAO Censo de Parcela */
public class ScheduleCensusDao {

	protected UtilJdbcOld utilJdbc;

	public void setUtilJdbc(UtilJdbcOld utilJdbc) {
		this.utilJdbc = utilJdbc;
	}

	public ScheduleCensus findById(ConnectionWrapper connectionWrapper, String id) {

		ScheduleCensus r = (ScheduleCensus) utilJdbc.findById(connectionWrapper, "cclip.schedule_census", id, new MapperResultFind());

		//System.out.println(connectionWrapper.getSql()); // Con esta linea podemos ver todos los sql que se ejecutaron con la misma conexión.

		return r;

	}

	public ScheduleCensus[] find(ConnectionWrapper connectionWrapper) {

		return find(connectionWrapper, new MapperQueryOrderArg[0]);

	}

	public ScheduleCensus[] find(ConnectionWrapper connectionWrapper, MapperQueryOrderArg[] orderList) {

		Object[] r = utilJdbc.find(connectionWrapper, "cclip.schedule_census", new MapperResultFind(), orderList);

		ScheduleCensus[] resultList = new ScheduleCensus[r.length];

		for (int i = 0; i < r.length; i++) {

			resultList[i] = (ScheduleCensus) r[i];

		}

		return resultList;

	}

	public ScheduleCensus[] find(ConnectionWrapper connectionWrapper, MapperQueryArg[] argList, MapperQueryOrderArg[] orderList) {

		Object[] r = utilJdbc.findByExample(connectionWrapper, "cclip.schedule_census", argList, new MapperResultFind(), orderList);

		ScheduleCensus[] resultList = new ScheduleCensus[r.length];

		for (int i = 0; i < r.length; i++) {

			resultList[i] = (ScheduleCensus) r[i];

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

		ResultList r = utilJdbc.findByExamplePageable(connectionWrapper, "cclip.schedule_census", argList, new MapperResultFind(), offSet, limit, orderList);

		return r;

	}

	public class MapperResultFind implements MapperResult {

		public Object mapRow(ResultSet rs) throws SQLException {

			ScheduleCensus dto = new ScheduleCensus();

			/* id */
			dto.setId(rs.getString("id"));

			/* erased */
			dto.setErased(rs.getBoolean("erased"));

			/* Código Catastral de Parcela */
			dto.setCadastralCode(rs.getString("cadastral_code"));

			/* Número de Censo Anual */
			dto.setNumberCensus(rs.getInt("number_census"));

			/* Fecha de Censo */
			dto.setCensused(rs.getDate("censused"));

			/* Censo Registrado */
			dto.setRecorded(rs.getDate("recorded"));

			/* Galpones en General */
			dto.setM2ShedsGeneral(rs.getDouble("m2_sheds_general"));

			/* Edificios en General */
			dto.setM2GeneralBuilding(rs.getDouble("m2_general_building"));

			/* Edificios para Espectaculos Publicos */
			dto.setM2BuildingsPublicEntertainment(rs.getDouble("m2_buildings_public_entertainment"));

			/* Progreso de Construcción */
			dto.setM2ProgressConstruction(rs.getDouble("m2_progress_construction"));

			/* DNI del Encuestado */
			dto.setDniCensused(rs.getString("dni_censused"));

			/* Nombre del Encuestado */
			dto.setNameCensused(rs.getString("name_censused"));

			/* Apellido del Encuestado */
			dto.setLastNameCensused(rs.getString("last_name_censused"));

			/* Apellido del Encuestado */
			dto.setSignatureCensused(rs.getBoolean("signature_censused"));

			/* Comentario */
			dto.setComment(rs.getString("comment"));

			/* Alta */
			dto.setInsertCadastre(rs.getBoolean("insert_cadastre"));

			/* Baja */
			dto.setDeleteCadastre(rs.getBoolean("delete_cadastre"));

			/* Modificación */
			dto.setUpdateCadastre(rs.getBoolean("update_cadastre"));

			/* Fecha de Baja */
//			dto.setDateDelete(rs.getTimestamp("date_delete"));
//
//			/* Código de Motivo de Baja */
//			dto.setCodeReasonLow(rs.getString("code_reason_low"));
//
//			/* Motivo de Baja */
//			dto.setReasonLow(rs.getString("reason_low"));

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

			if(rs.getString("schedule_batch_id") != null) { 

				/* Lote de Entrega */

				ScheduleBatch dtoFk = new ScheduleBatch();

				dtoFk.setId(rs.getString("schedule_batch_id"));
				dto.setScheduleBatch(dtoFk);

			}

			if(rs.getString("cadastre_activity_type_id") != null) { 

				/* Tipo de Actividad */

				CadastreActivityType dtoFk = new CadastreActivityType();

				dtoFk.setId(rs.getString("cadastre_activity_type_id"));
				dto.setCadastreActivityType(dtoFk);

			}

			if(rs.getString("schedule_census_result_id") != null) { 

				/* Resultado de Censo */

				ScheduleCensusResult dtoFk = new ScheduleCensusResult();

				dtoFk.setId(rs.getString("schedule_census_result_id"));
				dto.setScheduleCensusResult(dtoFk);

			}

			return dto;
		}
	}
}
