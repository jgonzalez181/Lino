package com.cclip.model.person;


/** Persona Física */
public class Person {


	/**id */
	private String id = null;

	/**erased */
	private Boolean erased = false;

	/**clazzDiscriminator */
	private String clazzDiscriminator = null;

	/**CUIL */
	private String cuil = null;

	/**DNI */
	private String dni = null;

	/**Nombre de Pila */
	private String givenName = null;

	/**Segundo Nombre */
	private String middleName = null;

	/**Apellido */
	private String familyName = null;

	/**Masculino */
	private Boolean masculine = null;

	/**Comentario */
	private String comment = null;

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

	public String getClazzDiscriminator() {
		return this.clazzDiscriminator;
	}
	public void setClazzDiscriminator(String clazzDiscriminator) {
		this.clazzDiscriminator = clazzDiscriminator;
	}

	public String getCuil() {
		return this.cuil;
	}
	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	public String getDni() {
		return this.dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getGivenName() {
		return this.givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getMiddleName() {
		return this.middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getFamilyName() {
		return this.familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public Boolean getMasculine() {
		return this.masculine;
	}
	public void setMasculine(Boolean masculine) {
		this.masculine = masculine;
	}

	public String getComment() {
		return this.comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	public String toString(){
		return "(" + this.cuil + " - " + this.dni + ") " + this.familyName + " " + this.givenName + " " + this.middleName;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Person cloneId() {
		Person other = new Person();
		other.setId(this.getId());

		return other;
	}
}
