package com.cclip.services;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.swing.SwingWorker;

import org.utiljdbc.ConnectionWrapper;
import org.utiljdbc.DataSourceWrapper;

import com.cclip.gui.aacc.JDialogImportIceDbProgress;
import com.cclip.model.person.UserSystem;
import com.cclip.util.LinoProperties;
import com.cclip.util.SO;
import com.cclip.util.UtilComponent;

public class ImportUnl extends SwingWorker<Double, Integer> {

	public static String FOLDER_TO = "archivos_unl_importados";

	public static String PRE_FOLDER_DB_ICE = "db_congelada_pre_import";
	public static String POST_FOLDER_DB_ICE = "db_congelada_post_import";

	public static String PRE_FOLDER_NOV = "novedades_pre_import";
	public static String POST_FOLDER_NOV = "novedades_post_import";

	private DataSourceWrapper dataSourceWrapper;
	private LinoProperties linoProperties;

	private JDialogImportIceDbProgress gui;
	private UserSystem userSystem;
	private File unidad;
	private File inmueble;
	private File superficie;
	private File subcuenta;
	private File datosPostales;

	private boolean stop = false;
	private ImportUnlToTable importUnlToTable;

	protected Double doInBackground() throws Exception {

		importUnl(gui, linoProperties, userSystem, unidad, inmueble, superficie, subcuenta, datosPostales);

		return 100.0;

	}

	public void stop() {
		stop = true;
		cancel(true);
		// done();
	}

	//
	protected void done() {
		System.out.println("done() esta en el hilo " + Thread.currentThread().getName());
		// etiqueta.setText("hecho");

	}

	protected void process(List<Integer> chunks) {
		System.out.println("process() esta en el hilo " + chunks);
		// gui.jProgressBar1.setValue(chunks.get(0));
	}

	public void setDataSourceWrapper(DataSourceWrapper dataSourceWrapper) {
		this.dataSourceWrapper = dataSourceWrapper;
	}

	public void setLinoProperties(LinoProperties linoProperties) {
		this.linoProperties = linoProperties;
	}

	public void setGui(JDialogImportIceDbProgress gui) {
		this.gui = gui;
	}

	public void setUserSystem(UserSystem userSystem) {
		this.userSystem = userSystem;
	}

	public void setUnidad(File unidad) {
		this.unidad = unidad;
	}

	public void setInmueble(File inmueble) {
		this.inmueble = inmueble;
	}

	public void setSuperficie(File superficie) {
		this.superficie = superficie;
	}

	public void setSubcuenta(File subcuenta) {
		this.subcuenta = subcuenta;
	}

	public void setDatosPostales(File datosPostales) {
		this.datosPostales = datosPostales;
	}

	// //////////////////////////////////////////////////

	private void importUnl(JDialogImportIceDbProgress gui, LinoProperties linoProperties, UserSystem userSystem, File unidad, File inmueble, File superficie, File subcuenta, File datosPostales) {

		gui.jLabelTimer.iniciar();

		ConnectionWrapper connectionWrapper = dataSourceWrapper.getConnectionWrapper();

		try {

			connectionWrapper.begin();

			// ______________________________________________________________________________

			importUnlToTable = new ImportUnlToTable(gui, this);

			Timestamp t = new Timestamp(System.currentTimeMillis());
			gui.jLabelStartValue.setText(t.toLocaleString());
			int cantStep = 14;

			// A
			String pathTo = step1(cantStep);
			isStop();
			String pathPre = step2(pathTo, cantStep);
			isStop();
			String pathPost = step3(pathTo, cantStep);
			isStop();
			String pathPreBatch = step4(pathPre, t, cantStep);
			isStop();
			step5(pathPreBatch, cantStep);
			isStop();
			step6(pathPreBatch, cantStep);
			isStop();
			step7(pathPreBatch, cantStep);
			isStop();
			step8(pathPreBatch, cantStep);
			isStop();
			step9(pathPreBatch, cantStep);
			isStop();

			// B
			String p = step10(importUnlToTable, pathPreBatch, t, connectionWrapper, cantStep);
			isStop();
			step11(importUnlToTable, t, connectionWrapper, p, cantStep);
			isStop();
			step12(importUnlToTable, t, connectionWrapper, p, cantStep);
			isStop();
			step13(importUnlToTable, t, connectionWrapper, p, cantStep);
			isStop();
			step14(importUnlToTable, t, connectionWrapper, p, cantStep);
			isStop();

			// ______________________________________________________________________________

			// INSERT EN TABLAS FINALES

			// String sql = SO.readFilePlainText(linoProperties.getUrlFiles() + File.separatorChar + "import_db_ice.sql");

			// connectionWrapper.getConnection().createStatement().execute(sql);

			// connectionWrapper.update(sql, null);

			// ______________________________________________________________________________

			// CARPETA FINAL DONDE SE VAN A REGISTRAR LOS ARCHIVOS QUE SE IMPORTARON
			// SE MUEVE EN LA CARPETA POST IMPORT

			// String pathPostBatch = SO.createFolder(pathPost + File.separatorChar + folderLote);
			//
			// SO.copy(unidad.getPath(), pathPostBatch + File.separatorChar + unidad.getName());
			// SO.copy(inmueble.getPath(), pathPostBatch + File.separatorChar + inmueble.getName());
			// SO.copy(superficie.getPath(), pathPostBatch + File.separatorChar + superficie.getName());
			// SO.copy(subcuenta.getPath(), pathPostBatch + File.separatorChar + subcuenta.getName());
			//
			// if (datosPostales != null) {
			// SO.copy(datosPostales.getPath(), pathPostBatch + File.separatorChar + datosPostales.getName());
			// }

			// ______________________________________________________________________________

			// SE ELIMINA LA CARPETA DE PRE IMPORT, PARA NO DUPLICAR CONTENIDO
			// IMPORTADO

			// SO.delete(pathPre);

			// ______________________________________________________________________________

			// ______________________________________________________________________________

			connectionWrapper.commit();

		} catch (StopEx stop) {

		} catch (Exception e) {

			connectionWrapper.rollBack();

			UtilComponent.logger(e);
		} finally {
			Timestamp t2 = new Timestamp(System.currentTimeMillis());

			gui.jLabelEndValue.setText(t2.toLocaleString());
			gui.jButtonStart.setVisible(true);
			gui.jButtonStop.setVisible(false);
			gui.jButtonCancel.setVisible(true);

			gui.jLabelTimer1.pausa();
			gui.jLabelTimer2.pausa();
			gui.jLabelTimer3.pausa();
			gui.jLabelTimer4.pausa();
			gui.jLabelTimer5.pausa();
			gui.jLabelTimer6.pausa();
			gui.jLabelTimer7.pausa();
			gui.jLabelTimer8.pausa();
			gui.jLabelTimer9.pausa();
			gui.jLabelTimer10.pausa();
			gui.jLabelTimer11.pausa();
			gui.jLabelTimer12.pausa();
			gui.jLabelTimer13.pausa();
			gui.jLabelTimer14.pausa();
			gui.jLabelTimer.pausa();
		}

	}

