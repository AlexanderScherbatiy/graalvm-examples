set -e
set -x

# Set GRAALVM enviroment variable

OUT=classes

rm -rf $OUT *.png
mkdir $OUT

$GRAALVM/bin/javac -d ./$OUT ./*.java
$GRAALVM/bin/java -classpath $OUT -Djava.awt.headless=false GenerateImage

