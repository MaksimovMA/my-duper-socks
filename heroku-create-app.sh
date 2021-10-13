#!/usr/bin/env sh

heroku apps:create my-duper-socks
heroku addons:create heroku-postgresql:hobby-dev --app my-duper-socks