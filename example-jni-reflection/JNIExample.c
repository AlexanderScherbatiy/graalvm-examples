#include "JNIExample.h"

/*
 * Class:     JNIExample
 * Method:    nativeHello
 * Signature: ()J
 */
JNIEXPORT void JNICALL Java_JNIExample_nativeHello(JNIEnv *env, jclass cls) {
    printf("Hello, JNI!\n");
}
