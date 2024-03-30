import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String userName;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            userName = in.readLine();
            out.println("Welcome to the chat "+userName+"! Enter your message or type 'exit' to quit.");

            System.out.println(userName + " has joined the chat.");

            String message;
            while ((message = in.readLine()) != null) {
                if (message.equalsIgnoreCase("exit")) {
                    out.println("You have left the chat.");
                    break; // Exit the loop if user types "exit"
                }
                System.out.println(userName + ": " + message);
                ChatServer.broadcastMessage(userName + ": " + message, this);
            }
            clientSocket.close(); // Close the client's socket
            System.out.println(userName + " has left the chat.");
            ChatServer.broadcastMessage(userName + " has left the chat.", this);
            ChatServer.clients.remove(this);
        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        } 
    }

    public void sendMessage(String message) {
        out.println(message);
    }

   
}
