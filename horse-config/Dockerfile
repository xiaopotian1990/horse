FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER zoulu669064@live.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /horse-config

WORKDIR /horse-config

EXPOSE 8888

ADD ./horse-config/target/horse-config.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar horse-config.jar
