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

test-coverage:
	mvn clean org.jacoco:jacoco-maven-plugin:0.8.13:prepare-agent verify org.jacoco:jacoco-maven-plugin:0.8.13:report

check-coverage:
	open -a Google\ Chrome target/jacoco-report/index.html

coverage-badge-gen:
	python3 -m jacoco_badge_generator -j target/jacoco-report/jacoco.csv

test-suite: test-coverage check-coverage coverage-badge-gen

.SILENT:
.PHONY: default clean build docker-build run
