package com.cclip.dao.schedule.census;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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

import com.cclip.Context;
import com.cclip.bo.person.UfCensusBo;
import com.cclip.dao.person.UfDao;
import com.cclip.model.geo.cadastre.Cadastre;
import com.cclip.model.geo.cadastre.CadastreActivityType;
import com.cclip.model.geo.cadastre.CadastreCensus;
import com.cclip.model.geo.cadastre.block.CadastreBlock;
import com.cclip.model.geo.cadastre.block.CadastreBlockCensus;
import com.cclip.model.person.CensusTaker;
import com.cclip.model.person.Uf;
import com.cclip.model.person.UfCensus;
import com.cclip.model.person.UserSystem;
import com.cclip.model.schedule.Schedule;
import com.cclip.model.schedule.ScheduleBatch;
import com.cclip.model.schedule.census.ScheduleCensus;
import com.cclip.model.schedule.census.ScheduleCensusItem;
import com.cclip.model.schedule.census.ScheduleCensusResult;
import com.cclip.util.UtilCadastre;

/** DAO Censo de Parcela */
public class CustomScheduleCensusDao extends ScheduleCensusDao {

	protected UfDao dao2;

	public void setDao2(UfDao dao2) {
		this.dao2 = dao2;
	}

	public ScheduleCensus insert(ConnectionWrapper connectionWrapper, ScheduleCensus updateDto) {

		updateDto.setId(UUID.randomUUID().toString());
		updateDto.setErased(false);

		List<MapperUpdateArg> mapperUpdateArgListTmp = new ArrayList<MapperUpdateArg>();

		mapperUpdateArgListTmp.add(new MapperUpdateArg("id", updateDto.getId()));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("erased", updateDto.getErased()));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastral_code", updateDto.getCadastralCode()));

		mapperUpdateArgListTmp.add(new MapperUpdateArg("insert_cadastre", updateDto.getInsertCadastre()));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("delete_cadastre", updateDto.getDeleteCadastre()));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("update_cadastre", updateDto.getUpdateCadastre()));

		// mapperUpdateArgListTmp.add(new MapperUpdateArg("date_delete",
		// updateDto.getDateDelete()));
		// mapperUpdateArgListTmp.add(new MapperUpdateArg("code_reason_low",
		// updateDto.getCodeReasonLow()));
		// mapperUpdateArgListTmp.add(new MapperUpdateArg("reason_low",
		// updateDto.getReasonLow()));

		if (updateDto.getSchedule() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_id", updateDto.getSchedule().getId()));
		}

		if (updateDto.getCensusTaker() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("census_taker_id", updateDto.getCensusTaker().getId()));
		}

		if (updateDto.getScheduleCensusResult() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_census_result_id", updateDto.getScheduleCensusResult().getId()));
		}

		// -------------------------------------------------

		MapperUpdateArg[] mapperUpdateArgList = new MapperUpdateArg[mapperUpdateArgListTmp.size()];
		mapperUpdateArgList = mapperUpdateArgListTmp.toArray(mapperUpdateArgList);

		int rowsUpdate = utilJdbc.insert(connectionWrapper, "cclip.schedule_census", mapperUpdateArgList);

		if (updateDto.getScheduleCensusItemList() != null && updateDto.getScheduleCensusItemList().length > 0) {

			for (ScheduleCensusItem scheduleCensusItem : updateDto.getScheduleCensusItemList()) {

				CadastreCensus cadastreCensus = scheduleCensusItem.getCadastreCensus();

				cadastreCensus = insert(connectionWrapper, cadastreCensus);

				scheduleCensusItem = insert(connectionWrapper, scheduleCensusItem);
			}
		}

		return updateDto;
	}

	private ScheduleCensusItem insert(ConnectionWrapper connectionWrapper, ScheduleCensusItem updateDto) {

		updateDto.setId(UUID.randomUUID().toString());
		updateDto.setErased(false);

		List<MapperUpdateArg> mapperUpdateArgListTmp = new ArrayList<MapperUpdateArg>();

		mapperUpdateArgListTmp.add(new MapperUpdateArg("id", updateDto.getId()));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("erased", updateDto.getErased()));
		if (updateDto.getCadastre() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_id", updateDto.getCadastre().getId()));
		}
		mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_census_id", updateDto.getCadastreCensus().getId()));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_census_id", updateDto.getScheduleCensus().getId()));

		// -------------------------------------------------

		MapperUpdateArg[] mapperUpdateArgList = new MapperUpdateArg[mapperUpdateArgListTmp.size()];
		mapperUpdateArgList = mapperUpdateArgListTmp.toArray(mapperUpdateArgList);

		int rowsUpdate = utilJdbc.insert(connectionWrapper, "cclip.schedule_census_item", mapperUpdateArgList);

		return updateDto;
	}

	public ScheduleCensus update(ConnectionWrapper connectionWrapper, ScheduleCensus updateDto) throws Exception {

		List<MapperUpdateArg> mapperUpdateArgListTmp = new ArrayList<MapperUpdateArg>();

//		if (updateDto.getDateDelete() != null) {
//			mapperUpdateArgListTmp.add(new MapperUpdateArg("date_delete", updateDto.getDateDelete()));
//		} else {
//			mapperUpdateArgListTmp.add(new MapperUpdateArg("date_delete", Timestamp.class));
//		}
//
//		if (updateDto.getCodeReasonLow() != null) {
//			mapperUpdateArgListTmp.add(new MapperUpdateArg("code_reason_low", updateDto.getCodeReasonLow()));
//		} else {
//			mapperUpdateArgListTmp.add(new MapperUpdateArg("code_reason_low", String.class));
//		}
//
//		if (updateDto.getCodeReasonLow() != null) {
//			mapperUpdateArgListTmp.add(new MapperUpdateArg("reason_low", updateDto.getReasonLow()));
//		} else {
//			mapperUpdateArgListTmp.add(new MapperUpdateArg("reason_low", String.class));
//		}

		if (updateDto.getErased() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("erased", updateDto.getErased()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("erased", Boolean.class));
		}

		if (updateDto.getCensused() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("censused", updateDto.getCensused()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("censused", Date.class));
		}

		if (updateDto.getRecorded() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("recorded", updateDto.getRecorded()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("recorded", Date.class));
		}

		if (updateDto.getM2ShedsGeneral() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_sheds_general", updateDto.getM2ShedsGeneral()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_sheds_general", Double.class));
		}

		if (updateDto.getM2GeneralBuilding() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_general_building", updateDto.getM2GeneralBuilding()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_general_building", Double.class));
		}

		if (updateDto.getM2BuildingsPublicEntertainment() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_buildings_public_entertainment", updateDto.getM2BuildingsPublicEntertainment()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_buildings_public_entertainment", Double.class));
		}

		if (updateDto.getM2ProgressConstruction() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_progress_construction", updateDto.getM2ProgressConstruction()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_progress_construction", Double.class));
		}

		if (updateDto.getDniCensused() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("dni_censused", updateDto.getDniCensused()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("dni_censused", String.class));
		}

		if (updateDto.getNameCensused() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("name_censused", updateDto.getNameCensused()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("name_censused", String.class));
		}

		if (updateDto.getLastNameCensused() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("last_name_censused", updateDto.getLastNameCensused()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("last_name_censused", String.class));
		}

		if (updateDto.getSignatureCensused() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("signature_censused", updateDto.getSignatureCensused()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("signature_censused", Boolean.class));
		}

		if (updateDto.getComment() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment", updateDto.getComment()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment", String.class));
		}

		if (updateDto.getSchedule() != null && updateDto.getSchedule().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_id", updateDto.getSchedule().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_id", String.class));
		}

		// if (updateDto.getCadastre() != null &&
		// updateDto.getCadastre().getId() != null) {
		// mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_id",
		// updateDto.getCadastre().getId()));
		// } else {
		// mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_id",
		// String.class));
		// }666

		if (updateDto.getCensusTaker() != null && updateDto.getCensusTaker().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("census_taker_id", updateDto.getCensusTaker().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("census_taker_id", String.class));
		}

		if (updateDto.getScheduleBatch() != null && updateDto.getScheduleBatch().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_batch_id", updateDto.getScheduleBatch().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_batch_id", String.class));
		}

		if (updateDto.getCadastreActivityType() != null && updateDto.getCadastreActivityType().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_activity_type_id", updateDto.getCadastreActivityType().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_activity_type_id", String.class));
		}

		if (updateDto.getScheduleCensusResult() != null && updateDto.getScheduleCensusResult().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_census_result_id", updateDto.getScheduleCensusResult().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_census_result_id", String.class));
		}

		// -------------------------------------------------

		// -------------------------------------------------

		MapperUpdateArg[] mapperUpdateArgList = new MapperUpdateArg[mapperUpdateArgListTmp.size()];
		mapperUpdateArgList = mapperUpdateArgListTmp.toArray(mapperUpdateArgList);

		MapperQueryArg[] mapperWhereArgList = new MapperQueryArg[1];

		MapperQueryArg mapperQueryArg = new MapperQueryArg();
		mapperQueryArg.setEqualsTo("id", updateDto.getId());
		mapperWhereArgList[0] = mapperQueryArg;

		int rowsUpdate = utilJdbc.update(connectionWrapper, "cclip.schedule_census", mapperUpdateArgList, mapperWhereArgList);

		String al = "Censado";

		if (updateDto.getCensused() != null) {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

			al += " el : " + df.format(updateDto.getCensused());
		}

		if (updateDto.getCensusTaker() != null) {
			String n = updateDto.getCensusTaker().getGivenName();
			String mn = updateDto.getCensusTaker().getMiddleName();
			String fn = updateDto.getCensusTaker().getFamilyName();
			String d = updateDto.getCensusTaker().getDni();
			String c = updateDto.getCensusTaker().getCuil();

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

		if (updateDto.getScheduleCensusItemList() != null) {

			for (ScheduleCensusItem scheduleCensusItem : updateDto.getScheduleCensusItemList()) {

				if (updateDto.getDeleteCadastre() != null && updateDto.getDeleteCadastre() == true) {

//					scheduleCensusItem.getCadastreCensus().setDateDelete(updateDto.getDateDelete());
//					scheduleCensusItem.getCadastreCensus().setCodeReasonLow(updateDto.getCodeReasonLow());
//					scheduleCensusItem.getCadastreCensus().setReasonLow(updateDto.getReasonLow());

				}

				CadastreCensus cadastreCensus = update(connectionWrapper, scheduleCensusItem.getCadastreCensus(), al, 2);
				scheduleCensusItem.setCadastreCensus(cadastreCensus);
			}

		}

		return updateDto;
	}

	public CadastreCensus update(ConnectionWrapper connectionWrapper, CadastreCensus updateDto, String auditLabel, int auditVersionCodeLabel) throws Exception {

		if (updateDto.getUf() != null) {

			UfCensusBo bo4 = Context.getBean("ufCensusBo", UfCensusBo.class);

			UfCensus ufCensus = bo4.findById(updateDto.getUf().getId());
			
			if (ufCensus != null) {
//				ufCensus = update(connectionWrapper, updateDto.getUfCensus(), auditLabel);
			} else {
//				ufCensus = insert(connectionWrapper, updateDto.getUfCensus(), auditLabel);
			}

//			updateDto.setUfCensus(ufCensus);
		}

		if (updateDto.getId() == null) {
			throw new RuntimeException("El objeto CadastreCensus tiene un id null, y por esta razon no se puede actualizar ! ");
		}

		List<MapperUpdateArg> mapperUpdateArgListTmp = new ArrayList<MapperUpdateArg>();

		if (updateDto.getDateDelete() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("date_delete", updateDto.getDateDelete()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("date_delete", Timestamp.class));
		}

		if (updateDto.getCodeReasonLow() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("code_reason_low", updateDto.getCodeReasonLow()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("code_reason_low", String.class));
		}

		if (updateDto.getCodeReasonLow() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("reason_low", updateDto.getReasonLow()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("reason_low", String.class));
		}

		if (updateDto.getCadastreType() != null && updateDto.getCadastreType().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_type_id", updateDto.getCadastreType().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_type_id", String.class));
		}

		if (updateDto.getCadastreSituation() != null && updateDto.getCadastreSituation().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_situation_id", updateDto.getCadastreSituation().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_situation_id", String.class));
		}

		if (updateDto.getWaterMeter() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("water_meter", updateDto.getWaterMeter()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("water_meter", Boolean.class));
		}

		if (updateDto.getM2() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2", updateDto.getM2()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2", Double.class));
		}

		if (updateDto.getM2Covered() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_covered", updateDto.getM2Covered()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_covered", Double.class));
		}

		if (updateDto.getM2CoveredShared() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_covered_shared", updateDto.getM2CoveredShared()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_covered_shared", Double.class));
		}

		if (updateDto.getM2CoveredExpanded() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_covered_expanded", updateDto.getM2CoveredExpanded()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_covered_expanded", Double.class));
		}

		if (updateDto.getM2Percent() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_percent", updateDto.getM2Percent()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_percent", Double.class));
		}

		if (updateDto.getInmNeighbourhood() != null && updateDto.getInmNeighbourhood().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_neighbourhood_id", updateDto.getInmNeighbourhood().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_neighbourhood_id", String.class));
		}

		if (updateDto.getInmStreet() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_street", updateDto.getInmStreet()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_street", String.class));
		}

		if (updateDto.getInmStreetNumber() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_street_number", updateDto.getInmStreetNumber()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_street_number", String.class));
		}

		if (updateDto.getInmStreetNumberEstimated() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_street_number_estimated", updateDto.getInmStreetNumberEstimated()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_street_number_estimated", Boolean.class));
		}

		if (updateDto.getInmBuildingFloor() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_building_floor", updateDto.getInmBuildingFloor()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_building_floor", String.class));
		}

		if (updateDto.getInmBuildingRoom() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_building_room", updateDto.getInmBuildingRoom()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_building_room", String.class));
		}

		if (updateDto.getInmBuilding() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_building", updateDto.getInmBuilding()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_building", String.class));
		}

		if (updateDto.getInmNeighbourhoodComment() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_neighbourhood_comment", updateDto.getInmNeighbourhoodComment()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_neighbourhood_comment", String.class));
		}

		if (updateDto.getInmCommentAddress() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_comment_address", updateDto.getInmCommentAddress()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_comment_address", String.class));
		}

		if (updateDto.getComment() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment", updateDto.getComment()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment", String.class));
		}

		if (updateDto.getUserPostalAdminAreaLevel1() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_admin_area_level1", updateDto.getUserPostalAdminAreaLevel1()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_admin_area_level1", String.class));
		}

		if (updateDto.getUserPostalLocality() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_locality", updateDto.getUserPostalLocality()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_locality", String.class));
		}

		if (updateDto.getUserPostalNeighbourhood() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_neighbourhood", updateDto.getUserPostalNeighbourhood()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_neighbourhood", String.class));
		}

		if (updateDto.getUserPostalStreet() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_street", updateDto.getUserPostalStreet()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_street", String.class));
		}

		if (updateDto.getUserPostalStreetNumber() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_street_number", updateDto.getUserPostalStreetNumber()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_street_number", String.class));
		}

		if (updateDto.getUserPostalBuildingFloor() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_building_floor", updateDto.getUserPostalBuildingFloor()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_building_floor", String.class));
		}

		if (updateDto.getUserPostalBuildingRoom() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_building_room", updateDto.getUserPostalBuildingRoom()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_building_room", String.class));
		}

		if (updateDto.getUserPostalBuilding() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_building", updateDto.getUserPostalBuilding()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_building", String.class));
		}

		if (updateDto.getUserPostalPostalCode() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_postal_code", updateDto.getUserPostalPostalCode()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_postal_code", String.class));
		}

		if (updateDto.getUserPostalCommentAddress() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_comment_address", updateDto.getUserPostalCommentAddress()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_comment_address", String.class));
		}

		if (updateDto.getUserPhone() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_phone", updateDto.getUserPhone()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_phone", String.class));
		}

		if (updateDto.getUserWaterService() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_water_service", updateDto.getUserWaterService()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_water_service", String.class));
		}

		if (updateDto.getUserWaterServiceDni() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_water_service_dni", updateDto.getUserWaterServiceDni()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_water_service_dni", String.class));
		}

		if (updateDto.getUserWaterServiceCuit() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_water_service_cuit", updateDto.getUserWaterServiceCuit()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_water_service_cuit", String.class));
		}

		if (updateDto.getUserIva() != null && updateDto.getUserIva().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_iva_id", updateDto.getUserIva().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_iva_id", String.class));
		}

		if (updateDto.getUserWaterSituation() != null && updateDto.getUserWaterSituation().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_water_situation_id", updateDto.getUserWaterSituation().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_water_situation_id", String.class));
		}

		if (updateDto.getCadastreSubDivisionType() != null && updateDto.getCadastreSubDivisionType().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_sub_division_type_id", updateDto.getCadastreSubDivisionType().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_sub_division_type_id", String.class));
		}
		
		if (updateDto.getCadastreActivityType() != null && updateDto.getCadastreActivityType().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_activity_type_id", updateDto.getCadastreActivityType().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_activity_type_id", String.class));
		}
		
		if (updateDto.getCadastreConstructiveType() != null && updateDto.getCadastreConstructiveType().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_constructive_type_id", updateDto.getCadastreConstructiveType().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_constructive_type_id", String.class));
		}

		// -----------------------------------------

		if (updateDto.getDateScanning0() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("date_scanning0", updateDto.getDateScanning0()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("date_scanning0", Date.class));
		}

		if (updateDto.getDateScanning1() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("date_scanning1", updateDto.getDateScanning1()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("date_scanning1", Date.class));
		}

		if (updateDto.getDateScanning2() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("date_scanning2", updateDto.getDateScanning2()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("date_scanning2", Date.class));
		}

		if (updateDto.getScheduleScanningResult0() != null && updateDto.getScheduleScanningResult0().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_scanning_result0_id", updateDto.getScheduleScanningResult0().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_scanning_result0_id", String.class));
		}

		if (updateDto.getScheduleScanningResult1() != null && updateDto.getScheduleScanningResult1().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_scanning_result1_id", updateDto.getScheduleScanningResult1().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_scanning_result1_id", String.class));
		}

		if (updateDto.getScheduleScanningResult2() != null && updateDto.getScheduleScanningResult2().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_scanning_result2_id", updateDto.getScheduleScanningResult2().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_scanning_result2_id", String.class));
		}

		// -----------------------------------------

		if (updateDto.getAuditVersion() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version", updateDto.getAuditVersion() + 1));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version", 1));
		}

		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version_code_label", auditVersionCodeLabel));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version_label", auditLabel));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_date_update", new Timestamp(System.currentTimeMillis())));

		UserSystem userSystem = Context.getBean("userSystem", UserSystem.class);
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_user_update", userSystem.getId()));

