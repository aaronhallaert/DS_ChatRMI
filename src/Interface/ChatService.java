package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ChatService extends Remote {



        void sendMessage(String naam, String message) throws RemoteException;

        List<String> getMessage(String naam) throws RemoteException;

        void registerUser(String naam) throws RemoteException;

        List<String> getUsers(String naam) throws RemoteException;
}
