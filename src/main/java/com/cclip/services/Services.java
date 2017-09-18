package com.cclip.services;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.utiljdbc.ConnectionWrapper;
import org.utiljdbc.DataSourceWrapper;
import org.utiljdbc.ResultList;

import com.cclip.Context;
import com.cclip.model.geo.cadastre.Cadastre;
import com.cclip.model.geo.cadastre.CadastreCensus;
import com.cclip.model.geo.cadastre.block.CadastreBlock;
import com.cclip.model.person.Uf;
import com.cclip.model.person.UserSystem;
import com.cclip.model.schedule.Schedule;
import com.cclip.model.schedule.ScheduleBatch;
import com.cclip.model.schedule.census.ScheduleCensus;
import com.cclip.util.LinoProperties;
import com.cclip.util.UtilComponent;

public class Services {

	public ImportUnl importUnl;
	private LinoProperties linoProperties;

	protected DataSourceWrapper dataSourceWrapper;

	private static Services services;

	private Services() {

	}

	public static Services getInstance() {
		if (services == null) {
			services = new Services();
			services.setDataSourceWrapper(Context.getBean("dataSourceWrapper",
					DataSourceWrapper.class));
		}

		return services;
	}

	public void setDataSourceWrapper(DataSourceWrapper dataSourceWrapper) {
		this.dataSourceWrapper = dataSourceWrapper;

		linoProperties = Context
				.getBean("linoProperties", LinoProperties.class);

		importUnl = new ImportUnl();
		importUnl.setLinoProperties(linoProperties);
		importUnl.setDataSourceWrapper(dataSourceWrapper);
	}

