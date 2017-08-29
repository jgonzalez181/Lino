package com.cclip.bo.geo.cadastre.aacc.icedb;

class ImportInmueble extends ImportTable {

	private static final String TABLE = "cclip.aacc_inmueble";
	private static final String TABLE_BACK = "cclip.aacc_inmueble_back";
	private static final String FILE = "db_congelada_inmueble.sql";

	private static final String[] FIELD_NAMES = { "id", "erased",

	"audit_date_create", "audit_user_create", "audit_date_update",
			"audit_user_update", "audit_version", "distrito", "cuenta", "dv",
			"fecha_alta", "tipo_inmueble", "locali", "cod_pos", "nro_calle",
			"calle", "numero", "dat_complem", "seccion", "manzana", "parcela",
			"cant_unidades", "cod_ser_uf", "fecha_baja", "motivo_baja",
			"descrip_baja", "sup_terreno", "sup_cubierta_pro",
			"sup_scubierta_pro", "sup_cubierta_com", "sup_scubierta_com",
			"sup_cubierta_amp", "sup_scubierta_amp", "regularizado",
			"plancheta", "subplancheta", "subtipo_inmueble", "estado"

	};

	public ImportInmueble() {
		super.table = this.TABLE;
		super.tableBack = this.TABLE_BACK;
		super.file = this.FILE;
		super.fieldNames = this.FIELD_NAMES;

	}

}
