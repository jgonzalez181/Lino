package org.utiljdbc;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.utiljdbcdao.ex.ExBeginDao;
import org.utiljdbcdao.ex.ExCloseConnection;
import org.utiljdbcdao.ex.ExCommitDao;
import org.utiljdbcdao.ex.ExDeleteDao;
import org.utiljdbcdao.ex.ExFindDao;
import org.utiljdbcdao.ex.ExInsertDao;
import org.utiljdbcdao.ex.ExRollBackDao;
import org.utiljdbcdao.ex.ExSqlVarQueryEmptyDao;
import org.utiljdbcdao.ex.ExSqlVarQueryNullDao;
import org.utiljdbcdao.ex.ExUnexpectedResult;
import org.utiljdbcdao.ex.ExUpdateDao;

public class UtilJdbcByList {

	// /////////////////////////////////////////////////////////////

	public void close(ConnectionWrapper connectionWrapper) {
		try {
			if (connectionWrapper.getConnection() != null
					& connectionWrapper.getConnection().isClosed() == false) {
				connectionWrapper.getConnection().close();
			}

			connectionWrapper.add("close();");
		} catch (SQLException e2) {
			ExCloseConnection ex2 = new ExCloseConnection(this.getClass(), e2,
					connectionWrapper);

			ex2.setFirstTrace(e2, this.getClass());

		}

	}

	public void begin(ConnectionWrapper connectionWrapper) {
		try {
			connectionWrapper.getConnection().setAutoCommit(false);
			connectionWrapper.add("begin();");
		} catch (Exception e) {

			ExBeginDao ex = new ExBeginDao(this.getClass(), e,
					connectionWrapper);

			ex.setFirstTrace(e, this.getClass());

			throw ex;
		}

	}

	public void commit(ConnectionWrapper connectionWrapper) {
		try {

			connectionWrapper.getConnection().commit();
			connectionWrapper.add("commit();");

			close(connectionWrapper);

		} catch (Exception e) {

			ExCommitDao ex = new ExCommitDao(this.getClass(), e,
					connectionWrapper);

			ex.setFirstTrace(e, this.getClass());

			throw ex;
		}

	}

	public void rollBack(ConnectionWrapper connectionWrapper) {
		try {

			connectionWrapper.getConnection().rollback();
			connectionWrapper.add("rollback();");

			close(connectionWrapper);

		} catch (Exception e) {

			ExRollBackDao ex = new ExRollBackDao(this.getClass(), e,
					connectionWrapper);

			ex.setFirstTrace(e, this.getClass());

			throw ex;
		}

	}

	public int delete(ConnectionWrapper connectionWrapper, String table) {
		try {

			String sql = "DELETE FROM " + table + ";";

			connectionWrapper.add(sql);

			return executeUpdate(sql, connectionWrapper.getConnection()
					.createStatement());

		} catch (Exception e) {
			ExDeleteDao ex = new ExDeleteDao(this.getClass(), e,
					connectionWrapper);

			ex.setFirstTrace(e, this.getClass());

			

			throw ex;
		}

	}

	public int delete(ConnectionWrapper connectionWrapper, String table,
			List<MapperQueryArg> mapperWhereArgList) {
		try {

			String sql = "DELETE FROM " + table + " WHERE ";

			for (int i = 0; i < mapperWhereArgList.size(); i++) {
				MapperQueryArg mapperQueryArg = mapperWhereArgList.get(i);
				if (i == 0) {
					sql += mapperQueryArg.getSql();
				} else {
					sql += " AND " + mapperQueryArg.getSql();
				}
			}

			sql += ";";

			PreparedStatement preparedStatement = connectionWrapper
					.getConnection().prepareStatement(sql);

			for (int i = 0; i < mapperWhereArgList.size(); i++) {
				MapperQueryArg mapperQueryArg = mapperWhereArgList.get(i);
				int r = i + 1;
				set(preparedStatement, mapperQueryArg.getValue(), r);
			}

			sql = preparedStatement.toString();

			connectionWrapper.add(sql);

			return executeUpdateByExample(preparedStatement);

		} catch (Exception e) {
			ExDeleteDao ex = new ExDeleteDao(this.getClass(), e,
					connectionWrapper);

			ex.setFirstTrace(e, this.getClass());

			

			throw ex;
		}

	}

