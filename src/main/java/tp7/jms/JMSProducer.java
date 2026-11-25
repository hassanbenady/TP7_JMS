package tp7.jms;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSProducer {

    private static final String BROKER_URL = "tcp://localhost:61616"; // IP du broker : localhost ici
    private static final String TOPIC_NAME = "TP7.TOPIC";
    private static final String SENDER = "Prenom Nom"; // <-- à remplacer par votre nom

    public static void main(String[] args) throws Exception {
        // 2-4. ConnectionFactory (URL = protocole TCP + IP + port)
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(BROKER_URL);

        // 5. Connection + start
        Connection connection = factory.createConnection();
        connection.start();

        // 6. Session transactionnelle, AUTO_ACKNOWLEDGE
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

        // 7. Destination : Topic
        Topic topic = session.createTopic(TOPIC_NAME);

        // 8. MessageProducer non persistant
        MessageProducer producer = session.createProducer(topic);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        // 9. Message texte
        TextMessage message = session.createTextMessage("Hello World! From: " + SENDER);
        producer.send(message);

        // Session transactionnelle => commit obligatoire, sinon le message n'est jamais envoyé
        session.commit();
        System.out.println("Message envoyé : " + message.getText());

        // 10-11. Fermetures
        session.close();
        connection.close();
    }
}
