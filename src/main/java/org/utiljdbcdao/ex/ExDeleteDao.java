package org.utiljdbcdao.ex;

import org.utiljdbc.ConnectionWrapper;



public class ExDeleteDao extends GenericExceptionDbDeleteDao {

	private static final long serialVersionUID = 4917330156524908643L;

	@SuppressWarnings("rawtypes")
	public ExDeleteDao(Class throwerClass, Exception thirdException,
			ConnectionWrapper connectionWrapper) {

		super(throwerClass, thirdException, connectionWrapper);

		super.title = "Borrando un Registro";
		super.message = "Error al intentar borrar un registro.";

	}

}
