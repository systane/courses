# Kubernetes

main topics:

1. What is Kubernetes?
1. Where it comes from?
1. Why Kubernetes?
1. What kind of problems it solve?
1. Architecture
1. Hands on




## **What kind of problems it solve?**

Have you already wondered how huge applications like youtube, facebook and instagram can be online with millions of users at the same time sharing photos and videos, texting, seeing gifs, etc? Do you think that there is only one big server running one of those apps? Actually, there are many machines running the same code, working synchronously in a way that the final user can't even notice that in the underground exists this incredible team work between this machines, trying to act as only one computer.

But if there are so many computers working synchronously how they were configured to run the same code? How they deal with all the requests? How can we scale our application? Kubenetes comes to resolve all these problems. You just need to say: "Hey, I have this app, theses containers and I need you run it for me, please.", and Kubernetes does all the hard work for us. You can imagine that you're sending packages via courier services, first of all you package up your goods,then you label it  and give to the courier and they take care of everthing else. They deal with all the complex logistics, what is the shortest path, which vehicle use, etc.

In summary, Kubernetes is an orchestrator for microservice apps that run on container. That's the perfect definition for Kubernetes, and it was built for it.


## **Architecture**
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

![diagram2](https://mapr.com/blog/kubernetes-kafka-event-sourcing-architecture-patterns-and-use-case-examples/assets/clusters.png)

- **ApiServer:** It acts like an interface that exposes the API (REST) at port 443, and throught that we can send a manifest (similar to a dockerfile) and deploy our app on the cluster. It's the only component that we deal directly and we can use the kuberctl for that.

- **Cluster Store:** It works like the memory of our cluster, where you can persist storage, state and configuration. This component uses **etcd** - a NoSQL distributed key value database. 

- **Controller Manager:** Kubernetes has a lot of controller like Node controller, Endpoint controller, etc. The main controller is something like "Controller of controllers". It is responsible to manager all other ones.

- **Scheduler:** It watches apiserver for new pods and assigns work to nodes.

On the other hand, the nodes run the actual work, report for the Master and watch for changes. Its architecture is composed for three components:

-**Kubelet:** It's the main agent in the Node, because it is reponsible to watches apiserver and everytime a change is detected, it instantiates a pod and reports back to master. It's simple. It watches, instantiates and reports. Kubelet also exposes and endpoint on 10255, we can use this port to spec the kubelet like a health check and running pods.

-**Container runtime:** It does the container management, because a container runs inside a pod, we need a way to deal with containers (starting/stopping, pulling images, etc). By default Kubernetes uses Docker for this task.

-**Kube-proxy:** It is responsible to make sure that every container in a pod share a single IP (one IP per pod). If there is necessity to address individual containers inside a pod, you can do this by using ports. The kube-proxy also does the load balance across all pods in a service (A way to hide multiple pods behind a single network address).


After all this, let's better understand what is a pod. As said before, a pod is the atomic unit in Kubernetes, and you must have a node to bring up a pod. At Docker the container is the atomic unit. But remember: In Kubernetes, a pod has one or more containers running. You can imagine it like a sandbox to run containers and all these can share the same environment (same IP address, volume, etc). Pods are also the unit scale, which you can add or remove pods and not by adding/removing containers inside a pod. Another interesting thing is that a pod can't be partially online with just some containers on and others off. Or the all containers inside a pod are online, so then the pod is also avaliable, or there isn't a pod.

Pods are mortals, they can die (anytime) inside a node and a new one can comes to life inside another node with a different ip address. So you cannot trust in these ip address to communicate with pods in different nodes. This is where services come to play. We can imagine a service like a stable abstraction point for multiple pods, and they provide a load balance of the request. 

![diagram3](https://github.com/systane/courses/blob/master/kubernetes/diagram3.png)


This load balance is make by using **labels**. With labels, the service can identify all pods with the same labels and make de load balance works. A service is an object like a pod and we can define it with a YAML file using the apiserver. 

![diagram4](https://github.com/systane/courses/blob/master/kubernetes/diagram4.png)

To make a deploy at a cluster Kubernetes it's pretty simple, you just need to package up your app inside a container and create an manifest (YAML file) that defines what SO image, port, network and how many replicas use. This YAML file describe a deployment object. After that, we just give the file to Kubernetes on the master and the master deploy our app in the nodes.

![diagram5](https://github.com/systane/courses/blob/master/kubernetes/diagram5.png)


## **Hands on**

- ### **Pods**

    Let's starting this chapter creating with a little of code. We will create a pod, service and a deployment object into our Cluster. Before starting we need to install the command line interface of kubernetes kubectl and minikube a light version created to run locally.  We can easily install these two following the instructions from this link: https://kubernetes.io/docs/tasks/tools/install-minikube/

    After minikube and kubectl installed, let's start creating a POD based on the following YAML file (manifest):

        apiVersion: v1
        kind: Pod
        metadata:
            name: hello-pod
            labels:
                zone: prod
                version: v1
        spec:
            containers:
            - name: hello-ctr
            image: nigelpoulton/pluralsight-docker-ci:latest
            ports:
            -  containerPort: 8080


    After you create the file, execute the following command to create a pod: `kubectl create -f pod.yml`. The `-f` flag sets the path to yaml file that we've just created. If everything gone right, you'll see this output on console `pod/hello-pod created`.

    To see all your running pods, just type `kubectl get pods`. If you wanna filter the list of running pods,you can type ** slash + name of your pod** like this: `kubectl get pods/hello-pod`. And if you want to see all running pods independently of namespace, just type `kubectl get pods --all-namespaces`. And you want to delete a specific pod, you can use `kubectl delete pods hello-pod`.


- ### **Replication Controller**

    To start scalling our pods we usually don't create more pods directly. We use another type of object to do this work for us, and this object is the ReplicationController. We can create this kind of object with the following yaml file:


        apiVersion: v1
        kind: ReplicationController
        metadata:
        name: hello-rc
        spec:
        replicas: 10
        selector:
            app: hello-world
        template:
            metadata:
            labels:
                app: hello-world
            spec:
            containers:
            - name: hello-pod
                image: nigelpoulton/pluralsight-docker-ci:latest
                ports:
                -  containerPort: 8080

    As you can see, in this file we want 10 replicas of our hello-pod, so the ReplicationController we'll create these pods for us and kubernetes will constantly watch if we have this **desired state** of 10 replicas of our hello-pod at port 8080.

    To create our replicationController object we can use the same command used before: `kubectl create -f rc.yml`. If you execute `kubectl get rc` you'll se this a output showing the numbers of desired replicas and how many are running. You can edit the yaml file and use the command `kubectl apply -f rc.yml` to apply the changes in your current replicationController. To get a more detailed vision of your containers and replicationController, you can use `kubectl get rc -o wide`. If you want to delete this replicationController, you can use `kubectl delete rc hello-rc`.

- ### **Services**
With services our application can communicate with the outside world (outside the cluster) and with other pods (inside the cluster). You can think a service like an interface with reliables IP, DNS and Port. This interface will be also responsible to make the load balancing of all requests.

We can create a service using a yaml file too, something like this:

    apiVersion: v1
    kind: Service
    metadata:
        name: hello-svc
        labels:
            app: hello-world
    spec:
        type: NodePort
        ports:
        - port: 8080
           
            protocol: TCP
        selector:
            app: hello-world

A point of attention in this yaml file is the type (ServiceType), we can configure three different type of services:
- ClusterIP: Gives the service a stable internal cluster IP (Default option), in other words it only make the service avaliable to other nodes within the cluster.
- NodePort: Exposes the app outside of the cluster by adding a cluster-wide port on top of ClusterIP.
- LoadBalancer: Integrates NodePort with cloud-based load balancers.

This time we are goingo to create a service of type NodePort, so we can use this command to create it `kubectl create - f svc.yml` and describe it with `kubectl describe svc hello-svc`. With this command we can se the output to 30001 and a list of endpoints. This list shows us all the endpoints of our apods that are running.

- ## Deployments**
This kind of object is frequently used to make rollbacks and rolling updates. As we saw before, Replication Controller works like a wrapper for Pods, and it gives scalability, reliability and desired state. Deployments add one more layer at the top of this stack to make easy execute rolling updates and rollbacks.

![diagram6](https://github.com/systane/courses/blob/master/kubernetes/diagram6.png)

Imagine the following scenario, we create a yaml file with a kind **Deployment** of our app in version 1.0, and throw it to the apiserver. After the validation of our .yml, the kubernetes creates our Replication Controller with all pods required in the desired state and everything is work perfectly. Now imagine that our we need update the image of our app with some new features that were added. First of all, we need to update our yaml file with that image and push it apiserver. In the background Kubernetes will create another Replication Controller and as it adds one pod to this new Replication Controller, it also removes one pod in the old one and this repeats until our desired state is reached in the new Replication Controller.

![diagram7](https://github.com/systane/courses/blob/master/kubernetes/diagram7.png)

In the last scenario we had two Replication Controller, one for our version 1.0 of our app and another to our new version created to our rolling update. The first Replication Controller wasn't deleted. Kubernetes keeps it in a case we need to execute a rollback. So, if we need make a rollback, we just need to update our yaml file with the previous image of our app and throw it again to apiserver. Kubernetes will use the first Replication Controller to add one pod as it also removes one pod of the second Replication Controller. 

After all this theory, let's take a look at a yaml file of kind Deployment:

    apiVersion: v1
    kind: ReplicationController
    metadata:
    name: hello-rc
    spec:
    replicas: 10
    selector:
        app: hello-world
    template:
        metadata:
        labels:
            app: hello-world
        spec:
        containers:
        - name: hello-pod
            image: nigelpoulton/pluralsight-docker-ci:latest
            ports:
            -  containerPort: 8080

