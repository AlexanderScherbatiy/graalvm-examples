set -e
set -x

# Set GRAALVM enviroment variable

if [ -z "$APP" ]; then
       echo "APP varibles is not set"
       exit -1
fi

OUT=classes

rm -rf $OUT *.png

$GRAALVM/bin/javac -d ./$OUT ./*.java
$GRAALVM/bin/java -classpath $OUT $OPTS $APP

