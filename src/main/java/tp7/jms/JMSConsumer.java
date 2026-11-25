package tp7.jms;

import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;


public class JMSConsumer {

    public static void main(String[] args) throws Exception {
        // 4. ConnectionFactory
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        // 5. Connection
        Connection connection = factory.createConnection();

        // 6. Session (non transactionnelle, AUTO_ACKNOWLEDGE)
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 7. Destination : le même Topic que le producteur
        Topic topic = session.createTopic("TP7.TOPIC");

        // 8. MessageConsumer
        MessageConsumer consumer = session.createConsumer(topic);

        // 10. Listener : réception asynchrone
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    if (message instanceof TextMessage) {
                        System.out.println("Message reçu : " + ((TextMessage) message).getText());
                    } else {
                        System.out.println("Message reçu : " + message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // 9. Démarrage de la connexion
        connection.start();
        System.out.println("Consumer en attente de messages... (Entrée pour quitter)");
        System.in.read();

        consumer.close();
        session.close();
        connection.close();
    }
}
