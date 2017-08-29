package com.cclip.dao.geo.cadastre;

import com.cclip.model.person.Iva;
import com.cclip.model.geo.cadastre.UserWaterSituation;
import com.cclip.model.person.Uf;
import com.cclip.model.geo.CityArea;
import com.cclip.model.geo.cadastre.CadastreType;
import com.cclip.model.geo.cadastre.CadastreSituation;
import com.cclip.model.geo.Neighbourhood;
import com.cclip.model.geo.cadastre.subdivision.CadastreSubDivisionType;
import com.cclip.model.schedule.scanning.ScheduleScanningResult;
import com.cclip.model.geo.cadastre.CadastreActivityType;
import com.cclip.model.geo.cadastre.WaterMeterType;
import com.cclip.model.geo.cadastre.block.CadastreConstructiveType;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.utiljdbc.ConnectionWrapper;
import org.utiljdbc.MapperQueryArg;
import org.utiljdbc.MapperQueryOrderArg;
import org.utiljdbc.MapperResult;
import org.utiljdbc.ResultList;
import org.utiljdbc.UtilJdbcOld;

import com.cclip.model.geo.cadastre.Cadastre;

/** DAO Catastro de Parcela de Tierra */
public class CadastreDao {

	protected UtilJdbcOld utilJdbc;

	public void setUtilJdbc(UtilJdbcOld utilJdbc) {
		this.utilJdbc = utilJdbc;
	}

	public Cadastre findById(ConnectionWrapper connectionWrapper, String id) {

		Cadastre r = (Cadastre) utilJdbc.findById(connectionWrapper, "cclip.cadastre", id, new MapperResultFind());

		//System.out.println(connectionWrapper.getSql()); // Con esta linea podemos ver todos los sql que se ejecutaron con la misma conexión.

		return r;

	}

	public Cadastre[] find(ConnectionWrapper connectionWrapper) {

		return find(connectionWrapper, new MapperQueryOrderArg[0]);

	}

	public Cadastre[] find(ConnectionWrapper connectionWrapper, MapperQueryOrderArg[] orderList) {

		Object[] r = utilJdbc.find(connectionWrapper, "cclip.cadastre", new MapperResultFind(), orderList);

		Cadastre[] resultList = new Cadastre[r.length];

		for (int i = 0; i < r.length; i++) {

			resultList[i] = (Cadastre) r[i];

		}

		return resultList;

	}

