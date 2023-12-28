package com.lld.and.hld.lldandhld.kafka;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.StringSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
// import io.confluent.common.utils.PerformanceStats.Callback;
import lombok.extern.slf4j.Slf4j;

/**
 * Producer
 */
@Slf4j
public class ProducerUtil {

    @Data
    class Student {
        int id;
        String name;
        int marks;
    }

    /**
     * ValueClass
     */
    class ValueSerializer implements org.apache.kafka.common.serialization.Serializer<Student> {

        private ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public byte[] serialize(String arg0, Student data) {

            try {
                if (data == null){
                    System.out.println("Null received at serializing");
                    return null;
                }
                System.out.println("Serializing...");
                return objectMapper.writeValueAsBytes(data);
            } catch (Exception e) {
                throw new SerializationException("Error wh`en serializing MessageDto to byte[]");
            }
        }    
    }

    KafkaProducer createProducer() throws UnknownHostException {
        Properties config = new Properties();
        try {
            config.put("client.id", InetAddress.getLocalHost().getHostName());
            config.put("bootstrap.servers", "localhost:29092");
            config.put("acks", "all");
            config.put("key.serializer", StringSerializer.class);
            config.put("value.serializer", StringSerializer.class);
            return new KafkaProducer<String, Object>(config);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        }
    }

    public static void main(String[] args) {
        ProducerUtil producerUtil = new ProducerUtil();
        try (KafkaProducer producer = producerUtil.createProducer()) {
            final ProducerRecord<String, Object> record = new ProducerRecord<>("test",
                    "key", new Student(1,"Manju", 1));
            Future<RecordMetadata> future = producer.send(record);
            RecordMetadata recordMetadata = future.get();
            System.out.println(record);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }`

}