package model.entites;


public class Endereco {
			
	private long id;
	
	private String rua;

	private long numero;

	private long cep;

	private String estado;

	private String pais;
	
		
	public Endereco() {
		super();
	}
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}



	public String getRua() {
		return rua;
	}



	public void setRua(String rua) {
		this.rua = rua;
	}



	public long getNumero() {
		return numero;
	}



	public void setNumero(long numero) {
		this.numero = numero;
	}


	public long getCep() {
		return cep;
	}



	public void setCep(long cep) {
		this.cep = cep;
	}



	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}



	public String getPais() {
		return pais;
	}



	public void setPais(String pais) {
		this.pais = pais;
	}






	public Endereco(long id, String rua, long numero, long cep, String estado, String pais) {
		super();
		this.id = id;
		this.rua = rua;
		this.numero = numero;
		this.cep = cep;
		this.estado = estado;
		this.pais = pais;
	}
	



	public Endereco(long id, String rua, long numero, long cep) {
		super();
		this.id = id;
		this.rua = rua;
		this.numero = numero;
		this.cep = cep;
	}



	
	
}
