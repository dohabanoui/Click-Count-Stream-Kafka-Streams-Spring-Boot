package ma.banouidoha.kafkastreamsclics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@EnableKafka
public class ClicksProducer {

    private static final String TOPIC = "clicks";  // Nom du topic Kafka

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;  // KafkaTemplate pour envoyer des messages

    // Méthode pour envoyer un clic au topic Kafka
    public void sendClick(String userId) {
        // Générer un message de clic avec une clé unique (userId) et une valeur "click"
        String message = "click";
        kafkaTemplate.send(TOPIC, userId, message);  // Envoi du message dans Kafka avec userId comme clé
    }
}
