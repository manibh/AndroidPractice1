package android.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.mani.android.Practice1.R;

/**
 * Created with IntelliJ IDEA.
 * User: Mani
 * Date: 7/11/2013
 * Time: 2:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResultActivity extends Activity {

    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        //to enable Up button
        getActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent= getIntent();
        String message= intent.getStringExtra(MainActivity.SENT_MESSAGE);
        TextView textView= new TextView(this);
        textView.setText(message);
        textView.setTextSize(Setting.FONT_SIZE);
        setContentView(textView);


    }


    /* Adding up button to navigate to home screen (Activity of your app)
    When running on Android 4.1 (API level 16) or higher, or when using ActionBarActivity from the Support Library,
    performing Up navigation simply requires that you declare the parent activity in the manifest file and enable
    the Up button for the action bar.

    <activity
        android:name="com.example.myfirstapp.DisplayMessageActivity"
        android:label="@string/title_activity_display_message"
        android:parentActivityName="com.example.myfirstapp.MainActivity" >
        <!-- Parent activity meta-data to support 4.0 and lower -->
        <meta-data
            android:name="android.support.PARENT_ACTIVITY"
            android:value="com.example.myfirstapp.MainActivity" />
    </activity>

    Then enable the app icon as the Up button by calling getActionBar().setDisplayHomeAsUpEnabled(true);
    in onCreate() of activity
     */
}