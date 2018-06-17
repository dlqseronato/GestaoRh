package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.entites.Cargo;
import model.entites.Colaborador;
import model.entites.ContaBancaria;
import model.entites.Endereco;
import model.entites.Telefone;


public class ColaboradorDAO extends AbstractDAO<Colaborador, Long> {

	@Override
	protected PreparedStatement criarStatementListar(Connection conexao) throws Exception {
		String sql = "SELECT * FROM DADOS_COLABORADORES";
		return conexao.prepareStatement(sql);
	}

	@Override
	protected Colaborador parseObjeto(ResultSet rs) throws Exception {
		Colaborador a = new Colaborador(
				rs.getLong("ID"),
				rs.getString("NOME"),
				rs.getLong("CPF"),
				rs.getDate("DT_NASCIMENTO"),
				rs.getString("GENERO"),
				rs.getString("EMAIL"),
				rs.getLong("CTPS_NUM"),
				rs.getLong("PIS_PASEP"),
				new ContaBancaria(
						rs.getLong("ID"),
						rs.getLong("BANCO"),
						rs.getLong("AGENCIA"),
						rs.getLong("CONTA"),
						rs.getLong("TIPO")
						),
				new Telefone(
						rs.getLong("ID_TELEFONE"),
						rs.getLong("ID_COLABORADOR"),
						rs.getLong("PREFIXO"),
						rs.getLong("NUMERO"),
						rs.getLong("TIPO_TELEFONE")
						)
				,new Endereco(
						rs.getLong("ID_ENDERECO"),
						rs.getString("RUA"),
						rs.getLong("NUMERO_RUA"),
						rs.getLong("CEP"),
						rs.getString("ESTADO"),
						rs.getString("PAIS")
						)
				,new Cargo(
						rs.getLong("ID_CARGO"),
						rs.getString("NOME_CARGO"),
						rs.getString("DESCRICAO"),
						rs.getLong("NIVEL"),
						rs.getDouble("VALOR_BASE_HORA")
						)
				);

		return a;
	}
	
	@Override
	protected PreparedStatement criarStatementRemover(Connection conexao, Long id) throws Exception {
		PreparedStatement statement= conexao.prepareStatement("DELETE FROM COLABORADOR WHERE CPF=?");
		statement.setLong(1, id);
		return statement;
	}

	@Override
	protected PreparedStatement criarStatementAtualizar(Connection conexao, Colaborador objeto) throws Exception {
		PreparedStatement statement = conexao
				.prepareStatement("UPDATE animal SET nome=?, nascimento=?,especie_id=? WHERE id=?");
		statement.setString(1, objeto.getNome());
		if (objeto.getDtNascimento() != null)
			statement.setDate(2, new java.sql.Date(objeto.getDtNascimento().getTime()));
		else
			statement.setNull(2, java.sql.Types.DATE);
		

		return statement;
	}

	@Override
	protected void carregarChavesGeradasNoObjeto(ResultSet generatedKeys, Colaborador objeto) throws Exception {
		objeto.setId(generatedKeys.getLong(1));
	}

	@Override
	protected PreparedStatement criarStatementPersistir(Connection conexao, Colaborador objeto) throws Exception {
		PreparedStatement statement = conexao.prepareStatement("INSERT INTO COLABORADOR (ID,NOME,CPF,DT_NASCIMENTO, GENERO, EMAIL,CTPS_NUM,PIS_PASEP,ID_CONTA_BANCARIA, ID_ENDERECO, ID_CARGO) VALUES (SEQ_COLABORADOR.NEXTVAL,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, objeto.getNome());
		statement.setLong(2, objeto.getCpf());
		if (objeto.getDtNascimento() != null)
			statement.setDate(3, new java.sql.Date(objeto.getDtNascimento().getTime()));
		else
			statement.setNull(3, java.sql.Types.DATE);
		statement.setString(4, objeto.getGenero());
		statement.setString(5, objeto.getEmail());
		statement.setLong(6, objeto.getCtpsNum());
		statement.setLong(7, objeto.getPisPasep());
		statement.setLong(8, objeto.getConta().getId());
		statement.setLong(9, objeto.getEndereco().getId());
		statement.setLong(9, objeto.getCargo().getId());
		return statement;
	}

	@Override
	protected PreparedStatement criarStatementBuscar(Connection conexao, Long cpf) throws Exception {
		PreparedStatement statement = conexao
				.prepareStatement("SELECT * FROM DADOS_COLABORADORES WHERE CPF = ?");
		statement.setLong(1, cpf);
		return statement;
	}
	
	@Override
	public void removerComRelacionamentos(Long id) throws Exception {
		remover(id);
	}
}
