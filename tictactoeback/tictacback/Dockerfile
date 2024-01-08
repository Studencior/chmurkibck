# Use a base image with OpenJDK 17
FROM openjdk:17

# Set environment variables for Maven
# ENV MAVEN_VERSION 3.8.8
# ENV MAVEN_HOME /usr/share/maven

# RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
#   && curl -fsSL https://downloads.apache.org/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
#   | tar -xzC /usr/share/maven --strip-components=1 \
#   && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn


WORKDIR /app

COPY . /app

# Make port 8080 available to the world outside this container
EXPOSE 8080

# RUN mvn clean install
# RUN mvn clean package
# The application's jar file (adjust the jar file name as per your project)
COPY target/tictac-0.0.1-SNAPSHOT.jar /app/tictac.jar

# Run the jar file
CMD ["java", "-jar", "tictac.jar"]