	public int update(ConnectionWrapper connectionWrapper, String table,
			List<MapperUpdateArg> mapperUpdateArgList) {
		try {

			String sql = "UPDATE " + table + " set ";

			for (int i = 0; i < mapperUpdateArgList.size(); i++) {
				MapperUpdateArg mapperUpdateArg = mapperUpdateArgList.get(i);
				if (i == 0) {
					sql += mapperUpdateArg.getTableAtt() + " = ?";
				} else {
					sql += ", " + mapperUpdateArg.getTableAtt() + " = ?";
				}
			}

			sql += ";";

			PreparedStatement preparedStatement = connectionWrapper
					.getConnection().prepareStatement(sql);

			for (int i = 0; i < mapperUpdateArgList.size(); i++) {
				MapperUpdateArg mapperUpdateArg = mapperUpdateArgList.get(i);
				int r = i + 1;
				if (mapperUpdateArg.getValue() == null) {
					preparedStatement.setNull(r, mapperUpdateArg.getClazz());
				} else {
					set(preparedStatement, mapperUpdateArg.getValue(), r);
				}
			}

			sql = preparedStatement.toString();

			connectionWrapper.add(sql);

			return executeUpdateByExample(preparedStatement);

		} catch (Exception e) {
			ExUpdateDao ex = new ExUpdateDao(this.getClass(), e,
					connectionWrapper);

			ex.setFirstTrace(e, this.getClass());

		

			throw ex;
		}

	}

	public int update(ConnectionWrapper connectionWrapper, String table,
			List<MapperUpdateArg> mapperUpdateArgList,
			List<MapperQueryArg> mapperWhereArgList) {
		try {

			String sql = "UPDATE " + table + " set ";

			for (int i = 0; i < mapperUpdateArgList.size(); i++) {
				MapperUpdateArg mapperUpdateArg = mapperUpdateArgList.get(i);
				if (i == 0) {
					sql += mapperUpdateArg.getTableAtt() + " = ?";
				} else {
					sql += ", " + mapperUpdateArg.getTableAtt() + " = ?";
				}
			}
			sql += " WHERE ";

			for (int i = 0; i < mapperWhereArgList.size(); i++) {
				MapperQueryArg mapperQueryArg = mapperWhereArgList.get(i);
				if (i == 0) {
					sql += mapperQueryArg.getSql();
				} else {
					sql += " AND " + mapperQueryArg.getSql();
				}
			}

			sql += ";";

			PreparedStatement preparedStatement = connectionWrapper
					.getConnection().prepareStatement(sql);

			int c = 0;
			for (int i = 0; i < mapperUpdateArgList.size(); i++) {
				MapperUpdateArg mapperUpdateArg = mapperUpdateArgList.get(i);
				int r = i + 1;
				if (mapperUpdateArg.getValue() == null) {
					preparedStatement.setNull(r, mapperUpdateArg.getClazz());
				} else {
					set(preparedStatement, mapperUpdateArg.getValue(), r);
				}

				c = i;
			}

			c = c + 1;

			for (int i = 0; i < mapperWhereArgList.size(); i++) {
				MapperQueryArg mapperQueryArg = mapperWhereArgList.get(i);
				int r = c + i + 1;
				set(preparedStatement, mapperQueryArg.getValue(), r);
			}

			sql = preparedStatement.toString();

			connectionWrapper.add(sql);

			return executeUpdateByExample(preparedStatement);

		} catch (Exception e) {
			ExUpdateDao ex = new ExUpdateDao(this.getClass(), e,
					connectionWrapper);

			ex.setFirstTrace(e, this.getClass());

			

			throw ex;
		}

	}

	public int insert(ConnectionWrapper connectionWrapper, String table,
			List<MapperUpdateArg> mapperUpdateArgList) {
		try {

			String sql = "INSERT INTO " + table + "(";

			for (int i = 0; i < mapperUpdateArgList.size(); i++) {
				MapperUpdateArg mapperUpdateArg = mapperUpdateArgList.get(i);
				if (i == 0) {
					sql += mapperUpdateArg.getTableAtt();
				} else {
					sql += ", " + mapperUpdateArg.getTableAtt();
				}
			}
			sql += ") VALUES (";
			for (int i = 0; i < mapperUpdateArgList.size(); i++) {
				if (i == 0) {
					sql += "?";
				} else {
					sql += ", ?";
				}
			}
			sql += ");";

			PreparedStatement preparedStatement = connectionWrapper
					.getConnection().prepareStatement(sql);

			for (int i = 0; i < mapperUpdateArgList.size(); i++) {
				MapperUpdateArg mapperUpdateArg = mapperUpdateArgList.get(i);
				int r = i + 1;
				if (mapperUpdateArg.getValue() == null) {
					preparedStatement.setNull(r, mapperUpdateArg.getClazz());
				} else {
					set(preparedStatement, mapperUpdateArg.getValue(), r);
				}
			}

			sql = preparedStatement.toString();

			connectionWrapper.add(sql);

			return executeUpdateByExample(preparedStatement);

		} catch (Exception e) {
			ExInsertDao ex = new ExInsertDao(this.getClass(), e,
					connectionWrapper);

			ex.setFirstTrace(e, this.getClass());

			

			throw ex;
		}

	}

