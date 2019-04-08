FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER zoulu669064@live.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /horse-eureka

WORKDIR /horse-eureka

EXPOSE 8761

ADD ./horse-eureka/target/horse-eureka.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar horse-eureka.jar
