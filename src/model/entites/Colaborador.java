package model.entites;

import java.sql.Date;




public class Colaborador {

	private long id;	
	private String nome;	
	private long cpf;	
	private Date dtNascimento;	
	private String genero;	
	private String email;	
	private long ctpsNum;	
	private long pisPasep;
	private ContaBancaria conta;
	private Telefone telefone;	
	private Endereco endereco;	
	private Cargo cargo;
	private double salarioAtual;
	
	public Colaborador() {
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

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public long getCpf() {
		return cpf;
	}



	public void setCpf(long cpf) {
		this.cpf = cpf;
	}



	public Date getDtAniversario() {
		return dtNascimento;
	}



	public void setDtAniversario(Date dtAniversario) {
		this.dtNascimento = dtAniversario;
	}



	public String getGenero() {
		return genero;
	}



	public void setGenero(String genero) {
		this.genero = genero;
	}



	public long getCtpsnum() {
		return ctpsNum;
	}



	public void setCtpsnum(long ctpsnum) {
		this.ctpsNum = ctpsnum;
	}



	public long getPispasep() {
		return pisPasep;
	}



	public void setPispasep(long pispasep) {
		this.pisPasep = pispasep;
	}



	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}




	public long getCtpsNum() {
		return ctpsNum;
	}



	public void setCtpsNum(long ctpsNum) {
		this.ctpsNum = ctpsNum;
	}



	public long getPisPasep() {
		return pisPasep;
	}


	public void setPisPasep(long pisPasep) {
		this.pisPasep = pisPasep;
	}
	
	

	public ContaBancaria getConta() {
		return conta;
	}

	public void setConta(ContaBancaria conta) {
		this.conta = conta;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Colaborador(long id, String nome, long cpf, Date dtNascimento, String genero, String email, long ctpsNum,
			long pisPasep) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dtNascimento = dtNascimento;
		this.genero = genero;
		this.email = email;
		this.ctpsNum = ctpsNum;
		this.pisPasep = pisPasep;
	}



	public Colaborador(
			long id,
			String nome, 
			long cpf, 
			Date dtNascimento,
			String genero, 
			String email, 
			long ctpsNum,
			long pisPasep, 
			ContaBancaria conta, 
			Telefone telefone, 
			Endereco endereco, 
			Cargo cargo,
			double salarioAtual
			) {
		
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dtNascimento = dtNascimento;
		this.genero = genero;
		this.email = email;
		this.ctpsNum = ctpsNum;
		this.pisPasep = pisPasep;
		this.conta = conta;
		this.telefone = telefone;
		this.endereco = endereco;
		this.cargo = cargo;
		this.salarioAtual = salarioAtual;
	}

	







	
	
}
