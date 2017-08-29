package com.cclip.gui.geo.cadastre.aacc;

import java.awt.Color;

public class JPanelSubcuenta extends JPanelUNL {

	private static final long serialVersionUID = -7296928040553846328L;

	public JPanelSubcuenta() {

		super("4 - SUBCUENTA.UNL", "cclip.aacc_subcuenta", new int[] { 0, 1, 2, 4, 8, 9, 10, 14, 17, 19, 26}, new String[] { "distrito", "cuenta", "subcuenta", "dv", "fecha_alta", "tipo_subcta", "sub_tipo", "factura",
				"titular", "tpo_doc", "nro_doc", "tomo", "folio", "numero", "anio", "piso", "depto", "subparcela", "porc_particip", "medido", "medido_vig_d", "actividad", "tbm", "ibm", "base_libre",
				"prom_const", "unidad", "cat_tar", "frec_fac", "orden", "frec_fac_desde", "jubilado", "jubilado_desde", "otro_desc_1", "otro_desc_1_desde", "otro_desc_1_hasta", "otro_desc_2",
				"otro_desc_2_desde", "otro_desc_2_hasta", "construccion", "independiente", "cla_fac", "tipo_cuenta", "gran_cons", "organismo", "cat_tar", "categoria_nrt", "clase_nrt", "subclase_nrt",
				"coef_e_pond", "habitable" }, 1, 26, 17, 1, 26, 17, new Color(192, 0, 0));

	}
}
