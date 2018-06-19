package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import model.entites.Cargo;



public class CargoDAO extends AbstractDAO<Cargo, Long> {

	@Override
	protected PreparedStatement criarStatementListar(Connection conexao) throws Exception {
		String sql = "SELECT * FROM CARGO";
		return conexao.prepareStatement(sql);
	}

	@Override
	protected Cargo parseObjeto(ResultSet rs) throws Exception {
		Cargo a = new Cargo(
						rs.getLong("ID"),
						rs.getString("NOME"),
						rs.getString("DESCRICAO"),
						rs.getLong("NIVEL"),
						rs.getDouble("VALOR_BASE_HORA")
						)
				;

		return a;
	}
	
	@Override
	protected PreparedStatement criarStatementRemover(Connection conexao, Long id) throws Exception {
		PreparedStatement statement= conexao.prepareStatement("DELETE FROM CARGO WHERE id=?");
		statement.setLong(1, id);
		return statement;
	}

	@Override
	protected PreparedStatement criarStatementAtualizar(Connection conexao, Cargo objeto) throws Exception {
		PreparedStatement statement = conexao
				.prepareStatement("UPDATE CARGO SET NOME=?, DESCRICAO=?, NIVEL =?, VALOR_BASE_HORA=? WHERE ID =?");
		statement.setString(1, objeto.getNome());
		statement.setString(2, objeto.getDescricao());
		statement.setLong(3, objeto.getNivel());
		statement.setDouble(4, objeto.getValorBaseHora());
		statement.setLong(5, objeto.getId());

		return statement;
	}

	@Override
	protected void carregarChavesGeradasNoObjeto(ResultSet generatedKeys, Cargo objeto) throws Exception {
		objeto.setId(generatedKeys.getLong(1));
	}

	@Override
	protected PreparedStatement criarStatementPersistir(Connection conexao, Cargo objeto) throws Exception {
		PreparedStatement statement = conexao.prepareStatement("INSERT INTO CARGO (ID,NOME,DESCRICAO,NIVEL,VALOR_BASE_HORA) VALUES (SEQ_CARGO.NEXTVAL,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, objeto.getNome());
		statement.setString(2, objeto.getDescricao());
		statement.setLong(3, objeto.getNivel());
		statement.setDouble(4, objeto.getValorBaseHora());
	
		return statement;
	}

	@Override
	protected PreparedStatement criarStatementBuscar(Connection conexao, Long id) throws Exception {
		PreparedStatement statement = conexao
				.prepareStatement("SELECT * FROM CARGO WHERE ID = ?");
		statement.setLong(1, id);
		return statement;
	}
	
	@Override
	public void removerComRelacionamentos(Long id) throws Exception {
		remover(id);
	}

	@Override
	protected List<PreparedStatement> criarStatementsPersistirComRelacionamento(Connection conexao, Cargo objeto)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persistirComRelacionamento(Cargo objeto) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected List<PreparedStatement> criarStatementsRemoverComRelacionamento(Connection conexao, Long id)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}





}
