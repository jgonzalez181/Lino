package com.cclip.model.schedule.census;


/** Resultado de Censo */
public class ScheduleCensusResult {


	/**id */
	private String id = null;

	/**erased */
	private Boolean erased = false;

	/**Nombre */
	private String name = null;

	/**Comentario */
	private String comment = null;

	/** Listado - Censo de Parcela */
	private ScheduleCensus[] scheduleCensusList = new ScheduleCensus[0];

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

	public ScheduleCensus[] getScheduleCensusList() {
		if(scheduleCensusList == null) {
			scheduleCensusList = new ScheduleCensus[0];
		}
		return this.scheduleCensusList;
	}

	public void setScheduleCensusList(ScheduleCensus[] scheduleCensusList) {

		if(scheduleCensusList == null)
			throw new IllegalArgumentException("Argument is null.");

		for(int i = 0; i < scheduleCensusList.length; i++){

			if(scheduleCensusList[i] == null)
				throw new IllegalArgumentException("Argument is null. Index " + i);

		}

		this.scheduleCensusList = scheduleCensusList;
	}

	public ScheduleCensus getScheduleCensusList(Integer index) {
		if(scheduleCensusList == null) {
			scheduleCensusList = new ScheduleCensus[0];
		}
		if (index >= scheduleCensusList.length)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + scheduleCensusList.length);

		return this.scheduleCensusList[index];
	}

	public ScheduleCensus removeScheduleCensusList(Integer index) {

		if(scheduleCensusList == null) {
			scheduleCensusList = new ScheduleCensus[0];
		}

		if (index >= scheduleCensusList.length)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + scheduleCensusList.length);

		ScheduleCensus oldValue = scheduleCensusList[index];

		ScheduleCensus[] listTmp = new ScheduleCensus[this.scheduleCensusList.length - 1];

		for (int i = 0; i < scheduleCensusList.length; i++) {

			if (i != index) {

				listTmp[i] = scheduleCensusList[i];

			}

		}

		this.scheduleCensusList = listTmp;

		return oldValue;
	}

	public void addScheduleCensusList(ScheduleCensus scheduleCensus) {
		if(scheduleCensus == null) 
				throw new IllegalArgumentException("Argument is null.");

		scheduleCensus.setScheduleCensusResult(this);

		if(scheduleCensusList == null) {
			scheduleCensusList = new ScheduleCensus[1];
			scheduleCensusList[0] = scheduleCensus;
		} else {
			ScheduleCensus[] listTmp = new ScheduleCensus[this.scheduleCensusList.length + 1];
			listTmp[listTmp.length - 1] = scheduleCensus;
			System.arraycopy(this.scheduleCensusList, 0, listTmp, 0, this.scheduleCensusList.length);
			this.scheduleCensusList = listTmp;
		}
	}

	public String toString(){
		return "(" + this.id + ") " + name;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScheduleCensusResult other = (ScheduleCensusResult) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public ScheduleCensusResult cloneId() {
		ScheduleCensusResult other = new ScheduleCensusResult();
		other.setId(this.getId());

		return other;
	}
}
