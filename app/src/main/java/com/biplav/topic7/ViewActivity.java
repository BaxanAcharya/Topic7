package com.biplav.topic7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        textView=findViewById(R.id.tvmeaning);
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null)
        {
            String meaning=bundle.getString("meaning");
            textView.setText(meaning);

        }
    }
}
