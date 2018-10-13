package Client;

import Interface.ChatService;

import java.rmi.RemoteException;
import java.util.Scanner;

public class MyRunnableSend extends Thread{



        ChatService impl;
        Controller controller;
        public MyRunnableSend(ChatService impl, Controller controller) {
            this.controller=controller;
            this.impl=impl;

        }

        public void send(){
            try {
                String fromUser=controller.messageSend.getText().toString();
                controller.messageSend.setText("");
                impl.sendMessage(Main.naam, fromUser);
                System.out.println("message send");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        public void run() {

        }


}
