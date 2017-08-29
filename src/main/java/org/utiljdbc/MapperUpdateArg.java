package org.utiljdbc;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;

public class MapperUpdateArg {

	private String tableAtt;
	private Object value;
	@SuppressWarnings({ "rawtypes" })
	private Class clazz;

	public MapperUpdateArg(String tableAtt, Object value) {
		this.tableAtt = tableAtt.toUpperCase();
		this.value = value;
		this.clazz = value.getClass();
	}

	public MapperUpdateArg(String tableAtt, @SuppressWarnings("rawtypes") Class clazz) {
		this.tableAtt = tableAtt.toUpperCase();
		this.clazz = clazz;
	}

	public String getTableAtt() {
		return tableAtt;
	}

	public Object getValue() {
		return value;
	}

	public Integer getClazz() {
		if (clazz.getName().equals(String.class.getName())) {
			return Types.VARCHAR;
		} else if (clazz.getName().equals(Boolean.class.getName())) {
			return Types.BOOLEAN;
		} else if (clazz.getName().equals(Short.class.getName())) {
			return Types.SMALLINT;
		} else if (clazz.getName().equals(Integer.class.getName())) {
			return Types.INTEGER;
		} else if (clazz.getName().equals(Long.class.getName())) {
			return Types.BIGINT;
		} else if (clazz.getName().equals(Float.class.getName())) {
			return Types.FLOAT;
		} else if (clazz.getName().equals(Double.class.getName())) {
			return Types.DOUBLE;
		} else if (clazz.getName().equals(BigDecimal.class.getName())) {
			return Types.NUMERIC;
		} else if (clazz.getName().equals(Date.class.getName())) {
			return Types.DATE;
		} else if (clazz.getName().equals(Timestamp.class.getName())) {
			return Types.TIMESTAMP;
		} else if (clazz.getName().equals(Time.class.getName())) {
			return Types.TIME;
		}

		return null;
	}

}
