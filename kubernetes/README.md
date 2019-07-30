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

![plan](https://github.com/systane/courses/blob/master/kubernetes/plan.png)

![plan2](https://github.com/systane/courses/blob/master/kubernetes/plan2.png)
