#!/bin/bash

findLeads() {
  ./gradlew :CommandLineTool:run --args="findLeads ${lowAnnualRevenue} ${highAnnualRevenue} ${state}"
}

findLeadsByDate() {
  ./gradlew :CommandLineTool:run --args="findLeadsByDate ${startDate} ${endDate}"
}

appRun() {
  ./gradlew --parallel :VirtualCRM:appRun :InternalCRM:run
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
elif [ "$1" == "appRun" ]; then
  appRun
elif [ "$1" == "-h" ] || [ "$1" == "-help" ]; then
    printf "Fonctions disponibles : \n findLeads (lowAnnualRevenue) (highAnnualRevenue) (state) \n findLeadsByDate (YYYY-MM-dd) (YYYY-MM-dd) \n appRun"
else
  echo "Fonction inconnue : $1. Veuillez utiliser -h ou -help pour voir les commandes disponibles."
  exit 1
fi