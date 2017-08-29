package com.cclip.services;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.UUID;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import org.utiljdbc.ConnectionWrapper;

import com.cclip.gui.aacc.JDialogImportIceDbProgress;
import com.cclip.gui.util.JLabelTimer;
import com.csvreader.CsvReader;

class ImportUnlToTable {

	private JDialogImportIceDbProgress gui;
	private ImportUnl importUnl;

	private long rowsTmp = 0;
	private long columnsTmp = 0;

	public ImportUnlToTable(JDialogImportIceDbProgress gui, ImportUnl importUnl) {
		super();
		this.gui = gui;
		this.importUnl = importUnl;
	}

	public void importTable(JProgressBar jProgressBar, int progress, JLabelTimer jLabelTimer, JLabel jLabelDelete, JLabel jLabelInsert, String table, File file, ConnectionWrapper connectionWrapper,
			Timestamp auditDateCreate, String auditUserCreate, Timestamp auditDateUpdate, String auditUserUpdate) throws IOException, Exception {

		jLabelTimer.iniciar();

		columnsTmp = 0;
		rowsTmp = 0;

		// System.out.println("     File: " + file.getName() + ", Table: " + table + ". Start: " + new Timestamp(System.currentTimeMillis()));

		// System.out.println("     deleteTable " + table + " ...");
		jLabelDelete.setText("Borrando tabla " + table + " ...");
		deleteTable(connectionWrapper, table);
		jLabelDelete.setText("Tabla " + table + " borrada.");
		jProgressBar.setValue(50);
		// System.out.println("[OK] deleteTable " + table);

		// System.out.println("     insertNewFile " + table + "...");
		jLabelInsert.setText("Insertando filas en " + table + " ...");
		insertNewFile(jLabelInsert, table, file, connectionWrapper, auditDateCreate, auditUserCreate, auditDateUpdate, auditUserUpdate);
		jProgressBar.setValue(100);
		// System.out.println("[OK] insertNewFile " + table + ". Columns found " + columnsTmp);

		// System.out.println("[OK] File: " + file.getName() + ", Table: " + table + ". End: " + new Timestamp(System.currentTimeMillis()));

		columnsTmp = 0;

		// [OK] insertNewFile cclip.aacc_unidad. Columns found 188
		// [OK] insertNewFile cclip.aacc_inmueble. Columns found 36
		// [OK] insertNewFile cclip.aacc_superficie. Columns found 16
		// [OK] insertNewFile cclip.aacc_subcuenta. Columns found 59
		// [OK] insertNewFile cclip.aacc_datos_postales. Columns found 26

		jProgressBar.setValue(progress);

		jLabelTimer.pausa();
	}

	private void deleteTable(ConnectionWrapper connectionWrapper, String table) throws SQLException {

		importUnl.isStop();

		String sql = "TRUNCATE " + table + " CASCADE;";

		Statement statement = connectionWrapper.getConnection().createStatement();

		statement.execute(sql);
	}

	private void insertNewFile(JLabel jLabelInsert, String table, File file, ConnectionWrapper connectionWrapper, Timestamp auditDateCreate, String auditUserCreate, Timestamp auditDateUpdate,
			String auditUserUpdate) throws IOException, Exception {

		FileReader freader = new FileReader(file);

		CsvReader cvsReader = new CsvReader(freader, '|');

		cvsReader.setSafetySwitch(false);

		if (cvsReader.readHeaders()) {
			String[] headers = cvsReader.getHeaders();

			insertNewRowFile(jLabelInsert, table, headers, connectionWrapper, auditDateCreate, auditUserCreate, auditDateUpdate, auditUserUpdate);
		}

		while (cvsReader.readRecord()) {
			String[] row = new String[cvsReader.getColumnCount()];

			for (int i = 0; i < cvsReader.getColumnCount(); i++) {
				String value = cvsReader.get(i);

				row[i] = value;
			}

			insertNewRowFile(jLabelInsert, table, row, connectionWrapper, auditDateCreate, auditUserCreate, auditDateUpdate, auditUserUpdate);
		}

	}

	private void insertNewRowFile(JLabel jLabelInsert, String table, String[] row, ConnectionWrapper connectionWrapper, Timestamp auditDateCreate, String auditUserCreate, Timestamp auditDateUpdate,
			String auditUserUpdate) throws Exception {

		importUnl.isStop();

		PreparedStatement ps = null;

		if (columnsTmp < row.length) {
			columnsTmp = row.length;
		}

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

						if (pDec == 0) {
							row[i] = row[i].split("[.]")[0];
						}

					} catch (NumberFormatException nfe) {
					}
				}

			}

			String sql = "INSERT INTO " + table + " ( id, erased, audit_date_create, audit_user_create";

			for (int i = 0; i < row.length; i++) {
				sql += ", c" + (i + 1);
			}

			sql += " ) VALUES ( ?, ?, ?, ?";

			for (int i = 0; i < row.length; i++) {

				sql += ", ?";

			}

			sql += " );";

			ps = connectionWrapper.getConnection().prepareStatement(sql);

			int c = 0;

			c = c + 1;
			ps.setString(c, UUID.randomUUID().toString());

			c = c + 1;
			ps.setBoolean(c, false);

			c = c + 1;
			ps.setTimestamp(c, auditDateCreate);

			c = c + 1;
			ps.setString(c, auditUserCreate);

			for (int i = 0; i < row.length; i++) {

				if (row[i] == null) {
					c = c + 1;
					ps.setNull(c, java.sql.Types.VARCHAR);
				} else {
					c = c + 1;
					ps.setString(c, row[i].toString().trim());
				}

			}

			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
			System.out.println(ps);
			throw e;
		}

		rowsTmp = rowsTmp + 1;

		jLabelInsert.setText("Filas insertadas en " + table + ". Filas insertadas " + rowsTmp + ". Columnas encontradas " + columnsTmp);

	}

}
