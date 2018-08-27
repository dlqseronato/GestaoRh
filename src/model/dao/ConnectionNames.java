package model.dao;

public enum ConnectionNames {
	POLYGON("POLYGON_OWNER");
	
	private String connName;
	
	ConnectionNames(String s){
		this.connName = s;
	}
	
    public String getConnName() {
        return connName;
    }
	
}
