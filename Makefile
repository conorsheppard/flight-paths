SHELL := /bin/bash

default: run

clean:
	mvn clean

build: clean
	mvn package -DskipTests

docker-build:
	docker build . -t conorsheppard/flight-paths

run:
	# Usage: make run from="castle black" to="riverrun"
	docker run --rm --name flight-paths conorsheppard/flight-paths "$(from)" "$(to)"

.SILENT:
.PHONY: default clean build docker-build run
