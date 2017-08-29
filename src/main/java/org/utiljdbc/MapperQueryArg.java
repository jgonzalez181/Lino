package org.utiljdbc;

public class MapperQueryArg {

	public static final String TRASLATE = "'áéíóúÁÉÍÓÚäëïöüÄËÏÖÜñÑ','aeiouAEIOUaeiouAEIOUnN'";

	private String tableAtt;
	private Object value;
	private String sql;
	private Boolean arg = true;

	public Object getValue() {
		return value;
	}

	public String getSql() {
		return sql;
	}

	public Boolean getArg() {
		return arg;
	}

	public void setArg(Boolean arg) {
		this.arg = arg;
	}

	public void setEndsWithIgnoreCaseTraslate(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = "%" + value.toString();
			this.sql = "translate(coalesce(" + this.tableAtt
					+ "::varchar, ''), " + TRASLATE
					+ ") ILIKE translate(coalesce(?::varchar, ''), " + TRASLATE
					+ ")";

		}
	}

	public void setNotEndsWithIgnoreCaseTraslate(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = "%" + value.toString();
			this.sql = "translate(coalesce(" + this.tableAtt
					+ "::varchar, ''), " + TRASLATE
					+ ") NOT ILIKE translate(coalesce(?::varchar, ''), "
					+ TRASLATE + ")";

		}
	}

	public void setEndsWithTraslate(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = "%" + value.toString();
			this.sql = "translate(coalesce(" + this.tableAtt
					+ "::varchar, ''), " + TRASLATE
					+ ") LIKE translate(coalesce(?::varchar, ''), " + TRASLATE
					+ ")";

		}
	}

	public void setNotEndsWithTraslate(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = "%" + value.toString();
			this.sql = "translate(coalesce(" + this.tableAtt
					+ "::varchar, ''), " + TRASLATE
					+ ") NOT LIKE translate(coalesce(?::varchar, ''), "
					+ TRASLATE + ")";

		}
	}

	public void setEndsWithIgnoreCase(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = "%" + value.toString();
			this.sql = this.tableAtt + " ILIKE ?";
		}
	}

	public void setNotEndsWithIgnoreCase(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = "%" + value.toString();
			this.sql = this.tableAtt + " NOT ILIKE ?";
		}
	}

	public void setEndsWith(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt;
			this.value = "%" + value.toString();
			this.sql = this.tableAtt + " LIKE ?";
		}
	}

	public void setNotEndsWith(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt;
			this.value = "%" + value.toString();
			this.sql = this.tableAtt + " NOT LIKE ?";
		}
	}

	// --------------------------------------------------------------

	public void setBeginsWithIgnoreCaseTraslate(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = value.toString() + "%";
			this.sql = "translate(coalesce(" + this.tableAtt
					+ "::varchar, ''), " + TRASLATE
					+ ") ILIKE translate(coalesce(?::varchar, ''), " + TRASLATE
					+ ")";
		}
	}

	public void setNotBeginsWithIgnoreCaseTraslate(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = value.toString() + "%";
			this.sql = "translate(coalesce(" + this.tableAtt
					+ "::varchar, ''), " + TRASLATE
					+ ") NOT ILIKE translate(coalesce(?::varchar, ''), "
					+ TRASLATE + ")";
		}
	}

	public void setBeginsWithTraslate(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = value.toString() + "%";
			this.sql = "translate(coalesce(" + this.tableAtt
					+ "::varchar, ''), " + TRASLATE
					+ ") LIKE translate(coalesce(?::varchar, ''), " + TRASLATE
					+ ")";
		}
	}

	public void setNotBeginsWithTraslate(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = value.toString() + "%";
			this.sql = "translate(coalesce(" + this.tableAtt
					+ "::varchar, ''), " + TRASLATE
					+ ") NOT LIKE translate(coalesce(?::varchar, ''), "
					+ TRASLATE + ")";
		}
	}

	public void setBeginsWithIgnoreCase(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = value.toString() + "%";
			this.sql = this.tableAtt + " ILIKE ?";
		}
	}

	public void setNotBeginsWithIgnoreCase(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = value.toString() + "%";
			this.sql = this.tableAtt + " NOT ILIKE ?";
		}
	}

	public void setBeginsWith(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = value.toString() + "%";
			this.sql = this.tableAtt + " LIKE ?";
		}
	}

	public void setNotBeginsWith(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = value.toString() + "%";
			this.sql = this.tableAtt + " NOT LIKE ?";
		}
	}

	// --------------------------------------------------------------

	public void setContainsIgnoreCaseTraslate(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = "%" + value.toString() + "%";
			this.sql = "translate(coalesce(" + this.tableAtt
					+ "::varchar, ''), " + TRASLATE
					+ ") ILIKE translate(coalesce(?::varchar, ''), " + TRASLATE
					+ ")";
		}
	}

	public void setNotContainsIgnoreCaseTraslate(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = "%" + value.toString() + "%";
			this.sql = "translate(coalesce(" + this.tableAtt
					+ "::varchar, ''), " + TRASLATE
					+ ") NOT ILIKE translate(coalesce(?::varchar, ''), "
					+ TRASLATE + ")";
		}
	}

	public void setContainsTraslate(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = "%" + value.toString() + "%";
			this.sql = "translate(coalesce(" + this.tableAtt
					+ "::varchar, ''), " + TRASLATE
					+ ") LIKE translate(coalesce(?::varchar, ''), " + TRASLATE
					+ ")";
		}
	}

	public void setNotContainsTraslate(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = "%" + value.toString() + "%";
			this.sql = "translate(coalesce(" + this.tableAtt
					+ "::varchar, ''), " + TRASLATE
					+ ") NOT LIKE translate(coalesce(?::varchar, ''), "
					+ TRASLATE + ")";
		}
	}

	public void setContainsIgnoreCase(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = "%" + value.toString() + "%";
			this.sql = this.tableAtt + " ILIKE ?";
		}
	}

	public void setNotContainsIgnoreCase(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = "%" + value.toString() + "%";
			this.sql = this.tableAtt + " NOT ILIKE ?";
		}
	}

	public void setContains(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = "%" + value.toString() + "%";
			this.sql = this.tableAtt + " LIKE ?";
		}
	}

	public void setNotContains(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = "%" + value.toString() + "%";
			this.sql = this.tableAtt + " NOT LIKE ?";
		}
	}

	// --------------------------------------------------------------

	public void setEqualsToIgnoreCaseTraslate(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = value.toString();
			this.sql = "translate(coalesce(" + this.tableAtt
					+ "::varchar, ''), " + TRASLATE
					+ ") ILIKE translate(coalesce(?::varchar, ''), " + TRASLATE
					+ ")";
		}
	}

	public void setNotEqualsToIgnoreCaseTraslate(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = value.toString();
			this.sql = "translate(coalesce(" + this.tableAtt
					+ "::varchar, ''), " + TRASLATE
					+ ") NOT ILIKE translate(coalesce(?::varchar, ''), "
					+ TRASLATE + ")";
		}
	}

	public void setEqualsToTraslate(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = value.toString();
			this.sql = "translate(coalesce(" + this.tableAtt
					+ "::varchar, ''), " + TRASLATE
					+ ") LIKE translate(coalesce(?::varchar, ''), " + TRASLATE
					+ ")";
		}
	}

	public void setNotEqualsToTraslate(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = value.toString();
			this.sql = "translate(coalesce(" + this.tableAtt
					+ "::varchar, ''), " + TRASLATE
					+ ") NOT LIKE translate(coalesce(?::varchar, ''), "
					+ TRASLATE + ")";
		}
	}

	public void setEqualsToIgnoreCase(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = value.toString();
			this.sql = this.tableAtt + " ILIKE ?";
		}
	}

	public void setNotEqualsToIgnoreCase(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = value.toString();
			this.sql = this.tableAtt + " NOT ILIKE ?";
		}
	}

	public void setEqualsTo(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = value;
			this.sql = this.tableAtt + " = ?";
		}
	}

	public void setNotEqualsTo(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = value;
			this.sql = this.tableAtt + " != ?";
		}
	}

	// --------------------------------------------------------------

	public void setMajorTo(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = value;
			this.sql = this.tableAtt + " > ?";
		}
	}

	public void setNotMajorTo(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = value;
			this.sql = this.tableAtt + " !> ?";
		}
	}

	public void setMajorEqualsTo(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = value;
			this.sql = this.tableAtt + " >= ?";
		}
	}

	// --------------------------------------------------------------

	public void setMinorTo(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = value;
			this.sql = this.tableAtt + " < ?";
		}
	}

	public void setNotMinorTo(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = value;
			this.sql = this.tableAtt + " !< ?";
		}
	}

	public void setMinorEqualsTo(String tableAtt, Object value) {
		if (value == null) {
			setIsNullTo(tableAtt);
		} else {
			this.tableAtt = tableAtt.toUpperCase();
			this.value = value;
			this.sql = this.tableAtt + " <= ?";
		}
	}

	// --------------------------------------------------------------

	public void setIsNullTo(String tableAtt) {
		this.tableAtt = tableAtt.toUpperCase();
		this.value = null;
		this.sql = this.tableAtt + " IS NULL ";
		this.arg = false;
	}

	public void setIsNotNullTo(String tableAtt) {
		this.tableAtt = tableAtt.toUpperCase();
		this.value = null;
		this.sql = this.tableAtt + " IS NOT NULL ";
		this.arg = false;
	}

}
