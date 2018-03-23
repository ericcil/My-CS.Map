#!/bin/sh

# $1 配置的网络名
# $2 网段

netname='www'
subnet='161.12.0.0/16'

if [ -n "$1" ];
then
  netname=$1
  subnet=$2
fi

echo $netname $subnet

dockerps=`ps -ef | grep docker | grep -v 'grep' | wc -l`
if(( $dockerps==0 ))
then
  echo 'docker run...'
  service docker start
  echo 'docker started'
fi 


netcount=`docker network ls | grep ${netname} | wc -l`
if(( $netcount==0 ))
then
  echo 'create network '${netname}
  docker network create -d bridge --subnet ${subnet} ${netname}
else
  echo 'network already exists'
fi




