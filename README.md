# auweb

Webes alkalmazás, amely Spring Boot és Vaadin keretrendszerekre épül.

## Technológiák
- **Java 17**
- **Spring Boot 3.x** (Spring MVC, Spring Data JPA)
- **Vaadin** (Frontend UI)
- **Jakarta EE**
- **PostgreSQL / Oracle** adatbázis támogatás
- **Gradle** build tool

## Projekt felépítése
- `src/main/java`: Backend logika, DTO-k, entitások és repository-k.
- `src/main/resources`: Konfigurációs fájlok (`application.properties`) és statikus erőforrások.
- `src/main/frontend`: Vaadin specifikus frontend összetevők.

## Clone, Build & Run

### 1. Clone
Egy üres folderben:
```bash
git clone https://github.com/balazsbalint/auweb.git
```
https://github.com/balazsbalint/auweb.git

### 2. Adatbázis beállítása
Az alkalmazás alapértelmezetten PostgreSQL-t használ. Ellenőrizd az 
**src/main/resources/application.properties** 
fájlt:
``` properties
spring.datasource.url=jdbc:postgresql://localhost:5432/au
spring.datasource.username=au
spring.datasource.password=[password]
```

### 3, Build & Run
``` bash
cd auweb
./gradlew clean build
./gradlew bootRun
```

Az alkalmazás elindítása után elérhető a http://localhost:8080 címen.