# Contributing til Alpha Solutions

Denne guide beskriver, hvad et nyt teammedlem skal vide for at kunne bidrage til koden.

## Projektstruktur

```text
src/
main/
java/com/example/projectcalctool/

controller/    ← HTTP-forespørgsler og routing

model/         ← Domæneklasser (Project, Task, SubTask)

repository/    ← Databaseadgang via JDBC/JdbcTemplate

service/       ← Forretningslogik og beregning af timer

resources/

static/        ← CSS og statiske filer

templates/     ← Thymeleaf HTML-sider

schema.sql     ← Databasestruktur

data.sql       ← Testdata

application.properties      ← Fælles konfiguration

application-dev.properties  ← Lokal MySQL

application-prod.properties ← Azure MySQL via environment variables

test/
java/com/example/projectcalctool/

service/       ← Integrationstest med H2

resources/

application-test.properties ← H2 testdatabase
```

## Arkitektur

Systemet følger en lagdelt MVC-arkitektur:

- **Controller** modtager HTTP-forespørgsler og kalder service-laget
- **Service** indeholder forretningslogik og beregner samlet projekttid
- **Repository** håndterer SQL-forespørgsler via JdbcTemplate
- **Database** gemmer Project, Task og SubTask

Flowet er:

```text
Controller → Service → Repository → Database
```

Alle centrale klasser bruger konstruktør-injektion.

## Spring profiles

Projektet bruger disse profiles:

- `dev` til lokal udvikling med MySQL
- `test` til automatiserede tests med H2
- `prod` til Azure deployment med Azure MySQL

Standard profile er `dev`.

## Branch-strategi

- `master` bruges som hovedbranch
- Opret en feature-branch for større nye funktioner
- Lav commits i små steps
- Kør tests før push
- GitHub Actions kører automatisk ved push til `master`

Eksempel:

```bash
git checkout -b feature/add-validation
git add .
git commit -m "Add validation"
git push
```

## Tilføj ny funktionalitet

1. Opret eller opdater model-klasse i `model/`
2. Opret eller opdater repository-metode i `repository/`
3. Opret eller opdater service-metode i `service/`
4. Opret eller opdater controller-metode i `controller/`
5. Opret eller opdater Thymeleaf-side i `resources/templates/`
6. Opdater SQL scripts hvis databasen ændres
7. Skriv eller opdater tests

## Kør applikationen lokalt

Se README.md for opsætningsinstruktioner.

## Kør tests

```bash
mvn test
```

Tests bruger H2 in-memory database via `@ActiveProfiles("test")`.

Det betyder, at testene kan køre uden MySQL-forbindelse.

## Kodestil

- Hold controller-metoder simple
- SQL skal ligge i repository-laget
- Forretningslogik skal ligge i service-laget
- Brug sigende navne på metoder og variable
- Skriv korte kommentarer hvis noget ikke er selvforklarende
- Undgå at blande HTML, SQL og forretningslogik sammen

## Hvis databasen ændres

Hvis der laves ændringer i databasen, skal disse filer og dele tjekkes:

- `schema.sql`
- `data.sql`
- repository-klasser
- service-klasser
- tests
- ER-diagram i rapporten

## Deployment

Deployment sker gennem GitHub Actions.

Workflowet ligger her:

```text
.github/workflows/master_projectcalc-aisha-app.yml
```

Production databaseoplysninger skal ikke skrives direkte i koden. De skal sættes som environment variables i Azure.

