FROM openjdk:8u121-alpine

COPY config-server/target/configServer-0.0.1-SNAPSHOT.jar /app/

RUN apk add --update \
    curl \
    && rm -rf /var/cache/apk/*

RUN apk update && apk add bash

COPY config-server/healthCheck.sh /healthCheck.sh

CMD ["java", "-jar", "/app/configServer-0.0.1-SNAPSHOT.jar"]