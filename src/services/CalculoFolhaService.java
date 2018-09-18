package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import dao.AbstractDAO;
import message.PontoMessageRequest;
import model.dao.CargoDAO;
import model.dao.ConnectionNames;
import model.entites.Cargo;
import model.entites.Colaborador;
import model.entites.ColaboradorPontoOut;
import model.entites.PontoColaborador;
import model.utils.Serializer;



public class CalculoFolhaService extends Service<ColaboradorPontoOut, Long, String> {
	private static int HTTP_COD_SUCESSO = 200;
	private static double horaCalculada;
	
	//public static double calculoSalario(long matricula, double valorHoraAtual,double horas) throws JAXBException {
	public static double calculoSalario(Colaborador c) throws JAXBException {
		PontoMessageRequest p = new PontoMessageRequest();
		ColaboradorPontoOut colaborador = new ColaboradorPontoOut(c);
		String serializedObject = new Serializer().serialize(colaborador);
		try {
			p.sendPontoMessageRequest(serializedObject);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return horaCalculada;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			ColaboradorPontoOut c = parseEntityFromParams(request);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected AbstractDAO<ColaboradorPontoOut, Long, String> createDao() {
		return null;
	}

	@Override
	protected ColaboradorPontoOut parseEntityFromParams(HttpServletRequest request) throws Exception {
		Serializer serializer = new Serializer();
		return serializer.desserialize(request.getReader(), ColaboradorPontoOut.class);
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
