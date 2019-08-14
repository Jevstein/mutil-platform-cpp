#**********************************************************
#
# by jevstein
# 2019/08/08
#
# Note: used to manage the configuration of different operating
#       systems and different CPU architectures. eg:
#         1.linux： ARM32、ARM64、X86_32、X86_64
#         2.mac： X86_32、X86_64
#         3.iOS： arm7、arm64、i386、x86_64
#
#**********************************************************

OS = $(shell uname)

######################### linux #########################
ifeq ($(findstring Linux, $(OS)), Linux)
    CROSS ?= 
    CXX := $(CROSS)g++ -fPIC -DPIC
    CC  := $(CROSS)gcc -fPIC -DPIC
    CPP := $(CROSS)g++ -fPIC -DPIC
    LD  := $(CROSS)ld
    AR  := $(CROSS)ar
    ASM :=	yasm -DPIC
	
    ### ARM32	
    ifeq ($(platform), arm32)
        ARCH_DEF     :=
        EXTRA_CFLAGS := -march=armv7-a -marm $(ARCH_DEF)
        EXTRA_LFLAGS := -march=armv7-a -marm 
        EXTRA_AFLAGS := -march=armv7-a $(ARCH_DEF)
        ELF_DIR      := arm32
    endif

    ### ARM64
    ifeq ($(platform), arm64)
        ARCH_DEF	 :=
        EXTRA_CFLAGS := -march=armv8-a $(ARCH_DEF)
        EXTRA_LFLAGS := -march=armv8-a  
        EXTRA_AFLAGS := -march=armv8-a $(ARCH_DEF)
        ELF_DIR		 := arm64
    endif

    ### X86_32	
    ifeq ($(platform), x86_32)
        ARCH_DEF :=
        EXTRA_CFLAGS := -m32 $(ARCH_DEF)
        EXTRA_LFLAGS := -m32 -shared
        EXTRA_AFLAGS := -m x86 $(ARCH_DEF)
        ELF_DIR      := x86m32
    endif

    ### X86_64
    ifeq ($(platform), x86_64)
        ARCH_DEF	 :=
        EXTRA_CFLAGS := -m64 $(ARCH_DEF)
        EXTRA_LFLAGS := -m64 -shared -Wl, -Bsymbolic
        EXTRA_AFLAGS := -m amd64 $(ARCH_DEF)
        ELF_DIR		 := x86m64
    endif
endif #linux: ifeq ($(findstring Linux, $(OS)), Linux)

######################### MAC/IOS #########################
ifeq ($(findstring Darwin, $(OS)), Darwin) 

    ### MAC
    ifeq ($(target_plat), mac)
        CROSS ?= 
        CXX   := $(CROSS)g++ -fPIC -DPIC
        CC	  := $(CROSS)gcc -fPIC -DPIC
        CPP	  := $(CROSS)g++ -fPIC -DPIC
        AR	  := $(CROSS)ar
        ASM	  := yasm -DPIC
    	
        ## X86_32
        ifeq ($(platform), x86_32)
            ARCH_DEF       :=
            EXTRA_CFLAGS   := -m32
            # EXTRA_LFLAGS   := -m32 -dynamiclib -Wl, -dynamic -Wl, -read_only_relocs, suppress
            EXTRA_LFLAGS   := -m32 -dynamiclib -Wl, -dynamic -Wl
            EXTRA_AFLAGS   := -f macho32 -m x86
            ELF_DIR        := mac32
        endif
    
        ## X86_64
        ifeq ($(platform), x86_64)
            ARCH_DEF       :=
            EXTRA_CFLAGS   := -m64
            EXTRA_LFLAGS   := -m64 -dynamiclib -Wl, -dynamic
            EXTRA_AFLAGS   := -f macho64 -m amd64
            ELF_DIR		   := mac64
        endif

        ## XDefault
        ifeq ($(platform), xDefault)
            ARCH_DEF       :=
            EXTRA_CFLAGS   := 
            EXTRA_LFLAGS   := 
            EXTRA_AFLAGS   := 
            ELF_DIR		   := xDefault
        endif
    endif ### MAC: ifeq ($(target_plat), mac)

    ###IOS
    ifeq ($(target_plat), ios)
        CROSS := iphone

		## ios32: armv7
        ifeq ($(platform), ios32)
            CXX := xcrun -sdk $(CROSS)os clang++
            CC  := xcrun -sdk $(CROSS)os clang++
            CPP := g++
            AR  := ar
            ASM := gas-preprocessor.pl -arch arm -as-type apple-clang --$(CC)
            
            ARCH_DEF     :=
            EXTRA_CFLAGS := -arch armv7 -mios-version-min=6.0
            EXTRA_LFLAGS := -arch armv7 -mios-version-min=6.0
            EXTRA_AFLAGS := -arch armv7 -mios-version-min=6.0
            ELF_DIR      := armv7
        endif

		## ios64: armv64
        ifeq ($(platform), ios64)
            CXX := xcrun -sdk $(CROSS)os clang++
            CC  := xcrun -sdk $(CROSS)os clang++
            CPP := g++
            AR  := ar
            ASM := gas-preprocessor.pl -arch aarch64 -as-type apple-clang --$(CC)
            
            ARCH_DEF     :=
            EXTRA_CFLAGS := -arch arm64 -mios-version-min=6.0
            EXTRA_LFLAGS := -arch arm64 -mios-version-min=6.0
            EXTRA_AFLAGS := -arch arm64 -mios-version-min=6.0
            ELF_DIR      := arm64
        endif 
    
		## ios_sim32: i386
        ifeq ($(platform), ios_sim32)
            CXX := xcrun -sdk $(CROSS)simulator clang++
            CC  := xcrun -sdk $(CROSS)simulator clang++
            CPP := g++
            AR  := ar
            ASM := yasm
            
            ARCH_DEF     :=
            EXTRA_CFLAGS := -arch i386 -mios-simulator-version-min=6.0
            # EXTRA_LFLAGS := -arch i386 -mios-simulator-version-min=6.0 -Wl, -Bsymbolic-functions -read_only_relocs suppress
            EXTRA_LFLAGS := -arch i386 -mios-simulator-version-min=6.0 -Wl, -Bsymbolic-functions
            EXTRA_AFLAGS := -f macho32 -m x86
            ELF_DIR      := i386
        endif 
    
		## ios_sim64: x86_64
        ifeq ($(platform), ios_sim64)
            CXX := xcrun -sdk $(CROSS)simulator clang++
            CC  := xcrun -sdk $(CROSS)simulator clang++
            CPP := g++
            AR  := ar
            ASM := yasm
            
            ARCH_DEF     :=
            EXTRA_CFLAGS := -arch x86_64 -mios-simulator-version-min=6.0
            EXTRA_LFLAGS := -arch x86_64 -mios-simulator-version-min=6.0
            EXTRA_AFLAGS := -f macho64 -m amd64
            ELF_DIR      := x86_64
        endif 
    endif ### IOS: ifeq ($(target_plat), ios)
endif# MAC/IOS: ifeq ($(findstring Darwin, $(OS)), Darwin) 