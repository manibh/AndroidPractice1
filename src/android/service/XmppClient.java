package android.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.IBinder;
import android.os.StrictMode;
import android.util.Log;
import android.ui.MainActivity;
import android.util.PropertiesManager;
import org.jivesoftware.smack.*;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.util.Base64;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Properties;


/**
 * Created with IntelliJ IDEA.
 * User: Mani
 * Date: 17/11/2013
 * Time: 10:59 AM
 * To change this template use File | Settings | File Templates.
 */
//it must extends service
public class XmppClient extends Service{
    private XMPPConnection xmppConnection;
    public static final String SERVER_ADDRESS="address";
    public static final String SERVER_PORT="port";
    public static final String USERNAME="username";
    public static final String PASSWORD="password";
    public static final String PROPERTIES_FILE="xmpp-properties.properties";


    public XmppClient(){
        init();
    }

    public void init(){
        Properties properties=new Properties();
        properties.put(XmppClient.SERVER_ADDRESS,"manibh.dnsd.me");
        properties.put(XmppClient.SERVER_PORT,"5222");
        properties.put(XmppClient.USERNAME,"mani");
        properties.put(XmppClient.PASSWORD,"mani");
        PropertiesManager.storeProperties(this,properties,XmppClient.PROPERTIES_FILE,"xmpp credentials");
        xmppConnection=getConnection();
    }
    public void sendMessagePacket(String to, String message, Message.Type type) {
//        XMPPConnection connection = getConnection();
        // Send chat msg to with msg type as (chat, normal, groupchat, headline, error)
        Message msg = new Message(to, type);
        msg.setBody(message);
        xmppConnection.sendPacket(msg);
//        receiveMessage(connection);
    }

    public void sendMessageXMPP(String message, String targetUser, MainActivity mainActivity) {
        // Create a connection to the jabber.org server.
//        XMPPConnection connection = getConnection();
        ChatManager chatmanager = xmppConnection.getChatManager();
        Chat newChat = chatmanager.createChat(targetUser, new MessageListener(mainActivity));

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

    private XMPPConnection getConnection() {
        Properties properties=PropertiesManager.loadProperties(this,PROPERTIES_FILE);
        String address= (String) properties.get(SERVER_ADDRESS);
        int port=(Integer) properties.get(SERVER_PORT);
        String username= (String) properties.get(USERNAME);
        String password= (String) properties.get(PASSWORD);

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

    @Override
    public IBinder onBind(Intent intent) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}

class MessageListener implements org.jivesoftware.smack.MessageListener{

    MainActivity mainActivity;
    public MessageListener(MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }

    @Override
    public void processMessage(Chat chat, Message message) {
        String user=chat.getParticipant();
        String msg=message.getBody().toString();

        mainActivity.setChatLog(user, msg);
        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //To change body of implemented methods use File | Settings | File Templates.
                System.out.println("test");
            }
        });
        Log.d("DEBUG", message.getBody().toString());
    }
}