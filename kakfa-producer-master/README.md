# kakfa-producer-
 Spring Boot Kafka Producer Example

Create two topics in "Kakfa":
# kafka-topics.sh --zookeeper localhost:2181 --create --topic Kafka_Message --partitions 1
# kafka-topics.sh --zookeeper localhost:2181 --create --topic Kafka_User --partitions 1


Run to Publish Topic "Kafka_Message" App Java Spring Boot:
# curl "http://127.0.0.1:8585/publish/teste"
OK




Run to Publish Topic "Kafka_User" App Java Spring Boot:
# curl "http://127.0.0.1:8585/publish/user/user_caiocf/2019"
OK
