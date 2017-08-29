package org.utiljdbcdao.ex;

import org.utiljdbc.ConnectionWrapper;

public class ExUpdateDao extends GenericExceptionDbUpdateDao {

	private static final long serialVersionUID = 5211806463927374148L;

	@SuppressWarnings("rawtypes")
	public ExUpdateDao(Class throwerClass, Exception thirdException,
			ConnectionWrapper connectionWrapper) {

		super(throwerClass, thirdException, connectionWrapper);

		super.title = "Actualizando un Registro";
		super.message = "Error al intentar actualizar un registro.";

	}

}
