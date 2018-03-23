#!/bin/sh

mfapi='mf-api'
mfservice='mf-service'
mfzk='mf-zk'
network='mf-net'

echo 'begin to clean'

docker container stop $mfapi
docker container stop $mfservice
docker container stop $mfzk

echo 'stop mf container all done'

docker container rm $mfapi
docker container rm $mfservice
docker container rm $mfzk
docker network rm $network

echo 'clean all done'
