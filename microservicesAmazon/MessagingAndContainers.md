## Notification

### SNS
SNS (Amazon Simple Notification Service) is a service that allows you to send notifications to the users of your applications. SNS allows you to decouple the notification logic
from being embedded in your applications and allows notifications to be published to a large number of subscribers. The users/applications need to subscribe first to receive the
notifications and they can be send via mobile push, text messages/SMS or email.

### SQS
SQS (Amazon Simple Queue Service) is a fully managed queuing service that allows you to integrate queuing functionality in your application. SQS offers two types of message queues:
Standard (offers best-effort ordering) and FIFO (process messages in the order they arrive and only once).


## Containers

### ECS
ECS (Elastic Container Service) is an orchestration service used for automating deployment, scaling, and managing of your containerized applications. ECS works well with Docker
containers because it can launch and stop docker containers, it also can scale your apps and querying the state of your apps.
