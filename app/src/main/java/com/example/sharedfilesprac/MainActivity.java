package com.example.sharedfilesprac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final String My_PREF = "My_PREF";
    EditText name, value;
    Button saveB, showB, showAllB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.name);
        value = (EditText) findViewById(R.id.value);
        saveB = (Button) findViewById(R.id.saveB);
        showB = (Button) findViewById(R.id.showB);
        showAllB = (Button) findViewById(R.id.showAllB);
        saveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameStr = name.getText().toString();
                String valueStr = value.getText().toString();
                SharedPreferences settings;
                SharedPreferences.Editor editor;
                settings = MainActivity.this.getSharedPreferences(
                        My_PREF, Context.MODE_PRIVATE);
                editor = settings.edit();
                editor.putString(nameStr, valueStr);
                editor.commit();
                Toast.makeText(MainActivity.this, nameStr
                        + "/" + valueStr, Toast.LENGTH_LONG).show();
            }
        });
        showB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameStr = name.getText().toString();
                SharedPreferences settings =
                        MainActivity.this.getSharedPreferences(
                                My_PREF, Context.MODE_PRIVATE);
                String valueStr = settings.getString(nameStr, null);
                value.setText(valueStr);
                Toast.makeText(MainActivity.this, nameStr + "/"
                        +valueStr, Toast.LENGTH_LONG).show();
            }
        });
        showAllB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences settings =
                        getSharedPreferences(My_PREF, MODE_PRIVATE);
                Map<String, ?> allEntries = settings.getAll();
                for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                    Log.d("map values", entry.getKey() + ": " +
                            entry.getValue().toString());
                    Toast.makeText(MainActivity.this, entry.getKey() +"/"
                                    +entry.getValue().toString(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}