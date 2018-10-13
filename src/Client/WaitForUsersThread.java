package Client;

import Interface.ChatService;
import javafx.application.Platform;

import java.rmi.RemoteException;
import java.util.List;

public class WaitForUsersThread extends Thread {
    ChatService impl;
    Controller controller;
    public WaitForUsersThread(ChatService impl, Controller controller) {
        this.controller=controller;
        this.impl=impl;

    }



    public void run() {
        while(true) {
            try {
                for (String s : impl.getUsers(Main.naam)) {
                    Platform.runLater(() -> {
                        controller.addNewUser(s);
                    });

                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
