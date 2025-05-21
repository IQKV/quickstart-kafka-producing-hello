# 🚀 Hello, World! - Kafka producer app

Minimal Spring Boot based sample Kafka producer app.

## 🛠️ Installation

Pre-Requisites to run this example locally

- Setup git command line tool (https://help.github.com/articles/set-up-git)
- Clone source code to the local machine:

```bash
git clone https://github.com/IQKV/quickstart-kafka-producing-hello.git

cd quickstart-kafka-producing-hello
```

- Install Docker [https://docs.docker.com/get-docker/](https://docs.docker.com/get-docker/)
- Add new version of Docker Compose [https://docs.docker.com/compose/install/](https://docs.docker.com/compose/install/)
- Spin-up single instance of Kafka broker, ZooKeeper by running command:

```bash
docker compose -f compose.yaml up -d
```

### Running locally

This application is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built
using [Maven](https://spring.io/guides/gs/maven/). You can build a jar files and run it from the command line:

- Create jar packages:

```bash
./mvnw package
```

- Run **quickstart-kafka-producing-hello** app:

```bash
java -jar target/*.jar
```

You might also want to use Maven's `spring-boot:run` goal - applications run in an exploded form, as they do in your IDE:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=local -P dev
```

## Code conventions

The code follows [Google Code Conventions](https://google.github.io/styleguide/javaguide.html). Code
quality is measured by:

- [SonarQube](https://docs.sonarsource.com/)
- [PMD](https://pmd.github.io/)
- [CheckStyle](https://checkstyle.sourceforge.io/)
- [SpotBugs](https://spotbugs.github.io/)
- [Qulice](https://www.qulice.com/)

### Tests

This project contains a JUnit tests, Hamcrest matchers, Mockito test doubles, Wiremock stubs, etc. You can run the test suite using

```bash
./mvnw verify -P use-testcontainers
```

The minimum percentage of code coverage required for the workflow to pass is **80 %**.

> ### Versioning
>
> Project uses a three-segment [CalVer](https://calver.org/) scheme, with a short year in the major version slot, short month in the minor version slot, and micro/patch version in the third
> and final slot.
>
> ```
>  YY.MM.MICRO
> ```
>
> 1. **YY** - short year - 6, 16, 106
> 2. **MM** - short month - 1, 2 ... 11, 12
> 3. **MICRO** - "patch" segment
