package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public static Connection getConnection() throws ClassNotFoundException {
		try {
			//Class.forName("oracle.jdbc.OracleDriver");
			//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE", "POLYGON_OWNER", "king*123");
			Class.forName("org.hsqldb.jdbcDriver");
			Connection conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/POLYGON_DB", "SA", "");
			conn.setAutoCommit(false);
			
			return conn;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}