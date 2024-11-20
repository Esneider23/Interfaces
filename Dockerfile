# Usa una imagen base con OpenJDK
FROM openjdk:21-jdk-slim AS build

# Instalar Maven
RUN apt-get update && apt-get install -y maven

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo pom.xml y el código fuente
COPY pom.xml . 
COPY src ./src

# Ejecutar Maven para compilar el proyecto
RUN mvn clean install -DskipTests

# Usar una imagen base para ejecutar el contenedor de la aplicación
FROM openjdk:21-jdk-slim

# Establecer el directorio de trabajo en el contenedor de ejecución
WORKDIR /app

# Copiar el archivo .jar desde la fase de construcción
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto en el que la aplicación escucha
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
