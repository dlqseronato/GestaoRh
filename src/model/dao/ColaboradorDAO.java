package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
/*
		Colaborador a = new Colaborador(
				rs.getLong("ID"),
				rs.getString("NOME"),
				rs.getLong("CPF"),
				rs.getDate("DT_NASCIMENTO"),
				rs.getString("GENERO"),
				rs.getString("EMAIL"),
				rs.getLong("CTPS_NUM"),
				rs.getLong("PIS_PASEP"));
				*/
		return a;
	}
	
	@Override
	protected PreparedStatement criarStatementRemover(Connection conexao, Long id) throws Exception {
		PreparedStatement statement= conexao.prepareStatement("DELETE FROM COLABORADOR WHERE ID=?");
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
			statement.setDate(3, objeto.getDtNascimento());
		else
			statement.setString(3,"");
		statement.setString(4, objeto.getGenero());
		statement.setString(5, objeto.getEmail());
		statement.setLong(6, objeto.getCtpsNum());
		statement.setLong(7, objeto.getPisPasep());
		statement.setLong(8, 1000);
		statement.setLong(9, 1002);
		try {
			statement.setLong(8, objeto.getConta().getId());
			statement.setLong(9, objeto.getEndereco().getId());
			statement.setLong(10, objeto.getCargo().getId());
		}catch (Exception e) {
			statement.setLong(8, 0);
			statement.setLong(9, 0);
			statement.setLong(10, 0);
		}
		
		return statement;
	
	}

	@Override
	protected PreparedStatement criarStatementBuscar(Connection conexao, Long cpf) throws Exception {
		PreparedStatement statement = conexao
				.prepareStatement("SELECT * FROM DADOS_COLABORADORES WHERE ID = ?");
		statement.setLong(1, cpf);
		return statement;
	}
	


	@Override
	protected List<PreparedStatement> criarStatementsPersistirComRelacionamento(Connection conexao, Colaborador objeto)
			throws Exception {
		List<PreparedStatement> statements = new ArrayList<>();
		PreparedStatement statement = null;
		objeto.getCargo().setId(buscarProximoIdSequence("SEQ_CARGO.NEXTVAL"));
		statement = conexao.prepareStatement("INSERT INTO CARGO (ID,NOME,DESCRICAO,NIVEL,VALOR_BASE_HORA) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		statement.setLong(1, objeto.getCargo().getId());
		statement.setString(2, objeto.getCargo().getNome());
		statement.setString(3, objeto.getCargo().getDescricao());
		statement.setLong(4, objeto.getCargo().getNivel());
		statement.setDouble(5, objeto.getCargo().getValorBaseHora());		
		statements.add(statement);	
		statement = null;
		
		objeto.getConta().setId(buscarProximoIdSequence("SEQ_CONTA_BANCARIA.NEXTVAL"));
		statement = conexao.prepareStatement("INSERT INTO CONTA_BANCARIA  (ID,BANCO,AGENCIA,CONTA,TIPO) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		statement.setLong(1, objeto.getConta().getId());
		statement.setLong(2, objeto.getConta().getBanco());
		statement.setLong(3, objeto.getConta().getAgencia());
		statement.setLong(4, objeto.getConta().getConta());
		statement.setLong(5, objeto.getConta().getTipo());		
		statements.add(statement);	
		statement = null;
		
		objeto.getEndereco().setId(buscarProximoIdSequence("SEQ_ENDERECO.NEXTVAL"));
		statement = conexao.prepareStatement("INSERT INTO ENDERECO (ID,RUA,NUMERO,CEP,ESTADO,PAIS) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		statement.setLong(1, objeto.getEndereco().getId());
		statement.setString(2, objeto.getEndereco().getRua());
		statement.setLong(3, objeto.getEndereco().getNumero());
		statement.setLong(4, objeto.getEndereco().getCep());
		statement.setString(5, objeto.getEndereco().getEstado());
		statement.setString(6, objeto.getEndereco().getPais());		
		statements.add(statement);	
		statement = null;
		
		objeto.setId(buscarProximoIdSequence("SEQ_COLABORADOR.NEXTVAL"));
		statement = conexao.prepareStatement("INSERT INTO COLABORADOR (ID,NOME,CPF,DT_NASCIMENTO, GENERO, EMAIL,CTPS_NUM,PIS_PASEP,ID_CONTA_BANCARIA, ID_ENDERECO, ID_CARGO) VALUES (?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		statement.setLong(1, objeto.getId());
		statement.setString(2, objeto.getNome());
		statement.setLong(3, objeto.getCpf());
		if (objeto.getDtNascimento() != null)
			statement.setDate(4, objeto.getDtNascimento());
		else
			statement.setString(4,"");
		statement.setString(5, objeto.getGenero());
		statement.setString(6, objeto.getEmail());
		statement.setLong(7, objeto.getCtpsNum());
		statement.setLong(8, objeto.getPisPasep());
		try {
			statement.setLong(9, objeto.getConta().getId());
			statement.setLong(10, objeto.getEndereco().getId());
			statement.setLong(11, objeto.getCargo().getId());
		}catch (Exception e) {
			statement.setLong(9, 0);
			statement.setLong(10, 0);
			statement.setLong(11, 0);
		}
		statements.add(statement);
		statement = null;
		objeto.getTelefone().setId(buscarProximoIdSequence("SEQ_TELEFONE.NEXTVAL"));
		statement = conexao.prepareStatement("INSERT INTO TELEFONE (ID, ID_COLABORADOR,PREFIXO,NUMERO,TIPO) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		statement.setLong(1, objeto.getTelefone().getId());
		statement.setLong(2, objeto.getId());
		statement.setLong(3, objeto.getTelefone().getPrefixo());
		statement.setLong(4, objeto.getTelefone().getNumero());
		statement.setDouble(5, objeto.getTelefone().getTipo());		
		statements.add(statement);	
		statement = null;
		
		return statements; 
	}

	@Override
	protected List<PreparedStatement> criarStatementsRemoverComRelacionamento(Connection conexao, Colaborador objeto)
			throws Exception {
		List<PreparedStatement> statements = new ArrayList<>();
		PreparedStatement statement = null;		
		if(objeto != null) {

		statement = conexao.prepareStatement("DELETE TELEFONE WHERE ID_COLABORADOR = ?", Statement.RETURN_GENERATED_KEYS);
		statement.setLong(1, objeto.getId());
		statements.add(statement);	
		statement = null;	
		
		statement = conexao.prepareStatement("DELETE COLABORADOR WHERE ID = ?", Statement.RETURN_GENERATED_KEYS);
		statement.setLong(1, objeto.getId());
		statements.add(statement);	
		statement = null;	
		

		statement = conexao.prepareStatement("DELETE CARGO WHERE ID = ?", Statement.RETURN_GENERATED_KEYS);
		statement.setLong(1, objeto.getCargo().getId());	
		statements.add(statement);	
		statement = null;
		

		statement = conexao.prepareStatement("DELETE CONTA_BANCARIA WHERE ID = ?", Statement.RETURN_GENERATED_KEYS);
		statement.setLong(1, objeto.getConta().getId());
		statements.add(statement);	
		statement = null;
		
		statement = conexao.prepareStatement("DELETE ENDERECO WHERE ID = ?", Statement.RETURN_GENERATED_KEYS);
		statement.setLong(1, objeto.getEndereco().getId());
		statements.add(statement);	
		statement = null;
	}

		
		return statements; 
	}




}
