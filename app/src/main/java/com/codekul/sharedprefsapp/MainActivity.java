package com.codekul.sharedprefsapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends Activity {

    private TextView textData;
    private Button btnSharedPrefs, btnSharedPrefsShow, btnInternalSave, btnInternalShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textData = (TextView) findViewById(R.id.textData);

        btnSharedPrefs = (Button) findViewById(R.id.btnSharedPrefsSave);
        btnSharedPrefs.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                // /data/data/<package-name>/shared_prefs/my_prefs.xml

                SharedPreferences sharedPrefs = getSharedPreferences("my_prefs",MODE_APPEND);
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putBoolean("key_boolean",false);
                editor.putInt("key_int",10);
                editor.putString("key_String","codekul.com");
                editor.commit();
            }
        });

        btnSharedPrefsShow = (Button) findViewById(R.id.btnSharedPrefsShow);
        btnSharedPrefsShow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("my_prefs",MODE_PRIVATE);
                Boolean _boolean =  sharedPreferences.getBoolean("key_boolean",true);
                Integer integer = sharedPreferences.getInt("key_int",-3);
                String string = sharedPreferences.getString("key_String","none");

                Log.i("@codekul","boolean - "+_boolean +" integer - "+integer+" string - "+string);
            }
        });

        btnInternalSave = (Button) findViewById(R.id.btnInternalSave);
        btnInternalSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                try {
                    FileOutputStream fileOutputStream = openFileOutput("my.txt",MODE_APPEND);
                    String data = new String("{company : codekul.com}");
                    fileOutputStream.write(data.getBytes());
                    fileOutputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnInternalShow = (Button) findViewById(R.id.btnInternalShow);
        btnInternalShow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                try {
                    FileInputStream fileInputStream = openFileInput("my.txt");
                    StringBuilder builder = new StringBuilder();
                    while(true){
                        int ch = fileInputStream.read();
                        if(ch == -1) break;
                        else {
                            builder.append((char)ch);
                        }
                    }
                    textData.setText(builder.toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