	private String step1(int cantStep) throws Exception {

		gui.jLabelTimer1.iniciar();

		gui.jTabbedPane1.setSelectedIndex(0);

		String path = linoProperties.getUrlUnlTo();

		String pathTo = SO.createFolder(path + File.separatorChar + FOLDER_TO);
		gui.jCheckBox1.setSelected(true);
		gui.jLabel1.setText("Carpeta creada en: " + path + File.separatorChar + FOLDER_TO);
		int p = (1 / cantStep) * 100;
		gui.jLabel1.setText(p + "");
		gui.jProgressBar.setValue(p);
		gui.jProgressBar1.setValue(100);

		gui.jLabelTimer1.pausa();

		return pathTo;
	}

	private String step2(String pathTo, int cantStep) throws Exception {
		gui.jLabelTimer2.iniciar();

		String preFolder = null;

		if (datosPostales == null) {
			preFolder = PRE_FOLDER_NOV;
		} else {
			preFolder = PRE_FOLDER_DB_ICE;
		}

		String pathPre = SO.createFolder(pathTo + File.separatorChar + preFolder);
		gui.jCheckBox2.setSelected(true);
		gui.jLabel2.setText("Carpeta creada en: " + pathPre);
		gui.jProgressBar.setValue((2 / cantStep) * 100);
		gui.jProgressBar2.setValue(100);

		gui.jLabelTimer2.pausa();

		return pathPre;
	}

	private String step3(String pathTo, int cantStep) throws Exception {
		gui.jLabelTimer3.iniciar();

		String postFolder = null;

		if (datosPostales == null) {
			postFolder = POST_FOLDER_NOV;
		} else {
			postFolder = POST_FOLDER_DB_ICE;
		}

		String pathPost = SO.createFolder(pathTo + File.separatorChar + postFolder);
		gui.jCheckBox3.setSelected(true);
		gui.jLabel3.setText("Carpeta creada en: " + pathPost);
		gui.jProgressBar.setValue((3 / cantStep) * 100);
		gui.jProgressBar3.setValue(100);

		gui.jLabelTimer3.pausa();

		return postFolder;
	}

	private String step4(String pathPre, Timestamp t, int cantStep) throws Exception {
		gui.jLabelTimer4.iniciar();

		// CARPETA FINAL DONDE SE VAN A REGISTRAR LOS ARCHIVOS QUE SE IMPORTARON
		// SE CREA EN LA CARPETA PRE IMPORT

		String folderLote = "";

		folderLote = t.toString().replace("-", "");
		folderLote = folderLote.replace(":", "");
		folderLote = folderLote.replace(".", "");
		folderLote = folderLote.replace(" ", "_");

		folderLote = folderLote + "_" + userSystem.getCuil();

		String pathPreBatch = SO.createFolder(pathPre + File.separatorChar + folderLote);

		gui.jCheckBox4.setSelected(true);
		gui.jLabel4.setText("Carpeta creada en: " + pathPre + File.separatorChar + folderLote);

		gui.jProgressBar.setValue((4 / cantStep) * 100);
		gui.jProgressBar4.setValue(100);

		gui.jLabelTimer4.pausa();

		return pathPreBatch;
	}

	private void step5(String pathPreBatch, int cantStep) throws Exception {
		gui.jLabelTimer5.iniciar();

		gui.jLabel5.setText("Archivo a copiar: " + unidad.getPath());
		gui.jProgressBar5.setValue(50);
		SO.copy(unidad.getPath(), pathPreBatch + File.separatorChar + unidad.getName());
		gui.jCheckBox5.setSelected(true);
		gui.jLabel5_1.setText("Archivo a copiado: " + pathPreBatch + File.separatorChar + unidad.getName());
		gui.jProgressBar.setValue((5 / cantStep) * 100);
		gui.jProgressBar5.setValue(100);

		gui.jLabelTimer5.pausa();
	}

