FROM openjdk:8

ADD target/spring-boot-find-the-urls.jar find-the-urls.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "find-the-urls.jar"]