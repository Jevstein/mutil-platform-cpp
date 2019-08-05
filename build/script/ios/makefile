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
OUT_DIR		:= $(ROOT_DIR)/lib/ios
DEFS_DIR	:= 
INCS_DIR	:= -I$(ROOT_DIR)/include
LIBS_DIR	:= 
LIBS		:= 
VPATH		:= $(ROOT_DIR)/src
SRCS		:= $(wildcard $(ROOT_DIR)/src/*.cpp)
platform	:= ios_i386

# 2.TOOL CHAIN
# CXX				:= clang++
COMMON_FLAGS	:= 
# CXX_FLAGS		:= $(COMMON_FLAGS)
MKDIR_P     	:= mkdir -p
CP          	:= cp
RM				:= rm
AR				:= ar rcs
INSTALL			:= $(CP)

ifeq ( $(findstring ios_i386, $(platform)), ios_i386)
    CXX = xcrun -sdk iphonesimulator clang
    CXX_FLAGS += $(COMMON_FLAGS) -Wall -O3 -arch i386 -mios-simulator-version-min=6.0  -DIOS
    LDFLAGS += -arch i386 -mios-simulator-version-min=6.0 -Wl, -Bsymbolic-functions
else ifeq ( $(findstring ios64, $(platform)), ios64)
    CXX = xcrun -sdk iphoneos clang
    CXX_FLAGS += $(COMMON_FLAGS) -Wall -O3 -arch arm64 -mios-version-min=6.0  
    LDFLAGS += -arch arm64 -mios-version-min=6.0 -Wl, -Bsymbolic-functions
endif

ifeq ($(DEBUG), YES)
    OBJ_DIR		:= Debug
else
    OBJ_DIR		:= Release
endif

# 3.FILES
SRCS 	:= $(notdir $(SRCS))
OBJS	:= $(addsuffix .o, $(basename $(SRCS)))
OBJS	:= $(patsubst %.o, $(OBJ_DIR)/%.o, $(OBJS))

# 4.OUTPUT
all: prog install
	$(info $(APP_OUT)\makefile -> all success!)

prog: $(OBJ_DIR)/$(APP_OUT)
	$(info $(APP_OUT)\makefile -> $(OBJ_DIR)/$(APP_OUT))

install: $(OUT_DIR)/$(APP_OUT)
 	$(info $(APP)\makefile -> $(OUT_DIR)/$(APP_OUT))

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