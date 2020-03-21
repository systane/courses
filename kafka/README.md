# **Apache Kafka**

## **Kafka Fundamentals:**
Before start to code an application using Kafka, we need first learn the core concepts of the components used in Kafka. 

- **Topics** Are a particular stream of data, where we are going to put the messages created by the writers. The consumers can also consume messages from topics. Topics are identified by its name and they can be split in partitions starting from 0 to whatever we want. Each message within a partition gets an incremental id called offset, and they never go back to zero, even if the messages from a partition are deleted. The order of the messages are guaranteed only within a partition (not across partitions), that's because partitions are independent (offset 0 in partition 0 doesn't represent the same data of the offset 0 in partition 1). The messages inside a partition is kept only by 1 week (default configuration), but you can change this behavior. When a message is written in a partition, it can't be modified and if you don't provide a key for this message, it will be delivered to a random partition (round robin), because are the keys that identifies which partition we're going to put our message inside our topic.

![topics](https://github.com/systane/courses/blob/master/kafka/img/topics.png)

- **Broker** A Kafka cluster is composed of multiple brokers (servers), and this servers/brokers hold our topics. Each Broker is identified by a **numeric id** and they can only contain certain topics partitions, and this means that when you have a kafka cluster composed by multiple brokers, if you create a topic with 3 partitions, each partition are going to be distribuited across the different brokers like in the bellow picture.

![brokers](https://github.com/systane/courses/blob/master/kafka/img/brokers.png)


- **Topic Replication Factory**  This mechanism allows Kafka to replicate our topics, so if the things go wrong and some brokers go down, our topics will still be able to receive/provide data from writers/consumers. Look that the bellow picture, and imagine that our broker 102 has gone down. If the topics replication factory configured, Kafka would have created a copy of the partition 0 in broker 101 and 102, and the partition 1, would have created in broker 102 and 103, so if the 102 goes down, our system can still work. But if we set up a topic replication factory of 2 (like in the bellow picture), but our broker 102 doesn't go down, it would still working at the same time like the other brokers (101 and 103). How a writer can decide which brokers choose to send a message? Kafka resolve this problem choosing a Leader for a partition. Only the leader partition can receive and serve data for a partition. The other brokers with a copy of this partition will only synchronize the data hold by the leader partition. To choose a leader kafka uses Zookeeper to make this task.

![brokers](https://github.com/systane/courses/blob/master/kafka/img/topics_replication_factory.png)


- **Producers** They are responsible to write data in the topics. The producers can choose one of the 3 acknowledgment of data:
    -acks=0: Producers won't wait for acknowledgment (possible data loss)
    -acks=1: Producers will wait for acknowledgment of the Leader partition (limited data loss)
    -acks=2: Producers will wait for acknowledgment of the Leader partition + replicas (no data loss)

- **Consumers** They read the data from topics in order, for example. Let's imagine that we have a topic with offset 0,1,2,3,4,5 the consumer are going to read this topics starting from offset 0 to 5. If this consumer is configured to read two or more topics, there is no guarantee that the messages between these topics are going to be read in order. The only guarantee is that the data is read in order by one topic at time, not by multiple topics. We can have a group of consumers that can read data from different topics, if we have more consumers than partition, some consumers will be inactive.

![brokers](https://github.com/systane/courses/blob/master/kafka/img/consumers_group.png)


- **Consumer Offsets**