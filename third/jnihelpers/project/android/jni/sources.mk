TEMP_LOCAL_PATH := $(call my-dir)/../../..

include $(TEMP_LOCAL_PATH)/../../ndk_utils/flags.mk


SRC := $(wildcard $(TEMP_LOCAL_PATH)/src/*.cpp)
SRC := $(SRC:$(LOCAL_PATH)/%=%)
LOCAL_SRC_FILES += $(SRC)
