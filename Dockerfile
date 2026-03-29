FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY . .
RUN ./mvnw -DskipTests clean install
EXPOSE 10000
CMD java -Dserver.port=$PORT -jar target/*.jar