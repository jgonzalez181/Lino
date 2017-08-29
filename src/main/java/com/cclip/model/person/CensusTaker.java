package com.cclip.model.person;

import com.cclip.model.schedule.scanning.ScheduleScanning;
import com.cclip.model.schedule.census.ScheduleCensus;

/** Censista */
public class CensusTaker extends Person {


	/**id */
	private String id = null;

	/**erased */
	private Boolean erased = false;

	/**Código */
	private String code = null;

	/**Comentario */
	private String comment = null;

	/** Listado - Barrido de Manzana Catastral */
	private ScheduleScanning[] scheduleScanningList = new ScheduleScanning[0];

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

	public String getCode() {
		return this.code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getComment() {
		return this.comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	public ScheduleScanning[] getScheduleScanningList() {
		if(scheduleScanningList == null) {
			scheduleScanningList = new ScheduleScanning[0];
		}
		return this.scheduleScanningList;
	}

	public void setScheduleScanningList(ScheduleScanning[] scheduleScanningList) {

		if(scheduleScanningList == null)
			throw new IllegalArgumentException("Argument is null.");

		for(int i = 0; i < scheduleScanningList.length; i++){

			if(scheduleScanningList[i] == null)
				throw new IllegalArgumentException("Argument is null. Index " + i);

		}

		this.scheduleScanningList = scheduleScanningList;
	}

	public ScheduleScanning getScheduleScanningList(Integer index) {
		if(scheduleScanningList == null) {
			scheduleScanningList = new ScheduleScanning[0];
		}
		if (index >= scheduleScanningList.length)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + scheduleScanningList.length);

		return this.scheduleScanningList[index];
	}

	public ScheduleScanning removeScheduleScanningList(Integer index) {

		if(scheduleScanningList == null) {
			scheduleScanningList = new ScheduleScanning[0];
		}

		if (index >= scheduleScanningList.length)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + scheduleScanningList.length);

		ScheduleScanning oldValue = scheduleScanningList[index];

		ScheduleScanning[] listTmp = new ScheduleScanning[this.scheduleScanningList.length - 1];

		for (int i = 0; i < scheduleScanningList.length; i++) {

			if (i != index) {

				listTmp[i] = scheduleScanningList[i];

			}

		}

		this.scheduleScanningList = listTmp;

		return oldValue;
	}

	public void addScheduleScanningList(ScheduleScanning scheduleScanning) {
		if(scheduleScanning == null) 
				throw new IllegalArgumentException("Argument is null.");

		scheduleScanning.setCensusTaker(this);

		if(scheduleScanningList == null) {
			scheduleScanningList = new ScheduleScanning[1];
			scheduleScanningList[0] = scheduleScanning;
		} else {
			ScheduleScanning[] listTmp = new ScheduleScanning[this.scheduleScanningList.length + 1];
			listTmp[listTmp.length - 1] = scheduleScanning;
			System.arraycopy(this.scheduleScanningList, 0, listTmp, 0, this.scheduleScanningList.length);
			this.scheduleScanningList = listTmp;
		}
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

		scheduleCensus.setCensusTaker(this);

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
		return super.toString() + " [" + this.code + "]";
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CensusTaker other = (CensusTaker) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public CensusTaker cloneId() {
		CensusTaker other = new CensusTaker();
		other.setId(this.getId());

		return other;
	}
}
