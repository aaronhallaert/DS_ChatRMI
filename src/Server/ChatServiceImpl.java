package Server;

import Interface.ChatService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChatServiceImpl extends UnicastRemoteObject implements ChatService {


    public ChatServiceImpl() throws RemoteException{



    }

    @Override
    public synchronized void registerUser(String naam) throws RemoteException{
        Main.clients.put(naam, new Client(naam));
        for (Map.Entry<String, Client> entry : Main.clients.entrySet())
        {
            entry.getValue().getUsers().add(naam);
        }
        notifyAll();
    }

    @Override
    public synchronized void sendMessage(String naam, String message) throws RemoteException{

        if(message.charAt(0)=='\\'){
            String receiver= message.split(" ")[0].substring(1);
            String resultMessage=message.replace("\\","");

            Main.clients.get(receiver).getInbox().add("secret message from "+naam+": "+resultMessage.replace(String.valueOf(receiver), ""));
        }
        else {
            for (Map.Entry<String, Client> entry : Main.clients.entrySet()) {
                entry.getValue().getInbox().add(naam + ": " + message);

            }
        }
        notifyAll();

    }


    @Override
    public synchronized List<String> getUsers(String naam) throws RemoteException{
        while(Main.clients.get(naam).getUsers().isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        List<String> users= new ArrayList<>(Main.clients.get(naam).getUsers());
        Main.clients.get(naam).getUsers().clear();
        return users;
    }

    @Override
    public synchronized List<String> getMessage(String naam) throws RemoteException{
        while(Main.clients.get(naam).getInbox().isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        List<String> inbox= new ArrayList<>(Main.clients.get(naam).getInbox());
        Main.clients.get(naam).getInbox().clear();
        return inbox;
    }

}
