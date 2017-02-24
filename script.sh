#!/usr/bin/env bash

if [[ "$TRAVIS_TAG" == *_* ]]
then
git describe --tags
git rev-list --first-parent --count origin/master
./gradlew publishApkRelease
fi
