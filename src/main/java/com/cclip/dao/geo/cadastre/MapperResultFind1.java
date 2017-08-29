package com.cclip.dao.geo.cadastre;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.utiljdbc.MapperResult;

import com.cclip.model.geo.CityArea;
import com.cclip.model.geo.Neighbourhood;
import com.cclip.model.geo.cadastre.Cadastre;
import com.cclip.model.geo.cadastre.CadastreActivityType;
import com.cclip.model.geo.cadastre.CadastreSituation;
import com.cclip.model.geo.cadastre.CadastreType;
import com.cclip.model.geo.cadastre.UserWaterSituation;
import com.cclip.model.geo.cadastre.block.CadastreConstructiveType;
import com.cclip.model.geo.cadastre.subdivision.CadastreSubDivisionType;
import com.cclip.model.person.Iva;
import com.cclip.model.person.Uf;
import com.thoughtworks.xstream.XStream;

public class MapperResultFind1 implements MapperResult {

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

		/* Titular */
		// dto.setTitularRegistration(rs.getString("titular_registration"));

		/* DNI Titular */
		// dto.setTitularRegistrationDni(rs.getString("titular_registration_dni"));

		/* CUIL/CUIT Titular */
		// dto.setTitularRegistrationCuit(rs
		// .getString("titular_registration_cuit"));

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
		dto.setInmNeighbourhoodComment(rs
				.getString("inm_neighbourhood_comment"));

		/* Calle */
		dto.setInmStreet(rs.getString("inm_street"));

		/* Número de Calle */
		dto.setInmStreetNumber(rs.getString("inm_street_number"));

		/* Número de Calle Estimado */
		dto.setInmStreetNumberEstimated(rs
				.getBoolean("inm_street_number_estimated"));

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

		/* País del Titular - Código ISO 3166-1 Alfa-2 */
		// dto.setTitularCountryCode(rs.getString("titular_country_code"));

		/* País del Titular */
		// dto.setTitularCountry(rs.getString("titular_country"));

		/* Provincia del Titular - Código ISO 3166-2 */
		// dto.setTitularAdminAreaLevel1Code(rs
		// .getString("titular_admin_area_level1_code"));

		/* Provincia del Titular */
		// dto.setTitularAdminAreaLevel1(rs.getString("titular_admin_area_level1"));

		/* Departamento / Distrito del Titular */
		// dto.setTitularAdminAreaLevel2(rs.getString("titular_admin_area_level2"));

		/* Localidad del Titular */
		// dto.setTitularLocality(rs.getString("titular_locality"));

		/* Vecindario del Titular */
		// dto.setTitularNeighbourhood(rs.getString("titular_neighbourhood"));

		/* Calle del Titular */
		// dto.setTitularStreet(rs.getString("titular_street"));

		/* Número de Calle del Titular */
		// dto.setTitularStreetNumber(rs.getString("titular_street_number"));

		/* Planta del Edificio del Titular */
		// dto.setTitularBuildingFloor(rs.getString("titular_building_floor"));

		/* Departamento de Edificio del Titular */
		// dto.setTitularBuildingRoom(rs.getString("titular_building_room"));

		/* Edificio del Titular */
		// dto.setTitularBuilding(rs.getString("titular_building"));

		/* Código Postal del Titular */
		// dto.setTitularPostalCode(rs.getString("titular_postal_code"));

		/* Comentario del Domicilio del Titular */
		// dto.setTitularCommentAddress(rs.getString("titular_comment_address"));

		/* Latitud del Titular */
		// dto.setTitularLat(rs.getDouble("titular_lat"));

		/* Longitud del Titular */
		// dto.setTitularLng(rs.getDouble("titular_lng"));

		/* Teléfono del Titular */
		// dto.setTitularPhone(rs.getString("titular_phone"));

		/* País del Titular - Código ISO 3166-1 Alfa-2 */
		dto.setUserPostalCountryCode(rs.getString("user_postal_country_code"));

		/* País del Usuario */
		dto.setUserPostalCountry(rs.getString("user_postal_country"));

		/* Provincia del Usuario - Código ISO 3166-2 */
		dto.setUserPostalAdminAreaLevel1Code(rs
				.getString("user_postal_admin_area_level1_code"));

