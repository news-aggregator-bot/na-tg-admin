#!/bin/bash
# set -x #echo on
ssh -o StrictHostKeyChecking=no -i ~/.ssh/$SSH_KEY_FILE -l $SSH_USER $SSH_HOST "/bin/bash --login -s" -- < scripts/swarm-update.sh "https://raw.githubusercontent.com/news-aggregator-bot/na-tg-admin/master/docker-compose.yml"