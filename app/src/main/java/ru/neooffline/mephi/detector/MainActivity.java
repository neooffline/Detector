package ru.neooffline.mephi.detector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        }
    public void settingsCall (View view){
        Intent toSetings = new Intent(this, AboutActivity.class);
        startActivity(toSetings);
    }
    public void sameActivity (View view){
        Intent toSameActivity = new Intent(this, SameActivity.class);
        startActivity(toSameActivity);
    }
}
