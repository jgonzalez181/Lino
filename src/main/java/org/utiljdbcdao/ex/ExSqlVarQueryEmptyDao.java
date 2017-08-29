package org.utiljdbcdao.ex;

public class ExSqlVarQueryEmptyDao extends GenericExceptionDao {

	private static final long serialVersionUID = -1427650804793558258L;

	@SuppressWarnings("rawtypes")
	public ExSqlVarQueryEmptyDao(Class throwerClass) {
		super(throwerClass);
		super.title = "Sentencia SQL para Consultar";
		super.message = "La variable sql esta vacia";
	}

}
