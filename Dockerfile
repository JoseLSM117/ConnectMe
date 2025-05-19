# ================================================
# FRONTEND (se mantiene igual)
# ================================================
FROM node:23-slim AS base
WORKDIR /usr/local/app

FROM base AS client-base
COPY ConnectMeFront/package.json ConnectMeFront/package-lock.json ./
RUN npm install
COPY ConnectMeFront/eslint.config.js ConnectMeFront/index.html ConnectMeFront/README.md ConnectMeFront/tsconfig.app.json ConnectMeFront/tsconfig.json ConnectMeFront/tsconfig.node.json ConnectMeFront/vite.config.ts ./
COPY ConnectMeFront/public ./public
COPY ConnectMeFront/src ./src
COPY ConnectMeFront/tests ./tests

FROM client-base AS client-dev
CMD ["npm", "run", "dev"]

FROM client-base AS client-test
RUN npm run test > result.log
RUN cat result.log

FROM client-base AS client-build
RUN npm run build

# ================================================
# BACKEND - Fase de construcción y desarrollo
# ================================================
FROM eclipse-temurin:17-jdk-jammy AS backend-dev
WORKDIR /app

# 1. Instalación optimizada de dependencias
RUN apt-get update && \
  apt-get install -y --no-install-recommends \
  maven \
  git \
  curl \
  && \
  rm -rf /var/lib/apt/lists/*

# 2. Cache estratégico de dependencias Maven
COPY connectme/pom.xml .
RUN mvn dependency:go-offline -B

# 3. Copia del código fuente (se montará como volumen en desarrollo)
VOLUME /tmp
COPY connectme/src ./src

# 4. Configuración para desarrollo con hot reload
EXPOSE 8080 5005
CMD ["mvn", "spring-boot:run", "-Dspring-boot.run.profiles=dev", "-Dspring-boot.run.jvmArguments=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005","-Dspring-boot.run.fork=false", "-Dspring.devtools.restart.enabled=true", "-Dspring.devtools.livereload.enabled=true", "-Dspring.devtools.remote.secret=mysecret"]  # Crucial para hot reload

# ================================================
# BACKEND - Fase de producción (opcional)
# ================================================
FROM eclipse-temurin:21-jre-jammy AS backend-prod
WORKDIR /app
COPY --from=backend-dev /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

# ================================================
# FRONTEND PRODUCTION (Nginx)
# ================================================
#sFROM nginx:1.27-alpine-slim AS frontend-prod
#COPY --from=client-build /usr/local/app/dist /usr/share/nginx/html
#COPY ConnectMeFront/nginx.conf /etc/nginx/conf.d/default.conf
#EXPOSE 80