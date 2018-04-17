#!/bin/bash

JAVA_CP=..:.:${HOME}/Desktop/Messenger/apache-activemq-5.15.3/activemq-all-5.15.3.jar:/Users/utdesign/Desktop/Messenger/apache-activemq-5.15.3/sqlite-jdbc-3.8.11.2.jar
RUN_A_MQ=${HOME}/Desktop/Messenger/apache-activemq-5.15.3/bin/activemq
RUN_MACY=macy.MessageModule
RUN_TRACY=tracy.MessageModule

#Stop AMQ server and modules
if [ "$1" = "STOP" ]; then
	$RUN_A_MQ stop
	kill `pgrep -f java`
	exit
fi

for module in "$@"; do
	#Begin JMS message broker
	if [ "$module" = "AMQ" ]; then
		$RUN_A_MQ start
		sleep 3s
	elif [ "$module" = "MACY" ]; then
		temp="$RUN_MACY"
	elif [ "$module" = "TRACY" ]; then
		temp="$RUN_TRACY"
	else
		echo "Not a valid module: $module"
		continue
	fi

	java -cp "$JAVA_CP" "$temp"
done
