package message;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.tribes.MessageListener;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import model.entites.Cargo;
import model.entites.ColaboradorPontoIn;
import model.utils.Serializer;

public class PontoMessageResponse {
	private final String HOST_POLYGON = "amqp://vcudyxng:amJ5wmgaL0u16o_m0OOGDRPeZNDWI-sg@chimpanzee.rmq.cloudamqp.com/vcudyxng";
	private final String QUE_POLYGON_INPUT = "CalculosAProcessar";
	private final String QUE_POLYGON_OUTPUT = "CalculosProcessados";
	Timer timer;
	String json;

	ColaboradorPontoIn colaboradorIn;

	public PontoMessageResponse(int seconds, String json) {
		
	}
	
	private ColaboradorPontoIn parseEntityFromParams(String entitySerialized) throws Exception {
		Serializer serializer = new Serializer();
		return serializer.desserialize(entitySerialized, ColaboradorPontoIn.class);
	}

	public ColaboradorPontoIn getMessage() {
		try {
			while (colaboradorIn == null) {
				Thread.sleep(1000);
				GetFromQueue();
				continue;
			}
			
			return this.colaboradorIn;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void GetFromQueue() {
		ConnectionFactory factory = new ConnectionFactory();
		try {
			factory.setUri(HOST_POLYGON);
			Connection connection;
			connection = factory.newConnection();
			Channel channel = connection.createChannel();
			channel.queueDeclare(QUE_POLYGON_OUTPUT, true, false, false, null);

			Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {
					String message = new String(body, "UTF-8");
					try {
						colaboradorIn = parseEntityFromParams(message);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			channel.basicConsume(QUE_POLYGON_OUTPUT, true, consumer);
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		} catch (KeyManagementException e1) {
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
	}
}
