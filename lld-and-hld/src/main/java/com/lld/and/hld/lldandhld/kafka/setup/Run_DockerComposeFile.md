https://www.baeldung.com/ops/kafka-docker-setup

docker-compose up -d
nc -z localhost 22181
nc -z localhost 29092
docker-compose logs kafka | grep -i started


References:
https://www.baeldung.com/kafka-custom-serializer
https://docs.confluent.io/kafka-clients/java/current/overview.html
https://docs.confluent.io/platform/current/clients/consumer.html
