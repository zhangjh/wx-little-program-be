#!/bin/bash

work_dir=$(pwd)
cd "${work_dir}" || exit 1

mvn clean package && nohup java -jar chatgpt-helper-start/target/*.jar &> ./log/app.log &
echo $?
