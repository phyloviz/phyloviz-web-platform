#!/bin/bash

cd ..

git clone https://github.com/devandrepascoa/phyloDB.git # https://github.com/phyloviz/phyloDB.git

cd phyloDB/

./build-docker.sh
./launch-docker.sh