	private void step6(String pathPreBatch, int cantStep) throws Exception {
		gui.jLabelTimer6.iniciar();
		gui.jLabel6.setText("Archivo a copiar: " + inmueble.getPath());
		gui.jProgressBar6.setValue(50);
		SO.copy(inmueble.getPath(), pathPreBatch + File.separatorChar + inmueble.getName());
		gui.jCheckBox6.setSelected(true);
		gui.jLabel6_1.setText("Archivo a copiado: " + pathPreBatch + File.separatorChar + inmueble.getName());
		gui.jProgressBar.setValue((6 / cantStep) * 100);
		gui.jProgressBar6.setValue(100);

		gui.jLabelTimer6.pausa();
	}

	private void step7(String pathPreBatch, int cantStep) throws Exception {
		gui.jLabelTimer7.iniciar();
		gui.jLabel7.setText("Archivo a copiar: " + superficie.getPath());
		gui.jProgressBar7.setValue(50);
		SO.copy(superficie.getPath(), pathPreBatch + File.separatorChar + superficie.getName());
		gui.jCheckBox7.setSelected(true);
		gui.jLabel7_1.setText("Archivo a copiado: " + pathPreBatch + File.separatorChar + inmueble.getName());
		gui.jProgressBar.setValue((7 / cantStep) * 100);
		gui.jProgressBar7.setValue(100);
		gui.jLabelTimer7.pausa();
	}

	private void step8(String pathPreBatch, int cantStep) throws Exception {
		gui.jLabelTimer8.iniciar();
		gui.jLabel8.setText("Archivo a copiar: " + subcuenta.getPath());
		gui.jProgressBar8.setValue(50);
		SO.copy(subcuenta.getPath(), pathPreBatch + File.separatorChar + subcuenta.getName());
		gui.jCheckBox8.setSelected(true);
		gui.jLabel8_1.setText("Archivo copiado: " + pathPreBatch + File.separatorChar + subcuenta.getName());
		gui.jProgressBar.setValue((8 / cantStep) * 100);
		gui.jProgressBar8.setValue(100);
		gui.jLabelTimer8.pausa();
	}

	private void step9(String pathPreBatch, int cantStep) throws Exception {
		if (datosPostales != null) {
			gui.jLabelTimer9.iniciar();
			gui.jLabel9.setText("Archivo a copiar: " + datosPostales.getPath());
			gui.jProgressBar9.setValue(50);
			SO.copy(datosPostales.getPath(), pathPreBatch + File.separatorChar + datosPostales.getName());
			gui.jCheckBox9.setSelected(true);
			gui.jLabel9_1.setText("Archivo copiado: " + pathPreBatch + File.separatorChar + datosPostales.getName());
			gui.jProgressBar.setValue((9 / cantStep) * 100);
			gui.jProgressBar9.setValue(100);
			gui.jLabelTimer9.pausa();
		} else {
			gui.jProgressBar.setValue((9 / cantStep) * 100);
		}
	}

	private String step10(ImportUnlToTable importUnlToTable, String pathPreBatch, Timestamp t, ConnectionWrapper connectionWrapper, int cantStep) throws IOException, Exception {
		gui.jTabbedPane1.setSelectedIndex(1);

		unidad = new File(pathPreBatch + File.separatorChar + unidad.getName());
		inmueble = new File(pathPreBatch + File.separatorChar + inmueble.getName());
		superficie = new File(pathPreBatch + File.separatorChar + superficie.getName());
		subcuenta = new File(pathPreBatch + File.separatorChar + subcuenta.getName());
		if (datosPostales != null) {
			datosPostales = new File(pathPreBatch + File.separatorChar + datosPostales.getName());
		}

		int progress = (10 / cantStep) * 100;

		String p = "";
		if (userSystem.getDni() != null) {
			p = userSystem.getDni() + " ";
		} else if (userSystem.getCuil() != null) {
			p = userSystem.getCuil() + " ";
		}
		p += userSystem.getFamilyName() + " " + userSystem.getGivenName();

		if (datosPostales == null) {
			// importUnlToTable.importTable("cclip.aacc_unidad_nov", unidad, connectionWrapper, t, p, t, p);
			gui.jProgressBar14.setValue(progress);
		} else {
			importUnlToTable.importTable(gui.jProgressBar10, progress, gui.jLabelTimer10, gui.jLabel10, gui.jLabel10_1, "cclip.aacc_unidad", unidad, connectionWrapper, t, p, t, p);
		}

		return p;
	}

	private void step11(ImportUnlToTable importUnlToTable, Timestamp t, ConnectionWrapper connectionWrapper, String p, int cantStep) throws IOException, Exception {

		int progress = (11 / cantStep) * 100;

		if (datosPostales == null) {
			// importUnlToTable.importTable("cclip.aacc_inmueble_nov", inmueble, connectionWrapper, t, p, t, p);
			gui.jProgressBar14.setValue(progress);
		} else {
			importUnlToTable.importTable(gui.jProgressBar11, progress, gui.jLabelTimer11, gui.jLabel11, gui.jLabel11_1, "cclip.aacc_inmueble", inmueble, connectionWrapper, t, p, t, p);

		}
	}

	private void step12(ImportUnlToTable importUnlToTable, Timestamp t, ConnectionWrapper connectionWrapper, String p, int cantStep) throws IOException, Exception {

		int progress = (12 / cantStep) * 100;

		if (datosPostales == null) {
			// importUnlToTable.importTable("cclip.aacc_superficie_nov", superficie, connectionWrapper, t, p, t, p);
			gui.jProgressBar14.setValue(progress);
		} else {
			importUnlToTable.importTable(gui.jProgressBar12, progress, gui.jLabelTimer12, gui.jLabel12, gui.jLabel12_1, "cclip.aacc_superficie", superficie, connectionWrapper, t, p, t, p);

		}
	}