//		if (updateDto.getUfCensus() != null) {
//
//			mapperUpdateArgListTmp.add(new MapperUpdateArg("uf_census_id", updateDto.getUfCensus().getId()));
//		} else {
//			mapperUpdateArgListTmp.add(new MapperUpdateArg("uf_census_id", String.class));
//		}

		// -----------------------------------------

		// -----------------------------------------

		MapperUpdateArg[] mapperUpdateArgList = new MapperUpdateArg[mapperUpdateArgListTmp.size()];
		mapperUpdateArgList = mapperUpdateArgListTmp.toArray(mapperUpdateArgList);

		MapperQueryArg[] mapperWhereArgList = new MapperQueryArg[1];

		MapperQueryArg mapperQueryArg = new MapperQueryArg();
		mapperQueryArg.setEqualsTo("id", updateDto.getId());
		mapperWhereArgList[0] = mapperQueryArg;

		int rowsUpdate = utilJdbc.update(connectionWrapper, "cclip.cadastre_census", mapperUpdateArgList, mapperWhereArgList);

		// if (updateDto.getUfCensus() != null) {
		//
		// UfCensus ufCensus = update(connectionWrapper,
		// updateDto.getUfCensus(), auditLabel);
		//
		// updateDto.setUfCensus(ufCensus);
		// }

