package org.utiljdbcdao.ex;

import java.util.ArrayList;
import java.util.List;

import org.utiljdbc.ConnectionWrapper;
import org.utiljdbc.DataSourceMetaData;

abstract class GenericExceptionDbDao extends GenericExceptionDao {

	private static final long serialVersionUID = 6279912367266829619L;

	protected String operationType = "unknown";

	private DataSourceMetaData dataSourceMetaData;
	private List<String> sqlList = new ArrayList<String>();

	@SuppressWarnings("rawtypes")
	public GenericExceptionDbDao(Class throwerClass, Exception thirdException,
			ConnectionWrapper connectionWrapper) {
		super(throwerClass, thirdException);

		dataSourceMetaData = connectionWrapper.getDataSourceMetaData();
		sqlList = connectionWrapper.getSqlList();

		if (thirdException instanceof GenericExceptionDbDao) {

			this.setThirdException(((GenericExceptionDbDao) thirdException)
					.getThirdException());

			this.setFirstTrace(((GenericExceptionDbDao) thirdException)
					.getFirstTrace());
		}
	}

	public String getTechnicalMessage() {
		return "\n\n\n[" + super.time + " Start Exception:" + getCode() + " "
				+ this.getClass().getCanonicalName() + "]"
				+ getTechnicalMessageTmp() + "\n[" + time + " End Exception:"
				+ getCode() + " " + this.getClass().getCanonicalName()
				+ "]\n\n\n";
	}

	private String getTechnicalMessageTmp() {
		String jDBCMajorVersion = "unknown";
		String jDBCMinorVersion = "unknown";
		if (getjDBCMajorVersion() > -1) {
			jDBCMajorVersion = getjDBCMajorVersion() + "";
		}
		if (getjDBCMinorVersion() > -1) {
			jDBCMinorVersion = getjDBCMinorVersion() + "";
		}

		return "\n[" + getCode() + "] = " + "Subject: " + title + "\n["
				+ getCode() + "] = " + "Message: " + message + "\n["
				+ getCode() + "] = " + "Thrower Class: " + throwerClass + "\n["
				+ getCode() + "] = " + "Layer: " + layer + "\n[" + getCode()
				+ "] = " + "Operation Type: " + operationType + "\n["
				+ getCode() + "] = " + "URL JDBC: " + this.getUrl() + "\n["
				+ getCode() + "] = " + "User Name Data Base: "
				+ this.getUserName() + "\n[" + getCode() + "] = "
				+ "Data Base Product Name: " + this.getDatabaseProductName()
				+ "\n[" + getCode() + "] = " + "Database Product Version: "
				+ this.getDatabaseProductVersion() + "\n[" + getCode() + "] = "
				+ "Driver JDBC Name: " + this.getDriverName() + "\n["
				+ getCode() + "] = " + "Driver JDBC Version: "
				+ this.getDriverVersion() + "\n[" + getCode() + "] = "
				+ "JDBC Major Version: " + jDBCMajorVersion + "\n[" + getCode()
				+ "] = " + "JDBC Minor Version: " + jDBCMinorVersion + "\n["
				+ getCode() + "] = "

				+ "SQL: " + this.getSql()

				+ "\n[" + getCode() + "] = " + "First Trace: "
				+ this.getFirstTrace() + "\n[" + getCode() + "] = " + "Cause: "
				+ this.thirdException;

	}

	public String toString() {
		String stackTrace = "";
		for (StackTraceElement st : getStackTrace()) {
			stackTrace += "\n" + st;
		}

		if (thirdException != null) {
			String stackTraceThirdException = "";
			for (StackTraceElement st : thirdException.getStackTrace()) {
				stackTraceThirdException += "\n" + st;
			}
			return "\n\n\n[" + super.time + " Start Exception:" + getCode()
					+ " " + this.getClass().getCanonicalName() + "]"
					+ getTechnicalMessageTmp() + "\n[" + getCode() + "] = "
					+ "Stack Trace:\n" + stackTrace + "\n\n[" + getCode()
					+ "] = " + "Stack Trace Third Exception:\n"
					+ stackTraceThirdException + "\n\n[" + time
					+ " End Exception:" + getCode() + " "
					+ this.getClass().getCanonicalName() + "]\n\n\n";
		} else {
			return "\n\n\n[" + super.time + " Start Exception:" + getCode()
					+ " " + this.getClass().getCanonicalName() + "]"
					+ getTechnicalMessageTmp() + "\n[" + getCode() + "] = "
					+ "Stack Trace: " + stackTrace + "\n\n[" + time
					+ " End Exception:" + getCode() + " "
					+ this.getClass().getCanonicalName() + "]\n\n\n";
		}
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
			r += "\n------------------------------------------------------------------------------------------";
			r += "\n" + sql;
		}

		return r;
	}

	public List<String> getSqlList() {
		return sqlList;
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

}