	// /////////////////////////////////////////////////////////////

	public List<Object> find(ConnectionWrapper connectionWrapper, String table,
			MapperResult mapperResult) {
		return find(connectionWrapper, table, mapperResult, null);
	}

	public List<Object> find(ConnectionWrapper connectionWrapper, String table,
			MapperResult mapperResult,
			List<MapperQueryOrderArg> mapperQueryOrderArgList) {

		try {

			String orderSql = "";
			if (mapperQueryOrderArgList != null) {
				for (int i = 0; i < mapperQueryOrderArgList.size(); i++) {
					MapperQueryOrderArg mapperQueryOrderArg = mapperQueryOrderArgList
							.get(i);
					if (i == 0) {
						orderSql += " ORDER BY " + mapperQueryOrderArg.getSql();
					} else {
						orderSql += ", " + mapperQueryOrderArg.getSql();
					}
				}
			}

			String sql = "SELECT * FROM " + table + orderSql + ";";

			if (table.toUpperCase().trim().startsWith("SELECT")) {
				sql = table + orderSql + ";";
			} else {
				sql = "SELECT * FROM " + table + orderSql + ";";
			}

			connectionWrapper.add(sql);

			Statement statement = connectionWrapper.getConnection()
					.createStatement();

			return executeQuery(sql, statement, mapperResult);

		} catch (Exception e) {
			ExFindDao ex = new ExFindDao(this.getClass(), e, connectionWrapper);

			ex.setFirstTrace(e, this.getClass());

			
			throw ex;
		}

	}

	public Object findById(ConnectionWrapper connectionWrapper, String table,
			String id, MapperResult mapperResult) {
		return findById(connectionWrapper, table, "id", id, mapperResult);
	}

	public Object findById(ConnectionWrapper connectionWrapper, String table,
			String attTable, String id, MapperResult mapperResult) {

		List<MapperQueryArg> mapperArgList = new ArrayList<MapperQueryArg>();
		MapperQueryArg mapperQueryArg = new MapperQueryArg();
		mapperQueryArg.setEqualsTo(attTable, id);
		mapperArgList.add(mapperQueryArg);

		List<Object> listT = findByExample(connectionWrapper, table,
				mapperArgList, mapperResult);

		if (listT.size() == 1) {

			return listT.get(0);
		}

		ExUnexpectedResult e = new ExUnexpectedResult(this.getClass(),
				listT.size());

		ExFindDao ex = new ExFindDao(this.getClass(), e, connectionWrapper);

		ex.setFirstTrace(e, this.getClass());

		

		throw ex;

	}

	public List<Object> findByExample(ConnectionWrapper connectionWrapper,
			String table, List<MapperQueryArg> mapperQueryArgList,
			MapperResult mapperResult) {

		return findByExample(connectionWrapper, table, mapperQueryArgList,
				mapperResult, null);
	}

