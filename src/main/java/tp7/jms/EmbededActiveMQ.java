package tp7.jms;

import org.apache.activemq.broker.BrokerService;

/** Broker ActiveMQ embarqué : écoute sur toutes les interfaces, port 61616. */
public class EmbededActiveMQ {

    public static BrokerService startBroker() throws Exception {
        // a. Objet BrokerService représentant le broker
        BrokerService broker = new BrokerService();
        broker.setBrokerName("tp7-broker");
        broker.setPersistent(false);   // pas de stockage disque (TP)
        broker.setUseJmx(false);

        // b. Connecteur TCP sur toutes les interfaces (0.0.0.0), port 61616
        broker.addConnector("tcp://0.0.0.0:61616");

        // c. Démarrage du broker
        broker.start();
        System.out.println("Broker ActiveMQ démarré sur tcp://0.0.0.0:61616");
        return broker;
    }

    public static void main(String[] args) throws Exception {
        BrokerService broker = startBroker();
        broker.waitUntilStopped();     // garde le broker actif
    }
}
