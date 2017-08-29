package com.cclip.model.schedule;

import com.cclip.model.schedule.census.ScheduleCensus;
import java.sql.Date;

/** Lote de Entrega */
public class ScheduleBatch {


	/**id */
	private String id = null;

	/**erased */
	private Boolean erased = false;

	/**Lote Planificado */
	private Date createDate = null;

	/**Lote Cerrado */
	private Date close = null;

	/**Lote Entregado */
	private Date delivered = null;

	/**Comentario */
	private String comment = null;

	/**Código */
	private Integer numberBatch = null;

	/**Cronograma de Facturación y Medición */
	private Schedule schedule = null;

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

	public Date getCreateDate() {
		return this.createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getClose() {
		return this.close;
	}
	public void setClose(Date close) {
		this.close = close;
	}

	public Date getDelivered() {
		return this.delivered;
	}
	public void setDelivered(Date delivered) {
		this.delivered = delivered;
	}

	public String getComment() {
		return this.comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getNumberBatch() {
		return this.numberBatch;
	}
	public void setNumberBatch(Integer numberBatch) {
		this.numberBatch = numberBatch;
	}

	public Schedule getSchedule() {
		return this.schedule;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
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

		scheduleCensus.setScheduleBatch(this);

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
		String s = "";
		
		if(this.schedule != null){
			s += this.numberBatch + "/" + schedule.getYear();
			return s;
		}
		
		return null;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScheduleBatch other = (ScheduleBatch) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public ScheduleBatch cloneId() {
		ScheduleBatch other = new ScheduleBatch();
		other.setId(this.getId());

		return other;
	}
}