//		if (updateDto.getCadastreBlockCensusList() != null) {
//
//			for (CadastreBlockCensus cadastreBlockCensus : updateDto.getCadastreBlockCensusList()) {
//
//				if (cadastreBlockCensus.getId() == null) {
//
//					if (cadastreBlockCensus.getErased() == null) {
//						cadastreBlockCensus.setErased(false);
//					}
//
//					if (cadastreBlockCensus.getErased() == false) {
//						cadastreBlockCensus = insert(connectionWrapper, cadastreBlockCensus, auditLabel);
//					}
//
//				} else {
//					cadastreBlockCensus = update(connectionWrapper, cadastreBlockCensus, auditLabel);
//				}
//
//			}
//		}

		return updateDto;
	}

	public Cadastre update(ConnectionWrapper connectionWrapper, Cadastre updateDto, String auditLabel, int auditVersionCodeLabel) {

		List<MapperUpdateArg> mapperUpdateArgListTmp = new ArrayList<MapperUpdateArg>();

		if (updateDto.getCadastreType() != null && updateDto.getCadastreType().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_type_id", updateDto.getCadastreType().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_type_id", String.class));
		}

		if (updateDto.getCadastreSituation() != null && updateDto.getCadastreSituation().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_situation_id", updateDto.getCadastreSituation().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_situation_id", String.class));
		}

		if (updateDto.getWaterMeter() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("water_meter", updateDto.getWaterMeter()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("water_meter", Boolean.class));
		}

		if (updateDto.getM2() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2", updateDto.getM2()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2", Double.class));
		}

		if (updateDto.getM2Covered() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_covered", updateDto.getM2Covered()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_covered", Double.class));
		}

		if (updateDto.getM2CoveredShared() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_covered_shared", updateDto.getM2CoveredShared()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_covered_shared", Double.class));
		}

		if (updateDto.getM2CoveredExpanded() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_covered_expanded", updateDto.getM2CoveredExpanded()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_covered_expanded", Double.class));
		}

		if (updateDto.getM2Percent() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_percent", updateDto.getM2Percent()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_percent", Double.class));
		}

		if (updateDto.getInmNeighbourhood() != null && updateDto.getInmNeighbourhood().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_neighbourhood_id", updateDto.getInmNeighbourhood().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_neighbourhood_id", String.class));
		}

		if (updateDto.getInmStreet() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_street", updateDto.getInmStreet()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_street", String.class));
		}

		if (updateDto.getInmStreetNumber() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_street_number", updateDto.getInmStreetNumber()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_street_number", String.class));
		}

		if (updateDto.getInmStreetNumberEstimated() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_street_number_estimated", updateDto.getInmStreetNumberEstimated()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_street_number_estimated", Boolean.class));
		}

		if (updateDto.getInmBuildingFloor() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_building_floor", updateDto.getInmBuildingFloor()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_building_floor", String.class));
		}

		if (updateDto.getInmBuildingRoom() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_building_room", updateDto.getInmBuildingRoom()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_building_room", String.class));
		}

		if (updateDto.getInmBuilding() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_building", updateDto.getInmBuilding()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_building", String.class));
		}

		if (updateDto.getInmNeighbourhoodComment() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_neighbourhood_comment", updateDto.getInmNeighbourhoodComment()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_neighbourhood_comment", String.class));
		}

		if (updateDto.getComment() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment", updateDto.getComment()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment", String.class));
		}

		if (updateDto.getUserPostalAdminAreaLevel1() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_admin_area_level1", updateDto.getUserPostalAdminAreaLevel1()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_admin_area_level1", String.class));
		}

		if (updateDto.getUserPostalLocality() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_locality", updateDto.getUserPostalLocality()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_locality", String.class));
		}

		if (updateDto.getUserPostalNeighbourhood() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_neighbourhood", updateDto.getUserPostalNeighbourhood()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_neighbourhood", String.class));
		}

		if (updateDto.getUserPostalStreet() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_street", updateDto.getUserPostalStreet()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_street", String.class));
		}

		if (updateDto.getUserPostalStreetNumber() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_street_number", updateDto.getUserPostalStreetNumber()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_street_number", String.class));
		}

		if (updateDto.getUserPostalBuildingFloor() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_building_floor", updateDto.getUserPostalBuildingFloor()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_building_floor", String.class));
		}

		if (updateDto.getUserPostalBuildingRoom() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_building_room", updateDto.getUserPostalBuildingRoom()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_building_room", String.class));
		}

		if (updateDto.getUserPostalBuilding() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_building", updateDto.getUserPostalBuilding()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_building", String.class));
		}

		if (updateDto.getUserPostalPostalCode() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_postal_code", updateDto.getUserPostalPostalCode()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_postal_code", String.class));
		}

		if (updateDto.getUserPostalCommentAddress() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_comment_address", updateDto.getUserPostalCommentAddress()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_comment_address", String.class));
		}

		if (updateDto.getUserPhone() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_phone", updateDto.getUserPhone()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_phone", String.class));
		}

		if (updateDto.getUserWaterService() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_water_service", updateDto.getUserWaterService()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_water_service", String.class));
		}

		if (updateDto.getUserWaterServiceDni() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_water_service_dni", updateDto.getUserWaterServiceDni()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_water_service_dni", String.class));
		}

		if (updateDto.getUserWaterServiceCuit() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_water_service_cuit", updateDto.getUserWaterServiceCuit()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_water_service_cuit", String.class));
		}

		if (updateDto.getUserIva() != null && updateDto.getUserIva().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_iva_id", updateDto.getUserIva().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_iva_id", String.class));
		}

		if (updateDto.getUserWaterSituation() != null && updateDto.getUserWaterSituation().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_water_situation_id", updateDto.getUserWaterSituation().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_water_situation_id", String.class));
		}

		// -----------------------------------------

		if (updateDto.getDateScanning0() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("date_scanning0", updateDto.getDateScanning0()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("date_scanning0", Date.class));
		}

		if (updateDto.getDateScanning1() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("date_scanning1", updateDto.getDateScanning1()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("date_scanning1", Date.class));
		}

		if (updateDto.getDateScanning2() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("date_scanning2", updateDto.getDateScanning2()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("date_scanning2", Date.class));
		}

		if (updateDto.getScheduleScanningResult0() != null && updateDto.getScheduleScanningResult0().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_scanning_result0_id", updateDto.getScheduleScanningResult0().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_scanning_result0_id", String.class));
		}

		if (updateDto.getScheduleScanningResult1() != null && updateDto.getScheduleScanningResult1().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_scanning_result1_id", updateDto.getScheduleScanningResult1().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_scanning_result1_id", String.class));
		}

		if (updateDto.getScheduleScanningResult2() != null && updateDto.getScheduleScanningResult2().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_scanning_result2_id", updateDto.getScheduleScanningResult2().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_scanning_result2_id", String.class));
		}

		// -----------------------------------------

		if (updateDto.getAuditVersion() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version", updateDto.getAuditVersion() + 1));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version", 1));
		}

		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version_code_label", auditVersionCodeLabel));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version_label", auditLabel));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_date_update", new Timestamp(System.currentTimeMillis())));

		UserSystem userSystem = Context.getBean("userSystem", UserSystem.class);
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_user_update", userSystem.getId()));

		// -----------------------------------------

		// -----------------------------------------

		MapperUpdateArg[] mapperUpdateArgList = new MapperUpdateArg[mapperUpdateArgListTmp.size()];
		mapperUpdateArgList = mapperUpdateArgListTmp.toArray(mapperUpdateArgList);

		MapperQueryArg[] mapperWhereArgList = new MapperQueryArg[1];

		MapperQueryArg mapperQueryArg = new MapperQueryArg();
		mapperQueryArg.setEqualsTo("id", updateDto.getId());
		mapperWhereArgList[0] = mapperQueryArg;

		int rowsUpdate = utilJdbc.update(connectionWrapper, "cclip.cadastre", mapperUpdateArgList, mapperWhereArgList);

		if (updateDto.getUf() != null) {

			Uf uf = update(connectionWrapper, updateDto.getUf(), auditLabel);

			updateDto.setUf(uf);
		}

		if (updateDto.getCadastreBlockList() != null) {

			for (CadastreBlock cadastreBlock : updateDto.getCadastreBlockList()) {

				if (cadastreBlock.getId() == null) {

					if (cadastreBlock.getErased() == null) {
						cadastreBlock.setErased(false);
					}

					if (cadastreBlock.getErased() == false) {
						cadastreBlock = insert(connectionWrapper, cadastreBlock, auditLabel);
					}
				} else {
					cadastreBlock = update(connectionWrapper, cadastreBlock, auditLabel);
				}

			}
		}

		return updateDto;
	}

	public Cadastre updateForScanning(ConnectionWrapper connectionWrapper, Cadastre updateDto, String auditLabel) {

		List<MapperUpdateArg> mapperUpdateArgListTmp = new ArrayList<MapperUpdateArg>();

		// -----------------------------------------

		if (updateDto.getDateScanning0() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("date_scanning0", updateDto.getDateScanning0()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("date_scanning0", Date.class));
		}

		if (updateDto.getDateScanning1() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("date_scanning1", updateDto.getDateScanning1()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("date_scanning1", Date.class));
		}

		if (updateDto.getDateScanning2() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("date_scanning2", updateDto.getDateScanning2()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("date_scanning2", Date.class));
		}

		if (updateDto.getScheduleScanningResult0() != null && updateDto.getScheduleScanningResult0().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_scanning_result0_id", updateDto.getScheduleScanningResult0().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_scanning_result0_id", String.class));
		}

		if (updateDto.getScheduleScanningResult1() != null && updateDto.getScheduleScanningResult1().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_scanning_result1_id", updateDto.getScheduleScanningResult1().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_scanning_result1_id", String.class));
		}

		if (updateDto.getScheduleScanningResult2() != null && updateDto.getScheduleScanningResult2().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_scanning_result2_id", updateDto.getScheduleScanningResult2().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("schedule_scanning_result2_id", String.class));
		}

		// -----------------------------------------

		if (updateDto.getAuditVersion() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version", updateDto.getAuditVersion() + 1));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version", 1));
		}

		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version_code_label", 4));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version_label", auditLabel));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_date_update", new Timestamp(System.currentTimeMillis())));

		UserSystem userSystem = Context.getBean("userSystem", UserSystem.class);
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_user_update", userSystem.getId()));

		// -----------------------------------------

		// -----------------------------------------

		MapperUpdateArg[] mapperUpdateArgList = new MapperUpdateArg[mapperUpdateArgListTmp.size()];
		mapperUpdateArgList = mapperUpdateArgListTmp.toArray(mapperUpdateArgList);

		MapperQueryArg[] mapperWhereArgList = new MapperQueryArg[1];

		MapperQueryArg mapperQueryArg = new MapperQueryArg();
		mapperQueryArg.setEqualsTo("id", updateDto.getId());
		mapperWhereArgList[0] = mapperQueryArg;

		int rowsUpdate = utilJdbc.update(connectionWrapper, "cclip.cadastre", mapperUpdateArgList, mapperWhereArgList);

		return updateDto;
	}

	private CadastreCensus insert(ConnectionWrapper connectionWrapper, CadastreCensus updateDto) {

		UserSystem userSystem = Context.getBean("userSystem", UserSystem.class);

		String auditLabel = "Asignado a planilla de censo";

		if (userSystem != null) {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

			auditLabel += " el : " + df.format(updateDto.getAuditDateCreate());
		}

		if (userSystem != null) {
			String n = userSystem.getGivenName();
			String mn = userSystem.getMiddleName();
			String fn = userSystem.getFamilyName();
			String d = userSystem.getDni();
			String c = userSystem.getCuil();

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

			auditLabel += " por " + fn + n + mn + d + c;
		}

//		if (updateDto.getUfCensus() != null) {
//
//			UfCensus ufCensus = insert(connectionWrapper, updateDto.getUfCensus(), auditLabel);
//
//			updateDto.setUfCensus(ufCensus);
//		}

		// ----------------------------------------------------------------------------------------------------

		updateDto.setId(UUID.randomUUID().toString());
		updateDto.setErased(false);

		List<MapperUpdateArg> mapperUpdateArgListTmp = new ArrayList<MapperUpdateArg>();

		mapperUpdateArgListTmp.add(new MapperUpdateArg("id", updateDto.getId()));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("erased", updateDto.getErased()));

		// ----------------------------------------------------------------------------------------------------

