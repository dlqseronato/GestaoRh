package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public interface IGenericDAO<T,U> {
	
	public List<T> listar() throws Exception;
	
	public T buscar(U id) throws Exception;
	
	public void remover(U id) throws Exception;
	
	public void removerComRelacionamentos(U id) throws Exception;
	
	public void atualizar(T objeto) throws Exception;
	
	public void persistir(T objeto) throws Exception;
	
	public void persistirComRelacionamento(T objeto) throws Exception;

	public long buscarProximoIdSequence(String sequence) throws Exception;

	public PreparedStatement criarStatementBuscaSequence(Connection conexao, String sequence) throws Exception;

	

}