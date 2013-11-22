package com.mani.android.Practice1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Mani
 * Date: 14/11/2013
 * Time: 10:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class Setting extends Activity {
    protected static final String FONT_COLOR = "com.mani.android.Practice1.setting.color";
    protected static int FONT_SIZE = 40;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        setContentView(R.layout.setting);
//        xmppProperties=new HashMap<String, String>();
    }

    public void setButton(View view) {
        Intent intent = new Intent(this,com.mani.android.Practice1.MainActivity.class);
        TextView fontTextView = (TextView) findViewById(R.id.setting_fontsize);
        TextView colorTextView = (TextView) findViewById(R.id.setting_fontcolor);
        TextView addressTextView = (TextView) findViewById(R.id.setting_address);
        TextView portTextView = (TextView) findViewById(R.id.setting_port);
        TextView userTextView = (TextView) findViewById(R.id.setting_user);
        TextView passwordTextView = (TextView) findViewById(R.id.setting_password);
        // should check for null values to try catch
        String fontSize=fontTextView.getText().toString();
        FONT_SIZE=Integer.valueOf(fontSize);
        MainActivity.map.put(XmppClient.SERVER_ADDRESS,addressTextView.getText().toString());
        MainActivity.map.put(XmppClient.SERVER_PORT,portTextView.getText().toString());
        MainActivity.map.put(XmppClient.USERNAME,userTextView.getText().toString());
        MainActivity.map.put(XmppClient.PASSWORD,passwordTextView.getText().toString());
        startActivity(intent);

    }
}
