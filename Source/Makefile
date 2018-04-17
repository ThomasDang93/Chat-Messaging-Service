JC = javac
#FLAGS = -verbose
BUILD_DIR = ../Build
SOURCE_DIR = ../Source
AMQ_DIR = /Users/utdesign/Desktop/Messenger/apache-activemq-5.15.3/activemq-all-5.15.3.jar
SQL_DIR = /Users/utdesign/Desktop/Messenger/apache-activemq-5.15.3/sqlite-jdbc-3.8.11.2.jar
CP = $(SOURCE_DIR):$(BUILD_DIR):$(AMQ_DIR):$(SQL_DIR)

#Network layer targets
NW_PATH = ./network
NW_OBJ = $(NW_PATH)/Message.class\
		 $(NW_PATH)/MessageHandler.class \
		 $(NW_PATH)/NetworkLayer.class

#SQLite
SQL_PATH = ./sqlite
SQL_OBJ = $(SQL_PATH)/SQLite.class

#tracy
TRACY_PATH = ./tracy
TRACY_OBJ = $(TRACY_PATH)/MessageLogic.class \
            $(TRACY_PATH)/MessageModule.class

#macy
MACY_PATH = ./macy
MACY_OBJ = $(MACY_PATH)/MessageLogic.class \
$(MACY_PATH)/MessageModule.class

all: framework modules

framework: $(NW_OBJ) $(SQL_OBJ)
modules: $(MACY_OBJ) $(TRACY_OBJ)

%.class: %.java
	$(JC) $(FLAGS) -d $(BUILD_DIR) -cp $(CP) $<

clean:
	find ../ -name "*.class" -type f -delete
