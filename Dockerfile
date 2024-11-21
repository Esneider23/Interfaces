# Fase 1: Imagen base para ejecutar la aplicación
FROM openjdk:21-jdk-alpine

# Copiar el archivo .jar desde la carpeta local al contenedor
COPY /target/puzzle-0.0.1-SNAPSHOT.jar puzzle-0.0.1-SNAPSHOT.jar

# Comando para ejecutar el archivo .jar
ENTRYPOINT [ "java", "-jar", "puzzle-0.0.1-SNAPSHOT.jar" ] 
