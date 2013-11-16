package com.mani.android.Practice1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: Mani
 * Date: 14/11/2013
 * Time: 10:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class Setting extends Activity {
    public static final String FONT_COLOR = "com.mani.android.Practice1.setting.color";
    public static int FONT_SIZE = 40;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        setContentView(R.layout.setting);

    }

    public void setButton(View view) {
        Intent intent = new Intent(this,com.mani.android.Practice1.MainActivity.class);
        TextView fontTextView = (TextView) findViewById(R.id.setting_fontsize);
        TextView colorTextView = (TextView) findViewById(R.id.setting_fontcolor);
        String fontSize=fontTextView.getText().toString();
//        intent.putExtra(FONT_SIZE,fontSize);
        FONT_SIZE=Integer.valueOf(fontSize);

        startActivity(intent);

    }
}
