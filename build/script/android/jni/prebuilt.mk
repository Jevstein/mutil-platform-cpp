TEMP_LOCAL_PATH := $(call my-dir)/../../../..


include $(CLEAR_VARS)
LOCAL_MODULE := modules
LOCAL_SRC_FILES := $(TEMP_LOCAL_PATH)/modules/project/android/obj/local/$(TARGET_ARCH_ABI)/libmodules.a
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := imcore
LOCAL_SRC_FILES := $(TEMP_LOCAL_PATH)/imcore/project/android/obj/local/$(TARGET_ARCH_ABI)/libimcore.a
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := log
LOCAL_SRC_FILES := $(TEMP_LOCAL_PATH)/log/project/android/obj/local/$(TARGET_ARCH_ABI)/liblog.a
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := common
LOCAL_SRC_FILES := $(TEMP_LOCAL_PATH)/common/project/android/obj/local/$(TARGET_ARCH_ABI)/libcommon.a
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := protocol
LOCAL_SRC_FILES := $(TEMP_LOCAL_PATH)/protocol/project/android/obj/local/$(TARGET_ARCH_ABI)/libprotocol.a
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := protobuf
LOCAL_SRC_FILES := $(TEMP_LOCAL_PATH)/thirdparty/protobuf/project/android/obj/local/$(TARGET_ARCH_ABI)/libprotobuf.a
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := boost
LOCAL_SRC_FILES := $(TEMP_LOCAL_PATH)/thirdparty/boost/project/android/obj/local/$(TARGET_ARCH_ABI)/libboost.a
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := sqlite
LOCAL_SRC_FILES := $(TEMP_LOCAL_PATH)/thirdparty/sqlite/project/android/obj/local/$(TARGET_ARCH_ABI)/libsqlite.a
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := jnihelpers
LOCAL_SRC_FILES := $(TEMP_LOCAL_PATH)/thirdparty/jnihelpers/project/android/obj/local/$(TARGET_ARCH_ABI)/libjnihelpers.a
include $(PREBUILT_STATIC_LIBRARY)
