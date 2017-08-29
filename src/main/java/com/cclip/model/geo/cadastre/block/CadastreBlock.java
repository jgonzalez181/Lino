package com.cclip.model.geo.cadastre.block;

import com.cclip.model.geo.cadastre.CadastreActivityType;
import com.cclip.model.geo.cadastre.Cadastre;
import java.sql.Timestamp;

/** Bloque Constructivo */
public class CadastreBlock {


	/**id */
	private String id = null;

	/**erased */
	private Boolean erased = false;

	/**Año Bloque */
	private Integer yearBuilding = null;

	/**Mes Bloque */
	private Integer monthBuilding = null;

	/**Metros Cuadrados Cubiertos */
	private Double m2Covered = null;

	/**Demolición */
	private Boolean demolition = null;

	/**Comentario */
	private String comment = null;

	/**Fachada */
	private String facade = null;

	/**Techos Estructura */
	private String roofStructure = null;

	/**Pisos */
	private String homes = null;

	/**Muros Interiores */
	private String interiorWalls = null;

	/**Cielorrasos */
	private String ceiling = null;

	/**Cocina */
	private String kitchen = null;

	/**Baños */
	private String toilets = null;

	/**Hall Ingreso */
	private String entranceHall = null;

	/**Instalaciones */
	private String facilities = null;

	/**Carpinteria */
	private String carpentry = null;

	/**Estructura Cubierta */
	private String coverStructure = null;

	/**Ornamentacion */
	private String ornamentation = null;

	/**Vidrieras Iluminacion */
	private String stainedGlassLighting = null;

	/**Buffet Comedor */
	private String buffetDining = null;

	/**Puntos */
	private Integer points = null;

	/**APC */
	private Boolean apc = null;

	/**Tarifa */
	private Integer rate = null;

	/**Detalle de APC */
	private String apcDesc = null;

	/**auditDateCreate */
	private Timestamp auditDateCreate = null;

	/**auditUserCreate */
	private String auditUserCreate = null;

	/**auditDateUpdate */
	private Timestamp auditDateUpdate = null;

	/**auditUserUpdate */
	private String auditUserUpdate = null;

	/**auditVersion */
	private Long auditVersion = null;

	/**auditVersionCodeLabel */
	private String auditVersionCodeLabel = null;

	/**auditVersionLabel */
	private String auditVersionLabel = null;

	/**Tipo de Actividad */
	private CadastreActivityType cadastreActivityType = null;

	/**Catastro de Parcela de Tierra */
	private Cadastre cadastre = null;

	/**Tipo Constructivo */
	private CadastreConstructiveType cadastreConstructiveType = null;

	/**Tipo de Destino */
	private CadastreDestinationType cadastreDestinationType = null;

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

	public Integer getYearBuilding() {
		return this.yearBuilding;
	}
	public void setYearBuilding(Integer yearBuilding) {
		this.yearBuilding = yearBuilding;
	}

	public Integer getMonthBuilding() {
		return this.monthBuilding;
	}
	public void setMonthBuilding(Integer monthBuilding) {
		this.monthBuilding = monthBuilding;
	}

	public Double getM2Covered() {
		return this.m2Covered;
	}
	public void setM2Covered(Double m2Covered) {
		this.m2Covered = m2Covered;
	}

	public Boolean getDemolition() {
		return this.demolition;
	}
	public void setDemolition(Boolean demolition) {
		this.demolition = demolition;
	}

	public String getComment() {
		return this.comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getFacade() {
		return this.facade;
	}
	public void setFacade(String facade) {
		this.facade = facade;
	}

	public String getRoofStructure() {
		return this.roofStructure;
	}
	public void setRoofStructure(String roofStructure) {
		this.roofStructure = roofStructure;
	}

	public String getHomes() {
		return this.homes;
	}
	public void setHomes(String homes) {
		this.homes = homes;
	}

	public String getInteriorWalls() {
		return this.interiorWalls;
	}
	public void setInteriorWalls(String interiorWalls) {
		this.interiorWalls = interiorWalls;
	}

	public String getCeiling() {
		return this.ceiling;
	}
	public void setCeiling(String ceiling) {
		this.ceiling = ceiling;
	}

	public String getKitchen() {
		return this.kitchen;
	}
	public void setKitchen(String kitchen) {
		this.kitchen = kitchen;
	}

	public String getToilets() {
		return this.toilets;
	}
	public void setToilets(String toilets) {
		this.toilets = toilets;
	}

	public String getEntranceHall() {
		return this.entranceHall;
	}
	public void setEntranceHall(String entranceHall) {
		this.entranceHall = entranceHall;
	}

	public String getFacilities() {
		return this.facilities;
	}
	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}

