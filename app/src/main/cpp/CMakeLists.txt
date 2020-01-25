# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)
project("skia_example")


 include_directories("${CMAKE_SOURCE_DIR}")

link_directories(${PROJECT_SOURCE_DIR}/../../../libs/armeabi-v7a)
#include_directories(${CMAKE_SOURCE_DIR})

aux_source_directory(${CMAKE_SOURCE_DIR} SRC_LIST)
# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.


add_library(skia SHARED IMPORTED)
#set_property(TARGET skia APPEND PROPERTY INTERFACE_INCLUDE_DIRECTORIES
#        "${CMAKE_SOURCE_DIR}"
#
#        "${CMAKE_SOURCE_DIR}/include"
#        "${CMAKE_SOURCE_DIR}/include/android"
#        "${CMAKE_SOURCE_DIR}/include/atlastext"
#        "${CMAKE_SOURCE_DIR}/include/c"
#        "${CMAKE_SOURCE_DIR}/include/codec"
#        "${CMAKE_SOURCE_DIR}/include/config"
#        "${CMAKE_SOURCE_DIR}/include/core"
#        "${CMAKE_SOURCE_DIR}/include/encode"
#        "${CMAKE_SOURCE_DIR}/include/effects"
#        "${CMAKE_SOURCE_DIR}/include/pathops"
#        "${CMAKE_SOURCE_DIR}/include/ports"
#        "${CMAKE_SOURCE_DIR}/include/private"
#        "${CMAKE_SOURCE_DIR}/include/svg"
#        "${CMAKE_SOURCE_DIR}/include/ports"
#        "${CMAKE_SOURCE_DIR}/include/third_party"
#        "${CMAKE_SOURCE_DIR}/include/utils")

set_target_properties(skia PROPERTIES IMPORTED_LOCATION
        "${PROJECT_SOURCE_DIR}/../../../libs/${ANDROID_ABI}/libskia.so")




message("-------------------------")
message("CMAKE_SOURCE_DIR:${CMAKE_SOURCE_DIR}")
message("file src:${SRC_LIST}")

add_library(native-lib
        SHARED
        # Provides a relative path to your source file(s).
        "${SRC_LIST}")

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library(opensl OpenSLES)

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_include_directories(native-lib PUBLIC
        "${CMAKE_SOURCE_DIR}"

        "${CMAKE_SOURCE_DIR}/include"
        "${CMAKE_SOURCE_DIR}/include/android"
        "${CMAKE_SOURCE_DIR}/include/atlastext"
        "${CMAKE_SOURCE_DIR}/include/c"
        "${CMAKE_SOURCE_DIR}/include/codec"
        "${CMAKE_SOURCE_DIR}/include/config"
        "${CMAKE_SOURCE_DIR}/include/core"
        "${CMAKE_SOURCE_DIR}/include/effects"
        "${CMAKE_SOURCE_DIR}/include/pathops"
        "${CMAKE_SOURCE_DIR}/include/ports"
        "${CMAKE_SOURCE_DIR}/include/encode"
        "${CMAKE_SOURCE_DIR}/include/gpu"
        "${CMAKE_SOURCE_DIR}/include/private"
        "${CMAKE_SOURCE_DIR}/include/svg"
        "${CMAKE_SOURCE_DIR}/include/ports"
        "${CMAKE_SOURCE_DIR}/include/third_party"
        "${CMAKE_SOURCE_DIR}/include/utils")

target_link_libraries(native-lib

        skia
        -ljnigraphics
        -landroid
        ${opensl}
        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})