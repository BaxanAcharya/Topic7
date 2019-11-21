package com.biplav.topic7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class AddWordActivity extends AppCompatActivity {

    EditText etword, etmeaning;
    Button btnAddWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        btnAddWord=findViewById(R.id.btnadd);
        etword=findViewById(R.id.etword);
        etmeaning=findViewById(R.id.etmean);
        btnAddWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    private void save()
    {
        PrintStream printStream= null;
        try {
            printStream = new PrintStream(openFileOutput("words.txt", MODE_PRIVATE | MODE_APPEND));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        printStream.println(etword.getText().toString()+"->"+etmeaning.getText().toString());
        Toast.makeText(this, "Saved to" + getFilesDir(), Toast.LENGTH_SHORT).show();
    }
}
