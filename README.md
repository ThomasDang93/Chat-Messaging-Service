# Chat-Messaging-Service
This is a chat service in Java that enables text communication between users through a command line client. This program utilizes apache-activemq-5.15.3 as the network architecture for sending and receiving messages. ActiveMQ is a message broker written in Java together with Java Messaging Service API that offers asynchronous communications and can support multiple clients or server. Each client will have its own queue to process incoming messages. Also each client will have its own personal SQLite database that stores all transmitting/receiving messagess for users to access.

1. To run this program, you will need to download the dependencies sqlite-jdbc-3.8.11.2.jar and activemq-all-5.15.3. 
2. Include both files somewhere in the project directory.
3. Go find the env file from activemq folder and set the package directory towards the Build folder. Also include NetworkLayer.Message as    a serializable package.
3. Go find the make file located under the Source directory. 
4. Be sure to define the path to activemq-all-5.15.3.jar and sqlite-jdbc-3.8.11.2.jar within the make file.
5. Open up the command line terminal and go to the project directory containing the make file.
6. To compile the program, type 'make' and all .class files will be stored under the Build directory.
7. Go into Build directory and be sure you include the path to activemq-all-5.15.3.jar and sqlite-jdbc-3.8.11.2.jar in the run script.
8. Type './run.sh AMQ' to run the activemq server. Once that is done, you can now run two client modules with './run.sh TRACY' or './run.sh MACY'
9. You can now star chatting with TRACY or MACY now.
