#!/bin/bash

work_dir=$(pwd)
cd "${work_dir}" || exit 1

pid=$(ps -ef | grep java | grep start | awk '{print $2}')
if [[ "X${pid}" != "X" ]];then
  kill "${pid}"
fi

mvn clean package && nohup java -jar start/target/*.jar &> ./log/app.log &
echo $?
