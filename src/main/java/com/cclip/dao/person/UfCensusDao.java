package com.cclip.dao.person;

import com.cclip.model.person.Iva;
import com.cclip.model.geo.Neighbourhood;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.utiljdbc.ConnectionWrapper;
import org.utiljdbc.MapperQueryArg;
import org.utiljdbc.MapperQueryOrderArg;
import org.utiljdbc.MapperResult;
import org.utiljdbc.ResultList;
import org.utiljdbc.UtilJdbcOld;

import com.cclip.model.person.UfCensus;

/** DAO Unidad de Facturación */
public class UfCensusDao {

	protected UtilJdbcOld utilJdbc;

	public void setUtilJdbc(UtilJdbcOld utilJdbc) {
		this.utilJdbc = utilJdbc;
	}

	public UfCensus findById(ConnectionWrapper connectionWrapper, String id) {

		UfCensus r = (UfCensus) utilJdbc.findById(connectionWrapper, "cclip.uf_census", id, new MapperResultFind());

		//System.out.println(connectionWrapper.getSql()); // Con esta linea podemos ver todos los sql que se ejecutaron con la misma conexión.

		return r;

	}

	public UfCensus[] find(ConnectionWrapper connectionWrapper) {

		return find(connectionWrapper, new MapperQueryOrderArg[0]);

	}

	public UfCensus[] find(ConnectionWrapper connectionWrapper, MapperQueryOrderArg[] orderList) {

		Object[] r = utilJdbc.find(connectionWrapper, "cclip.uf_census", new MapperResultFind(), orderList);

		UfCensus[] resultList = new UfCensus[r.length];

		for (int i = 0; i < r.length; i++) {

			resultList[i] = (UfCensus) r[i];

		}

		return resultList;

	}

	public UfCensus[] find(ConnectionWrapper connectionWrapper, MapperQueryArg[] argList, MapperQueryOrderArg[] orderList) {

		Object[] r = utilJdbc.findByExample(connectionWrapper, "cclip.uf_census", argList, new MapperResultFind(), orderList);

		UfCensus[] resultList = new UfCensus[r.length];

		for (int i = 0; i < r.length; i++) {

			resultList[i] = (UfCensus) r[i];

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

		ResultList r = utilJdbc.findByExamplePageable(connectionWrapper, "cclip.uf_census", argList, new MapperResultFind(), offSet, limit, orderList);

		return r;

	}

	public class MapperResultFind implements MapperResult {

		public Object mapRow(ResultSet rs) throws SQLException {

			UfCensus dto = new UfCensus();

			/* id */
			dto.setId(rs.getString("id"));

			/* erased */
			dto.setErased(rs.getBoolean("erased"));

			/* Nombre */
			dto.setName(rs.getString("name"));

			/* DNI */
			dto.setDni(rs.getString("dni"));

			/* CUIL/CUIT */
			dto.setCuit(rs.getString("cuit"));

			/* Teléfono */
			dto.setPhone(rs.getString("phone"));

			/* Comentario */
			dto.setComment(rs.getString("comment"));

			/* País - Código ISO 3166-1 Alfa-2 */
			dto.setCountryCode(rs.getString("country_code"));

			/* País */
			dto.setCountry(rs.getString("country"));

			/* Provincia - Código ISO 3166-2 */
			dto.setAdminAreaLevel1Code(rs.getString("admin_area_level1_code"));

			/* Provincia */
			dto.setAdminAreaLevel1(rs.getString("admin_area_level1"));

			/* Departamento / Distrito */
			dto.setAdminAreaLevel2(rs.getString("admin_area_level2"));

			/* Localidad */
			dto.setLocality(rs.getString("locality"));

			/* Calle */
			dto.setStreet(rs.getString("street"));

			/* Número de Calle */
			dto.setStreetNumber(rs.getString("street_number"));

			/* Planta del Edificio */
			dto.setBuildingFloor(rs.getString("building_floor"));

			/* Departamento de Edificio */
			dto.setBuildingRoom(rs.getString("building_room"));

			/* Edificio */
			dto.setBuilding(rs.getString("building"));

			/* Comentario del Domicilio */
			dto.setCommentAddress(rs.getString("comment_address"));

			/* Latitud */
			dto.setLat(rs.getDouble("lat"));

			/* Longitud */
			dto.setLng(rs.getDouble("lng"));

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

			if(rs.getString("iva_id") != null) { 

				/* Condición de IVA */

				Iva dtoFk = new Iva();

				dtoFk.setId(rs.getString("iva_id"));
				dto.setIva(dtoFk);

			}

			if(rs.getString("neighbourhood_id") != null) { 

				/* Vecindario */

				Neighbourhood dtoFk = new Neighbourhood();

				dtoFk.setId(rs.getString("neighbourhood_id"));
				dto.setNeighbourhood(dtoFk);

			}

			return dto;
		}
	}
}
