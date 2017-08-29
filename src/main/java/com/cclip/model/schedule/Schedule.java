package com.cclip.model.schedule;

import com.cclip.model.schedule.scanning.ScheduleScanning;
import com.cclip.model.schedule.census.ScheduleCensus;
import java.sql.Date;

/** Cronograma de Facturación y Medición */
public class Schedule {


	/**id */
	private String id = null;

	/**erased */
	private Boolean erased = false;

	/**Año */
	private Integer year = null;

	/**Fecha de Inicio */
	private Date dateFrom = null;

	/**Fecha de Fin */
	private Date dateTo = null;

	/**Comentario */
	private String comment = null;

	/** Listado - Lote de Entrega */
	private ScheduleBatch[] scheduleBatchList = new ScheduleBatch[0];

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

	public Integer getYear() {
		return this.year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}

	public Date getDateFrom() {
		return this.dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return this.dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public String getComment() {
		return this.comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	public ScheduleBatch[] getScheduleBatchList() {
		if(scheduleBatchList == null) {
			scheduleBatchList = new ScheduleBatch[0];
		}
		return this.scheduleBatchList;
	}

	public void setScheduleBatchList(ScheduleBatch[] scheduleBatchList) {

		if(scheduleBatchList == null)
			throw new IllegalArgumentException("Argument is null.");

		for(int i = 0; i < scheduleBatchList.length; i++){

			if(scheduleBatchList[i] == null)
				throw new IllegalArgumentException("Argument is null. Index " + i);

		}

		this.scheduleBatchList = scheduleBatchList;
	}

	public ScheduleBatch getScheduleBatchList(Integer index) {
		if(scheduleBatchList == null) {
			scheduleBatchList = new ScheduleBatch[0];
		}
		if (index >= scheduleBatchList.length)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + scheduleBatchList.length);

		return this.scheduleBatchList[index];
	}

	public ScheduleBatch removeScheduleBatchList(Integer index) {

		if(scheduleBatchList == null) {
			scheduleBatchList = new ScheduleBatch[0];
		}

		if (index >= scheduleBatchList.length)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + scheduleBatchList.length);

		ScheduleBatch oldValue = scheduleBatchList[index];

		ScheduleBatch[] listTmp = new ScheduleBatch[this.scheduleBatchList.length - 1];

		for (int i = 0; i < scheduleBatchList.length; i++) {

			if (i != index) {

				listTmp[i] = scheduleBatchList[i];

			}

		}

		this.scheduleBatchList = listTmp;

		return oldValue;
	}

	public void addScheduleBatchList(ScheduleBatch scheduleBatch) {
		if(scheduleBatch == null) 
				throw new IllegalArgumentException("Argument is null.");

		scheduleBatch.setSchedule(this);

		if(scheduleBatchList == null) {
			scheduleBatchList = new ScheduleBatch[1];
			scheduleBatchList[0] = scheduleBatch;
		} else {
			ScheduleBatch[] listTmp = new ScheduleBatch[this.scheduleBatchList.length + 1];
			listTmp[listTmp.length - 1] = scheduleBatch;
			System.arraycopy(this.scheduleBatchList, 0, listTmp, 0, this.scheduleBatchList.length);
			this.scheduleBatchList = listTmp;
		}
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

		scheduleScanning.setSchedule(this);

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

		scheduleCensus.setSchedule(this);

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
		return this.year + "";
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Schedule other = (Schedule) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Schedule cloneId() {
		Schedule other = new Schedule();
		other.setId(this.getId());

		return other;
	}
}
