package com.cclip.model.person;

/** Unidad de Facturación */
public class UfCensus extends Uf {

	private Uf uf;

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

	public String toString() {

		String r = "";

		if (this.uf != null) {
			r = this.uf.getId();
		}

		if (this.getName() != null && this.getId().trim().equals(this.getName().trim()) == false) {
			r += " " + this.getName();
		}

		if (this.getCuit() != null) {
			r += " (" + this.getCuit() + ")";
		}

		if (this.getDni() != null) {
			r += " (" + this.getDni() + ")";
		}

		if (this.getIva() != null) {
			r += " " + this.getIva();
		}

		if (this.getPhone() != null) {
			r += " (TE: " + this.getPhone() + ")";
		}

		if (this.toStringAddres() != null) {
			r += "\n" + this.toStringAddres();
		}

		if (this.getComment() != null) {
			r += "\n" + this.getComment();
		}

		r = r.trim();

		if (r.length() == 0) {
			r = null;
		}

		return r;
	}

	public UfCensus cloneId() {
		UfCensus other = new UfCensus();
		other.setId(this.getId());

		return other;
	}
}
