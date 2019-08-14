#**********************************************************
#
# by jevstein
# 2019/8/14
#
# Note: used to compile and generate executable file
#
#**********************************************************

# 1.CONFIG
DEBUG 		:= YES
APP			:= calc_so_demo
APP_OUT		:= calc_so_demo
ROOT_DIR	:= ../../..
PROJECT_DIR := .
OUT_DIR		:= $(ROOT_DIR)/bin/mac
OBJ_DIR		:= $(ROOT_DIR)/bin/obj/mac
DEFS_DIR	:= 
INCS_DIR	:= -I$(ROOT_DIR)/include
LIBS_DIR	:= $(ROOT_DIR)/lib
LIBS		:= -ldl -lpthread
SRCS		:= $(wildcard *.cpp)

# 2.TOOL CHAIN
CXX				:= g++
COMMON_FLAGS	:= -g
CXX_FLAGS		:= $(COMMON_FLAGS)
MKDIR_P     	:= mkdir -p
CP          	:= cp
RM				:= rm
INSTALL			:= $(CP)

# 3.FILES
OBJS	:= $(addsuffix .o, $(basename $(SRCS)))
OBJS	:= $(patsubst %.o, $(OBJ_DIR)/%.o, $(OBJS))

# 4.OUTPUT
all: prog install
	$(info $(APP_OUT)\makefile -> all success!)

prog: $(OBJ_DIR)/$(APP_OUT)
	$(info $(APP_OUT)\makefile -> $(OBJ_DIR)/$(APP_OUT))

install: $(OUT_DIR)/$(APP_OUT)
	$(CP) $(ROOT_DIR)/lib/mac/libcalc_sdk.so $(OUT_DIR)/
	$(info $(APP)\makefile -> $(OUT_DIR)/$(APP_OUT))

clean:
	$(RM) -rf $(OBJ_DIR)

cleaninstall:
	$(RM) $(OUT_DIR)/$(APP_OUT)

cleanall: clean cleaninstall
	$(info $(APP)\makefile -> clean all success!)

# 5.COMMON RULE
$(OBJ_DIR)/%.o : %.cpp
	$(info creating $@ ...)
	@$(MKDIR_P) $(OBJ_DIR) ..
	$(CXX) -c $(CXXFLAGS) $(INCS_DIR) $< -o $@

$(OBJ_DIR)/$(APP_OUT): $(OBJS)
	$(info linking ...)
	$(CXX) $(CXX_FLAGS) -o $(OBJ_DIR)/$(APP_OUT) $(OBJS) $(LIBS)

$(OUT_DIR)/$(APP_OUT): $(OBJ_DIR)/$(APP_OUT)
	$(info installing ...)
	@$(MKDIR_P) $(OUT_DIR) ..
	@$(CP) $< $@

#include $(subst .cpp,.d,$(SRCS))

# 6.PHONY
.PHONY: all prog install clean cleaninstall cleanall