	private void step13(ImportUnlToTable importUnlToTable, Timestamp t, ConnectionWrapper connectionWrapper, String p, int cantStep) throws IOException, Exception {

		int progress = (13 / cantStep) * 100;

		if (datosPostales == null) {
			// importUnlToTable.importTable("cclip.aacc_subcuenta_nov", subcuenta, connectionWrapper, t, p, t, p);
			gui.jProgressBar14.setValue(progress);
		} else {
			importUnlToTable.importTable(gui.jProgressBar13, progress, gui.jLabelTimer13, gui.jLabel13, gui.jLabel13_1, "cclip.aacc_subcuenta", subcuenta, connectionWrapper, t, p, t, p);

		}
	}

	private void step14(ImportUnlToTable importUnlToTable, Timestamp t, ConnectionWrapper connectionWrapper, String p, int cantStep) throws IOException, Exception {

		int progress = (14 / cantStep) * 100;

		if (datosPostales == null) {
			gui.jProgressBar14.setValue(progress);
		} else {
			importUnlToTable.importTable(gui.jProgressBar14, progress, gui.jLabelTimer14, gui.jLabel14, gui.jLabel14_1, "cclip.aacc_datos_postales", datosPostales, connectionWrapper, t, p, t, p);
		}
	}

	private void step15(ConnectionWrapper connectionWrapper, int cantStep) throws IOException, Exception {

		gui.jLabelTimer15.iniciar();

		int progress = (15 / cantStep) * 100;

		// String sql = SO.readFilePlainText(linoProperties.getUrlFiles() + File.separatorChar + "import_db_ice.sql");

		String sql = "DROP TABLE IF EXISTS cclip.aacc_superficie_new_case CASCADE;CREATE TABLE cclip.aacc_superficie_new_case(	id varchar NOT NULL, -- id	erased boolean NOT NULL, -- erased	audit_date_create timestamp NOT NULL, -- auditDateCreate	audit_user_create varchar NOT NULL, -- auditUserCreate	c1 varchar, -- c1	c2 varchar, -- c2	c3 varchar, -- c3	c4 varchar, -- c4	c5 varchar, -- c5	c6 varchar, -- c6	c7 varchar, -- c7	c8 varchar, -- c8	c9 varchar, -- c9	c10 varchar, -- c10	c11 varchar, -- c11	c12 varchar, -- c12	c13 varchar, -- c13	c14 varchar, -- c14	c15 varchar, -- c15	c16 varchar, -- c16	c17 varchar, -- c17	c18 varchar, -- c18	c19 varchar, -- c19	c20 varchar, -- c20	c21 varchar, -- c21	c22 varchar, -- c22	c23 varchar, -- c23	c24 varchar, -- c24	c25 varchar, -- c25	c26 varchar, -- c26	c27 varchar, -- c27	c28 varchar, -- c28	c29 varchar, -- c29	c30 varchar, -- c30	c31 varchar, -- c31	c32 varchar, -- c32	c33 varchar, -- c33	c34 varchar, -- c34	c35 varchar, -- c35	c36 varchar, -- c36	c37 varchar, -- c37	c38 varchar, -- c38	c39 varchar, -- c39	c40 varchar, -- c40	c41 varchar, -- c41	c42 varchar, -- c42	c43 varchar, -- c43	c44 varchar, -- c44	c45 varchar, -- c45	c46 varchar, -- c46	c47 varchar, -- c47	c48 varchar, -- c48	c49 varchar, -- c49	c50 varchar, -- c50	c51 varchar, -- c51	c52 varchar, -- c52	c53 varchar, -- c53	c54 varchar, -- c54	c55 varchar, -- c55	c56 varchar, -- c56	c57 varchar, -- c57	c58 varchar, -- c58	c59 varchar, -- c59	c60 varchar, -- c60	c61 varchar, -- c61	c62 varchar, -- c62	c63 varchar, -- c63	c64 varchar, -- c64	c65 varchar, -- c65	c66 varchar, -- c66	c67 varchar, -- c67	c68 varchar, -- c68	c69 varchar, -- c69	c70 varchar, -- c70	c71 varchar, -- c71	c72 varchar, -- c72	c73 varchar, -- c73	c74 varchar, -- c74	c75 varchar, -- c75	c76 varchar, -- c76	c77 varchar, -- c77	c78 varchar, -- c78	c79 varchar, -- c79	c80 varchar, -- c80	c81 varchar, -- c81	c82 varchar, -- c82	c83 varchar, -- c83	c84 varchar, -- c84	c85 varchar, -- c85	c86 varchar, -- c86	c87 varchar, -- c87	c88 varchar, -- c88	c89 varchar, -- c89	c90 varchar, -- c90	c91 varchar, -- c91	c92 varchar, -- c92	c93 varchar, -- c93	c94 varchar, -- c94	c95 varchar, -- c95	c96 varchar, -- c96	c97 varchar, -- c97	c98 varchar, -- c98	c99 varchar, -- c99	c100 varchar, -- c100	c101 varchar, -- c101	c102 varchar, -- c102	c103 varchar, -- c103	c104 varchar, -- c104	c105 varchar, -- c105	c106 varchar, -- c106	c107 varchar, -- c107	c108 varchar, -- c108	c109 varchar, -- c109	c110 varchar, -- c110	c111 varchar, -- c111	c112 varchar, -- c112	c113 varchar, -- c113	c114 varchar, -- c114	c115 varchar, -- c115	c116 varchar, -- c116	c117 varchar, -- c117	c118 varchar, -- c118	c119 varchar, -- c119	c120 varchar, -- c120	c121 varchar, -- c121	c122 varchar, -- c122	c123 varchar, -- c123	c124 varchar, -- c124	c125 varchar, -- c125	c126 varchar, -- c126	c127 varchar, -- c127	c128 varchar, -- c128	c129 varchar, -- c129	c130 varchar, -- c130	c131 varchar, -- c131	c132 varchar, -- c132	c133 varchar, -- c133	c134 varchar, -- c134	c135 varchar, -- c135	c136 varchar, -- c136	c137 varchar, -- c137	c138 varchar, -- c138	c139 varchar, -- c139	c140 varchar, -- c140	c141 varchar, -- c141	c142 varchar, -- c142	c143 varchar, -- c143	c144 varchar, -- c144	c145 varchar, -- c145	c146 varchar, -- c146	c147 varchar, -- c147	c148 varchar, -- c148	c149 varchar, -- c149	c150 varchar, -- c150	c151 varchar, -- c151	c152 varchar, -- c152	c153 varchar, -- c153	c154 varchar, -- c154	c155 varchar, -- c155	c156 varchar, -- c156	c157 varchar, -- c157	c158 varchar, -- c158	c159 varchar, -- c159	c160 varchar, -- c160	c161 varchar, -- c161	c162 varchar, -- c162	c163 varchar, -- c163	c164 varchar, -- c164	c165 varchar, -- c165	c166 varchar, -- c166	c167 varchar, -- c167	c168 varchar, -- c168	c169 varchar, -- c169	c170 varchar, -- c170	c171 varchar, -- c171	c172 varchar, -- c172	c173 varchar, -- c173	c174 varchar, -- c174	c175 varchar, -- c175	c176 varchar, -- c176	c177 varchar, -- c177	c178 varchar, -- c178	c179 varchar, -- c179	c180 varchar, -- c180	c181 varchar, -- c181	c182 varchar, -- c182	c183 varchar, -- c183	c184 varchar, -- c184	c185 varchar, -- c185	c186 varchar, -- c186	c187 varchar, -- c187	c188 varchar, -- c188	c189 varchar, -- c189	c190 varchar, -- c190	c191 varchar, -- c191	c192 varchar, -- c192	c193 varchar, -- c193	c194 varchar, -- c194	c195 varchar, -- c195	c196 varchar, -- c196	c197 varchar, -- c197	c198 varchar, -- c198	c199 varchar, -- c199	c200 varchar, -- c200	c201 varchar, -- c201	c202 varchar, -- c202	c203 varchar, -- c203	c204 varchar, -- c204	c205 varchar, -- c205	c206 varchar, -- c206	c207 varchar, -- c207	c208 varchar, -- c208	c209 varchar, -- c209	c210 varchar, -- c210	c211 varchar, -- c211	c212 varchar, -- c212	c213 varchar, -- c213	c214 varchar, -- c214	c215 varchar, -- c215	c216 varchar, -- c216	c217 varchar, -- c217	c218 varchar, -- c218	c219 varchar, -- c219	c220 varchar, -- c220	c221 varchar, -- c221	c222 varchar, -- c222	c223 varchar, -- c223	c224 varchar, -- c224	c225 varchar, -- c225	c226 varchar, -- c226	c227 varchar, -- c227	c228 varchar, -- c228	c229 varchar, -- c229	c230 varchar, -- c230	c231 varchar, -- c231	c232 varchar, -- c232	c233 varchar, -- c233	c234 varchar, -- c234	c235 varchar, -- c235	c236 varchar, -- c236	c237 varchar, -- c237	c238 varchar, -- c238	c239 varchar, -- c239	c240 varchar, -- c240	c241 varchar, -- c241	c242 varchar, -- c242	c243 varchar, -- c243	c244 varchar, -- c244	c245 varchar, -- c245	c246 varchar, -- c246	c247 varchar, -- c247	c248 varchar, -- c248	c249 varchar, -- c249	c250 varchar, -- c250	c251 varchar, -- c251	c252 varchar, -- c252	c253 varchar, -- c253	c254 varchar, -- c254	c255 varchar, -- c255	c256 varchar, -- c256	c257 varchar, -- c257	c258 varchar, -- c258	c259 varchar, -- c259	c260 varchar, -- c260	c261 varchar, -- c261	c262 varchar, -- c262	c263 varchar, -- c263	c264 varchar, -- c264	c265 varchar, -- c265	c266 varchar, -- c266	c267 varchar, -- c267	c268 varchar, -- c268	c269 varchar, -- c269	c270 varchar, -- c270	c271 varchar, -- c271	c272 varchar, -- c272	c273 varchar, -- c273	c274 varchar, -- c274	c275 varchar, -- c275	c276 varchar, -- c276	c277 varchar, -- c277	c278 varchar, -- c278	c279 varchar, -- c279	c280 varchar, -- c280	c281 varchar, -- c281	c282 varchar, -- c282	c283 varchar, -- c283	c284 varchar, -- c284	c285 varchar, -- c285	c286 varchar, -- c286	c287 varchar, -- c287	c288 varchar, -- c288	c289 varchar, -- c289	c290 varchar, -- c290	c291 varchar, -- c291	c292 varchar, -- c292	c293 varchar, -- c293	c294 varchar, -- c294	c295 varchar, -- c295	c296 varchar, -- c296	c297 varchar, -- c297	c298 varchar, -- c298	c299 varchar, -- c299	c300 varchar, -- c300	c301 varchar, -- c301	c302 varchar, -- c302	c303 varchar, -- c303	c304 varchar, -- c304	c305 varchar, -- c305	c306 varchar, -- c306	c307 varchar, -- c307	c308 varchar, -- c308	c309 varchar, -- c309	c310 varchar, -- c310	c311 varchar, -- c311	c312 varchar, -- c312	c313 varchar, -- c313	c314 varchar, -- c314	c315 varchar, -- c315	c316 varchar, -- c316	c317 varchar, -- c317	c318 varchar, -- c318	c319 varchar, -- c319	c320 varchar, -- c320	c321 varchar, -- c321	c322 varchar, -- c322	c323 varchar, -- c323	c324 varchar, -- c324	c325 varchar, -- c325	c326 varchar, -- c326	c327 varchar, -- c327	c328 varchar, -- c328	c329 varchar, -- c329	c330 varchar, -- c330	c331 varchar, -- c331	c332 varchar, -- c332	c333 varchar, -- c333	c334 varchar, -- c334	c335 varchar, -- c335	c336 varchar, -- c336	c337 varchar, -- c337	c338 varchar, -- c338	c339 varchar, -- c339	c340 varchar, -- c340	c341 varchar, -- c341	c342 varchar, -- c342	c343 varchar, -- c343	c344 varchar, -- c344	c345 varchar, -- c345	c346 varchar, -- c346	c347 varchar, -- c347	c348 varchar, -- c348	c349 varchar, -- c349	c350 varchar, -- c350	c351 varchar, -- c351	c352 varchar, -- c352	c353 varchar, -- c353	c354 varchar, -- c354	c355 varchar, -- c355	c356 varchar, -- c356	c357 varchar, -- c357	c358 varchar, -- c358	c359 varchar, -- c359	c360 varchar, -- c360	c361 varchar, -- c361	c362 varchar, -- c362	c363 varchar, -- c363	c364 varchar, -- c364	c365 varchar, -- c365	c366 varchar, -- c366	c367 varchar, -- c367	c368 varchar, -- c368	c369 varchar, -- c369	c370 varchar, -- c370	c371 varchar, -- c371	c372 varchar, -- c372	c373 varchar, -- c373	c374 varchar, -- c374	c375 varchar, -- c375	c376 varchar, -- c376	c377 varchar, -- c377	c378 varchar, -- c378	c379 varchar, -- c379	c380 varchar, -- c380	c381 varchar, -- c381	c382 varchar, -- c382	c383 varchar, -- c383	c384 varchar, -- c384	c385 varchar, -- c385	c386 varchar, -- c386	c387 varchar, -- c387	c388 varchar, -- c388	c389 varchar, -- c389	c390 varchar, -- c390	c391 varchar, -- c391	c392 varchar, -- c392	c393 varchar, -- c393	c394 varchar, -- c394	c395 varchar, -- c395	c396 varchar, -- c396	c397 varchar, -- c397	c398 varchar, -- c398	c399 varchar, -- c399	c400 varchar, -- c400	c401 varchar, -- c401	c402 varchar, -- c402	c403 varchar, -- c403	c404 varchar, -- c404	c405 varchar, -- c405	c406 varchar, -- c406	c407 varchar, -- c407	c408 varchar, -- c408	c409 varchar, -- c409	c410 varchar, -- c410	c411 varchar, -- c411	c412 varchar, -- c412	c413 varchar, -- c413	c414 varchar, -- c414	c415 varchar, -- c415	c416 varchar, -- c416	c417 varchar, -- c417	c418 varchar, -- c418	c419 varchar, -- c419	c420 varchar, -- c420	c421 varchar, -- c421	c422 varchar, -- c422	c423 varchar, -- c423	c424 varchar, -- c424	c425 varchar, -- c425	c426 varchar, -- c426	c427 varchar, -- c427	c428 varchar, -- c428	c429 varchar, -- c429	c430 varchar, -- c430	c431 varchar, -- c431	c432 varchar, -- c432	c433 varchar, -- c433	c434 varchar, -- c434	c435 varchar, -- c435	c436 varchar, -- c436	c437 varchar, -- c437	c438 varchar, -- c438	c439 varchar, -- c439	c440 varchar, -- c440	c441 varchar, -- c441	c442 varchar, -- c442	c443 varchar, -- c443	c444 varchar, -- c444	c445 varchar, -- c445	c446 varchar, -- c446	c447 varchar, -- c447	c448 varchar, -- c448	c449 varchar, -- c449	c450 varchar, -- c450	c451 varchar, -- c451	c452 varchar, -- c452	c453 varchar, -- c453	c454 varchar, -- c454	c455 varchar, -- c455	c456 varchar, -- c456	c457 varchar, -- c457	c458 varchar, -- c458	c459 varchar, -- c459	c460 varchar, -- c460	c461 varchar, -- c461	c462 varchar, -- c462	c463 varchar, -- c463	c464 varchar, -- c464	c465 varchar, -- c465	c466 varchar, -- c466	c467 varchar, -- c467	c468 varchar, -- c468	c469 varchar, -- c469	c470 varchar, -- c470	c471 varchar, -- c471	c472 varchar, -- c472	c473 varchar, -- c473	c474 varchar, -- c474	c475 varchar, -- c475	c476 varchar, -- c476	c477 varchar, -- c477	c478 varchar, -- c478	c479 varchar, -- c479	c480 varchar, -- c480	c481 varchar, -- c481	c482 varchar, -- c482	c483 varchar, -- c483	c484 varchar, -- c484	c485 varchar, -- c485	c486 varchar, -- c486	c487 varchar, -- c487	c488 varchar, -- c488	c489 varchar, -- c489	c490 varchar, -- c490	c491 varchar, -- c491	c492 varchar, -- c492	c493 varchar, -- c493	c494 varchar, -- c494	c495 varchar, -- c495	c496 varchar, -- c496	c497 varchar, -- c497	c498 varchar, -- c498	c499 varchar, -- c499	c500 varchar, -- c500	CONSTRAINT aacc_superficie_new_case_pkey PRIMARY KEY (id));";

		connectionWrapper.getConnection().createStatement().execute(sql);

		connectionWrapper.update(sql, null);

		gui.jProgressBar15.setValue(100);
		gui.jLabelTimer15.pausa();
		gui.jProgressBar.setValue((15 / cantStep) * 100);

	}
	
