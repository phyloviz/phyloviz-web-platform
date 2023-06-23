#!/bin/bash

docker image rm localhost:5000/rclone
docker build -t localhost:5000/rclone -f rclone/Dockerfile .
docker push localhost:5000/rclone