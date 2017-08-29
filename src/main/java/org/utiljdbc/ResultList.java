package org.utiljdbc;

public class ResultList {

	private Object[] list = new Object[0];
	private Long cantPages = 0L;
	private Long numberOfRecords = 0L;

	public Object[] getList() {
		return list;
	}

	public void setList(Object[] list) {
		this.list = list;
	}

	public Long getCantPages() {
		return cantPages;
	}

	public void setCantPages(Long cantPages) {
		this.cantPages = cantPages;
	}

	public Long getNumberOfRecords() {
		return numberOfRecords;
	}

	public void setNumberOfRecords(Long numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}

	public String toString() {
		return list.toString();
	}

}
