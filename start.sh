#!/bin/bash

work_dir=$(pwd)
cd "${work_dir}" || exit 1

mvn clean package

if [ "$?" -eq 0 ];then
  pid=$(ps -ef | grep java | grep start | awk '{print $2}')
  if [[ "X${pid}" != "X" ]];then
    kill "${pid}"
  fi

  nohup java -jar start/target/*.jar &> ./log/app.log &
fi

echo $?
