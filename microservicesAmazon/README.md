# **Cloud Fundamentals**


## Cloud Computing Types
Cloud computing can be divided in 3 different types:
1. IaaS - Infrastructure as a Service: In this kind of clouding deployment the provider supplies the hardware for you, they can supply storage, virtual server and tools for you manage then. Examples: Aws EC2 and Digital Ocean.
1. PaaS - Plataform as a Service: This type on cloud computing adds one layer at the top of IaaS, in this case the provider manage the hardware and operating system, so you can focus on managing and deploying applications. Examples: o Amazon Beanstalk, Heroku, Openshift and etc.
1. SaaS - Software as a Service: The last type of cloud computing adds one more layer at the top of PaaS, so if in PaaS you can manage and deploy your application, in SaaS you just use the service. Examples:gemidão do zap, google docs, gmail and etc.

## Deployment Models
There are 3 cloud computing deployment models:
1. Public Cloud: This kind of deployment makes resources (database, servers and etc) avaliable over the internet. Example: Amazon AWS.
1. Private Cloud (On premises): It's a private network (usually owned by companies) that supplies services to a limited number of people.
1. Hybrid Cloud:  This model is a combination of the private and public cloud, for example you could have a database hosted on a private cloud for security reasons and a web app hosted on a public cloud acessible over the internet.

# **Aws Cloud Fundamentals**

## Regions and Availability Zones
Region is a geographic location around the world and must contain at the least 2 Availability zone (AZs). You can select your region to reduce the latency and costs on AWS. Regions are isolated and independent, and resources are not automatically replicated across them, if you need cross resources betweern regions, you need to set it up. Availability Zones are isolated location within a region and it is a physical data center. So failure in one AZ does not impact another AZ. There is also the `Èdge Location` that is a mini datacenter used for cache large data files closer to user's location, like a CDN.


