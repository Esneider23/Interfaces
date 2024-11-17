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


RUN apt-get update && apt-get install -y netcat-openbsd


# Exponer el puerto en el que la aplicación escucha
EXPOSE 8080

# Comando para esperar a que MySQL esté listo y luego iniciar la aplicación
CMD bash -c " \
  until nc -z mysql-db 3306; do \
    echo 'Esperando a que MySQL esté listo...'; \
    sleep 2; \
  done; \
  echo 'MySQL está listo, iniciando la aplicación...'; \
  java -jar app.jar"
