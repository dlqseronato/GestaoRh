package services;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import dao.AbstractDAO;
import model.dao.ColaboradorDAO;
import model.dao.ConnectionNames;
import model.entites.Colaborador;
import model.utils.Serializer;


@WebServlet("/ColaboradorService")
public class ColaboradorService extends Service<Colaborador, Long, String> {
	private static final long serialVersionUID = 1L;

	@Override
	protected AbstractDAO<Colaborador, Long, String> createDao() {
		return new ColaboradorDAO();
	}

	@Override
	protected Colaborador parseEntityFromParams(HttpServletRequest request) throws Exception {
		Serializer serializer = new Serializer();
		return serializer.desserialize(request.getReader(), Colaborador.class);
	}
	
	@Override
	protected Long parsePrimaryKeyFromParams(HttpServletRequest request) {
		return Long.parseLong(request.getParameter("id"));
	}
	
	@Override
	protected String parseActionFromParams(HttpServletRequest request) {
		return request.getParameter("action");
	}

	@Override
	protected String getConnName() {
		return ConnectionNames.POLYGON.getConnName();
	}
}
