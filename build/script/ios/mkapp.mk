#**********************************************************
#
# by jevstein
# 2019/08/08
#
# Note: used to compile and generate corresponding static libraries, 
#		dynamic libraries, executable files and other ELF files.
#
#**********************************************************

### 1.CONFIG
APP_NAME     = calc_sdk
APP_OUT      = $(APP_NAME)
APP_OUTS     = lib$(APP_NAME).a lib$(APP_NAME).so
# APP_OUTS     = lib$(APP_NAME).a
ROOT_DIR	 = ../../..
PROJ_DIR     = .
OUT_DIR      = $(ROOT_DIR)/lib/$(target_plat)
OBJ_DIR		 = $(ROOT_DIR)/bin/obj/$(target_plat)
DEFS_DIR	 = 
INCS_DIR	 = $(ROOT_DIR)/include
LIBS_DIR	 = 
LIBS		 = 
VPATH		 = $(ROOT_DIR)/src
SRCS		 = $(wildcard $(ROOT_DIR)/src/*.cpp)

### 2.TOOL CHAIN
MKDIR        := mkdir -p
CP           := cp
RM			 := rm
INSTALL		 := $(CP)
DBG_DATE     := $(shell date +"%F %T")
DBG_ECHO     := @echo [$(DBG_DATE)]
DBG_FLAG     := $(target_plat).$(platform)

ifeq ($(DEBUG), YES)
    DEBUG_FLAGS := -g
else
    DEBUG_FLAGS := -O3
endif

include platform.mk

COMMON_FLAGS := -std=c++11 -stdlib=libc++
ARFLAGS		 := cru
 
CFLAGS		 := $(DEBUG_FLAGS)
CFLAGS		 += $(COMMON_FLAGS) $(EXTRA_CFLAGS) -I$(INCS_DIR)
 
ASMFLAGS	 :=
ASMFLAGS	 += $(EXTRA_AFLAGS) -I$(INCS_DIR)
 
LDFALGS		 := -lm -ldl
LDFALGS		 += $(EXTRA_LFLAGS)

### 3.RECOMBINE FILES
OUT_DIR		 := $(OUT_DIR)/$(platform)
OBJ_DIR		 := $(OBJ_DIR)/$(platform)
 
SRCS 	     := $(notdir $(SRCS))
OBJS	     := $(addsuffix .o, $(basename $(SRCS)))
OBJS	     := $(patsubst %.o, $(OBJ_DIR)/%.o, $(OBJS))

OBJ_APP_OUTS := $(addprefix $(OBJ_DIR)/, $(APP_OUTS))
OUT_APP_OUTS := $(addprefix $(OUT_DIR)/, $(APP_OUTS))

APP_OUT      := $(OBJ_DIR)/$(APP_OUT)
LIB_OUT      := $(filter %.a, $(OBJ_APP_OUTS))
DLIB_OUT     := $(filter %.so, $(OBJ_APP_OUTS))
DYLIB_OUT    := $(filter %.dylib, $(OBJ_APP_OUTS))

### 4.OUTPUT
all: prog install
	$(info [$(DBG_DATE)] makefile[$(DBG_FLAG)]: $(APP_OUTS) -> all success!)

prog: $(OBJ_APP_OUTS)
	$(info [$(DBG_DATE)] makefile[$(DBG_FLAG)]: $^)

install: $(OUT_APP_OUTS)
	$(info [$(DBG_DATE)] makefile[$(DBG_FLAG)]: $^)

clean:
	$(RM) -rf $(OBJ_DIR)

cleaninstall:
	$(RM) -rf $(OUT_DIR)

cleanall: clean cleaninstall
	$(info [$(DBG_DATE)] clean -> all success!)

### 5.COMMON RULE
$(OUT_APP_OUTS): $(OBJ_APP_OUTS)
	$(info [$(DBG_DATE)] installing $@ ...)
	@$(MKDIR) $(OUT_DIR) ..
	@$(CP) $< $@

$(LIB_OUT): $(OBJS)
	$(info [$(DBG_DATE)] linking $@ ...)
	$(AR) $(ARFLAGS) $@ $^ $(LIBS)

$(DLIB_OUT): $(OBJS)
	$(info [$(DBG_DATE)] linking $@ ...)
	$(CXX) -shared $(LDFALGS) -o $@ $^ $(LIBS)

$(DYLIB_OUT): $(OBJS)
	$(info [$(DBG_DATE)] linking $@ ...)
	$(CXX) -shared $(LDFALGS) -o $@ $^ $(LIBS)

$(OBJ_DIR)/%.o : %.cpp
	$(info [$(DBG_DATE)] compling $@ ...)
	@$(MKDIR) $(OBJ_DIR) ..
	$(CXX) -c $(CFLAGS) $< -o $@

$(OBJ_DIR)/%.o : %.c
	$(info [$(DBG_DATE)] compling $@ ...)
	@$(MKDIR) $(OBJ_DIR) ..
	$(CXX) -c $(CFLAGS) $< -o $@