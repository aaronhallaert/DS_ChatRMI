package Client;

import Interface.ChatService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Main extends Application {


    public static String naam;
    Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Hello World");
        Scene scene= new Scene(root, 720, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        controller = loader.getController();
        
        Scanner sc = new Scanner(System.in);

        // setup connection

        try {
            // fire to localhost port 1099
            Registry myRegistry = LocateRegistry.getRegistry("localhost", 1099);
            // search for ChatService
            ChatService impl = (ChatService) myRegistry.lookup("ChatService");

            System.out.println("geef je naam in");
            naam= sc.nextLine();

            controller.setNameOnUI(naam);
            impl.registerUser(naam);

            MyRunnableReceive mThReceive= new MyRunnableReceive(impl, controller);
            mThReceive.start();

            MyRunnableSend mThSend= new MyRunnableSend(impl, controller);
            mThSend.start();
            controller.setSendThread(mThSend);

            /*
            WaitForUsersThread mThUsers=new WaitForUsersThread(impl, controller);
            mThUsers.start();
            */


        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public Controller getController(){
        return  controller;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
