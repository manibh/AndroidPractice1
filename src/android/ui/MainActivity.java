package android.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.service.XmppClient;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.mani.android.Practice1.R;

import java.util.HashMap;


public class MainActivity extends Activity {

    protected static final String SENT_MESSAGE = "android.ui.MainActivity.message";
    private XmppClient xmppClient;
    protected static HashMap <String,String>map;
    private static String chatLogText=new String();
    protected static TextView chatLog;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        map=new HashMap<String, String>();
        chatLog= (TextView)findViewById(R.id.main_chatlog);
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
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        xmppClient=new XmppClient();
        xmppClient.sendMessageXMPP(message, "bahar@manis-macbook-pro.local", this);
//        xmppClient.sendMessagePacket("bahar@manis-macbook-pro.local", message, Message.Type.chat);
        setChatLog(this.map.get(XmppClient.USERNAME),message);
        //start target activity with using given intent
//        Intent intent = new Intent(this, android.ui.ResultActivity.class);
//        intent.putExtra(SENT_MESSAGE, message);
//        this.startActivity(intent);
    }


    void openSettings() {
        Intent intent = new Intent(this, Setting.class);
        startActivity(intent);
    }


    public static void setChatLog(String user,String chat) {
        String temp=chatLogText + user + ":"+chat +"\n";
        System.out.println(temp);
        chatLog.append(chatLogText + user + ":"+chat +"\n");
    }

}
