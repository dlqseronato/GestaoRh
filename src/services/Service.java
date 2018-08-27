package services;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AbstractDAO;
import model.dao.ConnectionNames;
import model.utils.Serializer;

public abstract class Service<T, U, V> extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected abstract AbstractDAO<T, U, V> createDao();

	protected abstract T parseEntityFromParams(HttpServletRequest request) throws Exception;

	protected abstract U parsePrimaryKeyFromParams(HttpServletRequest request);

	protected abstract V getConnName();

	protected abstract String parseActionFromParams(HttpServletRequest request);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			AbstractDAO<T, U, V> dao = createDao();
			String action = parseActionFromParams(request);
			if (action == null || action.equals("getAll")) {
				List<T> objects = dao.list(getConnName());
				String serializedObjects = new Serializer().serialize(objects);
				ok(response, serializedObjects);
			} else if (action.equals("get")) {
				U primaryKey = parsePrimaryKeyFromParams(request);
				T object = dao.find(getConnName(), primaryKey);
				String serializedObjects = new Serializer().serialize(object);
				ok(response, serializedObjects);
			}

		} catch (Exception e) {
			internalServerError(response, "Houve um problema ao listar os objetos.");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String action = parseActionFromParams(request);
			AbstractDAO<T, U, V> dao = createDao();
			if (action == null || action.equals("put")) {
				T object = parseEntityFromParams(request);
				dao.save(getConnName(), object);
				ok(response);
			} else if (action.equals("set")) {
				T object = parseEntityFromParams(request);
				dao.update(getConnName(), object);
				ok(response);
			}

		} catch (Exception e) {
 			internalServerError(response, "Houve um problema ao criar o objeto.");
		}
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			AbstractDAO<T, U, V> dao = createDao();
		try {
			U primaryKey = parsePrimaryKeyFromParams(request);
			if (primaryKey == null) {
				badRequest(response, "A chave primária deve ser informada.");
				return;
			}
			if (dao.find(getConnName(), primaryKey) == null) {
				notFound(response, "Objeto não encontrado");
				return;
			}
			dao.removeRelacionated(getConnName(), primaryKey);
			;
			ok(response);
		} catch (Exception e) {
			internalServerError(response, "Houve um erro ao remover objeto.");
		}
	}

	private void ok(HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setStatus(HttpServletResponse.SC_OK);
	}

	private void ok(HttpServletResponse response, String returnMessage) throws IOException {
		ok(response);
		response.getWriter().write(returnMessage);
	}

	private void badRequest(HttpServletResponse response, String errorMessage) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		response.getWriter().write(errorMessage);
	}

	private void notFound(HttpServletResponse response, String errorMessage) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		response.getWriter().write(errorMessage);
	}

	private void internalServerError(HttpServletResponse response, String errorMessage) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.getWriter().write(errorMessage);
	}
}