	private void step16(ConnectionWrapper connectionWrapper, int cantStep) throws IOException, Exception {

		gui.jLabelTimer16.iniciar();

		int progress = (15 / cantStep) * 100;

		// String sql = SO.readFilePlainText(linoProperties.getUrlFiles() + File.separatorChar + "import_db_ice.sql");

		String sql = "INSERT INTO cclip.aacc_superficie_new_case (SELECT * FROM cclip.aacc_superficie);";

		connectionWrapper.getConnection().createStatement().execute(sql);

		connectionWrapper.update(sql, null);

		gui.jProgressBar16.setValue(100);
		gui.jLabelTimer16.pausa();
		gui.jProgressBar.setValue((16 / cantStep) * 100);

	}
	
	private void step17(ConnectionWrapper connectionWrapper, int cantStep) throws IOException, Exception {

		gui.jLabelTimer17.iniciar();

		int progress = (17 / cantStep) * 100;

		// String sql = SO.readFilePlainText(linoProperties.getUrlFiles() + File.separatorChar + "import_db_ice.sql");

		String sql = "UPDATE cclip.aacc_superficie_new_case SET c9 = TRIM(c9), c15 = TRIM(c15), c13 = TRIM(c13), c14 = TRIM(c14), c3 = TRIM(c3), c2 = TRIM(c2), c5 = TRIM(c5), c1 = TRIM(c1), c4 = TRIM(c4);";

		connectionWrapper.getConnection().createStatement().execute(sql);

		connectionWrapper.update(sql, null);

		gui.jProgressBar17.setValue(100);
		gui.jLabelTimer17.pausa();
		gui.jProgressBar.setValue((17 / cantStep) * 100);

	}
	
