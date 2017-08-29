package com.cclip.bo.geo.cadastre.aacc.icedb;

class ImportUnidad extends ImportTable {

	private static final String TABLE = "cclip.aacc_unidad";
	private static final String TABLE_BACK = "cclip.aacc_unidad_back";
	private static final String FILE = "db_congelada_unidad.sql";

	private static final String[] FIELD_NAMES = { "id", "erased",

	"audit_date_create", "audit_user_create", "audit_date_update",
			"audit_user_update", "audit_version", "unidad", "unidad_alt",
			"razon", "usuario", "zona", "calle", "numero", "piso", "depto",
			"dat_complem", "locali", "cod_pos", "seccion", "manzana",
			"parcela", "subparcela", "uni_fun", "cod_edi", "nom_edi",
			"dir_env", "dir_cob", "reparto", "ruta_dis", "tpo_doc", "num_doc",
			"tpo_iva", "por_gra", "cuenta", "ite_agr", "val_atr_0",
			"val_atr_1", "val_atr_2", "val_atr_3", "val_atr_4", "val_atr_5",
			"val_atr_6", "val_atr_7", "val_atr_8", "val_atr_9", "nro_calle",
			"ult_act", "usu_act", "situacion", "usu_sit", "fec_sit",
			"cortable", "lec_obl", "rep_obl", "telefono", "val_atr_10",
			"val_atr_11", "val_atr_12", "val_atr_13", "val_atr_14",
			"val_atr_15", "val_atr_16", "val_atr_17", "val_atr_18",
			"val_atr_19", "val_atr_20", "val_atr_21", "val_atr_22",
			"val_atr_23", "val_atr_24", "val_atr_25", "val_atr_26",
			"val_atr_27", "val_atr_28", "val_atr_29"

	};

	public ImportUnidad() {
		super.table = this.TABLE;
		super.tableBack = this.TABLE_BACK;
		super.file = this.FILE;
		super.fieldNames = this.FIELD_NAMES;

	}

}
