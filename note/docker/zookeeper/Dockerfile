FROM my-jre8:latest

ARG ZK_DIR=/usr/local/zookeeper
#ENV ZOO_CONF_DIR=${ZK_DIR}/conf


COPY zookeeper ${ZK_DIR}

ENV ZK_HOME ${ZK_DIR} \
    PATH ${PATH}:${ZK_HOME}/bin
    #ZOOCFGDIR=$ZOO_CONF_DIR

ENTRYPOINT ["/usr/local/zookeeper/bin/zkServer.sh","start-foreground"]
