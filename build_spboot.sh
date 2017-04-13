#!/bin/bash

set -m

war=/usr/local/war/spboot/ROOT.war
path=/usr/local/apache-tomcat-7.0.59
war_path=$path/webapps
bin_path=$path/bin

pid () {
    echo `ps -ef | grep $bin_path | grep -v grep | awk '{print $2}'`
}

update () {
    echo "backup"
    mv $war_path/ROOT.war{,.$(date +%F_%T)}
    echo "update"
    rm -rf $war_path/ROOT
    mv $war $war_path/ROOT.war
    $bin_path/startup.sh
}

# shutdown tomcat
tomcat_pid=$(pid)
echo $tomcat_pid
if [ -z $tomcat_pid ];then
    echo -e "\033[31m tomcat is stoped \033[0m"
else
    echo "tomcat pid is $tomcat_pid"
    echo "shutdown tomcat"
    kill -9 $tomcat_pid
    echo -e "\033[31m tomcat is stoped \033[0m"
fi

# start update

echo -e "\033[31m start update \033[0m"
update
