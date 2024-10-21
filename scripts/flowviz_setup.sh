#!/bin/bash

cd ..

git clone https://github.com/devandrepascoa/FLOWViZ.git # https://github.com/DIVA-IPL-Project/FLOWViZ.git

cd ./FLOWViZ

echo "Steps 1 to 3 of the manual setup are going to be executed..."

npm install

cd client

npm install

cd ..

./setupAirflow.sh

echo "Setting up FLOWViZ docker..."
docker compose up -d
echo "FLOWViZ docker setup completed!"

echo "Steps 1 to 3 of the manual setup were executed successfully!"
echo "Now you still need to execute steps 4 to 8 of the manual setup. (https://github.com/devandrepascoa/FLOWViZ?tab=readme-ov-file#manual-setup)"
