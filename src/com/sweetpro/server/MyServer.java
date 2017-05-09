package com.sweetpro.server;

import com.sweetpro.entities.CommondAttr;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class MyServer {

    public static void main(String[] args){

        try {
            //1.
            ServerSocket serverSocket = new ServerSocket(CommondAttr.TCP_PORT);
            Socket socket = null;

            int count = 0;
            while(true){
                //2.
                socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();

                InetAddress inetAddress = socket.getInetAddress();
                System.out.println(inetAddress.getHostAddress());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
