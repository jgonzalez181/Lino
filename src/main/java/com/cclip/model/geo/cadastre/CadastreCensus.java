package com.cclip.model.geo.cadastre;

import com.cclip.model.person.UfCensus;
import com.cclip.model.schedule.census.ScheduleCensus;

/** Catastro de Parcela de Tierra */
public class CadastreCensus extends Cadastre {

	/** Unidad de Facturación */
	private UfCensus uf = null;

	private Cadastre cadastre;

	private ScheduleCensus scheduleCensus;

	public UfCensus getUf() {
		return this.uf;
	}

	public void setUf(UfCensus uf) {
		this.uf = uf;
	}

	public Cadastre getCadastre() {
		return cadastre;
	}

	public void setCadastre(Cadastre cadastre) {
		this.cadastre = cadastre;
	}

	public ScheduleCensus getScheduleCensus() {
		return scheduleCensus;
	}

	public void setScheduleCensus(ScheduleCensus scheduleCensus) {
		this.scheduleCensus = scheduleCensus;
	}

	public CadastreCensus cloneId() {
		CadastreCensus other = new CadastreCensus();
		other.setId(this.getId());

		return other;
	}
}
