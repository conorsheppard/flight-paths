SHELL := /bin/bash

default: run

clean:
	mvn clean

test:
	mvn test

build: clean
	mvn package -DskipTests

docker-build:
	docker build . -t dockeropsengineer/flight-paths

run:
	# Usage: make run from="castle black" to="riverrun"
	docker run --rm --name flight-paths dockeropsengineer/flight-paths "$(from)" "$(to)"

java-run:
	# Usage: make run from="castle black" to="riverrun"
	java -jar target/flight-paths-1.0.0.jar "$(from)" "$(to)"

.SILENT:
.PHONY: default clean build docker-build run
