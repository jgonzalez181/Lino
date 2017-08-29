package com.cclip.model.schedule.scanning;

/** Resultado de Barrido */
public class ScheduleScanningResultTotal {

	/** id */
	private String id = null;

	/** erased */
	private Boolean erased = false;

	/** Código */
	private String code = null;

	/** Nombre */
	private String name = null;

	/** Comentario */
	private String comment = null;

	/** Total */
	private Integer total = null;

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

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}
