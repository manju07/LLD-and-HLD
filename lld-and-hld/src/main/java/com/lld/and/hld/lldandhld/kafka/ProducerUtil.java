package com.lld.and.hld.lldandhld.kafka;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.commons.lang3.RandomUtils;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import com.lld.and.hld.lldandhld.kafka.model.Student;

import lombok.extern.slf4j.Slf4j;

/**
 * Producer
 */
@Slf4j
public class ProducerUtil {

    KafkaProducer<String, Student> createProducer() throws UnknownHostException {
        Properties config = new Properties();
        try {
            config.put("client.id", InetAddress.getLocalHost().getHostName());
            config.put("bootstrap.servers", "localhost:29092,localhost:39092");
            config.put("acks", "all");
            config.put("key.serializer", StringSerializer.class);
            config.put("value.serializer", StudentValueSeializerAndDeserializer.class);
            return new KafkaProducer<String, Student>(config);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void main(String[] args) {
        ProducerUtil producerUtil = new ProducerUtil();
        try {
            int id = 1;
            
            KafkaProducer producer = producerUtil.createProducer();

            while (true) {
                final ProducerRecord<String, Student> record = new ProducerRecord<>("topic1",
                        String.valueOf(id), new Student(id, "Manju", new RandomUtils().nextInt(60, 101)));
                        
                // Sync way to send events
                // sendEventInSync(producer, record);

                sendEventAsync(producer, record);
                
                // Thread.sleep(5000);
                id++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendEventAsync(KafkaProducer producer, final ProducerRecord<String, Student> record) {
        producer.send(record, new Callback() {
            public void onCompletion(RecordMetadata metadata, Exception e) {
                if (e != null) {
                    log.debug("Send failed for record {}", record, e);
                }
            }
        });
    }

    private static void sendEventInSync(KafkaProducer producer, final ProducerRecord<String, Student> record)
            throws InterruptedException, ExecutionException {
        Future<RecordMetadata> future = producer.send(record);
        RecordMetadata recordMetadata = future.get();
        System.out.println(recordMetadata);
    }

}