	public Cadastre findCadastreById(String id) {

		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT * FROM cclip.f_cadastre_by_id(?)";

			Cadastre cadastre = (Cadastre) connectionWrapper.findToJsonById(
					sql, id);

			connectionWrapper.close();

			return cadastre;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException("Error al consultar los datos");
		}

	}

	public Object[][] findScheduleScanningItemListByIdCadastre(String id) {

		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT schedule_scanning_item_id, scanning, schedule_scanning_result_id, m2_construction, progress_construction, person FROM cclip.v_schedule_scanning_item_list WHERE cadastre_id = ?";

			Object[][] table = connectionWrapper.findToTable(sql, id);

			connectionWrapper.close();

			return table;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException("Error al consultar los datos");
		}

	}

	public Object[][] findCadastreBlockListByIdCadastre(String id) {

		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT cadastre_block_id, year_building, month_building, cadastre_constructive_type_id, m2_covered, cadastre_destination_type_id FROM cclip.v_cadastre_block_list WHERE cadastre_id = ?";

			Object[][] table = connectionWrapper.findToTable(sql, id);

			connectionWrapper.close();

			return table;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException("Error al consultar los datos");
		}

	}

	public Object[][] findCadastrePhListByCadastralCode(String id,
			String cadastralCode) {

		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT  id, ppp, uf_id, cta_cli, inm_building_floor, inm_building_room, inm_street, inm_street_number, inm_street_number_estimated,  m2_covered, m2_percent, cadastre_sub_division_type_id, cadastre_activity_type_id FROM cclip.v_cadastre_ph_list WHERE id != ? AND cadastral_code LIKE ?";

			Object[][] table = connectionWrapper.findToTable(sql, id,
					cadastralCode + '%');

			connectionWrapper.close();

			return table;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException("Error al consultar los datos");
		}

	}

	public ResultList findCadastreListByExample(String cadastralCode,
			String uf, String ctaCli, String cityAreaId, String cadastreTypeId,
			String cadastreSituationId, String persona, Integer offSet,
			Integer limit) {

		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String where = "";

			if (cadastralCode != null && cadastralCode.trim().length() != 0) {
				cadastralCode = cadastralCode.trim();
				where += " cadastral_code LIKE ?";
				if (cadastralCode.length() > 10) {
					cadastralCode = cadastralCode.substring(0, 10);
				}

				cadastralCode += '%';
			} else {
				cadastralCode = null;
			}

			if (uf != null && uf.trim().length() != 0) {
				uf = uf.trim();
				if (where.length() > 0) {
					where += " AND ";
				}
				where += " uf_id = ?";
			} else {
				uf = null;
			}

			if (ctaCli != null && ctaCli.trim().length() != 0) {
				ctaCli = ctaCli.trim();
				if (where.length() > 0) {
					where += " AND ";
				}
				where += " cta_cli = ?";
			} else {
				ctaCli = null;
			}

			if (cityAreaId != null && cityAreaId.trim().length() != 0) {
				cityAreaId = cityAreaId.trim();
				if (where.length() > 0) {
					where += " AND ";
				}
				where += " city_area_id = ?";
			} else {
				cityAreaId = null;
			}

			if (cadastreTypeId != null && cadastreTypeId.trim().length() != 0) {
				cadastreTypeId = cadastreTypeId.trim();
				if (where.length() > 0) {
					where += " AND ";
				}
				where += " cadastre_type_id = ?";
			} else {
				cadastreTypeId = null;
			}

			if (cadastreSituationId != null
					&& cadastreSituationId.trim().length() != 0) {
				cadastreSituationId = cadastreSituationId.trim();
				if (where.length() > 0) {
					where += " AND ";
				}
				where += " cadastre_situation_id = ?";
			} else {
				cadastreSituationId = null;
			}

			if (persona != null && persona.trim().length() != 0) {
				persona = persona.trim();
				if (where.length() > 0) {
					where += " AND ";
				}
				where += " persona ILIKE ?";

				persona = '%' + persona + '%';
			} else {
				persona = null;
			}

			String sql = "SELECT id, cadastral_code, uf_id, cta_cli, cadastre_type_id, m2, m2_covered FROM cclip.v_cadastre_list";
			String sqlCount = "SELECT COUNT(*) FROM cclip.v_cadastre_list";

			if (where != null && where.trim().length() > 0) {
				sql += " WHERE " + where;
				sqlCount += " WHERE " + where;
			}

			List<String> list = new ArrayList<String>();

			if (cadastralCode != null) {
				list.add(cadastralCode);
			}

			if (uf != null) {
				list.add(uf);
			}

			if (ctaCli != null) {
				list.add(ctaCli);
			}

			if (cityAreaId != null) {
				list.add(cityAreaId);
			}

			if (cadastreTypeId != null) {
				list.add(cadastreTypeId);
			}

			if (cadastreSituationId != null) {
				list.add(cadastreSituationId);
			}

			if (persona != null) {
				list.add(persona);
			}

			Object[] args = new Object[list.size()];
			args = list.toArray(args);

			sql += " OFFSET ? LIMIT ?;";
			sqlCount += ";";

			ResultList table = connectionWrapper.findToResultList(sql,
					sqlCount, offSet, limit, args);

			connectionWrapper.close();

			return table;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException("Error al consultar las parcelas");
		}

	}
	
	public ResultList findScheduleCensusListByExample(String scheduleId, String cadastralCode,
			String censusTakerId, String scheduleCensusResultId, String scheduleBatchCode, 
			Boolean scheduleBatch, Boolean scheduleBatchClose, 
			Integer offSet,Integer limit) {

		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String where = "";
			
			

			if (scheduleId != null && scheduleId.trim().length() != 0) {
				
				
				where += " trim(to_char(year, '9999')) = ?";
					
			} else {
				scheduleId = null;
			}

			if (cadastralCode != null && cadastralCode.trim().length() != 0) {
				cadastralCode = cadastralCode.trim();
				if (where.length() > 0) {
					where += " AND ";
				}
				where += " cadastral_Code = ?";
				
				if (cadastralCode.length() > 10) {
					cadastralCode = cadastralCode.substring(0, 10);
				}

				cadastralCode += '%';
				
			} else {
				cadastralCode = null;
			}
			
			if (censusTakerId != null && censusTakerId.trim().length() != 0) {
				censusTakerId = censusTakerId.trim();
				if (where.length() > 0) {
					where += " AND ";
				}
				where += " census_Taker_Id like ?";
				
				censusTakerId = '%' + censusTakerId + '%';
				
			} else {
				censusTakerId = null;
			}

			if (scheduleCensusResultId != null && scheduleCensusResultId.trim().length() != 0) {
				scheduleCensusResultId = scheduleCensusResultId.trim();
				if (where.length() > 0) {
					where += " AND ";
				}
				where += " schedule_Census_Result_Id like ?";
				
				scheduleCensusResultId = '%' + scheduleCensusResultId + '%';
				
			} else {
				scheduleCensusResultId = null;
			}
			
			if (scheduleBatchCode != null && scheduleBatchCode.trim().length() != 0) {
				scheduleBatchCode = scheduleBatchCode.trim();
				if (where.length() > 0) {
					where += " AND ";
				}
				where += " schedule_batch like ?";
				
				scheduleBatchCode = '%' + scheduleBatchCode + '%';
				
			} else {
				scheduleBatchCode = null;
			}
			
			
			String sql = "SELECT * FROM cclip.v_schedule_census_list  ";
			String sqlCount = "SELECT COUNT(*) FROM cclip.v_schedule_census_list";
			
			
			if (where != null && where.trim().length() > 0) {
				sql += " WHERE " + where;
				sqlCount += " WHERE " + where;
			}

			List<String> list = new ArrayList<String>();

			if (scheduleId != null) {
				list.add(scheduleId);
			}
			
			if (censusTakerId != null) {
				list.add(censusTakerId);
			}
			
			if (scheduleCensusResultId != null) {
				list.add(scheduleCensusResultId);
			}
			
			if(cadastralCode !=null){
				list.add(cadastralCode);
			}
			
			if(scheduleBatchCode !=null){
				list.add(scheduleBatchCode);
			}
			
			

			
			Object[] args = new Object[list.size()];
			args = list.toArray(args);

			sql += " OFFSET ? LIMIT ?;";
			sqlCount += ";";

			ResultList table = connectionWrapper.findToResultList(sql,
					sqlCount, offSet, limit, args);

			connectionWrapper.close();

			return table;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException("Error al consultar censos");
		}

	}

	public String insertScheduleCensus(String cadastreId, String cadastralCode,
			String ufId) {

		ConnectionWrapper connectionWrapper = null;

		try {

			connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();

			connectionWrapper.begin();

			// ---------------------------------------------------------------------------

			String ufCensusId = UUID.randomUUID().toString();

			String sqlUf = "INSERT INTO cclip.uf_census ( SELECT ?, erased, name, dni, cuit, phone, comment, country_code, "
					+ "country, admin_area_level1_code, admin_area_level1, admin_area_level2, locality, street, street_number, "
					+ "building_floor, building_room, building, comment_address, lat, lng, audit_date_create, audit_user_create, "
					+ "audit_date_update, audit_user_update, audit_version, audit_version_code_label, audit_version_label, iva_id, "
					+ "neighbourhood_id, id "
					+ "FROM cclip.uf "
					+ "WHERE uf.id = ? );";

			int rows = connectionWrapper.update(sqlUf, ufCensusId, ufId);

			if (rows != 1) {

				throw new RuntimeException("Error al crear Censo");
			}

			// ---------------------------------------------------------------------------

			String cadastreCensusId = UUID.randomUUID().toString();

			String sqlCadastreCensus = "INSERT INTO cclip.cadastre_census ( SELECT ?, erased, date_create, date_delete, cadastral_code, m2, m2_covered, "
					+ "cta_cli, sub_cta_cli, dv, user_water_service, user_water_service_dni, user_water_service_cuit, water_meter, "
					+ "comment, inm_locality, inm_neighbourhood_comment, inm_street, inm_street_number, inm_street_number_estimated,"
					+ " inm_building_floor, inm_building_room, inm_building, inm_postal_code, inm_comment_address, inm_lat, inm_lng, "
					+ "user_postal_country_code, user_postal_country, user_postal_admin_area_level1_code, user_postal_admin_area_level1, "
					+ "user_postal_admin_area_level2, user_postal_locality, user_postal_neighbourhood, user_postal_street, "
					+ "user_postal_street_number, user_postal_building_floor, user_postal_building_room, user_postal_building, "
					+ "user_postal_postal_code, user_postal_comment_address, user_postal_lat, user_postal_lng, user_phone, "
					+ "code_reason_low, reason_low, cant_ph, date_scanning2, date_scanning1, date_scanning0, audit_date_create, "
					+ "audit_user_create, audit_date_update, audit_user_update, audit_version, audit_version_code_label, "
					+ "audit_version_label, fact, m2_covered_shared, m2_covered_expanded, m2_percent, year_building, "
					+ "progress_construction_prev, user_iva_id, user_water_situation_id, ?, city_area_id, cadastre_type_id, "
					+ "cadastre_situation_id, inm_neighbourhood_id, cadastre_sub_division_type_id, schedule_scanning_result2_id, "
					+ "schedule_scanning_result1_id, schedule_scanning_result0_id, cadastre_activity_type_id, water_meter_type_id, "
					+ "cadastre_constructive_type_id, id "
					+ "FROM cclip.cadastre WHERE cadastre.id = ?);";

			String sqlCadastreCensusSingle = "INSERT INTO cclip.cadastre_census(id, erased, uf_id, cadastral_code, cadastre_type_id) VALUES ( ?, false, ? , ?, ?);";

			if (cadastreId != null) {

				rows = connectionWrapper.update(sqlCadastreCensus,
						cadastreCensusId, ufCensusId, cadastreId);

				if (rows != 1) {

					throw new RuntimeException("Error al crear Censo");
				}
			} else {

				rows = connectionWrapper.update(sqlCadastreCensusSingle,
						cadastreCensusId, ufCensusId, cadastralCode + "000",
						"PV");

				if (rows != 1) {

					throw new RuntimeException("Error al crear Censo");
				}

			}

			// ---------------------------------------------------------------------------

			String sqlCadastreBlockId = "SELECT id FROM cclip.cadastre_block WHERE cadastre_id = ?";

			String sqlCadastreBlockCensus = "INSERT INTO cclip.cadastre_block_census (SELECT ?, erased, year_building, month_building, m2_covered, demolition, comment, facade, roof_structure, homes, interior_walls, ceiling, kitchen, toilets, entrance_hall, facilities, carpentry, cover_structure, ornamentation, stained_glass_lighting, buffet_dining, points, apc, rate, apc_desc, audit_date_create, audit_user_create, audit_date_update, audit_user_update, audit_version, audit_version_code_label, audit_version_label, cadastre_activity_type_id, ?, cadastre_constructive_type_id, cadastre_destination_type_id, id FROM cclip.cadastre_block WHERE cadastre_block.id = ?);";

			if (cadastreId != null) {

				Object[][] table = connectionWrapper.findToTable(
						sqlCadastreBlockId, cadastreId);

				for (int i = 0; i < table.length; i++) {

					String cadastreBlockId = table[i][0].toString();

					String cadastreBlockCensusId = UUID.randomUUID().toString();

					rows = connectionWrapper.update(sqlCadastreBlockCensus,
							cadastreBlockCensusId, cadastreCensusId,
							cadastreBlockId);

					if (rows != 1) {

						throw new RuntimeException("Error al crear Censo");
					}

				}
			}

			// ---------------------------------------------------------------------------

			String sql = "INSERT INTO cclip.schedule_census(id, erased, cadastral_code, censused, insert_cadastre, delete_cadastre, update_cadastre, "
					+ "schedule_id, census_taker_id, "
					+ "schedule_batch_id, schedule_census_result_id, cadastre_census_id) "
					+ "VALUES (?, false, ?, now(), ?, ?, ?, "
					+ "(SELECT id FROM cclip.schedule ORDER BY year DESC LIMIT 1), "
					+ "(SELECT t.id FROM cclip.census_taker t JOIN cclip.person p ON p.id = t.id ORDER BY p.family_name, p.given_name LIMIT 1), "
					+ "(SELECT b.id FROM cclip.schedule_batch b JOIN cclip.schedule s ON s.id = b.schedule_id "
					+ "WHERE close IS NULL ORDER BY s.year DESC, b.number_batch DESC LIMIT 1),"
					+ "'Pe', ?);";

			String idCensus = UUID.randomUUID().toString();

			boolean i = true;
			boolean d = false;
			boolean u = false;

			if (cadastreId != null && cadastreId.length() > 0) {

				i = false;
				d = false;
				u = true;
			}

			rows = connectionWrapper.update(sql, idCensus, cadastralCode, i, d,
					u, cadastreCensusId);

			if (rows != 1) {

				throw new RuntimeException("Error al crear Censo");

			}
			// ---------------------------------------------------------------------------

			String sqlCadastreCensusUpdate = "UPDATE cclip.cadastre_census SET schedule_census_id = ? WHERE id = ?;";

			rows = connectionWrapper.update(sqlCadastreCensusUpdate, idCensus,
					cadastreCensusId);

			if (rows != 1) {

				throw new RuntimeException("Error al crear Censo");
			}

			// ---------------------------------------------------------------------------

			if (cadastreId != null) {

				String sqlPh = "SELECT id, uf_id FROM cclip.cadastre WHERE id != ? AND cadastral_code LIKE ?";

				Object[][] table = connectionWrapper.findToTable(sqlPh,
						cadastreId, cadastralCode.substring(0, 10) + '%');

				// ---------------------------------------------------------------------------

				for (int j = 0; j < table.length; j++) {

					ufCensusId = UUID.randomUUID().toString();

					if (table[j][1] != null) {
						ufId = table[j][1].toString();
					}

					rows = connectionWrapper.update(sqlUf, ufCensusId, ufId);

					if (rows != 1) {

						throw new RuntimeException("Error al crear Censo");
					}

					// ---------------------------------------------------------------------------

					cadastreId = table[j][0].toString();

					cadastreCensusId = UUID.randomUUID().toString();

					rows = connectionWrapper.update(sqlCadastreCensus,
							cadastreCensusId, ufCensusId, cadastreId);

					if (rows != 1) {

						throw new RuntimeException("Error al crear Censo");
					}

					// ---------------------------------------------------------------------------

					Object[][] table2 = connectionWrapper.findToTable(
							sqlCadastreBlockId, cadastreId);

					for (int k = 0; k < table2.length; k++) {

						String cadastreBlockId = table2[k][0].toString();

						String cadastreBlockCensusId = UUID.randomUUID()
								.toString();

						rows = connectionWrapper.update(sqlCadastreBlockCensus,
								cadastreBlockCensusId, cadastreCensusId,
								cadastreBlockId);

						if (rows != 1) {

							throw new RuntimeException("Error al crear Censo");
						}

					}

					// ---------------------------------------------------------------------------

					rows = connectionWrapper.update(sqlCadastreCensusUpdate,
							idCensus, cadastreCensusId);

					if (rows != 1) {

						throw new RuntimeException("Error al crear Censo");
					}

				}
			}

			// ---------------------------------------------------------------------------

			connectionWrapper.commit();

			return idCensus;

		} catch (Exception e) {

			connectionWrapper.rollBack();

			UtilComponent.logger(e);

			// throw new RuntimeException("Error al crear Censo");

			return null;

		}

	}
	
	public Cadastre updateCadastreCensusGeneral(Cadastre cadastre,
			UserSystem userSystem) {

		if (cadastre == null) {
			return null;
		}

		String cadastreTypeId = null;

		if (cadastre.getCadastreType() != null) {
			cadastreTypeId = cadastre.getCadastreType().getId();
		}

		String cityAreaId = null;

		if (cadastre.getCityArea() != null) {
			cityAreaId = cadastre.getCityArea().getId();
		}

		double m2 = 0;

		if (cadastre.getM2() != null) {
			m2 = cadastre.getM2();
		}

		double m2Covered = 0;

		if (cadastre.getM2Covered() != null) {
			m2Covered = cadastre.getM2Covered();
		}

		double m2CoveredShared = 0;

		if (cadastre.getM2CoveredShared() != null) {
			m2CoveredShared = cadastre.getM2CoveredShared();
		}

		String cadastreSituationId = null;

		if (cadastre.getCadastreSituation() != null) {
			cadastreSituationId = cadastre.getCadastreSituation().getId();
		}

		boolean waterMeter = false;

		if (cadastre.getWaterMeter() != null) {
			waterMeter = cadastre.getWaterMeter();
		}

		String waterMeterTypeId = null;

		if (cadastre.getWaterMeterType() != null) {
			waterMeterTypeId = cadastre.getWaterMeterType().getId();
		}

		String userSystemId = null;

		if (userSystem != null) {
			userSystemId = userSystem.getId();
		}

		updateCadastreCensusGeneral(cadastre.getId(),
				cadastre.getCadastralCode(), cadastreTypeId,
				cadastre.getCtaCli(), cityAreaId, cadastre.getDv(), m2,
				m2Covered, m2CoveredShared, cadastreSituationId, waterMeter,
				waterMeterTypeId, cadastre.getDateCreate(),
				cadastre.getDateDelete(), cadastre.getCodeReasonLow(),
				cadastre.getReasonLow(), userSystemId);

		return cadastre;
	}

	private String updateCadastreGeneral(String idCadastre,
			String cadastralCode, String cadastreTypeId, String ctaCli,
			String cityAreaId, String dv, double m2, double m2Covered,
			double m2CoveredShared, String cadastreSituationId,
			boolean waterMeter, String waterMeterTypeId, Timestamp dateCreate,
			Timestamp dateDelete, String codeReasonLow, String reasonLow,
			String userId) {

		ConnectionWrapper connectionWrapper = null;

		try {

			connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();

			connectionWrapper.begin();

			// ---------------------------------------------------------------------------
			String sql = "UPDATE cclip.cadastre SET cadastral_code = ?, cadastre_type_id = ?, cta_cli = ?, city_area_id = ?, dv = ?, m2 = ?, m2_covered = ?, m2_covered_shared = ?, "
					+ "cadastre_situation_id = ?, water_meter = ?, water_meter_type_id = ?, date_create = ?, date_delete = ?, code_reason_low = ?, reason_low = ?, audit_date_update = now(), "
					+ "audit_user_update = ?, audit_version_code_label = ?, audit_version_label = ? WHERE id = ?";

			Object[] args = new Object[19];

			if (cadastralCode == null) {
				args[0] = String.class;
			} else {
				args[0] = cadastralCode;
			}

			if (cadastreTypeId == null) {
				args[1] = String.class;
			} else {
				args[1] = cadastreTypeId;
			}

			if (ctaCli == null) {
				args[2] = String.class;
			} else {
				args[2] = ctaCli;
			}

			if (cityAreaId == null) {
				args[3] = String.class;
			} else {
				args[3] = cityAreaId;
			}

			if (dv == null) {
				args[4] = String.class;
			} else {
				args[4] = dv;
			}

			args[5] = m2;
			args[6] = m2Covered;
			args[7] = m2CoveredShared;

			if (cadastreSituationId == null) {
				args[8] = String.class;
			} else {
				args[8] = cadastreSituationId;
			}

			args[9] = waterMeter;

			if (waterMeterTypeId == null) {
				args[10] = String.class;
			} else {
				args[10] = waterMeterTypeId;
			}

			if (dateCreate == null) {
				args[11] = Timestamp.class;
			} else {
				args[11] = dateCreate;
			}

			if (dateDelete == null) {
				args[12] = Timestamp.class;
			} else {
				args[12] = dateDelete;
			}

			if (codeReasonLow == null) {
				args[13] = String.class;
			} else {
				args[13] = codeReasonLow;
			}

			if (reasonLow == null) {
				args[14] = String.class;
			} else {
				args[14] = reasonLow;
			}

			if (userId == null) {
				args[15] = String.class;
			} else {
				args[15] = userId;
			}

			args[16] = "10";
			args[17] = "Actualización directa";

			if (idCadastre == null) {
				args[18] = String.class;
			} else {
				args[18] = idCadastre;
			}

			int rows = connectionWrapper.update(sql, args);

			if (rows != 1) {

				throw new RuntimeException("Error al actualizar la parcela "
						+ idCadastre + "\t" + cadastralCode);
			}
			// ---------------------------------------------------------------------------

			connectionWrapper.commit();

			return idCadastre;

		} catch (Exception e) {

			connectionWrapper.rollBack();

			UtilComponent.logger(e);

			throw new RuntimeException("Error al actualizar la parcela "
					+ idCadastre + "\t" + cadastralCode);

			// return null;

		}

	}

	public ScheduleCensus findScheduleCensusById(String id) {

		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT * FROM cclip.f_scheule_census_by_id(?)";

			ScheduleCensus scheduleCensus = (ScheduleCensus) connectionWrapper
					.findToJsonById(sql, id);

			connectionWrapper.close();

			return scheduleCensus;

		} catch (Exception e) {

			UtilComponent.logger(e);

			return null;
		}

	}

	public CadastreCensus findCadastreCensusById(String id) {

		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT * FROM cclip.f_cadastre_census_by_id(?)";

			CadastreCensus cadastreCensus = (CadastreCensus) connectionWrapper
					.findToJsonById(sql, id);

			connectionWrapper.close();

			return cadastreCensus;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException("Error al consultar los datos");
		}

	}

	public Object[][] findCadastreBlockCensusListByIdCadastre(String id) {

		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT cadastre_block_id, year_building, month_building, cadastre_constructive_type_id, m2_covered, cadastre_destination_type_id FROM cclip.v_cadastre_block_census_list WHERE cadastre_id = ?";

			Object[][] table = connectionWrapper.findToTable(sql, id);

			connectionWrapper.close();

			return table;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException("Error al consultar los datos");
		}

	}

	public Object[][] findCadastreCensusPhListByCadastralCode(String id,
			String scheduleCensusId) {

		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT  id, ppp, uf_id, cta_cli, inm_building_floor, inm_building_room, inm_street, inm_street_number, inm_street_number_estimated,  m2_covered, m2_percent, cadastre_sub_division_type_id, cadastre_activity_type_id FROM cclip.v_cadastre_census_ph_list WHERE id != ? AND schedule_census_id = ?";

			Object[][] table = connectionWrapper.findToTable(sql, id,
					scheduleCensusId);

			connectionWrapper.close();

			return table;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException("Error al consultar los datos");
		}

	}

	public ResultList findScheduleCensusListByExample(Integer offSet,
			Integer limit) {

		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT * FROM cclip.v_schedule_census_list OFFSET ? LIMIT ?;";
			String sqlCount = "SELECT COUNT(*) FROM cclip.v_schedule_census_list";

			ResultList table = connectionWrapper.findToResultList(sql,
					sqlCount, offSet, limit, new Object[] {});

			connectionWrapper.close();

			return table;

		} catch (Exception e) {

			UtilComponent.logger(e);

			return null;
		}

	}

	public Object[][] findCadastreType() {
		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT id, name FROM cclip.cadastre_type WHERE erased = false ORDER BY name, id ";

			Object[][] table = connectionWrapper.findToTable(sql);

			connectionWrapper.close();

			return table;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException("Error al consultar los datos");
		}
	}

	public Object[][] findCityArea() {
		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT id, name FROM cclip.city_area WHERE erased = false ORDER BY name, id ";

			Object[][] table = connectionWrapper.findToTable(sql);

			connectionWrapper.close();

			return table;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException("Error al consultar los datos");
		}
	}

	public Object[][] findCadastreSituation() {
		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT id, name FROM cclip.cadastre_situation WHERE erased = false ORDER BY name DESC, id DESC";

			Object[][] table = connectionWrapper.findToTable(sql);

			connectionWrapper.close();

			return table;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException("Error al consultar los datos");
		}
	}

	public Object[][] findWaterMeterType() {
		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT id, name FROM cclip.water_meter_type WHERE erased = false ORDER BY name DESC, id DESC";

			Object[][] table = connectionWrapper.findToTable(sql);

			connectionWrapper.close();

			return table;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException("Error al consultar los datos");
		}
	}

	public Object[][] findReasonLow() {
		try {

			Object[][] table = { { "03", "Baja por Subdivisión" },
					{ "04", "Baja por Unificación" } };

			return table;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException("Error al consultar los datos");
		}
	}

	public Object[][] findNeighbourhood() {
		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT id, name FROM cclip.neighbourhood WHERE erased = false ORDER BY name, id ";

			Object[][] table = connectionWrapper.findToTable(sql);

			connectionWrapper.close();

			return table;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException("Error al consultar los datos");
		}
	}

	public Object[][] findIva() {
		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT id, name FROM cclip.iva WHERE erased = false ORDER BY name, id ";

			Object[][] table = connectionWrapper.findToTable(sql);

			connectionWrapper.close();

			return table;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException("Error al consultar los datos");
		}
	}

	public Object[][] findUserWaterSituation() {
		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT id, name FROM cclip.user_water_situation WHERE erased = false ORDER BY name, id ";

			Object[][] table = connectionWrapper.findToTable(sql);

			connectionWrapper.close();

			return table;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException("Error al consultar los datos");
		}
	}

	public Uf findUfById(String id) {

		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT * FROM cclip.f_uf_by_id(?)";

			Uf uf = (Uf) connectionWrapper.findToJsonById(sql, id);

			connectionWrapper.close();

			return uf;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException("Error al consultar los datos");
		}

	}

	public Cadastre updateCadastreUf(Cadastre cadastre, UserSystem userSystem) {

		if (cadastre == null || cadastre.getId() == null
				|| cadastre.getId().trim().length() == 0) {
			return null;
		}

		ConnectionWrapper connectionWrapper = null;

		try {

			connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();

			connectionWrapper.begin();

			// ---------------------------------------------------------------------------

			if (cadastre.getUf() != null && cadastre.getUf().getId() != null
					&& cadastre.getUf().getId().trim().length() != 0) {

				if (connectionWrapper.isExitsById("cclip.uf", cadastre.getUf()
						.getId().trim()) == false) {
					throw new RuntimeException(
							"Error al actualizar la parcela "
									+ cadastre.getId().trim() + "\t"
									+ cadastre.getCadastralCode() + ", la UF "
									+ cadastre.getUf().getId() + " no existe.");
				}

				updateUf(cadastre.getUf(), userSystem, connectionWrapper);
			}
			// ---------------------------------------------------------------------------

			String sql = "UPDATE cclip.cadastre SET uf_id = ?, audit_date_update = now(), "
					+ "audit_user_update = ?, audit_version_code_label = ?, "
					+ "audit_version_label = ? WHERE id = ?";

			Object[] args = new Object[5];

			if (cadastre.getUf() != null && cadastre.getUf().getId() != null
					&& cadastre.getUf().getId().trim().length() > 0) {
				args[0] = cadastre.getUf().getId().trim();
			} else {
				args[0] = String.class;
			}

			if (userSystem != null && userSystem.getId() != null
					&& userSystem.getId().trim().length() > 0) {
				args[1] = userSystem.getId().trim();
			} else {
				args[1] = String.class;
			}

			args[2] = "10";
			args[3] = "Actualización directa";
			args[4] = cadastre.getId().trim();

			int rows = connectionWrapper.update(sql, args);

			if (rows != 1) {

				throw new RuntimeException("Error al actualizar la parcela "
						+ cadastre.getId().trim() + "\t"
						+ cadastre.getCadastralCode());
			}
			// ---------------------------------------------------------------------------

			connectionWrapper.commit();

			return cadastre;

		} catch (Exception e) {

			connectionWrapper.rollBack();

			UtilComponent.logger(e);

			throw new RuntimeException("Error al actualizar la parcela "
					+ cadastre.getId().trim() + "\t"
					+ cadastre.getCadastralCode());

			// return null;

		}
	}

	public Cadastre updateCadastreUfCensus(CadastreCensus cadastre,
			UserSystem userSystem) {

		if (cadastre == null || cadastre.getId() == null
				|| cadastre.getId().trim().length() == 0) {
			return null;
		}

		ConnectionWrapper connectionWrapper = null;

		try {

			connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();

			connectionWrapper.begin();

			// ---------------------------------------------------------------------------

			if (cadastre.getUf() != null && cadastre.getUf().getId() != null
					&& cadastre.getUf().getUf().getId().trim().length() != 0) {

				if (connectionWrapper.isExitsById("cclip.uf_census", cadastre
						.getUf().getId().trim()) == false) {
					throw new RuntimeException(
							"Error al actualizar la parcela "
									+ cadastre.getId().trim() + "\t"
									+ cadastre.getCadastralCode() + ", la UF "
									+ cadastre.getUf().getUf().getId()
									+ " no existe.");
				}

				updateUfCensus(cadastre.getUf(), userSystem, connectionWrapper);
			}
			// ---------------------------------------------------------------------------

			String sql = "UPDATE cclip.cadastre_census SET uf_id = ?, audit_date_update = now(), "
					+ "audit_user_update = ?, audit_version_code_label = ?, "
					+ "audit_version_label = ? WHERE id = ?";

			Object[] args = new Object[5];

			if (cadastre.getUf() != null && cadastre.getUf().getId() != null
					&& cadastre.getUf().getId().trim().length() > 0) {
				args[0] = cadastre.getUf().getId().trim();
			} else {
				args[0] = String.class;
			}

			if (userSystem != null && userSystem.getId() != null
					&& userSystem.getId().trim().length() > 0) {
				args[1] = userSystem.getId().trim();
			} else {
				args[1] = String.class;
			}

			args[2] = "10";
			args[3] = "Actualización directa";
			args[4] = cadastre.getId().trim();

			int rows = connectionWrapper.update(sql, args);

			if (rows != 1) {

				throw new RuntimeException("Error al actualizar la parcela "
						+ cadastre.getId().trim() + "\t"
						+ cadastre.getCadastralCode());
			}
			// ---------------------------------------------------------------------------

			connectionWrapper.commit();

			return cadastre;

		} catch (Exception e) {

			connectionWrapper.rollBack();

			UtilComponent.logger(e);

			throw new RuntimeException("Error al actualizar la parcela "
					+ cadastre.getId().trim() + "\t"
					+ cadastre.getCadastralCode());

			// return null;

		}
	}

	public boolean isExitsUfId(String ufId) {
		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			if (ufId == null || ufId.trim().length() == 0) {
				return false;
			}

			// ---------------------------------------------------------------------------

			boolean b = connectionWrapper.isExitsById("cclip.uf", ufId.trim());

			// ---------------------------------------------------------------------------

			connectionWrapper.close();

			return b;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException("Error al consultar los datos");
		}

	}

	public boolean isExitsCadastrealCode(String cadastralCode) {

		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT id FROM cclip.cadastre WHERE cadastral_code = ? LIMIT 1";

			Object[][] table = connectionWrapper
					.findToTable(sql, cadastralCode);

			boolean b = (table.length > 0);

			connectionWrapper.close();

			return b;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException("Error al consultar los datos");
		}

	}

	public Uf updateUf(Uf uf, UserSystem userSystem,
			ConnectionWrapper connectionWrapper) {

		if (uf == null || uf.getId() == null || uf.getId().trim().length() == 0) {
			return null;
		}

		// ---------------------------------------------------------------------------

		String sql = "UPDATE cclip.uf SET name = ?, dni = ?, cuit = ?, iva_id = ?, phone = ?, neighbourhood_id = ?, street = ?, street_number = ?, building_floor = ?, building_room = ?, "
				+ "building = ?, comment_address = ?, "
				+ "audit_date_update = now(), "
				+ "audit_user_update = ?, audit_version_code_label = ?, "
				+ "audit_version_label = ? WHERE id = ?";

		Object[] args = new Object[16];

		if (uf.getName() != null && uf.getName().trim().length() > 0) {
			args[0] = uf.getName().trim();
		} else {
			args[0] = String.class;
		}

		if (uf.getDni() != null && uf.getDni().trim().length() > 0) {
			args[1] = uf.getDni().trim();
		} else {
			args[1] = String.class;
		}

		if (uf.getCuit() != null && uf.getCuit().trim().length() > 0) {
			args[2] = uf.getCuit().trim();
		} else {
			args[2] = String.class;
		}

		if (uf.getIva() != null && uf.getIva().getId() != null
				&& uf.getIva().getId().trim().length() > 0) {
			args[3] = uf.getIva().getId().trim();
		} else {
			args[3] = String.class;
		}

		if (uf.getPhone() != null && uf.getPhone().trim().length() > 0) {
			args[4] = uf.getPhone().trim();
		} else {
			args[4] = String.class;
		}

		if (uf.getNeighbourhood() != null
				&& uf.getNeighbourhood().getId() != null
				&& uf.getNeighbourhood().getId().trim().length() > 0) {
			args[5] = uf.getNeighbourhood().getId().trim();
		} else {
			args[5] = String.class;
		}

		if (uf.getStreet() != null && uf.getStreet().trim().length() > 0) {
			args[6] = uf.getStreet().trim();
		} else {
			args[6] = String.class;
		}

		if (uf.getStreetNumber() != null
				&& uf.getStreetNumber().trim().length() > 0) {
			args[7] = uf.getStreetNumber().trim();
		} else {
			args[7] = String.class;
		}

		if (uf.getBuildingFloor() != null
				&& uf.getBuildingFloor().trim().length() > 0) {
			args[8] = uf.getBuildingFloor().trim();
		} else {
			args[8] = String.class;
		}

		if (uf.getBuildingRoom() != null
				&& uf.getBuildingRoom().trim().length() > 0) {
			args[9] = uf.getBuildingRoom().trim();
		} else {
			args[9] = String.class;
		}

		if (uf.getBuilding() != null && uf.getBuilding().trim().length() > 0) {
			args[10] = uf.getBuilding().trim();
		} else {
			args[10] = String.class;
		}

		if (uf.getCommentAddress() != null
				&& uf.getCommentAddress().trim().length() > 0) {
			args[11] = uf.getCommentAddress().trim();
		} else {
			args[11] = String.class;
		}

		if (userSystem != null && userSystem.getId() != null
				&& userSystem.getId().trim().length() > 0) {
			args[12] = userSystem.getId().trim();
		} else {
			args[12] = String.class;
		}

		args[13] = "10";
		args[14] = "Actualización directa";
		args[15] = uf.getId().trim();

		int rows = connectionWrapper.update(sql, args);

		if (rows != 1) {

			throw new RuntimeException("Error al actualizar la uf "
					+ uf.getId().trim());
		}
		// ---------------------------------------------------------------------------

		return uf;
	}

	public Uf updateUfCensus(Uf uf, UserSystem userSystem,
			ConnectionWrapper connectionWrapper) {

		if (uf == null || uf.getId() == null || uf.getId().trim().length() == 0) {
			return null;
		}

		// ---------------------------------------------------------------------------

		String sql = "UPDATE cclip.uf_census SET name = ?, dni = ?, cuit = ?, iva_id = ?, phone = ?, neighbourhood_id = ?, street = ?, street_number = ?, building_floor = ?, building_room = ?, "
				+ "building = ?, comment_address = ?, "
				+ "audit_date_update = now(), "
				+ "audit_user_update = ?, audit_version_code_label = ?, "
				+ "audit_version_label = ? WHERE id = ?";

		Object[] args = new Object[16];

		if (uf.getName() != null && uf.getName().trim().length() > 0) {
			args[0] = uf.getName().trim();
		} else {
			args[0] = String.class;
		}

		if (uf.getDni() != null && uf.getDni().trim().length() > 0) {
			args[1] = uf.getDni().trim();
		} else {
			args[1] = String.class;
		}

		if (uf.getCuit() != null && uf.getCuit().trim().length() > 0) {
			args[2] = uf.getCuit().trim();
		} else {
			args[2] = String.class;
		}

		if (uf.getIva() != null && uf.getIva().getId() != null
				&& uf.getIva().getId().trim().length() > 0) {
			args[3] = uf.getIva().getId().trim();
		} else {
			args[3] = String.class;
		}

		if (uf.getPhone() != null && uf.getPhone().trim().length() > 0) {
			args[4] = uf.getPhone().trim();
		} else {
			args[4] = String.class;
		}

		if (uf.getNeighbourhood() != null
				&& uf.getNeighbourhood().getId() != null
				&& uf.getNeighbourhood().getId().trim().length() > 0) {
			args[5] = uf.getNeighbourhood().getId().trim();
		} else {
			args[5] = String.class;
		}

		if (uf.getStreet() != null && uf.getStreet().trim().length() > 0) {
			args[6] = uf.getStreet().trim();
		} else {
			args[6] = String.class;
		}

		if (uf.getStreetNumber() != null
				&& uf.getStreetNumber().trim().length() > 0) {
			args[7] = uf.getStreetNumber().trim();
		} else {
			args[7] = String.class;
		}

		if (uf.getBuildingFloor() != null
				&& uf.getBuildingFloor().trim().length() > 0) {
			args[8] = uf.getBuildingFloor().trim();
		} else {
			args[8] = String.class;
		}

		if (uf.getBuildingRoom() != null
				&& uf.getBuildingRoom().trim().length() > 0) {
			args[9] = uf.getBuildingRoom().trim();
		} else {
			args[9] = String.class;
		}

		if (uf.getBuilding() != null && uf.getBuilding().trim().length() > 0) {
			args[10] = uf.getBuilding().trim();
		} else {
			args[10] = String.class;
		}

		if (uf.getCommentAddress() != null
				&& uf.getCommentAddress().trim().length() > 0) {
			args[11] = uf.getCommentAddress().trim();
		} else {
			args[11] = String.class;
		}

		if (userSystem != null && userSystem.getId() != null
				&& userSystem.getId().trim().length() > 0) {
			args[12] = userSystem.getId().trim();
		} else {
			args[12] = String.class;
		}

		args[13] = "10";
		args[14] = "Actualización directa";
		args[15] = uf.getId().trim();

		int rows = connectionWrapper.update(sql, args);

		if (rows != 1) {

			throw new RuntimeException("Error al actualizar la uf "
					+ uf.getId().trim());
		}
		// ---------------------------------------------------------------------------

		return uf;
	}

	public Cadastre updateCadastreGeneral(Cadastre cadastre,
			UserSystem userSystem) {

		if (cadastre == null) {
			return null;
		}

		String cadastreTypeId = null;

		if (cadastre.getCadastreType() != null) {
			cadastreTypeId = cadastre.getCadastreType().getId();
		}

		String cityAreaId = null;

		if (cadastre.getCityArea() != null) {
			cityAreaId = cadastre.getCityArea().getId();
		}

		double m2 = 0;

		if (cadastre.getM2() != null) {
			m2 = cadastre.getM2();
		}

		double m2Covered = 0;

		if (cadastre.getM2Covered() != null) {
			m2Covered = cadastre.getM2Covered();
		}

		double m2CoveredShared = 0;

		if (cadastre.getM2CoveredShared() != null) {
			m2CoveredShared = cadastre.getM2CoveredShared();
		}

		String cadastreSituationId = null;

		if (cadastre.getCadastreSituation() != null) {
			cadastreSituationId = cadastre.getCadastreSituation().getId();
		}

		boolean waterMeter = false;

		if (cadastre.getWaterMeter() != null) {
			waterMeter = cadastre.getWaterMeter();
		}

		String waterMeterTypeId = null;

		if (cadastre.getWaterMeterType() != null) {
			waterMeterTypeId = cadastre.getWaterMeterType().getId();
		}

		String userSystemId = null;

		if (userSystem != null) {
			userSystemId = userSystem.getId();
		}

		updateCadastreGeneral(cadastre.getId(), cadastre.getCadastralCode(),
				cadastreTypeId, cadastre.getCtaCli(), cityAreaId,
				cadastre.getDv(), m2, m2Covered, m2CoveredShared,
				cadastreSituationId, waterMeter, waterMeterTypeId,
				cadastre.getDateCreate(), cadastre.getDateDelete(),
				cadastre.getCodeReasonLow(), cadastre.getReasonLow(),
				userSystemId);

		return cadastre;
	}

	public ScheduleCensus updateScheduleCensus(ScheduleCensus scheduleCensus,
			UserSystem userSystem) {

		if (scheduleCensus == null) {
			return null;
		}
		
		ConnectionWrapper connectionWrapper = null;

		try {

			connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();

			connectionWrapper.begin();

			// ---------------------------------------------------------------------------
			String sql = "UPDATE cclip.schedule_census SET insert_cadastre = ?, delete_cadastre = ?, update_cadastre = ?, "
					+ " schedule_id = ?, schedule_batch_id = ?, censused = ?, schedule_census_result_id = ?, census_taker_id = ? "
					+ " WHERE id = ?";

			Object[] args = new Object[9];
			//se considera este campo en el update???
			if (scheduleCensus.getInsertCadastre() == null) {
				args[0] = Boolean.class;
			} else {
				args[0] = scheduleCensus.getInsertCadastre();
			}
			
			if (scheduleCensus.getDeleteCadastre() == null) {
				args[1] = Boolean.class;
			} else {
				args[1] = scheduleCensus.getDeleteCadastre();
			}

			if (scheduleCensus.getUpdateCadastre() == null) {
				args[2] = Boolean.class;
			} else {
				args[2] = scheduleCensus.getUpdateCadastre();
			}

			if (scheduleCensus.getSchedule() == null) {
				args[3] = String.class;
			} else {
				args[3] = scheduleCensus.getSchedule().getId();
			}

			if (scheduleCensus.getScheduleBatch() == null) {
				args[4] = String.class;
			} else {
				args[4] = scheduleCensus.getScheduleBatch().getId();
			}
			
			if (scheduleCensus.getCensused() == null) {
				args[5] = Date.class;
			} else {
				args[5] = scheduleCensus.getCensused();
			}
			
			if (scheduleCensus.getScheduleCensusResult() == null) {
				args[6] = String.class;
			} else {
				args[6] = scheduleCensus.getScheduleCensusResult().getId();
			}
			
			if (scheduleCensus.getCensusTaker() == null) {
				args[7] = String.class;
			} else {
				args[7] = scheduleCensus.getCensusTaker().getId();
			}
			
			if (scheduleCensus.getId() == null) {
				args[8] = String.class;
			} else {
				args[8] = scheduleCensus.getId();
			}

			
			int rows = connectionWrapper.update(sql, args);

			if (rows != 1) {

				throw new RuntimeException("Error al actualizar cabecera del censo "
						+ scheduleCensus.getId() + "\t" + scheduleCensus.getCadastralCode());
			}
			// ---------------------------------------------------------------------------

			connectionWrapper.commit();

			

		} catch (Exception e) {

			connectionWrapper.rollBack();

			UtilComponent.logger(e);

			throw new RuntimeException("Error al actualizar cabecera del censo "
					+ scheduleCensus.getId() + "\t" + scheduleCensus.getCadastralCode());

			// return null;

		}

		

		return scheduleCensus;
	}
	
	public ScheduleCensus updateScheduleCensusConstruccionWork(ScheduleCensus scheduleCensus,
			UserSystem userSystem) {

		if (scheduleCensus == null) {
			return null;
		}
		
		ConnectionWrapper connectionWrapper = null;

		try {

			connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();

			connectionWrapper.begin();

			// ---------------------------------------------------------------------------
			String sql = "UPDATE cclip.schedule_census SET m2_sheds_general = ?, m2_general_building = ?, m2_buildings_public_entertainment = ?, "
					+ " m2_progress_construction = ? "
					+ " WHERE id = ?";

			Object[] args = new Object[5];
			
			if (scheduleCensus.getM2ShedsGeneral() == null) {
				args[0] = Double.class;
			} else {
				args[0] = scheduleCensus.getM2ShedsGeneral();
			}
			
			if (scheduleCensus.getM2GeneralBuilding() == null) {
				args[1] = Double.class;
			} else {
				args[1] = scheduleCensus.getM2GeneralBuilding();
			}

			if (scheduleCensus.getM2BuildingsPublicEntertainment() == null) {
				args[2] = Double.class;
			} else {
				args[2] = scheduleCensus.getM2BuildingsPublicEntertainment();
			}

			if (scheduleCensus.getM2ProgressConstruction() == null) {
				args[3] = Double.class;
			} else {
				args[3] = scheduleCensus.getM2ProgressConstruction();
			}

			
			if (scheduleCensus.getId() == null) {
				args[4] = String.class;
			} else {
				args[4] = scheduleCensus.getId();
			}

			
			int rows = connectionWrapper.update(sql, args);

			if (rows != 1) {

				throw new RuntimeException("Error al actualizar obras en contrucción del censo "
						+ scheduleCensus.getId() + "\t" + scheduleCensus.getCadastralCode());
			}
			// ---------------------------------------------------------------------------

			connectionWrapper.commit();

			

		} catch (Exception e) {

			connectionWrapper.rollBack();

			UtilComponent.logger(e);

			throw new RuntimeException("Error al actualizar cabecera del censo "
					+ scheduleCensus.getId() + "\t" + scheduleCensus.getCadastralCode());

			// return null;

		}

		

		return scheduleCensus;
	}

	

	private String updateCadastreCensusGeneral(String idCadastre,
			String cadastralCode, String cadastreTypeId, String ctaCli,
			String cityAreaId, String dv, double m2, double m2Covered,
			double m2CoveredShared, String cadastreSituationId,
			boolean waterMeter, String waterMeterTypeId, Timestamp dateCreate,
			Timestamp dateDelete, String codeReasonLow, String reasonLow,
			String userId) {

		ConnectionWrapper connectionWrapper = null;

		try {

			connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();

			connectionWrapper.begin();

			// ---------------------------------------------------------------------------
			String sql = "UPDATE cclip.cadastre_census SET cadastral_code = ?, cadastre_type_id = ?, cta_cli = ?, city_area_id = ?, dv = ?, m2 = ?, m2_covered = ?, m2_covered_shared = ?, "
					+ "cadastre_situation_id = ?, water_meter = ?, water_meter_type_id = ?, date_create = ?, date_delete = ?, code_reason_low = ?, reason_low = ?, audit_date_update = now(), "
					+ "audit_user_update = ?, audit_version_code_label = ?, audit_version_label = ? WHERE id = ?";

			Object[] args = new Object[19];

			if (cadastralCode == null) {
				args[0] = String.class;
			} else {
				args[0] = cadastralCode;
			}

			if (cadastreTypeId == null) {
				args[1] = String.class;
			} else {
				args[1] = cadastreTypeId;
			}

			if (ctaCli == null) {
				args[2] = String.class;
			} else {
				args[2] = ctaCli;
			}

			if (cityAreaId == null) {
				args[3] = String.class;
			} else {
				args[3] = cityAreaId;
			}

			if (dv == null) {
				args[4] = String.class;
			} else {
				args[4] = dv;
			}

			args[5] = m2;
			args[6] = m2Covered;
			args[7] = m2CoveredShared;

			if (cadastreSituationId == null) {
				args[8] = String.class;
			} else {
				args[8] = cadastreSituationId;
			}

			args[9] = waterMeter;

			if (waterMeterTypeId == null) {
				args[10] = String.class;
			} else {
				args[10] = waterMeterTypeId;
			}

			if (dateCreate == null) {
				args[11] = Timestamp.class;
			} else {
				args[11] = dateCreate;
			}

			if (dateDelete == null) {
				args[12] = Timestamp.class;
			} else {
				args[12] = dateDelete;
			}

			if (codeReasonLow == null) {
				args[13] = String.class;
			} else {
				args[13] = codeReasonLow;
			}

			if (reasonLow == null) {
				args[14] = String.class;
			} else {
				args[14] = reasonLow;
			}

			if (userId == null) {
				args[15] = String.class;
			} else {
				args[15] = userId;
			}

			args[16] = "10";
			args[17] = "Actualización directa";

			if (idCadastre == null) {
				args[18] = String.class;
			} else {
				args[18] = idCadastre;
			}

			int rows = connectionWrapper.update(sql, args);

			if (rows != 1) {

				throw new RuntimeException("Error al actualizar la parcela "
						+ idCadastre + "\t" + cadastralCode);
			}
			// ---------------------------------------------------------------------------

			connectionWrapper.commit();

			return idCadastre;

		} catch (Exception e) {

			connectionWrapper.rollBack();

			UtilComponent.logger(e);

			throw new RuntimeException("Error al actualizar la parcela "
					+ idCadastre + "\t" + cadastralCode);

			// return null;

		}

	}

	public Cadastre updateCadastreInmAddres(Cadastre cadastre,
			UserSystem userSystem) {

		if (cadastre == null) {
			return null;
		}

		String inmNeighbourhoodId = null;

		if (cadastre.getInmNeighbourhood() != null) {
			inmNeighbourhoodId = cadastre.getInmNeighbourhood().getId();
		}

		boolean inmStreetNumberEstimated = false;

		if (cadastre.getInmStreetNumberEstimated() != null) {
			inmStreetNumberEstimated = cadastre.getInmStreetNumberEstimated();
		}

		String userSystemId = null;

		if (userSystem != null) {
			userSystemId = userSystem.getId();
		}

		updateCadastreInmAddres(cadastre.getId(), cadastre.getCadastralCode(),
				inmNeighbourhoodId, cadastre.getInmStreet(),
				cadastre.getInmStreetNumber(), inmStreetNumberEstimated,
				cadastre.getInmBuilding(), cadastre.getInmCommentAddress(),
				userSystemId);

		return cadastre;

	}

	public Cadastre updateCadastreCensusInmAddres(Cadastre cadastre,
			UserSystem userSystem) {

		if (cadastre == null) {
			return null;
		}

		String inmNeighbourhoodId = null;

		if (cadastre.getInmNeighbourhood() != null) {
			inmNeighbourhoodId = cadastre.getInmNeighbourhood().getId();
		}

		boolean inmStreetNumberEstimated = false;

		if (cadastre.getInmStreetNumberEstimated() != null) {
			inmStreetNumberEstimated = cadastre.getInmStreetNumberEstimated();
		}

		String userSystemId = null;

		if (userSystem != null) {
			userSystemId = userSystem.getId();
		}

		updateCadastreCensusInmAddres(cadastre.getId(),
				cadastre.getCadastralCode(), inmNeighbourhoodId,
				cadastre.getInmStreet(), cadastre.getInmStreetNumber(),
				inmStreetNumberEstimated, cadastre.getInmBuilding(),
				cadastre.getInmCommentAddress(), userSystemId);

		return cadastre;

	}

	private String updateCadastreInmAddres(String idCadastre,
			String cadastralCode, String inmNeighbourhoodId, String inmStreet,
			String inmStreetNumber, boolean inmStreetNumberEstimated,
			String inmBuilding, String inmCommentAddress, String userId) {

		ConnectionWrapper connectionWrapper = null;

		try {

			connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();

			connectionWrapper.begin();

			// ---------------------------------------------------------------------------
			String sql = "UPDATE cclip.cadastre SET inm_neighbourhood_id = ?, inm_street = ?, inm_street_number = ?, inm_street_number_estimated = ?, inm_building = ?, inm_comment_address = ?, "
					+ "audit_date_update = now(), audit_user_update = ?, audit_version_code_label = ?, audit_version_label = ? WHERE id = ?";

			Object[] args = new Object[10];

			if (inmNeighbourhoodId == null) {
				args[0] = String.class;
			} else {
				args[0] = inmNeighbourhoodId;
			}

			if (inmStreet == null) {
				args[1] = String.class;
			} else {
				args[1] = inmStreet;
			}

			if (inmStreetNumber == null) {
				args[2] = String.class;
			} else {
				args[2] = inmStreetNumber;
			}

			args[3] = inmStreetNumberEstimated;

			if (inmBuilding == null) {
				args[4] = String.class;
			} else {
				args[4] = inmBuilding;
			}

			if (inmCommentAddress == null) {
				args[5] = String.class;
			} else {
				args[5] = inmCommentAddress;
			}

			if (userId == null) {
				args[6] = String.class;
			} else {
				args[6] = userId;
			}

			args[7] = "10";
			args[8] = "Actualización directa";

			if (idCadastre == null) {
				args[9] = String.class;
			} else {
				args[9] = idCadastre;
			}

			int rows = connectionWrapper.update(sql, args);

			if (rows != 1) {

				throw new RuntimeException("Error al actualizar la parcela "
						+ idCadastre + "\t" + cadastralCode);
			}
			// ---------------------------------------------------------------------------

			connectionWrapper.commit();

			return idCadastre;

		} catch (Exception e) {

			connectionWrapper.rollBack();

			UtilComponent.logger(e);

			throw new RuntimeException("Error al actualizar la parcela "
					+ idCadastre + "\t" + cadastralCode);

			// return null;

		}

	}

	private String updateCadastreCensusInmAddres(String idCadastre,
			String cadastralCode, String inmNeighbourhoodId, String inmStreet,
			String inmStreetNumber, boolean inmStreetNumberEstimated,
			String inmBuilding, String inmCommentAddress, String userId) {

		ConnectionWrapper connectionWrapper = null;

		try {

			connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();

			connectionWrapper.begin();

			// ---------------------------------------------------------------------------
			String sql = "UPDATE cclip.cadastre_census SET inm_neighbourhood_id = ?, inm_street = ?, inm_street_number = ?, inm_street_number_estimated = ?, inm_building = ?, inm_comment_address = ?, "
					+ "audit_date_update = now(), audit_user_update = ?, audit_version_code_label = ?, audit_version_label = ? WHERE id = ?";

			Object[] args = new Object[10];

			if (inmNeighbourhoodId == null) {
				args[0] = String.class;
			} else {
				args[0] = inmNeighbourhoodId;
			}

			if (inmStreet == null) {
				args[1] = String.class;
			} else {
				args[1] = inmStreet;
			}

			if (inmStreetNumber == null) {
				args[2] = String.class;
			} else {
				args[2] = inmStreetNumber;
			}

			args[3] = inmStreetNumberEstimated;

			if (inmBuilding == null) {
				args[4] = String.class;
			} else {
				args[4] = inmBuilding;
			}

			if (inmCommentAddress == null) {
				args[5] = String.class;
			} else {
				args[5] = inmCommentAddress;
			}

			if (userId == null) {
				args[6] = String.class;
			} else {
				args[6] = userId;
			}

			args[7] = "10";
			args[8] = "Actualización directa";

			if (idCadastre == null) {
				args[9] = String.class;
			} else {
				args[9] = idCadastre;
			}

			int rows = connectionWrapper.update(sql, args);

			if (rows != 1) {

				throw new RuntimeException("Error al actualizar la parcela "
						+ idCadastre + "\t" + cadastralCode);
			}
			// ---------------------------------------------------------------------------

			connectionWrapper.commit();

			return idCadastre;

		} catch (Exception e) {

			connectionWrapper.rollBack();

			UtilComponent.logger(e);

			throw new RuntimeException("Error al actualizar la parcela "
					+ idCadastre + "\t" + cadastralCode);

			// return null;

		}

	}

	public Cadastre updateUserWaterService(Cadastre cadastre,
			UserSystem userSystem) {

		if (cadastre == null) {
			return null;
		}

		String userWaterSituationId = null;

		if (cadastre.getUserWaterSituation() != null) {
			userWaterSituationId = cadastre.getUserWaterSituation().getId();
		}

		String userIvaId = null;

		if (cadastre.getUserIva() != null) {
			userIvaId = cadastre.getUserIva().getId();
		}

		String userSystemId = null;

		if (userSystem != null) {
			userSystemId = userSystem.getId();
		}

		updateUserWaterService(cadastre.getId(), cadastre.getCadastralCode(),
				userWaterSituationId, cadastre.getUserWaterService(),
				cadastre.getUserWaterServiceDni(),
				cadastre.getUserWaterServiceCuit(), userIvaId,
				cadastre.getUserPhone(), cadastre.getUserPostalPostalCode(),
				cadastre.getUserPostalNeighbourhood(),
				cadastre.getUserPostalStreet(),
				cadastre.getUserPostalStreetNumber(),
				cadastre.getUserPostalBuildingFloor(),
				cadastre.getUserPostalBuildingRoom(),
				cadastre.getUserPostalBuilding(),
				cadastre.getUserPostalCommentAddress(), userSystemId);

		return cadastre;
	}

	public Cadastre updateUserWaterServiceCensus(Cadastre cadastre,
			UserSystem userSystem) {

		if (cadastre == null) {
			return null;
		}

		String userWaterSituationId = null;

		if (cadastre.getUserWaterSituation() != null) {
			userWaterSituationId = cadastre.getUserWaterSituation().getId();
		}

		String userIvaId = null;

		if (cadastre.getUserIva() != null) {
			userIvaId = cadastre.getUserIva().getId();
		}

		String userSystemId = null;

		if (userSystem != null) {
			userSystemId = userSystem.getId();
		}

		updateUserWaterServiceCensus(cadastre.getId(),
				cadastre.getCadastralCode(), userWaterSituationId,
				cadastre.getUserWaterService(),
				cadastre.getUserWaterServiceDni(),
				cadastre.getUserWaterServiceCuit(), userIvaId,
				cadastre.getUserPhone(), cadastre.getUserPostalPostalCode(),
				cadastre.getUserPostalNeighbourhood(),
				cadastre.getUserPostalStreet(),
				cadastre.getUserPostalStreetNumber(),
				cadastre.getUserPostalBuildingFloor(),
				cadastre.getUserPostalBuildingRoom(),
				cadastre.getUserPostalBuilding(),
				cadastre.getUserPostalCommentAddress(), userSystemId);

		return cadastre;
	}

	private String updateUserWaterService(String idCadastre,
			String cadastralCode, String userWaterSituationId,
			String userWaterService, String userWaterServiceDni,
			String userWaterServiceCuit, String userIvaId, String userPhone,
			String userPostalPostalCode, String userPostalNeighbourhood,
			String userPostalStreet, String streetNumber, String buildingFloor,
			String buildingRoom, String building, String commentAddress,
			String userId) {

		ConnectionWrapper connectionWrapper = null;

		try {

			connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();

			connectionWrapper.begin();

			// ---------------------------------------------------------------------------
			String sql = "UPDATE cclip.cadastre SET  user_water_situation_id = ?, user_water_service = ?, user_water_service_dni = ?, user_water_service_cuit = ?, user_iva_id = ?, user_phone = ?, "
					+ "user_postal_postal_code = ?, user_postal_neighbourhood = ?, user_postal_street = ?, user_postal_street_number = ?, user_postal_building_floor = ?, "
					+ "user_postal_building_room = ?, user_postal_building = ?, user_postal_comment_address = ?, "
					+ "audit_date_update = now(), audit_user_update = ?, audit_version_code_label = ?, audit_version_label = ? WHERE id = ?";

			Object[] args = new Object[18];

			if (userWaterSituationId == null) {
				args[0] = String.class;
			} else {
				args[0] = userWaterSituationId;
			}

			if (userWaterService == null) {
				args[1] = String.class;
			} else {
				args[1] = userWaterService;
			}

			if (userWaterServiceDni == null) {
				args[2] = String.class;
			} else {
				args[2] = userWaterServiceDni;
			}

			if (userWaterServiceCuit == null) {
				args[3] = String.class;
			} else {
				args[3] = userWaterServiceCuit;
			}

			if (userIvaId == null) {
				args[4] = String.class;
			} else {
				args[4] = userIvaId;
			}

			if (userPhone == null) {
				args[5] = String.class;
			} else {
				args[5] = userPhone;
			}

			if (userPostalPostalCode == null) {
				args[6] = String.class;
			} else {
				args[6] = userPostalPostalCode;
			}

			if (userPostalNeighbourhood == null) {
				args[7] = String.class;
			} else {
				args[7] = userPostalNeighbourhood;
			}

			if (userPostalStreet == null) {
				args[8] = String.class;
			} else {
				args[8] = userPostalStreet;
			}

			if (streetNumber == null) {
				args[9] = String.class;
			} else {
				args[9] = streetNumber;
			}

			if (buildingFloor == null) {
				args[10] = String.class;
			} else {
				args[10] = buildingFloor;
			}

			if (buildingRoom == null) {
				args[11] = String.class;
			} else {
				args[11] = buildingRoom;
			}

			if (building == null) {
				args[12] = String.class;
			} else {
				args[12] = building;
			}

			if (commentAddress == null) {
				args[13] = String.class;
			} else {
				args[13] = commentAddress;
			}

			if (userId == null) {
				args[14] = String.class;
			} else {
				args[14] = userId;
			}

			args[15] = "10";
			args[16] = "Actualización directa";

			if (idCadastre == null) {
				args[17] = String.class;
			} else {
				args[17] = idCadastre;
			}

			int rows = connectionWrapper.update(sql, args);

			if (rows != 1) {

				throw new RuntimeException("Error al actualizar la parcela "
						+ idCadastre + "\t" + cadastralCode);
			}
			// ---------------------------------------------------------------------------

			connectionWrapper.commit();

			return idCadastre;

		} catch (Exception e) {

			connectionWrapper.rollBack();

			UtilComponent.logger(e);

			throw new RuntimeException("Error al actualizar la parcela "
					+ idCadastre + "\t" + cadastralCode);

			// return null;

		}

	}

	private String updateUserWaterServiceCensus(String idCadastre,
			String cadastralCode, String userWaterSituationId,
			String userWaterService, String userWaterServiceDni,
			String userWaterServiceCuit, String userIvaId, String userPhone,
			String userPostalPostalCode, String userPostalNeighbourhood,
			String userPostalStreet, String streetNumber, String buildingFloor,
			String buildingRoom, String building, String commentAddress,
			String userId) {

		ConnectionWrapper connectionWrapper = null;

		try {

			connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();

			connectionWrapper.begin();

			// ---------------------------------------------------------------------------
			String sql = "UPDATE cclip.cadastre_census SET  user_water_situation_id = ?, user_water_service = ?, user_water_service_dni = ?, user_water_service_cuit = ?, user_iva_id = ?, user_phone = ?, "
					+ "user_postal_postal_code = ?, user_postal_neighbourhood = ?, user_postal_street = ?, user_postal_street_number = ?, user_postal_building_floor = ?, "
					+ "user_postal_building_room = ?, user_postal_building = ?, user_postal_comment_address = ?, "
					+ "audit_date_update = now(), audit_user_update = ?, audit_version_code_label = ?, audit_version_label = ? WHERE id = ?";

			Object[] args = new Object[18];

			if (userWaterSituationId == null) {
				args[0] = String.class;
			} else {
				args[0] = userWaterSituationId;
			}

			if (userWaterService == null) {
				args[1] = String.class;
			} else {
				args[1] = userWaterService;
			}

			if (userWaterServiceDni == null) {
				args[2] = String.class;
			} else {
				args[2] = userWaterServiceDni;
			}

			if (userWaterServiceCuit == null) {
				args[3] = String.class;
			} else {
				args[3] = userWaterServiceCuit;
			}

			if (userIvaId == null) {
				args[4] = String.class;
			} else {
				args[4] = userIvaId;
			}

			if (userPhone == null) {
				args[5] = String.class;
			} else {
				args[5] = userPhone;
			}

			if (userPostalPostalCode == null) {
				args[6] = String.class;
			} else {
				args[6] = userPostalPostalCode;
			}

			if (userPostalNeighbourhood == null) {
				args[7] = String.class;
			} else {
				args[7] = userPostalNeighbourhood;
			}

			if (userPostalStreet == null) {
				args[8] = String.class;
			} else {
				args[8] = userPostalStreet;
			}

			if (streetNumber == null) {
				args[9] = String.class;
			} else {
				args[9] = streetNumber;
			}

			if (buildingFloor == null) {
				args[10] = String.class;
			} else {
				args[10] = buildingFloor;
			}

			if (buildingRoom == null) {
				args[11] = String.class;
			} else {
				args[11] = buildingRoom;
			}

			if (building == null) {
				args[12] = String.class;
			} else {
				args[12] = building;
			}

			if (commentAddress == null) {
				args[13] = String.class;
			} else {
				args[13] = commentAddress;
			}

			if (userId == null) {
				args[14] = String.class;
			} else {
				args[14] = userId;
			}

			args[15] = "10";
			args[16] = "Actualización directa";

			if (idCadastre == null) {
				args[17] = String.class;
			} else {
				args[17] = idCadastre;
			}

			int rows = connectionWrapper.update(sql, args);

			if (rows != 1) {

				throw new RuntimeException("Error al actualizar la parcela "
						+ idCadastre + "\t" + cadastralCode);
			}
			// ---------------------------------------------------------------------------

			connectionWrapper.commit();

			return idCadastre;

		} catch (Exception e) {

			connectionWrapper.rollBack();

			UtilComponent.logger(e);

			throw new RuntimeException("Error al actualizar la parcela "
					+ idCadastre + "\t" + cadastralCode);

			// return null;

		}

	}

	public Object[][] findCadastreBlockG(String cadastreId) {
		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT * FROM cclip.v_cadastre_block_g WHERE 	cadastre_id = ?;";

			Object[][] table = connectionWrapper.findToTable(sql, cadastreId);

			connectionWrapper.close();

			return table;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException("Error al consultar los datos");
		}
	}

	public Object[][] findCadastreBlockCensusG(String cadastreId) {
		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT * FROM cclip.v_cadastre_block_census_g WHERE 	cadastre_id = ?;";

			Object[][] table = connectionWrapper.findToTable(sql, cadastreId);

			connectionWrapper.close();

			return table;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException("Error al consultar los datos");
		}
	}

	public Object[][] findCadastreBlockI(String cadastreId) {
		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT * FROM cclip.v_cadastre_block_i WHERE 	cadastre_id = ?;";

			Object[][] table = connectionWrapper.findToTable(sql, cadastreId);

			connectionWrapper.close();

			return table;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException("Error al consultar los datos");
		}
	}

	public Object[][] findCadastreBlockCensusI(String cadastreId) {
		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT * FROM cclip.v_cadastre_block_census_i WHERE 	cadastre_id = ?;";

			Object[][] table = connectionWrapper.findToTable(sql, cadastreId);

			connectionWrapper.close();

			return table;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException("Error al consultar los datos");
		}
	}

	public Object[][] findCadastreBlockC(String cadastreId) {
		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT * FROM cclip.v_cadastre_block_c WHERE 	cadastre_id = ?;";

			Object[][] table = connectionWrapper.findToTable(sql, cadastreId);

			connectionWrapper.close();

			return table;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException("Error al consultar los datos");
		}
	}

	public Object[][] findCadastreBlockCensusC(String cadastreId) {
		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT * FROM cclip.v_cadastre_block_census_c WHERE 	cadastre_id = ?;";

			Object[][] table = connectionWrapper.findToTable(sql, cadastreId);

			connectionWrapper.close();

			return table;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException("Error al consultar los datos");
		}
	}

	public Cadastre saveCadastreBlock(Cadastre cadastre,
			CadastreBlock[] cadastreBlockList, UserSystem userSystem) {

		if (cadastre == null || cadastre.getId() == null
				|| cadastre.getId().trim().length() == 0 || userSystem == null
				|| userSystem.getId() == null
				|| userSystem.getId().trim().length() == 0) {
			return null;
		}

		ConnectionWrapper connectionWrapper = null;

		try {

			connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();

			connectionWrapper.begin();

			// ---------------------------------------------------------------------------

			String sql = "SELECT id FROM cclip.cadastre_block WHERE cadastre_id = ?;";

			Object[][] tableO = connectionWrapper.findToTable(sql, cadastre
					.getId().trim());

			if (tableO != null && tableO.length > 0) {

				boolean b = false;

				for (int i = 0; i < tableO.length; i++) {

					sql = "UPDATE cclip.cadastre_block SET erased = true WHERE id = ?;";

					int rows = connectionWrapper.update(sql,
							tableO[i][0].toString());

					if (rows != 1) {

						throw new RuntimeException(
								"Error al actualizar los bloques la parcela "
										+ cadastre.getId().trim() + "\t"
										+ cadastre.getCadastralCode());
					}

					// for (int x = 0; x < cadastreBlockList.length; x++) {
					// if
					// (tableO[i][0].toString().equals(cadastreBlockList[x].getId()))
					// {
					// b = true;
					// }
					// }
					//
					// if (b == false) {
					//
					// sql =
					// "UPDATE cclip.cadastre_block SET erased = true WHERE id = ?;";
					//
					// int rows = connectionWrapper.update(sql,
					// tableO[i][0].toString());
					//
					// if (rows != 1) {
					//
					// throw new
					// RuntimeException("Error al actualizar los bloques la parcela "
					// + cadastre.getId().trim() + "\t" +
					// cadastre.getCadastralCode());
					// }
					// }
				}

			}

			String sqlu = "UPDATE cclip.cadastre_block SET " + "id=?, "// 0
					+ "erased=?, "// 1
					+ "year_building=?, "// 2
					+ "month_building=?, "// 3
					+ "m2_covered=?, "// 4
					+ "demolition=false, "//
					+ "comment=null, " //
					+ "facade=?, "// 5
					+ "roof_structure=?, "// 6
					+ "homes=?, "// 7
					+ "interior_walls=?, "// 8
					+ "ceiling=?, "// 9
					+ "kitchen=?, "// 10
					+ "toilets=?, "// 11
					+ "entrance_hall=?, "// 12
					+ "facilities=?, "// 13
					+ "carpentry=?, "// 14
					+ "cover_structure=?, "// 15
					+ "ornamentation=?, "// 16
					+ "stained_glass_lighting=?, "// 17
					+ "buffet_dining=?, "// 18
					+ "points=?, "// 19
					+ "apc=?, "// 20
					+ "rate=?, "// 21
					+ "apc_desc=?, "// 22
					+ "cadastre_activity_type_id=?, "// 23
					+ "cadastre_constructive_type_id=?, "// 24
					+ "cadastre_destination_type_id=?, "// 25
					+ "cadastre_id=?,  "// 26
					+ "audit_date_update=now(), "//
					+ "audit_user_update=?, "// 27
					+ "audit_version_code_label=?, "// 28
					+ "audit_version_label=? "// 29
					+ "WHERE id = ?;";// 30

			String sqli = "INSERT INTO cclip.cadastre_block("//
					+ "id, "// 0
					+ "erased, "// 1
					+ "year_building, "// 2
					+ "month_building, "// 3
					+ "m2_covered, "// 4
					+ "demolition, "//
					+ "comment, "//
					+ "facade, "// 5
					+ "roof_structure, "// 6
					+ "homes, "// 7
					+ "interior_walls, "// 8
					+ "ceiling, "// 9
					+ "kitchen, "// 10
					+ "toilets, "// 11
					+ "entrance_hall, "// 12
					+ "facilities, "// 13
					+ "carpentry, "// 14
					+ "cover_structure, "// 15
					+ "ornamentation, "// 16
					+ "stained_glass_lighting, "// 17
					+ "buffet_dining, "// 18
					+ "points, "// 19
					+ "apc, "// 20
					+ "rate, "// 21
					+ "apc_desc, "// 22
					+ "cadastre_activity_type_id, "// 23
					+ "cadastre_constructive_type_id, "// 24
					+ "cadastre_destination_type_id,"// 25
					+ "cadastre_id,"// 26

					+ "audit_date_create, "//
					+ "audit_user_create,"// 27
					+ "audit_version_code_label, "// 28
					+ "audit_version_label "// 29

					+ ") VALUES (" //
					+ "?, "// 0
					+ "?, "// 1
					+ "?, "// 2
					+ "?, "// 3
					+ "?, "// 4
					+ "false," //
					+ "null,"//
					+ "?, "// 5
					+ "?, "// 6
					+ "?, "// 7
					+ "?, "// 8
					+ "?, "// 9
					+ "?, "// 10
					+ "?, "// 11
					+ "?, "// 12
					+ "?, "// 13
					+ "?, "// 14
					+ "?, "// 15
					+ "?, "// 16
					+ "?, "// 17
					+ "?, "// 18
					+ "?, "// 19
					+ "?, "// 20
					+ "?, "// 21
					+ "?, "// 22
					+ "?, "// 23
					+ "?, "// 24
					+ "?, "// 25
					+ "?, "// 26
					+ "now(), "//
					+ "?, "// 27
					+ "?, "// 28
					+ "?"// 29
					+ ");";

			for (CadastreBlock cadastreBlock : cadastreBlockList) {

				Object[] args = new Object[0];
				int index = 0;// 0

				if (cadastreBlock.getId() != null) {
					sql = sqlu;

					args = new Object[31];

					if (cadastreBlock.getId() != null
							&& cadastreBlock.getId().trim().length() > 0) {
						args[index] = cadastreBlock.getId().trim();
					} else {
						args[index] = String.class;
					}

				} else {
					sql = sqli;

					args = new Object[30];
					args[index] = UUID.randomUUID().toString();
				}

				// -----------------------------------

				index++; // 1
				if (cadastreBlock.getErased() != null) {
					args[index] = cadastreBlock.getErased();
				} else {
					args[index] = Boolean.class;
				}

				index++;// 2
				if (cadastreBlock.getYearBuilding() != null) {
					args[index] = cadastreBlock.getYearBuilding();
				} else {
					args[index] = Integer.class;
				}

				index++;// 3
				if (cadastreBlock.getMonthBuilding() != null) {
					args[index] = cadastreBlock.getMonthBuilding();
				} else {
					args[index] = Integer.class;
				}

				index++;// 4
				if (cadastreBlock.getM2Covered() != null) {
					args[index] = cadastreBlock.getM2Covered();
				} else {
					args[index] = Double.class;
				}

				index++;// 5
				if (cadastreBlock.getFacade() != null) {
					args[index] = cadastreBlock.getFacade();
				} else {
					args[index] = String.class;
				}

				index++;// 6
				if (cadastreBlock.getRoofStructure() != null) {
					args[index] = cadastreBlock.getRoofStructure();
				} else {
					args[index] = String.class;
				}

				index++;// 7
				if (cadastreBlock.getHomes() != null) {
					args[index] = cadastreBlock.getHomes();
				} else {
					args[index] = String.class;
				}

				index++;// 8
				if (cadastreBlock.getInteriorWalls() != null) {
					args[index] = cadastreBlock.getInteriorWalls();
				} else {
					args[index] = String.class;
				}

				index++;// 9
				if (cadastreBlock.getCeiling() != null) {
					args[index] = cadastreBlock.getCeiling();
				} else {
					args[index] = String.class;
				}

				index++;// 10
				if (cadastreBlock.getKitchen() != null) {
					args[index] = cadastreBlock.getKitchen();
				} else {
					args[index] = String.class;
				}

				index++;// 11
				if (cadastreBlock.getToilets() != null) {
					args[index] = cadastreBlock.getToilets();
				} else {
					args[index] = String.class;
				}

				index++;// 12
				if (cadastreBlock.getEntranceHall() != null) {
					args[index] = cadastreBlock.getEntranceHall();
				} else {
					args[index] = String.class;
				}

				index++;// 13
				if (cadastreBlock.getFacilities() != null) {
					args[index] = cadastreBlock.getFacilities();
				} else {
					args[index] = String.class;
				}

				index++;// 14
				if (cadastreBlock.getCarpentry() != null) {
					args[index] = cadastreBlock.getCarpentry();
				} else {
					args[index] = String.class;
				}

				index++;// 15
				if (cadastreBlock.getCoverStructure() != null) {
					args[index] = cadastreBlock.getCoverStructure();
				} else {
					args[index] = String.class;
				}

				index++;// 16
				if (cadastreBlock.getOrnamentation() != null) {
					args[index] = cadastreBlock.getOrnamentation();
				} else {
					args[index] = String.class;
				}

				index++;// 17
				if (cadastreBlock.getStainedGlassLighting() != null) {
					args[index] = cadastreBlock.getStainedGlassLighting();
				} else {
					args[index] = String.class;
				}

				index++;// 18
				if (cadastreBlock.getBuffetDining() != null) {
					args[index] = cadastreBlock.getBuffetDining();
				} else {
					args[index] = String.class;
				}

				// ---------------------------------------

				index++;// 19
				if (cadastreBlock.getPoints() != null) {
					args[index] = cadastreBlock.getPoints();
				} else {
					args[index] = Integer.class;
				}

				index++;// 20
				if (cadastreBlock.getApc() != null) {
					args[index] = cadastreBlock.getApc();
				} else {
					args[index] = Boolean.class;
				}

				index++;// 21
				if (cadastreBlock.getRate() != null) {
					args[index] = cadastreBlock.getRate();
				} else {
					args[index] = Integer.class;
				}

				index++;// 22
				if (cadastreBlock.getApcDesc() != null) {
					args[index] = cadastreBlock.getApcDesc();
				} else {
					args[index] = String.class;
				}

				index++;// 23
				if (cadastreBlock.getCadastreActivityType() != null
						&& cadastreBlock.getCadastreActivityType().getId() != null) {
					args[index] = cadastreBlock.getCadastreActivityType()
							.getId();
				} else {
					args[index] = String.class;
				}

				index++;// 24
				if (cadastreBlock.getCadastreConstructiveType() != null
						&& cadastreBlock.getCadastreConstructiveType().getId() != null) {
					args[index] = cadastreBlock.getCadastreConstructiveType()
							.getId();
				} else {
					args[index] = String.class;
				}

				index++;// 25
				if (cadastreBlock.getCadastreDestinationType() != null
						&& cadastreBlock.getCadastreDestinationType().getId() != null) {
					args[index] = cadastreBlock.getCadastreDestinationType()
							.getId();
				} else {
					args[index] = String.class;
				}

				index++;// 26
				if (cadastreBlock.getCadastre() != null
						&& cadastreBlock.getCadastre().getId() != null) {
					args[index] = cadastreBlock.getCadastre().getId();
				} else {
					args[index] = String.class;
				}

				// ------------------------------------------------

				index++;// 27
				if (userSystem != null && userSystem.getId() != null
						&& userSystem.getId().trim().length() > 0) {
					args[index] = userSystem.getId().trim();
				} else {
					args[index] = String.class;
				}

				index++;// 28
				args[index] = "10";

				index++;// 29
				args[index] = "Actualización directa";

				if (cadastreBlock.getId() != null) {
					index++;// 30
					if (cadastreBlock.getId() != null
							&& cadastreBlock.getId().trim().length() > 0) {
						args[index] = cadastreBlock.getId().trim();
					} else {
						args[index] = String.class;
					}
				}

				// for (int i = 0; i < args.length; i++) {
				// System.out.println(i + ": " + args[i]);
				// }

				int rows = connectionWrapper.update(sql, args);

				if (rows != 1) {

					throw new RuntimeException(
							"Error al actualizar la parcela "
									+ cadastre.getId().trim() + "\t"
									+ cadastre.getCadastralCode());
				}

			}

			// ---------------------------------------------------------------------------

			connectionWrapper.commit();

			return cadastre;

		} catch (Exception e) {

			connectionWrapper.rollBack();

			UtilComponent.logger(e);

			throw new RuntimeException(
					"Error al actualizar los bloques la parcela "
							+ cadastre.getId().trim() + "\t"
							+ cadastre.getCadastralCode());

			// return null;

		}

	}

	public Cadastre saveCadastreBlockCensus(Cadastre cadastre,
			CadastreBlock[] cadastreBlockList, UserSystem userSystem) {

		if (cadastre == null || cadastre.getId() == null
				|| cadastre.getId().trim().length() == 0 || userSystem == null
				|| userSystem.getId() == null
				|| userSystem.getId().trim().length() == 0) {
			return null;
		}

		ConnectionWrapper connectionWrapper = null;

		try {

			connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();

			connectionWrapper.begin();

			// ---------------------------------------------------------------------------

			String sql = "SELECT id FROM cclip.cadastre_block_census WHERE cadastre_id = ?;";

			Object[][] tableO = connectionWrapper.findToTable(sql, cadastre
					.getId().trim());

			if (tableO != null && tableO.length > 0) {

				boolean b = false;

				for (int i = 0; i < tableO.length; i++) {

					sql = "UPDATE cclip.cadastre_block_census SET erased = true WHERE id = ?;";

					int rows = connectionWrapper.update(sql,
							tableO[i][0].toString());

					if (rows != 1) {

						throw new RuntimeException(
								"Error al actualizar los bloques la parcela "
										+ cadastre.getId().trim() + "\t"
										+ cadastre.getCadastralCode());
					}

					// for (int x = 0; x < cadastreBlockList.length; x++) {
					// if
					// (tableO[i][0].toString().equals(cadastreBlockList[x].getId()))
					// {
					// b = true;
					// }
					// }
					//
					// if (b == false) {
					//
					// sql =
					// "UPDATE cclip.cadastre_block SET erased = true WHERE id = ?;";
					//
					// int rows = connectionWrapper.update(sql,
					// tableO[i][0].toString());
					//
					// if (rows != 1) {
					//
					// throw new
					// RuntimeException("Error al actualizar los bloques la parcela "
					// + cadastre.getId().trim() + "\t" +
					// cadastre.getCadastralCode());
					// }
					// }
				}

			}

			String sqlu = "UPDATE cclip.cadastre_block_census SET " + "id=?, "// 0
					+ "erased=?, "// 1
					+ "year_building=?, "// 2
					+ "month_building=?, "// 3
					+ "m2_covered=?, "// 4
					+ "demolition=false, "//
					+ "comment=null, " //
					+ "facade=?, "// 5
					+ "roof_structure=?, "// 6
					+ "homes=?, "// 7
					+ "interior_walls=?, "// 8
					+ "ceiling=?, "// 9
					+ "kitchen=?, "// 10
					+ "toilets=?, "// 11
					+ "entrance_hall=?, "// 12
					+ "facilities=?, "// 13
					+ "carpentry=?, "// 14
					+ "cover_structure=?, "// 15
					+ "ornamentation=?, "// 16
					+ "stained_glass_lighting=?, "// 17
					+ "buffet_dining=?, "// 18
					+ "points=?, "// 19
					+ "apc=?, "// 20
					+ "rate=?, "// 21
					+ "apc_desc=?, "// 22
					+ "cadastre_activity_type_id=?, "// 23
					+ "cadastre_constructive_type_id=?, "// 24
					+ "cadastre_destination_type_id=?, "// 25
					+ "cadastre_id=?,  "// 26
					+ "audit_date_update=now(), "//
					+ "audit_user_update=?, "// 27
					+ "audit_version_code_label=?, "// 28
					+ "audit_version_label=? "// 29
					+ "WHERE id = ?;";// 30

			String sqli = "INSERT INTO cclip.cadastre_block_census ("//
					+ "id, "// 0
					+ "erased, "// 1
					+ "year_building, "// 2
					+ "month_building, "// 3
					+ "m2_covered, "// 4
					+ "demolition, "//
					+ "comment, "//
					+ "facade, "// 5
					+ "roof_structure, "// 6
					+ "homes, "// 7
					+ "interior_walls, "// 8
					+ "ceiling, "// 9
					+ "kitchen, "// 10
					+ "toilets, "// 11
					+ "entrance_hall, "// 12
					+ "facilities, "// 13
					+ "carpentry, "// 14
					+ "cover_structure, "// 15
					+ "ornamentation, "// 16
					+ "stained_glass_lighting, "// 17
					+ "buffet_dining, "// 18
					+ "points, "// 19
					+ "apc, "// 20
					+ "rate, "// 21
					+ "apc_desc, "// 22
					+ "cadastre_activity_type_id, "// 23
					+ "cadastre_constructive_type_id, "// 24
					+ "cadastre_destination_type_id,"// 25
					+ "cadastre_id,"// 26

					+ "audit_date_create, "//
					+ "audit_user_create,"// 27
					+ "audit_version_code_label, "// 28
					+ "audit_version_label "// 29

					+ ") VALUES (" //
					+ "?, "// 0
					+ "?, "// 1
					+ "?, "// 2
					+ "?, "// 3
					+ "?, "// 4
					+ "false," //
					+ "null,"//
					+ "?, "// 5
					+ "?, "// 6
					+ "?, "// 7
					+ "?, "// 8
					+ "?, "// 9
					+ "?, "// 10
					+ "?, "// 11
					+ "?, "// 12
					+ "?, "// 13
					+ "?, "// 14
					+ "?, "// 15
					+ "?, "// 16
					+ "?, "// 17
					+ "?, "// 18
					+ "?, "// 19
					+ "?, "// 20
					+ "?, "// 21
					+ "?, "// 22
					+ "?, "// 23
					+ "?, "// 24
					+ "?, "// 25
					+ "?, "// 26
					+ "now(), "//
					+ "?, "// 27
					+ "?, "// 28
					+ "?"// 29
					+ ");";

			for (CadastreBlock cadastreBlock : cadastreBlockList) {

				Object[] args = new Object[0];
				int index = 0;// 0

				if (cadastreBlock.getId() != null) {
					sql = sqlu;

					args = new Object[31];

					if (cadastreBlock.getId() != null
							&& cadastreBlock.getId().trim().length() > 0) {
						args[index] = cadastreBlock.getId().trim();
					} else {
						args[index] = String.class;
					}

				} else {
					sql = sqli;

					args = new Object[30];
					args[index] = UUID.randomUUID().toString();
				}

				// -----------------------------------

				index++; // 1
				if (cadastreBlock.getErased() != null) {
					args[index] = cadastreBlock.getErased();
				} else {
					args[index] = Boolean.class;
				}

				index++;// 2
				if (cadastreBlock.getYearBuilding() != null) {
					args[index] = cadastreBlock.getYearBuilding();
				} else {
					args[index] = Integer.class;
				}

				index++;// 3
				if (cadastreBlock.getMonthBuilding() != null) {
					args[index] = cadastreBlock.getMonthBuilding();
				} else {
					args[index] = Integer.class;
				}

				index++;// 4
				if (cadastreBlock.getM2Covered() != null) {
					args[index] = cadastreBlock.getM2Covered();
				} else {
					args[index] = Double.class;
				}

				index++;// 5
				if (cadastreBlock.getFacade() != null) {
					args[index] = cadastreBlock.getFacade();
				} else {
					args[index] = String.class;
				}

				index++;// 6
				if (cadastreBlock.getRoofStructure() != null) {
					args[index] = cadastreBlock.getRoofStructure();
				} else {
					args[index] = String.class;
				}

				index++;// 7
				if (cadastreBlock.getHomes() != null) {
					args[index] = cadastreBlock.getHomes();
				} else {
					args[index] = String.class;
				}

				index++;// 8
				if (cadastreBlock.getInteriorWalls() != null) {
					args[index] = cadastreBlock.getInteriorWalls();
				} else {
					args[index] = String.class;
				}

				index++;// 9
				if (cadastreBlock.getCeiling() != null) {
					args[index] = cadastreBlock.getCeiling();
				} else {
					args[index] = String.class;
				}

				index++;// 10
				if (cadastreBlock.getKitchen() != null) {
					args[index] = cadastreBlock.getKitchen();
				} else {
					args[index] = String.class;
				}

				index++;// 11
				if (cadastreBlock.getToilets() != null) {
					args[index] = cadastreBlock.getToilets();
				} else {
					args[index] = String.class;
				}

				index++;// 12
				if (cadastreBlock.getEntranceHall() != null) {
					args[index] = cadastreBlock.getEntranceHall();
				} else {
					args[index] = String.class;
				}

				index++;// 13
				if (cadastreBlock.getFacilities() != null) {
					args[index] = cadastreBlock.getFacilities();
				} else {
					args[index] = String.class;
				}

				index++;// 14
				if (cadastreBlock.getCarpentry() != null) {
					args[index] = cadastreBlock.getCarpentry();
				} else {
					args[index] = String.class;
				}

				index++;// 15
				if (cadastreBlock.getCoverStructure() != null) {
					args[index] = cadastreBlock.getCoverStructure();
				} else {
					args[index] = String.class;
				}

				index++;// 16
				if (cadastreBlock.getOrnamentation() != null) {
					args[index] = cadastreBlock.getOrnamentation();
				} else {
					args[index] = String.class;
				}

				index++;// 17
				if (cadastreBlock.getStainedGlassLighting() != null) {
					args[index] = cadastreBlock.getStainedGlassLighting();
				} else {
					args[index] = String.class;
				}

				index++;// 18
				if (cadastreBlock.getBuffetDining() != null) {
					args[index] = cadastreBlock.getBuffetDining();
				} else {
					args[index] = String.class;
				}

				// ---------------------------------------

				index++;// 19
				if (cadastreBlock.getPoints() != null) {
					args[index] = cadastreBlock.getPoints();
				} else {
					args[index] = Integer.class;
				}

				index++;// 20
				if (cadastreBlock.getApc() != null) {
					args[index] = cadastreBlock.getApc();
				} else {
					args[index] = Boolean.class;
				}

				index++;// 21
				if (cadastreBlock.getRate() != null) {
					args[index] = cadastreBlock.getRate();
				} else {
					args[index] = Integer.class;
				}

				index++;// 22
				if (cadastreBlock.getApcDesc() != null) {
					args[index] = cadastreBlock.getApcDesc();
				} else {
					args[index] = String.class;
				}

				index++;// 23
				if (cadastreBlock.getCadastreActivityType() != null
						&& cadastreBlock.getCadastreActivityType().getId() != null) {
					args[index] = cadastreBlock.getCadastreActivityType()
							.getId();
				} else {
					args[index] = String.class;
				}

				index++;// 24
				if (cadastreBlock.getCadastreConstructiveType() != null
						&& cadastreBlock.getCadastreConstructiveType().getId() != null) {
					args[index] = cadastreBlock.getCadastreConstructiveType()
							.getId();
				} else {
					args[index] = String.class;
				}

				index++;// 25
				if (cadastreBlock.getCadastreDestinationType() != null
						&& cadastreBlock.getCadastreDestinationType().getId() != null) {
					args[index] = cadastreBlock.getCadastreDestinationType()
							.getId();
				} else {
					args[index] = String.class;
				}

				index++;// 26
				if (cadastreBlock.getCadastre() != null
						&& cadastreBlock.getCadastre().getId() != null) {
					args[index] = cadastreBlock.getCadastre().getId();
				} else {
					args[index] = String.class;
				}

				// ------------------------------------------------

				index++;// 27
				if (userSystem != null && userSystem.getId() != null
						&& userSystem.getId().trim().length() > 0) {
					args[index] = userSystem.getId().trim();
				} else {
					args[index] = String.class;
				}

				index++;// 28
				args[index] = "10";

				index++;// 29
				args[index] = "Actualización directa";

				if (cadastreBlock.getId() != null) {
					index++;// 30
					if (cadastreBlock.getId() != null
							&& cadastreBlock.getId().trim().length() > 0) {
						args[index] = cadastreBlock.getId().trim();
					} else {
						args[index] = String.class;
					}
				}

				// for (int i = 0; i < args.length; i++) {
				// System.out.println(i + ": " + args[i]);
				// }

				int rows = connectionWrapper.update(sql, args);

				if (rows != 1) {

					throw new RuntimeException(
							"Error al actualizar la parcela "
									+ cadastre.getId().trim() + "\t"
									+ cadastre.getCadastralCode());
				}

			}

			// ---------------------------------------------------------------------------

			connectionWrapper.commit();

			return cadastre;

		} catch (Exception e) {

			connectionWrapper.rollBack();

			UtilComponent.logger(e);

			throw new RuntimeException(
					"Error al actualizar los bloques la parcela "
							+ cadastre.getId().trim() + "\t"
							+ cadastre.getCadastralCode());

			// return null;

		}

	}

	public Object[][] findCadastreConstructiveType() {
		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT id, name FROM cclip.cadastre_constructive_type WHERE erased = false";

			Object[][] table = connectionWrapper.findToTable(sql);

			connectionWrapper.close();

			return table;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException(
					"Error al consultar los tipos constructivos");
		}
	}

	public Object[][] findCadastreActivityType() {
		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT id, name FROM cclip.cadastre_activity_type WHERE erased = false";

			Object[][] table = connectionWrapper.findToTable(sql);

			connectionWrapper.close();

			return table;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException(
					"Error al consultar los códigos de actividad");
		}
	}

	public Object[][] findSchedule() {
		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT id, year FROM cclip.schedule WHERE erased = false ORDER BY year DESC";

			Object[][] table = connectionWrapper.findToTable(sql);

			connectionWrapper.close();

			return table;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException("Error al consultar los cronogramas");
		}
	}

	public Object[][] findCensusTaker() {
		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT census_taker.id, family_name,  given_name FROM cclip.census_taker JOIN cclip.person on person.id = census_taker.id WHERE census_taker.erased = false ORDER BY family_name,  given_name";

			Object[][] table = connectionWrapper.findToTable(sql);

			connectionWrapper.close();

			return table;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException("Error al consultar los censistas");
		}
	}

	public Object[][] findScheduleCensusResult() {
		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT id, name FROM cclip.schedule_census_result WHERE erased = false ORDER BY id, name";

			Object[][] table = connectionWrapper.findToTable(sql);

			connectionWrapper.close();

			return table;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException(
					"Error al consultar los códigos de resultados para censos");
		}
	}

	public Object[][] findScheduleCensusCensus() {
		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT id, name FROM cclip.schedule_census_result WHERE erased = false ORDER BY id, name";

			Object[][] table = connectionWrapper.findToTable(sql);

			connectionWrapper.close();

			return table;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException(
					"Error al consultar los años para censos");
		}
	}

	public Object[][] findScheduleCensusCensusItem(String scheduleBatchId) {
		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT id, year, cadastral_code, uf_id, cta_cli, censused, person, schedule_census_result, number_batch, close FROM cclip.v_schedule_census WHERE SCHEDULE_BATCH_ID = ?";

			Object[][] table = connectionWrapper.findToTable(sql,
					scheduleBatchId);

			connectionWrapper.close();

			return table;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException("Error al consultar los items de censos");
		}
	}

	public ResultList findScheduleBatchByExample(String scheduleId,
			Integer offSet, Integer limit) {

		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT schedule_batch.id, number_batch, year, close, delivered, schedule_batch.comment  FROM cclip.schedule_batch LEFT JOIN cclip.schedule ON schedule_batch.schedule_id = schedule.id WHERE schedule_id = ? ORDER BY number_batch DESC OFFSET ? LIMIT ? ;";
			String sqlCount = "SELECT COUNT(*) FROM cclip.schedule_batch WHERE schedule_id = ?";

			ResultList table = connectionWrapper.findToResultList(sql,
					sqlCount, offSet, limit, scheduleId);

			connectionWrapper.close();

			return table;

		} catch (Exception e) {

			UtilComponent.logger(e);

			return null;
		}

	}

	public Object[][] findScheduleBatchByscheduleId(String scheduleId) {

		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT schedule_batch.id, number_batch, year, close, delivered, schedule_batch.comment  "
					+ "FROM cclip.schedule_batch "
					+ "LEFT JOIN cclip.schedule ON schedule_batch.schedule_id = schedule.id "
					+ "WHERE schedule_id = ? " + "ORDER BY number_batch DESC ;";

			Object[][] table = connectionWrapper.findToTable(sql, scheduleId);

			connectionWrapper.close();

			return table;

		} catch (Exception e) {

			UtilComponent.logger(e);

			return null;
		}

	}

	public ScheduleBatch insertScheduleBatch(ScheduleBatch scheduleBatch) {

		if (scheduleBatch == null || scheduleBatch.getId() == null
				|| scheduleBatch.getId().trim().length() == 0) {
			return null;
		}

		ConnectionWrapper connectionWrapper = null;

		try {

			connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();

			connectionWrapper.begin();

			// ---------------------------------------------------------------------------

			String sql = "INSERT INTO cclip.schedule_batch(ID, ERASED, CREATE_DATE, SCHEDULE_ID, COMMENT) VALUES (?, false, now(), ?, ?)";

			Object[] args = new Object[3];

			args[0] = scheduleBatch.getId();

			if (scheduleBatch.getSchedule() != null
					&& scheduleBatch.getSchedule().getId() != null
					&& scheduleBatch.getSchedule().getId().trim().length() > 0) {
				args[1] = scheduleBatch.getSchedule().getId().trim();
			} else {
				args[1] = String.class;
			}

			if (scheduleBatch.getComment() != null
					&& scheduleBatch.getComment().trim().length() > 0) {
				args[2] = scheduleBatch.getComment().trim();
			} else {
				args[2] = String.class;
			}

			int rows = connectionWrapper.update(sql, args);

			if (rows != 1) {

				throw new RuntimeException("Error al actualizar la parcela "
						+ scheduleBatch.getId().trim() + "\t"
						+ scheduleBatch.getComment());
			}
			// ---------------------------------------------------------------------------

			connectionWrapper.commit();

			return scheduleBatch;

		} catch (Exception e) {

			connectionWrapper.rollBack();

			UtilComponent.logger(e);

			throw new RuntimeException("Error al actualizar la parcela "
					+ scheduleBatch.getId().trim() + "\t"
					+ scheduleBatch.getComment());

			// return null;

		}
	}

	public Schedule insertSchedule(Schedule schedule) {

		if (schedule == null || schedule.getId() == null
				|| schedule.getId().trim().length() == 0) {
			return null;
		}

		ConnectionWrapper connectionWrapper = null;

		try {

			connectionWrapper = this.dataSourceWrapper.getConnectionWrapper();

			connectionWrapper.begin();

			// ---------------------------------------------------------------------------

			String sql = "INSERT INTO cclip.schedule(ID, ERASED, YEAR, DATE_FROM, DATE_TO) VALUES (?, false, ?, ?, ?)";
			// INSERT INTO cclip.schedule(ID, ERASED, YEAR, DATE_FROM, DATE_TO)
			// VALUES ('8a495192-b334-4ac8-906b-05f3a749a2a7', '0', '2019',
			// '2019-02-01 -03:00:00', '2020-01-31 -03:00:00')

			Object[] args = new Object[4];

			args[0] = schedule.getId();

			if (schedule.getYear() != null) {
				args[1] = schedule.getYear();
			} else {
				args[1] = Integer.class;
			}

			if (schedule.getDateFrom() != null) {
				args[2] = schedule.getDateFrom();
			} else {
				args[2] = Date.class;
			}

			if (schedule.getDateTo() != null) {
				args[3] = schedule.getDateTo();
			} else {
				args[3] = Date.class;
			}

			int rows = connectionWrapper.update(sql, args);

			if (rows != 1) {

				throw new RuntimeException("Error al actualizar la parcela "
						+ schedule.getYear());
			}
			// ---------------------------------------------------------------------------

			connectionWrapper.commit();

			return schedule;

		} catch (Exception e) {

			connectionWrapper.rollBack();

			UtilComponent.logger(e);

			throw new RuntimeException("Error al actualizar la parcela "
					+ schedule.getYear());

			// return null;

		}
	}

	// public void importUnlNov(JDialogImportIceDbProgress gui, UserSystem
	// userSystem, File unidad, File inmueble, File superficie, File subcuenta)
	// throws Exception {
	// importUnl.importUnlNov(gui, userSystem, unidad, inmueble, superficie,
	// subcuenta);
	// }
	//
	// public void importUnlDbIce(JDialogImportIceDbProgress gui, UserSystem
	// userSystem, File unidad, File inmueble, File superficie, File subcuenta,
	// File datosPostales) throws Exception {
	// importUnl.importUnlDbIce(gui, userSystem, unidad, inmueble, superficie,
	// subcuenta, datosPostales);
	// }

	public UserSystem findUserSystemByUserPass(String cuil, String pass)
			throws Exception {
		try {

			ConnectionWrapper connectionWrapper = this.dataSourceWrapper
					.getConnectionWrapper();

			String sql = "SELECT * FROM cclip.f_user_system_by_cuil_pass(?, ?)";

			UserSystem userSystem = (UserSystem) connectionWrapper
					.findToJsonByExample(sql, cuil.trim(), pass.trim());

			connectionWrapper.close();

			return userSystem;

		} catch (Exception e) {

			UtilComponent.logger(e);

			throw new RuntimeException(
					"Error al consultar los datos del usuario " + cuil);
		}
	}

}
