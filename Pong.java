package essai1;

import java.awt.Color;
import javax.swing.JFrame;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Pong {

	private JFrame frame;

	public static void main(String[] args) throws Exception {
		Pong pong = new Pong();
		pong.start();
	}

	public Pong() {
		frame = new JFrame("Pong");
		frame.setSize(300, 300);
		frame.getContentPane().setBackground(Color.GREEN);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void start() throws Exception {
		DatagramSocket socket = new DatagramSocket(4002); // Port changé à 5002
		byte[] buffer = new byte[256];

		while (true) {
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			socket.receive(packet);
			String message = new String(packet.getData(), packet.getOffset(), packet.getLength()).trim();

			if (message.equals("red")) {
				System.out.println("Message reçu de Ping: " + message);
				changeColor(Color.RED);
				Thread.sleep(1000);
				changeColor(Color.GREEN);

				DatagramSocket sendSocket = new DatagramSocket();
				String response = "red";
				DatagramPacket responsePacket = new DatagramPacket(response.getBytes(), response.length(),new InetSocketAddress("localhost", 4001)); // Port changé à 5001
				sendSocket.send(responsePacket);
				sendSocket.close();
			}
		}
	}

	private void changeColor(Color color) {
		frame.getContentPane().setBackground(color);
		frame.repaint();
	}
}
