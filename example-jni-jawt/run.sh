#$GRAAL/bin/javac -h . JAWTExample.java

ROOTDIR=$(dirname "$0")/..

export APP=JAWTExample
export USE_JNI=true
export JNI_OPTIONS="-l:libjawt.a -l:libawt_xawt.a -l:libawt.a -L$GRAALVM/lib"
export RUN_APP=true
export NATIVE_IMAGE=true
export OUT=.

$ROOTDIR/run.sh

