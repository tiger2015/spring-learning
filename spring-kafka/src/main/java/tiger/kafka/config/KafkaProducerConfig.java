package tiger.kafka.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@EnableKafka
@PropertySource(value = {"classpath:application.properties", "${user.dir}/config/application.properties"}, ignoreResourceNotFound = true)
@Profile(value = {"producer"})
public class KafkaProducerConfig {
    @Value("${kafka.producer.bootstrap-servers:}")
    private String bootstrapServers;
    @Value("${kafka.producer.value-serializer:}")
    private String valueSerializerClass;
    @Value("${kafka.producer.key-serializer:}")
    private String keySerializerClass;

    @Bean
    private ProducerFactory producerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializerClass);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializerClass);
        ProducerFactory producerFactory = new DefaultKafkaProducerFactory(config);
        return producerFactory;
    }

    @Bean
    public KafkaTemplate kafkaTemplate(ProducerFactory producerFactory) {
        KafkaTemplate kafkaTemplate = new KafkaTemplate(producerFactory, true);
        return kafkaTemplate;
    }

}
