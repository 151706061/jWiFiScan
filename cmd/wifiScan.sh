#!/bin/bash

#echo '"MAC","kanal","frekvence","silaSignalu","dBm","sifrovani","SSID"' > ./jWiFiScan.csv && 

echo -e "$2\n" | /usr/bin/sudo -S /sbin/iwlist $1 scan | awk -F '[ :=]+' '/Address/{printf "\"" substr($0,30)"\",\""} /(Channel:|Frequency|Quality)/{printf $3"\",\""} /(Signal)/{printf $6"\",\""} /Encryption/{printf $4"\","} /ESSID/{print substr($0,27)}'

# >> ./jWiFiScan.csv

### bez zadavani hesla:   
###		/bin/bash wifiScan.sh wlan0 SUDO_HESLO


