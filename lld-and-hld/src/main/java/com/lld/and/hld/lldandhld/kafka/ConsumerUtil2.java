package com.lld.and.hld.lldandhld.kafka;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.lld.and.hld.lldandhld.kafka.model.Student;

import lombok.extern.slf4j.Slf4j;

/**
 * Consumer
 */
@Slf4j
public class ConsumerUtil2 {

    private KafkaConsumer<String, Student> createConsumer() throws UnknownHostException {
        Properties config = new Properties();
        try {
            config.put("client.id", InetAddress.getLocalHost().getHostName());
            config.put("bootstrap.servers", "localhost:29092,localhost:39092");
            config.put("group.id", "topic1Group");
            config.put("auto.offset.reset.config", "earliest");
            config.put("key.deserializer", StringDeserializer.class);
            config.put("value.deserializer", StudentValueSeializerAndDeserializer.class);
            return new KafkaConsumer<String, Student>(config);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void main(String[] args) {
        ConsumerUtil2 consumerUtil = new ConsumerUtil2();
        try {
            final KafkaConsumer<String, Student> consumer = consumerUtil.createConsumer();
            consumer.subscribe(Arrays.asList("topic1"));

            // Thread thread1 = new Thread(() -> {
                pollRecords(consumer);
            // });

            // Thread thread2 = new Thread(() -> {
                // pollRecords(consumer);
            // });

            // thread1.start();
            // thread2.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void pollRecords(KafkaConsumer<String, Student> consumer) {
        while (true) {
            ConsumerRecords<String, Student> consumerRecords = consumer.poll(Duration.ofMillis(2000));

            // consumerRecords.forEach(consumerRecord -> {
            //     System.out.println("testing:"+ consumerRecord.value());
            //     Student student = consumerRecord.value();
            //     System.out.println(student);
            // });
            consumer.commitAsync();
        }
    }

}