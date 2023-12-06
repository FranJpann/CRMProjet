#!/bin/bash

findLeads() {
  api_url="http://localhost:8080/findLeads?lowAnnualRevenue=${lowAnnualRevenue}&highAnnualRevenue=${highAnnualRevenue}&state=${state}"

  result=$(curl -s "$api_url")
  echo "$result"
}

findLeadsByDate() {
  api_url="http://localhost:8080/findLeadsByDate?startDate=${startDate}&endDate=${endDate}"

  result=$(curl -s $api_url)
  echo "$result"
}

getToken() {
  read -p "Entrez votre login salesforce : " login
  read -s -p "Entrez votre mot de passe : " password
  echo

  result=$(curl --request POST 'https://login.salesforce.com/services/oauth2/token' --header 'Content-Type: application/x-www-form-urlencoded' --data-urlencode 'grant_type=password' --data-urlencode 'client_id=3MVG9XLfsJ3MQLvysrUmz9Ib74AMdcwELVowIuBXxwcRRR2vcWh5wYv7.teloCTpnKQn6fQX6wr1qFgekjoSZ' --data-urlencode 'client_secret=2F60A3BA3D0AB8A632C6E11007EC2BF9775CF599048B7DC0D70D7EE791344869'  --data-urlencode "username=${login}" --data-urlencode "password=${password}")
  echo "$result"
}

if [ "$1" == "findLeads" ]; then
  lowAnnualRevenue=$2
  highAnnualRevenue=$3
  state=$4
  findLeads
elif [ "$1" == "findLeadsByDate" ]; then
  startDate=$2
  endDate=$3
  findLeadsByDate
elif [ "$1" == "getToken" ]; then
  getToken
elif [ "$1" == "runDocker" ]; then
  sudo docker-compose build
  sudo docker-compose up
else
  echo "Fonction inconnue : $1. Veuillez spécifier une commande correcte (findLeads, findLeadsByDate, getToken, runDocker)."
  exit 1
fi