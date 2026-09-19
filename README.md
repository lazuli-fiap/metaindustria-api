# Metaindustria API

Backend do grupo Metaindustria para a Sprint 3 (integracao com o app mobile
`sprint-mobile`). Reescrito a partir do `securitymonitor-api` (Sprint 2),
mas simplificado para seguir o padrao pedido no enunciado: camadas simples,
CRUD basico, banco H2 em arquivo (sem Docker/Postgres).

> O `securitymonitor-api` deixou de estar acessivel para o grupo nesse
> formato, por isso este repositorio novo foi criado como base da Sprint 3.

## Stack

- Java 17, Spring Boot 3.4.5
- Spring Web + Spring Data JPA
- H2 (arquivo local, persiste entre reinicios)
- Maven (wrapper incluso, nao precisa instalar Maven)

## Como rodar

Pre-requisito: JDK 17.

```bash
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
