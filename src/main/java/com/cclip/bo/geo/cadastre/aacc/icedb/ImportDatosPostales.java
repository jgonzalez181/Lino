package com.cclip.bo.geo.cadastre.aacc.icedb;

class ImportDatosPostales extends ImportTable {

	private static final String TABLE = "cclip.aacc_datos_postales";
	private static final String TABLE_BACK = "cclip.aacc_datos_postales_back";
	private static final String FILE = "db_congelada_datos_postales.sql";

	private static final String[] FIELD_NAMES = { "id", "erased",

	"audit_date_create", "audit_user_create", "audit_date_update",
			"audit_user_update", "audit_version", "unidad", "unidad_alt",
			"usuario", "cliente", "dat_complem", "calle", "numero", "piso",
			"depto", "cod_locali", "locali", "cod_pos", "telefono",
			"nro_calle", "provin", "desc_prov", "tiene_clie", "reparto",
			"ruta_dis"

	};

	public ImportDatosPostales() {
		super.table = this.TABLE;
		super.tableBack = this.TABLE_BACK;
		super.file = this.FILE;
		super.fieldNames = this.FIELD_NAMES;

	}

}
