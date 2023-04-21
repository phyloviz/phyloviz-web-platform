#!/bin/bash

docker image rm localhost:5000/compute-tree-view
docker build -t localhost:5000/compute-tree-view -f compute_tree_view/Dockerfile .
docker push localhost:5000/compute-tree-view
