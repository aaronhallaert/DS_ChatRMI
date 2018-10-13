package Client;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.PrintWriter;


public class Controller {
    private MyRunnableSend sendThread;
    private PrintWriter out;

    @FXML
    public TextField messageSend;

    @FXML
    public Label messageBox;

    @FXML
    public Label usersUI;

    @FXML
    private Button sendButton;

    @FXML
    private Label userLabel;

    public Controller(){

    }

    public Label getUsersUI() {
        return usersUI;
    }

    public void setUsersUI(Label usersUI) {
        this.usersUI = usersUI;
    }

    public void setSendThread(MyRunnableSend sendThread) {
        this.sendThread = sendThread;
    }

    public void addMessage(String message){
        messageBox.setText(messageBox.getText().toString()+"\n"+message);
    }

    public void addNewUser(String naam){
        usersUI.setText(usersUI.getText().toString()+"\n"+naam);
    }

    public void setNameOnUI(String naam){
        userLabel.setText(naam);
    }
    public void sendMessageOnClick(){
        sendThread.send();
    }
    public Button getSendButton(){
        return sendButton;
    }

    public TextField getMessageSend(){
        return messageSend;
    }

    public Label getMessageBox() {
        return messageBox;
    }
}
