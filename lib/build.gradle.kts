/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample C++ project to get you started.
 * For more details on building C++ applications and libraries, please refer to https://docs.gradle.org/8.8/userguide/building_cpp_projects.html in the Gradle documentation.
 * This project uses @Incubating APIs which are subject to change.
 */

plugins {
    // Apply the cpp-library plugin to add support for building C++ libraries
    `cpp-library`

    // Apply the cpp-unit-test plugin to add support for building and running C++ test executables
    `cpp-unit-test`


}

library {
    // Set the target operating system and architecture for this library
    targetMachines.add(machines.macOS.architecture("aarch64"))
}
