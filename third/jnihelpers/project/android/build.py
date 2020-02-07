import os
import sys
import time
import platform
import shutil
import getpass
import threading
import zipfile
import urllib
import urllib2

CUR_FILE_DIR = os.path.split(os.path.realpath(__file__))[0]

MODULE_NAME = "jnihelpers"
NDK_BUILD_CMD = ' _APP_PLATFORM_=%s _APP_ABI_=%s NDK_DEBUG=0 %s -j 4 -C "%s"'

def get_argv(argv, i):
    return (argv[i] if(len(argv) >= i + 1) else "")

def main():
    NDK_BUILD = get_argv(sys.argv, 1)
    APP_PLATFORM = get_argv(sys.argv, 2)
    APP_ABI = get_argv(sys.argv, 3)
    APPEND_NDK_CMD = get_argv(sys.argv, 4)
    
    if '' == APP_PLATFORM.strip() or '' == APP_ABI.strip():
        print(("%s build error: APP_PLATFORM=%s, APP_ABI=%s")%(MODULE_NAME, APP_PLATFORM, APP_ABI))
        return 1
    
    run_cmd = NDK_BUILD + NDK_BUILD_CMD%(APP_PLATFORM, APP_ABI, APPEND_NDK_CMD, os.path.join(CUR_FILE_DIR, 'jni'))
    print(("build %s for %s<-->%s")%(MODULE_NAME, APP_PLATFORM, APP_ABI) + "\n" + run_cmd)

    ret = os.system(run_cmd)
    if(0 == ret):
        print(("build %s finish\n")%(MODULE_NAME))
    else:
        print(("build %s error!!!(ret=%d)\n")%(MODULE_NAME, ret))
        
    return ret

if __name__ == "__main__":
    sys.exit(main())