package monpackage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;

public class ClientUdp {

	private int port;
	private String host;
	DatagramSocket socket;
	InetSocketAddress adrDest;
	
	ClientUdp(String host, int port) throws IOException {
		this.host = host;
		this.port = port;
		open();
	}

	public void open() throws IOException
	{
		System.out.println("Demarrage du client ...");
		//Creation de la socket
		socket = new DatagramSocket();
		// Creation et envoi du message
		adrDest = new InetSocketAddress(host, port);
		socket.setSoTimeout(3000);
	}
	
	public boolean send(String msg) throws IOException {
		byte[] bufE = new String(msg).getBytes();
		DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
		socket.send(dpE);
		byte[] bufR = new byte[2048];
		DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
		try {
			socket.receive(dpR);
			String message = new String(bufR, dpR.getOffset(), dpR.getLength());
			if(message.contains("ACK")) {
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(SocketTimeoutException ste) {
			return false;
		}
	}
	
	public void close() {
		socket.close();
		System.out.println("Arret du client .");
	}

}
