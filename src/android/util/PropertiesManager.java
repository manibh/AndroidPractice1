package android.util;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Mani
 * Date: 27/11/2013
 * Time: 3:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class PropertiesManager{

    public static void storeProperties(Context context,Properties properties, String fileName, String comment){
        FileOutputStream fos=null;
        try {
            fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            properties.store(fos,comment);
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }  finally {
            assert fos != null;
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

        }
    }

    public static Properties loadProperties(Context context, String fileName){
        FileInputStream fis=null;
        Properties pp=new Properties();
        try{
            fis=context.openFileInput(fileName);
            pp.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            assert fis != null;
            try{
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return pp;

    }

}
