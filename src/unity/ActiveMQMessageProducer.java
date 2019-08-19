package unity;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ActiveMQMessageProducer {

	private Message message;
	private Session session;
	private MessageProducer producer;
	private Connection connection;

	private String url;
	public String queue;

	public ActiveMQMessageProducer(String url, String queuename) {
		this.url = url;
		this.queue = queuename;
	}

	public void connectProducer() {

		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
			connection = connectionFactory.createConnection();
			connection.start();

			// Creating session for sending messages
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Getting the queue 'VALLYSOFTQ'
			Destination destination = session.createQueue(queue);

			// MessageProducer is used for sending messages
			producer = session.createProducer(destination);

		} catch (Exception e) {
			System.out.println("Exception: " + e);
			e.printStackTrace();
		}
	}

	public void sendMessage(String msg) {
		sendMessage(msg, false);
	}

	public void sendMessage(String msg, boolean printMsgYesNo) {
		try {
			this.message = session.createTextMessage(msg);
			this.producer.send(message);
			if (printMsgYesNo) {
				System.out.println(msg);
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			this.session.close();
			this.connection.close();
		} catch (Exception e) {
			System.out.println("Exception: " + e);
			e.printStackTrace();
		}
	}
}
