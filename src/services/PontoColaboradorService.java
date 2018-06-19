package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.sun.xml.internal.ws.util.Pool.Unmarshaller;

import model.entites.PontoColaborador;



public class PontoColaboradorService {
	private static int HTTP_COD_SUCESSO = 200;
	
	public static void buscaPontoColaborador() throws JAXBException {
		 
	try {
		 
        URL url = new URL("http://localhost:8080/musicApp/banda/get");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        if (con.getResponseCode() != HTTP_COD_SUCESSO) {
            throw new RuntimeException("HTTP error code : "+ con.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
                     
        JAXBContext jaxbContext = JAXBContext.newInstance(PontoColaboradorService.class);

        javax.xml.bind.Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        PontoColaborador ponto = (PontoColaborador) jaxbUnmarshaller.unmarshal(br);
         
        System.out.println("------  Dados da Banda  -------- \n");
        System.out.println("Nome da Banda : "+ponto.getRegistros().get(1));
   
         

        con.disconnect();

    } catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
	}
}
