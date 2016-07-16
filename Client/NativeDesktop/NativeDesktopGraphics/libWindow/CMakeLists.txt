cmake_minimum_required(VERSION 3.5)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

include_directories(headers)

set(SOURCE_FILES headers/DefaultNativeWindow.cpp)

add_library(libWindow ${SOURCE_FILES})
target_include_directories(libWindow PUBLIC headers)