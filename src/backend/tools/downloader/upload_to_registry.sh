#!/bin/bash

docker image rm localhost:5000/downloader
docker build -t localhost:5000/downloader -f downloader/Dockerfile .
docker push localhost:5000/downloader