//		if (updateDto.getUfCensus() != null) {
//			mapperUpdateArgListTmp.add(new MapperUpdateArg("uf_census_id", updateDto.getUfCensus().getId()));
//		} else {
//			mapperUpdateArgListTmp.add(new MapperUpdateArg("uf_census_id", String.class));
//		}

		if (updateDto.getDateCreate() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("date_create", updateDto.getDateCreate()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("date_create", Date.class));
		}

		if (updateDto.getDateDelete() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("date_delete", updateDto.getDateDelete()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("date_delete", Date.class));
		}

		if (updateDto.getCadastralCode() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastral_code", updateDto.getCadastralCode()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastral_code", String.class));
		}

		if (updateDto.getM2() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2", updateDto.getM2()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2", Double.class));
		}

		if (updateDto.getM2Covered() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_covered", updateDto.getM2Covered()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_covered", Double.class));
		}

		if (updateDto.getCtaCli() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cta_cli", updateDto.getCtaCli()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cta_cli", String.class));
		}

		if (updateDto.getSubCtaCli() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("sub_cta_cli", updateDto.getSubCtaCli()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("sub_cta_cli", String.class));
		}

		if (updateDto.getDv() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("dv", updateDto.getDv()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("dv", String.class));
		}

		if (updateDto.getUserWaterService() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_water_service", updateDto.getUserWaterService()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_water_service", String.class));
		}

		if (updateDto.getUserWaterServiceDni() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_water_service_dni", updateDto.getUserWaterServiceDni()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_water_service_dni", String.class));
		}

		if (updateDto.getUserWaterServiceCuit() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_water_service_cuit", updateDto.getUserWaterServiceCuit()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_water_service_cuit", String.class));
		}

		if (updateDto.getWaterMeter() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("water_meter", updateDto.getWaterMeter()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("water_meter", Boolean.class));
		}

		if (updateDto.getComment() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment", updateDto.getComment()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment", String.class));
		}

		if (updateDto.getInmLocality() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_locality", updateDto.getInmLocality()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_locality", String.class));
		}

		if (updateDto.getInmNeighbourhoodComment() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_neighbourhood_comment", updateDto.getInmNeighbourhoodComment()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_neighbourhood_comment", String.class));
		}

		if (updateDto.getInmStreet() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_street", updateDto.getInmStreet()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_street", String.class));
		}

		if (updateDto.getInmStreetNumber() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_street_number", updateDto.getInmStreetNumber()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_street_number", String.class));
		}

		if (updateDto.getInmStreetNumberEstimated() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_street_number_estimated", updateDto.getInmStreetNumberEstimated()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_street_number_estimated", Boolean.class));
		}

		if (updateDto.getInmBuildingFloor() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_building_floor", updateDto.getInmBuildingFloor()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_building_floor", String.class));
		}

		if (updateDto.getInmBuildingRoom() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_building_room", updateDto.getInmBuildingRoom()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_building_room", String.class));
		}

		if (updateDto.getInmBuilding() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_building", updateDto.getInmBuilding()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_building", String.class));
		}

		if (updateDto.getInmPostalCode() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_postal_code", updateDto.getInmPostalCode()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_postal_code", String.class));
		}

		if (updateDto.getInmCommentAddress() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_comment_address", updateDto.getInmCommentAddress()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_comment_address", String.class));
		}

		if (updateDto.getInmLat() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_lat", updateDto.getInmLat()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_lat", Double.class));
		}

		if (updateDto.getInmLng() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_lng", updateDto.getInmLng()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_lng", Double.class));
		}

		if (updateDto.getUserPostalCountryCode() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_country_code", updateDto.getUserPostalCountryCode()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_country_code", String.class));
		}

		if (updateDto.getUserPostalCountry() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_country", updateDto.getUserPostalCountry()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_country", String.class));
		}

		if (updateDto.getUserPostalAdminAreaLevel1Code() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_admin_area_level1_code", updateDto.getUserPostalAdminAreaLevel1Code()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_admin_area_level1_code", String.class));
		}

		if (updateDto.getUserPostalAdminAreaLevel1() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_admin_area_level1", updateDto.getUserPostalAdminAreaLevel1()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_admin_area_level1", String.class));
		}

		if (updateDto.getUserPostalAdminAreaLevel2() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_admin_area_level2", updateDto.getUserPostalAdminAreaLevel2()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_admin_area_level2", String.class));
		}

		if (updateDto.getUserPostalLocality() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_locality", updateDto.getUserPostalLocality()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_locality", String.class));
		}

		if (updateDto.getUserPostalNeighbourhood() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_neighbourhood", updateDto.getUserPostalNeighbourhood()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_neighbourhood", String.class));
		}

		if (updateDto.getUserPostalStreet() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_street", updateDto.getUserPostalStreet()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_street", String.class));
		}

		if (updateDto.getUserPostalStreetNumber() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_street_number", updateDto.getUserPostalStreetNumber()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_street_number", String.class));
		}

		if (updateDto.getUserPostalBuildingFloor() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_building_floor", updateDto.getUserPostalBuildingFloor()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_building_floor", String.class));
		}

		if (updateDto.getUserPostalBuildingRoom() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_building_room", updateDto.getUserPostalBuildingRoom()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_building_room", String.class));
		}

		if (updateDto.getUserPostalBuilding() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_building", updateDto.getUserPostalBuilding()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_building", String.class));
		}

		if (updateDto.getUserPostalPostalCode() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_postal_code", updateDto.getUserPostalPostalCode()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_postal_code", String.class));
		}

		if (updateDto.getUserPostalCommentAddress() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_comment_address", updateDto.getUserPostalCommentAddress()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_comment_address", String.class));
		}

		if (updateDto.getUserPostalLat() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_lat", updateDto.getUserPostalLat()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_lat", Double.class));
		}

		if (updateDto.getUserPostalLng() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_lng", updateDto.getUserPostalLng()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_lng", Double.class));
		}

		if (updateDto.getUserPhone() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_phone", updateDto.getUserPhone()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_phone", String.class));
		}

		if (updateDto.getCodeReasonLow() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("code_reason_low", updateDto.getCodeReasonLow()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("code_reason_low", String.class));
		}

		if (updateDto.getReasonLow() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("reason_low", updateDto.getReasonLow()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("reason_low", String.class));
		}

		if (updateDto.getCantPh() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cant_ph", updateDto.getCantPh()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cant_ph", Integer.class));
		}

		// -------------------------------------------------------

		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_date_create", updateDto.getAuditDateCreate()));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_user_create", userSystem.getId()));

		if (updateDto.getAuditVersion() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version", updateDto.getAuditVersion() + 1));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version", 1));
		}
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version_code_label", "2"));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version_label", auditLabel));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_date_update", new Timestamp(System.currentTimeMillis())));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_user_update", userSystem.getId()));

		if (updateDto.getFact() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("fact", updateDto.getFact()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("fact", Boolean.class));
		}

		if (updateDto.getM2CoveredShared() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_covered_shared", updateDto.getM2CoveredShared()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_covered_shared", Double.class));
		}

		if (updateDto.getM2CoveredExpanded() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_covered_expanded", updateDto.getM2CoveredExpanded()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_covered_expanded", Double.class));
		}

		if (updateDto.getM2Percent() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_percent", updateDto.getM2Percent()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_percent", Double.class));
		}

		if (updateDto.getYearBuilding() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("year_building", updateDto.getYearBuilding()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("year_building", Double.class));
		}

		if (updateDto.getCityArea() != null && updateDto.getCityArea().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("city_area_id", updateDto.getCityArea().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("city_area_id", String.class));
		}

		if (updateDto.getUserIva() != null && updateDto.getUserIva().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_iva_id", updateDto.getUserIva().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_iva_id", String.class));
		}

		if (updateDto.getUserWaterSituation() != null && updateDto.getUserWaterSituation().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_water_situation_id", updateDto.getUserWaterSituation().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_water_situation_id", String.class));
		}

		if (updateDto.getCadastreType() != null && updateDto.getCadastreType().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_type_id", updateDto.getCadastreType().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_type_id", String.class));
		}

		if (updateDto.getCadastreSituation() != null && updateDto.getCadastreSituation().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_situation_id", updateDto.getCadastreSituation().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_situation_id", String.class));
		}

		if (updateDto.getInmNeighbourhood() != null && updateDto.getInmNeighbourhood().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_neighbourhood_id", updateDto.getInmNeighbourhood().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_neighbourhood_id", String.class));
		}

		if (updateDto.getCadastreSubDivisionType() != null && updateDto.getCadastreSubDivisionType().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_sub_division_type_id", updateDto.getCadastreSubDivisionType().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_sub_division_type_id", String.class));
		}

		if (updateDto.getCadastreActivityType() != null && updateDto.getCadastreActivityType().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_activity_type_id", updateDto.getCadastreActivityType().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_activity_type_id", String.class));
		}

		// ----------------------------------------------------------------------------------------------------

		MapperUpdateArg[] mapperUpdateArgList = new MapperUpdateArg[mapperUpdateArgListTmp.size()];
		mapperUpdateArgList = mapperUpdateArgListTmp.toArray(mapperUpdateArgList);

		int rowsUpdate = utilJdbc.insert(connectionWrapper, "cclip.cadastre_census", mapperUpdateArgList);

