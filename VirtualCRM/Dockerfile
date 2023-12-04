FROM ubuntu:20.04

WORKDIR /app/VirtualCRM

RUN apt-get -y update && apt-get -y upgrade
RUN apt-get install -y thrift-compiler
RUN apt-get install -y openjdk-17-jdk

RUN apt-get -y install wget unzip
RUN mkdir /opt/gradle
RUN wget https://services.gradle.org/distributions/gradle-8.2-bin.zip && unzip -d /opt/gradle gradle-8.2-bin.zip
RUN rm gradle-8.2-bin.zip

COPY src ./src
COPY gradle ./gradle
COPY build.gradle settings.gradle ./

RUN /opt/gradle/gradle-8.2/bin/gradle clean build

CMD ["java", "-jar", "/app/VirtualCRM/build/libs/VirtualCRM-0.0.1-SNAPSHOT.war"]
