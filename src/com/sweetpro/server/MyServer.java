package com.sweetpro.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by danielchang on 2017/5/5.
 */
public class MyServer {

    public static void main(String[] args){

        try {
            //1.
            ServerSocket serverSocket = new ServerSocket(54321);
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
