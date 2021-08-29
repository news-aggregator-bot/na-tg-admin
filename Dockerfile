FROM adoptopenjdk/openjdk11

MAINTAINER Vladyslav Yemelianov <emelyanov.vladyslav@gmail.com>

ADD target/na-tg-admin.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/na-tg-admin.jar"]

EXPOSE 8080