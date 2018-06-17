package model.entites;





public class Cargo {

	private long id;
	
	private String nome;
	
	private String descricao;

	private long nivel;
	
	private double valorBaseHora;

	
	
	public Cargo() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public long getNivel() {
		return nivel;
	}

	public void setNivel(long nivel) {
		this.nivel = nivel;
	}

	public double getValorBaseHora() {
		return valorBaseHora;
	}

	public void setValorBaseHora(double valorBaseHora) {
		this.valorBaseHora = valorBaseHora;
	}

	public Cargo(long id, String nome, String descricao, long nivel, double valorBaseHora) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.nivel = nivel;
		this.valorBaseHora = valorBaseHora;
	}
	

	
	
	
}
