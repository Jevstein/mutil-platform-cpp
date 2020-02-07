import os
import sys
import time
import platform
import shutil
import getpass
import threading

CUR_FILE_DIR = os.path.split(os.path.realpath(__file__))[0]

NDK_BUILD = 'ndk-build'
NDK_BUILD_CMD = ' _APP_PLATFORM_=%s _APP_ABI_=%s NDK_DEBUG=0 -j 4 -C "%s"'

APP_PLATFORM = "android-14"
#APP_ABI = ['armeabi', 'armeabi-v7a', 'x86', 'arm64-v8a', 'x86_64', 'mips64', 'mips']
APP_ABI = ['armeabi-v7a']
#APP_ABI = ['all32']
#APP_ABI = ['all64']

def main():
    for i in range(0, len(APP_ABI)):
        '''build thirdparty'''
        #protobuf
        run_cmd = 'python ' + os.path.join(CUR_FILE_DIR, '../../../thirdparty/protobuf/project/android/build.py "%s" %s %s %s')
        if 0 != os.system(run_cmd%(NDK_BUILD, APP_PLATFORM, APP_ABI[i], "")):
            return 1
        #boost    
        run_cmd = 'python ' + os.path.join(CUR_FILE_DIR, '../../../thirdparty/boost/project/android/build.py "%s" %s %s %s')
        if 0 != os.system(run_cmd%(NDK_BUILD, APP_PLATFORM, APP_ABI[i], "")):
            return 1
        #sqlite    
        run_cmd = 'python ' + os.path.join(CUR_FILE_DIR, '../../../thirdparty/sqlite/project/android/build.py "%s" %s %s %s')
        if 0 != os.system(run_cmd%(NDK_BUILD, APP_PLATFORM, APP_ABI[i], "")):
            return 1
        #jnihelpers    
        run_cmd = 'python ' + os.path.join(CUR_FILE_DIR, '../../../thirdparty/jnihelpers/project/android/build.py "%s" %s %s %s')
        if 0 != os.system(run_cmd%(NDK_BUILD, APP_PLATFORM, APP_ABI[i], "")):
            return 1

        #build lib
        run_cmd = 'python ' + os.path.join(CUR_FILE_DIR, '../../../modules/project/android/build.py "%s" %s %s %s')
        if 0 != os.system(run_cmd%(NDK_BUILD, APP_PLATFORM, APP_ABI[i], "")):
            return 1
            
        run_cmd = 'python ' + os.path.join(CUR_FILE_DIR, '../../../log/project/android/build.py "%s" %s %s %s')
        if 0 != os.system(run_cmd%(NDK_BUILD, APP_PLATFORM, APP_ABI[i], "")):
            return 1
            
        run_cmd = 'python ' + os.path.join(CUR_FILE_DIR, '../../../imcore/project/android/build.py "%s" %s %s %s')
        if 0 != os.system(run_cmd%(NDK_BUILD, APP_PLATFORM, APP_ABI[i], "")):
            return 1
            
        run_cmd = 'python ' + os.path.join(CUR_FILE_DIR, '../../../common/project/android/build.py "%s" %s %s %s')
        if 0 != os.system(run_cmd%(NDK_BUILD, APP_PLATFORM, APP_ABI[i], "")):
            return 1

        run_cmd = 'python ' + os.path.join(CUR_FILE_DIR, '../../../protocol/project/android/build.py "%s" %s %s %s')
        if 0 != os.system(run_cmd%(NDK_BUILD, APP_PLATFORM, APP_ABI[i], "")):
            return 1
    
        #build imsdk
        run_cmd = NDK_BUILD + NDK_BUILD_CMD%(APP_PLATFORM, APP_ABI[i], os.path.join(CUR_FILE_DIR, "jni"))
        print(("build imsdk for %s<-->%s")%(APP_PLATFORM, APP_ABI[i]) + "\n" + run_cmd)
        if 0 != os.system(run_cmd):
            print("build imsdk error!!!\n")
            return 1
        
        print("build imsdk finish\n")
            
    return 0

if __name__ == "__main__":
    sys.exit(main())