package model.entites;

import java.sql.Date;
import java.util.List;

public class PontoColaborador {

	private long id;
	
	private List<Date> registros;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Date> getRegistros() {
		return registros;
	}

	public void setRegistros(List<Date> registros) {
		this.registros = registros;
	}
	
	
	
}
