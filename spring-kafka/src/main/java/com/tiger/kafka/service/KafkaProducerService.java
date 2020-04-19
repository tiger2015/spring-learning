package com.tiger.kafka.service;

public interface KafkaProducerService<K, V> {

     void send(String topic, K key, V value);
}
