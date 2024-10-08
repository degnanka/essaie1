package monpackage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class ServerUdp {
	private int port;
	DatagramSocket socket;
	InetSocketAddress adrDest;
	
	ServerUdp(int port) throws IOException {
		this.port = port;
		open();
	}
	
	public void open() throws IOException
	{
		System.out.println("Demarrage du serveur");
		socket = new DatagramSocket(null);
		socket.bind(new InetSocketAddress(port));
	}

	public boolean waitForHand() throws IOException {
		byte[] bufR = new byte[2048];
		DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
		socket.receive(dpR);
		String message = new String(bufR, dpR.getOffset(), dpR.getLength());
		if(message.contains("OK")) {
			byte[] bufE = new String("ACK").getBytes();
			DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, new InetSocketAddress(dpR.getAddress().getHostName(), dpR.getPort()));
			socket.send(dpE);
			return true;
		}
		else {
			return false;
		}
	}	
	public void close() {
		socket.close();
		System.out.println("Arret du serveur .");		
	}
	
}
