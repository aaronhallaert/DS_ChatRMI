package Server;



import java.util.LinkedList;
import java.util.List;


public class Client {
    private List<String> inbox;
    private List<String> users;
    private String naam;


    public Client(List<String> inbox, List<String> users,  String naam) {
        this.inbox = inbox;
        this.users=users;
        this.naam=naam;

    }

    public Client(String naam){
        this.inbox=new LinkedList<String>();
        this.users=new LinkedList<String>();
        this.naam=naam;

    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public List<String> getInbox() {
        return inbox;
    }

    public void setInbox(List<String> inbox) {
        this.inbox = inbox;
    }



}
