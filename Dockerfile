FROM openjdk:8-jdk-alpine
VOLUME [ "/opt/contact/" ]
WORKDIR /opt/contact/
ADD target/lib /opt/contact/lib
ADD target/contact-0.0.1.jar /opt/contact/contact.jar
EXPOSE 8181

ENTRYPOINT ["java", "-jar","/opt/contact/contact.jar"]