	private void step18(ConnectionWrapper connectionWrapper, int cantStep) throws IOException, Exception {

		gui.jLabelTimer18.iniciar();

		int progress = (18 / cantStep) * 100;

		// String sql = SO.readFilePlainText(linoProperties.getUrlFiles() + File.separatorChar + "import_db_ice.sql");

		String sql = "UPDATE cclip.aacc_superficie_new_case SET c9 = null WHERE LENGTH(c9) = 0;";

		connectionWrapper.getConnection().createStatement().execute(sql);

		connectionWrapper.update(sql, null);

		gui.jProgressBar18.setValue(100);
		gui.jLabelTimer18.pausa();
		gui.jProgressBar.setValue((18 / cantStep) * 100);

	}
	
	private void step19(ConnectionWrapper connectionWrapper, int cantStep) throws IOException, Exception {

		gui.jLabelTimer19.iniciar();

		int progress = (19 / cantStep) * 100;

		// String sql = SO.readFilePlainText(linoProperties.getUrlFiles() + File.separatorChar + "import_db_ice.sql");

		String sql = "UPDATE cclip.aacc_superficie_new_case SET c15 = null WHERE LENGTH(c15) = 0;";

		connectionWrapper.getConnection().createStatement().execute(sql);

		connectionWrapper.update(sql, null);

		gui.jProgressBar19.setValue(100);
		gui.jLabelTimer19.pausa();
		gui.jProgressBar.setValue((19 / cantStep) * 100);

	}
	
