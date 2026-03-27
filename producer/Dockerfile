# producer/Dockerfile (igual para consumer)
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copiar solo pom para cachear dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el código fuente
COPY src ./src

# Construir el jar
RUN mvn clean package -DskipTests

# Imagen final
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copiar jar construido
COPY --from=build /app/target/*.jar app.jar

# Opcional: copiar src para live reload / recompilación incremental en dev
COPY src ./src

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]