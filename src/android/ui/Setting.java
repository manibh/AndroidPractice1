package android.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.service.XmppClient;
import android.util.PropertiesManager;
import android.view.View;
import android.widget.TextView;
import com.mani.android.Practice1.R;

import java.util.Properties;

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
        Intent intent = new Intent(this,MainActivity.class);
        TextView fontTextView = (TextView) findViewById(R.id.setting_fontsize);
        TextView colorTextView = (TextView) findViewById(R.id.setting_fontcolor);
        TextView addressTextView = (TextView) findViewById(R.id.setting_address);
        TextView portTextView = (TextView) findViewById(R.id.setting_port);
        TextView userTextView = (TextView) findViewById(R.id.setting_user);
        TextView passwordTextView = (TextView) findViewById(R.id.setting_password);
        // should check for null values to try catch
        String fontSize=fontTextView.getText().toString();
        FONT_SIZE=Integer.valueOf(fontSize);
        Properties properties=new Properties();
        properties.put(XmppClient.SERVER_ADDRESS,addressTextView.getText().toString());
        properties.put(XmppClient.SERVER_PORT,portTextView.getText().toString());
        properties.put(XmppClient.USERNAME,userTextView.getText().toString());
        properties.put(XmppClient.PASSWORD,passwordTextView.getText().toString());
        PropertiesManager.storeProperties(this,properties,XmppClient.PROPERTIES_FILE,"xmpp credentials");
        startActivity(intent);

    }
}
