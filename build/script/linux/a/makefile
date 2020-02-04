#**********************************************************
#
# by jevstein
# 2020/02/03
#
# Note: 
#
#**********************************************************

# 1.config
SRC_MKS = sdk.mk demo.mk

# 2.OUTPUT
all: prog
	$(info all success!)

prog :
	@for mk in $(SRC_MKS); 					\
	do 										\
		$(MAKE) -f $$mk; 					\
		echo  ;								\
	done
	
clean :
	@for mk in $(SRC_MKS); 					\
	do 										\
		$(MAKE) clean -f $$mk; 				\
		echo  ;								\
	done

cleanall :
	@for mk in $(SRC_MKS); 					\
	do 										\
		$(MAKE) cleanall -f $$mk; 			\
		echo  ;								\
	done

# 3.PHONY
.PHONY : all prog clean cleanall