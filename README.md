# Metaindustria API

API REST Spring Boot do grupo Metaindustria (entrega final, Sprint 4), consumida
pelo app mobile `sprint-mobile`. Derivada do `securitymonitor-api` (Sprint 2) e
simplificada: camadas (controller/service/repository/model/dto), CRUD, banco H2 em
arquivo (sem Docker/Postgres) e CORS liberado.

**Problema.** Em ambientes industriais, saber quem esta habilitado a operar qual maquina
e acompanhar as cameras de seguranca costuma ser feito em planilhas e papel. Esta API
centraliza esses dados.

**Entidade principal:** `Funcionario`, com `Certificado` (habilitacao para uma maquina)
e `Camera` como entidades de apoio.

## Integrantes

| Nome | RM |
|---|---|
| [PREENCHER - nome completo] | [PREENCHER - RM] |
| [PREENCHER - nome completo] | [PREENCHER - RM] |
| [PREENCHER - nome completo] | [PREENCHER - RM] |

## Links

- **Backend (este repositorio):** https://github.com/lazuli-fiap/metaindustria-api
- **Frontend (app Expo):** https://github.com/EmilyPereiraRibeiroAcad/sprint-mobile
  (o README do frontend tem o passo a passo completo de integracao)
- **Video de demonstracao (YouTube, nao listado):** [PREENCHER - link do video]

## Stack e pre-requisitos

- Java 17 (JDK), Spring Boot 3.4.5
- Spring Web + Spring Data JPA
- H2 (arquivo local, persiste entre reinicios)
- Maven: nao precisa instalar, o repositorio traz o wrapper (`mvnw`)
- Para rodar o app junto: Node.js 20.19+ e Expo (ver README do frontend)

## Como rodar

```bash
git clone https://github.com/lazuli-fiap/metaindustria-api.git
cd metaindustria-api
./mvnw spring-boot:run
```

No Windows: `mvnw.cmd spring-boot:run`.

A API sobe em `http://localhost:8080`, sem prefixo `/api`. Os dados ficam em
`./data/metaindustriadb.mv.db` (arquivo local, ignorado pelo git).

Console do H2 (inspecionar o banco pelo navegador): `http://localhost:8080/h2-console`
— JDBC URL `jdbc:h2:file:./data/metaindustriadb`, usuario `sa`, sem senha.

## CORS

Cada controller tem `@CrossOrigin(origins = "*")`, liberando qualquer
origem — suficiente para desenvolvimento com o Expo (web, emulador ou
celular fisico). Se for hospedar em producao, trocar por uma lista de
origens especificas.

## Endpoints

### Funcionarios

| Metodo | Rota | Descricao |
|---|---|---|
| GET | `/funcionarios` | Lista todos |
| GET | `/funcionarios/{id}` | Busca por id |
| POST | `/funcionarios` | Cria (`{ nome, matricula, cargo, setor }`) |
| PUT | `/funcionarios/{id}` | Atualiza |
| DELETE | `/funcionarios/{id}` | Remove |

### Certificados

| Metodo | Rota | Descricao |
|---|---|---|
| GET | `/certificados` | Lista todos |
| GET | `/certificados/{id}` | Busca por id |
| GET | `/certificados/funcionario/{funcionarioId}` | Certificados de um funcionario |
| POST | `/certificados` | Cria (`{ maquina, descricao, dataEmissao, dataValidade, funcionarioId }`) |
| PUT | `/certificados/{id}` | Atualiza |
| DELETE | `/certificados/{id}` | Remove |

### Cameras

| Metodo | Rota | Descricao |
|---|---|---|
| GET | `/cameras` | Lista todas |
| GET | `/cameras/{id}` | Busca por id |
| POST | `/cameras` | Cria (`{ nomeExibicao, uri }`) |
| PUT | `/cameras/{id}` | Atualiza |
| DELETE | `/cameras/{id}` | Remove |

## Testando a integracao com o app

1. Suba a API (`./mvnw spring-boot:run`) e confirme `GET http://localhost:8080/funcionarios`
   no navegador ou Postman/Insomnia (deve retornar `[]` na primeira vez).
2. No `sprint-mobile`, rode `npx expo start` e abra a tela de Funcionarios —
   a lista deve carregar do backend (vazia no inicio).
3. Cadastre um funcionario pelo app e confirme que ele aparece tanto na
   lista do app quanto em `GET /funcionarios`.
4. Derrube a API e reabra a tela no app: deve aparecer o estado de erro
   ("nao foi possivel conectar ao servidor"), sem o app quebrar.
