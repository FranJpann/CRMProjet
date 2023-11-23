FROM ubuntu:20.04

WORKDIR /app

RUN apt -y update && apt -y upgrade
RUN apt-get install -y thrift-compiler
RUN apt-get install -y default-jdk

COPY gradle src ./
COPY gradlew build.gradle settings.gradle ./

RUN ./gradlew clean build

CMD ["java", "-jar", "/app/libs/InternalCRMServer-0.0.1-SNAPSHOT.war"]
