#$GRAAL/bin/javac -h . JNIExample.java

ROOTDIR=$(dirname "$0")/..

export APP=ReflectionExample
export NATIVE_APP=JNIExample
export USE_JNI=true
export RUN_APP=true
export NATIVE_IMAGE=true
export OUT=.

$ROOTDIR/run.sh