	public List<Object> findByExample(ConnectionWrapper connectionWrapper,
			String table, List<MapperQueryArg> mapperQueryArgList,
			MapperResult mapperResult,
			List<MapperQueryOrderArg> mapperQueryOrderArgList) {

		try {

			String sqlWhere = "";

			for (int i = 0; i < mapperQueryArgList.size(); i++) {
				MapperQueryArg mapperQueryArg = mapperQueryArgList.get(i);
				if (i == 0) {
					sqlWhere += mapperQueryArg.getSql();
				} else {
					sqlWhere += " AND " + mapperQueryArg.getSql();
				}
			}

			String orderSql = "";
			if (mapperQueryOrderArgList != null) {
				for (int i = 0; i < mapperQueryOrderArgList.size(); i++) {
					MapperQueryOrderArg mapperQueryOrderArg = mapperQueryOrderArgList
							.get(i);
					if (i == 0) {
						orderSql += " ORDER BY " + mapperQueryOrderArg.getSql();
					} else {
						orderSql += "," + mapperQueryOrderArg.getSql();
					}
				}
			}

			String sql = "SELECT * FROM " + table + " WHERE " + sqlWhere
					+ orderSql + ";";

			if (table.toUpperCase().trim().startsWith("SELECT")) {
				sql = table + ";";
			} else {
				sql = "SELECT * FROM " + table + " WHERE " + sqlWhere
						+ orderSql + ";";
			}

			PreparedStatement preparedStatement = connectionWrapper
					.getConnection().prepareStatement(sql);

			for (int i = 0; i < mapperQueryArgList.size(); i++) {
				MapperQueryArg mapperQueryArg = mapperQueryArgList.get(i);
				int r = i + 1;
				set(preparedStatement, mapperQueryArg.getValue(), r);
			}

			sql = preparedStatement.toString();

			connectionWrapper.add(sql);

			// System.out.println(connectionWrapper.getSql()); // Con esta linea
			// podemos ver todos los sql que se ejecutaron con la misma
			// conexión.

			return executeQueryByExample(preparedStatement, mapperResult);

		} catch (Exception e) {
			ExFindDao ex = new ExFindDao(this.getClass(), e, connectionWrapper);

			ex.setFirstTrace(e, this.getClass());

			throw ex;
		}

	}

	public ResultList findPageable(ConnectionWrapper connectionWrapper,
			String table, MapperResult mapperResult, Integer offSet,
			Integer limit) {
		return findPageable(connectionWrapper, table, mapperResult, offSet,
				limit, null);
	}

	public ResultList findPageable(ConnectionWrapper connectionWrapper,
			String table, MapperResult mapperResult, Integer offSet,
			Integer limit, List<MapperQueryOrderArg> mapperQueryOrderArgList) {

		try {

			if (offSet == null) {
				throw new RuntimeException(
						"El argumento offSet no puede ser nulo.");
			}
			if (limit == null) {
				throw new RuntimeException(
						"El argumento limit no puede ser nulo.");
			}

			if (offSet < 0) {
				throw new RuntimeException(
						"El argumento offSet no puede ser menor a 0.");
			}
			if (limit < 0) {
				throw new RuntimeException(
						"El argumento limit no puede ser menor a 0.");
			}

			String orderSql = "";
			if (mapperQueryOrderArgList != null) {
				for (int i = 0; i < mapperQueryOrderArgList.size(); i++) {
					MapperQueryOrderArg mapperQueryOrderArg = mapperQueryOrderArgList
							.get(i);
					if (i == 0) {
						orderSql += " ORDER BY " + mapperQueryOrderArg.getSql();
					} else {
						orderSql += "," + mapperQueryOrderArg.getSql();
					}
				}
			}

			String sql = "SELECT * FROM " + table + orderSql
					+ " OFFSET ? LIMIT ?;";

			PreparedStatement preparedStatement = connectionWrapper
					.getConnection().prepareStatement(sql);

			preparedStatement.setInt(1, offSet);
			preparedStatement.setInt(2, limit);

			sql = preparedStatement.toString();

			connectionWrapper.add(sql);

			List<Object> listT = executeQuery(sql, preparedStatement,
					mapperResult);

			sql = "SELECT count(*) AS cant FROM " + table + ";";

			connectionWrapper.add(sql);

			Long rowCount = executeQueryForLong(sql, connectionWrapper
					.getConnection().createStatement());

			Long cantPages = 0L;

			if (listT.size() > 0) {
				cantPages = 1L;
			}

			if (limit > 0) {
				cantPages = rowCount / limit;
				if ((rowCount % limit) > 0) {
					cantPages++;
				}
			}

			ResultList resultList = new ResultList();

			resultList.setList(listT.toArray());
			resultList.setCantPages(cantPages);
			resultList.setNumberOfRecords(rowCount);

			return resultList;
		} catch (Exception e) {
			ExFindDao ex = new ExFindDao(this.getClass(), e, connectionWrapper);


			throw ex;
		}

	}

	public ResultList findByExamplePageable(
			ConnectionWrapper connectionWrapper, String table,
			List<MapperQueryArg> mapperQueryArgList, MapperResult mapperResult,
			Integer offSet, Integer limit) {
		return findByExamplePageable(connectionWrapper, table,
				mapperQueryArgList, mapperResult, offSet, limit, null);
	}

