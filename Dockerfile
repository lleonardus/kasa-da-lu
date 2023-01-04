FROM maven as MAVEN_BUILD
COPY ./ ./
RUN mvn clean package

FROM openjdk
COPY --from=MAVEN_BUILD /target/kasa-da-lu-0.0.1-SNAPSHOT.jar /app.jar
CMD ["java", "-jar", "/app.jar"]