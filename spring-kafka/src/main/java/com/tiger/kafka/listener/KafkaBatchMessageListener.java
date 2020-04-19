package com.tiger.kafka.listener;

import cn.starcart.cors.model.gridvrs.GridVrsMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.listener.BatchMessageListener;
import org.springframework.kafka.listener.ConsumerSeekAware;

import java.util.List;
import java.util.Map;

@Slf4j
public class KafkaBatchMessageListener<K, V> implements BatchMessageListener<K, V>, ConsumerSeekAware {
    @Override
    public void onMessage(List<ConsumerRecord<K, V>> data) {
        for (ConsumerRecord<K, V> record : data) {
            if (record.value() instanceof GridVrsMessage) {
                handleGridVrsMessage(record.key().toString(), (GridVrsMessage) record.value());
            } else if (record.value() instanceof String) {
                log.info("key:{}, value:{}", record.key().toString(), record.value());
            } else if (record.value() instanceof Long || record.value() instanceof Integer) {
                log.info("key:{}, value:{}", record.key().toString(), record.value());
            } else if (record.value() instanceof Float || record.value() instanceof Double) {
                log.info("key:{}, value:{}", record.key().toString(), record.value());
            } else {
                log.info("key:{}, value:{}", record.key().toString(), record.value().toString());
            }
        }

    }

    @Override
    public void registerSeekCallback(ConsumerSeekCallback callback) {
    }

    @Override
    public void onPartitionsAssigned(Map<TopicPartition, Long> assignments, ConsumerSeekCallback callback) {
        log.info("partition assigned");
        assignments.forEach(((topicPartition, aLong) -> {
            // callback.seek(topicPartition.topic(), topicPartition.partition(), 100);
            callback.seekToBeginning(topicPartition.topic(), topicPartition.partition());
            log.info("seek to begin");
        }));
    }

    @Override
    public void onIdleContainer(Map<TopicPartition, Long> assignments, ConsumerSeekCallback callback) {

    }

    private static void handleGridVrsMessage(String gridVrsId, GridVrsMessage gridVrsMessage) {
        long current = System.currentTimeMillis();
        if (gridVrsMessage.getGpsMessage() == null) {
            log.info("gridvrs:{} gps message is null", gridVrsId);
        } else {
            log.info("gridvrs:{}, obstime:{}, delay:{}", gridVrsId, gridVrsMessage.getObsTime(), current - gridVrsMessage.getObsTime() * 1000);
        }

    }
}
