FROM azul/zulu-openjdk-alpine:18.0.1
ENV JAR_NAME backend.jar
COPY build/libs/$JAR_NAME $JAR_NAME
EXPOSE 6000
ENTRYPOINT java -jar $JAR_NAME $JAR_NAME