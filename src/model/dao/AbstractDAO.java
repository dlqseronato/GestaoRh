package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entites.Colaborador;

public abstract class AbstractDAO<T, U> implements IGenericDAO<T, U> {

	@Override
	public List<T> listar() throws Exception {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<T> retorno = new ArrayList<T>();
		Exception ultimaExcecao = null;

		try {
			con = ConnectionFactory.getConnection();
			statement = this.criarStatementListar(con);
			rs = statement.executeQuery();

			while (rs.next()) {
				retorno.add(this.parseObjeto(rs));
			}

			return retorno;
		} catch (Exception e) {
 			ultimaExcecao = e;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				ultimaExcecao = e;
			}
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
				ultimaExcecao = e;
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
				ultimaExcecao = e;
			}
		}
		
		throw ultimaExcecao;
	}

	@Override
	public void persistir(T objeto) throws Exception {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet generatedKeys = null;
		Exception ultimaExcecao = null;

		try {
			con = ConnectionFactory.getConnection();
			statement = this.criarStatementPersistir(con, objeto);
			statement.executeUpdate();

			con.commit();
			generatedKeys = statement.getGeneratedKeys();
			generatedKeys.next();

			this.carregarChavesGeradasNoObjeto(generatedKeys, objeto);
			return;
		} catch (Exception e) {
			ultimaExcecao = e;
			e.printStackTrace();
		} finally {
			try {
				if (generatedKeys != null)
					generatedKeys.close();
			} catch (SQLException e) {
				ultimaExcecao = e;
			}
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
				ultimaExcecao = e;
			}
			try {
				if (con != null) {
					con.rollback();
					con.close();
				}
			} catch (Exception e) {
				ultimaExcecao = e;
			}
		}
		if (ultimaExcecao != null)
			throw ultimaExcecao;
	}

	@Override
	public T buscar(U id) throws Exception {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		T retorno = null;
		Exception ultimaExcecao = null;

		try {
			con = ConnectionFactory.getConnection();
			statement = this.criarStatementBuscar(con, id);
			rs = statement.executeQuery();

			if (rs.next())
				retorno = this.parseObjeto(rs);

			return retorno;
		} catch (Exception e) {
			ultimaExcecao = e;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				ultimaExcecao = e;
			}
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
				ultimaExcecao = e;
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
				ultimaExcecao = e;
			}
		}

		throw ultimaExcecao;
	}

	@Override
	public void atualizar(T objeto) throws Exception {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet generatedKeys = null;
		Exception ultimaExcecao = null;

		try {
			con = ConnectionFactory.getConnection();
			statement = this.criarStatementAtualizar(con, objeto);
			statement.executeUpdate();
			con.commit();
			return;
		} catch (Exception e) {
			ultimaExcecao = e;
		} finally {
			try {
				if (generatedKeys != null)
					generatedKeys.close();
			} catch (SQLException e) {
				ultimaExcecao = e;
			}
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
				ultimaExcecao = e;
			}
			try {
				if (con != null) {
					con.rollback();
					con.close();
				}
			} catch (Exception e) {
				ultimaExcecao = e;
			}
		}
		if (ultimaExcecao != null)
			if (ultimaExcecao != null)
				throw ultimaExcecao;
	}

	@Override
	public void remover(U id) throws Exception {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet generatedKeys = null;
		Exception ultimaExcecao = null;

		try {
			con = ConnectionFactory.getConnection();
			statement = this.criarStatementRemover(con, id);
			statement.executeUpdate();
			con.commit();
			return;
		} catch (Exception e) {
			ultimaExcecao = e;
		} finally {
			try {
				if (generatedKeys != null)
					generatedKeys.close();
			} catch (SQLException e) {
				ultimaExcecao = e;
			}
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
				ultimaExcecao = e;
			}
			try {
				if (con != null) {
					con.rollback();
					con.close();
				}
			} catch (Exception e) {
				ultimaExcecao = e;
			}
		}
		if (ultimaExcecao != null)
			throw ultimaExcecao;
	}
	
	@Override
	public void removerComRelacionamentos(U id) throws Exception {
		Connection con = null;
		List<PreparedStatement> statements = null;
		ResultSet generatedKeys = null;
		Exception ultimaExcecao = null;
		int n = 0;
		try {
			con = ConnectionFactory.getConnection();
			statements = this.criarStatementsRemoverComRelacionamento(con, id);
			
			for(PreparedStatement statement : statements) {
				try {
					n += statement.executeUpdate();
				} catch (SQLException e) {
					ultimaExcecao = e;
					e.printStackTrace();
				}
			}
			if(n == 5)
				con.commit();
			else {
				con.rollback();

			}

		} catch (Exception e) {
			ultimaExcecao = e;
			e.printStackTrace();
		} finally {
			try {
				if (generatedKeys != null)
					generatedKeys.close();
			} catch (SQLException e) {
				ultimaExcecao = e;
			}
			try {
				if (statements != null)
					for(PreparedStatement statement : statements) {
						try {
							statement.close();
						} catch (SQLException e) {
							ultimaExcecao = e;
						}catch (Exception e) {
							ultimaExcecao = e;
						}
					}
					
			} catch (Exception e) {
				ultimaExcecao = e;
			}
			try {
				if (con != null) {
					con.rollback();
					con.close();
				}
			} catch (Exception e) {
				ultimaExcecao = e;
			}
		}
		if (ultimaExcecao != null)
			throw ultimaExcecao;		
	}
	
	@Override
	public void persistirComRelacionamento(T objeto) throws Exception {
		Connection con = null;
		List<PreparedStatement> statements = null;
		ResultSet generatedKeys = null;
		Exception ultimaExcecao = null;
		int n = 0;
		try {
			con = ConnectionFactory.getConnection();
			statements = this.criarStatementsPersistirComRelacionamento(con, objeto);
			
			for(PreparedStatement statement : statements) {
				try {
					n += statement.executeUpdate();
				} catch (SQLException e) {
					ultimaExcecao = e;
				}
			}
			if(n == 5)
				con.commit();
			else {
				con.rollback();
			}
		} catch (Exception e) {
			ultimaExcecao = e;
			e.printStackTrace();
		} finally {
			try {
				if (generatedKeys != null)
					generatedKeys.close();
			} catch (SQLException e) {
				ultimaExcecao = e;
			}
			try {
				if (statements != null)
					for(PreparedStatement statement : statements) {
						try {
							statement.close();
						} catch (SQLException e) {
							ultimaExcecao = e;
						}catch (Exception e) {
							ultimaExcecao = e;
						}
					}
					
			} catch (Exception e) {
				ultimaExcecao = e;
			}
			try {
				if (con != null) {
					con.rollback();
					con.close();
				}
			} catch (Exception e) {
				ultimaExcecao = e;
			}
		}
		if (ultimaExcecao != null)
			throw ultimaExcecao;		
	}
	

	@Override
	public long buscarProximoIdSequence(String sequence) throws Exception {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		long retorno = 0;
		Exception ultimaExcecao = null;

		try {
			con = ConnectionFactory.getConnection();
			statement = this.criarStatementBuscaSequence(con, sequence);
			rs = statement.executeQuery();

			if (rs.next())
			{
				retorno = Long.parseLong(rs.getString(1));
				System.out.println(rs.getString(1));
			}
				
			return retorno;
		} catch (Exception e) {
			ultimaExcecao = e;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				ultimaExcecao = e;
			}
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
				ultimaExcecao = e;
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
				ultimaExcecao = e;
			}
		}

		throw ultimaExcecao;
	}

	@Override
	public PreparedStatement criarStatementBuscaSequence(Connection conexao, String sequence) throws Exception{
		PreparedStatement statement = conexao
				.prepareStatement("SELECT "+sequence+" FROM DUAL");

		return statement;
	}

	protected abstract PreparedStatement criarStatementBuscar(Connection conexao, U id) throws Exception;

	protected abstract PreparedStatement criarStatementRemover(Connection conexao, U id) throws Exception;

	protected abstract void carregarChavesGeradasNoObjeto(ResultSet generatedKeys, T objeto) throws Exception;

	protected abstract PreparedStatement criarStatementPersistir(Connection conexao, T objeto) throws Exception;
	
	protected abstract List<PreparedStatement> criarStatementsPersistirComRelacionamento(Connection conexao, T objeto) throws Exception;
	
	protected abstract List<PreparedStatement> criarStatementsRemoverComRelacionamento(Connection conexao, U id) throws Exception;

	protected abstract PreparedStatement criarStatementAtualizar(Connection conexao, T objeto) throws Exception;

	protected abstract PreparedStatement criarStatementListar(Connection conexao) throws Exception;

	protected abstract T parseObjeto(ResultSet rs) throws Exception;
}
