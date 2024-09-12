package essai1;
import java.awt.Color;
import javax.swing.JFrame;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Telecommande {

    public JFrame frame;

    public static void main(String[] args) throws Exception {
        Telecommande telecommande = new Telecommande();
        telecommande.execute(); // Commence à écouter les commandes
    }

    public Telecommande() {
        // Création de la fenêtre avec la couleur verte au départ
    	frame = new JFrame("Telecommande");
        frame.setSize(300, 300);
        frame.getContentPane().setBackground(Color.GREEN);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Méthode pour écouter les commandes UDP sur le port 7050
     */
    public void execute() throws Exception {
        DatagramSocket socket = new DatagramSocket(null);// Écoute sur le port 7050
        socket.bind(new InetSocketAddress(7050));
        byte[] buffer = new byte[256];

        while (true) {
            // Réception des données
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            // Convertit les données en string (commande reçue)
            String command = new String(buffer, packet.getOffset(), packet.getLength()).trim();

            // Affichage pour le debug
            System.out.println("Commande reçue : " + command);

            // Change la couleur en fonction de la commande
            if (command.equals("red")) {  // Comparaison correcte des chaînes
                changeColor(Color.RED);
            } else if (command.equals("green")) {
                changeColor(Color.GREEN);
            }
        }
    }

    /**
     * Méthode pour changer la couleur de la fenêtre
     */
    private void changeColor(Color color) {
        frame.getContentPane().setBackground(color);
        //frame.repaint(); // Redessine la fenêtre avec la nouvelle couleur
    }
}

