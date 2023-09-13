#!/bin/bash
cd /home/ec2-user
aws s3 cp s3://tcc-microservice-builds-bucket/calendar-event-management-service.jar
java -jar calendar-event-management-service.jar