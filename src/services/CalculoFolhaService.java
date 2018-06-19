package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import model.entites.PontoColaborador;



public class CalculoFolhaService {
	private static int HTTP_COD_SUCESSO = 200;
	
	public static double calculoSalario(long matricula, double valorHoraAtual,double horas) throws JAXBException {
		 
	try {
		 
        URL url = new URL("http://localhost:8080/musicApp/banda/get");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        if (con.getResponseCode() != HTTP_COD_SUCESSO) {
            throw new RuntimeException("HTTP error code : "+ con.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
                     
        JAXBContext jaxbContext = JAXBContext.newInstance(CalculoFolhaService.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        PontoColaborador ponto = (PontoColaborador) jaxbUnmarshaller.unmarshal(br);
         
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        for(Date data :ponto.getRegistros()) {
        	String fdata = sdf.format(data);
        	int d = Integer.parseInt(fdata);
        	if(d == mes) {
        		
        	}
        }
          

        con.disconnect();
        
        return ponto;

    } catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
	return null;
	}
}