	public ResultList findByExamplePageable(
			ConnectionWrapper connectionWrapper, String table,
			List<MapperQueryArg> mapperQueryArgList, MapperResult mapperResult,
			Integer offSet, Integer limit,
			List<MapperQueryOrderArg> mapperQueryOrderArgList) {

		try {

			String sqlWhere = "";

			if (offSet == null) {
				throw new RuntimeException(
						"El argumento offSet no puede ser nulo.");
			}
			if (limit == null) {
				throw new RuntimeException(
						"El argumento limit no puede ser nulo.");
			}

			if (offSet < 0) {
				throw new RuntimeException(
						"El argumento offSet no puede ser menor a 0.");
			}
			if (limit < 0) {
				throw new RuntimeException(
						"El argumento limit no puede ser menor a 0.");
			}

			for (int i = 0; i < mapperQueryArgList.size(); i++) {
				MapperQueryArg mapperQueryArg = mapperQueryArgList.get(i);
				if (i == 0) {
					sqlWhere += "\t" + mapperQueryArg.getSql();
				} else {
					sqlWhere += "\n\tAND " + mapperQueryArg.getSql();
				}
			}

			String orderSql = "";
			if (mapperQueryOrderArgList != null) {
				for (int i = 0; i < mapperQueryOrderArgList.size(); i++) {
					MapperQueryOrderArg mapperQueryOrderArg = mapperQueryOrderArgList
							.get(i);
					if (i == 0) {
						orderSql += "\nORDER BY "
								+ mapperQueryOrderArg.getSql();
					} else {
						orderSql += ", " + mapperQueryOrderArg.getSql();
					}
				}
			}

			String sql = "";

			if (mapperQueryArgList.size() > 0) {
				sql = "SELECT * FROM " + table + "\nWHERE " + sqlWhere
						+ orderSql + "\nOFFSET ? LIMIT ?;";
			} else {
				sql = "SELECT * FROM " + table + orderSql
						+ " OFFSET ? LIMIT ?;";
			}

			PreparedStatement preparedStatement = connectionWrapper
					.getConnection().prepareStatement(sql);

			// =====================================================================
			int parameterCount = preparedStatement.getParameterMetaData()
					.getParameterCount();
			int parameterIndex = 1;

			for (int i = 0; i < mapperQueryArgList.size(); i++) {

				MapperQueryArg mapperQueryArg = mapperQueryArgList.get(i);

				if (mapperQueryArg.getArg() == true) {

					if (parameterIndex <= parameterCount) {
						set(preparedStatement, mapperQueryArg.getValue(),
								parameterIndex);

						parameterIndex++;

					}

				}
			}

			if (parameterIndex <= parameterCount) {
				preparedStatement.setInt(parameterIndex, offSet);

				parameterIndex++;
			}

			if (parameterIndex <= parameterCount) {
				preparedStatement.setInt(parameterIndex, limit);

				parameterIndex++;
			}

			// =====================================================================

			sql = preparedStatement.toString();
			connectionWrapper.add(sql);

			List<Object> listT = executeQueryByExample(preparedStatement,
					mapperResult);

			// ==============================================================

			if (mapperQueryArgList.size() > 0) {
				sql = "SELECT count(*) AS cant FROM " + table + "\nWHERE "
						+ sqlWhere + ";";
			} else {
				sql = "SELECT count(*) AS cant FROM " + table + ";";
			}

			preparedStatement = connectionWrapper.getConnection()
					.prepareStatement(sql);

			// =====================================================================
			parameterCount = preparedStatement.getParameterMetaData()
					.getParameterCount();
			parameterIndex = 1;

			for (int i = 0; i < mapperQueryArgList.size(); i++) {

				MapperQueryArg mapperQueryArg = mapperQueryArgList.get(i);

				if (mapperQueryArg.getArg() == true) {

					if (parameterIndex <= parameterCount) {
						set(preparedStatement, mapperQueryArg.getValue(),
								parameterIndex);

						parameterIndex++;

					}

				}
			}

			// =====================================================================

			sql = preparedStatement.toString();

			connectionWrapper.add(sql);

			Long rowCount = executeQueryByExampleForLong(preparedStatement);

			// =====================================================================

			Long cantPages = 0L;

			if (listT.size() > 0) {
				cantPages = 1L;
			}

			if (limit > 0) {
				cantPages = rowCount / limit;
				if ((rowCount % limit) > 0) {
					cantPages++;
				}
			}

			ResultList resultList = new ResultList();

			resultList.setList(listT.toArray());
			resultList.setCantPages(cantPages);
			resultList.setNumberOfRecords(rowCount);

			return resultList;
		} catch (Exception e) {
			ExFindDao ex = new ExFindDao(this.getClass(), e, connectionWrapper);

			ex.setFirstTrace(e, this.getClass());


			throw ex;
		}

	}

