#include <jni.h>
#include <jawt_md.h>

#include "JAWTExample.h"

/*
 * Class:     JAWTExample
 * Method:    getJAWT
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_JAWTExample_getJAWT(JNIEnv *env, jclass cls) {

    printf("[native] call getJAWT\n");

    JAWT *awt = new JAWT();
    awt->version = (jint)JAWT_VERSION_9;
    jboolean result = JAWT_GetAWT(env, awt);

    return (result == JNI_TRUE) ? (jlong) awt : 0;
}
