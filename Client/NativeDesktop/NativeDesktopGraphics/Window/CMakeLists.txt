cmake_minimum_required(VERSION 3.5)
project(nativeWindow)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES DefaultNativeWindow.cpp mainwindow.cpp)

find_package(Qt5Core)
find_package(Qt5Widgets)

set(CMAKE_AUTOMOC ON)
SET(CMAKE_AUTOUIC ON)


SET(CMAKE_INCLUDE_CURRENT_DIR ON)
INCLUDE_DIRECTORIES(${CMAKE_CURRENT_BINARY_DIR})

add_library(Window ${SOURCE_FILES} ${nativeWindow_hdr_moc} ${nativeWindow_form_hdr})
target_include_directories(Window PUBLIC .)

target_link_libraries(Window Qt5::Core)
target_link_libraries(Window Qt5::Widgets)

