/*
 * This source file was generated by the Gradle 'init' task
 */

#include "provenance-protos.h"
#include <cassert>

int main() {
    provenance_protos::Greeter greeter;
    assert(greeter.greeting().compare("Hello, World!") == 0);
    return 0;
}
