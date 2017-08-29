package com.cclip.model.schedule.census;

import java.sql.Date;

import com.cclip.model.geo.cadastre.CadastreActivityType;
import com.cclip.model.geo.cadastre.CadastreCensus;
import com.cclip.model.person.CensusTaker;
import com.cclip.model.schedule.Schedule;
import com.cclip.model.schedule.ScheduleBatch;

/** Censo de Parcela */
public class ScheduleCensus {

	/** id */
	private String id = null;

	/** erased */
	private Boolean erased = false;

	/** Código Catastral de Parcela */
	private String cadastralCode = null;

	/** Número de Censo Anual */
	private Integer numberCensus = null;

	/** Fecha de Censo */
	private Date censused = null;

	/** Censo Registrado */
	private Date recorded = null;

	/** Galpones en General */
	private Double m2ShedsGeneral = null;

	/** Edificios en General */
	private Double m2GeneralBuilding = null;

	/** Edificios para Espectaculos Publicos */
	private Double m2BuildingsPublicEntertainment = null;

	/** Progreso de Construcción */
	private Double m2ProgressConstruction = null;

	/** DNI del Encuestado */
	private String dniCensused = null;

	/** Nombre del Encuestado */
	private String nameCensused = null;

	/** Apellido del Encuestado */
	private String lastNameCensused = null;

	/** Apellido del Encuestado */
	private Boolean signatureCensused = null;

	/** Comentario */
	private String comment = null;

	/** Alta */
	private Boolean insertCadastre = null;

	/** Baja */
	private Boolean deleteCadastre = null;

	/** Modificación */
	private Boolean updateCadastre = null;

	/** Cronograma de Facturación y Medición */
	private Schedule schedule = null;

	/** Censista */
	private CensusTaker censusTaker = null;

	/** Lote de Entrega */
	private ScheduleBatch scheduleBatch = null;

	/** Tipo de Actividad */
	private CadastreActivityType cadastreActivityType = null;

	/** Resultado de Censo */
	private ScheduleCensusResult scheduleCensusResult = null;

	/** Listado - Item Censo de Parcela */
	private ScheduleCensusItem[] scheduleCensusItemList = new ScheduleCensusItem[0];

	private CadastreCensus cadastreCensus;

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

	public String getCadastralCode() {
		return this.cadastralCode;
	}

	public void setCadastralCode(String cadastralCode) {
		this.cadastralCode = cadastralCode;
	}

	public Integer getNumberCensus() {
		return this.numberCensus;
	}

	public void setNumberCensus(Integer numberCensus) {
		this.numberCensus = numberCensus;
	}

	public Date getCensused() {
		return this.censused;
	}

	public void setCensused(Date censused) {
		this.censused = censused;
	}

	public Date getRecorded() {
		return this.recorded;
	}

	public void setRecorded(Date recorded) {
		this.recorded = recorded;
	}

	public Double getM2ShedsGeneral() {
		return this.m2ShedsGeneral;
	}

	public void setM2ShedsGeneral(Double m2ShedsGeneral) {
		this.m2ShedsGeneral = m2ShedsGeneral;
	}

	public Double getM2GeneralBuilding() {
		return this.m2GeneralBuilding;
	}

	public void setM2GeneralBuilding(Double m2GeneralBuilding) {
		this.m2GeneralBuilding = m2GeneralBuilding;
	}

	public Double getM2BuildingsPublicEntertainment() {
		return this.m2BuildingsPublicEntertainment;
	}

	public void setM2BuildingsPublicEntertainment(Double m2BuildingsPublicEntertainment) {
		this.m2BuildingsPublicEntertainment = m2BuildingsPublicEntertainment;
	}

	public Double getM2ProgressConstruction() {
		return this.m2ProgressConstruction;
	}

	public void setM2ProgressConstruction(Double m2ProgressConstruction) {
		this.m2ProgressConstruction = m2ProgressConstruction;
	}

	public String getDniCensused() {
		return this.dniCensused;
	}

	public void setDniCensused(String dniCensused) {
		this.dniCensused = dniCensused;
	}

	public String getNameCensused() {
		return this.nameCensused;
	}

	public void setNameCensused(String nameCensused) {
		this.nameCensused = nameCensused;
	}

	public String getLastNameCensused() {
		return this.lastNameCensused;
	}

	public void setLastNameCensused(String lastNameCensused) {
		this.lastNameCensused = lastNameCensused;
	}

	public Boolean getSignatureCensused() {
		return this.signatureCensused;
	}

