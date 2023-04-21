#!/bin/bash

docker image rm localhost:5000/uploader
docker build -t localhost:5000/uploader -f uploader/Dockerfile .
docker push localhost:5000/uploader