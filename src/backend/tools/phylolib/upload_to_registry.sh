#!/bin/bash

docker image rm localhost:5000/phylolib
docker build -t localhost:5000/phylolib -f phylolib/Dockerfile .
docker push localhost:5000/phylolib