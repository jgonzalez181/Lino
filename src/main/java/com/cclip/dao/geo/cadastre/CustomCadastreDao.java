package com.cclip.dao.geo.cadastre;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.utiljdbc.ConnectionWrapper;
import org.utiljdbc.MapperQueryArg;
import org.utiljdbc.MapperQueryOrderArg;
import org.utiljdbc.MapperResult;
import org.utiljdbc.ResultList;

import com.cclip.model.geo.cadastre.Cadastre;

/** DAO Catastro de Parcela de Tierra */
public class CustomCadastreDao extends CadastreDao {

	public ResultList find1(ConnectionWrapper connectionWrapper, MapperQueryArg[] argList, MapperQueryOrderArg[] orderList, Integer offSet, Integer limit) {

		ResultList r = utilJdbc.findByExamplePageable(connectionWrapper, "cclip.v_cadastre", argList, new MapperResultFind1(), offSet, limit, orderList);

		return r;

	}

	public ResultList find1Aacc(ConnectionWrapper connectionWrapper, MapperQueryArg[] argList, MapperQueryOrderArg[] orderList, Integer offSet, Integer limit, boolean nov) {

		ResultList r = null;

		if (nov) {
			r = utilJdbc.findByExamplePageable(connectionWrapper, "cclip.v_cadastre_aacc_nov", argList, new MapperResultFind1(), offSet, limit, orderList);
		} else {
			r = utilJdbc.findByExamplePageable(connectionWrapper, "cclip.v_cadastre_aacc", argList, new MapperResultFind1(), offSet, limit, orderList);
		}

		return r;

	}

	public Cadastre[] find1Aacc(ConnectionWrapper connectionWrapper, MapperQueryArg[] argList, MapperQueryOrderArg[] orderList, boolean nov) {

		Object[] r = null;

		if (nov) {
			r = utilJdbc.findByExample(connectionWrapper, "cclip.v_cadastre_aacc_nov", argList, new MapperResultFind1(), orderList);
		} else {
			r = utilJdbc.findByExample(connectionWrapper, "cclip.v_cadastre_aacc", argList, new MapperResultFind1(), orderList);
		}

		Cadastre[] resultList = new Cadastre[r.length];

		for (int i = 0; i < r.length; i++) {

			resultList[i] = (Cadastre) r[i];
		}

		return resultList;

	}

	public Cadastre[] find2(ConnectionWrapper connectionWrapper, String cadastralCode) {

		MapperQueryArg[] argList = null;

		if (cadastralCode != null && cadastralCode.trim().length() > 0) {

			argList = new MapperQueryArg[1];

			MapperQueryArg a = new MapperQueryArg();
			a.setBeginsWith("cadastral_code", cadastralCode.trim());

			argList[0] = a;

		} else {
			return new Cadastre[0];
		}

		MapperQueryOrderArg[] orderList = {  new MapperQueryOrderArg("cadastral_code", true) /*, new MapperQueryOrderArg("cta_cli", true) */};

		// Object[] r = utilJdbc.findByExample(connectionWrapper,
		// "cclip.v_cadastre", argList, new MapperResultFind1(), orderList);

		Object[] r = utilJdbc.findByExample(connectionWrapper, "cclip.v_cadastre_for_build_schedule_scanning", argList, new MapperResultFind4(), orderList);

		Cadastre[] resultList = new Cadastre[r.length];

		for (int i = 0; i < r.length; i++) {

			resultList[i] = (Cadastre) r[i];
		}

		return resultList;

	}

	public Cadastre[] findByCadastralCode(ConnectionWrapper connectionWrapper, String cadastralCode) {

		MapperQueryArg[] argList = null;

		if (cadastralCode != null && cadastralCode.trim().length() > 0) {

			argList = new MapperQueryArg[1];

			MapperQueryArg a = new MapperQueryArg();
			a.setEqualsTo("cadastral_code", cadastralCode.trim());

			argList[0] = a;

		} else {
			return new Cadastre[0];
		}

		MapperQueryOrderArg[] orderList = { new MapperQueryOrderArg("cadastral_code", true) };

		Object[] r = utilJdbc.findByExample(connectionWrapper, "cclip.v_cadastre", argList, new MapperResultFind1(), orderList);

		Cadastre[] resultList = new Cadastre[r.length];

		for (int i = 0; i < r.length; i++) {

			resultList[i] = (Cadastre) r[i];
		}

		return resultList;

	}

	public Cadastre[] findByBeginsWithCadastralCode(ConnectionWrapper connectionWrapper, String cadastralCode) {

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

		Object[] r = utilJdbc.findByExample(connectionWrapper, "cclip.v_cadastre", argList, new MapperResultFind1(), orderList);

		Cadastre[] resultList = new Cadastre[r.length];

		for (int i = 0; i < r.length; i++) {

			resultList[i] = (Cadastre) r[i];
		}

		return resultList;

	}

	
	public String[] find3(ConnectionWrapper connectionWrapper, String cadastralCode) {

		if (cadastralCode != null && cadastralCode.trim().length() > 0) {

		} else {
			return new String[0];
		}

		MapperQueryArg[] argList = new MapperQueryArg[0];
		MapperQueryOrderArg[] orderList = new MapperQueryOrderArg[0];

		String sql = "SELECT DISTINCT substring(cadastral_code FROM 0 FOR 8) AS code FROM cclip.cadastre WHERE cadastral_code LIKE '" + cadastralCode.trim() + "%' "
				+ "ORDER BY substring(cadastral_code FROM 0 FOR 8) ASC";

		Object[] r = utilJdbc.findByExample(connectionWrapper, sql, argList, new MapperResultFind3(), orderList);

		String[] resultList = new String[r.length];

		for (int i = 0; i < r.length; i++) {

			resultList[i] = (String) r[i];
		}

		return resultList;

	}

	public class MapperResultFind3 implements MapperResult {

		public Object mapRow(ResultSet rs) throws SQLException {

			return rs.getString("code");
		}
	}

	public class MapperResultFind4 implements MapperResult {

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

			return dto;
		}
	}

}
