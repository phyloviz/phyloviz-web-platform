#!/bin/bash

docker build -t localhost:5000/downloader -f downloader/Dockerfile .
docker build -t localhost:5000/uploader -f uploader/Dockerfile .
# docker build -t localhost:5000/phylodb -f phylodb/Dockerfile .

docker push localhost:5000/downloader
docker push localhost:5000/uploader
# docker push localhost:5000/phylodb