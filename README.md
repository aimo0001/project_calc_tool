# Alpha Solutions - Projektkalkulationsværktoej

Et simpelt webbaseret projektkalkulationsvaerktoej udviklet for Alpha Solutions.

Systemet bruges til at oprette projekter, opgaver og delopgaver. Timer registreres paa delopgaver, og systemet beregner den samlede tid for et projekt.

## Link til applikation

```text
[indsæt Azure link]
```

## Tekniske forudsaetninger

- Java 21
- Maven 3.9.14
- MySQL 8.0
- IntelliJ IDEA
- Git

## Teknologier

- Java 21
- Spring Boot 4.0.5
- Thymeleaf 3.1.3
- MySQL 8.0
- JDBC / JdbcTemplate
- H2 til integrationstest
- Maven
- GitHub Actions
- Azure App Service

## Sådan starter du applikationen lokalt

1. Klon repository:

```bash
git clone https://github.com/[dit-brugernavn]/project_calc_tool.git
```

2. Opret databasen i MySQL Workbench:

```sql
CREATE DATABASE project_calc;
```

3. Tjek databaseopsætningen i:

```text
src/main/resources/application-dev.properties
```

Standard lokal opsætning:

```properties
spring.datasource.url=jdbc:mysql://127.0.0.1:3307/project_calc
spring.datasource.username=root
spring.datasource.password=root1234
```

4. SQL scripts ligger i:

```text
src/main/resources/schema.sql
src/main/resources/data.sql
```

5. Start applikationen via IntelliJ eller kør:

```bash
mvn spring-boot:run
```

6. Aaben browseren og gå til:

```text
http://localhost:8080/projects
```

## Spring profiles

Projektet bruger Spring profiles til at skelne mellem udvikling, test og produktion.

| Profile | Brug | Database |
| --- | --- | --- |
| `dev` | Lokal udvikling | MySQL lokalt |
| `test` | Automatiserede tests | H2 in-memory |
| `prod` | Azure/produktion | Azure MySQL via environment variables |

Standard profile er `dev`.

Production profile aktiveres med:

```text
SPRING_PROFILES_ACTIVE=prod
```

## Funktioner

- Opret projekt
- Se projektliste
- Se projektdetaljer
- Rediger og slet projekt
- Tilføj opgaver til et projekt
- Rediger og slet opgaver
- Tilføj delopgaver til en opgave
- Rediger og slet delopgaver
- Registrer timer paa delopgaver
- Beregn samlet projekttid

## Arkitektur

Systemet følger en lagdelt MVC-struktur:

- **Controller** modtager HTTP-forespoergsler og returnerer Thymeleaf-sider
- **Service** indeholder forretningslogik, blandt andet beregning af samlet tid
- **Repository** håndterer SQL-forespørgsler via JdbcTemplate
- **Database** gemmer projekter, opgaver og delopgaver

Flowet i systemet er:

```text
Controller -> Service -> Repository -> Database
```

## Database

Databasen består af tre centrale tabeller:

- `project`
- `task`
- `subtask`

Relationerne er:

- Et projekt kan have mange opgaver
- En opgave kan have mange delopgaver
- Timer gemmes på delopgaver

SQL scripts til oprettelse af database og testdata ligger i `resources`-mappen.

## Kør tests

```bash
mvn test
```

Tests bruger H2 in-memory database via testprofilen.

Testkonfigurationen ligger i:

```text
src/test/resources/application-test.properties
```

## GitHub Actions CI/CD

Applikationen bygges og testes automatisk ved push til `master` via GitHub Actions.

Pipeline konfigurationen findes i:

```text
.github/workflows/master_projectcalc-aisha-app.yml
```

Workflowet bygger projektet med Maven og deployer JAR-filen til Azure Web App.

## Azure environment variables

Naar Azure MySQL er oprettet, skal disse variabler sættes i Azure Web App:

```text
SPRING_PROFILES_ACTIVE=prod
DB_URL=jdbc:mysql://[server-navn].mysql.database.azure.com:3306/project_calc?useSSL=true&requireSSL=true&serverTimezone=UTC
DB_USERNAME=[database-bruger]
DB_PASSWORD=[database-password]
SPRING_SQL_INIT_MODE=always
```

Efter databasen er oprettet første gang, kan `SPRING_SQL_INIT_MODE` ændres til:

```text
never
```

