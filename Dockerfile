# Pull base image
From tomcat:8-jre8

# Maintainer
MAINTAINER "DIEGO SERONATO <dlqseronato@gmail.com">

# Copy to images tomcat path
ADD gestaorh.war /usr/local/tomcat/webapps/