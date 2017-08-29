package org.utiljdbcdao.ex;

public class ExMetaDataDbCloseConnection extends GenericExceptionDao {

	private static final long serialVersionUID = -8794280264552412829L;

	@SuppressWarnings("rawtypes")
	public ExMetaDataDbCloseConnection(Class throwerClass) {
		super(throwerClass);
		super.title = "Data Source Meta Data";
		super.message = "No se pudo cerrar la conexion para obtener información del origen de datos.";
	}

	@SuppressWarnings("rawtypes")
	public ExMetaDataDbCloseConnection(Class throwerClass,
			Exception thirdException) {
		super(throwerClass, thirdException);
		super.title = "Data Source Meta Data";
		super.message = "No se pudo cerrar la conexion para obtener información del origen de datos.";
	}
}