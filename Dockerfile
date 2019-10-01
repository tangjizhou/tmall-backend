FROM openjdk:11.0.3
VOLUME /tmp
ADD target/tmall-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
