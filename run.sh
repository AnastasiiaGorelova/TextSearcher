#!/bin/bash
# This script will install dependencies then build TextSearcher application

npm install  @material-ui/core
npm install @material-ui/icons

./gradlew build
java -jar ./build/libs/TextSearcher-0.0.1-SNAPSHOT.jar
