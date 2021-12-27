#!/usr/bin/env sh

set +ex

export PROJECT_NAME="$1"

cd "${GITHUB_WORKSPACE}/${PROJECT_NAME}" || exit

lein do clean, check, test, compile, uberjar, native, run-native
