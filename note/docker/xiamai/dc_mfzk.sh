#!/bin/bash

# $1 zookeeper容器名
# $2 连接的网络名
# $3 zookeeper的ip

zkname='mf-zk'
network='www'
zkip='161.12.0.2'

if [ -n "$1" ];
then
  zkname=$1
  network=$2
  zkip=$3
fi


zkcount=`docker container ls --all | grep $zkname | wc -l`
if(( $zkcount==0 ))
then
  docker create --name $zkname --network $network --ip $zkip my-zookeeper:latest
fi

zkpscount=`docker container ls | grep $zkname | wc -l`
if(( $zkpscount==0 ))
then
  echo 'start zk....'
  docker container start $zkname
fi

echo 'zk started...'

