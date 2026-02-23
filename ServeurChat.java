import java.io.*;
import java.net.*;
import java.util.*;

public class ServeurChat {

    private static List<PrintWriter> clients = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Serveur démarré sur le port 5000...");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Nouveau client connecté : " );

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            clients.add(out);

            new Thread(new ClientHandler(socket, out)).start();
        }
    }

    public static void broadcast(String message) {

        //  Le serveur affiche les messages en direct
        System.out.println(message);

        for (PrintWriter client : clients) {
            client.println(message);
        }
    }

    public static void removeClient(PrintWriter writer) {
        clients.remove(writer);
    }
}
