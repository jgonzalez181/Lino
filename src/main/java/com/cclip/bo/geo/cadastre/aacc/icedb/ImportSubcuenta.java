package com.cclip.bo.geo.cadastre.aacc.icedb;

class ImportSubcuenta extends ImportTable {

	private static final String TABLE = "cclip.aacc_subcuenta";
	private static final String TABLE_BACK = "cclip.aacc_subcuenta_back";
	private static final String FILE = "db_congelada_subcuenta.sql";

	private static final String[] FIELD_NAMES = { "id", "erased",

	"audit_date_create", "audit_user_create", "audit_date_update",
			"audit_user_update", "audit_version", "distrito", "cuenta",
			"subcuenta", "dv", "fecha_alta", "tipo_subcta", "sub_tipo",
			"factura", "titular", "tpo_doc", "nro_doc", "tomo", "folio",
			"numero", "anio", "piso", "depto", "subparcela", "porc_particip",
			"medido", "medido_vig_d", "actividad", "tbm", "ibm", "base_libre",
			"prom_const", "unidad", "cat_tar", "frec_fac", "orden",
			"frec_fac_desde", "jubilado", "jubilado_desde", "otro_desc_1",
			"otro_desc_1_desde", "otro_desc_1_hasta", "otro_desc_2",
			"otro_desc_2_desde", "otro_desc_2_hasta", "construccion",
			"independiente"

	};

	public ImportSubcuenta() {
		super.table = this.TABLE;
		super.tableBack = this.TABLE_BACK;
		super.file = this.FILE;
		super.fieldNames = this.FIELD_NAMES;

	}

}
