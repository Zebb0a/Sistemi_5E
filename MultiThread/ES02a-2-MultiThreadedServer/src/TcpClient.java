import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient {
    private static final String HOST = "localhost";
    private static final int PORT = 5654;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT);  // 3-way handshake
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connessione avvenuta " + HOST + ":" + PORT);

            String userInput;
            while (true) {
                System.out.print("Scrivi un messaggio da inviare al server(scrivi 'quit' per disconnetterti): ");
                userInput = scanner.nextLine();
                if ("quit".equalsIgnoreCase(userInput)) break;

                out.println(userInput);           // Invia al server
                String response = in.readLine();  // Ricevi risposta
                System.out.println("Risposta da parte del server: " + response);
            }

        } catch (IOException e) {
            System.err.println("Errore client: " + e.getMessage());
        } // close() automatico → 4-way handshake

        System.out.println("Client disconnesso.");
    }
}