	// ///////////////////////////////////////////////////////////////////7

	private List<Object> executeQueryByExample(
			PreparedStatement preparedStatement, MapperResult mapperResult)
			throws SQLException {

		List<Object> listT = new ArrayList<Object>();

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			listT.add(mapperResult.mapRow(resultSet));
		}

		// if (resultSet != null && resultSet.isClosed() == false) {
		// resultSet.close();
		// }

		// if (preparedStatement != null && preparedStatement.isClosed() ==
		// false) {
		// preparedStatement.close();
		// }

		return listT;
	}

	private List<Object> executeQuery(String sql, Statement statement,
			MapperResult mapperResult) throws SQLException {

		if (sql == null) {
			throw new ExSqlVarQueryNullDao(this.getClass());
		}

		if (sql.trim().length() == 0) {
			throw new ExSqlVarQueryEmptyDao(this.getClass());
		}

		List<Object> listT = new ArrayList<Object>();

		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			listT.add(mapperResult.mapRow(resultSet));
		}

		// if (resultSet.isClosed() == false) {
		// resultSet.close();
		// }
		//
		// if (statement.isClosed() == false) {
		// statement.close();
		// }

		return listT;
	}

	private Long executeQueryByExampleForLong(
			PreparedStatement preparedStatement) throws SQLException {

		Long l = 0L;

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			l = resultSet.getLong(1);
		}

		// if (resultSet.isClosed() == false) {
		// resultSet.close();
		// }
		//
		// if (preparedStatement.isClosed() == false) {
		// preparedStatement.close();
		// }

		return l;
	}

	private Long executeQueryForLong(String sql, Statement statement)
			throws SQLException {

		if (sql == null) {
			throw new ExSqlVarQueryNullDao(this.getClass());
		}

		if (sql.trim().length() == 0) {
			throw new ExSqlVarQueryEmptyDao(this.getClass());
		}

		Long l = 0L;

		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			l = resultSet.getLong(1);
		}

		if (resultSet.isClosed() == false) {
			resultSet.close();
		}

		if (statement.isClosed() == false) {
			statement.close();
		}

		return l;
	}

	private int executeUpdateByExample(PreparedStatement preparedStatement)
			throws SQLException {

		int r = preparedStatement.executeUpdate();

		// if (preparedStatement.isClosed() == false) {
		// preparedStatement.close();
		// }

		return r;
	}

	private int executeUpdate(String sql, Statement statement)
			throws SQLException {

		int r = statement.executeUpdate(sql);

		if (statement.isClosed() == false) {
			statement.close();
		}

		return r;
	}

	private void set(PreparedStatement preparedStatement, Object value,
			Integer i) throws SQLException {
		if (value != null) {
			if (value instanceof String) {

				preparedStatement.setString(i, (String) value);

			} else if (value instanceof Boolean) {
				preparedStatement.setBoolean(i, (Boolean) value);
			} else if (value instanceof Short) {
				preparedStatement.setShort(i, (Short) value);
			} else if (value instanceof Integer) {
				preparedStatement.setInt(i, (Integer) value);
			} else if (value instanceof Long) {
				preparedStatement.setLong(i, (Long) value);
			} else if (value instanceof Float) {
				preparedStatement.setFloat(i, (Float) value);
			} else if (value instanceof Double) {
				preparedStatement.setDouble(i, (Double) value);
			} else if (value instanceof BigDecimal) {
				preparedStatement.setBigDecimal(i, (BigDecimal) value);
			} else if (value instanceof Date) {
				preparedStatement.setDate(i, (Date) value);
			} else if (value instanceof Timestamp) {
				preparedStatement.setTimestamp(i, (Timestamp) value);
			} else if (value instanceof Time) {
				preparedStatement.setTime(i, (Time) value);
			}
		}
	}

}
