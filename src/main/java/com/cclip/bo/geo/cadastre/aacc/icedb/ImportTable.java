package com.cclip.bo.geo.cadastre.aacc.icedb;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.utiljdbc.ConnectionWrapper;

import com.csvreader.CsvReader;

abstract class ImportTable {

	protected static String table = null;
	protected static String tableBack = null;
	protected static String file = null;

	protected static String[] fieldNames = null;

	public void importTable(File file, ConnectionWrapper connectionWrapper,
			Timestamp auditDateCreate, String auditUserCreate,
			Timestamp auditDateUpdate, String auditUserUpdate)
			throws IOException, Exception {

//		String sql = SO.readFilePlainText(this.file);

		System.out.println("     deleteOldBackup ...");
		
		deleteOldBackup(connectionWrapper);
		
		System.out.println("[OK] deleteOldBackup");
		System.out.println("     buildBackup ...");

		buildBackup(connectionWrapper);
		
		System.out.println("[OK] buildBackup");
		System.out.println("     deleteTable ...");

		deleteTable(connectionWrapper);
		
		System.out.println("[OK] deleteTable");
		System.out.println("     insertNewFile ...");

		insertNewFile(file, connectionWrapper, auditDateCreate, auditUserCreate,
				auditDateUpdate, auditUserUpdate);
		
		System.out.println("[OK] insertNewFile");		
		System.out.println("     statement.execute(sql) ...");
		
//		Statement statement = connectionWrapper.getConnection().createStatement();
//
//		statement.execute(sql);
//		
//		System.out.println("[OK] statement.execute(sql)");

	}

	private void deleteOldBackup(ConnectionWrapper connectionWrapper) throws SQLException {

		String sql = "TRUNCATE " + this.tableBack + " CASCADE;";

		Statement statement = connectionWrapper.getConnection().createStatement();

		statement.execute(sql);
	}

	private void buildBackup(ConnectionWrapper connectionWrapper) throws SQLException {

		String sql = "INSERT INTO " + this.tableBack + " ( SELECT * FROM "
				+ this.table + ");";

		Statement statement = connectionWrapper.getConnection().createStatement();

		statement.execute(sql);
	}

	private void deleteTable(ConnectionWrapper connectionWrapper) throws SQLException {

		String sql = "TRUNCATE " + this.table + " CASCADE;";

		Statement statement = connectionWrapper.getConnection().createStatement();

		statement.execute(sql);
	}

	private void insertNewFile(File file, ConnectionWrapper connectionWrapper,
			Timestamp auditDateCreate, String auditUserCreate,
			Timestamp auditDateUpdate, String auditUserUpdate)
			throws IOException, Exception {

		FileReader freader = new FileReader(file);

		CsvReader cvsReader = new CsvReader(freader, '|');

		cvsReader.setSafetySwitch(false);

		if (cvsReader.readHeaders()) {
			String[] headers = cvsReader.getHeaders();

			insertNewRowUnidadFile(headers, connectionWrapper, auditDateCreate,
					auditUserCreate, auditDateUpdate, auditUserUpdate);
		}

		while (cvsReader.readRecord()) {
			String[] row = new String[cvsReader.getColumnCount()];

			for (int i = 0; i < cvsReader.getColumnCount(); i++) {
				String value = cvsReader.get(i);

				row[i] = value;
			}

			insertNewRowUnidadFile(row, connectionWrapper, auditDateCreate,
					auditUserCreate, auditDateUpdate, auditUserUpdate);
		}

	}

	private void insertNewRowUnidadFile(String[] row, ConnectionWrapper connectionWrapper,
			Timestamp auditDateCreate, String auditUserCreate,
			Timestamp auditDateUpdate, String auditUserUpdate) throws Exception {

		try {

			for (int i = 0; i < row.length; i++) {
				if (row[i] != null) {
					row[i] = row[i].trim();
				}

				if (row[i] != null && row[i].trim().length() == 0) {
					row[i] = null;
				}

				if (row[i] != null) {
					try {						
						double d = Double.parseDouble(row[i]);
						int pEnt = (int) d;
						double pDec = d - pEnt;
						
						if(pDec == 0){
							row[i] = row[i].split("[.]")[0];
						}
//						if (pDec == 0) {
//							row[i] = pEnt + "";
//						}
					} catch (NumberFormatException nfe) {
					}
				}

			}

			String sql = "INSERT INTO " + this.table + " (";

			for (int i = 0; i < fieldNames.length; i++) {
				if (i == 0) {
					sql += " " + fieldNames[i];
				} else {
					sql += ", " + fieldNames[i];
				}

			}

			sql += ") VALUES (";

			for (int i = 0; i < fieldNames.length; i++) {

				if (i == 0) {
					sql += " ?";
				} else {
					sql += ", ?";
				}

			}

			sql += ");";

			List<Object> l2 = new ArrayList<Object>();

			for (int i = 0; i < row.length; i++) {

				l2.add(row[i]);
			}

			l2.add(0, UUID.randomUUID().toString());
			l2.add(1, false);
			l2.add(2, auditDateCreate);
			l2.add(3, auditUserCreate);
			l2.add(4, auditDateUpdate);
			l2.add(5, auditUserUpdate);
			l2.add(6, 1L);

			if (l2.size() < fieldNames.length) {
				int x = fieldNames.length - l2.size();
				for (int i = 0; i < x; i++) {
					l2.add(null);
				}
			}

			Object[] r2 = l2.toArray();

			// ////////////////////////////////////////////////////////

			// System.out.println(r2.length + "  .................. "
			// + FIELD_NAMES.length);
			//
			// for (int i = 0; i < FIELD_NAMES.length; i++) {
			// System.out.println("i" + i + "_" + FIELD_NAMES[i] + " = "
			// + r2[i]);
			// }

			// ////////////////////////////////////////////////////////

			PreparedStatement ps = connectionWrapper.getConnection().prepareStatement(sql);

			int c = 0;
			for (int i = 0; i < fieldNames.length; i++) {

				if (i == 1) {
					if (r2[i] == null) {
						c = c + 1;
						ps.setNull(c, java.sql.Types.BOOLEAN);
					} else {
						c = c + 1;
						ps.setBoolean(c, (Boolean) r2[i]);
					}
				} else if (i == 2 || i == 4) {
					if (r2[i] == null) {
						c = c + 1;
						ps.setNull(c, java.sql.Types.TIMESTAMP);
					} else {
						c = c + 1;
						ps.setTimestamp(c, (Timestamp) r2[i]);
					}
				} else if (i == 6) {
					if (r2[i] == null) {
						c = c + 1;
						ps.setNull(c, java.sql.Types.BIGINT);
					} else {
						c = c + 1;
						ps.setLong(c, (Long) r2[i]);
					}
				} else {
					if (r2[i] == null) {
						c = c + 1;
						ps.setNull(c, java.sql.Types.VARCHAR);
					} else {
						c = c + 1;
						ps.setString(c, r2[i].toString().trim());
					}
				}

			}

			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
			throw e;
		}
	}

}
