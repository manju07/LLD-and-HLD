https://www.baeldung.com/ops/kafka-docker-setup

docker-compose up -d
nc -z localhost 22181
nc -z localhost 29092
docker-compose logs kafka | grep -i started