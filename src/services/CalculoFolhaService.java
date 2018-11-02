package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import dao.AbstractDAO;
import message.PontoMessageRequest;
import message.PontoMessageResponse;
import model.dao.CargoDAO;
import model.dao.ConnectionNames;
import model.entites.Cargo;
import model.entites.Colaborador;
import model.entites.ColaboradorPontoIn;
import model.entites.ColaboradorPontoOut;
import model.entites.PontoColaborador;
import model.utils.Serializer;


@WebServlet("/CalculoFolhaService")
public class CalculoFolhaService extends Service<Colaborador, Long, String> {
	private static int HTTP_COD_SUCESSO = 200;
	private static double horaCalculada;
	
	//public static double calculoSalario(long matricula, double valorHoraAtual,double horas) throws JAXBException {
	public static ColaboradorPontoIn calculoSalario(Colaborador c) throws JAXBException {
		ColaboradorPontoOut cRequest = new ColaboradorPontoOut(c);
		String serializedObject = new Serializer().serialize(cRequest);
		PontoMessageRequest p = new PontoMessageRequest();
		PontoMessageResponse r = new PontoMessageResponse(1, serializedObject);
		
		try {
			p.sendPontoMessageRequest(serializedObject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return r.getMessage();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			/*
			double salario = this.calculoSalario(c);
	        PrintWriter out = response.getWriter();
	        out.println("{");
	        out.println("\"Salario\": \""+salario+"\"");
	        out.println("}");
	        out.close();*/
			Colaborador c = parseEntityFromParams(request);
			String resp = new Serializer().serialize(CalculoFolhaService.calculoSalario(c));
 			ok(response, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	protected AbstractDAO<Colaborador, Long, String> createDao() {
		return null;
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
