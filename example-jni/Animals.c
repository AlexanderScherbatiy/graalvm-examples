#include <jni.h>
#include "Animals.h"

/*
 * Class:     Animals
 * Method:    createAnimal
 * Signature: ()LAnimals/Animal;
 */
JNIEXPORT jobject JNICALL Java_Animals_createAnimal
  (JNIEnv *env, jclass c) {

    jobject cls;
    jobject object;
    jmethodID constructor;

    cls = (*env)->FindClass(env, "Animals$Dog");
    constructor = (*env)->GetMethodID(env, cls, "<init>", "()V");
    object = (*env)->NewObject(env, cls, constructor);
    return object;
  }
