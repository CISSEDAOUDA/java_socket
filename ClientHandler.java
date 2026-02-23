import java.io.*;
import java.net.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ClientHandler implements Runnable {

    private Socket socket;
    private PrintWriter out;

    public ClientHandler(Socket socket, PrintWriter out) {
        this.socket = socket;
        this.out = out;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            String message;
            while ((message = in.readLine()) != null) {

                String time = LocalTime.now()
                        .format(DateTimeFormatter.ofPattern("HH:mm"));

                String formattedMessage = "[" + time + "] " + message;

                ServeurChat.broadcast(formattedMessage);
            }

        } catch (Exception e) {
            System.out.println("Client déconnecté.");
        } finally {
            ServeurChat.removeClient(out);
        }
    }
}
