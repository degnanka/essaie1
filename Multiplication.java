package essai1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;


public class Multiplication {

	public static void main(String[] args) throws IOException {
		Multiplication  client  = new Multiplication();
		client.execute();
	}
	
public void execute() throws IOException {		
		for(int i=1 ; i<=10;i++ ) {
			System.out.println("Debut de la partie " + i );
			System.out.println("Envoie d'un Paguet UDP JOUER");
			DatagramSocket socket = new DatagramSocket();
			InetSocketAddress addrClient = new InetSocketAddress("localhost",11000);
			byte[]message = new String("JOUER").getBytes();
			DatagramPacket pckClient = new DatagramPacket(message,message.length,addrClient);
			socket.send(pckClient);
			
			//attente de reponse 
			
			byte[]buff = new byte[2048];
			DatagramPacket pckServeur= new DatagramPacket(buff , buff.length);
			socket.receive(pckServeur);
			String reponse1 = new String(buff , pckServeur.getOffset(),pckServeur.getLength());
			
			
			//attente de la reponse 2
			
			byte[]buffsecond = new byte[2048];
			DatagramPacket pckServeur2= new DatagramPacket(buffsecond , buffsecond.length);
			socket.receive(pckServeur2);
			String reponse2 = new String(buffsecond , pckServeur.getOffset(),pckServeur.getLength());
			
			String necessaire1 = reponse1.substring(0,1);
			String necessaire2 = reponse2.substring(0,1);
			System.out.println("Le serveur a repondu " + necessaire1 + " et " + necessaire2 );
			int r1 = Integer.parseInt(necessaire1);
			int r2 = Integer.parseInt(necessaire2);
			int r = r1 * r2 ;
			
			//envoie de ma reponse 
			
			
			byte[]resultat = new String(Integer.toString(r) + ";").getBytes();
			DatagramPacket pckResultat = new DatagramPacket(resultat,resultat.length,addrClient);
			socket.send(pckResultat);
			System.out.println("Envoie d'un packet UDP avec " + r + ";");
			
			
			//attente de reponse 
			byte[]buffR = new byte[2048];
			DatagramPacket pckResServeur= new DatagramPacket(buffR , buffR.length);
			socket.receive(pckResServeur);
			String resultatFinal = new String(buffR , pckResServeur.getOffset(),pckResServeur.getLength());
			System.out.println("Le serveur a repondu " + resultatFinal);
			System.out.println("Fin de la partie " + i);
	
			socket.close();
			
}
		System.out.println("Fin du jeu ");
		}
	}
