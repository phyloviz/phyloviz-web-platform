cd src/backend || exit

./gradlew build

cd ../frontend || exit

npm install

npm start &

docker compose up -d --build
echo "Doing very heavy work..."
sleep 90
docker compose up -d
