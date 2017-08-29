package org.utiljdbc;

public class MapperQueryOrderArg {

	private String tableAtt;
	private String sql;
	private Boolean asc;
	
	public MapperQueryOrderArg(String tableAtt, Boolean asc){
		this.tableAtt = tableAtt.toUpperCase();
		this.asc = asc;
		if(this.asc == null){
			this.asc = true;
		}
		if(this.asc == true){
			this.sql = tableAtt + " ASC";
		} else {
			this.sql = tableAtt + " DESC";
		}
		
	}
	
	public String getTableAtt() {
		return tableAtt;
	}
	
	public Boolean getAsc(){
		return asc;
	}
	
	public String getSql(){
		return sql;
	}

}
