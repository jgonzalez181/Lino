package com.cclip.model.schedule.census;

import com.cclip.model.geo.cadastre.Cadastre;
import com.cclip.model.geo.cadastre.CadastreCensus;

/** Item Censo de Parcela */
public class ScheduleCensusItem {


	/**id */
	private String id = null;

	/**erased */
	private Boolean erased = false;

	/**Catastro de Parcela de Tierra */
	private Cadastre cadastre = null;

	/**Catastro de Parcela de Tierra */
	private CadastreCensus cadastreCensus = null;

	/**Censo de Parcela */
	private ScheduleCensus scheduleCensus = null;

	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public Boolean getErased() {
		return this.erased;
	}
	public void setErased(Boolean erased) {
		this.erased = erased;
	}

	public Cadastre getCadastre() {
		return this.cadastre;
	}
	public void setCadastre(Cadastre cadastre) {
		this.cadastre = cadastre;
	}

	public CadastreCensus getCadastreCensus() {
		return this.cadastreCensus;
	}
	public void setCadastreCensus(CadastreCensus cadastreCensus) {
		this.cadastreCensus = cadastreCensus;
	}

	public ScheduleCensus getScheduleCensus() {
		return this.scheduleCensus;
	}
	public void setScheduleCensus(ScheduleCensus scheduleCensus) {
		this.scheduleCensus = scheduleCensus;
	}

	public String toString(){
		return this.getClass() + ": " + this.id;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScheduleCensusItem other = (ScheduleCensusItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public ScheduleCensusItem cloneId() {
		ScheduleCensusItem other = new ScheduleCensusItem();
		other.setId(this.getId());

		return other;
	}
}
