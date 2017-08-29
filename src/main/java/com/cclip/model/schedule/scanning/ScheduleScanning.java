package com.cclip.model.schedule.scanning;

import com.cclip.model.schedule.Schedule;
import com.cclip.model.person.CensusTaker;
import java.sql.Date;

/** Barrido de Manzana Catastral */
public class ScheduleScanning {


	/**id */
	private String id = null;

	/**erased */
	private Boolean erased = false;

	/**Código Catastral de Manzana */
	private String cadastralCode = null;

	/**Número de Barrido Anual */
	private Integer numberScanning = null;

	/**Comentario */
	private String comment = null;

	/**Fecha Planificada (Resuelto por Censista) */
	private Date plannedDelivered = null;

	/**Barrido Planificado */
	private Date createDate = null;

	/**Barrido Ejecutado por Censista */
	private Date scanning = null;

	/**Barrido Registrado */
	private Date recorded = null;

	/**Cronograma de Facturación y Medición */
	private Schedule schedule = null;

	/**Censista */
	private CensusTaker censusTaker = null;

	/** Listado - Item de Barrido de Manzana Catastral */
	private ScheduleScanningItem[] scheduleScanningItemList = new ScheduleScanningItem[0];

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

	public String getCadastralCode() {
		return this.cadastralCode;
	}
	public void setCadastralCode(String cadastralCode) {
		this.cadastralCode = cadastralCode;
	}

	public Integer getNumberScanning() {
		return this.numberScanning;
	}
	public void setNumberScanning(Integer numberScanning) {
		this.numberScanning = numberScanning;
	}

	public String getComment() {
		return this.comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getPlannedDelivered() {
		return this.plannedDelivered;
	}
	public void setPlannedDelivered(Date plannedDelivered) {
		this.plannedDelivered = plannedDelivered;
	}

	public Date getCreateDate() {
		return this.createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getScanning() {
		return this.scanning;
	}
	public void setScanning(Date scanning) {
		this.scanning = scanning;
	}

	public Date getRecorded() {
		return this.recorded;
	}
	public void setRecorded(Date recorded) {
		this.recorded = recorded;
	}

	public Schedule getSchedule() {
		return this.schedule;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public CensusTaker getCensusTaker() {
		return this.censusTaker;
	}
	public void setCensusTaker(CensusTaker censusTaker) {
		this.censusTaker = censusTaker;
	}

	public ScheduleScanningItem[] getScheduleScanningItemList() {
		if(scheduleScanningItemList == null) {
			scheduleScanningItemList = new ScheduleScanningItem[0];
		}
		return this.scheduleScanningItemList;
	}

	public void setScheduleScanningItemList(ScheduleScanningItem[] scheduleScanningItemList) {

		if(scheduleScanningItemList == null)
			throw new IllegalArgumentException("Argument is null.");

		for(int i = 0; i < scheduleScanningItemList.length; i++){

			if(scheduleScanningItemList[i] == null)
				throw new IllegalArgumentException("Argument is null. Index " + i);

		}

		this.scheduleScanningItemList = scheduleScanningItemList;
	}

	public ScheduleScanningItem getScheduleScanningItemList(Integer index) {
		if(scheduleScanningItemList == null) {
			scheduleScanningItemList = new ScheduleScanningItem[0];
		}
		if (index >= scheduleScanningItemList.length)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + scheduleScanningItemList.length);

		return this.scheduleScanningItemList[index];
	}

	public ScheduleScanningItem removeScheduleScanningItemList(Integer index) {

		if(scheduleScanningItemList == null) {
			scheduleScanningItemList = new ScheduleScanningItem[0];
		}

		if (index >= scheduleScanningItemList.length)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + scheduleScanningItemList.length);

		ScheduleScanningItem oldValue = scheduleScanningItemList[index];

		ScheduleScanningItem[] listTmp = new ScheduleScanningItem[this.scheduleScanningItemList.length - 1];

		for (int i = 0; i < scheduleScanningItemList.length; i++) {

			if (i != index) {

				listTmp[i] = scheduleScanningItemList[i];

			}

		}

		this.scheduleScanningItemList = listTmp;

		return oldValue;
	}

	public void addScheduleScanningItemList(ScheduleScanningItem scheduleScanningItem) {
		if(scheduleScanningItem == null) 
				throw new IllegalArgumentException("Argument is null.");

		scheduleScanningItem.setScheduleScanning(this);

		if(scheduleScanningItemList == null) {
			scheduleScanningItemList = new ScheduleScanningItem[1];
			scheduleScanningItemList[0] = scheduleScanningItem;
		} else {
			ScheduleScanningItem[] listTmp = new ScheduleScanningItem[this.scheduleScanningItemList.length + 1];
			listTmp[listTmp.length - 1] = scheduleScanningItem;
			System.arraycopy(this.scheduleScanningItemList, 0, listTmp, 0, this.scheduleScanningItemList.length);
			this.scheduleScanningItemList = listTmp;
		}
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
		ScheduleScanning other = (ScheduleScanning) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public ScheduleScanning cloneId() {
		ScheduleScanning other = new ScheduleScanning();
		other.setId(this.getId());

		return other;
	}
}
