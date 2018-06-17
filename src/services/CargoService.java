package services;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import model.dao.AbstractDAO;
import model.dao.CargoDAO;
import model.entites.Cargo;
import model.utils.Serializer;


@WebServlet("/CargoService")
public class CargoService extends Service<Cargo, Long> {
	private static final long serialVersionUID = 1L;

	@Override
	protected AbstractDAO<Cargo, Long> createDao() {
		return new CargoDAO();
	}

	@Override
	protected Cargo parseEntityFromParams(HttpServletRequest request) throws Exception {
		Serializer serializer = new Serializer();
		return serializer.desserialize(request.getReader(), Cargo.class);
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
