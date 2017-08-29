package com.cclip.model.schedule.scanning;

import com.cclip.model.geo.cadastre.Cadastre;

/** Item de Barrido de Manzana Catastral */
public class ScheduleScanningItem {


	/**id */
	private String id = null;

	/**erased */
	private Boolean erased = false;

	/**Metros Cuadrados en Construcción */
	private Double m2Construction = null;

	/**Progreso de Construcción */
	private Double progressConstruction = null;

	/**Comentario */
	private String comment = null;

	/**Catastro de Parcela de Tierra */
	private Cadastre cadastre = null;

	/**Resultado de Barrido */
	private ScheduleScanningResult scheduleScanningResult = null;

	/**Barrido de Manzana Catastral */
	private ScheduleScanning scheduleScanning = null;

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

	public Double getM2Construction() {
		return this.m2Construction;
	}
	public void setM2Construction(Double m2Construction) {
		this.m2Construction = m2Construction;
	}

	public Double getProgressConstruction() {
		return this.progressConstruction;
	}
	public void setProgressConstruction(Double progressConstruction) {
		this.progressConstruction = progressConstruction;
	}

	public String getComment() {
		return this.comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	public Cadastre getCadastre() {
		return this.cadastre;
	}
	public void setCadastre(Cadastre cadastre) {
		this.cadastre = cadastre;
	}

	public ScheduleScanningResult getScheduleScanningResult() {
		return this.scheduleScanningResult;
	}
	public void setScheduleScanningResult(ScheduleScanningResult scheduleScanningResult) {
		this.scheduleScanningResult = scheduleScanningResult;
	}

	public ScheduleScanning getScheduleScanning() {
		return this.scheduleScanning;
	}
	public void setScheduleScanning(ScheduleScanning scheduleScanning) {
		this.scheduleScanning = scheduleScanning;
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
		ScheduleScanningItem other = (ScheduleScanningItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public ScheduleScanningItem cloneId() {
		ScheduleScanningItem other = new ScheduleScanningItem();
		other.setId(this.getId());

		return other;
	}
}
