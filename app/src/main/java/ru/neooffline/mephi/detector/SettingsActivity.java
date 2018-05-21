package ru.neooffline.mephi.detector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SettingsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
//    TextView readVar_detName = findViewById(R.id.detName_var);
    public void backToMain2 (View view){
        Intent backToMain2 = new Intent(this, MainActivity.class);
        startActivity(backToMain2);
    }
}
