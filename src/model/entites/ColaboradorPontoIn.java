package model.entites;

public class ColaboradorPontoIn {
	private String Id;
	private Funcionario Funcionario;
	private double ValorHora;
	private double horasTrabalhadas;
	private double SalarioBruto;
	private double Inss;
	private double Irrf;
	private double SalarioLiquido;
	private double AdicionalPericulosidade;
	
	public String getId() {
		return Id;
	}
	public double getAdicionalPericulosidade() {
		return AdicionalPericulosidade;
	}
	public void setAdicionalPericulosidade(double adicionalPericulosidade) {
		AdicionalPericulosidade = adicionalPericulosidade;
	}
	public void setId(String id) {
		Id = id;
	}
	public Funcionario getFuncionario() {
		return Funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		Funcionario = funcionario;
	}
	public double getValorHora() {
		return ValorHora;
	}
	public void setValorHora(double valorHora) {
		ValorHora = valorHora;
	}
	public double getHorasTrabalhadas() {
		return horasTrabalhadas;
	}
	public void setHorasTrabalhadas(double horasTrabalhadas) {
		this.horasTrabalhadas = horasTrabalhadas;
	}
	public double getSalarioBruto() {
		return SalarioBruto;
	}
	public void setSalarioBruto(double salarioBruto) {
		SalarioBruto = salarioBruto;
	}
	public double getInss() {
		return Inss;
	}
	public void setInss(double inss) {
		Inss = inss;
	}
	public double getIrrf() {
		return Irrf;
	}
	public void setIrrf(double irrf) {
		Irrf = irrf;
	}
	public double getSalarioLiquido() {
		return SalarioLiquido;
	}
	public void setSalarioLiquido(double salarioLiquido) {
		SalarioLiquido = salarioLiquido;
	}
	public ColaboradorPontoIn(String id, model.entites.Funcionario funcionario, double valorHora, double horasTrabalhadas,
			double salarioBruto, double inss, double irrf, double salarioLiquido) {
		super();
		Id = id;
		Funcionario = funcionario;
		ValorHora = valorHora;
		this.horasTrabalhadas = horasTrabalhadas;
		SalarioBruto = salarioBruto;
		Inss = inss;
		Irrf = irrf;
		SalarioLiquido = salarioLiquido;
	}
		
	
}
