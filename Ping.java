package essai1;

import java.awt.Color;
import javax.swing.JFrame;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Ping {

	private JFrame frame;

	public static void main(String[] args) throws Exception {
		Ping ping = new Ping();
		ping.start();
	}

	public Ping() {
		frame = new JFrame("Ping");
		frame.setSize(300, 300);
		frame.getContentPane().setBackground(Color.GREEN);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void start() throws Exception {
		DatagramSocket socket = new DatagramSocket(4001); // Port changé à 5001
		byte[] buffer = new byte[256];

		while (true) {
			changeColor(Color.RED);
			Thread.sleep(1000);
			changeColor(Color.GREEN);

			DatagramSocket sendSocket = new DatagramSocket();
			InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 4002); // Port changé à 5002
			byte[] bufE = new String("red").getBytes();

			DatagramPacket packet = new DatagramPacket(bufE, bufE.length, adrDest);
			sendSocket.send(packet);
			sendSocket.close();

			// Attend la réponse de Pong
			DatagramPacket response = new DatagramPacket(buffer, buffer.length);
			socket.receive(response);
			String receivedMessage = new String(buffer, response.getOffset(), response.getLength()).trim();

			// Si Pong envoie "red", recommence la boucle
			if (receivedMessage.equals("red")) {
				System.out.println("Message reçu de Pong: " + receivedMessage);
				changeColor(Color.RED);
				Thread.sleep(1000);
				changeColor(Color.GREEN);
			}
		}
	}

	private void changeColor(Color color) {
		frame.getContentPane().setBackground(color);
		frame.repaint();
	}
}
