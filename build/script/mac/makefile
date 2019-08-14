#**********************************************************
#
# by jevstein
# 2019/8/14
#
# Note: generate corresponding multiple static libraries, 
#		dynamic libraries, executable files and other ELF files.
#
#**********************************************************

### 1.CONFIG
DEBUG 		 = YES
APP_NAME     = calc_sdk
APP_OUT      = $(APP_NAME)
APP_OUTS     = lib$(APP_NAME).a lib$(APP_NAME).so
# APP_OUTS     = lib$(APP_NAME).so
ROOT_DIR	 = ../../..
PROJ_DIR     = .
OUT_DIR		 = $(ROOT_DIR)/lib/mac
OBJ_DIR      = $(ROOT_DIR)/bin/obj/mac
DEFS_DIR	 = 
INCS_DIR	 = $(ROOT_DIR)/include
SRCS_DIR     = $(ROOT_DIR)/src
LIBS_DIR	 = 
LIBS		 = 
VPATH		 = $(SRCS_DIR)
SRCS		 = $(wildcard $(SRCS_DIR)/*.cpp)

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

CXX          := g++ #-fPIC -DPIC
CPP	         := g++ #-fPIC -DPIC
CC	         := gcc #-fPIC -DPIC
AR	         := ar
ASM	         := yasm #-DPIC

# ARCH_DEF     :=
# EXTRA_CFLAGS := -m32
# EXTRA_LFLAGS := -m32 -dynamiclib -Wl, -dynamic -Wl
# EXTRA_AFLAGS := -f macho32 -m x86

IINCS_DIR     := $(addprefix -I, $(INCS_DIR))

COMMON_FLAGS := -std=c++11 -stdlib=libc++

ARFLAGS		 := crs #cru

CFLAGS		 := $(DEBUG_FLAGS)
CFLAGS		 += -fPIC -DPIC $(COMMON_FLAGS) $(IINCS_DIR)
 
ASMFLAGS	 :=
 
LDFALGS		 := -lm -ldl

### 3.REFILES
LIB_OBJ_DIR  := $(OBJ_DIR)/obj_lib
DLIB_OBJ_DIR := $(OBJ_DIR)/obj_dlib

SRCS 	     := $(notdir $(SRCS))
OBJS	     := $(addsuffix .o, $(basename $(SRCS)))
LIB_OBJS	 := $(patsubst %.o, $(LIB_OBJ_DIR)/%.o, $(OBJS))
DLIB_OBJS	 := $(patsubst %.o, $(DLIB_OBJ_DIR)/%.o, $(OBJS))
OBJS	     := $(patsubst %.o, $(OBJ_DIR)/%.o, $(OBJS))

OBJ_APP_OUTS := $(addprefix $(OBJ_DIR)/, $(APP_OUTS))
OUT_APP_OUTS := $(addprefix $(OUT_DIR)/, $(APP_OUTS))

APP_OUT      := $(OBJ_DIR)/$(APP_OUT)
LIB_OUT      := $(filter %.a, $(OBJ_APP_OUTS))
DLIB_OUT     := $(filter %.so, $(OBJ_APP_OUTS))

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

### 5.RULE
$(OUT_APP_OUTS): $(OBJ_APP_OUTS)
	$(info [$(DBG_DATE)] installing $@ ...)
	@$(MKDIR) $(OUT_DIR) ..
	@$(CP) $< $@

$(LIB_OUT): $(LIB_OBJS)
	$(info [$(DBG_DATE)] linking $@ ...)
	$(AR) $(ARFLAGS) $@ $^

$(DLIB_OUT): $(DLIB_OBJS)
	$(info [$(DBG_DATE)] linking $@ ...)
	$(CXX) -shared $(LDFALGS) -o $@ $^ $(LIBS)


# $(OBJ_DIR)/%.o : %.cpp
# 	$(info [$(DBG_DATE)] compling $@ ...)
# 	@$(MKDIR) $(OBJ_DIR) ..
# 	$(CXX) -c $(CFLAGS) $< -o $@

# $(OBJ_DIR)/%.o : %.cc
# 	$(info [$(DBG_DATE)] compling $@ ...)
# 	@$(MKDIR) $(OBJ_DIR) ..
# 	$(CXX) -c $(CFLAGS) $< -o $@ 

# $(OBJ_DIR)/%.o : %.c
# 	$(info [$(DBG_DATE)] compling $@ ...)
# 	@$(MKDIR) $(OBJ_DIR) ..
# 	$(CC) -c $(CFLAGS) $< -o $@

#lib
$(LIB_OBJ_DIR)/%.o : %.cpp
	$(info [$(DBG_DATE)] compling $@ ...)
	@$(MKDIR) $(LIB_OBJ_DIR) ..
	$(CXX) -c $(CFLAGS) $< -o $@

$(LIB_OBJ_DIR)/%.o : %.cc
	$(info [$(DBG_DATE)] compling $@ ...)
	@$(MKDIR) $(LIB_OBJ_DIR) ..
	$(CXX) -c $(CFLAGS) $< -o $@ 

$(LIB_OBJ_DIR)/%.o : %.c
	$(info [$(DBG_DATE)] compling $@ ...)
	@$(MKDIR) $(LIB_OBJ_DIR) ..
	$(CC) -c $(CFLAGS) $< -o $@

#dlib
$(DLIB_OBJ_DIR)/%.o : %.cpp
	$(info [$(DBG_DATE)] compling $@ ...)
	@$(MKDIR) $(DLIB_OBJ_DIR) ..
	$(CXX) -c $(CFLAGS) $< -o $@

$(DLIB_OBJ_DIR)/%.o : %.cc
	$(info [$(DBG_DATE)] compling $@ ...)
	@$(MKDIR) $(DLIB_OBJ_DIR) ..
	$(CXX) -c $(CFLAGS) $< -o $@ 

$(DLIB_OBJ_DIR)/%.o : %.c
	$(info [$(DBG_DATE)] compling $@ ...)
	@$(MKDIR) $(DLIB_OBJ_DIR) ..
	$(CC) -c $(CFLAGS) $< -o $@

#include $(subst .cpp,.d,$(SRCS))

# 6.PHONY
.PHONY: all prog install clean cleaninstall cleanall