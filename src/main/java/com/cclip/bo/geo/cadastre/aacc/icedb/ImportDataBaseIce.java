package com.cclip.bo.geo.cadastre.aacc.icedb;

import java.io.File;
import java.sql.Timestamp;

import org.utiljdbc.ConnectionWrapper;
import org.utiljdbc.DataSourceWrapper;

import com.cclip.Context;
import com.cclip.model.person.UserSystem;
import com.cclip.util.LinoProperties;
import com.cclip.util.SO;

public class ImportDataBaseIce {

	public static String DB_ICE_FOLDER = "db_ice";
	public static String DB_ICE_PRE_FOLDER = "pre_import";
	public static String DB_ICE_POST_FOLDER = "post_import";

	private DataSourceWrapper dataSourceWrapper;

	public void setDataSourceWrapper(DataSourceWrapper dataSourceWrapper) {
		this.dataSourceWrapper = dataSourceWrapper;
	}

	public void importDbIce(UserSystem userSystem, File unidad, File inmueble, File superficie, File subcuenta, File datosPostales) throws Exception {

		ConnectionWrapper connectionWrapper = dataSourceWrapper.getConnectionWrapper();

		importDbIce(userSystem, connectionWrapper, unidad, inmueble, superficie, subcuenta, datosPostales);

		connectionWrapper.close(connectionWrapper);
	}

	private void importDbIce(UserSystem userSystem, ConnectionWrapper connectionWrapper, File unidad, File inmueble, File superficie, File subcuenta, File datosPostales) throws Exception {

		Timestamp t = new Timestamp(System.currentTimeMillis());
		String folderLote = "";

		folderLote = t.toString().replace("-", "");
		folderLote = folderLote.replace(":", "");
		folderLote = folderLote.replace(".", "");
		folderLote = folderLote.replace(" ", "_");

		LinoProperties linoProperties = Context.getBean("linoProperties", LinoProperties.class);
		String path = linoProperties.getUrlFiles();

		String pathDbIce = SO.createFolder(path + File.separatorChar + DB_ICE_FOLDER);

		String pathPost = SO.createFolder(pathDbIce + File.separatorChar + DB_ICE_POST_FOLDER);

		String pathPre = SO.createFolder(pathDbIce + File.separatorChar + DB_ICE_PRE_FOLDER);

		String pathPreBatch = SO.createFolder(pathPre + File.separatorChar + folderLote);

		SO.copy(unidad.getPath(), pathPreBatch + File.separatorChar + unidad.getName());
		SO.copy(inmueble.getPath(), pathPreBatch + File.separatorChar + inmueble.getName());
		SO.copy(superficie.getPath(), pathPreBatch + File.separatorChar + superficie.getName());
		SO.copy(subcuenta.getPath(), pathPreBatch + File.separatorChar + subcuenta.getName());
		SO.copy(datosPostales.getPath(), pathPreBatch + File.separatorChar + datosPostales.getName());

		unidad = new File(pathPreBatch + File.separatorChar + unidad.getName());
		inmueble = new File(pathPreBatch + File.separatorChar + inmueble.getName());
		superficie = new File(pathPreBatch + File.separatorChar + superficie.getName());
		subcuenta = new File(pathPreBatch + File.separatorChar + subcuenta.getName());
		datosPostales = new File(pathPreBatch + File.separatorChar + datosPostales.getName());

		importDb(t, userSystem, connectionWrapper, unidad, inmueble, superficie, subcuenta, datosPostales);

		String pathPostBatch = SO.createFolder(pathPost + File.separatorChar + folderLote);

		SO.copy(unidad.getPath(), pathPostBatch + File.separatorChar + unidad.getName());
		SO.copy(inmueble.getPath(), pathPostBatch + File.separatorChar + inmueble.getName());
		SO.copy(superficie.getPath(), pathPostBatch + File.separatorChar + superficie.getName());
		SO.copy(subcuenta.getPath(), pathPostBatch + File.separatorChar + subcuenta.getName());
		SO.copy(datosPostales.getPath(), pathPostBatch + File.separatorChar + datosPostales.getName());

		SO.delete(pathPre);

	}

	private static void importDb(Timestamp t, UserSystem userSystem, ConnectionWrapper connectionWrapper, File unidad, File inmueble, File superficie, File subcuenta, File datosPostales)
			throws Exception {

		try {

			connectionWrapper.begin(connectionWrapper);

			String p = "";

			if (userSystem.getDni() != null) {
				p = userSystem.getDni() + " ";
			} else if (userSystem.getCuil() != null) {
				p = userSystem.getCuil() + " ";
			}

			p += userSystem.getFamilyName() + " " + userSystem.getGivenName();

			new ImportUnidad().importTable(unidad, connectionWrapper, t, p, t, p);
			new ImportInmueble().importTable(inmueble, connectionWrapper, t, p, t, p);
			new ImportSuperficie().importTable(superficie, connectionWrapper, t, p, t, p);
			new ImportSubcuenta().importTable(subcuenta, connectionWrapper, t, p, t, p);
			new ImportDatosPostales().importTable(datosPostales, connectionWrapper, t, p, t, p);

			connectionWrapper.commit(connectionWrapper);

		} catch (Exception e) {

			connectionWrapper.rollBack(connectionWrapper);

			throw e;
		}

	}

}
