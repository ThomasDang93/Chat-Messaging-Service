package network;

import javax.jms.*;
import javax.naming.*;
import java.util.HashMap;
import java.io.*;

import org.apache.activemq.*;

/* Network Layer Class 
	Wraps all JMS functionality.

	Implements MessageListener for asynchronous messaging. */
public class NetworkLayer implements MessageListener {
	private QueueConnection qConnection;
	private QueueSession qSession;
	private Queue consumer = null;

	// producer is a mapping of the ActiveMQ queue name to the Queue object.
	// an instance of NetworLayer can provide messages to any Queue in the map.
	private HashMap<String, Queue> producer = null;

	// messageHandler is used to supply functionality to the onMessage method.
	private MessageHandler messageHandler = null;

	/* Constructor 
		queueCF - The connection factory jndi name in the ActiveMQ server.
		consName - The queue jndi name that this instance will consume from.
		prodNames - All queue jndi names that this instance will produce to.
		msgH - The message handler used to process messages consumed by this. */
	public
	NetworkLayer(String queueCF, String consName, String[] prodNames,
	MessageHandler msgH) throws Exception {
		try {
			// Create connection
			Context ctx = new InitialContext();
			ActiveMQConnectionFactory qFactory =
				(ActiveMQConnectionFactory)ctx.lookup(queueCF);
			qFactory.setTrustAllPackages(true);
			qConnection = qFactory.createQueueConnection();

			// Create Session
			qSession =
				qConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

			// Add produce-to queues to HashMap
			producer = new HashMap<String, Queue>();
			for(String s : prodNames) {
				Queue p = (Queue)ctx.lookup(s);
				producer.put(s, p);
			}

			// Connect to consumer queue and start connection
			if(consName != null)
				consumer = (Queue)ctx.lookup(consName);
			qConnection.start();

			// Set listener for messages in consumer queue
			if(consumer != null) {
				MessageConsumer mc = qSession.createReceiver(consumer);
				mc.setMessageListener(this);

				// Set message handler
				messageHandler = msgH;
			}
		}
		catch(JMSException | NamingException ex) {
			throw (Exception)ex;
		}
	}

	/* onMessage
		Implementation of MessageListener interface method.
		Called by ActiveMQ server and runs in thread spawned by the server.   
		msg - the message being consumed by this. */
	public void
	onMessage(javax.jms.Message msg) {
		try {
			ObjectMessage m = (ObjectMessage)msg;
			Message nwlMsg = (Message)m.getObject();
			messageHandler.process(nwlMsg);
		}
		catch(JMSException jmse) {
			jmse.printStackTrace();
		}
	}

	/*sendMessage
		msg - The message object to pass to the destination.
		destination - The jndi name of the queue to produce this message to.
		Returns true if the message sent successfully, false otherwise. */
	public boolean
	sendMessage(Serializable msg, String destination) {
		boolean retVal = true;

		try {
			// Create object message using msg
			ObjectMessage omsg = qSession.createObjectMessage();
			omsg.setObject(msg);

			// Create the sender and send the message
			QueueSender qSender = qSession.createSender(producer.get(destination));
			qSender.send(omsg);
		}
		catch(JMSException jmse) {
			System.err.println("Error sending message.");
			retVal = false;
		}

		return retVal;
	}
}

