cmake_minimum_required(VERSION 3.5)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES boot.cpp)

add_executable(NativeDesktopGraphicsBoot ${SOURCE_FILES})

target_link_libraries(NativeDesktopGraphicsBoot LINK_PUBLIC libWindow)

