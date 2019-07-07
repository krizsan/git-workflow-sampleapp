FROM openjdk:11.0.3-jdk-stretch

# Absolute path to the JAR file to be launched when a Docker container is started.
ENV JAR_PATH=/application/lib/hello-webapp.jar \
	# Default is to set timezone on container start.
    SET_CONTAINER_TIMEZONE=true \
    # Default timezone.
    CONTAINER_TIMEZONE=Europe/Stockholm \
    # Path to application configuration file in image.
    CONFIG_LOCATION=file:/application/config/application.properties \
    # User which will run the application in containers.
    RUN_AS_USER=runner

# User which will run the application in containers - build argument.
# Must be the same as the above RUN_AS_USER environment variable.
ARG RUN_AS_USER_ARG=runner

# Add user and group which will run the application in containers.
RUN groupadd -f ${RUN_AS_USER_ARG} && \
    useradd --system --home /home/${RUN_AS_USER_ARG} -g ${RUN_AS_USER_ARG} ${RUN_AS_USER_ARG} && \

# Install NTP for time synchronization and gosu for step-down from root.
# Cannot use the USER command in the Docker file since the startup script
# needs to be executed as root in order to be able to start NTP.
    apt-get update && \
    apt-get dist-upgrade -y && \
    apt-get install -y ntp gosu && \
    # Clean up after instals.
    apt-get autoclean && apt-get --purge -y autoremove && \
    rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/* && \

# Create directory to hold the application and all its contents in the Docker image.
    mkdir /application && \
    mkdir /application/config && \
    mkdir /application/certs && \
    mkdir /application/bin

# Copy all the static contents to be included in the Docker image.
COPY ./application/ /application/ 
COPY ./application/config/* /application/config/
COPY ./application/bin/* /application/bin/

# Make all scripts in the bin directory executable. Includes start-script.
RUN chmod +x /application/bin/*.sh && \
# Set the owner of all application-related files to the user which will
# run the application in containers.
    chown -R ${RUN_AS_USER_ARG}:${RUN_AS_USER_ARG} /application/

# Web port.
EXPOSE 8080

CMD [ "/application/bin/start-app.sh" ]

