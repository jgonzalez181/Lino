package com.cclip.dao.schedule.census;

import com.cclip.model.geo.cadastre.Cadastre;
import com.cclip.model.geo.cadastre.CadastreCensus;
import com.cclip.model.schedule.census.ScheduleCensus;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.utiljdbc.ConnectionWrapper;
import org.utiljdbc.MapperQueryArg;
import org.utiljdbc.MapperQueryOrderArg;
import org.utiljdbc.MapperResult;
import org.utiljdbc.ResultList;
import org.utiljdbc.UtilJdbcOld;

import com.cclip.model.schedule.census.ScheduleCensusItem;

/** DAO Item Censo de Parcela */
public class ScheduleCensusItemDao {

	protected UtilJdbcOld utilJdbc;

	public void setUtilJdbc(UtilJdbcOld utilJdbc) {
		this.utilJdbc = utilJdbc;
	}

	public ScheduleCensusItem findById(ConnectionWrapper connectionWrapper, String id) {

		ScheduleCensusItem r = (ScheduleCensusItem) utilJdbc.findById(connectionWrapper, "cclip.schedule_census_item", id, new MapperResultFind());

		//System.out.println(connectionWrapper.getSql()); // Con esta linea podemos ver todos los sql que se ejecutaron con la misma conexión.

		return r;

	}

	public ScheduleCensusItem[] find(ConnectionWrapper connectionWrapper) {

		return find(connectionWrapper, new MapperQueryOrderArg[0]);

	}

	public ScheduleCensusItem[] find(ConnectionWrapper connectionWrapper, MapperQueryOrderArg[] orderList) {

		Object[] r = utilJdbc.find(connectionWrapper, "cclip.schedule_census_item", new MapperResultFind(), orderList);

		ScheduleCensusItem[] resultList = new ScheduleCensusItem[r.length];

		for (int i = 0; i < r.length; i++) {

			resultList[i] = (ScheduleCensusItem) r[i];

		}

		return resultList;

	}

	public ScheduleCensusItem[] find(ConnectionWrapper connectionWrapper, MapperQueryArg[] argList, MapperQueryOrderArg[] orderList) {

		Object[] r = utilJdbc.findByExample(connectionWrapper, "cclip.schedule_census_item", argList, new MapperResultFind(), orderList);

		ScheduleCensusItem[] resultList = new ScheduleCensusItem[r.length];

		for (int i = 0; i < r.length; i++) {

			resultList[i] = (ScheduleCensusItem) r[i];

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

		ResultList r = utilJdbc.findByExamplePageable(connectionWrapper, "cclip.schedule_census_item", argList, new MapperResultFind(), offSet, limit, orderList);

		return r;

	}

	public class MapperResultFind implements MapperResult {

		public Object mapRow(ResultSet rs) throws SQLException {

			ScheduleCensusItem dto = new ScheduleCensusItem();

			/* id */
			dto.setId(rs.getString("id"));

			/* erased */
			dto.setErased(rs.getBoolean("erased"));

			if(rs.getString("cadastre_id") != null) { 

				/* Catastro de Parcela de Tierra */

				Cadastre dtoFk = new Cadastre();

				dtoFk.setId(rs.getString("cadastre_id"));
				dto.setCadastre(dtoFk);

			}

			if(rs.getString("cadastre_census_id") != null) { 

				/* Catastro de Parcela de Tierra */

				CadastreCensus dtoFk = new CadastreCensus();

				dtoFk.setId(rs.getString("cadastre_census_id"));
				dto.setCadastreCensus(dtoFk);

			}

			if(rs.getString("schedule_census_id") != null) { 

				/* Censo de Parcela */

				ScheduleCensus dtoFk = new ScheduleCensus();

				dtoFk.setId(rs.getString("schedule_census_id"));
				dto.setScheduleCensus(dtoFk);

			}

			return dto;
		}
	}
}
