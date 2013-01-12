#!/bin/bash

#echo '"MAC","kanal","frekvence","silaSignalu","dBm","sifrovani","SSID"' > ./jWiFiScan.csv && 

gksudo iwlist $1 scan | awk -F '[ :=]+' '/Address/{printf "\"" substr($0,30)"\",\""} /(Channel:|Frequency|Quality)/{printf $3"\",\""} /(Signal)/{printf $6"\",\""} /Encryption/{printf $4"\","} /ESSID/{print substr($0,27)}'

# >> ./jWiFiScan.csv


