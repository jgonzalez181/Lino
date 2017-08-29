package org.utiljdbcdao.ex;

import org.utiljdbc.ConnectionWrapper;

public class ExInsertDao extends GenericExceptionDbInsertDao {

	private static final long serialVersionUID = 3034770828948130227L;

	@SuppressWarnings("rawtypes")
	public ExInsertDao(Class throwerClass, Exception thirdException,
			ConnectionWrapper connectionWrapper) {

		super(throwerClass, thirdException, connectionWrapper);

		super.title = "Insertando un Registro";
		super.message = "Error al intentar insertar un registro.";

	}

}
