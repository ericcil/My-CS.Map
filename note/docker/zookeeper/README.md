### zookeeper image

there I use COPY too.

I delete those things:

zookeeper/
    docs/
    dist-maven/
    zookeeper.jar.asc
    zookeeper.jar.md5
    zookeeper.jar.sha1

but just free up 8M.


What's more,I learn more about container.
I use 'start'  command in the first time at the end of Dockerfile
( ENTRYPOINT ["/usr/local/zookeeper/bin/zkServer.sh","start"] ).
It's perfect when I build the image and create the container.
But the container do not keep running  it always stop immedearly when i execute start command.
Finally i found it could be the cause by 'start' that i use at the end of Dockerfile.
It tend to run zookeeper with a daemon thread when use 'start',that can keep the container running.
