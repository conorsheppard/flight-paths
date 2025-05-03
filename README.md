# ✈️ Flight Paths

![Coverage](./badges/jacoco.svg)

A command-line Java application that calculates all possible flight paths between two cities in the world of **Game of Thrones**, showing the total cost for each valid route (north-to-south only).

## 🧭 Overview

The application represents a simplified flight network as a **directed acyclic graph (DAG)**, where each city is a node and each flight (with an associated cost) is a directed edge going southward. Given two cities, the app finds all valid paths and displays the cost of each.

## 📦 Features

- Includes **unit tests** with 100% test coverage, analysed using [Jacoco](https://github.com/jacoco/jacoco).
- Built with Java 24, [Picocli](https://picocli.info/), and Docker.
- Displays **all valid southbound paths** with individual total costs.
- Enforces **north-to-south travel** rules only.
- Uses standard streams and exit codes. 
- Application accepts case-insensitive city names input as strings (e.g., `"Castle Black"` or `"castle black"`).

## 🚀 Running with Docker (Recommended)

**Prerequisite:** Install _[Docker Desktop](https://docs.docker.com/desktop/setup/install/mac-install)_ 

> Note: On a cold start, the `list-flight-paths` script will pull the Docker image from Docker Hub, this may take a few seconds depending on your internet speed - there will be no output while this happens as I've redirected the output to align with the assignment guidelines.

To run from the command line using Docker:

```shell
./bin/list-flight-paths "Castle Black" "King's Landing"
```

<details>
<summary>Running locally with Java & Maven</summary>

The preferred way to run this application is using the containerized approach described above.
That said, if you're comfortable with Maven and Java, you’re welcome to run it directly.
Note, however, that this method is not considered an official alternative for submission, as it is not supported by the bin/list-flight-paths wrapper script.

First, build the project with Maven. You must have Maven installed for this to work.

```shell
make build
```

Then run the jar file
```shell
java -jar target/flight-paths-1.0.0.jar "Castle Black" "King's Landing"
```
</details>
