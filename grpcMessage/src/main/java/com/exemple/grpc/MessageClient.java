package com.exemple.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

import com.exemple.messaging.*;

public class MessageClient {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 1000)
                .usePlaintext()
                .build();
        MessagingServiceGrpc.MessagingServiceBlockingStub stub = MessagingServiceGrpc.newBlockingStub(channel);

        System.out.println("saisir votre nom: ");
        String username = scanner.nextLine();

        boolean running = true;
        while (running) {
            System.out.println("Choisir une option: ");
            System.out.println("1.Voir Message Recu");
            System.out.println("2.Envoyer un message");
            System.out.println("3.exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    MessagingServiceOuterClass.GetMessagesRequest getRequest = MessagingServiceOuterClass.GetMessagesRequest.newBuilder()
                            .setUser(username)
                            .build();
                    MessagingServiceOuterClass.GetMessagesResponse getResponse = stub.getMessages(getRequest);
                    System.out.println("Messages pour " + username + ": " + getResponse.getMessagesList());
                    break;
                case 2:
                    System.out.print("Enter nom du destinataire: ");
                    String recipient = scanner.nextLine();
                    System.out.print("Entrer votre message: ");
                    String message = scanner.nextLine();

                    MessagingServiceOuterClass.SendMessageRequest sendRequest = MessagingServiceOuterClass.SendMessageRequest.newBuilder()
                            .setRecipient(recipient)
                            .setMessage(message)
                            .build();
                    MessagingServiceOuterClass.SendMessageResponse sendResponse = stub.sendMessage(sendRequest);
                     if(sendResponse.getSuccess()){
                       System.out.println("Message envoye avec succes ! " );
                    }else{
                       System.out.println("Echec d'envoi ! " );
                     }
                    break;
                case 3:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
        channel.shutdown();
    }
}
