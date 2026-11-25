# TP7 – JMS avec ActiveMQ (Développement d'applications réparties – LSI3)

**Auteur :** Med Hassan Ben Adi
## Objectif
Communication asynchrone producteur/consommateur via JMS (Topic) avec un broker ActiveMQ embarqué.

## Structure
- `EmbededActiveMQ` / `BrokerStarter` : démarre le broker (`tcp://0.0.0.0:61616`)
- `JMSConsumer` : s'abonne au topic `TP7.TOPIC` avec un `MessageListener`
- `JMSProducer` : publie `Hello World! From: <nom>` (non persistant, session transactionnelle)

## Exécution
1. Lancer `BrokerStarter`
2. Lancer `JMSConsumer`
3. Lancer `JMSProducer`

> Le consumer doit être lancé **avant** le producer : un Topic non durable n'est pas conservé pour les abonnés absents.
