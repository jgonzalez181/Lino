package com.cclip.dao.schedule.scanning;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.utiljdbc.ConnectionWrapper;
import org.utiljdbc.MapperQueryArg;
import org.utiljdbc.MapperQueryOrderArg;
import org.utiljdbc.MapperResult;
import org.utiljdbc.MapperUpdateArg;
import org.utiljdbc.ResultList;

import com.cclip.model.person.CensusTaker;
import com.cclip.model.schedule.Schedule;
import com.cclip.model.schedule.ScheduleBatch;
import com.cclip.model.schedule.scanning.ScheduleScanning;

/** DAO Barrido de Manzana Catastral */
public class CustomScheduleScanningDao extends ScheduleScanningDao {

	public ScheduleScanning insert(ConnectionWrapper connectionWrapper,
			ScheduleScanning insertDto) {

		insertDto.setId(UUID.randomUUID().toString());

		List<MapperUpdateArg> mapperUpdateArgListTmp = new ArrayList<MapperUpdateArg>();
		
		mapperUpdateArgListTmp.add(new MapperUpdateArg("id", insertDto
				.getId()));

//		if (insertDto.getId() != null) {
//			mapperUpdateArgListTmp.add(new MapperUpdateArg("id", insertDto
//					.getId()));
//		} else {
//			mapperUpdateArgListTmp.add(new MapperUpdateArg("id", String.class));
//		}

		if (insertDto.getErased() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("erased", insertDto
					.getErased()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("erased",
					Boolean.class));
		}

		if (insertDto.getCadastralCode() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastral_code",
					insertDto.getCadastralCode()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastral_code",
					String.class));
		}

		if (insertDto.getComment() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment", insertDto
					.getComment()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment",
					String.class));
		}

		if (insertDto.getPlannedDelivered() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("planned_delivered",
					insertDto.getPlannedDelivered()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("planned_delivered",
					Date.class));
		}

		if (insertDto.getCreateDate() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("create_date",
					insertDto.getCreateDate()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("create_date",
					Date.class));
		}

//		if (insertDto.getInProgress() != null) {
//			mapperUpdateArgListTmp.add(new MapperUpdateArg("in_progress",
//					insertDto.getInProgress()));
//		} else {
//			mapperUpdateArgListTmp.add(new MapperUpdateArg("in_progress",
//					Date.class));
//		}

		if (insertDto.getScanning() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("scanning",
					insertDto.getScanning()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("scanning",
					Date.class));
		}

//		if (insertDto.getDelivered() != null) {
//			mapperUpdateArgListTmp.add(new MapperUpdateArg("delivered",
//					insertDto.getDelivered()));
//		} else {
//			mapperUpdateArgListTmp.add(new MapperUpdateArg("delivered",
//					Date.class));
//		}

		if (insertDto.getRecorded() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("recorded",
					insertDto.getRecorded()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("recorded",
					Date.class));
		}

		if (insertDto.getSchedule() != null
				&& insertDto.getSchedule().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_id",
					insertDto.getSchedule().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_id",
					String.class));
		}

