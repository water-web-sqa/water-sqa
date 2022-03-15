FROM tomcat9-jdk8:latest
COPY target/Jiseki.war /usr/local/tomcat/webapps/Jiseki.war
EXPOSE 8080
CMD ["catalina.sh", "run"]
