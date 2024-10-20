cd src/backend || exit

./gradlew build

cd ../frontend || exit

npm install

npm start &

docker compose up -d --build
sleep 60
echo "Doing very heavy work..."
docker compose up -d
