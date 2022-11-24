FROM adoptopenjdk/openjdk11
WORKDIR /app
COPY BikeID/0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
#
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dfile.encoding=UTF-8 -jar 0.0.1-SNAPSHOT.jar" ]