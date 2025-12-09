# TerminalChess

Simple terminal-based chess game written in Kotlin.

## Quick Start

Requirements: Java 25 (Temurin 25 recommended). Kotlin is not required at runtime.

Option A — Run with Gradle Wrapper (recommended)
```
# Using the project wrapper (no local Gradle needed)
./gradlew run

# Or build a fat JAR and run with Java 25
./gradlew shadowJar
java -jar build/libs/terminalchess-all.jar
```

Option B — Run with Docker (zero local setup; uses JDK/JRE 25)
```
docker build -t terminalchess .
docker run -it terminalchess
```

Option B2 — Run the published image from GitHub Container Registry (GHCR)
- Once the image is published, you can pull and run directly without building locally:
```
docker run -it ghcr.io/dctabansi/terminalchess:latest
```

Option C — Run from IntelliJ IDEA
- File → New → Project from Existing Sources… → choose this folder → import as Gradle project.
- Run the `Application` configuration (main class: `dev.tabansi.chess.MainKt`).

## Notes
- Compatibility: The build targets Java 25 (toolchain + bytecode). Ensure your runtime is Java 25+.
- Fat JAR: The `shadowJar` task produces `build/libs/terminalchess-all.jar`, which includes Kotlin stdlib for easy `java -jar` runs.
- CI: GitHub Actions workflow builds on Windows, Linux, and macOS (JDK 25) using the Gradle wrapper and uploads the runnable JAR as an artifact.

## Releases
- On tag push (e.g., `v0.1.0`), CI builds and attaches the fat JAR to the GitHub Release.

## License
MIT License © 2025 Tabansi — see [LICENSE](LICENSE) for full text.
