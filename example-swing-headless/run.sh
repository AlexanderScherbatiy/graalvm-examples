ROOTDIR=$(dirname "$0")/..

export APP=GenerateSwingImage
export OPTS="-Djava.awt.headless=false"

$ROOTDIR/run.sh

