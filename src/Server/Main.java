package Server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

public class Main {


    public static Map<String,Client> clients;

    public static void main(String[] args){


        clients= new HashMap<>();



        //vast poortnummer
        try {
            // create on port 1099
            Registry registry = LocateRegistry.createRegistry (1099);
            // create a new service named CounterService
            registry.rebind("ChatService", new ChatServiceImpl());
        }
        catch (Exception e) {
            e.printStackTrace();
        }




        }



    }




