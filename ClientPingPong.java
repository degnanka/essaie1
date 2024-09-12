package essai1;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class ClientPingPong {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ClientPingPong client=new ClientPingPong();
		client.execute();
	}
	
	public void execute() throws IOException {
		
		
		for(int i=1 ; i<=10;i++ ) {
			System.out.println("Debut de la partie " + i );
			System.out.println("Envoie d'un Paguet UDP JOUER");
			DatagramSocket socket = new DatagramSocket();
			InetSocketAddress addrClient = new InetSocketAddress("localhost",29000);
			byte[]message = new String("JOUER").getBytes();
			DatagramPacket pckClient = new DatagramPacket(message,message.length,addrClient);
			socket.send(pckClient);
			
			//attente de reponse 
			
			byte[]buff = new byte[2048];
			DatagramPacket pckServeur= new DatagramPacket(buff , buff.length);
			socket.receive(pckServeur);
			String reponse = new String(buff , pckServeur.getOffset(),pckServeur.getLength());
			System.out.println("Le serveur a repondu " + reponse);
			
			
			//envoie de ma reponse 
			
			if(reponse.equals(new String("PING"))) {
			byte[]resultat = new String("PONG").getBytes();
			DatagramPacket pckResultat = new DatagramPacket(resultat,resultat.length,addrClient);
			socket.send(pckResultat);
			System.out.println("Envoie d'un packet UDP avec PONG");
			
			//attente de reponse 
			byte[]buffR = new byte[2048];
			DatagramPacket pckResServeur= new DatagramPacket(buffR , buffR.length);
			socket.receive(pckResServeur);
			String resultatFinal = new String(buffR , pckResServeur.getOffset(),pckResServeur.getLength());
			System.out.println("Le serveur a repondu " + resultatFinal);
			System.out.println("Envoie d'un packet UDP avec PONG");
		}
			else {
				//envoie de ma reponse 
				
			
				byte[]resultatf = new String("PING").getBytes();
				DatagramPacket pckResultatf = new DatagramPacket(resultatf,resultatf.length,addrClient);
				socket.send(pckResultatf);
				System.out.println("Envoie d'un packet UDP avec PING");
				
				//attente de reponse 
				byte[]buffRf = new byte[2048];
				DatagramPacket pckResServeurf= new DatagramPacket(buffRf , buffRf.length);
				socket.receive(pckResServeurf);
				String resultatFinale = new String(buffRf , pckResServeurf.getOffset(),pckResServeurf.getLength());
				System.out.println("Le serveur a repondu " + resultatFinale);			
				
			
			System.out.println("Fin de la partie " + i);
	}
			socket.close();
			
}
		System.out.println("fin du jeu");
		}
	}
