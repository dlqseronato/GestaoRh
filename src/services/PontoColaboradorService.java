package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import model.entites.PontoColaborador;



public class PontoColaboradorService {
	private static int HTTP_COD_SUCESSO = 200;
	
	public static PontoColaborador buscaPontoColaborador(long id) throws JAXBException {
		 
	try {
		 
        URL url = new URL("http://localhost:8080/musicApp/banda/get");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        if (con.getResponseCode() != HTTP_COD_SUCESSO) {
            throw new RuntimeException("HTTP error code : "+ con.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
                     
        JAXBContext jaxbContext = JAXBContext.newInstance(PontoColaboradorService.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        PontoColaborador ponto = (PontoColaborador) jaxbUnmarshaller.unmarshal(br);       

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
