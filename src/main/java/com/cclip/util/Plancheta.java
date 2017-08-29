package com.cclip.util;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

public class Plancheta {

	// public static void main(String[] args) {
	// String path =
	// "/home/java/jaspe/cclip/cclip_db/parcelarios y mapas/parcelarios_mapas";
	//
	// try {
	// byte[] b = new Plancheta().getDistrictCatastro(path, "05");
	// System.out.println(b.length);
	//
	// b = new Plancheta().getBlockCatastro(path, "01", "08", "008");
	// System.out.println(b.length);
	//
	// b = new Plancheta().getZoneCatastroZip(path, "10", "07");
	// System.out.println(b.length);
	//
	// b = new Plancheta().getDistrictCatastroZip(path, "10");
	// System.out.println(b.length);
	//
	// // byte[] b = getZoneCatastroFormsZip(conn, path, idZoneCatastro)
	//
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	public File getDistrictCatastroFile(String path, String code) throws IOException {
		// File folder = new File(path + File.separatorChar + "distritos");
		File folder = new File(path);

		File[] files = folder.listFiles();

		for (File file : files) {
			if (file.getName().trim().toLowerCase().startsWith("d" + code)) {
				// System.out.println(file);
				return file;
			}
		}

		return null;
	}

	public byte[] getDistrictCatastro(String path, String code) throws IOException {
		// File folder = new File(path + File.separatorChar + "distritos");
		File folder = new File(path);

		File[] files = folder.listFiles();

		if (files != null) {
			for (File file : files) {
				if (file.getName().trim().toLowerCase().startsWith("d" + code)) {
					// System.out.println(file);
					return readBinary(file.getPath());
				}
			}
		}

		return null;
	}

	public String getDistrictCatastroExt(String path, String code) throws IOException {
		// File folder = new File(path + File.separatorChar + "distritos");
		File folder = new File(path);

		File[] files = folder.listFiles();

		if (files != null) {
			for (File file : files) {
				if (file.getName().trim().toLowerCase().startsWith("d" + code)) {
					// System.out.println(file);
					return file.getName().split("[.]")[1];
				}
			}
		}

		return null;
	}

	public byte[] getDistrictCatastroZip(String path, String districtCode) throws IOException {

		List<File> filesZip = new ArrayList<File>();

		File districtFile = getDistrictCatastroFile(path, districtCode);
		filesZip.add(districtFile);

		File folder = new File(path + File.separatorChar + "manzanas");

		File[] files = folder.listFiles();

		for (File file : files) {
			if (file.getName().trim().toLowerCase().startsWith(districtCode)) {
				// System.out.println(file);
				filesZip.add(file);
			}
		}

		File[] f = new File[filesZip.size()];
		f = filesZip.toArray(f);

		return buildZip(path, "parcelarios_mapas_" + districtCode, f);
	}

	public File getZoneCatastroZip(String pathDistrital, String pathPlancheta, String districtCode, String code, String pathTo) throws IOException {

		List<File> filesZip = new ArrayList<File>();

		File districtFile = getDistrictCatastroFile(pathDistrital, districtCode);
		filesZip.add(districtFile);

		// File folder = new File(path + File.separatorChar + "manzanas");
		File folder = new File(pathPlancheta);

		File[] files = folder.listFiles();

		for (File file : files) {
			if (file.getName().trim().toLowerCase().startsWith(districtCode + "-" + code)) {
				filesZip.add(file);

			}
		}

		File[] f = new File[filesZip.size()];
		f = filesZip.toArray(f);
		
		
//		for (File file : f) {
//			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx " + file);
//		}
		

		return buildZipReturnFile(pathTo, "parcelarios_mapas_" + districtCode + "_" + code, f);
	}

	public File getZoneCatastroReportZip(String path, String districtCode, String zoneCode, String year, String pathTo) throws IOException {

		List<File> filesZip = new ArrayList<File>();

		File folder = new File(path);

		File[] files = folder.listFiles();

		for (File file : files) {

			String c = districtCode + "_" + zoneCode;

			// System.out.println(file.getName() + " --- "
			// + file.getName().trim().toLowerCase().startsWith(c)
			// + " --- " + c);

			String n = file.getName().trim().toLowerCase();

			if (n.startsWith(c) && n.contains("_" + year + "_")) {

				filesZip.add(file);
			}
		}

		File[] f = new File[filesZip.size()];
		f = filesZip.toArray(f);

		return buildZipReturnFile(pathTo, "planillas_barrido_" + year + "_" + districtCode + "_" + zoneCode, f);
	}

	public File printZoneCatastroReport(String path, String districtCode, String zoneCode, String year, String pathTo) throws IOException {

		List<File> filesZip = new ArrayList<File>();

		File folder = new File(path);

		// System.out.println(folder.getAbsolutePath());

		File[] files = folder.listFiles();

		// System.out.println(files);

		for (File file : files) {

			if (file.getName().trim().toLowerCase().startsWith(year + districtCode + zoneCode)) {
				// System.out.println(file);
				filesZip.add(file);
				Print.print(file);
			}
		}

		File[] f = new File[filesZip.size()];
		f = filesZip.toArray(f);

		return buildZipReturnFile(pathTo, "planillas_barrido_" + year + "_" + districtCode + "_" + zoneCode, f);
	}

