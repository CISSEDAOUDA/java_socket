import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class ClientChat {

    private static JTextPane messagePane;
    private static PrintWriter out;
    private static String username;

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}

        JFrame frame = new JFrame();
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        username = JOptionPane.showInputDialog(frame, "Entrez votre nom :");
        frame.setTitle("Chat - " + username);

        messagePane = new JTextPane();
        messagePane.setEditable(false);
        messagePane.setBackground(new Color(240, 240, 240));
        messagePane.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(messagePane);

        JTextField messageField = new JTextField();
        messageField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JButton sendButton = new JButton("Envoyer");
        sendButton.setBackground(new Color(0, 120, 215));
        sendButton.setForeground(Color.BLUE);
        sendButton.setFocusPainted(false);

        JPanel bottomPanel = new JPanel(new BorderLayout(10,10));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        bottomPanel.add(messageField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

        try {
            Socket socket = new Socket("localhost", 5000);

            out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            // Thread réception
            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        messagePane.setText(messagePane.getText() + msg + "\n");
                    }
                } catch (Exception e) {
                    messagePane.setText(messagePane.getText() + "\nConnexion perdue.\n");
                }
            }).start();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Erreur de connexion au serveur !");
        }

        sendButton.addActionListener(e -> {
            String message = messageField.getText().trim();
            if (!message.isEmpty()) {
                out.println(username + ": " + message);
                messageField.setText("");
            }
        });

        messageField.addActionListener(e -> sendButton.doClick());
    }
}
