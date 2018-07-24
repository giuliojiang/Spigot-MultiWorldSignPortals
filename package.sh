#!/bin/bash

# cd to this directory
cd "$(dirname "$0")"
ROOTDIR="$(pwd)"

rm -rf build

# Create out/production/WelcomeMessage/WelcomeMessage.jar
cd out/production/Spigot-MultiWorldSignPortals
zip -r MultiWorldSignPortals.jar .

cd $ROOTDIR
pwd
mkdir build
mv out/production/Spigot-MultiWorldSignPortals/MultiWorldSignPortals.jar build/MultiWorldSignPortals.jar