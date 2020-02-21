package tiger.kafka.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.stereotype.Component;
import tiger.kafka.listener.KafkaBatchMessageListener;
import tiger.kafka.listener.KafkaMessageListener;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@EnableKafka
@PropertySource(value = {"classpath:application.properties", "${user.dir}/config/application.properties"}, ignoreResourceNotFound = true)
@Profile(value = {"consumer"})
public class KafkaConsumerConfig {

    @Value("${kafka.consumer.bootstrap-servers:}")
    private String bootstrapServers;

    @Value("${kafka.consumer.group-id:test}")
    private String groupId;
    @Value("${kafka.consumer.topics:}")
    private String[] topics;
    @Value("${kafka.consumer.key-deserializer:}")
    private String keyDeserializerClass;
    @Value("${kafka.consumer.value-deserializer:}")
    private String valueDeserializerClass;
    @Value("${kafka.consumer.enable-auto-commit:true}")
    private boolean enableAutoCommit;
    @Value("${kafka.consumer.auto-offset-reset:latest}")
    private String autoOffsetReset;
    @Value("${kafka.consumer.poll-timeout.millis:5000}")
    private long pollTimeoutInMills;
    @Value("${kafka.consumer.max-poll-records:1000}")
    private int maxPollRecords;
    @Value("${kafka.consumer.enable-batch-listener:true}")
    private boolean enableBatchListener;
    @Value("${kafka.consumer.currency:1}")
    private int currency;

    @Bean
    public ConsumerFactory consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializerClass);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializerClass);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecords);
        ConsumerFactory consumerFactory = new DefaultKafkaConsumerFactory(props);
        return consumerFactory;
    }


    @Bean
    public ContainerProperties containerProperties() {
        ContainerProperties containerProperties = new ContainerProperties(topics);
        containerProperties.setPollTimeout(pollTimeoutInMills);
        if (enableBatchListener) {
            containerProperties.setMessageListener(new KafkaBatchMessageListener<>());
        } else {
            containerProperties.setMessageListener(new KafkaMessageListener<>());
        }
        return containerProperties;
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory containerFactory(ConsumerFactory consumerFactory) {
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory);
        factory.setConcurrency(currency);
        return factory;
    }

    @Bean("kafkaMessageListenerContainer")
    @Profile("nonoccurrence")
    public KafkaMessageListenerContainer listenerContainer(ConsumerFactory consumerFactory, ContainerProperties containerProperties) {
        KafkaMessageListenerContainer container = new KafkaMessageListenerContainer<>(consumerFactory, containerProperties);
        return container;
    }

    @Bean("concurrentMessageListenerContainer")
    @Profile("concurrent")
    public ConcurrentMessageListenerContainer concurrentMessageListenerContainer(ConsumerFactory consumerFactory, ContainerProperties containerProperties) {
        ConcurrentMessageListenerContainer container = new ConcurrentMessageListenerContainer(consumerFactory, containerProperties);
        container.setConcurrency(currency);
        return container;
    }
}