	private void step20(ConnectionWrapper connectionWrapper, int cantStep) throws IOException, Exception {

		gui.jLabelTimer20.iniciar();

		int progress = (20 / cantStep) * 100;

		// String sql = SO.readFilePlainText(linoProperties.getUrlFiles() + File.separatorChar + "import_db_ice.sql");

		String sql = "UPDATE cclip.aacc_superficie_new_case SET c13 = null WHERE LENGTH(c13) = 0;";

		connectionWrapper.getConnection().createStatement().execute(sql);

		connectionWrapper.update(sql, null);

		gui.jProgressBar20.setValue(100);
		gui.jLabelTimer20.pausa();
		gui.jProgressBar.setValue((20 / cantStep) * 100);

	}
	
	private void step21(ConnectionWrapper connectionWrapper, int cantStep) throws IOException, Exception {

		gui.jLabelTimer21.iniciar();

		int progress = (21 / cantStep) * 100;

		// String sql = SO.readFilePlainText(linoProperties.getUrlFiles() + File.separatorChar + "import_db_ice.sql");

		String sql = "UPDATE cclip.aacc_superficie_new_case SET c14 = null WHERE LENGTH(c14) = 0;";

		connectionWrapper.getConnection().createStatement().execute(sql);

		connectionWrapper.update(sql, null);

		gui.jProgressBar21.setValue(100);
		gui.jLabelTimer21.pausa();
		gui.jProgressBar.setValue((21 / cantStep) * 100);

	}
	