		/* Provincia del Usuario */
		dto.setUserPostalAdminAreaLevel1(rs
				.getString("user_postal_admin_area_level1"));

		/* Departamento / Distrito del Usuario */
		dto.setUserPostalAdminAreaLevel2(rs
				.getString("user_postal_admin_area_level2"));

		/* Localidad del Usuario */
		dto.setUserPostalLocality(rs.getString("user_postal_locality"));

		/* Vecindario del Usuario */
		dto.setUserPostalNeighbourhood(rs
				.getString("user_postal_neighbourhood"));

		/* Calle del Usuario */
		dto.setUserPostalStreet(rs.getString("user_postal_street"));

		/* Número de Calle del Usuario */
		dto.setUserPostalStreetNumber(rs.getString("user_postal_street_number"));

		/* Planta del Edificio del Usuario */
		dto.setUserPostalBuildingFloor(rs
				.getString("user_postal_building_floor"));

		/* Departamento de Edificio del Usuario */
		dto.setUserPostalBuildingRoom(rs.getString("user_postal_building_room"));

		/* Edificio del Usuario */
		dto.setUserPostalBuilding(rs.getString("user_postal_building"));

		/* Código Postal del Usuario */
		dto.setUserPostalPostalCode(rs.getString("user_postal_postal_code"));

		/* Comentario del Domicilio del Usuario */
		dto.setUserPostalCommentAddress(rs
				.getString("user_postal_comment_address"));

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

		/* Fecha de última Modificación por Censista */
		// dto.setFua(rs.getTimestamp("fua"));

		/* auditDateCreate */
		dto.setAuditDateCreate(rs.getTimestamp("audit_date_create"));

		/* auditUserCreate */
		dto.setAuditUserCreate(rs.getString("audit_person_create"));

		/* auditDateUpdate */
		dto.setAuditDateUpdate(rs.getTimestamp("audit_date_update"));

		/* auditUserUpdate */
		dto.setAuditUserUpdate(rs.getString("audit_person_update"));

		/* auditVersion */
		dto.setAuditVersion(rs.getLong("audit_version"));

		/* auditVersionCodeLabel */
		dto.setAuditVersionCodeLabel(rs.getString("audit_version_code_label"));

		/* auditVersionLabel */
		dto.setAuditVersionLabel(rs.getString("audit_version_label"));

		dto.setFact((rs.getBoolean("fact")));

		dto.setM2CoveredShared(rs.getDouble("m2_covered_shared"));
		dto.setM2CoveredExpanded(rs.getDouble("m2_covered_expanded"));
		dto.setM2Percent(rs.getDouble("m2_percent"));
		dto.setYearBuilding(rs.getInt("year_building"));

		// if (rs.getString("titular_iva_id") != null) {
		//
		// /* Condición de IVA */
		//
		// Iva dtoFk = new Iva();
		//
		// dtoFk.setId(rs.getString("titular_iva_id"));
		// dtoFk.setErased(rs.getBoolean("titular_iva_erased"));
		// dtoFk.setCode(rs.getString("titular_iva_code"));
		// dtoFk.setName(rs.getString("titular_iva_name"));
		// dtoFk.setComment(rs.getString("titular_iva_comment"));
		// dto.setTitularIva(dtoFk);
		//
		// }

		if (rs.getString("user_iva_id") != null) {

			/* Condición de IVA */

			Iva dtoFk = new Iva();

			dtoFk.setId(rs.getString("user_iva_id"));
			dtoFk.setErased(rs.getBoolean("user_iva_erased"));
			// dtoFk.setCode(rs.getString("user_iva_code"));
			dtoFk.setName(rs.getString("user_iva_name"));
			dtoFk.setComment(rs.getString("user_iva_comment"));
			dto.setUserIva(dtoFk);

		}

		if (rs.getString("user_water_situation_id") != null) {

			/* Situación de Usuario del Servicio */

			UserWaterSituation dtoFk = new UserWaterSituation();

			dtoFk.setId(rs.getString("user_water_situation_id"));
			dtoFk.setErased(rs.getBoolean("user_water_situation_erased"));
			// dtoFk.setCode(rs.getString("user_water_situation_code"));
			dtoFk.setName(rs.getString("user_water_situation_name"));
			dtoFk.setComment(rs.getString("user_water_situation_comment"));
			dto.setUserWaterSituation(dtoFk);

		}

