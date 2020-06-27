FROM openjdk:8-jdk
ARG JAR_FILE
COPY JAR_FILE authentication.jar
EXPOSE 6001
CMD [ "java","-jar","authentication.jar" ]