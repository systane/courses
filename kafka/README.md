# **Apache Kafka**

## **Kafka Fundamentals:**
Before start to code an application using Kafka, we need first learn the core concepts of the components used in Kafka. 

- **Topics** Are a particular stream of data, where we are going to put the messages created by the writers. The consumers can also consume messages from topics. Topics are identified by its name and they can be split in partitions starting from 0 to whatever we want. Each message within a partition gets an incremental id called offset, and they never go back to zero, even if the messages from a partition are deleted. The order of the messages are guaranteed only within a partition (not across partitions), that's because partitions are independent (offset 0 in partition 0 doesn't represent the same data of the offset 0 in partition 1). The messages inside a partition is kept only by 1 week (default configuration), but you can change this behavior. When a message is written in a partition, it can't be modified and if you don't provide a key for this message, it will be delivered to a random partition, because are the keys that identifies which partition we're going to put our message inside our topic.

- **Broker** A Kafka cluster is composed of multiple brokers (servers), and this servers/brokers hold our topics



