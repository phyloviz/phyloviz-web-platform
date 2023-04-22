#!/bin/bash

docker image rm localhost:5000/tree-indexer
docker build -t localhost:5000/tree-indexer -f tree_indexer/Dockerfile .
docker push localhost:5000/tree-indexer