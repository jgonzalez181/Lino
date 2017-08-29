package com.cclip.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class SO {

	private static int EntraIncial = 0;

	public static void exec(Object[] args) throws IOException,
			InterruptedException {

		Runtime rt = Runtime.getRuntime();
		String s = "";
		String[] argsString = new String[args.length];
		for (int i = 0; i < args.length; i++) {
			s += args[i] + " ";
			argsString[i] = args[i] + "";
		}
		System.out.println(s);
		rt.traceInstructions(true);

		Process p = rt.exec(argsString);

		// p.waitFor();

	}

	public static byte[] readBinary(String path) throws IOException {
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

	public static String readFilePlainText(String path) {
		path = path.replace('/', File.separatorChar);
		String txt = "";
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File(path);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			// Lectura del fichero
			String linea;
			while ((linea = br.readLine()) != null)
				txt += linea + "\n";
			// System.out.println(linea);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta
			// una excepcion.
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return txt;
	}

	public static void delete(String fromPath) {
		File file = new File(fromPath);
		delete(file);
	}

	public static void delete(File file) {
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
				System.out.println("[OK]\tDELETE FOLDER " + path);
			} else {
				System.out.println("[OK]\tDELETE FILE " + path);
			}
		} else {
			if (file.isDirectory()) {
				System.out.println("[ERROR]\tDELETE FOLDER " + path);
			} else {
				System.out.println("[ERROR]\tDELETE FILE " + path);
			}
		}

	}

	public static void createFile(String path, String fileName, String content)
			throws IOException {
		path = path + File.separatorChar + fileName;

		path = path.replace('/', File.separatorChar);

		PrintWriter txt = new PrintWriter(new FileWriter(path));
		txt.print(content);
		txt.close();
		System.out.println("[OK]\tCREATE FILE " + path);
	}

	public static String createFolder(String path) throws Exception {
		path = path.replace('/', File.separatorChar);
		File file = new File(path);
		String pathTmp = file.getAbsolutePath();
		if (file.exists() == false) {
			boolean b = file.mkdir();
			String r = "[NONE]\t";
			if (b) {
				r = "[OK]\t";
			} else {
				r = "[ERROR]\t";
				System.out.println(r + " CREATE FOLDER " + file.getAbsolutePath());
				throw new Exception(r + " CREATE FOLDER "
						+ file.getAbsolutePath());
				

			}
			System.out.println(r + " CREATE FOLDER " + file.getAbsolutePath());
		}

		return pathTmp;
	}

	public static void copy(String fromPath, String toPath) throws Exception {

		File FOrigen = new File(fromPath);

		File FDestino = new File(toPath);

		copy(FOrigen, FDestino);

		EntraIncial = 0;
	}

	/*
	 * Metodo que copia completamente una carpeta usando recursividad
	 * PARAMETRO1:FOrigen:Fichero o carpeta que se desea copiar
	 * PARAMETRO2:FDestino:Carpeta destino
	 */
	private static void copy(File FOrigen, File FDestino) throws Exception {
		// si el origen no es una carpeta
		if (!FOrigen.isDirectory()) {
			// Llamo la funcion que lo copia
			copyFile(FOrigen, FDestino);
		} else {
			// incremento el contador de entradas a esta funcion
			EntraIncial++;
			// si es el directorio padre(1 carpeta a copiar)
			if (EntraIncial == 1) {
				// Cambio la ruta destino por el nombre que tenia mas el nombre
				// de
				// la carpeta padre
				FDestino = new File(FDestino.getAbsolutePath() + "/"
						+ FOrigen.getName());
				// si la carpeta no existe la creo
				if (!FDestino.exists()) {
					FDestino.mkdir();
				}
			}
			// obtengo el nombre de todos los archivos y carpetas que
			// petenecen a este fichero(FOrigen)
			String[] Rutas = FOrigen.list();
			// recorro uno a uno el contenido de la carpeta
			for (int i = 0; i < Rutas.length; i++) {
				// establesco el nombre del nuevo archivo origen
				File FnueOri = new File(FOrigen.getAbsolutePath() + "/"
						+ Rutas[i]);
				// establesco el nombre del nuevo archivo destino
				File FnueDest = new File(FDestino.getAbsolutePath() + "/"
						+ Rutas[i]);
				// si no existe el archivo destino lo creo
				if (FnueOri.isDirectory() && !FnueDest.exists()) {
					FnueDest.mkdir();
				}
				// uso recursividad y llamo a esta misma funcion has llegar
				// al ultimo elemento
				copy(FnueOri, FnueDest);
			}
		}

	}

	private static void copyFile(File FOrigen, File FDestino) throws Exception {
		try {
			// Si el archivo a copiar existe
			if (FOrigen.exists()) {
				String copiar = "S";
				// si el fichero destino ya existe
				// if (FDestino.exists()) {
				// System.out
				// .println("El fichero ya existe, Desea Sobre Escribir:S/N ");
				// copiar = (new BufferedReader(new InputStreamReader(
				// System.in))).readLine();
				// }
				// si puedo copiar
				if (copiar.toUpperCase().equals("S")) {
					// Flujo de lectura al fichero origen(que se va a copiar)
					FileInputStream LeeOrigen = new FileInputStream(FOrigen);
					// Flujo de lectura al fichero destino(donde se va a copiar)
					OutputStream Salida = new FileOutputStream(FDestino);
					// separo un buffer de 1MB de lectura
					byte[] buffer = new byte[1024];
					int tamaño;
					// leo el fichero a copiar cada 1MB
					while ((tamaño = LeeOrigen.read(buffer)) > 0) {
						// Escribe el MB en el fichero destino
						Salida.write(buffer, 0, tamaño);
					}
					System.out.println(FOrigen + " Copiado con Exito a "
							+ FDestino);
					// cierra los flujos de lectura y escritura
					Salida.close();
					LeeOrigen.close();
				}

			} else {// l fichero a copiar no existe
				
				System.out.println("[ERROR]\tCOPY FILE - FILE DOES NOT EXIST. " + FOrigen.getAbsolutePath());
				throw new Exception("[ERROR]\tCOPY FILE - FILE DOES NOT EXIST. " + FOrigen.getAbsolutePath());
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw ex;
		}

	}

}
