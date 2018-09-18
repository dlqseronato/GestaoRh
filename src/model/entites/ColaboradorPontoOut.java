package model.entites;

public class ColaboradorPontoOut {
	private int matriculaFuncionario;
	private double valorHora;
	private double horasTrabalhadas;
	
	
	public ColaboradorPontoOut(Colaborador c) {
		super();
		this.matriculaFuncionario = (int) c.getId();
		this.valorHora = c.getCargo().getValorBaseHora();
		this.horasTrabalhadas = c.getHorasTrabalhadas();
	}
	
	public int getMatriculaFuncionario() {
		return matriculaFuncionario;
	}
	public void setMatriculaFuncionario(int matriculaFuncionario) {
		this.matriculaFuncionario = matriculaFuncionario;
	}
	public double getValorHora() {
		return valorHora;
	}
	public void setValorHora(double valorHora) {
		this.valorHora = valorHora;
	}
	public double getHorasTrabalhadas() {
		return horasTrabalhadas;
	}
	public void setHorasTrabalhadas(double horasTrabalhadas) {
		this.horasTrabalhadas = horasTrabalhadas;
	} 
		
}