//		if (updateDto.getCadastreBlockCensusList() != null) {
//
//			for (CadastreBlockCensus cadastreBlockCensus : updateDto.getCadastreBlockCensusList()) {
//
//				cadastreBlockCensus.setErased(false);
//				cadastreBlockCensus = insert(connectionWrapper, cadastreBlockCensus, auditLabel);
//			}
//		}

		return updateDto;
	}

	public Cadastre insert(ConnectionWrapper connectionWrapper, Cadastre updateDto) {

		UserSystem userSystem = Context.getBean("userSystem", UserSystem.class);

		String auditLabel = "Importado";

		if (userSystem != null) {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

			auditLabel += " el : " + df.format(updateDto.getAuditDateCreate());
		}

		if (userSystem != null) {
			String n = userSystem.getGivenName();
			String mn = userSystem.getMiddleName();
			String fn = userSystem.getFamilyName();
			String d = userSystem.getDni();
			String c = userSystem.getCuil();

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

			auditLabel += " por " + fn + n + mn + d + c;
		}

		if (updateDto.getUf() != null) {

			Uf uf = dao2.findById(connectionWrapper, updateDto.getUf().getId());

			if (uf != null) {
				uf = update(connectionWrapper, updateDto.getUf(), auditLabel);
			} else {
				uf = insert(connectionWrapper, updateDto.getUf(), auditLabel);
			}

			updateDto.setUf(uf);
		}

		// ----------------------------------------------------------------------------------------------------

		updateDto.setId(UUID.randomUUID().toString());
		updateDto.setErased(false);

		List<MapperUpdateArg> mapperUpdateArgListTmp = new ArrayList<MapperUpdateArg>();

		mapperUpdateArgListTmp.add(new MapperUpdateArg("id", updateDto.getId()));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("erased", updateDto.getErased()));

		// ----------------------------------------------------------------------------------------------------

		if (updateDto.getDateCreate() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("date_create", updateDto.getDateCreate()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("date_create", Date.class));
		}

		if (updateDto.getDateDelete() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("date_delete", updateDto.getDateDelete()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("date_delete", Date.class));
		}

		if (updateDto.getCadastralCode() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastral_code", updateDto.getCadastralCode()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastral_code", String.class));
		}

		if (updateDto.getM2() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2", updateDto.getM2()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2", Double.class));
		}

		if (updateDto.getM2Covered() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_covered", updateDto.getM2Covered()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_covered", Double.class));
		}

		if (updateDto.getCtaCli() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cta_cli", updateDto.getCtaCli()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cta_cli", String.class));
		}

		if (updateDto.getSubCtaCli() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("sub_cta_cli", updateDto.getSubCtaCli()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("sub_cta_cli", String.class));
		}

		if (updateDto.getDv() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("dv", updateDto.getDv()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("dv", String.class));
		}

		if (updateDto.getUserWaterService() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_water_service", updateDto.getUserWaterService()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_water_service", String.class));
		}

		if (updateDto.getUserWaterServiceDni() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_water_service_dni", updateDto.getUserWaterServiceDni()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_water_service_dni", String.class));
		}

		if (updateDto.getUserWaterServiceCuit() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_water_service_cuit", updateDto.getUserWaterServiceCuit()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_water_service_cuit", String.class));
		}

		if (updateDto.getWaterMeter() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("water_meter", updateDto.getWaterMeter()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("water_meter", Boolean.class));
		}

		if (updateDto.getComment() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment", updateDto.getComment()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment", String.class));
		}

		if (updateDto.getInmLocality() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_locality", updateDto.getInmLocality()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_locality", String.class));
		}

		if (updateDto.getInmNeighbourhoodComment() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_neighbourhood_comment", updateDto.getInmNeighbourhoodComment()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_neighbourhood_comment", String.class));
		}

		if (updateDto.getInmStreet() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_street", updateDto.getInmStreet()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_street", String.class));
		}

		if (updateDto.getInmStreetNumber() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_street_number", updateDto.getInmStreetNumber()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_street_number", String.class));
		}

		if (updateDto.getInmStreetNumberEstimated() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_street_number_estimated", updateDto.getInmStreetNumberEstimated()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_street_number_estimated", Boolean.class));
		}

		if (updateDto.getInmBuildingFloor() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_building_floor", updateDto.getInmBuildingFloor()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_building_floor", String.class));
		}

		if (updateDto.getInmBuildingRoom() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_building_room", updateDto.getInmBuildingRoom()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_building_room", String.class));
		}

		if (updateDto.getInmBuilding() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_building", updateDto.getInmBuilding()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_building", String.class));
		}

		if (updateDto.getInmPostalCode() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_postal_code", updateDto.getInmPostalCode()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_postal_code", String.class));
		}

		if (updateDto.getInmCommentAddress() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_comment_address", updateDto.getInmCommentAddress()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_comment_address", String.class));
		}

		if (updateDto.getInmLat() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_lat", updateDto.getInmLat()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_lat", Double.class));
		}

		if (updateDto.getInmLng() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_lng", updateDto.getInmLng()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_lng", Double.class));
		}

		if (updateDto.getUserPostalCountryCode() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_country_code", updateDto.getUserPostalCountryCode()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_country_code", String.class));
		}

		if (updateDto.getUserPostalCountry() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_country", updateDto.getUserPostalCountry()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_country", String.class));
		}

		if (updateDto.getUserPostalAdminAreaLevel1Code() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_admin_area_level1_code", updateDto.getUserPostalAdminAreaLevel1Code()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_admin_area_level1_code", String.class));
		}

		if (updateDto.getUserPostalAdminAreaLevel1() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_admin_area_level1", updateDto.getUserPostalAdminAreaLevel1()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_admin_area_level1", String.class));
		}

		if (updateDto.getUserPostalAdminAreaLevel2() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_admin_area_level2", updateDto.getUserPostalAdminAreaLevel2()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_admin_area_level2", String.class));
		}

		if (updateDto.getUserPostalLocality() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_locality", updateDto.getUserPostalLocality()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_locality", String.class));
		}

		if (updateDto.getUserPostalNeighbourhood() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_neighbourhood", updateDto.getUserPostalNeighbourhood()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_neighbourhood", String.class));
		}

		if (updateDto.getUserPostalStreet() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_street", updateDto.getUserPostalStreet()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_street", String.class));
		}

		if (updateDto.getUserPostalStreetNumber() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_street_number", updateDto.getUserPostalStreetNumber()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_street_number", String.class));
		}

		if (updateDto.getUserPostalBuildingFloor() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_building_floor", updateDto.getUserPostalBuildingFloor()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_building_floor", String.class));
		}

		if (updateDto.getUserPostalBuildingRoom() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_building_room", updateDto.getUserPostalBuildingRoom()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_building_room", String.class));
		}

		if (updateDto.getUserPostalBuilding() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_building", updateDto.getUserPostalBuilding()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_building", String.class));
		}

		if (updateDto.getUserPostalPostalCode() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_postal_code", updateDto.getUserPostalPostalCode()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_postal_code", String.class));
		}

		if (updateDto.getUserPostalCommentAddress() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_comment_address", updateDto.getUserPostalCommentAddress()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_comment_address", String.class));
		}

		if (updateDto.getUserPostalLat() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_lat", updateDto.getUserPostalLat()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_lat", Double.class));
		}

		if (updateDto.getUserPostalLng() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_lng", updateDto.getUserPostalLng()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_postal_lng", Double.class));
		}

		if (updateDto.getUserPhone() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_phone", updateDto.getUserPhone()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_phone", String.class));
		}

		if (updateDto.getCodeReasonLow() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("code_reason_low", updateDto.getCodeReasonLow()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("code_reason_low", String.class));
		}

		if (updateDto.getReasonLow() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("reason_low", updateDto.getReasonLow()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("reason_low", String.class));
		}

		if (updateDto.getCantPh() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cant_ph", updateDto.getCantPh()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cant_ph", Integer.class));
		}

		// -------------------------------------------------------

		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_date_create", updateDto.getAuditDateCreate()));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_user_create", userSystem.getId()));

		if (updateDto.getAuditVersion() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version", updateDto.getAuditVersion() + 1));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version", 1));
		}
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version_code_label", "3"));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version_label", auditLabel));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_date_update", new Timestamp(System.currentTimeMillis())));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_user_update", userSystem.getId()));

		if (updateDto.getFact() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("fact", updateDto.getFact()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("fact", Boolean.class));
		}

		if (updateDto.getM2CoveredShared() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_covered_shared", updateDto.getM2CoveredShared()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_covered_shared", Double.class));
		}

		if (updateDto.getM2CoveredExpanded() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_covered_expanded", updateDto.getM2CoveredExpanded()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_covered_expanded", Double.class));
		}

		if (updateDto.getM2Percent() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_percent", updateDto.getM2Percent()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_percent", Double.class));
		}

		if (updateDto.getCityArea() != null && updateDto.getCityArea().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("city_area_id", updateDto.getCityArea().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("city_area_id", String.class));
		}

		if (updateDto.getUserIva() != null && updateDto.getUserIva().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_iva_id", updateDto.getUserIva().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_iva_id", String.class));
		}

		if (updateDto.getUserWaterSituation() != null && updateDto.getUserWaterSituation().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_water_situation_id", updateDto.getUserWaterSituation().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("user_water_situation_id", String.class));
		}

		if (updateDto.getCadastreType() != null && updateDto.getCadastreType().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_type_id", updateDto.getCadastreType().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_type_id", String.class));
		}

		if (updateDto.getCadastreSituation() != null && updateDto.getCadastreSituation().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_situation_id", updateDto.getCadastreSituation().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_situation_id", String.class));
		}

		if (updateDto.getInmNeighbourhood() != null && updateDto.getInmNeighbourhood().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_neighbourhood_id", updateDto.getInmNeighbourhood().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("inm_neighbourhood_id", String.class));
		}

		if (updateDto.getCadastreSubDivisionType() != null && updateDto.getCadastreSubDivisionType().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_sub_division_type_id", updateDto.getCadastreSubDivisionType().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_sub_division_type_id", String.class));
		}

		// ----------------------------------------------------------------------------------------------------

		MapperUpdateArg[] mapperUpdateArgList = new MapperUpdateArg[mapperUpdateArgListTmp.size()];
		mapperUpdateArgList = mapperUpdateArgListTmp.toArray(mapperUpdateArgList);

		int rowsUpdate = utilJdbc.insert(connectionWrapper, "cclip.cadastre", mapperUpdateArgList);

		if (updateDto.getCadastreBlockList() != null) {

			for (CadastreBlock cadastreBlock : updateDto.getCadastreBlockList()) {

				if (cadastreBlock.getId() == null) {

					if (cadastreBlock.getErased() == null) {
						cadastreBlock.setErased(false);
					}

					if (cadastreBlock.getErased() == false) {
						cadastreBlock = insert(connectionWrapper, cadastreBlock, auditLabel);
					}
				} else {
					cadastreBlock = update(connectionWrapper, cadastreBlock, auditLabel);
				}
			}
		}

		return updateDto;
	}

	private CadastreBlockCensus update(ConnectionWrapper connectionWrapper, CadastreBlockCensus updateDto, String auditLabel) {

		List<MapperUpdateArg> mapperUpdateArgListTmp = new ArrayList<MapperUpdateArg>();

		if (updateDto.getErased() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("erased", updateDto.getErased()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("erased", false));
		}

		if (updateDto.getYearBuilding() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("year_building", updateDto.getYearBuilding()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("year_building", Integer.class));
		}

		if (updateDto.getMonthBuilding() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("month_building", updateDto.getMonthBuilding()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("month_building", Integer.class));
		}

		if (updateDto.getM2Covered() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_covered", updateDto.getM2Covered()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_covered", Double.class));
		}

		if (updateDto.getDemolition() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("demolition", updateDto.getDemolition()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("demolition", false));
		}

		if (updateDto.getComment() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment", updateDto.getComment()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment", String.class));
		}

		if (updateDto.getFacade() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("facade", updateDto.getFacade()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("facade", String.class));
		}

		if (updateDto.getRoofStructure() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("roof_structure", updateDto.getRoofStructure()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("roof_structure", String.class));
		}

		if (updateDto.getHomes() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("homes", updateDto.getHomes()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("homes", String.class));
		}

		if (updateDto.getInteriorWalls() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("interior_walls", updateDto.getInteriorWalls()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("interior_walls", String.class));
		}

		if (updateDto.getCeiling() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("ceiling", updateDto.getCeiling()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("ceiling", String.class));
		}

		if (updateDto.getKitchen() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("kitchen", updateDto.getKitchen()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("kitchen", String.class));
		}

		if (updateDto.getToilets() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("toilets", updateDto.getToilets()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("toilets", String.class));
		}

		if (updateDto.getEntranceHall() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("entrance_hall", updateDto.getEntranceHall()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("entrance_hall", String.class));
		}

		if (updateDto.getFacilities() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("facilities", updateDto.getFacilities()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("facilities", String.class));
		}

		if (updateDto.getCarpentry() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("carpentry", updateDto.getCarpentry()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("carpentry", String.class));
		}

		if (updateDto.getCoverStructure() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cover_structure", updateDto.getCoverStructure()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cover_structure", String.class));
		}

		if (updateDto.getOrnamentation() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("ornamentation", updateDto.getOrnamentation()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("ornamentation", String.class));
		}

		if (updateDto.getStainedGlassLighting() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("stained_glass_lighting", updateDto.getStainedGlassLighting()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("stained_glass_lighting", String.class));
		}

		if (updateDto.getBuffetDining() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("buffet_dining", updateDto.getBuffetDining()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("buffet_dining", String.class));
		}

		if (updateDto.getPoints() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("points", updateDto.getPoints()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("points", Integer.class));
		}

		if (updateDto.getApc() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("apc", updateDto.getApc()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("apc", false));
		}

		if (updateDto.getApcDesc() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("apc_desc", updateDto.getApcDesc()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("apc_desc", String.class));
		}

		if (updateDto.getRate() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("rate", updateDto.getRate()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("rate", Integer.class));
		}

		if (updateDto.getCadastreActivityType() != null && updateDto.getCadastreActivityType().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_activity_type_id", updateDto.getCadastreActivityType().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_activity_type_id", String.class));
		}

