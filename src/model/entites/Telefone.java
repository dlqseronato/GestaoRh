package model.entites;


public class Telefone {

	private long id;
	
	private long id_colaborador;
	
	private long prefixo;
	
	private long numero;
	
	private long tipo;
			
	public Telefone() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public long getPrefixo() {
		return prefixo;
	}

	public void setPrefixo(long prefixo) {
		this.prefixo = prefixo;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public long getTipo() {
		return tipo;
	}

	public void setTipo(long tipo) {
		this.tipo = tipo;
	}

	public long getId_colaborador() {
		return id_colaborador;
	}

	public void setId_colaborador(long id_colaborador) {
		this.id_colaborador = id_colaborador;
	}

	public Telefone(long id, long id_colaborador, long prefixo, long numero, long tipo) {
		super();
		this.id = id;
		this.id_colaborador = id_colaborador;
		this.prefixo = prefixo;
		this.numero = numero;
		this.tipo = tipo;
	}

	

	

	
	
}
