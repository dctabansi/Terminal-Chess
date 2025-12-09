# Multi-stage build to produce a slim runtime image using Java 25 and Gradle wrapper

# --- Build stage: use JDK 25 and the project's Gradle wrapper
FROM eclipse-temurin:25-jdk AS build
WORKDIR /workspace

# Copy wrapper and build files first to leverage Docker cache
COPY gradlew gradlew.bat ./
COPY gradle/wrapper ./gradle/wrapper
COPY settings.gradle.kts build.gradle.kts ./

# Verify wrapper and warm up dependencies
RUN set -e; \
    if [ -f ./gradlew ]; then \
      chmod +x ./gradlew && ./gradlew --version; \
      ./gradlew --no-daemon dependencies || true; \
    else \
      echo "Gradle wrapper not found. Please commit gradlew and gradle/wrapper." && exit 1; \
    fi

# Copy sources and build fat JAR
COPY src ./src
RUN ./gradlew --no-daemon shadowJar

# --- Runtime stage: minimal JRE 25 image
FROM eclipse-temurin:25-jre
WORKDIR /app

# Copy the fat JAR from the build stage
COPY --from=build /workspace/build/libs/terminalchess-all.jar /app/terminalchess-all.jar

# Default command: run the game (interactive)
ENTRYPOINT ["java","-jar","/app/terminalchess-all.jar"]