//		if (insertDto.getScheduleBatch() != null
//				&& insertDto.getScheduleBatch().getId() != null) {
//			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_batch_id",
//					insertDto.getScheduleBatch().getId()));
//		} else {
//			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_batch_id",
//					String.class));
//		}

		if (insertDto.getCensusTaker() != null
				&& insertDto.getCensusTaker().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("census_taker_id",
					insertDto.getCensusTaker().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("census_taker_id",
					String.class));
		}

		MapperUpdateArg[] mapperUpdateArgList = new MapperUpdateArg[mapperUpdateArgListTmp
				.size()];
		mapperUpdateArgList = mapperUpdateArgListTmp
				.toArray(mapperUpdateArgList);

		int rowsInsert = utilJdbc.insert(connectionWrapper,
				"cclip.schedule_scanning", mapperUpdateArgList);

		// mapperUpdateArgList = new ArrayList<MapperUpdateArg>();
		//
		// mapperUpdateArgList.add(new MapperUpdateArg("cadastral_code",
		// insertDto
		// .getCadastralCode()));
		//
		// mapperUpdateArgList.add(new MapperUpdateArg("schedule_id",
		// insertDto.getSchedule().getId()));
		//
		// rowsInsert = utilJdbc.insert(connectionWrapper,
		// "cclip.schedule_scanning_tmp", mapperUpdateArgList);

		// System.out.println(connectionWrapper.getSql()); // Con esta linea
		// podemos ver todos los sql que se ejecutaron con la misma conexión.

		return insertDto;
	}

	public ScheduleScanning update(ConnectionWrapper connectionWrapper,
			ScheduleScanning updateDto) {

		List<MapperUpdateArg> mapperUpdateArgListTmp = new ArrayList<MapperUpdateArg>();

		if (updateDto.getErased() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("erased", updateDto
					.getErased()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("erased",
					Boolean.class));
		}

		if (updateDto.getCadastralCode() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastral_code",
					updateDto.getCadastralCode()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastral_code",
					String.class));
		}

		if (updateDto.getComment() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment", updateDto
					.getComment()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment",
					String.class));
		}

		if (updateDto.getPlannedDelivered() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("planned_delivered",
					updateDto.getPlannedDelivered()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("planned_delivered",
					Date.class));
		}

		if (updateDto.getCreateDate() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("create_date",
					updateDto.getCreateDate()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("create_date",
					Date.class));
		}

//		if (updateDto.getInProgress() != null) {
//			mapperUpdateArgListTmp.add(new MapperUpdateArg("in_progress",
//					updateDto.getInProgress()));
//		} else {
//			mapperUpdateArgListTmp.add(new MapperUpdateArg("in_progress",
//					Date.class));
//		}

		if (updateDto.getScanning() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("scanning",
					updateDto.getScanning()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("scanning",
					Date.class));
		}

//		if (updateDto.getDelivered() != null) {
//			mapperUpdateArgListTmp.add(new MapperUpdateArg("delivered",
//					updateDto.getDelivered()));
//		} else {
//			mapperUpdateArgListTmp.add(new MapperUpdateArg("delivered",
//					Date.class));
//		}

		if (updateDto.getRecorded() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("recorded",
					updateDto.getRecorded()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("recorded",
					Date.class));
		}

		if (updateDto.getSchedule() != null
				&& updateDto.getSchedule().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_id",
					updateDto.getSchedule().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_id",
					String.class));
		}

