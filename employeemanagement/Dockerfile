# Start with a base image containing Java runtime
FROM java:8

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 9000
  
# The application's jar file  
ARG JAR_FILE=target/employeemanagement-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container  
COPY target/employeemanagement-0.0.1-SNAPSHOT.jar test.jar
  
# Run the jar file  
ENTRYPOINT ["java","-Djava.security.egd_=file:/dev/./urandom","-jar","test.jar"]  