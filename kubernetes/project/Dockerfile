FROM maven:3.5-jdk-8

COPY src /app/kubernetes/src

COPY pom.xml /app/kubernetes

RUN mkdir /app/jar && \
mvn -f /app/kubernetes/pom.xml package -DskipTests && \
mv /app/kubernetes/target/kubernetes-0.0.1-SNAPSHOT.jar /app/jar/kubernetes.jar && \
rm -rf /app/kubernetes

EXPOSE 8081/TCP

CMD ["java", "-Dserver.port=8081" ,"-jar", "/app/jar/kubernetes.jar"]