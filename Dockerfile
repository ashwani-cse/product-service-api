#base image for jdk17 from Eclipse temurin
FROM eclipse-temurin:17-jdk-jammy
#set the image's working directory
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve
COPY src ./src
CMD ["./mvnw", "spring-boot:run"]