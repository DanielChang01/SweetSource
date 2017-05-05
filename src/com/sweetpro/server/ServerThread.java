package com.sweetpro.server;

import java.io.*;
import java.net.Socket;


public class ServerThread extends Thread {

    Socket socket = null;

    public ServerThread(Socket socket){
        this.socket = socket;
    }

    public void run(){
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        OutputStream outputStream = null;
        PrintWriter printWriter = null;

        try {
            inputStream = socket.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String info = null;
            while((info = bufferedReader.readLine()) != null){
                getInfoHandled(info);
            }

            socket.shutdownInput();
            outputStream = socket.getOutputStream();
            printWriter = new PrintWriter(outputStream);
            printWriter.write("something");
            printWriter.flush();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (printWriter != null){
                    printWriter.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (bufferedReader != null){
                    bufferedReader.close();
                }
                if (inputStreamReader != null){
                    inputStreamReader.close();
                }
                if (inputStream != null){
                    inputStream.close();
                }
                if (socket != null){
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    private void getInfoHandled(String info) {
        String[] strings = info.split(";");
        int handleAttr = Integer.parseInt(strings[0]);
        switch(handleAttr%10)
        {
            case 1: //将数据进行持久化操作，返回持久化list集合
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }

        switch(handleAttr/10)
        {
            case 1://对持久化类进行插入数据库操作
                break;
        }
    }
}
