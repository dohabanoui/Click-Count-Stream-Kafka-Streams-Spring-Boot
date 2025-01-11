package ma.banouidoha.kafkastreamsclics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/click")
@CrossOrigin(origins = "*")
public class ClickStreamController {
    @Autowired
    private ClicksProducer producerService;
    @Autowired
    private KafkaConsumer consumerService;



    @GetMapping("/{userId}")
    public ResponseEntity<String> registerClick(@PathVariable String userId) {
        producerService.sendClick(userId);
        return ResponseEntity.ok("Click enregistre pour l'utilisateur" + userId);
    }
    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> getClickCounts() {
        return ResponseEntity.ok(consumerService.getClickCounts());
    }
}
