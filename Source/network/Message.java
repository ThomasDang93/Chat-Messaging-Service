package network;
import java.io.Serializable;

public class Message implements Serializable {
	private String origin;
	private Serializable payload;

	public
	Message(String o, Serializable p) {
		this.origin = o;
		this.payload = p;
	}

	public String
	getOrigin() {
		return this.origin;
	}

	public Serializable
	getPayload() {
		return this.payload;
	}
}

