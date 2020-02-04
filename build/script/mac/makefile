#**********************************************************
#
# by jevstein
# 2020/02/03
#
# Note: 
#
#**********************************************************

# 1.config
SRC_DIRS = a f

# 2.OUTPUT
all: lib frmwk
	$(info all success!)

lib :
	$(info ========= make libcalc_sdk.a(in ios or mac) =========)
	@for dir in a; 									\
	do 												\
		cd $$dir; $(MAKE); cd -;					\
		echo  ;										\
	done

frmwk :
	$(info ========= make calc_sdk.framework(in ios) =========)
	$(info ignore...)
	
clean :
	@for dir in $(SRC_DIRS); 						\
	do 												\
		echo ========= clean $$dir =========;       \
		cd $$dir; $(MAKE) clean; cd -;				\
		echo  ;										\
	done

cleanall :
	@for dir in $(SRC_DIRS); 						\
	do 												\
		echo ========= clean all $$dir =========;   \
		cd $$dir; $(MAKE) cleanall; cd -;			\
		echo  ;										\
	done

# 3.PHONY
.PHONY : all prog clean cleanall