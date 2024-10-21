#!/bin/bash

# Check if Docker is running, if not exit the script
checkDocker() {
    if ! docker info >/dev/null 2>&1; then
        echo "Error: Docker is not running. Please start Docker first."
        exit 1
    fi
    echo "Docker is running!"
}

echo "Checking Docker..."
checkDocker

cd ..

git clone --branch wsl https://github.com/devandrepascoa/phyloDB.git # https://github.com/phyloviz/phyloDB.git

cd phyloDB/

./build-docker.sh
./launch-docker.sh

echo "PhyloDB setup completed!"