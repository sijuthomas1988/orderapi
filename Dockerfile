# The base image
FROM ubuntu:latest

MAINTAINER Sijumon Karyil Raju <sijuthomas1988@gmail.com>
LABEL Description="Order API"

COPY target/order-api.jar /order-api.jar

CMD echo Docker container started.
CMD exec java -jar /order-api.jar
EXPOSE   8080