		if (rs.getString("uf_id") != null) {

			/* Unidad de Facturación */

			Uf dtoFk = new Uf();

			dtoFk.setId(rs.getString("uf_id"));
			dtoFk.setErased(rs.getBoolean("uf_erased"));
			// dtoFk.setUf(rs.getString("uf_uf"));
			dtoFk.setName(rs.getString("uf_name"));
			dtoFk.setDni(rs.getString("uf_dni"));
			dtoFk.setCuit(rs.getString("uf_cuit"));
			dtoFk.setPhone(rs.getString("uf_phone"));
			dtoFk.setComment(rs.getString("uf_comment"));
			dtoFk.setCountryCode(rs.getString("uf_country_code"));
			dtoFk.setCountry(rs.getString("uf_country"));
			dtoFk.setAdminAreaLevel1Code(rs
					.getString("uf_admin_area_level1_code"));
			dtoFk.setAdminAreaLevel1(rs.getString("uf_admin_area_level1"));
			dtoFk.setAdminAreaLevel2(rs.getString("uf_admin_area_level2"));
			dtoFk.setLocality(rs.getString("uf_locality"));
			// dtoFk.setNeighbourhoodComment(rs.getString("uf_neighbourhood"));
			dtoFk.setStreet(rs.getString("uf_street"));
			dtoFk.setStreetNumber(rs.getString("uf_street_number"));
			dtoFk.setBuildingFloor(rs.getString("uf_building_floor"));
			dtoFk.setBuildingRoom(rs.getString("uf_building_room"));
			dtoFk.setBuilding(rs.getString("uf_building"));
			// dtoFk.setPostalCode(rs.getString("uf_postal_code"));
			dtoFk.setCommentAddress(rs.getString("uf_comment_address"));
			dtoFk.setLat(rs.getDouble("uf_lat"));
			dtoFk.setLng(rs.getDouble("uf_lng"));
			dtoFk.setAuditDateCreate(rs.getTimestamp("uf_audit_date_create"));
			dtoFk.setAuditUserCreate(rs.getString("uf_audit_user_create"));
			dtoFk.setAuditDateUpdate(rs.getTimestamp("uf_audit_date_update"));
			dtoFk.setAuditUserUpdate(rs.getString("uf_audit_user_update"));
			dtoFk.setAuditVersion(rs.getLong("uf_audit_version"));
			dtoFk.setAuditVersionCodeLabel(rs
					.getString("uf_audit_version_code_label"));
			dtoFk.setAuditVersionLabel(rs.getString("uf_audit_version_label"));

			if (rs.getString("uf_iva_id") != null) {

				/* Condición de IVA */

				Iva dtoIvaFk = new Iva();

				dtoIvaFk.setId(rs.getString("uf_iva_id"));
				dtoIvaFk.setErased(rs.getBoolean("uf_iva_erased"));
				// dtoIvaFk.setCode(rs.getString("uf_iva_code"));
				dtoIvaFk.setName(rs.getString("uf_iva_name"));
				dtoIvaFk.setComment(rs.getString("uf_iva_comment"));
				dtoFk.setIva(dtoIvaFk);

			}

			if (rs.getString("uf_neighbourhood_id") != null) {

				/* Vecindario */

				Neighbourhood dtoNeighbourhoodFk = new Neighbourhood();

				dtoNeighbourhoodFk.setId(rs.getString("uf_neighbourhood_id"));
				dtoNeighbourhoodFk.setErased(rs
						.getBoolean("uf_neighbourhood_erased"));
				// dtoNeighbourhoodFk.setCode(rs.getString("uf_neighbourhood_code"));
				dtoNeighbourhoodFk.setName(rs
						.getString("uf_neighbourhood_name"));
				dtoNeighbourhoodFk.setComment(rs
						.getString("uf_neighbourhood_comment"));
				dtoFk.setNeighbourhood(dtoNeighbourhoodFk);
			}

			dto.setUf(dtoFk);

		}

