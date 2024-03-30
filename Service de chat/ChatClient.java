import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to the chat server.");

            System.out.print("Enter your username: ");
            String userName = consoleReader.readLine();
            out.println(userName);

            Thread receiveMessagesThread = new Thread(() -> {
                String serverResponse;
                try {
                    while ((serverResponse = in.readLine()) != null) {
                        System.out.println(serverResponse);
                    }
                } catch (IOException e) {
                    System.err.println("Error receiving messages: " + e.getMessage());
                }
            });
            receiveMessagesThread.start();

            String message;
            while ((message = consoleReader.readLine()) != null) {
                out.println(message);
            }

        } catch (IOException e) {
            System.err.println("Error in the client: " + e.getMessage());
        }
    }
}
