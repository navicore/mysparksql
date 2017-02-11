#!/bin/sh
cd player-master && /usr/bin/sbt assembly && cd ..
mkdir -p assembly-artifacts
cp player-master/Dockerfile assembly-artifacts/
cp -R player-master/devops assembly-artifacts/
cp -R player-master/target assembly-artifacts/

