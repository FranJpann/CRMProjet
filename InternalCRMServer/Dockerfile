FROM openjdk:21

WORKDIR /app

COPY ./build/libs/InternalCRMServer-0.0.1-SNAPSHOT.war /app/war/

CMD ["java", "-jar", "war/InternalCRMServer-0.0.1-SNAPSHOT.war"]