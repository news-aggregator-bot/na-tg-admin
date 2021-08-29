wget -q $1 -O docker-stack-tg-admin.yml
echo "Deploying new na-tg-admin stack"
docker stack rm na-tg-admin
docker stack deploy --compose-file docker-stack-bot.yml --with-registry-auth na-tg-admin