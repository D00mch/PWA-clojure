FROM openjdk:8-alpine

COPY target/uberjar/pwa.jar /pwa/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/pwa/app.jar"]
