package com.cclip.model.geo.cadastre.block;

/** Bloque Constructivo */
public class CadastreBlockCensus extends CadastreBlock {

	public CadastreBlockCensus cloneId() {
		CadastreBlockCensus other = new CadastreBlockCensus();
		other.setId(this.getId());

		return other;
	}
}