	public void setSignatureCensused(Boolean signatureCensused) {
		this.signatureCensused = signatureCensused;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Boolean getInsertCadastre() {
		return this.insertCadastre;
	}

	public void setInsertCadastre(Boolean insertCadastre) {
		this.insertCadastre = insertCadastre;
	}

	public Boolean getDeleteCadastre() {
		return this.deleteCadastre;
	}

	public void setDeleteCadastre(Boolean deleteCadastre) {
		this.deleteCadastre = deleteCadastre;
	}

	public Boolean getUpdateCadastre() {
		return this.updateCadastre;
	}

	public void setUpdateCadastre(Boolean updateCadastre) {
		this.updateCadastre = updateCadastre;
	}

	public Schedule getSchedule() {
		return this.schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public CensusTaker getCensusTaker() {
		return this.censusTaker;
	}

	public void setCensusTaker(CensusTaker censusTaker) {
		this.censusTaker = censusTaker;
	}

	public ScheduleBatch getScheduleBatch() {
		return this.scheduleBatch;
	}

	public void setScheduleBatch(ScheduleBatch scheduleBatch) {
		this.scheduleBatch = scheduleBatch;
	}

	public CadastreActivityType getCadastreActivityType() {
		return this.cadastreActivityType;
	}

	public void setCadastreActivityType(CadastreActivityType cadastreActivityType) {
		this.cadastreActivityType = cadastreActivityType;
	}

	public ScheduleCensusResult getScheduleCensusResult() {
		return this.scheduleCensusResult;
	}

	public void setScheduleCensusResult(ScheduleCensusResult scheduleCensusResult) {
		this.scheduleCensusResult = scheduleCensusResult;
	}

	public ScheduleCensusItem[] getScheduleCensusItemList() {
		if (scheduleCensusItemList == null) {
			scheduleCensusItemList = new ScheduleCensusItem[0];
		}
		return this.scheduleCensusItemList;
	}

	public void setScheduleCensusItemList(ScheduleCensusItem[] scheduleCensusItemList) {

		if (scheduleCensusItemList == null)
			throw new IllegalArgumentException("Argument is null.");

		for (int i = 0; i < scheduleCensusItemList.length; i++) {

			if (scheduleCensusItemList[i] == null)
				throw new IllegalArgumentException("Argument is null. Index " + i);

		}

		this.scheduleCensusItemList = scheduleCensusItemList;
	}

	public ScheduleCensusItem getScheduleCensusItemList(Integer index) {
		if (scheduleCensusItemList == null) {
			scheduleCensusItemList = new ScheduleCensusItem[0];
		}
		if (index >= scheduleCensusItemList.length)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + scheduleCensusItemList.length);

		return this.scheduleCensusItemList[index];
	}

	public ScheduleCensusItem removeScheduleCensusItemList(Integer index) {

		if (scheduleCensusItemList == null) {
			scheduleCensusItemList = new ScheduleCensusItem[0];
		}

		if (index >= scheduleCensusItemList.length)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + scheduleCensusItemList.length);

		ScheduleCensusItem oldValue = scheduleCensusItemList[index];

		ScheduleCensusItem[] listTmp = new ScheduleCensusItem[this.scheduleCensusItemList.length - 1];

		for (int i = 0; i < scheduleCensusItemList.length; i++) {

			if (i != index) {

				listTmp[i] = scheduleCensusItemList[i];

			}

		}

		this.scheduleCensusItemList = listTmp;

		return oldValue;
	}

	public void addScheduleCensusItemList(ScheduleCensusItem scheduleCensusItem) {
		if (scheduleCensusItem == null)
			throw new IllegalArgumentException("Argument is null.");

		scheduleCensusItem.setScheduleCensus(this);

		if (scheduleCensusItemList == null) {
			scheduleCensusItemList = new ScheduleCensusItem[1];
			scheduleCensusItemList[0] = scheduleCensusItem;
		} else {
			ScheduleCensusItem[] listTmp = new ScheduleCensusItem[this.scheduleCensusItemList.length + 1];
			listTmp[listTmp.length - 1] = scheduleCensusItem;
			System.arraycopy(this.scheduleCensusItemList, 0, listTmp, 0, this.scheduleCensusItemList.length);
			this.scheduleCensusItemList = listTmp;
		}
	}

	public CadastreCensus getCadastreCensus() {
		return cadastreCensus;
	}

	public void setCadastreCensus(CadastreCensus cadastreCensus) {
		this.cadastreCensus = cadastreCensus;
	}

	public String toString() {
		return this.getClass() + ": " + this.id;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScheduleCensus other = (ScheduleCensus) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public ScheduleCensus cloneId() {
		ScheduleCensus other = new ScheduleCensus();
		other.setId(this.getId());

		return other;
	}
}
