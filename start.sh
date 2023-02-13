#!/bin/bash

work_dir=$(pwd)
cd "${work_dir}" || exit 1

pid=$(ps -ef | grep java | grep chatgpt-helper | awk '{print $2}')
if [[ "${pid}" != "" ]];then
  kill "${pid}"
fi

mvn clean package && nohup java -jar chatgpt-helper-start/target/*.jar &> ./log/app.log &
echo $?