//		if (updateDto.getCadastreCensus() != null && updateDto.getCadastreCensus().getId() != null) {
//			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_census_id", updateDto.getCadastreCensus().getId()));
//		} else {
//			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_census_id", String.class));
//		}

		if (updateDto.getCadastreConstructiveType() != null && updateDto.getCadastreConstructiveType().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_constructive_type_id", updateDto.getCadastreConstructiveType().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_constructive_type_id", String.class));
		}

		if (updateDto.getCadastreDestinationType() != null && updateDto.getCadastreDestinationType().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_destination_type_id", updateDto.getCadastreDestinationType().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_destination_type_id", String.class));
		}

		// -----------------------------------------

		if (updateDto.getAuditVersion() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version", updateDto.getAuditVersion() + 1));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version", 1));
		}

		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version_code_label", "2"));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version_label", auditLabel));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_date_update", new Timestamp(System.currentTimeMillis())));

		UserSystem userSystem = Context.getBean("userSystem", UserSystem.class);
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_user_update", userSystem.getId()));

		// -----------------------------------------

		// -----------------------------------------

		MapperUpdateArg[] mapperUpdateArgList = new MapperUpdateArg[mapperUpdateArgListTmp.size()];
		mapperUpdateArgList = mapperUpdateArgListTmp.toArray(mapperUpdateArgList);

		MapperQueryArg[] mapperWhereArgList = new MapperQueryArg[1];

		MapperQueryArg mapperQueryArg = new MapperQueryArg();
		mapperQueryArg.setEqualsTo("id", updateDto.getId());
		mapperWhereArgList[0] = mapperQueryArg;

		int rowsUpdate = utilJdbc.update(connectionWrapper, "cclip.cadastre_block_census", mapperUpdateArgList, mapperWhereArgList);

		return updateDto;
	}

	private CadastreBlock update(ConnectionWrapper connectionWrapper, CadastreBlock updateDto, String auditLabel) {

		List<MapperUpdateArg> mapperUpdateArgListTmp = new ArrayList<MapperUpdateArg>();

		if (updateDto.getErased() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("erased", updateDto.getErased()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("erased", false));
		}

		if (updateDto.getYearBuilding() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("year_building", updateDto.getYearBuilding()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("year_building", Integer.class));
		}

		if (updateDto.getMonthBuilding() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("month_building", updateDto.getMonthBuilding()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("month_building", Integer.class));
		}

		if (updateDto.getM2Covered() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_covered", updateDto.getM2Covered()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_covered", Double.class));
		}

		if (updateDto.getDemolition() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("demolition", updateDto.getDemolition()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("demolition", false));
		}

		if (updateDto.getComment() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment", updateDto.getComment()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment", String.class));
		}

		if (updateDto.getFacade() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("facade", updateDto.getFacade()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("facade", String.class));
		}

		if (updateDto.getRoofStructure() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("roof_structure", updateDto.getRoofStructure()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("roof_structure", String.class));
		}

		if (updateDto.getHomes() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("homes", updateDto.getHomes()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("homes", String.class));
		}

		if (updateDto.getInteriorWalls() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("interior_walls", updateDto.getInteriorWalls()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("interior_walls", String.class));
		}

		if (updateDto.getCeiling() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("ceiling", updateDto.getCeiling()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("ceiling", String.class));
		}

		if (updateDto.getKitchen() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("kitchen", updateDto.getKitchen()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("kitchen", String.class));
		}

		if (updateDto.getToilets() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("toilets", updateDto.getToilets()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("toilets", String.class));
		}

		if (updateDto.getEntranceHall() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("entrance_hall", updateDto.getEntranceHall()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("entrance_hall", String.class));
		}

		if (updateDto.getFacilities() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("facilities", updateDto.getFacilities()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("facilities", String.class));
		}

		if (updateDto.getCarpentry() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("carpentry", updateDto.getCarpentry()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("carpentry", String.class));
		}

		if (updateDto.getCoverStructure() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cover_structure", updateDto.getCoverStructure()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cover_structure", String.class));
		}

		if (updateDto.getOrnamentation() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("ornamentation", updateDto.getOrnamentation()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("ornamentation", String.class));
		}

		if (updateDto.getStainedGlassLighting() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("stained_glass_lighting", updateDto.getStainedGlassLighting()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("stained_glass_lighting", String.class));
		}

		if (updateDto.getBuffetDining() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("buffet_dining", updateDto.getBuffetDining()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("buffet_dining", String.class));
		}

		if (updateDto.getPoints() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("points", updateDto.getPoints()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("points", Integer.class));
		}

		if (updateDto.getApc() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("apc", updateDto.getApc()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("apc", false));
		}

		if (updateDto.getApcDesc() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("apc_desc", updateDto.getApcDesc()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("apc_desc", String.class));
		}

		if (updateDto.getRate() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("rate", updateDto.getRate()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("rate", Integer.class));
		}

		if (updateDto.getCadastreActivityType() != null && updateDto.getCadastreActivityType().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_activity_type_id", updateDto.getCadastreActivityType().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_activity_type_id", String.class));
		}

		if (updateDto.getCadastre() != null && updateDto.getCadastre().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_id", updateDto.getCadastre().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_id", String.class));
		}

		if (updateDto.getCadastreConstructiveType() != null && updateDto.getCadastreConstructiveType().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_constructive_type_id", updateDto.getCadastreConstructiveType().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_constructive_type_id", String.class));
		}

		if (updateDto.getCadastreDestinationType() != null && updateDto.getCadastreDestinationType().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_destination_type_id", updateDto.getCadastreDestinationType().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_destination_type_id", String.class));
		}

		// -----------------------------------------

		if (updateDto.getAuditVersion() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version", updateDto.getAuditVersion() + 1));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version", 1));
		}

		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version_code_label", "2"));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version_label", auditLabel));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_date_update", new Timestamp(System.currentTimeMillis())));

		UserSystem userSystem = Context.getBean("userSystem", UserSystem.class);
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_user_update", userSystem.getId()));

		// -----------------------------------------

		// -----------------------------------------

		MapperUpdateArg[] mapperUpdateArgList = new MapperUpdateArg[mapperUpdateArgListTmp.size()];
		mapperUpdateArgList = mapperUpdateArgListTmp.toArray(mapperUpdateArgList);

		MapperQueryArg[] mapperWhereArgList = new MapperQueryArg[1];

		MapperQueryArg mapperQueryArg = new MapperQueryArg();
		mapperQueryArg.setEqualsTo("id", updateDto.getId());
		mapperWhereArgList[0] = mapperQueryArg;

		int rowsUpdate = utilJdbc.update(connectionWrapper, "cclip.cadastre_block", mapperUpdateArgList, mapperWhereArgList);

		return updateDto;
	}

	private CadastreBlockCensus insert(ConnectionWrapper connectionWrapper, CadastreBlockCensus updateDto, String auditLabel) {

		List<MapperUpdateArg> mapperUpdateArgListTmp = new ArrayList<MapperUpdateArg>();

		UserSystem userSystem = Context.getBean("userSystem", UserSystem.class);

		updateDto.setId(UUID.randomUUID().toString());
		updateDto.setErased(false);

		mapperUpdateArgListTmp.add(new MapperUpdateArg("id", updateDto.getId()));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("erased", updateDto.getErased()));

		if (updateDto.getYearBuilding() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("year_building", updateDto.getYearBuilding()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("year_building", Integer.class));
		}

		if (updateDto.getMonthBuilding() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("month_building", updateDto.getMonthBuilding()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("month_building", Integer.class));
		}

		if (updateDto.getM2Covered() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_covered", updateDto.getM2Covered()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_covered", Double.class));
		}

		if (updateDto.getDemolition() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("demolition", updateDto.getDemolition()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("demolition", false));
		}

		if (updateDto.getComment() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment", updateDto.getComment()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment", String.class));
		}

		if (updateDto.getFacade() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("facade", updateDto.getFacade()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("facade", String.class));
		}

		if (updateDto.getRoofStructure() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("roof_structure", updateDto.getRoofStructure()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("roof_structure", String.class));
		}

		if (updateDto.getHomes() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("homes", updateDto.getHomes()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("homes", String.class));
		}

		if (updateDto.getInteriorWalls() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("interior_walls", updateDto.getInteriorWalls()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("interior_walls", String.class));
		}

		if (updateDto.getCeiling() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("ceiling", updateDto.getCeiling()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("ceiling", String.class));
		}

		if (updateDto.getKitchen() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("kitchen", updateDto.getKitchen()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("kitchen", String.class));
		}

		if (updateDto.getToilets() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("toilets", updateDto.getToilets()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("toilets", String.class));
		}

		if (updateDto.getEntranceHall() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("entrance_hall", updateDto.getEntranceHall()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("entrance_hall", String.class));
		}

		if (updateDto.getFacilities() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("facilities", updateDto.getFacilities()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("facilities", String.class));
		}

		if (updateDto.getCarpentry() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("carpentry", updateDto.getCarpentry()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("carpentry", String.class));
		}

		if (updateDto.getCoverStructure() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cover_structure", updateDto.getCoverStructure()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cover_structure", String.class));
		}

		if (updateDto.getOrnamentation() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("ornamentation", updateDto.getOrnamentation()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("ornamentation", String.class));
		}

		if (updateDto.getStainedGlassLighting() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("stained_glass_lighting", updateDto.getStainedGlassLighting()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("stained_glass_lighting", String.class));
		}

		if (updateDto.getBuffetDining() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("buffet_dining", updateDto.getBuffetDining()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("buffet_dining", String.class));
		}

		if (updateDto.getPoints() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("points", updateDto.getPoints()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("points", Integer.class));
		}

		if (updateDto.getApc() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("apc", updateDto.getApc()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("apc", false));
		}

		if (updateDto.getApcDesc() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("apc_desc", updateDto.getApcDesc()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("apc_desc", String.class));
		}

		if (updateDto.getRate() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("rate", updateDto.getRate()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("rate", Integer.class));
		}

		if (updateDto.getCadastreActivityType() != null && updateDto.getCadastreActivityType().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_activity_type_id", updateDto.getCadastreActivityType().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_activity_type_id", String.class));
		}

