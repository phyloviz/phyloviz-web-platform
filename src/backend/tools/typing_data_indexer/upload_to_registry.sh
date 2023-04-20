#!/bin/bash

docker image rm localhost:5000/typing-data-indexer
docker build -t localhost:5000/typing-data-indexer -f typing_data_indexer/Dockerfile .
docker push localhost:5000/typing-data-indexer