//		if (updateDto.getScheduleBatch() != null
//				&& updateDto.getScheduleBatch().getId() != null) {
//			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_batch_id",
//					updateDto.getScheduleBatch().getId()));
//		} else {
//			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_batch_id",
//					String.class));
//		}

		if (updateDto.getCensusTaker() != null
				&& updateDto.getCensusTaker().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("census_taker_id",
					updateDto.getCensusTaker().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("census_taker_id",
					String.class));
		}

		MapperUpdateArg[] mapperUpdateArgList = new MapperUpdateArg[mapperUpdateArgListTmp
				.size()];
		mapperUpdateArgList = mapperUpdateArgListTmp
				.toArray(mapperUpdateArgList);

		MapperQueryArg[] mapperWhereArgList = new MapperQueryArg[1];

		MapperQueryArg mapperQueryArg = new MapperQueryArg();
		mapperQueryArg.setEqualsTo("id", updateDto.getId());
		mapperWhereArgList[0] = mapperQueryArg;

		int rowsUpdate = utilJdbc.update(connectionWrapper,
				"cclip.schedule_scanning", mapperUpdateArgList,
				mapperWhereArgList);

		// System.out.println(connectionWrapper.getSql()); // Con esta linea
		// podemos ver todos los sql que se ejecutaron con la misma conexión.

		return updateDto;
	}	
	

	public ResultList find1(ConnectionWrapper connectionWrapper,
			MapperQueryArg[] argList, MapperQueryOrderArg[] orderList,
			Integer offSet, Integer limit) {

		ResultList r = utilJdbc.findByExamplePageable(connectionWrapper,
				"cclip.v_schedule_scanning", argList, new MapperResultFind1(),
				offSet, limit, orderList);

		return r;

	}

	public Integer[] find2(ConnectionWrapper connectionWrapper, Boolean asc) {

		MapperQueryOrderArg[] orderList = { new MapperQueryOrderArg("year", asc) };

		String sql = "cclip.v_schedule_scanning_year";

		Object[] r = utilJdbc.find(connectionWrapper, sql,
				new MapperResultFind2(), orderList);

		Integer[] resultList = new Integer[r.length];

		for (int i = 0; i < r.length; i++) {

			resultList[i] = (Integer) r[i];
		}

		return resultList;

	}

	public CensusTaker[] find3(ConnectionWrapper connectionWrapper) {

		MapperQueryArg[] argList = new MapperQueryArg[1];

		MapperQueryArg a = new MapperQueryArg();
		a.setEqualsTo("erased", false);
		argList[0] = a;

		Object[] r = utilJdbc.find(connectionWrapper,
				"cclip.v_schedule_scanning_person", new MapperResultFind3());

		CensusTaker[] resultList = new CensusTaker[r.length];

		for (int i = 0; i < r.length; i++) {

			resultList[i] = (CensusTaker) r[i];

		}

		return resultList;

	}

	public String[] find4(ConnectionWrapper connectionWrapper, String scheduleId) {

		String sql = "SELECT 	DISTINCT substring(c.cadastral_code::varchar FROM 0 FOR 3) AS dd "
				+ "FROM 	cclip.cadastre c "
				+ "WHERE "
				+ "	c.id NOT IN ( "
				+ "				SELECT 	cadastre_id "
				+ "				FROM 	cclip.schedule_scanning_item ssi "
				+ "				JOIN	cclip.schedule_scanning ss "
				+ "					ON ss.id = ssi.schedule_scanning_id "
				+ "				WHERE 	ss.schedule_id = '"
				+ scheduleId
				+ "' "
				+ "			) "
				+ "ORDER BY substring(c.cadastral_code::varchar FROM 0 FOR 3); ";

		Object[] r = utilJdbc.find(connectionWrapper, sql,
				new MapperResultFind4());

		String[] resultList = new String[r.length];

		for (int i = 0; i < r.length; i++) {

			resultList[i] = (String) r[i];

		}

		return resultList;

	}

	public String[] find5(ConnectionWrapper connectionWrapper,
			String scheduleId, String zz) {

		String sql = "SELECT 	DISTINCT substring(c.cadastral_code::varchar FROM 3 FOR 2) AS zz "
				+ "FROM 	cclip.cadastre c "
				+ "WHERE 	c.cadastral_code like '"
				+ zz
				+ "%' "
				+ "	AND c.id NOT IN ( "
				+ "				SELECT 	cadastre_id "
				+ "				FROM 	cclip.schedule_scanning_item ssi "
				+ "				JOIN	cclip.schedule_scanning ss "
				+ "					ON ss.id = ssi.schedule_scanning_id "
				+ "				WHERE 	ss.schedule_id = '"
				+ scheduleId
				+ "' "
				+ "			) "
				+ "ORDER BY substring(c.cadastral_code::varchar FROM 3 FOR 2); ";

		Object[] r = utilJdbc.find(connectionWrapper, sql,
				new MapperResultFind5());

		String[] resultList = new String[r.length];

		for (int i = 0; i < r.length; i++) {

			resultList[i] = (String) r[i];

		}

		return resultList;

	}

	public ScheduleScanning[] find6(ConnectionWrapper connectionWrapper,
			MapperQueryArg[] argList, MapperQueryOrderArg[] orderList) {

		Object[] r = utilJdbc.findByExample(connectionWrapper,
				"cclip.v_schedule_scanning", argList, new MapperResultFind1(),
				orderList);

		ScheduleScanning[] resultList = new ScheduleScanning[r.length];

		for (int i = 0; i < r.length; i++) {

			resultList[i] = (ScheduleScanning) r[i];

		}

		return resultList;

	}
	

	public class MapperResultFind5 implements MapperResult {

		public Object mapRow(ResultSet rs) throws SQLException {

			return rs.getString("zz");
		}
	}

	public class MapperResultFind4 implements MapperResult {

		public Object mapRow(ResultSet rs) throws SQLException {

			return rs.getString("dd");
		}
	}

	public class MapperResultFind3 implements MapperResult {

		public Object mapRow(ResultSet rs) throws SQLException {

			CensusTaker dto = new CensusTaker();

			/* id */
			dto.setId(rs.getString("person_id"));

			dto.setGivenName(rs.getString("person_given_name"));

			dto.setFamilyName(rs.getString("person_family_name"));

			dto.setCuil(rs.getString("person_cuil"));

			return dto;
		}
	}

	public class MapperResultFind2 implements MapperResult {

		public Object mapRow(ResultSet rs) throws SQLException {

			return rs.getInt("year");
		}
	}

	public class MapperResultFind1 implements MapperResult {

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

			/* Barrido en Curso */
//			dto.setInProgress(rs.getDate("in_progress"));

			/* Barrido Ejecutado por Censista */
			dto.setScanning(rs.getDate("scanning"));

			/* Planilla de Barrido Entregada por Censista */
//			dto.setDelivered(rs.getDate("delivered"));

			/* Barrido Registrado */
			dto.setRecorded(rs.getDate("recorded"));

			if (rs.getString("schedule_id") != null) {

				/* Cronograma de Facturación y Medición */

				Schedule dtoFk = new Schedule();

				dtoFk.setId(rs.getString("schedule_id"));
				dtoFk.setErased(rs.getBoolean("schedule_erased"));
				dtoFk.setYear(rs.getInt("schedule_year"));
				dtoFk.setDateFrom(rs.getDate("schedule_date_from"));
				dtoFk.setDateTo(rs.getDate("schedule_date_to"));
				dtoFk.setComment(rs.getString("schedule_comment"));
				dto.setSchedule(dtoFk);

			}

//			if (rs.getString("schedule_batch_id") != null) {
//
//				/* Lote de Entrega */
//
//				ScheduleBatch dtoFk = new ScheduleBatch();
//
//				dtoFk.setId(rs.getString("schedule_batch_id"));
//				dtoFk.setErased(rs.getBoolean("schedule_batch_erased"));
//				dtoFk.setCode(rs.getString("schedule_batch_code"));
//				dtoFk.setPlannedDelivered(rs
//						.getDate("schedule_batch_planned_delivered"));
//				dtoFk.setCreateDate(rs.getDate("schedule_batch_create_date"));
//				dtoFk.setClose(rs.getDate("schedule_batch_close"));
//				dtoFk.setDelivered(rs.getDate("schedule_batch_delivered"));
//				dtoFk.setComment(rs.getString("schedule_batch_comment"));
//				dto.setScheduleBatch(dtoFk);
//
//			}

			if (rs.getString("census_taker_id") != null) {

				/* Censista */

				CensusTaker dtoFk = new CensusTaker();

				dtoFk.setId(rs.getString("census_taker_id"));
				dtoFk.setErased(rs.getBoolean("census_taker_erased"));
				dtoFk.setCode(rs.getString("census_taker_code"));
				dtoFk.setComment(rs.getString("census_taker_comment"));

				// dtoFk.setErased(rs.getBoolean("person_erased"));
				dtoFk.setCuil(rs.getString("person_cuil"));
				dtoFk.setDni(rs.getString("person_dni"));
				dtoFk.setGivenName(rs.getString("person_given_name"));
				dtoFk.setMiddleName(rs.getString("person_middle_name"));
				dtoFk.setFamilyName(rs.getString("person_family_name"));
				dtoFk.setMasculine(rs.getBoolean("person_masculine"));
				// dtoFk.setComment(rs.getString("person_comment"));

				dto.setCensusTaker(dtoFk);

			}

			return dto;
		}
	}
}
