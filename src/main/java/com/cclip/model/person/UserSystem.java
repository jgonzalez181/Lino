package com.cclip.model.person;


/** Usuario del Sistema */
public class UserSystem extends Person {


	/**id */
	private String id = null;

	/**erased */
	private Boolean erased = false;

	/**Contraseña */
	private String pass = null;

	/**Correo Electrónico */
	private String email = null;

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

	public String getPass() {
		return this.pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getComment() {
		return this.comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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
		UserSystem other = (UserSystem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public UserSystem cloneId() {
		UserSystem other = new UserSystem();
		other.setId(this.getId());

		return other;
	}
}
