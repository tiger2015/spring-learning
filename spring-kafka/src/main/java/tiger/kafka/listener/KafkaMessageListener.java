package tiger.kafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

@Slf4j
public class KafkaMessageListener<K, V> implements MessageListener<K, V> {
    @Override
    public void onMessage(ConsumerRecord<K, V> data) {
           log.info(data.key().toString());
    }
}
