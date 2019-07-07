#!/bin/sh
set -e

# Set the timezone of the container.
if [ "$SET_CONTAINER_TIMEZONE" = "true" ]; then
    echo ${CONTAINER_TIMEZONE} >/etc/timezone && \
    ln -sf /usr/share/zoneinfo/${CONTAINER_TIMEZONE} /etc/localtime && \
    dpkg-reconfigure -f noninteractive tzdata
    echo "Container timezone set to: $CONTAINER_TIMEZONE"
else
    echo "Container timezone not modified"
fi

# Synchronize the time of the container.
ntpd -gq
service ntp start

echo "Using configuration location: ${CONFIG_LOCATION}"

# Start the application that is to run in the container.
gosu $RUN_AS_USER java -jar ${JAR_PATH} --spring.config.location=${CONFIG_LOCATION}