	public Cadastre[] find(ConnectionWrapper connectionWrapper, MapperQueryArg[] argList, MapperQueryOrderArg[] orderList) {

		Object[] r = utilJdbc.findByExample(connectionWrapper, "cclip.cadastre", argList, new MapperResultFind(), orderList);

		Cadastre[] resultList = new Cadastre[r.length];

		for (int i = 0; i < r.length; i++) {

			resultList[i] = (Cadastre) r[i];

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

		ResultList r = utilJdbc.findByExamplePageable(connectionWrapper, "cclip.cadastre", argList, new MapperResultFind(), offSet, limit, orderList);

		return r;

	}

	public class MapperResultFind implements MapperResult {

		public Object mapRow(ResultSet rs) throws SQLException {

			Cadastre dto = new Cadastre();

			/* id */
			dto.setId(rs.getString("id"));

			/* erased */
			dto.setErased(rs.getBoolean("erased"));

			/* Fecha de Alta */
			dto.setDateCreate(rs.getTimestamp("date_create"));

			/* Fecha de Baja */
			dto.setDateDelete(rs.getTimestamp("date_delete"));

			/* Código Catastral */
			dto.setCadastralCode(rs.getString("cadastral_code"));

			/* Metros Cuadrados */
			dto.setM2(rs.getDouble("m2"));

			/* Metros Cuadrados Cubiertos */
			dto.setM2Covered(rs.getDouble("m2_covered"));

			/* Cuenta Cliente */
			dto.setCtaCli(rs.getString("cta_cli"));

			/* Sub Cuenta Cliente */
			dto.setSubCtaCli(rs.getString("sub_cta_cli"));

			/* Dígito Verificador */
			dto.setDv(rs.getString("dv"));

			/* Usuario del Servicio de Agua */
			dto.setUserWaterService(rs.getString("user_water_service"));

			/* DNI Titular */
			dto.setUserWaterServiceDni(rs.getString("user_water_service_dni"));

			/* CUIL/CUIT Titular */
			dto.setUserWaterServiceCuit(rs.getString("user_water_service_cuit"));

			/* Medidor */
			dto.setWaterMeter(rs.getBoolean("water_meter"));

			/* Comentario */
			dto.setComment(rs.getString("comment"));

			/* Localidad */
			dto.setInmLocality(rs.getString("inm_locality"));

			/* Vecindario */
			dto.setInmNeighbourhoodComment(rs.getString("inm_neighbourhood_comment"));

			/* Calle */
			dto.setInmStreet(rs.getString("inm_street"));

			/* Número de Calle */
			dto.setInmStreetNumber(rs.getString("inm_street_number"));

			/* Número de Calle Estimado */
			dto.setInmStreetNumberEstimated(rs.getBoolean("inm_street_number_estimated"));

			/* Planta del Edificio */
			dto.setInmBuildingFloor(rs.getString("inm_building_floor"));

			/* Departamento de Edificio */
			dto.setInmBuildingRoom(rs.getString("inm_building_room"));

			/* Edificio */
			dto.setInmBuilding(rs.getString("inm_building"));

			/* Código Postal */
			dto.setInmPostalCode(rs.getString("inm_postal_code"));

			/* Comentario del Domicilio */
			dto.setInmCommentAddress(rs.getString("inm_comment_address"));

			/* Latitud */
			dto.setInmLat(rs.getDouble("inm_lat"));

			/* Longitud */
			dto.setInmLng(rs.getDouble("inm_lng"));

			/* País  del Titular - Código ISO 3166-1 Alfa-2 */
			dto.setUserPostalCountryCode(rs.getString("user_postal_country_code"));

			/* País del Usuario */
			dto.setUserPostalCountry(rs.getString("user_postal_country"));

			/* Provincia  del Usuario - Código ISO 3166-2 */
			dto.setUserPostalAdminAreaLevel1Code(rs.getString("user_postal_admin_area_level1_code"));

			/* Provincia del Usuario */
			dto.setUserPostalAdminAreaLevel1(rs.getString("user_postal_admin_area_level1"));

			/* Departamento / Distrito  del Usuario */
			dto.setUserPostalAdminAreaLevel2(rs.getString("user_postal_admin_area_level2"));

			/* Localidad del Usuario */
			dto.setUserPostalLocality(rs.getString("user_postal_locality"));

			/* Vecindario del Usuario */
			dto.setUserPostalNeighbourhood(rs.getString("user_postal_neighbourhood"));

			/* Calle del Usuario */
			dto.setUserPostalStreet(rs.getString("user_postal_street"));

			/* Número de Calle del Usuario */
			dto.setUserPostalStreetNumber(rs.getString("user_postal_street_number"));

			/* Planta del Edificio del Usuario */
			dto.setUserPostalBuildingFloor(rs.getString("user_postal_building_floor"));

			/* Departamento de Edificio del Usuario */
			dto.setUserPostalBuildingRoom(rs.getString("user_postal_building_room"));

			/* Edificio del Usuario */
			dto.setUserPostalBuilding(rs.getString("user_postal_building"));

			/* Código Postal del Usuario */
			dto.setUserPostalPostalCode(rs.getString("user_postal_postal_code"));

			/* Comentario del Domicilio del Usuario */
			dto.setUserPostalCommentAddress(rs.getString("user_postal_comment_address"));

			/* Latitud del Usuario */
			dto.setUserPostalLat(rs.getDouble("user_postal_lat"));

			/* Longitud del Usuario */
			dto.setUserPostalLng(rs.getDouble("user_postal_lng"));

			/* Teléfono del Usuario */
			dto.setUserPhone(rs.getString("user_phone"));

			/* Código de Motivo de Baja */
			dto.setCodeReasonLow(rs.getString("code_reason_low"));

			/* Motivo de Baja */
			dto.setReasonLow(rs.getString("reason_low"));

			/* Cantidad de PH */
			dto.setCantPh(rs.getInt("cant_ph"));

			/* Fecha Barrido -2 */
			dto.setDateScanning2(rs.getDate("date_scanning2"));

			/* Fecha Barrido -1 */
			dto.setDateScanning1(rs.getDate("date_scanning1"));

			/* Fecha Barrido 0 */
			dto.setDateScanning0(rs.getDate("date_scanning0"));

			/* auditDateCreate */
			dto.setAuditDateCreate(rs.getTimestamp("audit_date_create"));

			/* auditUserCreate */
			dto.setAuditUserCreate(rs.getString("audit_user_create"));

			/* auditDateUpdate */
			dto.setAuditDateUpdate(rs.getTimestamp("audit_date_update"));

			/* auditUserUpdate */
			dto.setAuditUserUpdate(rs.getString("audit_user_update"));

			/* auditVersion */
			dto.setAuditVersion(rs.getLong("audit_version"));

			/* auditVersionCodeLabel */
			dto.setAuditVersionCodeLabel(rs.getString("audit_version_code_label"));

			/* auditVersionLabel */
			dto.setAuditVersionLabel(rs.getString("audit_version_label"));

			/* SubDivision de Hecho */
			dto.setFact(rs.getBoolean("fact"));

			/* Metros Cuadrados Cubiertos Compartidos */
			dto.setM2CoveredShared(rs.getDouble("m2_covered_shared"));

			/* Metros Cuadrados Cubiertos Ampliados */
			dto.setM2CoveredExpanded(rs.getDouble("m2_covered_expanded"));

			/* Porcentaje de PH */
			dto.setM2Percent(rs.getDouble("m2_percent"));

			/* Año Bloque de PH */
			dto.setYearBuilding(rs.getInt("year_building"));

			/* Progreso de Construcción */
			dto.setProgressConstructionPrev(rs.getDouble("progress_construction_prev"));

			if(rs.getString("iva_id") != null) { 

				/* Condición de IVA */

				Iva dtoFk = new Iva();

				dtoFk.setId(rs.getString("iva_id"));
				dto.setUserIva(dtoFk);

			}

			if(rs.getString("user_water_situation_id") != null) { 

				/* Situación de Usuario del Servicio */

				UserWaterSituation dtoFk = new UserWaterSituation();

				dtoFk.setId(rs.getString("user_water_situation_id"));
				dto.setUserWaterSituation(dtoFk);

			}

			if(rs.getString("uf_id") != null) { 

				/* Unidad de Facturación */

				Uf dtoFk = new Uf();

				dtoFk.setId(rs.getString("uf_id"));
				dto.setUf(dtoFk);

			}

			if(rs.getString("city_area_id") != null) { 

				/* Area (Zona) de Ciudad */

				CityArea dtoFk = new CityArea();

				dtoFk.setId(rs.getString("city_area_id"));
				dto.setCityArea(dtoFk);

			}

			if(rs.getString("cadastre_type_id") != null) { 

				/* Tipo de Catastro */

				CadastreType dtoFk = new CadastreType();

				dtoFk.setId(rs.getString("cadastre_type_id"));
				dto.setCadastreType(dtoFk);

			}

			if(rs.getString("cadastre_situation_id") != null) { 

				/* Situación de Catastro */

				CadastreSituation dtoFk = new CadastreSituation();

				dtoFk.setId(rs.getString("cadastre_situation_id"));
				dto.setCadastreSituation(dtoFk);

			}

			if(rs.getString("neighbourhood_id") != null) { 

				/* Vecindario */

				Neighbourhood dtoFk = new Neighbourhood();

				dtoFk.setId(rs.getString("neighbourhood_id"));
				dto.setInmNeighbourhood(dtoFk);

			}

			if(rs.getString("cadastre_sub_division_type_id") != null) { 

				/* Tipo de PH */

				CadastreSubDivisionType dtoFk = new CadastreSubDivisionType();

				dtoFk.setId(rs.getString("cadastre_sub_division_type_id"));
				dto.setCadastreSubDivisionType(dtoFk);

			}

			if(rs.getString("schedule_scanning_result_id") != null) { 

				/* Resultado de Barrido */

				ScheduleScanningResult dtoFk = new ScheduleScanningResult();

				dtoFk.setId(rs.getString("schedule_scanning_result_id"));
				dto.setScheduleScanningResult2(dtoFk);

			}

			if(rs.getString("schedule_scanning_result_id") != null) { 

				/* Resultado de Barrido */

				ScheduleScanningResult dtoFk = new ScheduleScanningResult();

				dtoFk.setId(rs.getString("schedule_scanning_result_id"));
				dto.setScheduleScanningResult1(dtoFk);

			}

			if(rs.getString("schedule_scanning_result_id") != null) { 

				/* Resultado de Barrido */

				ScheduleScanningResult dtoFk = new ScheduleScanningResult();

				dtoFk.setId(rs.getString("schedule_scanning_result_id"));
				dto.setScheduleScanningResult0(dtoFk);

			}

			if(rs.getString("cadastre_activity_type_id") != null) { 

				/* Tipo de Actividad */

				CadastreActivityType dtoFk = new CadastreActivityType();

				dtoFk.setId(rs.getString("cadastre_activity_type_id"));
				dto.setCadastreActivityType(dtoFk);

			}

			if(rs.getString("water_meter_type_id") != null) { 

				/* Tipo de Medidor */

				WaterMeterType dtoFk = new WaterMeterType();

				dtoFk.setId(rs.getString("water_meter_type_id"));
				dto.setWaterMeterType(dtoFk);

			}

			if(rs.getString("cadastre_constructive_type_id") != null) { 

				/* Tipo Constructivo */

				CadastreConstructiveType dtoFk = new CadastreConstructiveType();

				dtoFk.setId(rs.getString("cadastre_constructive_type_id"));
				dto.setCadastreConstructiveType(dtoFk);

			}

			return dto;
		}
	}
}
