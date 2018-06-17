package model.entites;





public class ContaBancaria {

	private long id;

	private long banco;	

	private long conta;

	private long agencia;

	private long tipo;
		
	public ContaBancaria() {
		super();
	}
	
	public ContaBancaria(long id, long banco, long conta, long agencia, long tipo  ) {
		super();
		this.id = id;
		this.banco = banco;
		this.conta = conta;
		this.agencia = agencia;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getConta() {
		return conta;
	}

	public void setConta(long conta) {
		this.conta = conta;
	}

	public long getAgencia() {
		return agencia;
	}

	public void setAgencia(long agencia) {
		this.agencia = agencia;
	}

	public long getBanco() {
		return banco;
	}

	public void setBanco(long banco) {
		this.banco = banco;
	}

	public long getTipo() {
		return tipo;
	}

	public void setTipo(long tipo) {
		this.tipo = tipo;
	}




	
	
}
