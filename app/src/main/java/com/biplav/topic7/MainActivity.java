package com.biplav.topic7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    private Map<String,String> dictionary;
    Button btnaddWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listview);
        btnaddWord=findViewById(R.id.btnaddWord);
        dictionary=new HashMap<>();


        btnaddWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), AddWordActivity.class);
                startActivity(intent);
            }
        });

        //read all the words from file
        try {
            readFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(dictionary.keySet())
        );
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String key=parent.getItemAtPosition(position).toString();
                String meaning=dictionary.get(key);
                Intent intent=new Intent(getApplicationContext(), ViewActivity.class);
                intent.putExtra("meaning", meaning);
                startActivity(intent);
            }
        });
    }

    private void readFromFile() throws IOException {
        FileInputStream  fileInputStream= null;
        try {
            fileInputStream = openFileInput("words.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
        String line="";
        while ((line=bufferedReader.readLine())!=null)
        {
            String parts[]=line.split("->");
            dictionary.put(parts[0],parts[1]);

        }
    }
}
