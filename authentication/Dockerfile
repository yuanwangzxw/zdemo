FROM openjdk:8-jdk
ARG JAR_FILE
COPY ${JAR_FILE} ${JAR_FILE}.jar
EXPOSE 6001
CMD [ "java","-jar","${JAR_FILE}.jar" ]