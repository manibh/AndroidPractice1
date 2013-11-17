package com.mani.android.Practice1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import org.jivesoftware.smack.*;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;

import java.util.HashMap;


public class MainActivity extends Activity {

    protected static final String SENT_MESSAGE = "com.mani.android.Practice1.MainActivity.message";
    private XmppClient xmppClient;
    private HashMap <String,String>map;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        map=new HashMap<String, String>();
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

        map.put(XmppClient.SERVER_ADDRESS,"manibh.dnsd.me");
        map.put(XmppClient.SERVER_PORT,"5222");
        map.put(XmppClient.USERNAME,"mani");
        map.put(XmppClient.PASSWORD,"mani");
        xmppClient = new XmppClient(map);
        xmppClient.sendMessageXMPP(message, "bahar@manis-macbook-pro.local");
        xmppClient.sendMessagePacket("bahar@manis-macbook-pro.local", message, Message.Type.chat);

        //start target activity with using given intent
        this.startActivity(intent);
    }


    void openSettings() {
        Intent intent = new Intent(this, com.mani.android.Practice1.Setting.class);
        startActivity(intent);
    }

}