	private void step22(ConnectionWrapper connectionWrapper, int cantStep) throws IOException, Exception {

		gui.jLabelTimer22.iniciar();

		int progress = (22 / cantStep) * 100;

		// String sql = SO.readFilePlainText(linoProperties.getUrlFiles() + File.separatorChar + "import_db_ice.sql");

		String sql = "UPDATE cclip.aacc_superficie_new_case SET c3 = null WHERE LENGTH(c3) = 0;";

		connectionWrapper.getConnection().createStatement().execute(sql);

		connectionWrapper.update(sql, null);

		gui.jProgressBar22.setValue(100);
		gui.jLabelTimer22.pausa();
		gui.jProgressBar.setValue((22 / cantStep) * 100);

	}
	
	private void step23(ConnectionWrapper connectionWrapper, int cantStep) throws IOException, Exception {

		gui.jLabelTimer23.iniciar();

		int progress = (23 / cantStep) * 100;

		// String sql = SO.readFilePlainText(linoProperties.getUrlFiles() + File.separatorChar + "import_db_ice.sql");

		String sql = "";

		connectionWrapper.getConnection().createStatement().execute(sql);

		connectionWrapper.update(sql, null);

		gui.jProgressBar23.setValue(100);
		gui.jLabelTimer23.pausa();
		gui.jProgressBar.setValue(progress);

	}
	
	private void step24(ConnectionWrapper connectionWrapper, int cantStep) throws IOException, Exception {

		gui.jLabelTimer24.iniciar();

		int progress = (24 / cantStep) * 100;

		// String sql = SO.readFilePlainText(linoProperties.getUrlFiles() + File.separatorChar + "import_db_ice.sql");

		String sql = "";

		connectionWrapper.getConnection().createStatement().execute(sql);

		connectionWrapper.update(sql, null);

		gui.jProgressBar24.setValue(100);
		gui.jLabelTimer24.pausa();
		gui.jProgressBar.setValue(progress);

	}
	
	private void step25(ConnectionWrapper connectionWrapper, int cantStep) throws IOException, Exception {

		gui.jLabelTimer23.iniciar();

		int progress = (25 / cantStep) * 100;

		// String sql = SO.readFilePlainText(linoProperties.getUrlFiles() + File.separatorChar + "import_db_ice.sql");

		String sql = "";

		connectionWrapper.getConnection().createStatement().execute(sql);

		connectionWrapper.update(sql, null);

		gui.jProgressBar25.setValue(100);
		gui.jLabelTimer25.pausa();
		gui.jProgressBar.setValue(progress);


		
	}
	
//	private void step23(ConnectionWrapper connectionWrapper, int cantStep) throws IOException, Exception {
//
//		gui.jLabelTimer23.iniciar();
//
//		int progress = (23 / cantStep) * 100;
//
//		// String sql = SO.readFilePlainText(linoProperties.getUrlFiles() + File.separatorChar + "import_db_ice.sql");
//
//		String sql = "";
//
//		connectionWrapper.getConnection().createStatement().execute(sql);
//
//		connectionWrapper.update(sql, null);
//
//		gui.jProgressBar23.setValue(100);
//		gui.jLabelTimer23.pausa();
//		gui.jProgressBar.setValue(progress);
//
//	}
	
//	private void step23(ConnectionWrapper connectionWrapper, int cantStep) throws IOException, Exception {
//
//		gui.jLabelTimer23.iniciar();
//
//		int progress = (23 / cantStep) * 100;
//
//		// String sql = SO.readFilePlainText(linoProperties.getUrlFiles() + File.separatorChar + "import_db_ice.sql");
//
//		String sql = "";
//
//		connectionWrapper.getConnection().createStatement().execute(sql);
//
//		connectionWrapper.update(sql, null);
//
//		gui.jProgressBar23.setValue(100);
//		gui.jLabelTimer23.pausa();
//		gui.jProgressBar.setValue(progress);
//
//	}
	
//	private void step23(ConnectionWrapper connectionWrapper, int cantStep) throws IOException, Exception {
//
//		gui.jLabelTimer23.iniciar();
//
//		int progress = (23 / cantStep) * 100;
//
//		// String sql = SO.readFilePlainText(linoProperties.getUrlFiles() + File.separatorChar + "import_db_ice.sql");
//
//		String sql = "";
//
//		connectionWrapper.getConnection().createStatement().execute(sql);
//
//		connectionWrapper.update(sql, null);
//
//		gui.jProgressBar23.setValue(100);
//		gui.jLabelTimer23.pausa();
//		gui.jProgressBar.setValue(progress);
//
//	}
//	
//	private void step23(ConnectionWrapper connectionWrapper, int cantStep) throws IOException, Exception {
//
//		gui.jLabelTimer23.iniciar();
//
//		int progress = (23 / cantStep) * 100;
//
//		// String sql = SO.readFilePlainText(linoProperties.getUrlFiles() + File.separatorChar + "import_db_ice.sql");
//
//		String sql = "";
//
//		connectionWrapper.getConnection().createStatement().execute(sql);
//
//		connectionWrapper.update(sql, null);
//
//		gui.jProgressBar23.setValue(100);
//		gui.jLabelTimer23.pausa();
//		gui.jProgressBar.setValue(progress);
//
//	}

	public void isStop() throws StopEx {
		if (stop) {
			gui.jButtonStart.setVisible(true);
			gui.jButtonStop.setVisible(false);
			gui.jButtonCancel.setVisible(true);

			throw new StopEx();
		}
	}

	private class StopEx extends RuntimeException {

	}
}
