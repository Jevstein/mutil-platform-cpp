#**********************************************************
#
# by jevstein
# 2019/08/08
#
# Note: generate corresponding multiple static libraries, 
#		dynamic libraries, executable files and other ELF files.
#
#**********************************************************

# target_plat = ios
# platform    = ios64
# DEBUG 		= NO
#
# include mkapp.mk

DEBUG 		     = NO
IOS_PLATFORMS    = ios32 ios64 ios_sim32 ios_sim64
MAC_PLATFORMS    = x86_32 x86_64 xDefault
LINUX_PLATFORMS  = arm32 arm64 X86_32 X86_64
ROOT_DIR         = ../../..

all: ios mac #lipo
	$(info all success!)
	
ios:
	@for i in $(IOS_PLATFORMS);                                    \
	do                                                             \
		echo ========= compile [ios.$$i] =========;                \
		$(MAKE) -f mkapp.mk target_plat=ios platform=$$i;          \
	done                                
                                
mac:                                
	@for i in $(MAC_PLATFORMS);                                    \
	do                                                             \
		echo ========= compile [mac.$$i] =========;                \
		$(MAKE) -f mkapp.mk target_plat=mac platform=$$i;          \
	done                              
                              
linux:                              
	@for i in $(LINUX_PLATFORMS);                                  \
	do                                                             \
		echo ========= compile [linux.$$i] =========;              \
		$(MAKE) -f mkapp.mk target_plat=linux platform=$$i;        \
	done    
    
lipo:    
	@echo ========= lipo libcalc_sdk.a =========;    
	@for dir in $(ROOT_DIR)/lib/ios;                               \
	do                                                             \
		cd $$dir;                                                  \
        echo lipo: $(addsuffix /libcalc_sdk.a, $(IOS_PLATFORMS))   \
		        to libcalc_sdk.a;                                  \
		lipo -create $(addsuffix /libcalc_sdk.a, $(IOS_PLATFORMS)) \
		        -output libcalc_sdk.a;                             \
		cd -;                                                      \
	done

clean:
	rm -rf $(ROOT_DIR)/bin/obj
	rm -rf $(ROOT_DIR)/lib/ios
	rm -rf $(ROOT_DIR)/lib/mac
	rm -rf $(ROOT_DIR)/lib/linux