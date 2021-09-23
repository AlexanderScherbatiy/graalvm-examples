# GraalVM examples

## Setup

Set `GRAALVM` environment variable which points to GraalVM
installation directory.

## Demos
### Notepad
`$GRAALMV/bin/native-image -Djava.awt.headless=false -H:IncludeResources='.*/Notepad.*properties$' -H:IncludeResources='.*/.*gif$' -jar Notepad.jar`

### Stylepad
`$GRAALMV/bin/native-image -Djava.awt.headless=false -H:IncludeResources='.*/(Notepad|Stylepad).*properties$' -H:IncludeResources='.*/.*gif$' -H:SerializationConfigurationFiles=serialization-config.json -jar Stylepad.jar`

## Options

### Include Resources
native-image: ```-H:IncludeResources='.*/Notepad.*properties$'```

### Tracing Agent
java: ```-agentlib:native-image-agent=config-output-dir=conf-dir```

### Report Exception Stack Traces
native-image: ```-H:+ReportExceptionStackTraces ```

### Headless Option
java|native-image: ```-Djava.awt.headless=true```

## GraalVM Error codes:

`Error: Image build request failed with exit status 137` - OutOfMemoryError
