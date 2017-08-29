package org.utiljdbcdao.ex;

public class ExMetaDataDbDao extends GenericExceptionDao {

	private static final long serialVersionUID = -2941309691921374399L;

	@SuppressWarnings("rawtypes")
	public ExMetaDataDbDao(Class throwerClass) {
		super(throwerClass);
		super.title = "Data Source Meta Data";
		super.message = "No se pudo obtener información del origen de datos.";
	}

	@SuppressWarnings("rawtypes")
	public ExMetaDataDbDao(Class throwerClass, Exception thirdException) {
		super(throwerClass, thirdException);
		super.title = "Data Source Meta Data";
		super.message = "No se pudo obtener información del origen de datos.";
	}

}
