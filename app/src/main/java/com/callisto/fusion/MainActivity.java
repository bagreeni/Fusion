package com.callisto.fusion;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FusionDatabase db = Room.databaseBuilder(getApplicationContext(),
                FusionDatabase.class, "fusion-db").build();
    }
}
