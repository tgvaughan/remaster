# Dockerfile to build container for unit testing

FROM openjdk:11

RUN apt-get update && apt-get install -y git ant

WORKDIR /root

ADD . ./

ENTRYPOINT ant test