	// public byte[] getZoneCatastroFormsZip(Connection conn, String path,
	// String scanningZoneTaskFormId)
	// throws IOException, SQLException {
	//
	// List<File> filesZip = new ArrayList<File>();
	//
	// //
	// ============================================================================0
	//
	// String sql =
	// "SELECT url FROM v_file_scanning_block_task_form_list WHERE zone_catastro_id = ?;";
	//
	// PreparedStatement ps = conn.prepareStatement(sql);
	// ps.setString(1, scanningZoneTaskFormId);
	//
	// ResultSet rs = ps.executeQuery(sql);
	//
	// while(rs.next()){
	// String url = rs.getString("url");
	// if(url != null && url.trim().length() > 0){
	// File file = new File(url);
	// filesZip.add(file);
	// }
	// }
	//
	//
	// //
	// ============================================================================0
	//
	// File[] f = new File[filesZip.size()];
	// f = filesZip.toArray(f);
	//
	// return buildZip(path, "forms_sccaning_zone_" + scanningZoneTaskFormId,
	// f);
	// }

	public byte[] getBlockCatastroFileReport(String path, String code, Integer year, Integer numberScnning) throws IOException {

		File folder = new File(path);

		File[] files = folder.listFiles();

		String c = UtilCadastre.formatCadastralCodeB(code) + "_" + year + "_" + numberScnning;

		for (File file : files) {
			String n = file.getName().trim().toLowerCase();

			if (n.startsWith(c)) {
				// System.out.println(file);
				return readBinary(file.getPath());
			}
		}

		return null;
	}

	public byte[] getBlockCatastro(String path, String districtCode, String zoneCode, String code) throws IOException {
		File folder = new File(path + File.separatorChar + "manzanas");

		File[] files = folder.listFiles();

		for (File file : files) {
			if (file.getName().trim().toLowerCase().startsWith(districtCode + "-" + zoneCode + "-" + code)) {
				// System.out.println(file);
				return readBinary(file.getPath());
			}
		}

		return null;
	}

	private File buildZipReturnFile(String path, String fileZipName, File[] files) throws IOException {

		String filePath = path + File.separatorChar + fileZipName + ".zip";

		// System.out.println(filePath);

		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(filePath));

		// create byte buffer
		byte[] buffer = new byte[1024];

		for (int i = 0; i < files.length; i++) {

//			 System.out.println("Adding filexxxxxxxxxxx: " + files[i]);

			FileInputStream fis = new FileInputStream(files[i]);

			// begin writing a new ZIP entry, positions the stream to
			// the start of the entry data
			zos.putNextEntry(new ZipEntry(files[i].getName()));

			int length;

			while ((length = fis.read(buffer)) > 0) {
				zos.write(buffer, 0, length);
			}

			zos.closeEntry();

			// close the InputStream
			fis.close();
		}

		// close the ZipOutputStream
		zos.close();

		return new File(filePath);
	}

	private byte[] buildZip(String path, String fileZipName, File[] files) throws IOException {

		String filePath = path + File.separatorChar + fileZipName + ".zip";

		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(filePath));

		// create byte buffer
		byte[] buffer = new byte[1024];

		for (int i = 0; i < files.length; i++) {

			// System.out.println("Adding file: " + files[i].getName());

			FileInputStream fis = new FileInputStream(files[i]);

			// begin writing a new ZIP entry, positions the stream to
			// the start of the entry data
			zos.putNextEntry(new ZipEntry(files[i].getName()));

			int length;

			while ((length = fis.read(buffer)) > 0) {
				zos.write(buffer, 0, length);
			}

			zos.closeEntry();

			// close the InputStream
			fis.close();
		}

		// close the ZipOutputStream
		zos.close();

		byte[] zip = readBinary(filePath);

		delete(filePath);

		return zip;
	}

	private byte[] readBinary(String path) throws IOException {
		path = path.replace('/', File.separatorChar);
		byte[] imgStream = null;
		FileInputStream in = new FileInputStream(path);
		imgStream = new byte[in.available()];
		in.read(imgStream);
		// Close it if we need to
		if (in != null)
			in.close();

		return imgStream;
	}

	private void delete(String fromPath) {
		File file = new File(fromPath);
		delete(file);
	}

	private void delete(File file) {
		if (file.isDirectory() && file.list() != null) {
			File[] list = file.listFiles();
			for (int i = 0; i < list.length; i++) {
				delete(list[i]);
			}
		}

		String path = file.getAbsolutePath();
		boolean b = file.delete();
		if (b) {
			if (file.isDirectory()) {
				// System.out.println("[OK]\tDELETE FOLDER " + path);
			} else {
				// System.out.println("[OK]\tDELETE FILE " + path);
			}
		} else {
			if (file.isDirectory()) {
				// System.out.println("[ERROR]\tDELETE FOLDER " + path);
			} else {
				// System.out.println("[ERROR]\tDELETE FILE " + path);
			}
		}

	}

}
