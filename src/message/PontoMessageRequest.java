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
     private final String HOST_POLYGON = "amqp://qaawhjrg:ToYjFh11DYBCJId-_1axNQ1xjm4yt0j7@toad.rmq.cloudamqp.com/qaawhjrg";
     private final String QUE_POLYGON_INPUT = "CalculosAProcessar";
     
     ConnectionFactory factory;
     Connection connection;
 	 Channel channel;

	 String json;
	 public void sendPontoMessageRequest(String serializedObjects) throws ClassNotFoundException {
		 	Class.forName("org.slf4j.LoggerFactory");
	    	factory = new ConnectionFactory();
			try {
	        	factory.setUri(new URI(HOST_POLYGON));
				connection = factory.newConnection();
	        	channel = connection.createChannel();
	        	channel.queueDeclare(QUE_POLYGON_INPUT, true, false, false, null);
	        	channel.basicPublish("", QUE_POLYGON_INPUT, null, serializedObjects.getBytes());
	        	
	        	channel.close();
	        	connection.close();
			} catch (Exception e) {
				e.printStackTrace();
				
				if(channel != null) {
					try {
						channel.close();
						connection.close();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
	    }


}
