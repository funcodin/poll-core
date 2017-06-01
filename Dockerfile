FROM jetty
ADD ./target/poll-core-0.0.1-SNAPSHOT.war /var/lib/jetty/webapps/root.war
EXPOSE 8080

