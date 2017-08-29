package com.cclip.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LinoProperties extends Properties {

	private static final long serialVersionUID = -4558972030661706059L;

	/** URL de Repositorio de Archivos */
	private String urlFiles = null;

	/** URL de Repositorio de Archivos */
	private String urlUnlDbIceFrom = null;

	/** URL de Repositorio de Archivos */
	private String urlUnlNovFrom = null;

	/** URL de Repositorio de Archivos */
	private String urlUnlTo = null;

	/** URL de origen de distritales */
	private String urlDistritalesFrom = null;

	/** URL de origen de planchetas */
	private String urlPlanchetasFrom = null;
	
	/** CUIL Login */
	private String cuilLogin = null;

	public LinoProperties() {

		try {

			urlFiles = System.getProperty("user.dir") + File.separatorChar + "lino_files";

			load(new FileInputStream(urlFiles + File.separatorChar + "lino.properties"));

			urlUnlDbIceFrom = this.getProperty("urlUnlDbIceFrom");
			urlUnlNovFrom = this.getProperty("urlUnlNovFrom");
			urlUnlTo = this.getProperty("urlUnlTo");
			urlDistritalesFrom = this.getProperty("urlDistritalesFrom");
			urlPlanchetasFrom = this.getProperty("urlPlanchetasFrom");
			cuilLogin = this.getProperty("cuilLogin");

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public String getUrlFiles() {
		return this.urlFiles;
	}

	public void setUrlFiles(String urlFiles) {
		this.urlFiles = urlFiles;
	}

	public String getUrlUnlDbIceFrom() {
		return this.urlUnlDbIceFrom;
	}

	public void setUrlUnlDbIceFrom(String urlUnlDbIceFrom) {
		this.urlUnlDbIceFrom = urlUnlDbIceFrom;
	}

	public String getUrlUnlNovFrom() {
		return this.urlUnlNovFrom;
	}

	public void setUrlUnlNovFrom(String urlUnlNovFrom) {
		this.urlUnlNovFrom = urlUnlNovFrom;
	}

	public String getUrlUnlTo() {
		return this.urlUnlTo;
	}

	public void setUrlUnlTo(String urlUnlTo) {
		this.urlUnlTo = urlUnlTo;
	}

	public String getUrlDistritalesFrom() {
		return this.urlDistritalesFrom;
	}

	public void setUrlDistritalesFrom(String urlDistritalesFrom) {
		this.urlDistritalesFrom = urlDistritalesFrom;
	}

	public String getUrlPlanchetasFrom() {
		return this.urlPlanchetasFrom;
	}

	public void setUrlPlanchetasFrom(String urlPlanchetasFrom) {
		this.urlPlanchetasFrom = urlPlanchetasFrom;
	}

	public String getCuilLogin() {
		return cuilLogin;
	}

	public void setCuilLogin(String cuilLogin) {
		this.cuilLogin = cuilLogin;
	}
	
	

}
