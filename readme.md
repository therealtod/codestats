# Codestats

A tiny Spring application that allows to get statistics about github repositories

Currently, the only functionality supported by this application is fetching the percentage of each language represented in an arbitrary GitHub organisation public repositories.

The default organisation is ProductBoard


## API Reference

#### Get percentage by language

```http
  GET /codestats/api/v1/languagepercentages
```

The Server response looks like:

{
"CSS": 0.0044,
"HTML": 0.0049,
"JavaScript": 0.2724,
"Ruby": 0.0266,
"TypeScript": 0.6892
}

## Spring boot configuration

It's possible to edit the following propeties in order to modify the behaviour of the application:

```bash
  codestats.language.percentages.precision.decimals
```

Determines how many decimals will be displayed the values in the endpoint. Languages with a share lower than 1/000 of 
this value will be filtered out

```bash
  codestats.target.orgid
```

determines the github organization whose language percentages over public repositories will be computed. Defaults to 
productboard





## Environment Variables

To run this project, you will need to add the following environment variables to your .env file

`GITHUB_TOKEN`

The github token that will be used in REST API calls

`MARIADB_USER`

A username for accessing the underlying mariadb database
`MARIADB_PASSWORD`

A password for accessing the underlying mariadb database
`MARIADB_ROOT_PASSWORD`

The password for the root user of the underlying mariadb database

## Running Tests

To run tests, run the following command

```bash
  ./gradlew test
```


## Run Locally

Clone the project

```bash
  git clone https://github.com/therealtod/codestats.git
```

Go to the project directory

```bash
  cd codestats
```

### Running via docker-compose (recommended. Remember to have your .env file set in the project root folder)

```bash
  docker-compose up
```

### Running without docker compose

Make sure you have a mariadb instance running somewhere.

You will need JDK17 and gradle 7 to run the app.

modify spring config (for example, through application.properties) setting these property values according to your setup:

```bash
spring.datasource.url=jdbc:mariadb://<YOUR MARIADB INSTANCE HERE>/codestats
spring.datasource.username=${MARIADB_USER}
spring.datasource.password=${MARIADB_PASSWORD}
```

Finally, start the application

```bash
  ./gradlew bootrun
```


## Authors

- [@therealtod](https://github.com/therealtod)
