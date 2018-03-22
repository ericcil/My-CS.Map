FROM my-jre8:latest

COPY tomcat /usr/local/tomcat

ENV TOMCAT_HOME /usr/local/tomcat
ENV PATH ${PATH}:${TOMCAT_HOME}/bin

ENTRYPOINT ["/usr/local/tomcat/bin/catalina.sh","run"]
