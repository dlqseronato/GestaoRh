package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.mysql.jdbc.Statement;

import dao.AbstractDAO;
import message.PontoMessageRequest;
import model.entites.Cargo;
import model.entites.Colaborador;
import model.entites.ContaBancaria;
import model.entites.Endereco;
import model.entites.Telefone;
import util.QueryWarehouse;

public class ColaboradorDAO extends AbstractDAO<Colaborador, Long, String> {
	
	@Override
	public Connection getConnectionCustom(String connName) {
		String url = "jdbc:mysql://localhost/polygon_owner";
		String user = "root";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url, user, "");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	protected PreparedStatement createStatementFind(Connection conn, Long id) throws Exception {
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM POLYGON_OWNER.DADOS_COLABORADORES WHERE ID = ?");
		statement.setLong(1, id);
		return statement;
	}

	@Override
	protected PreparedStatement createStatementList(Connection conn) throws Exception {
		String sql = "SELECT * FROM POLYGON_OWNER.DADOS_COLABORADORES";
		return conn.prepareStatement(sql);
	}

	@Override
	protected PreparedStatement createStatementRemove(Connection conn, Long id) throws Exception {
		String sql = "SELECT * FROM POLYGON_OWNER.COLABORADOR";
		return conn.prepareStatement(sql);
	}

	@Override
	protected PreparedStatement createStatementSave(Connection conn, Colaborador c, int nextId) throws Exception {
		PreparedStatement statement = conn.prepareStatement("INSERT INTO POLYGON_OWNER.COLABORADOR (ID,NOME,CPF,DT_NASCIMENTO, GENERO, EMAIL,CTPS_NUM,PIS_PASEP,ID_CONTA_BANCARIA, ID_ENDERECO, ID_CARGO, NM_SERIE_RFID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		statement.setInt(1, nextId);
		statement.setString(2, c.getNome());
		statement.setLong(3, c.getCpf());
		if (c.getDtNascimento() != null)
			statement.setDate(4, c.getDtNascimento());
		else
			statement.setString(4,null); 	
		statement.setString(5, c.getGenero());
		statement.setString(6, c.getEmail());
		statement.setLong(7, c.getCtpsNum());
		statement.setLong(8, c.getPisPasep());
		try {
			statement.setLong(9, c.getConta().getId());
			statement.setLong(10, c.getEndereco().getId());
			statement.setLong(11, c.getCargo().getId());
			statement.setString(12, c.getNumSerieRFID());
		}catch (Exception e) {
			statement.setLong(9, 0);
			statement.setLong(10, 0);
			statement.setLong(11, 0);
		}
		
		return statement;
	}

	@Override
	protected PreparedStatement createStatementUpdate(Connection conn, Colaborador c) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		PreparedStatement statement = conn.prepareStatement("UPDATE POLYGON_OWNER.COLABORADOR SET NOME = ?, CPF = ?, DT_NASCIMENTO = ?, GENERO = ?, EMAIL = ?, CTPS_NUM = ?, PIS_PASEP = ?, ID_CONTA_BANCARIA = ?, ID_ENDERECO = ?, ID_CARGO = ?, NM_SERIE_RFID = ? WHERE ID = ?");
		statement.setString(1, c.getNome());
		statement.setLong(2, c.getCpf());
		if (c.getDtNascimento() != null)
			statement.setDate(3,c.getDtNascimento());
		else
			statement.setString(3,dateFormat.format(new Date(System.currentTimeMillis()))); 	
		statement.setString(4, c.getGenero());
		statement.setString(5, c.getEmail());
		statement.setLong(6, c.getCtpsNum());
		statement.setLong(7, c.getPisPasep());
		try {
			statement.setLong(8, c.getConta().getId());
			statement.setLong(9, c.getEndereco().getId());
			statement.setLong(10, c.getCargo().getId());
			statement.setString(11, c.getNumSerieRFID());
		}catch (Exception e) {
			statement.setLong(8, 0);
			statement.setLong(9, 0);
			statement.setLong(10, 0);
			statement.setString(11, c.getNumSerieRFID());
		}
		statement.setLong(12, c.getId());
		
 		return statement;
	}

	@Override
	protected List<PreparedStatement> createStatementsRemoveRelacionated(Connection conn, Colaborador c)
			throws Exception {
		List<PreparedStatement> statements = new ArrayList<>();
	
		if(c != null) {

		PreparedStatement stTelefone  = conn.prepareStatement("DELETE FROM POLYGON_OWNER.TELEFONE WHERE ID_COLABORADOR = ?");
		stTelefone.setLong(1, c.getId());
		statements.add(stTelefone);	
		
		PreparedStatement stColaborador = conn.prepareStatement("DELETE FROM POLYGON_OWNER.COLABORADOR WHERE ID = ?");
		stColaborador.setLong(1, c.getId());
		statements.add(stColaborador);	
		
	}

		
		return statements; 
	}

	@Override
	protected List<PreparedStatement> createStatementsSaveRelacionated(Connection conn, Colaborador c)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	protected PreparedStatement createStatementFindNextId(Connection conn) throws Exception {
		String sql = "SELECT IFNULL(MAX(ID)+1,1) FROM POLYGON_OWNER.COLABORADOR";
		return conn.prepareStatement(sql);
	}

	@Override
	protected Colaborador parseObject(ResultSet rs) throws Exception {
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
				,0,
				rs.getString("NM_SERIE_RFID")
				);
		a.setNewSalarioAtual();
		return a;
	}


}
