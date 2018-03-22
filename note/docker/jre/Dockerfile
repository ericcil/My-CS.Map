FROM alpine:latest

RUN echo -e "https://mirrors.aliyun.com/alpine/v3.6/main\nhttps://mirrors.aliyun.com/alpine/v3.6/community" > /etc/apk/repositories

#COPY repositories /etc/apk/repositories
RUN apk --update add bash openjdk8-jre-base && \
	rm -rf /var/cache/apk/*

ENV JAVA_HOME /usr/lib/jvm/default-jvm
ENV PATH ${PATH}:${JAVA_HOME}/bin

