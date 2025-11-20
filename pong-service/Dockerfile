# ===============================
# 1. STAGE: BUILD (Maven + JDK)
# ===============================
# Hivatalos Maven image a buildeléshez. Ez nagy, de nem baj, mert nem kerül bele a végeredménybe.
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

# Először csak a pom.xml-t másoljuk, hogy a dependency letöltést cache-elje a Docker
COPY pom.xml .
# Dependency-k letöltése (ez a lépés sokáig tarthat elsőre, de utána gyors lesz)
RUN mvn dependency:go-offline

# Forráskód másolása és buildelés
COPY src ./src
RUN mvn package -DskipTests

# ===============================
# 2. STAGE: RUNTIME (Csak JRE - Kicsi és Biztonságos)
# ===============================
# Itt már egy sokkal kisebb image-et használunk, amiben csak Java futtató környezet van.
# Az eclipse-temurin JRE verziója kicsi és stabil.
FROM eclipse-temurin:17-jre-jammy

# SECURITY: Nem-root felhasználó létrehozása
# Best practice: soha ne futtass konténert rootként!
RUN groupadd -r spring && useradd -r -g spring spring

WORKDIR /app

# Csak a kész .jar fájlt másoljuk át az előző (builder) stage-ből
COPY --from=builder /app/target/*.jar app.jar

# Jogosultságok átadása az új felhasználónak
RUN chown spring:spring app.jar

# Váltunk a biztonságos felhasználóra
USER spring

# Ez csak dokumentáció, a Kubernetes fogja kezelni a portokat
EXPOSE 8080

# Indítás
ENTRYPOINT ["java", "-jar", "app.jar"]