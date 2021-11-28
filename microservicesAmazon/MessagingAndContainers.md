## Notification

### SNS
SNS (Amazon Simple Notification Service) is a service that allows you to send notifications to the users of your applications. SNS allows you to decouple the notification logic
from being embedded in your applications and allows notifications to be published to a large number of subscribers. The users/applications need to subscribe first to receive the
notifications and they can be send via mobile push, text messages/SMS or email.

### SQS
SQS (Amazon Simple Queue Service) is a fully managed queuing service that allows you to integrate queuing functionality in your application. SQS offers two types of message queues:
- Standard (offers best-effort ordering): In this type of message queue, occasionally the messages might be delivered in a different order from which they were sent. This type of queue has unlimited throughput (transaction per second) and at the least one message is delivered, but occasionally more than one copy is delivered.
- FIFO (process messages in the order they arrive and only once). In this type, all messages are delivered in the order from which they were sent, and exactly one message (no duplicates) is delivered, and it remains avaliable until the consumer processes and deletes it. This kind of queue support up to 300 messages per second.

SQS has a functionality known as DLQ (Dead-Letter-Queue) that is used to store all messages that were'nt processed. Maybe a consumer tried to process a message multiple times without succcess, so SQS sent this message to DLQ (Standard or FIFO queue) and the developer can analyze and debug the messages from this queue.


## Containers

### ECS
ECS (Elastic Container Service) is an orchestration service used for automating deployment, scaling, and managing of your containerized applications. ECS works well with Docker
containers because it can launch and stop docker containers, it also can scale your apps and querying the state of your apps.
