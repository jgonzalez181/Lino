package com.cclip.gui.geo.cadastre.aacc;

import java.awt.Color;

public class JPanelDatosPostales extends JPanelUNL {

	private static final long serialVersionUID = -1892620481491154996L;

	public JPanelDatosPostales() {
		super("5 - DATOS_POSTALES.UNL", "cclip.aacc_datos_postales", new int[] { 0, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18 }, new String[] { "unidad", "unidad_alt", "usuario",
				"cliente", "dat_complem", "calle", "numero", "piso", "depto", "cod_locali", "locali", "cod_pos", "telefono", "nro_calle", "provin", "desc_prov", "tiene_clie", "reparto", "ruta_dis" },
				0, 2, 3, 0, 2, 3, new Color(192, 0, 0));

	}

}
