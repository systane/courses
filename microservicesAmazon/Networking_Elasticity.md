## Network


### Route 53
It is a cloud domain name system (DNS) service that has servers distributed around the globe used to translates human-readble names like www.google.com into the numeric IP
address like 74.125.21.147
Route 53 scales automatically to manage spikes in DNS queries, allows you to register a domain name, route internet traffic to the resources for your domain and
check the health of your resources.

### EC2 Auto Scalling 
It is a service that monitors your EC2 instances and automatically adjusts by adding or removing EC2 instances based on conditions you define in order to maintain application
avaliability and provide peak performance to your users. EC2 Auto Scalling notifies SNS (Simple Notification Service) when a new instance is launching or terminating.
If you want to lauch instances of other services like DynamoDB, you can see the AWS Auto Scalling service, that can help you scale any other service.

### Elastic Load Balacing
Automatically distributes incoming application traffic accross multiple servers. This service can:
- Balance load between two or more servers
- Stands in front of a web server
- Provides redundancy and performance

There are 3 kind of Load Balancer:
- Application Load Balancer (ALB): Assume you're running microservices-architecture based application, you can use ALB to host the different API endpoints of your application on
 different servers. The load balancer then redirects the incoming HTTP traffic to the suitable server based on the rules you specify in the configuration.
- Network Load Balancer (NLB): Helps to balance the load on each individual server. Having an NLB becomes essential when you application requires handling millions of request per
second securely while maintainin ultra-low latencies.
- Classic Load Balancer (CLB): It is a previous generation option. You can choose a CLB when you have an existing application running in the EC2-Classic network.
