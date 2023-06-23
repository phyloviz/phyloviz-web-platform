#!/bin/bash

docker image rm localhost:5000/metadata_uploader
docker build -t localhost:5000/metadata_uploader -f metadata_uploader/Dockerfile .
docker push localhost:5000/metadata_uploader