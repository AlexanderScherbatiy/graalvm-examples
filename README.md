# GraalVM examples

## Setup

Set `GRAALVM` environment variable which points to GraalVM
installation directory.

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
