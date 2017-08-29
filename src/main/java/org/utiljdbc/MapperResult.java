package org.utiljdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface MapperResult {
	
	public Object mapRow(ResultSet rs) throws SQLException;

}
