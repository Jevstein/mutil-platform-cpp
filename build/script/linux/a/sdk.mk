#**********************************************************
#
# by jevstein
# 2020/02/03
#
# Note: 
#
#**********************************************************

### 1.CONFIG
DEBUG		 = YES
APP_NAME     = calc_sdk
APP_OUT      = $(APP_NAME)
APP_OUTS     = lib$(APP_NAME).a
ROOT_DIR	 = ../../../..
OUT_DIR      = $(ROOT_DIR)/lib/linux/a
OBJ_DIR		 = $(ROOT_DIR)/bin/obj/linux/a
INCS_DIR	 = $(ROOT_DIR)/include
LIBS_DIR	 = 
LIBS		 = 
VPATH		 = $(ROOT_DIR)/src
SRCS		 = $(wildcard $(ROOT_DIR)/src/*.cpp)

### 2.TOOL CHAIN
MKDIR        := mkdir -p
CP           := cp
CC           := g++
AR           := ar
LD  		 := ld
RM			 := rm
INSTALL		 := $(CP)
DBG_DATE     := $(shell date +"%F %T")
DBG_ECHO     := @echo [$(DBG_DATE)]
DBG_FLAG     := 

ifeq ($(DEBUG), YES)
    DEBUG_FLAGS := -g
else
    DEBUG_FLAGS := -O3
endif

ARFLAGS		 := -rs
CFLAGS		 := $(DEBUG_FLAGS)
CFLAGS		 += $(EXTRA_CFLAGS) -I$(INCS_DIR)
LDFALGS		 :=
LDFALGS		 +=

### 3.RECOMBINE FILES
SRCS 	     := $(notdir $(SRCS))
OBJS	     := $(addsuffix .o, $(basename $(SRCS)))
OBJS	     := $(patsubst %.o, $(OBJ_DIR)/%.o, $(OBJS))

OBJ_APP_OUTS := $(addprefix $(OBJ_DIR)/, $(APP_OUTS))
OUT_APP_OUTS := $(addprefix $(OUT_DIR)/, $(APP_OUTS))

APP_OUT      := $(OBJ_DIR)/$(APP_OUT)
LIB_OUT      := $(filter %.a, $(OBJ_APP_OUTS))
DLIB_OUT     := $(filter %.so, $(OBJ_APP_OUTS))

### 4.OUTPUT
all: prog install
	$(info [$(DBG_DATE)] make$(DBG_FLAG): $(APP_OUTS) -> all success!)

prog: $(OBJ_APP_OUTS)
	$(info [$(DBG_DATE)] make$(DBG_FLAG): $^)

install: $(OUT_APP_OUTS)
	$(info [$(DBG_DATE)] make$(DBG_FLAG): $^)

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

$(APP_OUT): $(OBJS)
	$(info [$(DBG_DATE)] linking $@ ...)
	$(CC) $(CFLAGS) $^ $(LIBS) -o $@

$(LIB_OUT): $(OBJS)
	$(info [$(DBG_DATE)] linking $@ ...)
	$(AR) $(ARFLAGS) $@ $^ $(LIBS)

$(DLIB_OUT): $(OBJS)
	$(info [$(DBG_DATE)] linking $@ ...)
	$(CC) -shared $(CFLAGS) $^ $(LIBS) -o $@

$(OBJ_DIR)/%.o : %.cpp
	$(info [$(DBG_DATE)] compling $@ ...)
	@$(MKDIR) $(OBJ_DIR) ..
	$(CC) -c $(CFLAGS) $^ -o $@

# 6.PHONY
.PHONY: all prog install clean cleaninstall cleanall