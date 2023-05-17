#!/bin/bash

docker image rm localhost:5000/isolate-data-indexer
docker build -t localhost:5000/isolate-data-indexer -f isolate_data_indexer/Dockerfile .
docker push localhost:5000/isolate-data-indexer