package ru.neooffline.mephi.detector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

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
    protected void textFill (View view) {
        Intent change_uDat = new Intent(this, MainActivity.class);
        startActivity(change_uDat);
        Integer a = 15;
        Integer b = a + 5;
        TextView change_uDat_var = (TextView) findViewById(R.id.uDat_var);
        change_uDat_var.setText(b);

    }
}
