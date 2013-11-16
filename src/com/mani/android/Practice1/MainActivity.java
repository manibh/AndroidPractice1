package com.mani.android.Practice1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import org.jivesoftware.smack.*;
import org.jivesoftware.smack.packet.Message;


public class MainActivity extends Activity {

    public static final String SENT_MESSAGE = "com.mani.android.Practice1.MainActivity.message";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    /*
    @MBH
    Beginning with Android 3.0 (API level 11), the action bar is included in all activities that use the Theme.
    Holo theme (or one of its descendants), which is the default theme when either the targetSdkVersion or
    minSdkVersion attribute is set to "11" or greater.

      <uses-sdk android:minSdkVersion="17"/>
     */

    /*
    @MBH
    by overriding following method we can add our res/menu/main_activity_actions menu
    on create to Menu bar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }


    /*
    @MBH
    event handler for menu bar buttons
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
//                openSearch();
                return true;
            case R.id.action_settings:
                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /*
    @MBH
    event handler for send button, event handler will use method signature void <method name>(View ?)
    (reflection) to find this method with its given name.
     */
    public void sendButtonClicked(View view) {
        Intent intent = new Intent(this, com.mani.android.Practice1.ResultActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(SENT_MESSAGE, message);
        sendMessageXMPP(message,"bahar");
        sendMessagePacket("baher",message,Message.Type.chat);
        //start target activity with using given intent
        this.startActivity(intent);
    }

    void sendMessagePacket(String to,String message, Message.Type type){
        XMPPConnection connection = getConnection();
        // Send chat msg to with msg type as (chat, normal, groupchat, headline, error)
        Message msg = new Message(to, type);
        msg.setBody("How are you?");
        connection.sendPacket(msg);
    }

    void sendMessageXMPP(String message,String targetUser) {
        // Create a connection to the jabber.org server.
        ChatManager chatmanager = getConnection().getChatManager();
        Chat newChat = chatmanager.createChat(targetUser, new MessageListener() {
            public void processMessage(Chat chat, Message message) {
                System.out.println("Received message: " + message);
            }
        });

        try {
            newChat.sendMessage(message);
            System.out.println("yohoo message sent");
        } catch (XMPPException e) {
            System.out.println("Error Delivering block");
        } catch (Exception e){
            System.out.println("----------Mani---------" + e.getMessage());
        }

    }
// Create a connection to the jabber.org server on a specific port.

    private XMPPConnection getConnection() {
        ConnectionConfiguration config = new ConnectionConfiguration("manibh.dnsd.me", 5222);
        config.setCompressionEnabled(true);
        config.setSASLAuthenticationEnabled(true);
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
            connection.login("mani", "mani");
        } catch (XMPPException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            System.out.println(e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
//            System.out.println("--------------------MANI-----------------------");
            System.out.println("--------------------MANI-----------------------" + "\n" +e.getMessage());
        }
        return connection;
    }


    void openSettings() {
        Intent intent = new Intent(this, com.mani.android.Practice1.Setting.class);
        startActivity(intent);
    }

}
