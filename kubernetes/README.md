# Kubernetes

main topics:

1. What is Kubernetes?
1. Where it comes from?
1. Why Kubernetes?
1. What kind of problems it solve?
1. Architecture




## What kind of problems it solve?

Have you already wondered how huge applications like youtube, facebook and instagram can be online with millions of users at the same time sharing photos and videos, texting, seeing gifs, etc? Do you think that there is only one big server running one of those apps? Actually, there are many machines running the same code, working synchronously in a way that the final user can't even notice that in the underground exists this incredible team work between this machines, trying to act as only one computer.

But if there are so many computers working synchronously how they were configured to run the same code? How they deal with all the requests? How can we scale our application? Kubenetes comes to resolve all these problems. You just need to say: "Hey, I have this app, theses containers and I need you run it for me, please.", and Kubernetes does all the hard work for us. You can imagine that you're sending packages via courier services, first of all you package up your goods,then you label it  and give to the courier and they take care of everthing else. They deal with all the complex logistics, what is the shortest path, which vehicle use, etc.

In summary, Kubernetes is an orchestrator for microservice apps that run on container. That's the perfect definition for Kubernetes, and it was built for it.


## Architecture
To understand the basic architecture of Kubernets we first need to understand what is a microservice app and the role of an orchestrator.

A microservice app is just a name that describes an application that is made of lots of small and independent services. Working together these microservices become a meaningful app.

Think that a microservice app is like a team as we can see in the below picture. Each of them have a specific role in the team and can work independently but they need work together to achieve a bigger objective, i.e. win the match.

![plan](https://github.com/systane/courses/blob/master/kubernetes/plan.png)


The orchestrator acts like a team manager. He is responsible to organize them into a unit with a clear formation as we can see in the next picture. The manager also make sure that the can keeps in condition to play by subbing off some injured player.

![plan2](https://github.com/systane/courses/blob/master/kubernetes/plan2.png)

Kubernetes and football have a lot of thing is common. In Kubernetes world we need first package each service of our app as a pod (one or more containers packaged togher and deployed as a single unit). After this,Kubernetes can organize all these pods to work together, on the right sequence and node. This is what we call orchestrating.

![diagram](https://github.com/systane/courses/blob/master/kubernetes/diagram.png)

Diving into details Kubernetes is compose by clusters, and each cluster has one or more Masters with a lot of nodes. The masters make decision about which node run. With this component you can also monitor the clusters, schedule the work, etc. 
Masters have four components: 

![diagram2](https://www.google.com/imgres?imgurl=https%3A%2F%2Fmapr.com%2Fblog%2Fkubernetes-kafka-event-sourcing-architecture-patterns-and-use-case-examples%2Fassets%2Fclusters.png&imgrefurl=https%3A%2F%2Fmapr.com%2Fblog%2Fkubernetes-kafka-event-sourcing-architecture-patterns-and-use-case-examples%2F&docid=gcFZvPY86_XEtM&tbnid=5ExS3JaNRW9kcM%3A&vet=10ahUKEwjUodu1ouDjAhUrH7kGHRBbCiUQMwhNKAcwBw..i&w=899&h=504&bih=657&biw=1024&q=architecture%20cluster%20kubernetes&ved=0ahUKEwjUodu1ouDjAhUrH7kGHRBbCiUQMwhNKAcwBw&iact=mrc&uact=8)

- **ApiServer:** It acts like an interface that exposes the API (REST) at port 443, and throught that we can send a manifest (similar to a dockerfile) and deploy our app on the cluster. It's the only component that we deal directly and we can use the kuberctl for that.

- **Cluster Store:** It works like the memory of our cluster, where you can persist storage, state and configuration. This component uses **etcd** - a NoSQL distributed key value database. 

- **Controller Manager:** Kubernetes has a lot of controller like Node controller, Endpoint controller, etc. The main controller is something like "Controller of controllers". It is responsible to manager all other ones.

- **Scheduler:** It watches apiserver for new pods and assigns work to nodes.

On the other hand, the nodes run the actual work, report for the Master and watch for changes. Its architecture is composed for three components:

-**Kubelet:** It's the main agent in the Node, because it is reponsible to watches apiserver and everytime a change is detected, it instantiates a pod and reports back to master. It's simple. It watches, instantiates and reports. Kubelet also exposes and endpoint on 10255, we can use this port to spec the kubelet like a health check and running pods.

-**Container runtime:** It does the container management, because a container runs inside a pod, we need a way to deal with containers (starting/stopping, pulling images, etc). By default Kubernetes uses Docker for this task.

-**Kube-proxy:** It is responsible to make sure that every container in a pod share a single IP (one IP per pod). If there is necessity to address individual containers inside a pod, you can do this by using ports. The kube-proxy also does the load balance across all pods in a service (A way to hide multiple pods behind a single network address).


To make a deploy at a cluster Kubernetes it's pretty simple, you just need to package up your app inside a container and create an manifest (YAML file) that defines what SO image, port, networe and how many replicas use. This YAML file describe a deployment object. After that, we just give the file to Kubernetes on the master and the master deploy our app in the nodes.

![diagram3](https://github.com/systane/courses/blob/master/kubernetes/diagram2.png)
