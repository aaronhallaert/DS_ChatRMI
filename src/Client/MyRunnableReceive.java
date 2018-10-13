package Client;

import Interface.ChatService;
import Server.ChatServiceImpl;
import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.Scanner;

public class MyRunnableReceive extends Thread{


    ChatService impl;
    Controller controller;
    public MyRunnableReceive(ChatService impl, Controller controller){
        this.impl=impl;
        this.controller=controller;
    }


    public void run(){
        while(true) {
            try {
                for (String s : impl.getMessage(Main.naam)) {
                    Platform.runLater(() -> {
                        controller.addMessage(s);
                    });

                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


}
