package com.cclip.gui.geo.cadastre.aacc;

import java.awt.Color;

public class JPanelInmueble extends JPanelUNL {

	private static final long serialVersionUID = -7296928040553846328L;

	public JPanelInmueble() {

		super("2 - INMUEBLE.UNL", "cclip.aacc_inmueble", new int[] { 0, 1, 2, 4, 11, 12, 13, 14}, new String[] { "distrito", "cuenta", "dv", "fecha_alta", "tipo_inmueble", "locali", "cod_pos", "nro_calle",
				"calle", "numero", "dat_complem", "seccion", "manzana", "parcela", "cant_unidades", "cod_ser_uf", "fecha_baja", "motivo_baja", "descrip_baja", "sup_terreno", "sup_cubierta_pro",
				"sup_scubierta_pro", "sup_cubierta_com", "sup_scubierta_com", "sup_cubierta_amp", "sup_scubierta_amp", "regularizado", "plancheta", "subplancheta", "subtipo_inmueble", "estado" }, 11,
				12, 13, 11, 12, 13, new Color(192, 0, 0));

	}

}
