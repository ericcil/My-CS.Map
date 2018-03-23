#!/bin/sh


# $1 api容器名
# $2 连接的网络名
# $3 api的ip
# $4 暴露的宿主机端口
# $5 日志储存地址


basedir=`pwd`

mfname='mf-api'
network='www'
mfip='161.12.0.4'
mflogdir=$basedir'/weblogs/servicelogs'
hostport='8082'
warpath=$basedir'/webapps/miaofen-api'


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
  docker create --name $mfname --network $network --ip $mfip --publish $hostport:8080 -v $mflogdir:/usr/local/tomcat/logs  my-tomcat8:latest
fi

docker container stop $mfname

docker cp $warpath mf-api:/usr/local/tomcat/webapps

echo 'start '$mfname'...'
docker container start $mfname

echo 'started '$mfname'...'
