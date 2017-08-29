package com.cclip.dao.schedule.scanning;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.utiljdbc.ConnectionWrapper;
import org.utiljdbc.MapperQueryArg;
import org.utiljdbc.MapperQueryOrderArg;
import org.utiljdbc.MapperResult;
import org.utiljdbc.UtilJdbcOld;

import com.cclip.model.schedule.scanning.ScheduleScanningResultTotal;

/** DAO Resultado de Barrido */
public class ScheduleScanningResultTotalDao {

	protected UtilJdbcOld utilJdbc;

	public void setUtilJdbc(UtilJdbcOld utilJdbc) {
		this.utilJdbc = utilJdbc;
	}

	public ScheduleScanningResultTotal[] find(
			ConnectionWrapper connectionWrapper, MapperQueryArg[] argList,
			MapperQueryOrderArg[] orderList) {

		Object[] r = utilJdbc.findByExample(connectionWrapper,
				"cclip.v_schedule_scanning_result_Total", argList,
				new MapperResultFind(), orderList);

		ScheduleScanningResultTotal[] resultList = new ScheduleScanningResultTotal[r.length];

		for (int i = 0; i < r.length; i++) {

			resultList[i] = (ScheduleScanningResultTotal) r[i];

		}

		return resultList;

	}

	public class MapperResultFind implements MapperResult {

		public Object mapRow(ResultSet rs) throws SQLException {

			ScheduleScanningResultTotal dto = new ScheduleScanningResultTotal();

			/* id */
			dto.setId(rs.getString("id"));

			/* erased */
			dto.setErased(rs.getBoolean("erased"));

			/* Código */
			dto.setCode(rs.getString("code"));

			/* Nombre */
			dto.setName(rs.getString("name"));

			/* Comentario */
			dto.setComment(rs.getString("comment"));

			/* Total */
			dto.setTotal(rs.getInt("total"));

			return dto;
		}
	}
}
