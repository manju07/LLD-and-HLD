
Series docs baeldung
    - https://www.baeldung.com/apache-kafka-series

    
Installing thru brew:
    - brew install zookeeper
    - brew install kafka

start services
    - brew services start zookeeper
    - brew services start kafka



default port of kafka 9092
default port of zookeeper 2801

- Must be running on multiple brokers to have multiple replicas for a topic

Example:
    - 3 brokers - B1, B2, B3
    - 1 Topic - (topic-1)
    - 3 Partitions - P1, P2, P3
    - 3 Replicas - R1, R2

    Derived:
        -  B1 
            - P1(topic-1) 
            - P2-R1(topic1) 
            - P3-R1(topic1)
            
        -  B2 
            - P2(topic-1)
            - P1-R1(topic1)
            - P3-R2(topic1)

        -  B3
            - P3(topic-1)
            - P1-R2(topic1)
            - P2-R2(topic1)

    - Partition
        - Each partition will be available in different broker due to single point failure.
        - And partition replicas will be allocated to different brokers for same reason.

    - Producer
        - Producers publish message to any partition based on Key
        - Or Producers can publish to specific partition
        - Or Sticky partitoner

    - Consumer
        - They can consume messages from 1 or more partitions.
        - If there are more consumers than partitions, some of the consumers wont be receiving messages as each partition is allocated to one consumer.


    

- Spring Kafka  - https://www.baeldung.com/spring-kafka
    
    - Producer
        - Create beans 
            - ProducerFactory - This builds strategy for creating producer instances.
            - KafkaTemplate - This wrap up the Producer instance and provides the conveniant methods to publish the messages to the topic.

    - Consumer
        - Enable kafka on Configuration class level with annotation @EnableKafka
        - Creates beans
            - ConsumerFactory
            - KafkaListenerContainerFactory

        - Once beans are defined, We can use @KafkaListener for listening messages.










Order-Management-Async Following:
    - Reading and processing
        - If there is a failure
            - publish to retry-topic
        - else
            - Manual Ackowledge to broker

    - Consume from retry-topic and process thru Cron JOB



Exactly processing once thru Transactions:

    - https://www.baeldung.com/kafka-exactly-once

    - Producer:
        - Set the enable.idempotence and transaction.id(this will be unique for each producer even after restart)
            -   Example:
                - producerProps.put("enable.idempotence", "true");
                - producerProps.put("transactional.id", "prod-1");

        - Then enable the transactions
            - producer.initTransactions();

    - Consumer:
        - Set the isolation.level='read_committed', means that read messages of only committed.
        
        


