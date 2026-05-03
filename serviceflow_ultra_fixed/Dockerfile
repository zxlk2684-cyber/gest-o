# Multi-stage build para otimizar tamanho da imagem final
FROM maven:3.9.4-eclipse-temurin-17 as builder

# Definir diretório de trabalho
WORKDIR /app

# Copiar apenas o pom.xml primeiro (melhor aproveitamento de cache)
COPY pom.xml /app/pom.xml

# Copiar todo o conteúdo do src
COPY . /app/

# Build da aplicação
RUN mvn clean package -DskipTests -q

# Etapa final - imagem de runtime
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copiar o JAR compilado da etapa anterior
COPY --from=builder /app/target/serviceflow-*.jar /app/app.jar

# Expor porta
EXPOSE 8080

# Variáveis de ambiente
ENV JAVA_OPTS="-Xmx512m -Xms256m"
ENV SERVER_PORT=8080

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
    CMD wget --quiet --tries=1 --spider http://localhost:8080/api/swagger-ui.html || exit 1

# Comando de inicialização
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app/app.jar"]
