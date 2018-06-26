# Chat-Messaging-Service
This is a chat service in Java that enables text communication between users through a command line client. This program utilizes apache-activemq-5.15.3 as the network architecture for sending and receiving messages. ActiveMQ is a message broker written in Java together with Java Messaging Service API that offers asynchronous communications and can support multiple clients on a server. Each client will have its own queue to process incoming messages. Each client will also have its own personal SQLite database that stores all transmitting/receiving messagess for users to access. In this project scope we will represent two clients as 'TRACY' and 'MACY'. Both TRACY and MACY send messages to each other in a Unix Command Line. **Note: Java 8 or above is required to run this project.**

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

5. Go find the Makefile located under the Source directory. 

![alt text](https://github.com/ThomasDang93/Chat-Messaging-Service/blob/master/images/make.png)

6. Be sure to define the path to **activemq-all-5.15.3.jar**, **sqlite-jdbc-3.8.11.2.jar** , Source folder, and Build folder within the make file.

![alt text](https://github.com/ThomasDang93/Chat-Messaging-Service/blob/master/images/make_detail.png)

For a closer look at the code.
```
BUILD_DIR = /mnt/c/Users/dangt/Desktop/Messenger/Build  #Path to your Build folder
SOURCE_DIR = /mnt/c/Users/dangt/Desktop/Messenger/Source  #Path to your Source folder
AMQ_DIR = /mnt/c/Users/dangt/Desktop/Messenger/apache-activemq-5.15.3/activemq-all-5.15.3.jar #Path to your activemq-all-5.15.3.jar
SQL_DIR = /mnt/c/Users/dangt/Desktop/Messenger/apache-activemq-5.15.3/sqlite-jdbc-3.8.11.2.jar #Path to your sqlite-jdbc-3.8.11.2.jar
```

7. Open up a Unix command line terminal and go to the project directory containing the Makefile.
8. To compile the program, type *make* in the command prompt. This will produce .class files, which will be stored under the Build directory.
9. Go into Build directory and open the file **run.sh**. Be sure you include the path to **activemq-all-5.15.3.jar** and **sqlite-jdbc-3.8.11.2.jar** in the run script. Also be sure to include the path to the activemq file under the bin folder.
![alt text](https://github.com/ThomasDang93/Chat-Messaging-Service/blob/master/images/run.png)


10. Go back to the Unix command line terminal and type *./run.sh AMQ* to run the activemq server. Once that is done, you can now run our two client modules, TRACY and MACY. 

11 Open two more Unix terminal sessions. In each of those sessions, go into the Build folder and type *./run.sh TRACY* in one session and *./run.sh MACY* in the other session.

12. You can now start chatting with the TRACY and MACY clients now. Here is an example of what the chat service should look like.

![alt text](https://github.com/ThomasDang93/Chat-Messaging-Service/blob/master/images/program_example.png)
