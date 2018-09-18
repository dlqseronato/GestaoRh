package message;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class PontoMessageRequest  {
     private final String HOST_POLYGON = "amqp://vcudyxng:amJ5wmgaL0u16o_m0OOGDRPeZNDWI-sg@chimpanzee.rmq.cloudamqp.com/vcudyxng";
     private final String QUE_POLYGON_INPUT = "CalculosAProcessar";

	 String json;
	 public void sendPontoMessageRequest(String serializedObjects) throws ClassNotFoundException {
		 	Class.forName("org.slf4j.LoggerFactory");
	    	ConnectionFactory factory = new ConnectionFactory();
        	Connection connection;
        	Channel channel;
			try {
	        	factory.setUri(new URI(HOST_POLYGON));
				connection = factory.newConnection();
	        	channel = connection.createChannel();
	        	channel.queueDeclare(QUE_POLYGON_INPUT, true, false, false, null);
	        	channel.basicPublish("", QUE_POLYGON_INPUT, null, serializedObjects.getBytes());
	        	
	        	channel.close();
	        	connection.close();
			} catch (IOException | TimeoutException e) {
				e.printStackTrace();
			} catch (KeyManagementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }


}
