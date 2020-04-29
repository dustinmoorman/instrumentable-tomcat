# Instrumentable Tomcat

A simple tomcat application used for testing purposes.  The objective is to provide a JSP and Servlet application, customized for various operations, and ultimately used for instrumenting and metrics emission from Tomcat.

![](https://github.com/dustinmoorman/instrumentable-tomcat/blob/master/assets/flowmap.png?raw=true)

## Technical Objectives

An application that can make web requests and log the activity to a database.  The outbound web requests will be visible in tracing tools and advanced APM solutions such as AppDynamics.  Additionally, the logging of the activity to a local database installation will also be logged by AppDynamics.

This application should be an analog to any standard Java application, and as additional capabilities need to be tested for proper instrumentation, they will be added.

## Deploying an application to Tomcat

Deploying an application to Tomcat is simple:

1. Place your War in the `/opt/tomcat/latest/webapps/` directory.
2. Restart Tomcat with Systemd `sudo service tomcat restart`.

## System Overview & Environment
- Ubuntu 18.04
- Java JDK (8+) (`java-1.8.0-openjdk-amd64`)
- MySQL (latest in apt-get)
- Tomcat 9
