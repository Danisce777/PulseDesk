FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY . .
RUN ./mvnw -DskipTests clean install
EXPOSE 8080
CMD java -Dserver.port=$PORT -jar target/*.jar