		if (rs.getString("city_area_id") != null) {

			/* Area (Zona) de Ciudad */

			CityArea dtoFk = new CityArea();

			dtoFk.setId(rs.getString("city_area_id"));
			dtoFk.setErased(rs.getBoolean("city_area_erased"));
			// dtoFk.setCode(rs.getString("city_area_code"));
			dtoFk.setName(rs.getString("city_area_name"));
			dtoFk.setComment(rs.getString("city_area_comment"));
			dto.setCityArea(dtoFk);

		}

		if (rs.getString("cadastre_type_id") != null) {

			/* Tipo de Catastro */

			CadastreType dtoFk = new CadastreType();

			dtoFk.setId(rs.getString("cadastre_type_id"));
			dtoFk.setErased(rs.getBoolean("cadastre_type_erased"));
			// dtoFk.setCode(rs.getString("cadastre_type_code"));
			dtoFk.setName(rs.getString("cadastre_type_name"));
			dtoFk.setComment(rs.getString("cadastre_type_comment"));
			dto.setCadastreType(dtoFk);

		}

		if (rs.getString("cadastre_situation_id") != null) {

			/* Situación de Catastro */

			CadastreSituation dtoFk = new CadastreSituation();

			dtoFk.setId(rs.getString("cadastre_situation_id"));
			dtoFk.setErased(rs.getBoolean("cadastre_situation_erased"));
			// dtoFk.setCode(rs.getString("cadastre_situation_code"));
			dtoFk.setName(rs.getString("cadastre_situation_name"));
			dtoFk.setComment(rs.getString("cadastre_situation_comment"));
			dto.setCadastreSituation(dtoFk);

		}

		if (rs.getString("neighbourhood_id") != null) {

			/* Vecindario */

			Neighbourhood dtoFk = new Neighbourhood();

			dtoFk.setId(rs.getString("neighbourhood_id"));
			dtoFk.setErased(rs.getBoolean("neighbourhood_erased"));
			// dtoFk.setCode(rs.getString("neighbourhood_code"));
			dtoFk.setName(rs.getString("neighbourhood_name"));
			dtoFk.setComment(rs.getString("neighbourhood_comment"));
			dto.setInmNeighbourhood(dtoFk);

			// if (dtoFk.getCode() != null && dtoFk.getCode().trim().length() >
			// 4) {
			// String pc = dtoFk.getCode().trim().substring(0, 4);
			//
			// //
			// dto.setInmPostalCode(pc);
			// }

			if (dtoFk.getId() != null && dtoFk.getId().trim().length() > 4) {
				String pc = dtoFk.getId().trim().substring(0, 4);

				//
				dto.setInmPostalCode(pc);
			}
		}

		if (rs.getString("cadastre_sub_division_type_id") != null) {

			/* Tipo de PH */

			CadastreSubDivisionType dtoFk = new CadastreSubDivisionType();

			dtoFk.setId(rs.getString("cadastre_sub_division_type_id"));
			dtoFk.setErased(rs.getBoolean("cadastre_sub_division_type_erased"));
			// dtoFk.setCode(rs.getString("cadastre_sub_division_type_code"));
			dtoFk.setName(rs.getString("cadastre_sub_division_type_name"));
			dtoFk.setComment(rs.getString("cadastre_sub_division_type_comment"));
			dto.setCadastreSubDivisionType(dtoFk);

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

		// if (rs.getString("cadastre_constructive_type_id") != null) {
		//
		// /* Tipo de Actividad */
		//
		// CadastreConstructiveType dtoFk = new CadastreConstructiveType();
		//
		// dtoFk.setId(rs.getString("cadastre_constructive_type_id"));
		// dtoFk.setErased(rs.getBoolean("cadastre_constructive_type_erased"));
		// // dtoFk.setCode(rs.getString("cadastre_activity_type_code"));
		// dtoFk.setName(rs.getString("cadastre_constructive_type_name"));
		// dtoFk.setComment(rs.getString("cadastre_constructive_type_comment"));
		// dto.setCadastreConstructiveType(dtoFk);
		//
		// }

		return dto;
	}
}
