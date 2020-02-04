#**********************************************************
#
# by jevstein
# 2020/02/03
#
# Note: 
#
#**********************************************************

# 1.config
SRC_DIRS = a so

# 2.OUTPUT
all: lib share
	$(info all success!)

lib :
	$(info ========= make linux/libcalc_sdk.a =========)
	@for dir in a; 									\
	do 												\
		cd $$dir; $(MAKE); cd -;					\
		echo  ;										\
	done

share :
	$(info ========= make linux/libcalc_sdk.so =========)
	@for dir in so; 								\
	do 												\
		cd $$dir; $(MAKE); cd -;					\
		echo  ;										\
	done
	
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