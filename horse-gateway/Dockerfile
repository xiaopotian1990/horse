FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER zoulu669064@live.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /horse-gateway

WORKDIR /horse-gateway

EXPOSE 9999

ADD ./horse-gateway/target/horse-gateway.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar horse-gateway.jar
