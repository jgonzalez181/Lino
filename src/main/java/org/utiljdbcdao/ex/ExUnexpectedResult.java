package org.utiljdbcdao.ex;


public class ExUnexpectedResult extends GenericExceptionDao {

	private static final long serialVersionUID = 8971469913696187213L;

	@SuppressWarnings("rawtypes")
	public ExUnexpectedResult(Class throwerClass, int cantRows) {
		super(throwerClass);
		super.title = "Cantidad de Registros Devueltos";
		super.message = "Se esperaba como resultado un solo registro. Registros devueltos "
				+ cantRows;
	}

}
