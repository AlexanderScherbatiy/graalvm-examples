#!/bin/bash
set -e
set -x

# Set GRAALVM environment variable

if [ -z "$APP" ]; then
  echo "APP varibles is not set"
  exit -1
fi

if [ -z "$OUT" ]; then
  OUT=./classes
  rm -rf ./$OUT *.png
fi

if [ -z "$NATIVE_APP" ]; then
  NATIVE_APP=$APP
fi

if [ "x$USE_JNI" == "xtrue" ]; then
  HEADERS="-h ./$OUT"
  JAVA_LIB_PATH="-Djava.library.path=./$OUT"
fi

$GRAALVM/bin/javac $HEADERS -d ./$OUT ./*.java

if [ "x$USE_JNI" == "xtrue" ]; then
  g++ -g -shared -fPIC -I${GRAALVM}/include -I${GRAALVM}/include/linux -I./$OUT $NATIVE_APP.c -o ./$OUT/lib$NATIVE_APP.so $JNI_OPTIONS
fi

if [ "x$RUN_APP" == "xtrue" ]; then
  $GRAALVM/bin/java -classpath $OUT $JAVA_LIB_PATH $OPTS $APP
else
  echo "Skip native image generation"
fi

if [ "x$NATIVE_IMAGE" == "xtrue" ]; then
  $GRAALVM/bin/native-image -classpath $OUT $JAVA_LIB_PATH $OPTS $APP
else
  echo "Skip native image generation"
fi

