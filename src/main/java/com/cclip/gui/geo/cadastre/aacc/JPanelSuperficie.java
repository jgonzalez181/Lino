package com.cclip.gui.geo.cadastre.aacc;

import java.awt.Color;

public class JPanelSuperficie extends JPanelUNL {

	private static final long serialVersionUID = -7296928040553846328L;

	public JPanelSuperficie() {

		super("3 - SUPERFICIE.UNL", "cclip.aacc_superficie", new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 }, new String[] { "distrito", "cuenta", "subcuenta", "dv", "nro_sec",
				"tipo_tar_sup", "sup_cub_com", "sup_scub_com", "sup_cub_propia", "sup_scub_propia", "sup_cub_amp", "sup_scub_amp", "tipo_const", "destino_const", "anio_const" }, 0, 1, 12, 0, 1, 12,
				new Color(192, 0, 0));

	}
}
