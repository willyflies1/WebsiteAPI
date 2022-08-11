FROM openjdk:16-jdk-alpine
RUN addgroup -S spring && adduser -S cloud-files -G spring
USER cloud-files:spring
EXPOSE 4001
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
#COPY logs .
ENTRYPOINT ["java","-jar","/app.jar"]