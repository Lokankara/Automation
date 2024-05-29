# Luma Project
[![Luma Tests Run](https://github.com/Lokankara/Automation/actions/workflows/test-run.yml/badge.svg?branch=master)](https://github.com/Lokankara/Automation/actions/workflows/test-run.yml)

[Allure Report](https://lokankara.github.io/Automation)

### Maven commands

Validate the project is correct and all necessary information is available

`mvn validate`

Compile the source code of the project

`mvn compile`

Run unit tests from Command line

`mvn test`

Take the compiled code and package it in its distributable format

`mvn package`

Run integration tests from Command line

`mvn verify`

Build the project with Maven Tool

`mvn -B package --file pom.xml`

Clean, install, and generate report

`mvn clean install site -P test`

Clean, install with details

`mvn clean install -X`

Clean, install skip Test

`mvn clean install -DskipTests`

Allure Report

`mvn jacoco:`