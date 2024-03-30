package com.exemple.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.io.IOException;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class MessageServer {
  public static void main(String[] args) throws Exception {
    Server server = ServerBuilder.forPort(1000)
        .addService(new MessageImpl())
        .build()
        .start();
    System.out.println("Server listening on port 1000...");
    server.awaitTermination();
  }
}