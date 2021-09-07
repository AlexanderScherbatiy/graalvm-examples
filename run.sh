set -e
set -x

# Set GRAALVM environment variable

if [ -z "$APP" ]; then
  echo "APP varibles is not set"
  exit -1
fi

OUT=classes

if [ "x$USE_JNI" == "xtrue" ]; then
  HEADERS="-h ./$OUT"
  JAVA_LIB_PATH="-Djava.library.path=./$OUT"
fi

rm -rf $OUT *.png

$GRAALVM/bin/javac $HEADERS -d ./$OUT ./*.java

if [ "x$USE_JNI" == "xtrue" ]; then
  gcc -g -shared -fPIC -I${GRAALVM}/include -I${GRAALVM}/include/linux -I./classes $APP.c -o classes/lib$APP.so
fi

$GRAALVM/bin/java -classpath $OUT $JAVA_LIB_PATH $OPTS $APP

