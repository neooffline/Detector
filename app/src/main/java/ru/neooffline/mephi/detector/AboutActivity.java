package ru.neooffline.mephi.detector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AboutActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
    public void backToMain (View view){
        Intent backToMain = new Intent(this, MainActivity.class);
        startActivity(backToMain);
    }
}
