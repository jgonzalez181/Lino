package com.cclip.model.person;

import java.sql.Timestamp;

import com.cclip.model.geo.Neighbourhood;

/** Unidad de Facturación */
public class Uf {

	/** id */
	private String id = null;

	/** erased */
	private Boolean erased = false;

	/** Nombre */
	private String name = null;

	/** DNI */
	private String dni = null;

	/** CUIL/CUIT */
	private String cuit = null;

	/** Teléfono */
	private String phone = null;

	/** Comentario */
	private String comment = null;

	/** País - Código ISO 3166-1 Alfa-2 */
	private String countryCode = null;

	/** País */
	private String country = null;

	/** Provincia - Código ISO 3166-2 */
	private String adminAreaLevel1Code = null;

	/** Provincia */
	private String adminAreaLevel1 = null;

	/** Departamento / Distrito */
	private String adminAreaLevel2 = null;

	/** Localidad */
	private String locality = null;

	/** Calle */
	private String street = null;

	/** Número de Calle */
	private String streetNumber = null;

	/** Planta del Edificio */
	private String buildingFloor = null;

	/** Departamento de Edificio */
	private String buildingRoom = null;

	/** Edificio */
	private String building = null;

	/** Comentario del Domicilio */
	private String commentAddress = null;

	/** Latitud */
	private Double lat = null;

	/** Longitud */
	private Double lng = null;

	/** auditDateCreate */
	private Timestamp auditDateCreate = null;

	/** auditUserCreate */
	private String auditUserCreate = null;

	/** auditDateUpdate */
	private Timestamp auditDateUpdate = null;

	/** auditUserUpdate */
	private String auditUserUpdate = null;

	/** auditVersion */
	private Long auditVersion = null;

	/** auditVersionCodeLabel */
	private String auditVersionCodeLabel = null;

	/** auditVersionLabel */
	private String auditVersionLabel = null;

	/** Condición de IVA */
	private Iva iva = null;

	/** Vecindario */
	private Neighbourhood neighbourhood = null;

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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCuit() {
		return this.cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAdminAreaLevel1Code() {
		return this.adminAreaLevel1Code;
	}

	public void setAdminAreaLevel1Code(String adminAreaLevel1Code) {
		this.adminAreaLevel1Code = adminAreaLevel1Code;
	}

	public String getAdminAreaLevel1() {
		return this.adminAreaLevel1;
	}

	public void setAdminAreaLevel1(String adminAreaLevel1) {
		this.adminAreaLevel1 = adminAreaLevel1;
	}

	public String getAdminAreaLevel2() {
		return this.adminAreaLevel2;
	}

	public void setAdminAreaLevel2(String adminAreaLevel2) {
		this.adminAreaLevel2 = adminAreaLevel2;
	}

	public String getLocality() {
		return this.locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetNumber() {
		return this.streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getBuildingFloor() {
		return this.buildingFloor;
	}

	public void setBuildingFloor(String buildingFloor) {
		this.buildingFloor = buildingFloor;
	}

	public String getBuildingRoom() {
		return this.buildingRoom;
	}

	public void setBuildingRoom(String buildingRoom) {
		this.buildingRoom = buildingRoom;
	}

	public String getBuilding() {
		return this.building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getCommentAddress() {
		return this.commentAddress;
	}

	public void setCommentAddress(String commentAddress) {
		this.commentAddress = commentAddress;
	}

	public Double getLat() {
		return this.lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return this.lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
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

	public Iva getIva() {
		return this.iva;
	}

	public void setIva(Iva iva) {
		this.iva = iva;
	}

	public Neighbourhood getNeighbourhood() {
		return this.neighbourhood;
	}

	public void setNeighbourhood(Neighbourhood neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Uf other = (Uf) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Uf cloneId() {
		Uf other = new Uf();
		other.setId(this.getId());

		return other;
	}

	public String toString() {

		String r = this.id;

		if (this.getName() != null && this.id.trim().equals(this.name.trim()) == false) {
			r += " " + this.getName();
		}

		if (this.getCuit() != null) {
			r += " (" + this.getCuit() + ")";
		}

		if (this.getDni() != null) {
			r += " (" + this.getDni() + ")";
		}

		if (this.getIva() != null) {
			r += " [IVA:" + this.getIva() + "]";
		}

		if (this.getPhone() != null) {
			r += " (TE: " + this.getPhone() + ")";
		}

		if (this.toStringAddres() != null) {
			r += "\n" + this.toStringAddres();
		}

		if (this.getComment() != null) {
//			r += "\n" + this.getComment();
		}

		r = r.trim();

		if (r.length() == 0) {
			r = null;
		}

		return r;
	}

	public String toStringAddres() {
		String r = "";

		if (this.getStreet() != null) {
			r += this.getStreet();
		}

		if (this.getStreetNumber() != null) {
			r += " " + this.getStreetNumber();
		}

		if (this.getBuilding() != null) {
			r += " " + this.getBuilding();
		}

		if (this.getBuildingFloor() != null) {
			r += " " + this.getBuildingFloor();
		}

		if (this.getBuildingRoom() != null) {
			r += " " + this.getBuildingRoom();
		}

		if (this.getNeighbourhood() != null) {
			r += " " + this.getNeighbourhood();
		}

		if (this.getLocality() != null) {
			r += " " + this.getLocality();
		}

		if (this.getNeighbourhood() != null) {
			r += " CP:" + this.getNeighbourhood().getId().trim().substring(0, 4);
		}

		if (this.getAdminAreaLevel2() != null) {
			r += " " + this.getAdminAreaLevel2();
		}

		if (this.getAdminAreaLevel1Code() != null) {
			r += " (" + this.getAdminAreaLevel1Code() + ") ";
		}

		if (this.getAdminAreaLevel1() != null) {
			r += " " + this.getAdminAreaLevel1();
		}

		if (this.getCountryCode() != null) {
			r += " (" + this.getCountryCode() + ") ";
		}

		if (this.getCountry() != null) {
			r += " " + this.getCountry();
		}

		if (this.getCommentAddress() != null) {
			r += " " + this.getCommentAddress();
		}

		r = r.trim();

		if (r.length() == 0) {
			r = null;
		}

		return r;
	}

}
