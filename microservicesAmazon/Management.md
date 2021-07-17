## Management

### Cloud Trail
It's a service that allow you to audit (or review) everything that occurs in your AWS account. Cloud Trail does this by recording all the AWS API calls occurring in your account
delivering a log file to you. With Cloud Trail we can: know who has logged in, services that were accessed, actions performed, parameters for the actions, responses returned. You
can set up alarms and notifications if suspicious access occurs.

### CloudFormation
This service allows you to model your entire infraestructure in a text file template allowing you to provision AWS resources based on the scripts you write. You can write these
templates using JSON or YAML.

### Aws cli
We can use AWS Cli to manage AWS services, we just need to configure it:
1. create an IAM user with programatic access
2. in your terminal configure a new profile with the client credentials of the user created in the last step. You can do this by using the command `aws configure --profile new_name`.
3. Configure you system to know that you have sensitive information in your .aws folder
```
export AWS_CONFIG_FILE=~/.aws/config
export AWS_SHARED_CREDENTIALS_FILE=~/.aws/credentials
```
4. If you're using gitbash on windows without the wsl, you can configure your system with the following commands

```
setx AWS_ACCESS_KEY_ID AKIAIOSFODNN7EXAMPLE
setx AWS_SECRET_ACCESS_KEY wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY
setx AWS_DEFAULT_REGION us-west-2
```
5. test if everything if working as it should `aws iam list-users --profile <profile-name>` this command should returns the user created in the first step 


### Useful commands:
- Upload entire folder to s3 `aws s3 cp <path-directory> s3://<bucket-name> --recursive --profile <profile-name>`
