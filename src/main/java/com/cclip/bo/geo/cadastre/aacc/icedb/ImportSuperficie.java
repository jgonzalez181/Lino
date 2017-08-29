package com.cclip.bo.geo.cadastre.aacc.icedb;

class ImportSuperficie extends ImportTable {

	private static final String TABLE = "cclip.aacc_superficie";
	private static final String TABLE_BACK = "cclip.aacc_superficie_back";
	private static final String FILE = "db_congelada_superficie.sql";

	private static final String[] FIELD_NAMES = { "id", "erased",

	"audit_date_create", "audit_user_create", "audit_date_update",
			"audit_user_update", "audit_version", "distrito", "cuenta",
			"subcuenta", "dv", "nro_sec", "tipo_tar_sup", "sup_cub_com",
			"sup_scub_com", "sup_cub_propia", "sup_scub_propia", "sup_cub_amp",
			"sup_scub_amp", "tipo_const", "destino_const", "anio_const"

	};

	public ImportSuperficie() {
		super.table = this.TABLE;
		super.tableBack = this.TABLE_BACK;
		super.file = this.FILE;
		super.fieldNames = this.FIELD_NAMES;

	}

}
