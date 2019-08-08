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
# include app.mk

DEBUG 		     = NO
IOS_PLATFORMS    = ios32 ios64 ios_sim32 ios_sim64
MAC_PLATFORMS    = x86_32 x86_64
LINUX_PLATFORMS  = arm32、arm64、X86_32、X86_64

all: ios mac
	$(info all success!)
	
ios:
	@for i in $(IOS_PLATFORMS);                         \
	do                                                  \
		echo ========= compile [ios.$$i] =========;     \
		$(MAKE) -f app.mk target_plat=ios platform=$$i; \
	done

mac:
	@for i in $(MAC_PLATFORMS);                         \
	do                                                  \
		echo ========= compile [mac.$$i] =========;     \
		$(MAKE) -f app.mk target_plat=mac platform=$$i; \
	done

linux:
	@for i in $(LINUX_PLATFORMS);                        \
	do                                                  \
		echo ========= compile [linux.$$i] =========;     \
		$(MAKE) -f app.mk target_plat=linux platform=$$i; \
	done

clean:
	rm -rf ../../../bin/obj
	rm -rf ../../../lib/ios
	rm -rf ../../../lib/mac
	rm -rf ../../../lib/linux