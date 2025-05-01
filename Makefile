SHELL := /bin/bash

default: run

clean:
	mvn clean

build: clean
	mvn package -DskipTests

docker-build:
	docker build . -t conorsheppard/flight-paths

run:
	#docker run --rm --name flight-paths flight-paths $(from) $(to)
	docker run --rm --name flight-paths flight-paths "castle black" "riverrun"

.SILENT:
.PHONY: default run
