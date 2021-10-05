ROOTDIR=$(dirname "$0")/..

export APP=SplashScreenExample
export OPTS="-Djava.awt.headless=false"
export OPTS="$OPTS -splash:splash.png"

$ROOTDIR/run.sh

