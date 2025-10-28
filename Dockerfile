# Dockerfile to build container for unit testing

FROM debian:stable

Run apt-get update
RUN apt-get install -y git openjdk-21-jdk ant

WORKDIR /root

ADD . ./

ENTRYPOINT ant test
