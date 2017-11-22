package com.drondon.androidforbeginners0910_lecture14;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        updateButton = findViewById(R.id.button);
    }
}
