FROM adoptopenjdk/openjdk11
WORKDIR /app
COPY target/bike-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080
EXPOSE 5432
#
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dfile.encoding=UTF-8 -jar bike-0.0.1-SNAPSHOT.jar" ]