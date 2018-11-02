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
	private double horasTrabalhadas;
	private String numSerieRFID;

	public String getNumSerieRFID() {
		return numSerieRFID;
	}

	public void setNumSerieRFID(String numSerieRFID) {
		this.numSerieRFID = numSerieRFID;
	}

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

	public Colaborador(long id, String nome, long cpf, Date dtNascimento, String genero, String email, long ctpsNum,
			long pisPasep, ContaBancaria conta, Telefone telefone, Endereco endereco, Cargo cargo,
			double salarioAtual) {

		super();
		this.id = id;
		if (nome != null)
			this.nome = nome;
		else
			this.nome = "";
		if (cpf != 0)
			this.cpf = cpf;
		else
			this.cpf = new Long(0);
		if (dtNascimento != null)
			this.dtNascimento = dtNascimento;
		else
			new Date(System.currentTimeMillis());
		if (genero != null)
			this.genero = genero;
		else
			genero = "N";
		if (email != null)
			this.email = email;
		else
			email = "";
		if (ctpsNum != 0)
			this.ctpsNum = ctpsNum;
		else
			this.ctpsNum = new Long(0);
		if (pisPasep != 0)
			this.pisPasep = pisPasep;
		else
			pisPasep = new Long(0);
		if (conta != null)
			this.conta = conta;
		else
			this.conta = new ContaBancaria();
		if (telefone != null)
			this.telefone = telefone;
		else
			telefone = new Telefone();
		if (endereco != null)
			this.endereco = endereco;
		else
			endereco = new Endereco();
		if (cargo != null)
			this.cargo = cargo;
		else
			this.cargo = new Cargo();
		if (salarioAtual != 0)
			this.salarioAtual = salarioAtual;
		else
			setNewSalarioAtual();

	}

	public Colaborador(long id, String nome, long cpf, Date dtNascimento, String genero, String email, long ctpsNum,
			long pisPasep, double salarioAtual,double horasTrabalhadas) {
		super();
		this.conta = new ContaBancaria();
		this.telefone = new Telefone();
		this.endereco = new Endereco();
		this.cargo = new Cargo();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dtNascimento = dtNascimento;
		this.genero = genero;
		this.email = email;
		this.ctpsNum = ctpsNum;
		this.pisPasep = pisPasep;
		this.salarioAtual = salarioAtual;
		this.horasTrabalhadas = horasTrabalhadas;
	}

	public double getSalarioAtual() {
		return salarioAtual;
	}

	public void setSalarioAtual(double salarioAtual) {
		this.salarioAtual = salarioAtual;
	}

	public void setNewSalarioAtual() {
		if (cargo != null) {
			this.horasTrabalhadas = 180+(Math.random()*40);
			this.salarioAtual = Math.round(cargo.getValorBaseHora() * horasTrabalhadas);
		}
			
	}

	public double getHorasTrabalhadas() {
		return horasTrabalhadas;
	}

	public void setHorasTrabalhadas(double horasTrabalhadas) {
		this.horasTrabalhadas = horasTrabalhadas;
	}
	
	

}
