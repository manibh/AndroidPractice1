package test;

import org.jivesoftware.smack.*;
import org.jivesoftware.smack.packet.Message;

/**
 * Created with IntelliJ IDEA.
 * User: Mani
 * Date: 15/11/2013
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestMain implements Runnable {
    private Connection connection;

    public TestMain() {
        setConnection();
        setChatManager();
    }

    void setConnection() {
        ConnectionConfiguration config = new ConnectionConfiguration("manibh.dnsd.me", 5222);
        connection = new XMPPConnection(config);
        try {
            connection.connect();
            connection.login("mani","mani");
        } catch (XMPPException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    void setChatManager() {
        // Assume we've created a Connection name "connection".
        ChatManager chatmanager = connection.getChatManager();
        Chat newChat = chatmanager.createChat("bahar", new MessageListener() {
            public void processMessage(Chat chat, Message message) {
                System.out.println("Received message: " + message);
            }
        });

        try {
            newChat.sendMessage("Howdy! salam manam mani");
            System.out.println("yohoo message sent");
        } catch (XMPPException e) {
            System.out.println("Error Delivering block");
        }
    }

    public static void main(String args[]) {

        TestMain test = new TestMain();

    }


    @Override
    public void run() {
        //To change body of implemented methods use File | Settings | File Templates.

    }
}
