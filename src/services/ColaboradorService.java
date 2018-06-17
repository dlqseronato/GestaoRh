package services;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import model.dao.AbstractDAO;
import model.dao.ColaboradorDAO;
import model.entites.Colaborador;
import model.utils.Serializer;

/**
 * Servlet implementation class AnimaisService
 */
@WebServlet("/ColaboradorService")
public class ColaboradorService extends Service<Colaborador, Long> {
	private static final long serialVersionUID = 1L;

	@Override
	protected AbstractDAO<Colaborador, Long> createDao() {
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
}
