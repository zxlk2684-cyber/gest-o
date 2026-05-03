# Stage 1: Build
FROM maven:3.9.4-eclipse-temurin-17 AS builder

WORKDIR /app

# Copiar arquivos de configuração do Maven
COPY pom.xml .

# Copiar código-fonte
COPY src ./src

# Compilar e empacotar
RUN mvn clean package -DskipTests -o 2>/dev/null || mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copiar JAR da etapa de build
COPY --from=builder /app/target/serviceflow-*.jar app.jar

# Expor porta
EXPOSE 8080

# Variáveis de ambiente padrão
ENV JAVA_OPTS="-Xmx512m -Xms256m"
ENV DB_HOST=db
ENV DB_PORT=5432
ENV DB_NAME=serviceflow
ENV DB_USER=serviceflow
ENV DB_PASSWORD=serviceflow123
ENV SERVER_PORT=8080

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=40s --retries=3 \
    CMD wget --quiet --tries=1 --spider http://localhost:8080/api/swagger-ui.html || exit 1

# Comando para iniciar a aplicação
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
