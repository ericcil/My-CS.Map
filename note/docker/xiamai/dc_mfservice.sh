#!/bin/bash


# $1 service容器名
# $2 连接的网络名
# $3 service的ip
# $4 暴露的宿主机端口
# $5 日志储存地址

basedir=`pwd`

mfname='mf-service'
network='www'
mfip='161.12.0.3'
mflogdir=$basedir'/weblogs/servicelogs'
hostport='8081'
warpath=$basedir'/webapps/miaofen-service'

if [ -n "$1" ];
then 
  mfname=$1
  network=$2
  mfip=$3
  hostport=$4
  mflogdir=$5
  warpath=$6
fi

rm -rf $mflogdir
mkdir $mflogdir

mfcount=`docker container ls --all | grep $mfname | wc -l`
if(( $mfcount==0 ))
then
  echo 'create '$mfname'...'
  docker create --name $mfname --network $network --ip $mfip -p $hostport:8080 -v $mflogdir:/usr/local/tomcat/logs my-tomcat8:latest
  docker cp ./MSFONT.TTF  mf-service:/
  docker cp ./SIMYOU.TTF  mf-service:/

fi


docker container stop $mfname

docker cp $warpath mf-service:/usr/local/tomcat/webapps


echo 'start '$mfname'...' 
docker container start $mfname

echo 'started '$mfname'...'

