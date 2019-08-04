FROM maven:3.5-jdk-8

COPY src /app/kubernetes/src

COPY pom.xml /app/kubernetes

RUN mkdir /app/jar && \
mvn -f /app/pom.xml package && \
mv /app/target/kubernetes-0.0.1-SNAPSHOT.jar /app/jar/kubernetes.jar && \
rm -rf /app/kubernetes

EXPOSE 8080/TCP

CMD ["java", "-Dserver.port=8080" ,"-jar", "/app/jar/api.jar"]