//		if (updateDto.getCadastreCensus() != null && updateDto.getCadastreCensus().getId() != null) {
//			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_census_id", updateDto.getCadastreCensus().getId()));
//		} else {
//			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_census_id", String.class));
//		}

		if (updateDto.getCadastreConstructiveType() != null && updateDto.getCadastreConstructiveType().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_constructive_type_id", updateDto.getCadastreConstructiveType().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_constructive_type_id", String.class));
		}

		if (updateDto.getCadastreDestinationType() != null && updateDto.getCadastreDestinationType().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_destination_type_id", updateDto.getCadastreDestinationType().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_destination_type_id", String.class));
		}

		// -----------------------------------------

		if (updateDto.getAuditVersion() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version", 1));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version", 1));
		}

		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version_code_label", "2"));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version_label", auditLabel));

		Timestamp t = new Timestamp(System.currentTimeMillis());

		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_date_create", t));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_date_update", t));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_user_create", userSystem.getId()));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_user_update", userSystem.getId()));

		// -----------------------------------------

		// -----------------------------------------

		MapperUpdateArg[] mapperUpdateArgList = new MapperUpdateArg[mapperUpdateArgListTmp.size()];
		mapperUpdateArgList = mapperUpdateArgListTmp.toArray(mapperUpdateArgList);

		int rowsUpdate = utilJdbc.insert(connectionWrapper, "cclip.cadastre_block_census", mapperUpdateArgList);

		return updateDto;
	}

	private CadastreBlock insert(ConnectionWrapper connectionWrapper, CadastreBlock updateDto, String auditLabel) {

		List<MapperUpdateArg> mapperUpdateArgListTmp = new ArrayList<MapperUpdateArg>();

		UserSystem userSystem = Context.getBean("userSystem", UserSystem.class);

		updateDto.setId(UUID.randomUUID().toString());
		updateDto.setErased(false);

		mapperUpdateArgListTmp.add(new MapperUpdateArg("id", updateDto.getId()));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("erased", updateDto.getErased()));

		if (updateDto.getYearBuilding() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("year_building", updateDto.getYearBuilding()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("year_building", Integer.class));
		}

		if (updateDto.getMonthBuilding() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("month_building", updateDto.getMonthBuilding()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("month_building", Integer.class));
		}

		if (updateDto.getM2Covered() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_covered", updateDto.getM2Covered()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("m2_covered", Double.class));
		}

		if (updateDto.getDemolition() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("demolition", updateDto.getDemolition()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("demolition", false));
		}

		if (updateDto.getComment() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment", updateDto.getComment()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment", String.class));
		}

		if (updateDto.getFacade() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("facade", updateDto.getFacade()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("facade", String.class));
		}

		if (updateDto.getRoofStructure() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("roof_structure", updateDto.getRoofStructure()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("roof_structure", String.class));
		}

		if (updateDto.getHomes() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("homes", updateDto.getHomes()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("homes", String.class));
		}

		if (updateDto.getInteriorWalls() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("interior_walls", updateDto.getInteriorWalls()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("interior_walls", String.class));
		}

		if (updateDto.getCeiling() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("ceiling", updateDto.getCeiling()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("ceiling", String.class));
		}

		if (updateDto.getKitchen() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("kitchen", updateDto.getKitchen()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("kitchen", String.class));
		}

		if (updateDto.getToilets() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("toilets", updateDto.getToilets()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("toilets", String.class));
		}

		if (updateDto.getEntranceHall() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("entrance_hall", updateDto.getEntranceHall()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("entrance_hall", String.class));
		}

		if (updateDto.getFacilities() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("facilities", updateDto.getFacilities()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("facilities", String.class));
		}

		if (updateDto.getCarpentry() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("carpentry", updateDto.getCarpentry()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("carpentry", String.class));
		}

		if (updateDto.getCoverStructure() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cover_structure", updateDto.getCoverStructure()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cover_structure", String.class));
		}

		if (updateDto.getOrnamentation() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("ornamentation", updateDto.getOrnamentation()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("ornamentation", String.class));
		}

		if (updateDto.getStainedGlassLighting() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("stained_glass_lighting", updateDto.getStainedGlassLighting()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("stained_glass_lighting", String.class));
		}

		if (updateDto.getBuffetDining() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("buffet_dining", updateDto.getBuffetDining()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("buffet_dining", String.class));
		}

		if (updateDto.getPoints() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("points", updateDto.getPoints()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("points", Integer.class));
		}

		if (updateDto.getApc() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("apc", updateDto.getApc()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("apc", false));
		}

		if (updateDto.getApcDesc() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("apc_desc", updateDto.getApcDesc()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("apc_desc", String.class));
		}

		if (updateDto.getRate() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("rate", updateDto.getRate()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("rate", Integer.class));
		}

		if (updateDto.getCadastreActivityType() != null && updateDto.getCadastreActivityType().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_activity_type_id", updateDto.getCadastreActivityType().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_activity_type_id", String.class));
		}

		if (updateDto.getCadastre() != null && updateDto.getCadastre().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_id", updateDto.getCadastre().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_id", String.class));
		}

		if (updateDto.getCadastreConstructiveType() != null && updateDto.getCadastreConstructiveType().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_constructive_type_id", updateDto.getCadastreConstructiveType().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_constructive_type_id", String.class));
		}

		if (updateDto.getCadastreDestinationType() != null && updateDto.getCadastreDestinationType().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_destination_type_id", updateDto.getCadastreDestinationType().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cadastre_destination_type_id", String.class));
		}

		// -----------------------------------------

		if (updateDto.getAuditVersion() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version", 1));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version", 1));
		}

		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version_code_label", "3"));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version_label", auditLabel));

		Timestamp t = new Timestamp(System.currentTimeMillis());

		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_date_create", t));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_date_update", t));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_user_create", userSystem.getId()));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_user_update", userSystem.getId()));

		// -----------------------------------------

		// -----------------------------------------

		MapperUpdateArg[] mapperUpdateArgList = new MapperUpdateArg[mapperUpdateArgListTmp.size()];
		mapperUpdateArgList = mapperUpdateArgListTmp.toArray(mapperUpdateArgList);

		int rowsUpdate = utilJdbc.insert(connectionWrapper, "cclip.cadastre_block", mapperUpdateArgList);

		return updateDto;
	}

	private Uf insert(ConnectionWrapper connectionWrapper, Uf updateDto, String auditLabel) {

		List<MapperUpdateArg> mapperUpdateArgListTmp = new ArrayList<MapperUpdateArg>();

		UserSystem userSystem = Context.getBean("userSystem", UserSystem.class);

		updateDto.setId(UUID.randomUUID().toString());
		updateDto.setErased(false);

		mapperUpdateArgListTmp.add(new MapperUpdateArg("id", updateDto.getId()));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("erased", updateDto.getErased()));

		if (updateDto.getNeighbourhood() != null && updateDto.getNeighbourhood().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("neighbourhood_id", updateDto.getNeighbourhood().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("neighbourhood_id", String.class));
		}

		if (updateDto.getStreet() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("street", updateDto.getStreet()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("street", String.class));
		}

		if (updateDto.getStreetNumber() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("street_number", updateDto.getStreetNumber()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("street_number", String.class));
		}

		if (updateDto.getCommentAddress() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment_address", updateDto.getCommentAddress()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment_address", String.class));
		}

		if (updateDto.getComment() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment", updateDto.getComment()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment", String.class));
		}

		if (updateDto.getBuildingFloor() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("building_floor", updateDto.getBuildingFloor()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("building_floor", String.class));
		}

		if (updateDto.getBuildingRoom() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("building_room", updateDto.getBuildingRoom()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("building_room", String.class));
		}

		if (updateDto.getBuilding() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("building", updateDto.getBuilding()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("building", String.class));
		}

		if (updateDto.getPhone() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("phone", updateDto.getPhone()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("phone", String.class));
		}

		if (updateDto.getName() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("name", updateDto.getName()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("name", String.class));
		}

		if (updateDto.getDni() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("dni", updateDto.getDni()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("dni", String.class));
		}

		if (updateDto.getCuit() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cuit", updateDto.getCuit()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cuit", String.class));
		}

		if (updateDto.getIva() != null && updateDto.getIva().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("iva_id", updateDto.getIva().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("iva_id", String.class));
		}

		// -----------------------------------------

		if (updateDto.getAuditVersion() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version", updateDto.getAuditVersion() + 1));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version", 1));
		}

		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version_code_label", "3"));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version_label", auditLabel));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_date_update", new Timestamp(System.currentTimeMillis())));

		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_user_update", userSystem.getId()));

		// -----------------------------------------

		MapperUpdateArg[] mapperUpdateArgList = new MapperUpdateArg[mapperUpdateArgListTmp.size()];
		mapperUpdateArgList = mapperUpdateArgListTmp.toArray(mapperUpdateArgList);

		int rowsUpdate = utilJdbc.insert(connectionWrapper, "cclip.uf", mapperUpdateArgList);

		return updateDto;
	}

	private UfCensus insert(ConnectionWrapper connectionWrapper, UfCensus updateDto, String auditLabel) {

		List<MapperUpdateArg> mapperUpdateArgListTmp = new ArrayList<MapperUpdateArg>();

		UserSystem userSystem = Context.getBean("userSystem", UserSystem.class);

		// updateDto.setId(UUID.randomUUID().toString());
		updateDto.setErased(false);

		mapperUpdateArgListTmp.add(new MapperUpdateArg("id", updateDto.getId()));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("erased", updateDto.getErased()));

		if (updateDto.getNeighbourhood() != null && updateDto.getNeighbourhood().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("neighbourhood_id", updateDto.getNeighbourhood().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("neighbourhood_id", String.class));
		}

		if (updateDto.getStreet() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("street", updateDto.getStreet()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("street", String.class));
		}

		if (updateDto.getStreetNumber() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("street_number", updateDto.getStreetNumber()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("street_number", String.class));
		}

		if (updateDto.getCommentAddress() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment_address", updateDto.getCommentAddress()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment_address", String.class));
		}

		if (updateDto.getComment() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment", updateDto.getComment()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment", String.class));
		}

		if (updateDto.getBuildingFloor() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("building_floor", updateDto.getBuildingFloor()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("building_floor", String.class));
		}

		if (updateDto.getBuildingRoom() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("building_room", updateDto.getBuildingRoom()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("building_room", String.class));
		}

		if (updateDto.getBuilding() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("building", updateDto.getBuilding()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("building", String.class));
		}

		if (updateDto.getPhone() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("phone", updateDto.getPhone()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("phone", String.class));
		}

		if (updateDto.getName() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("name", updateDto.getName()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("name", String.class));
		}

		if (updateDto.getDni() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("dni", updateDto.getDni()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("dni", String.class));
		}

		if (updateDto.getCuit() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cuit", updateDto.getCuit()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cuit", String.class));
		}

		if (updateDto.getIva() != null && updateDto.getIva().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("iva_id", updateDto.getIva().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("iva_id", String.class));
		}

		// -----------------------------------------

		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_date_create", updateDto.getAuditDateCreate()));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_user_create", userSystem.getId()));

		if (updateDto.getAuditVersion() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version", updateDto.getAuditVersion() + 1));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version", 1));
		}

		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version_code_label", "3"));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version_label", auditLabel));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_date_update", new Timestamp(System.currentTimeMillis())));

		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_user_update", userSystem.getId()));

		// -----------------------------------------

		MapperUpdateArg[] mapperUpdateArgList = new MapperUpdateArg[mapperUpdateArgListTmp.size()];
		mapperUpdateArgList = mapperUpdateArgListTmp.toArray(mapperUpdateArgList);

		int rowsUpdate = utilJdbc.insert(connectionWrapper, "cclip.uf_census", mapperUpdateArgList);

		return updateDto;
	}

	private Uf update(ConnectionWrapper connectionWrapper, Uf updateDto, String auditLabel) {

		List<MapperUpdateArg> mapperUpdateArgListTmp = new ArrayList<MapperUpdateArg>();

		if (updateDto.getNeighbourhood() != null && updateDto.getNeighbourhood().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("neighbourhood_id", updateDto.getNeighbourhood().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("neighbourhood_id", String.class));
		}

		if (updateDto.getStreet() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("street", updateDto.getStreet()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("street", String.class));
		}

		if (updateDto.getStreetNumber() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("street_number", updateDto.getStreetNumber()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("street_number", String.class));
		}

		if (updateDto.getCommentAddress() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment_address", updateDto.getCommentAddress()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment_address", String.class));
		}

		if (updateDto.getComment() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment", updateDto.getComment()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment", String.class));
		}

		if (updateDto.getBuildingFloor() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("building_floor", updateDto.getBuildingFloor()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("building_floor", String.class));
		}

		if (updateDto.getBuildingRoom() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("building_room", updateDto.getBuildingRoom()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("building_room", String.class));
		}

		if (updateDto.getBuilding() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("building", updateDto.getBuilding()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("building", String.class));
		}

		if (updateDto.getPhone() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("phone", updateDto.getPhone()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("phone", String.class));
		}

		if (updateDto.getName() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("name", updateDto.getName()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("name", String.class));
		}

		if (updateDto.getDni() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("dni", updateDto.getDni()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("dni", String.class));
		}

		if (updateDto.getCuit() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cuit", updateDto.getCuit()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cuit", String.class));
		}

		if (updateDto.getIva() != null && updateDto.getIva().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("iva_id", updateDto.getIva().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("iva_id", String.class));
		}

		// -----------------------------------------

		if (updateDto.getAuditVersion() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version", updateDto.getAuditVersion() + 1));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version", 1));
		}

		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version_code_label", "2"));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version_label", auditLabel));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_date_update", new Timestamp(System.currentTimeMillis())));

		UserSystem userSystem = Context.getBean("userSystem", UserSystem.class);
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_user_update", userSystem.getId()));

		// -----------------------------------------

		MapperUpdateArg[] mapperUpdateArgList = new MapperUpdateArg[mapperUpdateArgListTmp.size()];
		mapperUpdateArgList = mapperUpdateArgListTmp.toArray(mapperUpdateArgList);

		MapperQueryArg[] mapperWhereArgList = new MapperQueryArg[1];

		MapperQueryArg mapperQueryArg = new MapperQueryArg();
		mapperQueryArg.setEqualsTo("id", updateDto.getId());
		mapperWhereArgList[0] = mapperQueryArg;

		int rowsUpdate = utilJdbc.update(connectionWrapper, "cclip.uf", mapperUpdateArgList, mapperWhereArgList);

		return updateDto;
	}

	private UfCensus update(ConnectionWrapper connectionWrapper, UfCensus updateDto, String auditLabel) {

		List<MapperUpdateArg> mapperUpdateArgListTmp = new ArrayList<MapperUpdateArg>();

		if (updateDto.getNeighbourhood() != null && updateDto.getNeighbourhood().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("neighbourhood_id", updateDto.getNeighbourhood().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("neighbourhood_id", String.class));
		}

		if (updateDto.getStreet() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("street", updateDto.getStreet()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("street", String.class));
		}

		if (updateDto.getStreetNumber() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("street_number", updateDto.getStreetNumber()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("street_number", String.class));
		}

		if (updateDto.getCommentAddress() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment_address", updateDto.getCommentAddress()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment_address", String.class));
		}

		if (updateDto.getComment() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment", updateDto.getComment()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("comment", String.class));
		}

		if (updateDto.getBuildingFloor() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("building_floor", updateDto.getBuildingFloor()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("building_floor", String.class));
		}

		if (updateDto.getBuildingRoom() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("building_room", updateDto.getBuildingRoom()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("building_room", String.class));
		}

		if (updateDto.getBuilding() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("building", updateDto.getBuilding()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("building", String.class));
		}

		if (updateDto.getPhone() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("phone", updateDto.getPhone()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("phone", String.class));
		}

		if (updateDto.getName() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("name", updateDto.getName()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("name", String.class));
		}

		if (updateDto.getDni() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("dni", updateDto.getDni()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("dni", String.class));
		}

		if (updateDto.getCuit() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cuit", updateDto.getCuit()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("cuit", String.class));
		}

		if (updateDto.getIva() != null && updateDto.getIva().getId() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("iva_id", updateDto.getIva().getId()));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("iva_id", String.class));
		}

		// -----------------------------------------

		if (updateDto.getAuditVersion() != null) {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version", updateDto.getAuditVersion() + 1));
		} else {
			mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version", 1));
		}

		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version_code_label", "2"));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_version_label", auditLabel));
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_date_update", new Timestamp(System.currentTimeMillis())));

		UserSystem userSystem = Context.getBean("userSystem", UserSystem.class);
		mapperUpdateArgListTmp.add(new MapperUpdateArg("audit_user_update", userSystem.getId()));

		// -----------------------------------------

		MapperUpdateArg[] mapperUpdateArgList = new MapperUpdateArg[mapperUpdateArgListTmp.size()];
		mapperUpdateArgList = mapperUpdateArgListTmp.toArray(mapperUpdateArgList);

		MapperQueryArg[] mapperWhereArgList = new MapperQueryArg[1];

		MapperQueryArg mapperQueryArg = new MapperQueryArg();
		mapperQueryArg.setEqualsTo("id", updateDto.getId());
		mapperWhereArgList[0] = mapperQueryArg;

		int rowsUpdate = utilJdbc.update(connectionWrapper, "cclip.uf_census", mapperUpdateArgList, mapperWhereArgList);

		return updateDto;
	}

	public ResultList find1(ConnectionWrapper connectionWrapper, MapperQueryArg[] argList, MapperQueryOrderArg[] orderList, Integer offSet, Integer limit) {

		ResultList r = utilJdbc.findByExamplePageable(connectionWrapper, "cclip.v_schedule_census", argList, new MapperResultFind1(), offSet, limit, orderList);

		return r;

	}

	public Integer[] find2(ConnectionWrapper connectionWrapper, Boolean asc) {

		MapperQueryOrderArg[] orderList = { new MapperQueryOrderArg("year", asc) };

		String sql = "cclip.v_schedule_census_year";

		Object[] r = utilJdbc.find(connectionWrapper, sql, new MapperResultFind2(), orderList);

		Integer[] resultList = new Integer[r.length];

		for (int i = 0; i < r.length; i++) {

			resultList[i] = (Integer) r[i];
		}

		return resultList;

	}

	public ScheduleCensus[] find3(ConnectionWrapper connectionWrapper, MapperQueryArg[] argList, MapperQueryOrderArg[] orderList) {

		Object[] r = utilJdbc.findByExample(connectionWrapper, "cclip.v_schedule_census", argList, new MapperResultFind1(), orderList);

		ScheduleCensus[] resultList = new ScheduleCensus[r.length];

		for (int i = 0; i < r.length; i++) {

			resultList[i] = (ScheduleCensus) r[i];

		}

		return resultList;

	}

	public class MapperResultFind2 implements MapperResult {

		public Object mapRow(ResultSet rs) throws SQLException {

			return rs.getInt("year");
		}
	}

	public class MapperResultFind1 implements MapperResult {

		public Object mapRow(ResultSet rs) throws SQLException {

			ScheduleCensus dto = new ScheduleCensus();

			/* id */
			dto.setId(rs.getString("id"));

			/* erased */
			dto.setErased(rs.getBoolean("erased"));

			dto.setCadastralCode(rs.getString("cadastral_code"));

			dto.setNumberCensus(rs.getInt("number_census"));

			dto.setInsertCadastre(rs.getBoolean("insert_cadastre"));
			dto.setDeleteCadastre(rs.getBoolean("delete_cadastre"));
			dto.setUpdateCadastre(rs.getBoolean("update_cadastre"));

//			dto.setDateDelete(rs.getTimestamp("date_delete"));
//			dto.setCodeReasonLow(rs.getString("code_reason_low"));
//			dto.setReasonLow(rs.getString("reason_low"));

			/* Fecha de Censo */
			dto.setCensused(rs.getDate("censused"));

			/* Planilla de Censo Entregada por Censista */
			// dto.setDelivered(rs.getDate("delivered"));

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

			if (rs.getString("schedule_batch_id") != null) {

				/* Lote de Entrega */

				ScheduleBatch dtoFk = new ScheduleBatch();

				dtoFk.setId(rs.getString("schedule_batch_id"));
				dtoFk.setErased(rs.getBoolean("schedule_batch_erased"));
				dtoFk.setNumberBatch(rs.getInt("schedule_number_batch"));
				// dtoFk.setPlannedDelivered(rs.getDate("schedule_batch_planned_delivered"));
				dtoFk.setCreateDate(rs.getDate("schedule_batch_create_date"));
				dtoFk.setClose(rs.getDate("schedule_batch_close"));
				dtoFk.setDelivered(rs.getDate("schedule_batch_delivered"));
				dtoFk.setComment(rs.getString("schedule_batch_comment"));

				dto.setScheduleBatch(dtoFk);

			}

			if (rs.getString("schedule_census_result_id") != null) {

				/* Resultado de Censo */

				ScheduleCensusResult dtoFk = new ScheduleCensusResult();

				dtoFk.setId(rs.getString("schedule_census_result_id"));
				dtoFk.setErased(rs.getBoolean("schedule_census_result_erased"));
				// dtoFk.setCode(rs.getString("schedule_census_result_code"));
				dtoFk.setName(rs.getString("schedule_census_result_name"));
				dtoFk.setComment(rs.getString("schedule_census_result_comment"));
				dto.setScheduleCensusResult(dtoFk);

			}

			if (rs.getString("cadastre_activity_type_id") != null) {

				/* Tipo de Actividad */

				CadastreActivityType dtoFk = new CadastreActivityType();

				dtoFk.setId(rs.getString("cadastre_activity_type_id"));
				dtoFk.setErased(rs.getBoolean("cadastre_activity_type_erased"));
				// dtoFk.setCode(rs.getString("cadastre_activity_type_code"));
				dtoFk.setName(rs.getString("cadastre_activity_type_name"));
				dtoFk.setComment(rs.getString("cadastre_activity_type_comment"));
				dto.setCadastreActivityType(dtoFk);

			}

			return dto;
		}
	}

	public ScheduleCensusItem[] findItem(ConnectionWrapper connectionWrapper, MapperQueryArg[] argList, MapperQueryOrderArg[] orderList) {

		Object[] r = utilJdbc.findByExample(connectionWrapper, "cclip.v_census_item", argList, new MapperResultFindItem(), orderList);

		ScheduleCensusItem[] resultList = new ScheduleCensusItem[r.length];

		for (int i = 0; i < r.length; i++) {

			resultList[i] = (ScheduleCensusItem) r[i];

		}

		return resultList;

	}

	public class MapperResultFindItem implements MapperResult {

		public Object mapRow(ResultSet rs) throws SQLException {

			ScheduleCensusItem dto = new ScheduleCensusItem();

			/* id */
			dto.setId(rs.getString("schedule_census_item_id"));

			/* erased */
			// dto.setErased(rs.getBoolean("erased"));

			if (rs.getString("id") != null) {

				/* Catastro de Parcela de Tierra */

				Cadastre cadastre = (Cadastre) new com.cclip.dao.geo.cadastre.MapperResultFind1().mapRow(rs);

				CadastreCensus cadastreCensus = UtilCadastre.buildByCadastre(cadastre, true);

				dto.setCadastreCensus(cadastreCensus);

			}

			if (rs.getString("cadastre_original_id") != null) {

				/* Catastro de Parcela de Tierra */

				Cadastre dtoFk = new Cadastre();

				dtoFk.setId(rs.getString("cadastre_original_id"));
				dto.setCadastre(dtoFk);

			}

			if (rs.getString("schedule_census_id") != null) {

				/* Censo de Parcela */

				ScheduleCensus dtoFk = new ScheduleCensus();

				dtoFk.setId(rs.getString("schedule_census_id"));
				dto.setScheduleCensus(dtoFk);

			}

			return dto;
		}
	}

	// private Cadastre mapRowCadastre(ResultSet rs) throws SQLException {
	public Cadastre[] findByCadastralCode(ConnectionWrapper connectionWrapper, String cadastralCode) {

		MapperQueryArg[] argList = null;

		if (cadastralCode != null && cadastralCode.trim().length() > 0) {

			argList = new MapperQueryArg[1];

			MapperQueryArg a = new MapperQueryArg();

			a.setBeginsWith("cadastral_code", cadastralCode.trim());

			argList[0] = a;

		} else {
			return new Cadastre[0];
		}

		MapperQueryOrderArg[] orderList = { new MapperQueryOrderArg("cadastral_code", true) };

		Object[] r = utilJdbc.findByExample(connectionWrapper, "cclip.v_cadastre", argList, new com.cclip.dao.geo.cadastre.MapperResultFind1(), orderList);

		Cadastre[] resultList = new Cadastre[r.length];

		for (int i = 0; i < r.length; i++) {

			resultList[i] = (Cadastre) r[i];
		}

		return resultList;

	}

}
