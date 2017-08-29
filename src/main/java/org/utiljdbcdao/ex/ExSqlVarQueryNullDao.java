package org.utiljdbcdao.ex;

public class ExSqlVarQueryNullDao extends GenericExceptionDao {

	private static final long serialVersionUID = 2229426744528040671L;

	@SuppressWarnings("rawtypes")
	public ExSqlVarQueryNullDao(Class throwerClass) {
		super(throwerClass);
		super.title = "Sentencia SQL para Consultar";
		super.message = "La variable sql es nula";
	}

}
