#**********************************************************
#
# by jevstein
# 2019/08/05
#
# DEBUG	: DEBUG can be set to YES to include debug info, or NO otherwise(RELEASE)
#		  you can also use like this: make DEBUG:=NO
#**********************************************************

# 1.CONFIG
DEBUG 		:= YES
APP			:= libcalc_sdk
APP_OUT		:= libcalc_sdk.a
ROOT_DIR	:= ../../..
PROJECT_DIR := .
OUT_DIR		:= $(ROOT_DIR)/lib/mac
OBJ_DIR		:= obj
DEFS_DIR	:= 
INCS_DIR	:= -I$(ROOT_DIR)/include
LIBS_DIR	:= 
LIBS		:= 
VPATH		:= $(ROOT_DIR)/src
SRCS		:= $(wildcard $(ROOT_DIR)/src/*.cpp)
PLATFORMS	:= mac32 mac64
PLATFORM	:= mac32

# 2.TOOL CHAIN
# CXX				:= clang++
LD              := ld
COMMON_FLAGS	:= 
# CXX_FLAGS		:= $(COMMON_FLAGS)
MKDIR_P     	:= mkdir -p
CP          	:= cp
RM				:= rm
AR				:= ar rcs
INSTALL			:= $(CP)

ifeq ( $(findstring mac32, $(platform)), mac32)
    CXX = g++
    CXX_FLAGS += -fPIC -arch i386 -m32
    LDFLAGS += -Wl, -Bsymbolic-functions
    LDFLAGS += -m32 -read_only_relocs suppress
else ifeq ( $(findstring mac64, $(platform)), mac64)
    CXX = g++
    CXX_FLAGS += -fPIC -arch x86_64 -m64
    LDFLAGS += -Wl, -Bsymbolic-functions -m64
    LDFLAGS += -m64 -read_only_relocs suppress
endif

# ifeq ($(DEBUG), YES)
#     OBJ_DIR		:= Debug
#     DEBUG_FLAGS   := -G
# else
#     OBJ_DIR		:= Release
#     DEBUG_FLAGS   := -O3
# endif

# 3.FILES
SRCS 	:= $(notdir $(SRCS))
OBJS	:= $(addsuffix .o, $(basename $(SRCS)))
OBJS	:= $(patsubst %.o, $(OBJ_DIR)/%.o, $(OBJS))

# 4.OUTPUT
all: prog install
	$(info $(APP_OUT)\makefile[$(PLATFORM)] -> all success!)

prog: $(OBJ_DIR)/$(APP_OUT)
	$(info $(APP_OUT)\makefile[$(PLATFORM)] -> $(OBJ_DIR)/$(APP_OUT))

install: $(OUT_DIR)/$(APP_OUT)
 	$(info $(APP)\makefile[$(PLATFORM)] -> $(OUT_DIR)/$(APP_OUT))

clean:
	$(RM) -rf $(OBJ_DIR)

cleaninstall:
	$(RM) $(OUT_DIR)/$(APP_OUT)

cleanall: clean cleaninstall
	$(info $(APP)\makefile -> clean all success!)

# 5.common rule
$(OBJ_DIR)/%.o : %.cpp
	$(info creating $@ ...)
	@$(MKDIR_P) $(OBJ_DIR) ..
	$(CXX) -c $(CXXFLAGS) $(INCS_DIR) $< -o $@

$(OBJ_DIR)/$(APP_OUT): $(OBJS)
	$(info linking ...)
	$(AR) $@ $(OBJS) $(LIBS)

$(OUT_DIR)/$(APP_OUT): $(OBJ_DIR)/$(APP_OUT)
	$(info installing ...)
	@$(MKDIR_P) $(OUT_DIR) ..
	@$(CP) $< $@

#include $(subst .cpp,.d,$(SRCS))

# 6.PHONY
.PHONY: all prog install clean cleaninstall cleanall