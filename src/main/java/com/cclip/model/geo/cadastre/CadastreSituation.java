package com.cclip.model.geo.cadastre;

/** Situación de Catastro */
public class CadastreSituation {

	/** id */
	private String id = null;

	/** erased */
	private Boolean erased = false;

	/** Nombre */
	private String name = null;

	/** Comentario */
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String toString() {
		return "(" + this.id + ") " + this.getName();
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CadastreSituation other = (CadastreSituation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public CadastreSituation cloneId() {
		CadastreSituation other = new CadastreSituation();
		other.setId(this.getId());

		return other;
	}
}
