package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.mysql.jdbc.Statement;
import dao.AbstractDAO;
import model.entites.Cargo;
import util.QueryWarehouse;



public class CargoDAO extends AbstractDAO<Cargo, Long,String> {
	
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
		String sql = QueryWarehouse.getQuery(this.getClass(),"polygonFindCargoById");
		return conn.prepareStatement(sql);
	}

	@Override
	protected PreparedStatement createStatementList(Connection conn) throws Exception {
		String sql = QueryWarehouse.getQuery(this.getClass(),"polygonFindCargoById");
		return conn.prepareStatement(sql);
	}

	@Override
	protected PreparedStatement createStatementRemove(Connection conn, Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected PreparedStatement createStatementSave(Connection conn, Cargo c, int nextId) throws Exception {
		PreparedStatement statement = conn.prepareStatement("INSERT INTO CARGO (ID,NOME,DESCRICAO,NIVEL,VALOR_BASE_HORA) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		statement.setInt(1, nextId);
		statement.setString(2, c.getNome());
		statement.setString(3, c.getDescricao());
		statement.setLong(4, c.getNivel());
		statement.setDouble(5, c.getValorBaseHora());
		return statement;
	}

	@Override
	protected PreparedStatement createStatementUpdate(Connection conn, Cargo c) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<PreparedStatement> createStatementsRemoveRelacionated(Connection conn, Cargo c) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<PreparedStatement> createStatementsSaveRelacionated(Connection conn, Cargo c) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Cargo parseObject(ResultSet rs) throws Exception {
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
	protected PreparedStatement createStatementFindNextId(Connection arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



}
