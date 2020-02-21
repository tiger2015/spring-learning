package tiger.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Profile("producer")
public class KafkaProducerServiceImpl<K, V> implements KafkaProducerService<K, V> {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Override
    public void send(String topic, K key, V value) {
        kafkaTemplate.send(topic, key, value);
    }
}
