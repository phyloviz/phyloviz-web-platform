#!/bin/bash

docker image rm localhost:5000/downloader
docker image rm localhost:5000/uploader
docker image rm localhost:5000/typing-data-indexer
docker image rm localhost:5000/tree-indexer
docker image rm localhost:5000/compute-tree-view

docker build -t localhost:5000/downloader -f downloader/Dockerfile .
docker build -t localhost:5000/uploader -f uploader/Dockerfile .
docker build -t localhost:5000/typing-data-indexer -f typing_data_indexer/Dockerfile .
docker build -t localhost:5000/tree-indexer -f tree_indexer/Dockerfile .
docker build -t localhost:5000/compute-tree-view -f compute_tree_view/Dockerfile .

docker push localhost:5000/downloader
docker push localhost:5000/uploader
docker push localhost:5000/typing-data-indexer
docker push localhost:5000/tree-indexer
docker push localhost:5000/compute-tree-view