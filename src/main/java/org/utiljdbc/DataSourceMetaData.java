package org.utiljdbc;

public class DataSourceMetaData {

	public String url = "unknown";
	public String userName = "unknown";
	public String databaseProductName = "unknown";
	public String databaseProductVersion = "unknown";
	public String driverName = "unknown";
	public String driverVersion = "unknown";
	public int jDBCMajorVersion = -1;
	public int jDBCMinorVersion = -1;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDatabaseProductName() {
		return databaseProductName;
	}

	public void setDatabaseProductName(String databaseProductName) {
		this.databaseProductName = databaseProductName;
	}

	public String getDatabaseProductVersion() {
		return databaseProductVersion;
	}

	public void setDatabaseProductVersion(String databaseProductVersion) {
		this.databaseProductVersion = databaseProductVersion;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverVersion() {
		return driverVersion;
	}

	public void setDriverVersion(String driverVersion) {
		this.driverVersion = driverVersion;
	}

	public int getjDBCMajorVersion() {
		return jDBCMajorVersion;
	}

	public void setjDBCMajorVersion(int jDBCMajorVersion) {
		this.jDBCMajorVersion = jDBCMajorVersion;
	}

	public int getjDBCMinorVersion() {
		return jDBCMinorVersion;
	}

	public void setjDBCMinorVersion(int jDBCMinorVersion) {
		this.jDBCMinorVersion = jDBCMinorVersion;
	}

	public String toString() {

		String jDBCMajorVersion = "unknown";
		String jDBCMinorVersion = "unknown";

		if (getjDBCMajorVersion() > -1) {
			jDBCMajorVersion = getjDBCMajorVersion() + "";
		}
		if (getjDBCMinorVersion() > -1) {
			jDBCMinorVersion = getjDBCMinorVersion() + "";
		}

		String r = "";

		r = "\n\t- URL JDBC = " + this.getUrl() + "\n\t- User Name Data Base = " + this.getUserName() + "\n\t- Data Base Product Name = " + this.getDatabaseProductName()
				+ "\n\t- Database Product Version = " + this.getDatabaseProductVersion() + "\n\t- Driver JDBC Name = " + this.getDriverName() + "\n\t- Driver JDBC Version = "
				+ this.getDriverVersion() + "\n\t- JDBC Major Version = " + jDBCMajorVersion + "\n\t- JDBC Minor Version = " + jDBCMinorVersion

		;

		return r;

	}

}