	public String getCarpentry() {
		return this.carpentry;
	}
	public void setCarpentry(String carpentry) {
		this.carpentry = carpentry;
	}

	public String getCoverStructure() {
		return this.coverStructure;
	}
	public void setCoverStructure(String coverStructure) {
		this.coverStructure = coverStructure;
	}

	public String getOrnamentation() {
		return this.ornamentation;
	}
	public void setOrnamentation(String ornamentation) {
		this.ornamentation = ornamentation;
	}

	public String getStainedGlassLighting() {
		return this.stainedGlassLighting;
	}
	public void setStainedGlassLighting(String stainedGlassLighting) {
		this.stainedGlassLighting = stainedGlassLighting;
	}

	public String getBuffetDining() {
		return this.buffetDining;
	}
	public void setBuffetDining(String buffetDining) {
		this.buffetDining = buffetDining;
	}

	public Integer getPoints() {
		return this.points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}

	public Boolean getApc() {
		return this.apc;
	}
	public void setApc(Boolean apc) {
		this.apc = apc;
	}

	public Integer getRate() {
		return this.rate;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public String getApcDesc() {
		return this.apcDesc;
	}
	public void setApcDesc(String apcDesc) {
		this.apcDesc = apcDesc;
	}

	public Timestamp getAuditDateCreate() {
		return this.auditDateCreate;
	}
	public void setAuditDateCreate(Timestamp auditDateCreate) {
		this.auditDateCreate = auditDateCreate;
	}

	public String getAuditUserCreate() {
		return this.auditUserCreate;
	}
	public void setAuditUserCreate(String auditUserCreate) {
		this.auditUserCreate = auditUserCreate;
	}

	public Timestamp getAuditDateUpdate() {
		return this.auditDateUpdate;
	}
	public void setAuditDateUpdate(Timestamp auditDateUpdate) {
		this.auditDateUpdate = auditDateUpdate;
	}

	public String getAuditUserUpdate() {
		return this.auditUserUpdate;
	}
	public void setAuditUserUpdate(String auditUserUpdate) {
		this.auditUserUpdate = auditUserUpdate;
	}

	public Long getAuditVersion() {
		return this.auditVersion;
	}
	public void setAuditVersion(Long auditVersion) {
		this.auditVersion = auditVersion;
	}

	public String getAuditVersionCodeLabel() {
		return this.auditVersionCodeLabel;
	}
	public void setAuditVersionCodeLabel(String auditVersionCodeLabel) {
		this.auditVersionCodeLabel = auditVersionCodeLabel;
	}

	public String getAuditVersionLabel() {
		return this.auditVersionLabel;
	}
	public void setAuditVersionLabel(String auditVersionLabel) {
		this.auditVersionLabel = auditVersionLabel;
	}

	public CadastreActivityType getCadastreActivityType() {
		return this.cadastreActivityType;
	}
	public void setCadastreActivityType(CadastreActivityType cadastreActivityType) {
		this.cadastreActivityType = cadastreActivityType;
	}

	public Cadastre getCadastre() {
		return this.cadastre;
	}
	public void setCadastre(Cadastre cadastre) {
		this.cadastre = cadastre;
	}

	public CadastreConstructiveType getCadastreConstructiveType() {
		return this.cadastreConstructiveType;
	}
	public void setCadastreConstructiveType(CadastreConstructiveType cadastreConstructiveType) {
		this.cadastreConstructiveType = cadastreConstructiveType;
	}

	public CadastreDestinationType getCadastreDestinationType() {
		return this.cadastreDestinationType;
	}
	public void setCadastreDestinationType(CadastreDestinationType cadastreDestinationType) {
		this.cadastreDestinationType = cadastreDestinationType;
	}

	public String toString(){
		return this.getClass() + ": " + this.yearBuilding;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CadastreBlock other = (CadastreBlock) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public CadastreBlock cloneId() {
		CadastreBlock other = new CadastreBlock();
		other.setId(this.getId());

		return other;
	}
}
