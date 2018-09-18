package model.entites;

public class Funcionario {
	private int Matricula;
	private double ValorHora;
	public int getMatricula() {
		return Matricula;
	}
	public void setMatricula(int matricula) {
		Matricula = matricula;
	}
	public double getValorHora() {
		return ValorHora;
	}
	public void setValorHora(double valorHora) {
		ValorHora = valorHora;
	}
	public Funcionario(int matricula, double valorHora) {
		super();
		Matricula = matricula;
		ValorHora = valorHora;
	}
	
	
}
