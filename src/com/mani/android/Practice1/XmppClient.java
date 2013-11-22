package com.mani.android.Practice1;

import android.os.StrictMode;
import android.util.Log;
import android.widget.EditText;
import org.jivesoftware.smack.*;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import java.util.HashMap;


/**
 * Created with IntelliJ IDEA.
 * User: Mani
 * Date: 17/11/2013
 * Time: 10:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class XmppClient {
    private XMPPConnection xmppConnection;
    protected static final String SERVER_ADDRESS="address";
    protected static final String SERVER_PORT="port";
    protected static final String USERNAME="username";
    protected static final String PASSWORD="password";

    public XmppClient(HashMap <String, String>map){
        xmppConnection=getConnection(map);
    }

    void sendMessagePacket(String to, String message, Message.Type type) {
//        XMPPConnection connection = getConnection();
        // Send chat msg to with msg type as (chat, normal, groupchat, headline, error)
        Message msg = new Message(to, type);
        msg.setBody(message);
        xmppConnection.sendPacket(msg);
//        receiveMessage(connection);
    }

    protected void sendMessageXMPP(String message, String targetUser, EditText editText) {
        // Create a connection to the jabber.org server.
//        XMPPConnection connection = getConnection();
        ChatManager chatmanager = xmppConnection.getChatManager();
        Chat newChat = chatmanager.createChat(targetUser, new MessageListener(editText));

        try {
            newChat.sendMessage(message);
            System.out.println("yohoo message sent");
//            receiveMessage(connection);
        } catch (XMPPException e) {
            System.out.println("Error Delivering block");
            System.out.println("----------sendMessageXMPP---------" + e.getMessage());
        } catch (Exception e) {
            System.out.println("----------sendMessageXMPP---------" + e.getMessage());
        }

    }

    protected void receiveMessage(XMPPConnection connection) {
        PacketFilter filter = new MessageTypeFilter(Message.Type.chat);
        connection.addPacketListener(new PacketListener() {
            public void processPacket(Packet packet) {
                Message message = (Message) packet;
                String body = message.getBody();
                String from = message.getFrom();
                System.out.println(from + "--------------------------->" + body);
            }
        }, filter);
    }

    private XMPPConnection getConnection(HashMap <String,String>properties) {

        String address= properties.get(SERVER_ADDRESS);
        int port=Integer.valueOf(properties.get(SERVER_PORT));
        String username=properties.get(USERNAME);
        String password=properties.get(PASSWORD);

        XMPPConnection.DEBUG_ENABLED = true;
        ConnectionConfiguration config = new ConnectionConfiguration(address,port);
        config.setCompressionEnabled(true);
        config.setSASLAuthenticationEnabled(true);
        config.setSelfSignedCertificateEnabled(true);

        XMPPConnection connection = new XMPPConnection(config);

        try {

            /*
            * @MBH
            * StrictMode.ThreadPolicy was introduced since API Level 9 and the default thread policy had been
            * changed since API Level 11, which in short, does not allow network operation (include HttpClient
             * and HttpUrlConnection) get executed on UI thread. if you do this, you get NetworkOnMainThreadException.
             *
             * also add following to manifest
             *  <uses-permission android:name="android.permission.INTERNET" />
             *
             *  ---->should do following on separate thread adding that policy here is bad practice
             *
            * */
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            connection.connect();
            connection.login(username, password);
        } catch (XMPPException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println("--------------------MANI-----------------------");
            System.out.println("--------------------getConnection-----------------------" + "\n" + e.getMessage());
        }
        return connection;
    }
}
class MessageListener implements org.jivesoftware.smack.MessageListener{

    private EditText editText;
    public MessageListener(EditText editText){
        this.editText=editText;
    }

    @Override
    public void processMessage(Chat chat, Message message) {
        //To change body of implemented methods use File | Settings | File Templates.
        String response=message.getBodies().toString();
        String from=message.getFrom();
        editText.append("/n"+from+":"+response);
        System.out.println("Received message: " + message.getBodies().toString());
        System.out.println("dobare manam: " + message.getBody() + "chat: " + chat.toString()
                + "chat participant" + chat.getParticipant());

        Log.d("DEBUG", message.getBody().toString());
    }
}