package ru.neooffline.mephi.detector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by user on 02.03.2018.
 */
public class SameActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_same_page);
    }
    public void backToMain (View view){
        Intent backToMain = new Intent(this, MainActivity.class);
        startActivity(backToMain);
    }
}
