#!/bin/bash

# http://ubuntuforums.org/showthread.php?t=1118003
for f in /sys/class/net/*
do
    if [[ -d $f/wireless && $f != "/sys/class/net/mon."* ]]; then
	basename $f
    fi
done
