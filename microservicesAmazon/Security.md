## Security

### Aws WAF
AWS WAF or AWS Web Application Firewall provides a firewall that protects your web application. WAF can stop common web attacks by reviewing the data sent to your application
and stopping wel-known attacks. AWS WAF combines three services:
- AWS WAF: It allows you to protect your web apps from common web exploits by monitoring and controlling the web requests coming to an Amazon Api Gateway, CloudFront or an 
Application Load Balancer.
- AWS Shield: It provides continuous DDoS attack detection and automatic mitigations.
- AWS Firewall Manager: It allows you to configure and manage firewall rules across accounts and applications centrally.

### IAM (Identity & Acces Management):
It allows you to configure who can access our AWS account, services or even apps running in our account. IAM is a global service available across all regions.
    - IAM User: It is an entity that you create an AWS to represent Person or service that interacts with services or applications running in your AWS account. An User in AWS
    consist of a username and access credentials.
    - IAM Group: It is a collection of users. You can specify permissions for a collection which makes permissions easier to manage.
    - IAM Role: is an identity with permissions or a set of privileges (pollicies) that are not associated with a specific IAM user or IAM group. Roles can be attached to a user, and
    a user can assume a single role temporarily to perform a specific task.
    - Policy: is a way to define granular level permissions and can be attached to users, groups and roles.
    
OBS: EC2 Security Group are not a part of IAM security group. They are two totally different things. EC2 Security groups are associated with EC2 instance and act like a firewall
for your virtual servers to either allow or deny access.
