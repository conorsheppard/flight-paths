# ✈️ Flight Paths

![Coverage](./badges/jacoco.svg)

A command-line Java application that calculates all possible flight paths between two cities in the world of **Game of Thrones**, showing the total cost for each valid route (north-to-south only). Built with Java 24, Picocli, and Docker.

## 🧭 Overview

The application represents a simplified flight network as a **directed acyclic graph (DAG)**, where each city is a node and each flight (with an associated cost) is a directed edge going southward. Given two cities, the app finds all valid paths and displays the cost of each.

## 📦 Features

- 100% test coverage, analysed using Jacoco
- Accepts **city names** (e.g. `castle black`, `king's landing`) instead of indices.
- Displays **all valid southbound paths** with individual total costs.
- Enforces **north-to-south travel** rules only.
- Supports **Dockerized execution**.
- Includes **unit tests** for correctness.

## 🏰 Supported Cities

| City Index | Name            |
|------------|-----------------|
| 0          | Castle Black     |
| 1          | Winterfell       |
| 2          | Riverrun         |
| 3          | King's Landing   |

> Note: City names are case-insensitive and should be input as strings (e.g., `"Castle Black"` or `"castle black"`).

## 🚀 Running with Docker (Recommended)

To run from the command line using Docker:

```shell
./bin/list-flight-paths "Castle Black" "King's Landing"
```

### Running locally with Java & Maven

The recommended way to run this application via the containerised approach outlined above.  
However, if you're familiar with Maven and Java and would like to 
This is an alternative approach for experimentation purposes, not an alternative way to run the submission as it is not
included in the `bin/list-flight-paths` wrapper script.


First, build the project with Maven. You must have Maven installed for this to work.

```shell
make build
```

Then run the jar file
```shell
java -jar target/flight-paths-1.0.0.jar "Castle Black" "King's Landing"
```