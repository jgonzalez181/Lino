package org.utiljdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.utiljdbcdao.ex.ExBeginDao;
import org.utiljdbcdao.ex.ExCloseConnection;
import org.utiljdbcdao.ex.ExCommitDao;
import org.utiljdbcdao.ex.ExRollBackDao;

public class ConnectionWrapper {

	private Connection connection;
	private DataSourceMetaData dataSourceMetaData;
	private List<String> sqlList = new ArrayList<String>();
	protected UtilJdbc utilJdbc;

	public ConnectionWrapper(Connection connection, DataSourceMetaData dataSourceMetaData) {
		this.connection = connection;
		this.dataSourceMetaData = dataSourceMetaData;
		this.utilJdbc = new UtilJdbc();
		utilJdbc.setVerbose(true);

	}

	public void setUtilJdbc(UtilJdbc utilJdbc) {
		this.utilJdbc = utilJdbc;
	}

	public void add(String sql) {
		sqlList.add(sql);
	}

	public String getSql() {
		if (this.getSqlList() == null) {
			return "unknown";
		}
		if (this.getSqlList().size() == 0) {
			return "unknown";
		}
		String r = "";
		for (String sql : this.getSqlList()) {
			r += "\n\t------------------------------------------------------------------------------------------";
			r += "\n\t" + sql;
		}

		return r;
	}

	public List<String> getSqlList() {
		return sqlList;
	}

	public Connection getConnection() throws SQLException {
		return connection;
	}

	public DataSourceMetaData getDataSourceMetaData() {
		return dataSourceMetaData;
	}

	public String getUrl() {
		return dataSourceMetaData.url;
	}

	public String getUserName() {
		return dataSourceMetaData.userName;
	}

	public String getDatabaseProductName() {
		return dataSourceMetaData.databaseProductName;
	}

	public String getDatabaseProductVersion() {
		return dataSourceMetaData.databaseProductVersion;
	}

	public String getDriverName() {
		return dataSourceMetaData.driverName;
	}

	public String getDriverVersion() {
		return dataSourceMetaData.driverVersion;
	}

	public int getjDBCMajorVersion() {
		return dataSourceMetaData.jDBCMajorVersion;
	}

	public int getjDBCMinorVersion() {
		return dataSourceMetaData.jDBCMinorVersion;
	}

	public void close() {
		close(this);
	}

	public void close(ConnectionWrapper connectionWrapper) {
		try {
			if (connectionWrapper.getConnection() != null & connectionWrapper.getConnection().isClosed() == false) {
				connectionWrapper.getConnection().close();
			}

			connectionWrapper.add("close();");
		} catch (SQLException e2) {
			ExCloseConnection ex2 = new ExCloseConnection(this.getClass(), e2, connectionWrapper);

			ex2.setFirstTrace(e2, this.getClass());

		}

	}

	public void begin(ConnectionWrapper connectionWrapper) {
		try {
			connectionWrapper.getConnection().setAutoCommit(false);
			connectionWrapper.add("begin();");
		} catch (Exception e) {

			ExBeginDao ex = new ExBeginDao(this.getClass(), e, connectionWrapper);

			ex.setFirstTrace(e, this.getClass());

			throw ex;
		}

	}

	public void begin() {
		try {
			this.getConnection().setAutoCommit(false);
			this.add("begin();");
		} catch (Exception e) {

			ExBeginDao ex = new ExBeginDao(this.getClass(), e, this);

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

			ExCommitDao ex = new ExCommitDao(this.getClass(), e, connectionWrapper);

			ex.setFirstTrace(e, this.getClass());

			throw ex;
		}

	}

	public void commit() {
		try {

			this.getConnection().commit();
			this.add("commit();");

			close(this);

		} catch (Exception e) {

			ExCommitDao ex = new ExCommitDao(this.getClass(), e, this);

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

			ExRollBackDao ex = new ExRollBackDao(this.getClass(), e, connectionWrapper);

			ex.setFirstTrace(e, this.getClass());

			throw ex;
		}

	}

	public void rollBack() {
		try {

			this.getConnection().rollback();
			this.add("rollback();");

			close(this);

		} catch (Exception e) {

			ExRollBackDao ex = new ExRollBackDao(this.getClass(), e, this);

			ex.setFirstTrace(e, this.getClass());

			throw ex;
		}

	}

	public int update(String sql, Object... args) {
		return utilJdbc.update(this, sql, args);
	}

	public Object[] findToJson(String sql, Object... args) {
		return utilJdbc.findToJson(this, sql, args);
	}

	public Object findToJsonById(String sql, String id) {
		return utilJdbc.findToJsonById(this, sql, id);
	}

	public Object[][] findToTable(String sql, Object... args) {
		return utilJdbc.findToTable(this, sql, args);
	}

	public ResultList findToResultList(String sql, String sqlCount, int offSet, int limit, Object... args) {
		return utilJdbc.findToResultList(this, sql, sqlCount, offSet, limit, args);
	}

	public boolean isExitsById(String table, String id) {
		return utilJdbc.isExitsById(this, table, id);
	}

	public Object findToJsonByExample(String sql, Object... args) {
		return utilJdbc.findToJsonByExample(this, sql, args);
	}
	
	
	
}
