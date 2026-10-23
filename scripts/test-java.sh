#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/.."
if ! command -v javac >/dev/null 2>&1; then
  echo 'JDK 17+ is required (javac not found).' >&2
  exit 1
fi
test_build_dir="$(mktemp -d)"
trap 'rm -r -- "$test_build_dir"' EXIT
find java/src java/test -name '*.java' -print > "$test_build_dir/sources.txt"
javac --release 17 -encoding UTF-8 -d "$test_build_dir/classes" @"$test_build_dir/sources.txt"
java -cp "$test_build_dir/classes" algorithms.backtracking.AlgorithmChecks
