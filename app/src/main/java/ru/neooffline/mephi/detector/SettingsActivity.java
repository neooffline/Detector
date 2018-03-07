package ru.neooffline.mephi.detector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class SettingsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
    public void backToMain2 (View view){
        Intent backToMain2 = new Intent(this, MainActivity.class);
        startActivity(backToMain2);
    }
}
