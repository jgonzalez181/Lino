package com.cclip.dao.schedule.scanning;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.utiljdbc.ConnectionWrapper;
import org.utiljdbc.MapperQueryArg;
import org.utiljdbc.MapperQueryOrderArg;
import org.utiljdbc.MapperResult;
import org.utiljdbc.MapperUpdateArg;
import org.utiljdbc.ResultList;

import com.cclip.dao.schedule.census.CustomScheduleCensusDao;
import com.cclip.model.geo.CityArea;
import com.cclip.model.geo.cadastre.Cadastre;
import com.cclip.model.geo.cadastre.CadastreType;
import com.cclip.model.geo.cadastre.subdivision.CadastreSubDivisionType;
import com.cclip.model.person.CensusTaker;
import com.cclip.model.person.Uf;
import com.cclip.model.schedule.Schedule;
import com.cclip.model.schedule.scanning.ScheduleScanning;
import com.cclip.model.schedule.scanning.ScheduleScanningItem;
import com.cclip.model.schedule.scanning.ScheduleScanningResult;

/** DAO Item de Barrido de Manzana Catastral */
public class CustomScheduleScanningItemDao extends ScheduleScanningItemDao {
	
	protected CustomScheduleCensusDao dao1;

	public void setDao1(CustomScheduleCensusDao dao1) {
		this.dao1 = dao1;
	}

