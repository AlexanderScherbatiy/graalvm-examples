#$GRAAL/bin/javac -h . JAWTExample.java

ROOTDIR=$(dirname "$0")/..

export APP=JAWTExample
export USE_JNI=true
export JNI_OPTIONS="-ljawt -L$GRAALVM/lib"
export RUN_APP=true
export NATIVE_IMAGE=false
export OUT=.

$ROOTDIR/run.sh

