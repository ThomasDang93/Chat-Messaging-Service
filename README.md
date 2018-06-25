# Chat-Messaging-Service
This is a chat service in Java that enables text communication between users through a command line client. This program utilizes apache-activemq-5.15.3 as the network architecture for sending and receiving messages. ActiveMQ is a message broker written in Java together with Java Messaging Service API that offers asynchronous communications and can support multiple clients on a server. Each client will have its own queue to process incoming messages. Each client will also have its own personal SQLite database that stores all transmitting/receiving messagess for users to access.

1. To run this program, you will need to download SQLite for database and ActiveMQ for network platform.
    
    - sqlite-jdbc-3.8.11.2.jar https://bitbucket.org/xerial/sqlite-jdbc/downloads/

    - activemq-all-5.15.3 http://activemq.apache.org/activemq-5153-release.html

2. Include both files somewhere in the project directory.

![alt text](https://github.com/ThomasDang93/Chat-Messaging-Service/blob/master/images/dependencies_install.png)

3. Find the env file in the directory .../apache-activemq-5.15.3/bin/env

![alt text](https://github.com/ThomasDang93/Chat-Messaging-Service/blob/master/images/env.png)

4. Set the package directory towards the Build folder. Also include NetworkLayer.Message as a serializable package.

![alt text](https://github.com/ThomasDang93/Chat-Messaging-Service/blob/master/images/env_detail.png)
For a closer look at the code.
```
ACTIVEMQ_OPTS_MEMORY="-Xms64M -Xmx1G"
SER_PKG_DIR="/mnt/c/Users/dangt/Desktop/Messenger/Build" #This is the Build directory that you have to specify for YOUR MACHINE

if [ -z "$ACTIVEMQ_OPTS" ] ; then
    ACTIVEMQ_OPTS="$ACTIVEMQ_OPTS_MEMORY -Djava.util.logging.config.file=logging.properties -Djava.security.auth.login.config=$ACTIVEMQ_CONF/login.config -Dorg.apache.activemq.SERIALIZABLE_PACKAGES=java.lang,javax.security,java.util,org.apache.activemq,$SER_PKG_DIR/NetworkLayer.Message"
fi
```

3. Go find the Makefile located under the Source directory. 

![alt text](https://github.com/ThomasDang93/Chat-Messaging-Service/blob/master/images/make.png)

4. Be sure to define the path to activemq-all-5.15.3.jar, sqlite-jdbc-3.8.11.2.jar, Source folder, and Build folder within the make file.

![alt text](https://github.com/ThomasDang93/Chat-Messaging-Service/blob/master/images/make_detail.png)

For a closer look at the code.
```
BUILD_DIR = /mnt/c/Users/dangt/Desktop/Messenger/Build  #Path to your Build folder
SOURCE_DIR = /mnt/c/Users/dangt/Desktop/Messenger/Source  #Path to your Source folder
AMQ_DIR = /mnt/c/Users/dangt/Desktop/Messenger/apache-activemq-5.15.3/activemq-all-5.15.3.jar #Path to your activemq-all-5.15.3.jar
SQL_DIR = /mnt/c/Users/dangt/Desktop/Messenger/apache-activemq-5.15.3/sqlite-jdbc-3.8.11.2.jar #Path to your sqlite-jdbc-3.8.11.2.jar
```

5. Open up the command line terminal and go to the project directory containing the make file.
6. To compile the program, type 'make' and all .class files will be stored under the Build directory.
7. Go into Build directory and be sure you include the path to activemq-all-5.15.3.jar and sqlite-jdbc-3.8.11.2.jar in the run script.
8. Type './run.sh AMQ' to run the activemq server. Once that is done, you can now run two client modules with './run.sh TRACY' or './run.sh MACY'
9. You can now star chatting with TRACY or MACY now.