	public ScheduleScanningItem insert(ConnectionWrapper connectionWrapper, ScheduleScanningItem insertDto) {

		insertDto.setId(UUID.randomUUID().toString());

		List<MapperUpdateArg> mapperUpdateArgListTmp = new ArrayList<MapperUpdateArg>();

		if (insertDto.getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("id", insertDto.getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("id", String.class));
		}

		if (insertDto.getErased() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("erased", insertDto.getErased()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("erased", Boolean.class));
		}

		if (insertDto.getM2Construction() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_construction", insertDto.getM2Construction()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_construction", Double.class));
		}

		if (insertDto.getProgressConstruction() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("progress_construction", insertDto.getProgressConstruction()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("progress_construction", Double.class));
		}

		if (insertDto.getComment() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment", insertDto.getComment()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment", String.class));
		}

		if (insertDto.getCadastre() != null && insertDto.getCadastre().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_id", insertDto.getCadastre().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_id", String.class));
		}

		if (insertDto.getScheduleScanningResult() != null && insertDto.getScheduleScanningResult().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_scanning_result_id", insertDto.getScheduleScanningResult().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_scanning_result_id", String.class));
		}

		// if (insertDto.getScheduleScanningResult1() != null
		// && insertDto.getScheduleScanningResult1().getId() != null) {
		// mapperUpdateArgListTmp.add(new MapperUpdateArg(
		// "schedule_scanning_result1_id", insertDto
		// .getScheduleScanningResult1().getId()));
		// } else {
		// mapperUpdateArgListTmp.add(new MapperUpdateArg(
		// "schedule_scanning_result1_id", String.class));
		// }

		// if (insertDto.getScheduleScanningResult2() != null
		// && insertDto.getScheduleScanningResult2().getId() != null) {
		// mapperUpdateArgListTmp.add(new MapperUpdateArg(
		// "schedule_scanning_result2_id", insertDto
		// .getScheduleScanningResult2().getId()));
		// } else {
		// mapperUpdateArgListTmp.add(new MapperUpdateArg(
		// "schedule_scanning_result2_id", String.class));
		// }

		if (insertDto.getScheduleScanning() != null && insertDto.getScheduleScanning().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_scanning_id", insertDto.getScheduleScanning().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_scanning_id", String.class));
		}

		MapperUpdateArg[] mapperUpdateArgList = new MapperUpdateArg[mapperUpdateArgListTmp.size()];
		mapperUpdateArgList = mapperUpdateArgListTmp.toArray(mapperUpdateArgList);

		int rowsInsert = utilJdbc.insert(connectionWrapper, "cclip.schedule_scanning_item", mapperUpdateArgList);

		// System.out.println(connectionWrapper.getSql()); // Con esta linea
		// podemos ver todos los sql que se ejecutaron con la misma conexión.

		return insertDto;
	}

	public ScheduleScanningItem update(ConnectionWrapper connectionWrapper, ScheduleScanningItem updateDto) {

		List<MapperUpdateArg> mapperUpdateArgListTmp = new ArrayList<MapperUpdateArg>();

		if (updateDto.getErased() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("erased", updateDto.getErased()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("erased", Boolean.class));
		}

		if (updateDto.getM2Construction() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_construction", updateDto.getM2Construction()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_construction", Double.class));
		}

		if (updateDto.getProgressConstruction() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("progress_construction", updateDto.getProgressConstruction()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("progress_construction", Double.class));
		}

		if (updateDto.getComment() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment", updateDto.getComment()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment", String.class));
		}

		if (updateDto.getCadastre() != null && updateDto.getCadastre().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_id", updateDto.getCadastre().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_id", String.class));
		}

		if (updateDto.getScheduleScanningResult() != null && updateDto.getScheduleScanningResult().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_scanning_result_id", updateDto.getScheduleScanningResult().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_scanning_result_id", String.class));
		}

		// if (updateDto.getScheduleScanningResult1() != null
		// && updateDto.getScheduleScanningResult1().getId() != null) {
		// mapperUpdateArgListTmp.add(new MapperUpdateArg(
		// "schedule_scanning_result1_id", updateDto
		// .getScheduleScanningResult1().getId()));
		// } else {
		// mapperUpdateArgListTmp.add(new MapperUpdateArg(
		// "schedule_scanning_result1_id", String.class));
		// }

		// if (updateDto.getScheduleScanningResult2() != null
		// && updateDto.getScheduleScanningResult2().getId() != null) {
		// mapperUpdateArgListTmp.add(new MapperUpdateArg(
		// "schedule_scanning_result2_id", updateDto
		// .getScheduleScanningResult2().getId()));
		// } else {
		// mapperUpdateArgListTmp.add(new MapperUpdateArg(
		// "schedule_scanning_result2_id", String.class));
		// }

		if (updateDto.getScheduleScanning() != null && updateDto.getScheduleScanning().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_scanning_id", updateDto.getScheduleScanning().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_scanning_id", String.class));
		}

		MapperUpdateArg[] mapperUpdateArgList = new MapperUpdateArg[mapperUpdateArgListTmp.size()];
		mapperUpdateArgList = mapperUpdateArgListTmp.toArray(mapperUpdateArgList);

		MapperQueryArg[] mapperWhereArgList = new MapperQueryArg[1];

		MapperQueryArg mapperQueryArg = new MapperQueryArg();
		mapperQueryArg.setEqualsTo("id", updateDto.getId());
		mapperWhereArgList[0] = mapperQueryArg;

		int rowsUpdate = utilJdbc.update(connectionWrapper, "cclip.schedule_scanning_item", mapperUpdateArgList, mapperWhereArgList);

		if (updateDto.getCadastre() != null) {
			
			String al = "Barrido";

			if (updateDto.getScheduleScanning().getScanning() != null) {
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

				al += " el : " + df.format(updateDto.getScheduleScanning().getScanning());
			}

			if (updateDto.getScheduleScanning().getCensusTaker() != null) {
				String n = updateDto.getScheduleScanning().getCensusTaker().getGivenName();
				String mn = updateDto.getScheduleScanning().getCensusTaker().getMiddleName();
				String fn = updateDto.getScheduleScanning().getCensusTaker().getFamilyName();
				String d = updateDto.getScheduleScanning().getCensusTaker().getDni();
				String c = updateDto.getScheduleScanning().getCensusTaker().getCuil();

				if (fn == null) {
					fn = "";
				}

				if (n == null) {
					n = "";
				} else {
					n = " " + n;
				}

				if (mn == null) {
					mn = "";
				} else {
					mn = " " + mn;
				}

				if (d == null) {
					d = "";
				} else {
					d = " (DNI) " + d;
				}

				if (c == null) {
					c = "";
				} else {
					c = " (CUIT) " + c;
				}

				al += " por " + fn + n + mn + d + c;
			}


			Cadastre cadastre = dao1.updateForScanning(connectionWrapper, updateDto.getCadastre(), al);

			updateDto.setCadastre(cadastre);
		}

		return updateDto;
	}

	public ResultList find1(ConnectionWrapper connectionWrapper, MapperQueryArg[] argList, MapperQueryOrderArg[] orderList, Integer offSet, Integer limit) {

		ResultList r = utilJdbc.findByExamplePageable(connectionWrapper, "cclip.v_schedule_scanning_item", argList, new MapperResultFind1(), offSet, limit, orderList);

		return r;

	}

	public ScheduleScanningItem[] find1(ConnectionWrapper connectionWrapper, MapperQueryArg[] argList, MapperQueryOrderArg[] orderList) {

		Object[] r = utilJdbc.findByExample(connectionWrapper, "cclip.v_schedule_scanning_item", argList, new MapperResultFind1(), orderList);

		ScheduleScanningItem[] scheduleScanningItemList = new ScheduleScanningItem[r.length];

		for (int i = 0; i < r.length; i++) {
			scheduleScanningItemList[i] = (ScheduleScanningItem) r[i];
		}

		return scheduleScanningItemList;

	}

	public class MapperResultFind1 implements MapperResult {

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

			if (rs.getString("cadastre_id") != null) {

				/* Catastro de Parcela de Tierra */

				Cadastre cadastre = new Cadastre();

				cadastre.setId(rs.getString("cadastre_id"));
				cadastre.setCadastralCode(rs.getString("cadastre_cadastral_code"));
				cadastre.setCtaCli(rs.getString("cadastre_cta_cli"));
				cadastre.setDv(rs.getString("cadastre_dv"));
				cadastre.setCantPh(rs.getInt("cadastre_cant_ph"));
				

				if (rs.getString("cadastre_uf_id") != null) {

					Uf uf = new Uf();

					uf.setId(rs.getString("cadastre_uf_id"));

					cadastre.setUf(uf);
				}

				if (rs.getString("cadastre_city_area_id") != null) {

					CityArea cityArea = new CityArea();

					cityArea.setId(rs.getString("cadastre_city_area_id"));

					cadastre.setCityArea(cityArea);
				}

				if (rs.getString("cadastre_type_id") != null) {

					/* Tipo de Catastro */

					CadastreType cadastreType = new CadastreType();

					cadastreType.setId(rs.getString("cadastre_type_id"));
					cadastreType.setErased(rs.getBoolean("cadastre_type_erased"));
					// cadastreType.setCode(rs.getString("cadastre_type_code"));
					cadastreType.setName(rs.getString("cadastre_type_name"));
					cadastreType.setComment(rs.getString("cadastre_type_comment"));
					cadastre.setCadastreType(cadastreType);

				}
				
				if (rs.getString("cadastre_cadastre_sub_division_type_id") != null) {

					/* Tipo de Catastro */

					CadastreSubDivisionType cadastreType = new CadastreSubDivisionType();

					cadastreType.setId(rs.getString("cadastre_cadastre_sub_division_type_id"));
					
					cadastre.setCadastreSubDivisionType(cadastreType);

				}
				
				if (rs.getString("cadastre_schedule_scanning_result0_id") != null) {

					ScheduleScanningResult scheduleScanningResult = new ScheduleScanningResult();

					scheduleScanningResult.setId(rs.getString("cadastre_schedule_scanning_result0_id"));

					cadastre.setScheduleScanningResult0(scheduleScanningResult);
				}
				
				if (rs.getString("cadastre_schedule_scanning_result1_id") != null) {

					ScheduleScanningResult scheduleScanningResult = new ScheduleScanningResult();

					scheduleScanningResult.setId(rs.getString("cadastre_schedule_scanning_result1_id"));

					cadastre.setScheduleScanningResult1(scheduleScanningResult);
				}
				
				if (rs.getString("cadastre_schedule_scanning_result2_id") != null) {

					ScheduleScanningResult scheduleScanningResult = new ScheduleScanningResult();

					scheduleScanningResult.setId(rs.getString("cadastre_schedule_scanning_result2_id"));

					cadastre.setScheduleScanningResult2(scheduleScanningResult);
				}

				dto.setCadastre(cadastre);

			}

			if (rs.getString("schedule_scanning_result_id") != null) {

				/* Resultado de Barrido */

				ScheduleScanningResult dtoFk = new ScheduleScanningResult();

				dtoFk.setId(rs.getString("schedule_scanning_result_id"));
				dtoFk.setErased(rs.getBoolean("schedule_scanning_result_erased"));
				// dtoFk.setCode(rs.getString("schedule_scanning_result_code"));
				dtoFk.setName(rs.getString("schedule_scanning_result_name"));
				dtoFk.setComment(rs.getString("schedule_scanning_result_comment"));
				dto.setScheduleScanningResult(dtoFk);

			}

			// if (rs.getString("schedule_scanning_result1_id") != null) {
			//
			// /* Resultado de Barrido */
			//
			// ScheduleScanningResult dtoFk = new ScheduleScanningResult();
			//
			// dtoFk.setId(rs.getString("schedule_scanning_result1_id"));
			// dtoFk.setErased(rs
			// .getBoolean("schedule_scanning_result1_erased"));
			// dtoFk.setCode(rs.getString("schedule_scanning_result1_code"));
			// dtoFk.setName(rs.getString("schedule_scanning_result1_name"));
			// dtoFk.setComment(rs
			// .getString("schedule_scanning_result1_comment"));
			// dto.setScheduleScanningResult1(dtoFk);
			//
			// }

			// if (rs.getString("schedule_scanning_result2_id") != null) {
			//
			// /* Resultado de Barrido */
			//
			// ScheduleScanningResult dtoFk = new ScheduleScanningResult();
			//
			// dtoFk.setId(rs.getString("schedule_scanning_result2_id"));
			// dtoFk.setErased(rs
			// .getBoolean("schedule_scanning_result2_erased"));
			// dtoFk.setCode(rs.getString("schedule_scanning_result2_code"));
			// dtoFk.setName(rs.getString("schedule_scanning_result2_name"));
			// dtoFk.setComment(rs
			// .getString("schedule_scanning_result2_comment"));
			// dto.setScheduleScanningResult2(dtoFk);
			//
			// }

			if (rs.getString("schedule_scanning_id") != null) {

				/* Barrido de Manzana Catastral */

				ScheduleScanning dtoFk = new ScheduleScanning();

				dtoFk = mapRowScheduleScanning(rs);

				// dtoFk.setId(rs.getString("schedule_scanning_id"));

				dto.setScheduleScanning(dtoFk);

			}

			return dto;
		}
	}

	public ScheduleScanning mapRowScheduleScanning(ResultSet rs) throws SQLException {

		ScheduleScanning dto = new ScheduleScanning();

		/* id */
		dto.setId(rs.getString("schedule_scanning_id"));

		/* erased */
		dto.setErased(rs.getBoolean("schedule_scanning_erased"));

		/* Código Catastral de Manzana */
		dto.setCadastralCode(rs.getString("schedule_scanning_cadastral_code"));

		/* Comentario */
		dto.setComment(rs.getString("schedule_scanning_comment"));

		/* Fecha Planificada (Resuelto por Censista) */
		dto.setPlannedDelivered(rs.getDate("schedule_scanning_planned_delivered"));

		/* Barrido Planificado */
		// dto.setCreateDate(rs.getDate("schedule_scanning_create_date"));

		/* Barrido en Curso */
		// dto.setInProgress(rs.getDate("schedule_scanning_in_progress"));

		/* Barrido Ejecutado por Censista */
		dto.setScanning(rs.getDate("schedule_scanning_scanning"));

		/* Planilla de Barrido Entregada por Censista */
		// dto.setDelivered(rs.getDate("schedule_scanning_delivered"));

		/* Barrido Registrado */
		dto.setRecorded(rs.getDate("schedule_scanning_recorded"));

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

		// if (rs.getString("schedule_batch_id") != null) {
		//
		// /* Lote de Entrega */
		//
		// ScheduleBatch dtoFk = new ScheduleBatch();
		//
		// dtoFk.setId(rs.getString("schedule_batch_id"));
		// dtoFk.setErased(rs.getBoolean("schedule_batch_erased"));
		// dtoFk.setCode(rs.getString("schedule_batch_code"));
		// dtoFk.setDelivered(rs.getDate("schedule_batch_planned_delivered"));
		// dtoFk.setCreateDate(rs.getDate("schedule_batch_create_date"));
		// dtoFk.setClose(rs.getDate("schedule_batch_close"));
		// dtoFk.setDelivered(rs.getDate("schedule_batch_delivered"));
		// dtoFk.setComment(rs.getString("schedule_batch_comment"));
		// dto.setScheduleBatch(dtoFk);
		//
		// }

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
